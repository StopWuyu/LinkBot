package tech.egglink.projects.linkbot.command

abstract class CommandHandler(val entry: Entry) {
    open class Entry {
        var name: String = ""
        var description: String = ""
        var usage: String = ""
        var aliases: Array<String> = arrayOf()
        var type: CommandType = CommandType.Console

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

    abstract suspend fun execute(sender: CommandSender, args: Array<String>): CommandResult
}
