package com.example.dimi.ecocare

import android.annotation.SuppressLint
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.util.Log
import java.sql.Connection
import java.sql.DriverManager


class ConnectionHelper {
    var con: Connection? = null
    var ip: String? = null
    var port: String? = null
    var db: String? = null
    var un: String? = null
    var pass: String? = null
    @SuppressLint("NewApi")
    fun conclass(): Connection? {
        ip = "192.168.1.67"
        port = "1433"
        db = "CustomerBillManager"
        un = "test"
        pass = "test111"
        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        var connection: Connection? = null
        var ConnectURL: String? = null
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver")
            ConnectURL = "jdbc:jtds:sqlserver://$ip:$port;databaseName$db;user=$un;password=$pass;"
            connection = DriverManager.getConnection(ConnectURL)
        } catch (exception: Exception) {
            Log.e("Error: ", exception.message!!)
        }
        return connection
    }
}
