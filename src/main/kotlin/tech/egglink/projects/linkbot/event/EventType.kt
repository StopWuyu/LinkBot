package tech.egglink.projects.linkbot.event

/**
 * 事件类型
 *
 * @property PLUGIN_ENABLE 插件启用
 * @property PLUGIN_DISABLE 插件禁用
 * @property BOT_LOGIN 机器人登录
 * @property BOT_LOGOUT 机器人登出
 * @property BOT_GROUP_JOIN 机器人加入群
 * @property BOT_GROUP_LEAVE 机器人离开群
 * @property BOT_GROUP_JOIN_REQUEST 机器人收到入群申请
 * @property BOT_GROUP_RECEIVE_MESSAGE 机器人收到群消息
 * @property BOT_GROUP_NAME_CHANGE 机器人群名改变
 * @property BOT_GROUP_MUTE_ALL 机器人群全员禁言
 * @property BOT_FRIENDS_ADD_REQUEST 机器人收到好友申请
 * @property BOT_FRIENDS_RECEIVE_MESSAGE 机器人收到好友消息
 * @property BOT_INVITE_JOIN_GROUP_REQUEST 机器人收到邀请入群申请
 * @property BOT_COMMAND 机器人指令
 * @property CONSOLE_COMMAND 控制台命令
 * */
enum class EventType {
    PLUGIN_ENABLE, // 插件启用
    PLUGIN_DISABLE, // 插件禁用
    BOT_LOGIN, // 机器人登录
    BOT_LOGOUT, // 机器人登出
    BOT_GROUP_JOIN, // 机器人加入群
    BOT_GROUP_LEAVE, // 机器人离开群
    BOT_GROUP_JOIN_REQUEST, // 机器人收到入群申请
    BOT_GROUP_RECEIVE_MESSAGE, // 机器人收到群消息
    BOT_GROUP_NAME_CHANGE, // 机器人群名改变
    BOT_GROUP_MUTE_ALL, // 机器人群全员禁言
    BOT_FRIENDS_ADD_REQUEST, // 机器人收到好友申请
    BOT_FRIENDS_RECEIVE_MESSAGE, // 机器人收到好友消息
    BOT_INVITE_JOIN_GROUP_REQUEST, // 机器人收到邀请入群申请
    BOT_COMMAND, // 机器人指令
    CONSOLE_COMMAND // 控制台命令
}
