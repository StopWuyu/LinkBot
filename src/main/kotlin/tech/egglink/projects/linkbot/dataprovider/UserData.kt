package tech.egglink.projects.linkbot.dataprovider

data class UserData(
    /**
     * QQ 号 用于核对
     * */
    var qqId: Long = 0L,

    /**
     * 被授予 True 权限的权限
     * */
    val permissionTrue: Array<String> = arrayOf(),

    /**
     * 被授予 False 权限的权限
     * */
    val permissionFalse: Array<String> = arrayOf()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserData

        if (qqId != other.qqId) return false
        if (!permissionTrue.contentEquals(other.permissionTrue)) return false
        return permissionFalse.contentEquals(other.permissionFalse)
    }

    override fun hashCode(): Int {
        var result = qqId.hashCode()
        result = 31 * result + permissionTrue.contentHashCode()
        result = 31 * result + permissionFalse.contentHashCode()
        return result
    }
}
