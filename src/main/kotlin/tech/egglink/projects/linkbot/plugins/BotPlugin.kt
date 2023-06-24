package tech.egglink.projects.linkbot.plugins

import tech.egglink.projects.linkbot.plugins.exception.PluginNotInitializeException
import tech.egglink.projects.linkbot.utils.logger.Logger

/**
 * 机器人的插件
 * */
abstract class BotPlugin {
    private var pluginConfig: BotPluginConfig? = null
    val logger: Logger by lazy {
        Logger(pluginConfig?.name ?: throw PluginNotInitializeException())
    }

    /**
     * 初始化插件
     *
     * 由主程序进行
     * */
    fun initializePlugin(config: BotPluginConfig) {
        pluginConfig = config
        logger
    }

    /**
     * 加载插件
     * */
    open fun onEnable() {
    }

    /**
     * 卸载插件
     * */
    open fun onDisable() {

    }
}