package tech.egglink.projects.linkbot.command.cmd.console

import tech.egglink.projects.linkbot.command.CommandHandler
import tech.egglink.projects.linkbot.command.CommandResult
import tech.egglink.projects.linkbot.command.CommandSender
import tech.egglink.projects.linkbot.command.ConsoleCommandSender
import tech.egglink.projects.linkbot.utils.Utils

/**
 * 命令: logout
 *
 * 功能: 退出登录机器人
 *
 * 作用于: [ConsoleCommandSender]
 *
 * 参数: 无
 * */
class CommandLogout : CommandHandler(
    Entry().apply {
        name = "logout"
        usage = Utils.message.command.logoutUsage
        description = Utils.message.command.logoutDescription
    }
) {
    override suspend fun execute(sender: CommandSender, args: Array<String>): CommandResult {
        try {
            Utils.bot.logout()
        } catch (e: Exception) {
            return CommandResult.ERROR
        }
        return CommandResult.SUCCESS
    }
}
