package tech.egglink.projects.linkbot.command

/**
 * 命令实例
 *
 * 所有的命令都应该继承这个类
 * @param entry 命令信息
 * */
abstract class CommandHandler(val entry: Entry) {
    /**
     * 命令信息
     *
     * @property name 命令名
     * @property description 命令描述
     * @property usage 命令用法
     * @property aliases 命令别名
     * @property argsType 参数类型
     * @property defaultArgs 默认参数
     * @property permission 权限
     * */
    open class Entry {
        var name: String = ""
        var description: String = ""
        var usage: String = ""
        var aliases: Array<String> = arrayOf()

        /**
         * 参数类型
         *
         * 例如: String, Int, Boolean, Double
         *
         * 例子: listOf("String", "Int") 代表 这个命令要用 String 和 Int 两个参数
         * */
        var argsType: List<String> = listOf()
        var defaultArgs: Array<String> = arrayOf()
        var permission: String = "true"
    }

    /**
     * 命令执行
     * */
    abstract suspend fun execute(sender: CommandSender, args: Array<String>): CommandResult
}
