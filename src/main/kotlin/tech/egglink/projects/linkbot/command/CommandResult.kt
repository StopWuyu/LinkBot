package tech.egglink.projects.linkbot.command

/**
 * 命令结果
 *
 * @property SUCCESS 成功
 * @property FAILURE 失败
 * @property ERROR 错误
 * @property NOT_FOUND 未找到
 * @property NO_PERMISSION 无权限
 * @property INVALID_USAGE 无效用法
 * */
enum class CommandResult {
    SUCCESS, // 成功
    FAILURE, // 失败
    ERROR, // 错误
    NOT_FOUND, // 未找到
    NO_PERMISSION, // 无权限
    INVALID_USAGE // 无效用法
}
