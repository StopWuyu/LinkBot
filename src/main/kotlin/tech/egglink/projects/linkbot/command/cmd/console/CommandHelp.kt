package tech.egglink.projects.linkbot.command.cmd.console

import tech.egglink.projects.linkbot.command.CommandHandler
import tech.egglink.projects.linkbot.command.CommandResult
import tech.egglink.projects.linkbot.command.CommandSender
import tech.egglink.projects.linkbot.command.CommandType
import tech.egglink.projects.linkbot.command.ConsoleCommandSender
import tech.egglink.projects.linkbot.utils.Utils
import tech.egglink.projects.linkbot.utils.Utils.consoleCmd
import kotlin.math.ceil

/**
 * 命令: help
 *
 * 功能: 显示帮助信息
 *
 * 作用于: [ConsoleCommandSender]
 *
 * 参数: [[Int]] = 1
 * */
class CommandHelp : CommandHandler(
    Entry().apply {
        name = "help"
        aliases = arrayOf("h")
        usage = Utils.message.command.helpUsage
        description = Utils.message.command.helpDescription
        argsType = listOf("Int")
        defaultArgs = arrayOf("1")
    }
) {
    override suspend fun execute(sender: CommandSender, args: Array<String>): CommandResult {
        val page = args[0].toInt()
        val commands = consoleCmd.getCommands()
        val maxPage = ceil(commands.size.toDouble() / Utils.config.setting.helpCountEachPage.toDouble())
        if (page > maxPage) {
            // 页码超出范围
            sender.sendMessage(Utils.message.info.helpPageOutOfIndex)
            return CommandResult.FAILURE
        }
        if (page < 1) {
            // 页码超出范围
            sender.sendMessage(Utils.message.info.helpPageOutOfIndex)
            return CommandResult.FAILURE
        }
        val message = StringBuilder()
        message.append("\n" + Utils.message.info.helpTextTitle + "\n")
        for (i in (page - 1) * Utils.config.setting.helpCountEachPage until page * Utils.config.setting.helpCountEachPage) {
            if (i >= commands.size) {
                break
            }
            if (commands[i].entry.type != CommandType.Console) {
                // 仅显示控制台命令
                continue
            }
            message.append(
                Utils.message.info.helpTextTemplate.format(
                    commands[i].entry.name,
                    commands[i].entry.aliases.joinToString(", "),
                    commands[i].entry.usage,
                    commands[i].entry.description
                )
            )
        }
        message.append(Utils.message.info.helpTextEnd.format(page, maxPage.toInt()))
        sender.sendMessage(message.toString())
        return CommandResult.SUCCESS
    }
}
