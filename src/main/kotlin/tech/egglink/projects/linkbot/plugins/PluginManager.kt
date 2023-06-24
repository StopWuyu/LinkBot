package tech.egglink.projects.linkbot.plugins

import kotlinx.coroutines.runBlocking
import tech.egglink.projects.linkbot.plugins.exception.InvalidPluginException
import tech.egglink.projects.linkbot.utils.Utils
import java.io.File
import java.util.jar.JarFile

/**
 * 插件管理类
 */
class PluginManager {

    /**
     * 插件列表
     */
    private val plugins: MutableList<BotPlugin> = mutableListOf()

    /**
     * @return 插件列表
     */
    fun getPluginList(): List<BotPlugin> = plugins

    /**
     * 从插件列表中注销插件
     */
    fun unregisterPlugin(plugin: BotPlugin) {
        try {
            plugin.onDisable()
        } catch (e: Exception) {
            Utils.logger.error("Error on disabled function", e)
        }
        plugins.remove(plugin)
    }

    /**
     * 注销所有插件
     */
    fun unregisterAllPlugins() {
        plugins.forEach(::unregisterPlugin)
    }

    /**
     * 加载插件
     */
    fun loadPlugins() {
        loadPlugins(Utils.config.path.plugins)
    }

    /**
     * 加载插件
     *
     * @param path 插件文件夹路径
     */
    fun loadPlugins(path: String) {
        loadPlugins(path, true)
    }

    /**
     * 加载插件
     *
     * @param path 插件文件夹路径
     * @param skipError 是否跳过错误（为True则遇见错误直接停止加载插件）
     */
    fun loadPlugins(path: String, skipError: Boolean) {
        val pluginFiles = File(path).listFiles() // 获取所有文件
        if (pluginFiles != null) {
            if (pluginFiles.isEmpty())
                Utils.logger.info(Utils.message.other.noPlugin)
            else
                pluginFiles.forEach { file ->
                    loadPlugin(file, skipError)
                }
        } else {
            Utils.logger.info(Utils.message.other.noPlugin)
        }
    }

    /**
     * 加载单个插件
     *
     * @param file 文件
     * @param skipError 是否跳过错误
     */
    fun loadPlugin(file: File, skipError: Boolean) {
        val config = Utils.fromJson(getConfigFromJar(file.path) ?: return, BotPluginConfig::class.java)

        // 从 JAR 文件中加载类
        val jarClassLoader = JarClassLoader(file.path)
        val mainClass = jarClassLoader.loadClass(config.mainClass)

        // 创建类实例
        val pluginInstance = try {
            mainClass.getDeclaredConstructor().newInstance()
        } catch (e: Exception) {
            if (skipError) {
                Utils.logger.error("Failed to create instance of plugin class: ${config.mainClass}", e)
                return
            } else {
                throw InvalidPluginException("Failed to create instance of plugin class: ${config.mainClass}", e)
            }
        }

        // 调用插件的初始化方法
        if (pluginInstance is BotPlugin) {
            try {
                pluginInstance.initializePlugin(config)
                pluginInstance.onEnable()
                plugins.add(pluginInstance)
            } catch (e: Exception) {
                Utils.logger.error("Failed to load class: ${config.mainClass}", e)
                try {
                    pluginInstance.onDisable()
                } catch (e: Exception) {
                    Utils.logger.error("Failed to disable plugin: ${config.name}", e)
                }
            }
        } else {
            if (skipError) {
                Utils.logger.error("Invalid plugin class: ${config.mainClass}. It does not implement BotPlugin.")
                return
            } else {
                throw InvalidPluginException("Invalid plugin class: ${config.mainClass}. It does not implement BotPlugin.")
            }
        }
    }

    /**
     * 从 jar 文件中获取 config.json 文本
     */
    private fun getConfigFromJar(jarPath: String): String? {
        var configJson: String? = null

        try {
            JarFile(jarPath).use { jarFile ->
                val entry = jarFile.getEntry("config.json")
                if (entry != null) {
                    val inputStream = jarFile.getInputStream(entry)
                    configJson = inputStream.bufferedReader().use { it.readText() }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return configJson
    }
}

class JarClassLoader(private val jarPath: String) : ClassLoader() {
    override fun findClass(name: String): Class<*> {
        JarFile(jarPath).use { jarFile ->
            val entry = jarFile.getJarEntry("$name.class")
            if (entry != null) {
                val inputStream = jarFile.getInputStream(entry)
                val bytes = inputStream.readBytes()
                return defineClass(name, bytes, 0, bytes.size)
            }
        }

        throw ClassNotFoundException(name)
    }
}
