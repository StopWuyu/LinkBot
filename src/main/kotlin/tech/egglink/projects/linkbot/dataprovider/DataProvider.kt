package tech.egglink.projects.linkbot.dataprovider

import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.ResultSet

class DataProvider(private val dbPath: String) {
    private var connection: Connection? = null

    init {
        connect()
        createTableIfNotExists()
    }

    private fun connect() {
        try {
            Class.forName("org.sqlite.JDBC")
            connection = DriverManager.getConnection("jdbc:sqlite:$dbPath")
        } catch (e: Exception) {
            println("Failed to connect to the database: ${e.message}")
        }
    }

    private fun createTableIfNotExists() {
        val query = "CREATE TABLE IF NOT EXISTS UserData (qqId INTEGER PRIMARY KEY, permissionTrue TEXT)"
        executeUpdate(query)
    }

    private fun executeUpdate(query: String) {
        try {
            connection?.createStatement()?.executeUpdate(query)
        } catch (e: Exception) {
            println("Failed to execute query: $query")
        }
    }

    fun insertUserData(userData: UserData) {
        val query = "INSERT INTO UserData (qqId, permissionTrue, permissionFalse) VALUES (?, ?, ?)"
        try {
            val preparedStatement: PreparedStatement = connection?.prepareStatement(query) ?: return
            preparedStatement.setLong(1, userData.qqId)
            preparedStatement.setString(2, userData.permissionTrue.joinToString(","))
            preparedStatement.executeUpdate()
        } catch (e: Exception) {
            println("Failed to insert UserData: ${e.message}")
        }
    }

    fun getUserDataByQQId(qqId: Long): UserData? {
        val query = "SELECT * FROM UserData WHERE qqId = ?"
        try {
            val preparedStatement: PreparedStatement = connection?.prepareStatement(query) ?: return null
            preparedStatement.setLong(1, qqId)
            val resultSet: ResultSet = preparedStatement.executeQuery()
            if (resultSet.next()) {
                val permissionTrue = resultSet.getString("permissionTrue").split(",").toTypedArray()
                return UserData(qqId, permissionTrue)
            }
        } catch (e: Exception) {
            println("Failed to get UserData: ${e.message}")
        }
        return null
    }
}
