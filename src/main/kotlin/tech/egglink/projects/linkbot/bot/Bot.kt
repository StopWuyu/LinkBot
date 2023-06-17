package tech.egglink.projects.linkbot.bot

import kotlinx.coroutines.runBlocking
import net.mamoe.mirai.Bot
import net.mamoe.mirai.Mirai
import net.mamoe.mirai.utils.BotConfiguration
import tech.egglink.projects.linkbot.event.EventType
import tech.egglink.projects.linkbot.utils.Utils

class Bot {
    private var bot: Bot? = null

    /**
     * 机器人登录
     * */
    fun login(account: Long, password: String) {
        bot = Mirai.BotFactory.newBot(
            account,
            password,
            BotConfiguration {
                noBotLog()
                noNetworkLog()
                fileBasedDeviceInfo()
            }
        )
        runBlocking {
            bot!!.login()
            Utils.event.broadcastEvent(EventType.BOT_LOGIN)
            Utils.logger.info(Utils.message.other.doneBotLogin)
        }
    }

    /**
     * 机器人登出
     * */
    fun logout() {
        runBlocking {
            bot!!.close()
            Utils.event.broadcastEvent(EventType.BOT_LOGOUT)
        }
    }

    /**
     * 获取机器人实例
     * */
    fun getBot(): Bot {
        return bot ?: throw NullPointerException("机器人未登录")
    }
}
