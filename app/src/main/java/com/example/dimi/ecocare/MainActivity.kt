package com.example.dimi.ecocare

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat
import java.sql.*
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    var textView : TextView? = null
    val ip = "192.168.1.67"
    val port = "1433"
    val Classes = "net.sourceforge.jtds.jdbc.Driver"
    val database = "CustomerBillManager"
    val username = "test"
    val password = "test111"
    val url = "jdbc:jtds:sqlserver://"+ip+":"+port+"/"+database
    var connection : Connection? = null
    var btListClients: Button? = null
    var button : Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ActivityCompat.requestPermissions(this,arrayOf(android.Manifest.permission.INTERNET),
            PackageManager.PERMISSION_GRANTED)
        textView = findViewById(R.id.textView)
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        try {
            Class.forName(Classes)
            connection = DriverManager.getConnection(url, username, password)
            textView?.text = "LIDHJA ME DATABAZËN ËSHTË E SUKSESSHME"
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
            textView?.text = "KA GABIM NË LIDHJEN ME DATABAZËN"
        } catch (e: SQLException) {
            e.printStackTrace()
            textView?.text = "LIDHJA ME DATABAZËN ËSHTË E PASUKSESSHME"
        }



        btListClients = findViewById(R.id.btListClients) as Button?

        btListClients!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?){
                val intent = Intent(applicationContext, ListClients::class.java)
                startActivity(intent)
            }
        })
    }
    fun btQRC (view:View){

        val intent = Intent (this, QRCodeScanner::class.java)
        startActivity(intent)
    }
/*
    fun sqlButton (view:View){
        if (connection!=null){
            var statement : Statement? = null
            try {
                statement = connection!!.createStatement()
                val resultSet: ResultSet = statement.executeQuery("Select Name From dbo.tblCustomer WHERE ID = 1023;")
                while (resultSet.next()) {
                    textView?.setText(resultSet.getString(1))
                }
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }else {
            textView?.text = "Connection is null"
        }
    }
*/



}