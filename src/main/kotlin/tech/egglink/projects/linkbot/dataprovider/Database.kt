package tech.egglink.projects.linkbot.dataprovider

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import tech.egglink.projects.linkbot.utils.Utils
import java.io.File

class Database {
    private var databaseInstance: Database? = null
    fun loadDatabase(): Database {
        val databaseFile = File(Utils.config.path.data, Utils.config.database.fileName) // 数据库文件
        val config = HikariConfig().apply { // 创建Hikari配置
            jdbcUrl = "jdbc:sqlite:${databaseFile.absolutePath}?foreign_keys=on" // 设置数据库路径
            maximumPoolSize = 1 // 设置最大连接数
            isAutoCommit = true // 设置自动提交
            username = Utils.config.database.user // 设置用户名
            password = Utils.config.database.password // 设置密码
        }
        val dataSource = HikariDataSource(config) // 创建数据源
        val db = Database.connect(dataSource) // 连接数据库
        databaseInstance = db
        return db
    }

    fun closeDatabase() {

    }
}
