package tech.egglink.projects.linkbot.command

import tech.egglink.projects.linkbot.utils.Utils

/**
 * 用于控制台的 CommandSender
 * */
class ConsoleCommandSender : CommandSender {
    /**
     * 发送消息
     *
     * @param message 消息
     * */
    override suspend fun sendMessage(message: String) {
        Utils.logger.info(message)
    }

    /**
     * 检查是否有权限
     *
     * @param permission 权限
     * */
    override fun hasPermission(permission: String): Boolean = true
}
