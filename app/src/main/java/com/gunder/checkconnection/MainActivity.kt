package com.gunder.checkconnection

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        deklarasi dan inisialisasi variabel
        val networkConnectionStatus = findViewById<TextView>(R.id.tvconnect)
//        gunakan thread untuk realtime pemantauan
        Thread(Runnable {
            while (true){
                var consStat: String = "Tidak terhubung"
//                memanggil manager connectivity
                val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//                memanggil informasi jaringan yang terhubung
                val netInfo = cm.allNetworkInfo
//                menemukan tipe koneksi apakah WIFI atau data seluler
                for (ni in netInfo){
                    if (ni.typeName.equals("WIFI", ignoreCase = true)){
                        if (ni.isConnected) consStat = "Terhubung dengan WIFI"
                    }
                    if (ni.typeName.equals("MOBILE", ignoreCase = true)){
                        if (ni.isConnected) consStat = "Terhubung dengan jaringan seluler"
                    }
                }

//                untuk bisa update element layout secara realtime, hasilnya akan dimaksukan
//                kedalam textView dalam variabel consStat
                runOnUiThread {
                    networkConnectionStatus.text = consStat
                }
            }
        }).start() // memulai thread
    }
}