package tech.egglink.projects.linkbot.event

import net.mamoe.mirai.event.events.BotEvent
import net.mamoe.mirai.event.events.BotInvitedJoinGroupRequestEvent
import net.mamoe.mirai.event.events.BotJoinGroupEvent
import net.mamoe.mirai.event.events.BotLeaveEvent
import net.mamoe.mirai.event.events.FriendAddEvent
import net.mamoe.mirai.event.events.FriendMessageEvent
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.event.events.GroupMuteAllEvent
import net.mamoe.mirai.event.events.GroupNameChangeEvent
import net.mamoe.mirai.event.events.MemberJoinRequestEvent
import tech.egglink.projects.linkbot.utils.Utils

/**
 * 事件监听器
 * */
object EventListener {
    fun beginListen() {
        Utils.bot.getBot().eventChannel.subscribeAlways<BotEvent> {
            when (this) {
                is GroupMessageEvent -> Utils.event.broadcastEvent(EventType.BOT_GROUP_RECEIVE_MESSAGE, this) // 机器人接收到群消息
                is GroupMuteAllEvent -> Utils.event.broadcastEvent(EventType.BOT_GROUP_MUTE_ALL, this) // 机器人群全员禁言
                is GroupNameChangeEvent -> Utils.event.broadcastEvent(EventType.BOT_GROUP_NAME_CHANGE, this) // 机器人群名改变
                is BotInvitedJoinGroupRequestEvent -> Utils.event.broadcastEvent(EventType.BOT_INVITE_JOIN_GROUP_REQUEST, this) // 机器人收到邀请入群申请
                is BotJoinGroupEvent -> Utils.event.broadcastEvent(EventType.BOT_GROUP_JOIN, this) // 机器人加入群
                is BotLeaveEvent -> Utils.event.broadcastEvent(EventType.BOT_GROUP_LEAVE, this) // 机器人离开群
                is FriendMessageEvent -> Utils.event.broadcastEvent(EventType.BOT_FRIENDS_RECEIVE_MESSAGE, this) // 机器人接收到好友消息
                is FriendAddEvent -> Utils.event.broadcastEvent(EventType.BOT_FRIENDS_ADD_REQUEST, this) // 机器人收到好友申请
                is MemberJoinRequestEvent -> Utils.event.broadcastEvent(EventType.BOT_GROUP_JOIN_REQUEST, this) // 机器人收到入群申请
            }
        }
    }
}
