package tech.egglink.projects.linkbot.event

import tech.egglink.projects.linkbot.utils.Utils

/**
 * 事件管理器
 * */
class EventManager {

    private val events = mutableListOf<Event>() // 事件列表

    /**
     * 注册事件
     *
     * @param events 事件列表
     * */
    fun registerEvents(vararg events: Event) {
        events.forEach { event ->
            registerEvent(event)
        }
    }

    /**
     * 注册事件
     *
     * @param events 事件列表
     * */
    fun registerEvents(events: List<Event>) {
        events.forEach { event ->
            registerEvent(event)
        }
    }

    /**
     * 注册单个事件
     *
     * @param event 事件
     * */
    fun registerEvent(event: Event) {
        events.add(event)
    }

    /**
     * 广播事件
     *
     * @param eventType 事件类型
     * @param evt 事件
     * */
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
