package tech.egglink.projects.linkbot.command.cmd.console

import tech.egglink.projects.linkbot.bot.BotLoginSolver
import tech.egglink.projects.linkbot.command.CommandHandler
import tech.egglink.projects.linkbot.command.CommandResult
import tech.egglink.projects.linkbot.command.CommandSender
import tech.egglink.projects.linkbot.command.ConsoleCommandSender
import tech.egglink.projects.linkbot.utils.Utils

/**
 * 命令: captcha
 *
 * 功能: 输入验证码
 *
 * 作用于: [ConsoleCommandSender]
 *
 * 参数: [String] 验证码类型, [String] 验证码内容
 * */
class CommandCaptcha : CommandHandler(
    Entry().apply {
        name = "captcha"
        aliases = arrayOf("c")
        usage = Utils.message.command.captchaUsage
        description = Utils.message.command.captchaDescription
        argsType = listOf("String", "String")
    }
) {
    override suspend fun execute(sender: CommandSender, args: Array<String>): CommandResult {
        when (args[0]) {
            "pic" -> {
                (Utils.bot.getBot()?.bot?.configuration?.loginSolver as BotLoginSolver).setCaptchaPic(args[1])
            }
            "slider" -> {
                (Utils.bot.getBot()?.bot?.configuration?.loginSolver as BotLoginSolver).setCaptchaSlider(args[1])
            }
            else -> {
                return CommandResult.INVALID_USAGE
            }
        }
        return CommandResult.SUCCESS
    }
}
