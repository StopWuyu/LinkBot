package tech.egglink.projects.linkbot.utils.configurations

/**
 * 消息文件
 *
 * 语言: 中文
 * @since 1.0.0
 * */
class Message {
    /**
     * 命令相关信息
     *
     * Usage & Description
     * */
    class Command {
        val exitUsage = "exit"
        val exitDescription = "退出程序。"
        val helpUsage = "help <page>"
        val helpDescription = "显示帮助信息。"
        val loginUsage = "login"
        val loginDescription = "登录机器人。"
        val logoutUsage = "logout"
        val logoutDescription = "登出机器人。"
        val captchaUsage = "captcha <pic/slider> <text/ticket>"
        val captchaDescription = "输入验证码。"
        val databaseUsage = "database <qq>"
        val databaseDescription = "查询指定QQ号的用户信息。"
    }
    val command = Command()

    /**
     * 错误信息
     * */
    class Error {
        val unknownError = "未知错误!"
        val unknownCommand = "未知命令!"
        val noPermission = "没有权限!"
        val invalidArgs = "参数错误!"
        val notLogin = "你还没有登录!"
        val isOnline = "你已经登录了!"
        val notFoundUser = "没有找到指定用户!"
    }
    val error = Error()

    /**
     * 命令执行信息
     * */
    class Info {
        val helpTextTitle = "==========帮助=========="
        val helpTextTemplate = "名称: %s\n别名: %s\n用法: %s\n描述: %s\n\n"
        val helpTextEnd = "=======第 %d/%d 页======="
        val helpPageOutOfIndex = "页码超出范围!"
        val database = "查询数据: \nQQ: %d\n权限-true: %s"
    }
    val info = Info()

    /**
     * 其他信息
     * */
    class Other {
        val startInfo: String = "正在启动 LinkBot..."
        val loadingConfig: String = "正在加载配置文件..."
        val loadingData: String = "正在加载数据..."
        val loadingPlugin: String = "正在加载插件..."
        val noPlugin: String = "没有找到插件。"
        val foundPlugin: String = "加载插件: %s - %s"  // 名称 - 版本
        val pluginException: String = "%s 插件发生了错误！"
        val pluginApiLow: String = "%s 插件API版本与当前不符合！"
        val loadingBot: String = "正在加载机器人..."
        val doneBotLogin: String = "机器人登录成功!"
        val loadingEvents: String = "正在加载事件..."
        val loadingCommand: String = "正在加载命令..."
        val startSuccess: String = "LinkBot 启动成功!"
        val interrupted: String = "再次按下 Ctrl-C 退出程序。"
        val exit: String = "正在退出程序..."
        val picCaptchaSaved: String = "验证码已保存至 %s"
        val picCaptchaInput: String = "请输入 captcha pic <text> 来输入验证码。"
        val sliderCaptchaUrl: String = "滑块验证码地址: %s"
        val sliderCaptchaInput: String = "请输入 captcha slider <ticket> 来确认校验验证码完成。"
    }
    val other = Other()
}
