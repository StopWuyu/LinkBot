package tech.egglink.projects.linkbot.command

/**
 * 命令发送者实例
 * */
interface CommandSender {
    /**
     * 发送消息
     *
     * @param message 消息
     * */
    suspend fun sendMessage(message: String)

    /**
     * 检查是否有权限
     *
     * @param permission 权限
     * */
    fun hasPermission(permission: String): Boolean
}
