package tech.egglink.projects.linkbot.plugins

/**
 * 在jar的根目录下 config.json找到
 * */
class BotPluginConfig {
    val name = "None"  // 名称
    val version = "1.0"  // 版本
    val description = "None"  // 插件描述
    val mainClass = "tech.egglink.projects.linkbot.LinkBot"  // 主类
    val apiVersion = "5"  // API版本
    val loadBefore = listOf<String>()  // 在{}之前加载
    val loadAfter = listOf<String>()  // 在{}之后加载
}