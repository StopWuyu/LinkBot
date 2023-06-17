package tech.egglink.projects.linkbot.event

import tech.egglink.projects.linkbot.utils.Utils

class EventManager {

    private val events = mutableListOf<Event>()
    fun registerEvents(vararg events: Event) {
        events.forEach { event ->
            registerEvent(event)
        }
    }
    fun registerEvents(events: List<Event>) {
        events.forEach { event ->
            registerEvent(event)
        }
    }

    fun registerEvent(event: Event) {
        events.add(event)
    }

    fun broadcastEvent(eventType: EventType, evt: net.mamoe.mirai.event.Event = CommandEvent()) {
        eventType.let {
            Utils.logger.debug("事件广播: ${it.javaClass.simpleName}(${it.hashCode()})")
            events.forEach { e ->
                if (e.type == it) {
                    Utils.logger.debug("事件执行: ${e.javaClass.simpleName}(${e.hashCode()})")
                    e.execute(evt)
                }
            }
        }
    }
}
