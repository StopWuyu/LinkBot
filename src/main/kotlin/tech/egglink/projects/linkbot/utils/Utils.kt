@file:Suppress("unused")

package tech.egglink.projects.linkbot.utils

import com.google.gson.GsonBuilder
import tech.egglink.projects.linkbot.bot.Bot
import tech.egglink.projects.linkbot.command.Commands
import tech.egglink.projects.linkbot.dataprovider.DataProvider
import tech.egglink.projects.linkbot.event.EventManager
import tech.egglink.projects.linkbot.plugins.PluginManager
import tech.egglink.projects.linkbot.utils.configurations.Configuration
import tech.egglink.projects.linkbot.utils.configurations.Message
import tech.egglink.projects.linkbot.utils.logger.Logger
import java.text.SimpleDateFormat
import java.util.*

object Utils {
    const val version = "1.0.0" // 版本号
    const val tunnel = "BETA" // 通道
    const val apiVersion = "5"  // API版本

    val consoleCmd = Commands()
    val botCmd = Commands()
    val bot = Bot()
    val event = EventManager()
    val database by lazy {
        DataProvider(getFilePath(config.path.data, "data.db"))
    }
    val pluginManager by lazy {
        PluginManager()
    }

    /**
     * 将多个路径加在一起
     *
     * @return 返回的路径最后不带 \
     * */
    fun getFilePath(vararg path: String): String {
        val result = StringBuilder()
        path.forEach {
            result.append(
                when (it.last()) {
                    '\\' -> it
                    '/' -> it.removeSuffix("/") + "\\"
                    else -> it + "\\"
                }
            )
        }
        return result.removeSuffix("\\").toString()
    }

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
     * 将对象转换为 JSON 字符串
     * */
    fun toJson(obj: Any): String {
        return GsonBuilder().setPrettyPrinting().create().toJson(obj)
    }

    /**
     * 从 JSON 字符串转换为对象
     * */
    fun <T> fromJson(json: String, clazz: Class<T>): T {
        return GsonBuilder().setPrettyPrinting().create().fromJson(json, clazz)
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

    /**
     * 读取 JSON 文件
     *
     * @param path 文件路径
     * @param clazz 类
     * */
    fun <T> readJsonFromFile(path: String, clazz: Class<T>): T {
        val file = java.io.File(path)
        if (!file.exists()) {
            file.createNewFile()
            file.writeText("{}")
        }
        return fromJson(file.readText(), clazz)
    }
}
