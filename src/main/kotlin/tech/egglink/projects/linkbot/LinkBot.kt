package tech.egglink.projects.linkbot

import kotlinx.coroutines.runBlocking
import org.jline.reader.EndOfFileException
import org.jline.reader.LineReader
import org.jline.reader.LineReaderBuilder
import org.jline.reader.UserInterruptException
import org.jline.terminal.Terminal
import org.jline.terminal.TerminalBuilder
import tech.egglink.projects.linkbot.command.CommandResult
import tech.egglink.projects.linkbot.command.ConsoleCommandSender
import tech.egglink.projects.linkbot.event.EventListener
import tech.egglink.projects.linkbot.event.evts.EventGroupMessage
import tech.egglink.projects.linkbot.utils.Utils
import tech.egglink.projects.linkbot.utils.Utils.botCmd
import tech.egglink.projects.linkbot.utils.Utils.consoleCmd
import tech.egglink.projects.linkbot.utils.Utils.logger
import java.io.File

object LinkBot {
    /**
     * 设置关闭钩子
     * */
    private fun setShutdownHook() {
        Runtime.getRuntime().addShutdownHook(
            Thread {
                logger.info(Utils.message.other.exit)
                Utils.bot.logout()
            }
        )
    }

    /**
     * 程序入口类
     * @param arg 命令行参数
     * */
    @JvmStatic
    fun main(arg: Array<String>) {
        setShutdownHook()
        logger.info(Utils.message.other.startInfo)
        var isLastInterrupted = false
        // TODO: 2023/2/12 读取插件
        Utils.config.loadConfig() // 加载配置文件
        // 创建parent目录
        File(Utils.config.path.data).mkdirs()
        File(Utils.config.path.plugins).mkdirs()
        logger.info(Utils.message.other.loadingConfig)
        logger.info(Utils.message.other.loadingData)
        if (Utils.config.bot.autoLogin) {
            logger.info(Utils.message.other.loadingBot)
            Utils.bot.login(Utils.config.account.id, Utils.config.account.password) // 登录机器人
        }
        logger.info(Utils.message.other.loadingEvents)
        EventListener.beginListen() // 开始监听事件
        Utils.event.registerEvents(
            EventGroupMessage()
        ) // 注册所有事件
        logger.info(Utils.message.other.loadingCommand)
        consoleCmd.registerCommands() // 注册所有命令
        botCmd.registerBotCommands() // 注册所有机器人命令
        var terminal: Terminal? = null // 终端
        try {
            terminal = TerminalBuilder.builder().jna(true).build()
        } catch (e: Exception) {
            try {
                terminal = TerminalBuilder.builder().dumb(true).build()
            } catch (ignored: Exception) {
            }
        }
        logger.info(Utils.message.other.startSuccess) // 启动成功

        val reader: LineReader = LineReaderBuilder.builder().terminal(terminal).build()
        // 主线程
        while (true) {
            try {
                reader.readLine("> ").let { s ->
                    val args = s.split(" ")
                    if (args.size > 1) {
                        val name = args[0]
                        var argCmd = arrayOf<String>()
                        var skip = true
                        args.forEach {
                            if (!skip) {
                                argCmd = argCmd.plus(it)
                            }
                            skip = false
                        }
                        runBlocking {
                            when (consoleCmd.runCommand(name, ConsoleCommandSender(), argCmd)) {
                                CommandResult.NOT_FOUND -> logger.error(Utils.message.error.unknownCommand)
                                CommandResult.NO_PERMISSION -> logger.error(Utils.message.error.noPermission)
                                CommandResult.SUCCESS -> {
                                    // 成功
                                }

                                CommandResult.ERROR -> logger.error(Utils.message.error.unknownError)
                                CommandResult.INVALID_USAGE -> logger.error(Utils.message.error.invalidArgs)
                                CommandResult.FAILURE -> {
                                    // 失败
                                }
                            }
                        }
                    } else {
                        val name = args[0]
                        runBlocking {
                            when (consoleCmd.runCommand(name, ConsoleCommandSender(), arrayOf())) {
                                CommandResult.NOT_FOUND -> logger.error(Utils.message.error.unknownCommand)
                                CommandResult.NO_PERMISSION -> logger.error(Utils.message.error.noPermission)
                                CommandResult.SUCCESS -> {
                                    // 成功
                                }

                                CommandResult.ERROR -> logger.error(Utils.message.error.unknownError)
                                CommandResult.INVALID_USAGE -> logger.error(Utils.message.error.invalidArgs)
                                CommandResult.FAILURE -> {
                                    // 失败
                                }
                            }
                        }
                    }
                }
                isLastInterrupted = false
            } catch (e: UserInterruptException) {
                if (!isLastInterrupted) {
                    isLastInterrupted = true
                    logger.warn(Utils.message.other.interrupted)
                    continue
                } else {
                    Runtime.getRuntime().exit(0)
                }
            } catch (e: EndOfFileException) {
                if (!isLastInterrupted) {
                    isLastInterrupted = true
                    logger.warn(Utils.message.other.interrupted)
                    continue
                } else {
                    Runtime.getRuntime().exit(0)
                }
            } catch (e: Exception) {
                logger.error(Utils.message.error.unknownError)
                e.printStackTrace()
            }
        }
    }
}
