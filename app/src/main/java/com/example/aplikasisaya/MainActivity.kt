package com.example.aplikasisaya

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import androidx.navigation.Navigation
import androidx.navigation.findNavController

//        cobafirebase-ccd6c
class MainActivity : AppCompatActivity() {
    companion object{
        private const val TAG = "Kotlin Activity"
    }

    private lateinit var notificationManager: NotificationManagerCompat
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = Firebase.database.reference
        val setNotif = FirebaseDatabase.getInstance().getReference("MonitoringControl/Notif")

        val databaseListener = object : ValueEventListener {
            @SuppressLint("UnspecifiedImmutableFlag")
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val getNotif = dataSnapshot.child("MonitoringControl/Notif").getValue()

                if (getNotif.toString() == "1"){

                    val intent = Intent (this@MainActivity, MainActivity::class.java)
                    val pendingIntent = PendingIntent.getActivities(this@MainActivity,
                        0, arrayOf(intent), 0)

                    val title:String = "Penggilingan Selesai"
                    val message:String = "Semua Mesin Akan Mati"

                    val builder = NotificationCompat.Builder(this@MainActivity, BaseApplication.CHANNEL_ID)
                        .setSmallIcon(R.drawable.wheat)
                        .setContentTitle(title)
                        .setContentText(message)
//                        .setSubText("Sub text")
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setAutoCancel(true)
                        .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                        .setContentIntent(pendingIntent)
                        .setOnlyAlertOnce(true)

                    val notification = builder.build()
                    notificationManager.notify(1, notification)

                    setNotif.setValue(0)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        }

        database.addValueEventListener(databaseListener)
        notificationManager = NotificationManagerCompat.from(this)
    }
}