package tech.egglink.projects.linkbot.command

import net.mamoe.mirai.contact.Contact
import net.mamoe.mirai.contact.Group

interface BotCommandSender : CommandSender {
    val sender: Contact
    val group: Group
    val sendToGroup: Boolean
    override suspend fun sendMessage(message: String) {
        if (sendToGroup) {
            group.sendMessage(message)
        } else {
            sender.sendMessage(message)
        }
    }

    override fun hasPermission(permission: String): Boolean {
        return true
    }
}
