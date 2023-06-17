package tech.egglink.projects.linkbot.dataprovider

class UserData {
    /**
     * QQ 号 用于核对
     * */
    var qqId: Long = 0L

    /**
     * 被授予 True 权限的权限
     * */
    val permissionTrue: Array<String> = arrayOf()

    /**
     * 被授予 False 权限的权限
     * */
    val permissionFalse: Array<String> = arrayOf()
}
