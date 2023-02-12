package tech.egglink.projects.linkbot.utils

import tech.egglink.projects.linkbot.bot.Bot
import tech.egglink.projects.linkbot.command.Commands
import tech.egglink.projects.linkbot.utils.configurations.Configuration
import tech.egglink.projects.linkbot.utils.configurations.Message
import tech.egglink.projects.linkbot.utils.logger.Logger
import java.text.SimpleDateFormat
import java.util.*

object Utils {
    val cmd = Commands()
    val bot = Bot()
    /**
     * 获取当前时间
     *
     * 格式: MM-dd HH:mm:ss
     * */
    fun nowTime(): String {
        val time = System.currentTimeMillis()
        val date = Date(time)
        val sdf = SimpleDateFormat("MM-dd HH:mm:ss")
        return sdf.format(date)
    }
    /**
     * 获取当前时间
     *
     * @param format 时间格式
     * */
    fun nowTime(format: String): String {
        val time = System.currentTimeMillis()
        val date = Date(time)
        val sdf = SimpleDateFormat(format)
        return sdf.format(date)
    }

    /**
     * 日志记录器
     * */
    val logger: Logger = Logger()

    /**
     * 配置文件
     * */
    var config: Configuration = Configuration()

    /**
     * 消息文件
     * */
    val message: Message = Message()
}