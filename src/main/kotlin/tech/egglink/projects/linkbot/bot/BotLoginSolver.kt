package tech.egglink.projects.linkbot.bot

import kotlinx.coroutines.CompletableDeferred
import net.mamoe.mirai.Bot
import net.mamoe.mirai.utils.LoginSolver
import tech.egglink.projects.linkbot.utils.Utils
import java.io.File

class BotLoginSolver : LoginSolver() {
    private var captchaPic = CompletableDeferred<String>()
    private var captchaSlider = CompletableDeferred<String>()
    private var verifySlider = false
    private var verifyPic = false

    override suspend fun onSolvePicCaptcha(bot: Bot, data: ByteArray): String {
        File("./data/captcha.jpg").writeBytes(data)
        Utils.logger.warn(Utils.message.other.picCaptchaSaved.format("./data/captcha.jpg"))
        Utils.logger.warn(Utils.message.other.picCaptchaInput)
        verifyPic = true
        return captchaPic.await()
    }

    override suspend fun onSolveSliderCaptcha(bot: Bot, url: String): String {
        Utils.logger.warn(Utils.message.other.sliderCaptchaUrl.format(url))
        Utils.logger.warn(Utils.message.other.sliderCaptchaInput)
        verifySlider = true
        return captchaSlider.await()
    }

    fun setCaptchaPic(captchaPic: String) {
        if (verifyPic) {
            this.captchaPic.complete(captchaPic)
            verifyPic = false
        }
    }

    fun setCaptchaSlider(captchaSlider: String) {
        if (verifySlider) {
            this.captchaSlider.complete(captchaSlider)
            verifySlider = false
        }
    }
}
