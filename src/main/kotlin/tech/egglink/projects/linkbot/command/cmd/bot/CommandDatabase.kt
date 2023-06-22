package tech.egglink.projects.linkbot.command.cmd.bot

import tech.egglink.projects.linkbot.command.BotCommandSender
import tech.egglink.projects.linkbot.command.CommandHandler
import tech.egglink.projects.linkbot.command.CommandResult
import tech.egglink.projects.linkbot.command.CommandSender
import tech.egglink.projects.linkbot.utils.Utils

/**
 * 命令: database
 *
 * 功能: 查询数据库
 *
 * 作用于: [BotCommandSender]
 *
 * 参数: [[Int]] = 10000
 * */
class CommandDatabase : CommandHandler(
    Entry().apply {
        name = "database"
        aliases = arrayOf("db")
        usage = Utils.message.command.databaseUsage
        description = Utils.message.command.databaseDescription
        argsType = listOf("Int")
    }
) {
    override suspend fun execute(sender: CommandSender, args: Array<String>): CommandResult {
        val qq = args[0].toLong()
        val result = Utils.database.getUserDataByQQId(qq)
        if (result == null) { // 空 （无对应用户）
            sender.sendMessage(Utils.message.error.notFoundUser)
            return CommandResult.FAILURE
        }
        val permission = StringBuilder()
        result.permissionTrue.forEach {
            permission.append(it).append(", ")
        }
        permission.removeSuffix(", ")
        sender.sendMessage(Utils.message.info.database.format(result.qqId, permission.toString()))
        return CommandResult.SUCCESS
    }
}
