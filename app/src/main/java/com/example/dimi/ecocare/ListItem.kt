package com.example.dimi.ecocare

import java.sql.Connection
import java.sql.SQLException
import java.util.*


class ListItem {
    var connect: Connection? = null
    var ConnectionResult = ""
    var isSuccess = false
    fun getlist(): List<Map<String?, String?>?> {
        var data: MutableList<Map<String?, String?>?>? = null
        data = ArrayList()
        try {
            val connectionHelper = ConnectionHelper()
            connect = connectionHelper.conclass()
            if (connect != null) {
                val qu =
                    "SELECT top 10 c.*, p.*\n" +
                            "FROM tblCustomer c OUTER APPLY\n" +
                            "     (SELECT TOP (1) p.*\n" +
                            "      FROM tblPayments p\n" +
                            "      WHERE c.CustomerNumber = p.CustomerNumber\n" +
                            "      ORDER BY p.id DESC\n" +
                            "     ) p;"
                val statement = connect!!.createStatement()
                val resultSet = statement.executeQuery(qu)
                while (resultSet.next()) {
                    val dtname: MutableMap<String?, String?> = HashMap()
                    dtname["tvCustomerID"] = resultSet.getString("CustomerNumber")
                    dtname["tvName"] = resultSet.getString("Name")
                    dtname["tvSurname"] = resultSet.getString("Surname")
                    dtname["tvCity"] = resultSet.getString("City")
                    dtname["tvAddress"] = resultSet.getString("Address")
                    dtname["tvCredit"] = resultSet.getString("Credit")
                    dtname["tvDebit"] = resultSet.getString("Debit")
                    data.add(dtname)
                }
                ConnectionResult = "Success"
                isSuccess = true
                connect!!.close()
            } else {
                ConnectionResult = "Failed"
            }
        } catch (throwables: SQLException) {
            throwables.printStackTrace()
        }
        return data
    }
}
