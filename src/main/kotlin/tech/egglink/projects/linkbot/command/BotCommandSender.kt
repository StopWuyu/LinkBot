package tech.egglink.projects.linkbot.command

class BotCommandSender: CommandSender {
    override suspend fun sendMessage(message: String) {
        // TODO
    }

    override fun hasPermission(permission: String): Boolean {
        // TODO
        return true
    }
}