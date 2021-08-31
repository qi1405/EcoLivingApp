package com.example.dimi.ecocare

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.view.View
import android.widget.TextView
import androidx.core.app.ActivityCompat
import java.sql.*

class CustInfo : AppCompatActivity() {
    var tvCustID : TextView? = null
    var tvCustName : TextView? = null
    var tvCustSurname : TextView? = null
    var tvCustAddress : TextView? = null
    var tvCustCity : TextView? = null
    var tvCustCredit : TextView? = null
    var tvCustDebit : TextView? = null
    var textView8 : TextView? = null
    var view : View? = null
    var connection : Connection? = null
    val ip = "192.168.1.67"
    val port = "1433"
    val Classes = "net.sourceforge.jtds.jdbc.Driver"
    val database = "CustomerBillManager"
    val username = "test"
    val password = "test111"
    val url = "jdbc:jtds:sqlserver://"+ip+":"+port+"/"+database
       override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cust_info)



        ActivityCompat.requestPermissions(this,arrayOf(android.Manifest.permission.INTERNET),
            PackageManager.PERMISSION_GRANTED)
           textView8 = findViewById(R.id.textView8)
           tvCustName = findViewById(R.id.tvCustName)
           tvCustSurname = findViewById(R.id.tvCustSurname)
           tvCustAddress = findViewById(R.id.tvCustAddress)
           tvCustCity = findViewById(R.id.tvCustCity)
           tvCustCredit = findViewById(R.id.tvCustCredit)
           tvCustDebit = findViewById(R.id.tvCustDebit)
           tvCustID = findViewById(R.id.tvCustID)
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        try {
            Class.forName(Classes)
            connection = DriverManager.getConnection(url, username, password)
            textView8?.text = "TË DHËNAT E KLIENTIT U MORËN ME SUKSES"
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
            textView8?.text = "NUK MUND TË MERREN TË DHËNAT E KLIENTIT NGA DATABAZA"
        } catch (e: SQLException) {
            e.printStackTrace()
            textView8?.text = "LIDHJA ME DATABAZËN U SHKËPUT"
        }

           if (connection!=null){
               var statement : Statement? = null
               try {
                   statement = connection!!.createStatement()
                   val cid : String? = intent.getStringExtra("it").toString()
                   val resultSet: ResultSet = statement.executeQuery("SELECT c.*, p.*\n" +
                           "FROM tblCustomer c OUTER APPLY\n" +
                           "     (SELECT TOP (1) p.*\n" +
                           "      FROM tblPayments p\n" +
                           "      WHERE c.CustomerNumber = p.CustomerNumber\n" +
                           "      ORDER BY p.id DESC\n" +
                           "     ) p\n" +
                           "\t where c.CustomerNumber = $cid;")
                   while (resultSet.next()) {
                       tvCustID?.setText(cid)
                       tvCustName?.setText(resultSet.getString(3))
                       tvCustSurname?.setText(resultSet.getString(4))
                       tvCustAddress?.setText(resultSet.getString(7))
                       tvCustCity?.setText(resultSet.getString(6))
                       tvCustCredit?.setText(resultSet.getString(17))
                       tvCustDebit?.setText(resultSet.getString(18))
                   }
               } catch (e: SQLException) {
                   e.printStackTrace()
               }
           }else {
               tvCustName?.text = "Connection is null"
           }


    }

/*
    fun btnGetCustInfo (view: View){
        if (connection!=null){
            var statement : Statement? = null
            try {
                statement = connection!!.createStatement()
                val cid : String? = intent.getStringExtra("it").toString()
                val resultSet: ResultSet = statement.executeQuery("SELECT c.*, p.*\n" +
                        "FROM tblCustomer c OUTER APPLY\n" +
                        "     (SELECT TOP (1) p.*\n" +
                        "      FROM tblPayments p\n" +
                        "      WHERE c.CustomerNumber = p.CustomerNumber\n" +
                        "      ORDER BY p.id DESC\n" +
                        "     ) p\n" +
                        "\t where c.CustomerNumber = $cid;")
                while (resultSet.next()) {
                    tvCustID?.setText(cid)
                    tvCustName?.setText(resultSet.getString(3))
                    tvCustSurname?.setText(resultSet.getString(4))
                    tvCustAddress?.setText(resultSet.getString(7))
                    tvCustCity?.setText(resultSet.getString(6))
                    tvCustCredit?.setText(resultSet.getString(17))
                    tvCustDebit?.setText(resultSet.getString(18))
                }
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }else {
            tvCustName?.text = "Connection is null"
        }
    }
*/

}