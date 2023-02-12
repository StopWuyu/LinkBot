package tech.egglink.projects.linkbot.utils.logger

import com.andreapivetta.kolor.Color
import com.andreapivetta.kolor.Kolor
import org.fusesource.jansi.AnsiConsole
import tech.egglink.projects.linkbot.utils.Utils
import tech.egglink.projects.linkbot.utils.Utils.nowTime
import java.io.File

class Logger {
    /**
     * 信息输出
     *
     * 信息等级: INFO
     * @param message 输出信息
     * */
    fun info(message: String) {
        print(
            "[${nowTime()}] " +
                    Kolor.foreground("[INFO] ", Color.LIGHT_GREEN) + message
        )
        printToFile("[${nowTime()}] [INFO] $message")
    }

    /**
     * 信息输出
     *
     * 信息等级: WARN
     * @param message 输出信息
     * */
    fun warn(message: String) {
        print(
            "[${nowTime()}] " +
                    Kolor.foreground("[WARN] ", Color.LIGHT_YELLOW) + message
        )
        printToFile("[${nowTime()}] [WARN] $message")
    }

    /**
     * 信息输出
     *
     * 信息等级: ERROR
     * @param message 输出信息
     * */
    fun error(message: String) {
        print(
            "[${nowTime()}] " +
                    Kolor.foreground("[ERROR] ", Color.LIGHT_RED) + message
        )
        printToFile("[${nowTime()}] [ERROR] $message")
    }

    /**
     * 信息输出
     *
     * 信息等级: DEBUG
     * @param message 输出信息
     * */
    fun debug(message: String) {
        if (Utils.config.debug) {
            print(
                "[${nowTime()}] " +
                        Kolor.foreground("[DEBUG] ", Color.LIGHT_BLUE) + message
            )
        }
        printToFile("[${nowTime()}] [DEBUG] $message")
    }

    /**
     * 信息输出
     *
     * 信息等级: FATAL
     * @param message 输出信息
     * */
    fun fatal(message: String) {
        print(
            "[${nowTime()}] " +
                    Kolor.foreground("[FATAL] ", Color.RED) + message
        )
        printToFile("[${nowTime()}] [FATAL] $message")
    }

    /**
     * 信息输出
     *
     * 信息等级: 自定义
     * @param message 输出信息
     * @param level 信息等级
     * */
    fun log(message: String, level: String) {
        when (level) {
            "INFO" -> info(message)
            "WARN" -> warn(message)
            "ERROR" -> error(message)
            "DEBUG" -> debug(message)
            "FATAL" -> fatal(message)
            else -> print(message)
        }
    }

    /**
     * 信息输出 API
     *
     * @param message 输出信息
     * */
    private fun print(message: String) {
        AnsiConsole.out().println(message)
    }

    /**
     * 文件输出
     *
     * @param message 输出信息
     * */
    private fun printToFile(message: String) {
        val logFile = File("logs/${nowTime("yyyy-MM-dd")}.log")
        if (!logFile.parentFile.exists()) {
            logFile.parentFile.mkdirs()
        }
        if (!logFile.exists()) {
            logFile.createNewFile()
        }
        logFile.appendText(message + "\n")
    }
}