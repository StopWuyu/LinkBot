package tech.egglink.projects.linkbot.utils.configurations

import com.google.gson.GsonBuilder
import tech.egglink.projects.linkbot.utils.Utils
import java.io.File

class Configuration {
    /**
     * 首选项
     */
    class Setting {
        var version = Utils.version
        var updateChannel = Utils.tunnel
        val language = "zh-CN"
        val debugMode = false
        val helpCountEachPage = 5
    }
    val setting = Setting()

    /**
     * 账号信息
     */
    class Account {
        val id = 10000L
        val password = "Password"
    }
    val account = Account()

    /**
     * 机器人信息
     */
    class Bot {
        val autoLogin = true // 自动登录
        val autoAcceptFriendRequest = true // 自动接受好友请求
        val autoAcceptGroupInvite = false // 自动接受群邀请
        val workGroups: List<Long> = listOf() // 工作群
        val administrators: List<Long> = listOf() // 管理员
        val disableCommands: List<String> = listOf() // 禁用的命令
    }
    val bot = Bot()

    /**
     * 数据库信息
     */
    class Database {
        val fileName = "database.db"
        val user = "root"
        val password = "123456"
    }
    val database = Database()

    /**
     * 路径信息
     */
    class Path {
        val plugins = "./plugins"
        val data = "./data"
    }
    val path = Path()

    // 以下为配置文件读写方法
    fun loadConfig() {
        val gson = GsonBuilder().setPrettyPrinting().create()
        val config = File("config.json")
        if (!config.exists()) {
            saveConfig(Configuration())
        }
        config.reader().use {
            Utils.config = gson.fromJson(it, Configuration::class.java)
        }
        Utils.config.setting.version = Utils.version
        Utils.config.setting.updateChannel = Utils.tunnel
        saveConfig(Utils.config) // 刷新键值
    }

    fun saveConfig(cfg: Configuration) {
        val gson = GsonBuilder().setPrettyPrinting().create()
        val config = File("config.json")
        config.writer().use {
            it.write(gson.toJson(cfg))
        }
    }
}
