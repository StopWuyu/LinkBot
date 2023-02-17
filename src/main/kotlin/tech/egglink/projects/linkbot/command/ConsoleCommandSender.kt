package tech.egglink.projects.linkbot.command

import tech.egglink.projects.linkbot.utils.Utils

class ConsoleCommandSender : CommandSender {
    override suspend fun sendMessage(message: String) {
        Utils.logger.info(message)
    }

    override fun hasPermission(permission: String): Boolean {
        return true
    }
}
