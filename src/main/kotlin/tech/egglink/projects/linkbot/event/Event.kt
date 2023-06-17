package tech.egglink.projects.linkbot.event

import net.mamoe.mirai.event.Event

/**
 * 事件接口
 * */
interface Event {
    /**
     * 执行事件
     * */
    fun execute(event: Event)

    /**
     * 事件类型
     * */
    val type: EventType
}
