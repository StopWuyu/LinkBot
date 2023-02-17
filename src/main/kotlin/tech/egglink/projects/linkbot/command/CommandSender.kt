package tech.egglink.projects.linkbot.command

interface CommandSender {
    suspend fun sendMessage(message: String)

    fun hasPermission(permission: String): Boolean
}
