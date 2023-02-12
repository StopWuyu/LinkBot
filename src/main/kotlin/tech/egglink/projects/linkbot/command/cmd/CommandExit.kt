package tech.egglink.projects.linkbot.command.cmd

import tech.egglink.projects.linkbot.command.CommandHandler
import tech.egglink.projects.linkbot.command.CommandResult
import tech.egglink.projects.linkbot.command.CommandSender
import tech.egglink.projects.linkbot.command.ConsoleCommandSender
import tech.egglink.projects.linkbot.utils.Utils
import kotlin.system.exitProcess

/**
 * 命令: exit
 *
 * 功能: 退出程序
 *
 * 作用于: [ConsoleCommandSender]
 *
 * 参数: 无
 * */
class CommandExit: CommandHandler(Entry().apply {
    name = "exit"
    description = Utils.message.command.exitDescription
    usage = Utils.message.command.exitUsage
    aliases = arrayOf("quit")
    argsType = listOf()
}) {
    override suspend fun execute(sender: CommandSender, args: Array<String>): CommandResult {
        exitProcess(0)
    }
}