package tech.egglink.projects.linkbot.event

/**
 * 不清楚干什么的了
 */
class CommandEvent : net.mamoe.mirai.event.Event {
    override val isIntercepted: Boolean = false

    var command: String = ""
    override fun intercept() {
    }
}
