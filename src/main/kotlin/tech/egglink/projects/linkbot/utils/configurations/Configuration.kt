package tech.egglink.projects.linkbot.utils.configurations

import com.google.gson.GsonBuilder
import tech.egglink.projects.linkbot.utils.Utils
import java.io.File

class Configuration {
    /**
     * 机器人配置
     * */
    val botAccount = 10000L
    val botPassword = "123456"

    /**
     * 是否开启调试模式
     * */
    val debug: Boolean = false
    val helpCount: Int = 5

    fun loadConfig() {
        val gson = GsonBuilder().setPrettyPrinting().create()
        val config = File("config.json")
        if (!config.exists()) {
            saveConfig(Configuration())
        }
        config.reader().use {
            Utils.config = gson.fromJson(it, Configuration::class.java)
        }
    }

    fun saveConfig(cfg: Configuration) {
        val gson = GsonBuilder().setPrettyPrinting().create()
        val config = File("config.json")
        config.writer().use {
            it.write(gson.toJson(cfg))
        }
    }
}