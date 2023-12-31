package tech.egglink.projects.linkbot.event.evts

import kotlinx.coroutines.runBlocking
import net.mamoe.mirai.event.events.GroupMessageEvent
import tech.egglink.projects.linkbot.command.BotCommandSender
import tech.egglink.projects.linkbot.command.CommandResult
import tech.egglink.projects.linkbot.dataprovider.UserData
import tech.egglink.projects.linkbot.event.Event
import tech.egglink.projects.linkbot.event.EventType
import tech.egglink.projects.linkbot.utils.Utils
import net.mamoe.mirai.event.Event as nEvent

/**
 * 群消息事件
 *
 * @property type 事件类型
 * @property execute 执行事件
 * */
class EventGroupMessage : Event {
    override fun execute(event: nEvent) {
        event as GroupMessageEvent // 转换为 GroupMessageEvent
        try {
            // 数据操作
            if (Utils.database.getUserDataByQQId(event.sender.id) == null) {
                Utils.database.insertUserData(
                    UserData().apply {
                        qqId = event.sender.id
                        permissionTrue = Utils.config.permission.defaultTrue.toTypedArray()
                    }
                )
            }
        } catch (e: Exception) {
            Utils.logger.error(Utils.message.error.unknownError)
            e.printStackTrace()
        }
        val text = event.message.serializeToMiraiCode() // 获取消息内容
        val prefix = Utils.config.bot.commandPrefix
        var isCmd = false
        prefix.forEach {
            if (text.startsWith(it)) {
                isCmd = true
            }
        }
        if (!isCmd) {
            return
        }
        val clear = text.substring(1)
        Utils.logger.debug("群消息: ${event.group.id}(${event.group.name}) - ${event.sender.id}(${event.sender.nick}) - $text")
        val args = clear.split(" ")
        val name = args[0]
        var skip = true
        var argCmd = arrayOf<String>()
        args.forEach {
            if (!skip) {
                argCmd = argCmd.plus(it)
            }
            skip = false
        }
        runBlocking {
            when (
                Utils.botCmd.runCommand(
                    name,
                    object : BotCommandSender { // 匿名类
                        override val sender = event.sender
                        override val group = event.group
                        override val sendToGroup = true

                        override fun hasPermission(permission: String): Boolean { // 调用数据库判断
                            return Utils.database.getUserDataByQQId(event.sender.id)?.permissionTrue?.contains(permission) ?: false
                        }
                    },
                    argCmd
                )
            ) {
                CommandResult.NOT_FOUND -> Utils.logger.debug(Utils.message.error.unknownCommand)
                CommandResult.NO_PERMISSION -> event.group.sendMessage(Utils.message.error.noPermission)
                CommandResult.SUCCESS -> {
                    // 成功
                }

                CommandResult.ERROR -> Utils.logger.debug(Utils.message.error.unknownError)
                CommandResult.INVALID_USAGE -> event.group.sendMessage(Utils.message.error.invalidArgs)
                CommandResult.FAILURE -> {
                    // 失败
                }
            }
        }
    }

    override val type: EventType = EventType.BOT_GROUP_RECEIVE_MESSAGE
}
