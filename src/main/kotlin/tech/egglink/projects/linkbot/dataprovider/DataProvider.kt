package tech.egglink.projects.linkbot.dataprovider

/**
 * 数据提供器
 * */
class DataProvider {
    /**
     * 通过 QQ 号新建用户数据
     *
     * @param qq QQ 号
     * */
    fun createUserData(qq: Long) {
        UserData(qq)
    }
}
