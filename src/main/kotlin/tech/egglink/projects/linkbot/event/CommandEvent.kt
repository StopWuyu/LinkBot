package tech.egglink.projects.linkbot.event

class CommandEvent : net.mamoe.mirai.event.Event {
    override val isIntercepted: Boolean = false

    var command: String = ""
    override fun intercept() {
    }
}
