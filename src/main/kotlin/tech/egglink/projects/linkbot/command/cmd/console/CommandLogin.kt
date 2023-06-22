package tech.egglink.projects.linkbot.command.cmd.console

import tech.egglink.projects.linkbot.command.CommandHandler
import tech.egglink.projects.linkbot.command.CommandResult
import tech.egglink.projects.linkbot.command.CommandSender
import tech.egglink.projects.linkbot.command.ConsoleCommandSender
import tech.egglink.projects.linkbot.utils.Utils

/**
 * 命令: login
 *
 * 功能: 登录机器人
 *
 * 作用于: [ConsoleCommandSender]
 *
 * 参数: 无
 * */
class CommandLogin : CommandHandler(
    Entry().apply {
        name = "login"
        usage = Utils.message.command.loginUsage
        description = Utils.message.command.loginDescription
    }
) {
    override suspend fun execute(sender: CommandSender, args: Array<String>): CommandResult {
        try {
            if (Utils.bot.getBot() == null || Utils.bot.getBot()?.isOnline == false) {
                Utils.bot.login(Utils.config.account.id, Utils.config.account.password)
            } else {
                sender.sendMessage(Utils.message.error.isOnline)
            }
        } catch (e: Exception) {
            return CommandResult.ERROR
        }
        return CommandResult.SUCCESS
    }
}
