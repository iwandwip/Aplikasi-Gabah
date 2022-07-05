package com.example.aplikasisaya

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler


@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
//    Deklarasi Variabel untuk timer Splash Screen
    private val SPLASH_TIME_OUT:Long = 3000 //Delay 3 detik
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

//  Kode untuk menjalankan main activity setelah timer splash screen habis
        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, SPLASH_TIME_OUT)

//        Untuk membuat splash screen dijalankan pertama
//        Ubah pengaturan di android manifest
    }
}