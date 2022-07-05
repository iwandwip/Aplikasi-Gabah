package com.example.aplikasisaya

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.NotificationManagerCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.aplikasisaya.databinding.FragmentIntroBinding
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class introFragment : Fragment() {

    companion object{
        const val TAG = "Kotlin Activity"
    }

    private lateinit var notificationManager: NotificationManagerCompat
    private lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentIntroBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_intro, container, false)

        database = Firebase.database.reference
        val pengaduk = FirebaseDatabase.getInstance().getReference("MonitoringControl/Control/Manual/State-Pengaduk")
        val heater = FirebaseDatabase.getInstance().getReference("MonitoringControl/Control/Manual/State-Heater")
        val servo = FirebaseDatabase.getInstance().getReference("MonitoringControl/Control/Manual/State-Servo")
        val blower_1 = FirebaseDatabase.getInstance().getReference("MonitoringControl/Control/Manual/State-Blower-1")
        val blower_2 = FirebaseDatabase.getInstance().getReference("MonitoringControl/Control/Manual/State-Blower-2")
        val penggiling = FirebaseDatabase.getInstance().getReference("MonitoringControl/Control/Manual/State-Penggiling")
        val modeManual = FirebaseDatabase.getInstance().getReference("MonitoringControl/Mode/Manual")
        val modeOtomatis = FirebaseDatabase.getInstance().getReference("MonitoringControl/Mode/Otomatis")

        var statePengaduk = 0
        var stateHeater = 0
        var stateServo = 0
        var stateBlower_1 = 0
        var stateBlower_2 = 0
        var statePenggiling = 0

        var flag_Otomatis = 0
        var flag_Manual = 0

        var beratSebelum:String = " "
        var beratSesudah:String = " "

//                TODO("------ MonitoringControl ------")
        val databaseListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val suhu = dataSnapshot.child("MonitoringControl/Monitoring/Suhu").getValue()
                val kelembaban = dataSnapshot.child("MonitoringControl/Monitoring/Kelembaban").getValue()
//                val beratSebelum = dataSnapshot.child("MonitoringControl/Monitoring/BeratSebelum").getValue()
//                val beratSesudah = dataSnapshot.child("MonitoringControl/Monitoring/BeratSesudah").getValue()
//                val setPointManual = dataSnapshot.child("MonitoringControl/Control/Manual/SetPointManual").getValue()
                val statePengadukFirebase = dataSnapshot.child("MonitoringControl/Control/Manual/State-Pengaduk").getValue()
                val stateHeaterFirebase = dataSnapshot.child("MonitoringControl/Control/Manual/State-Heater").getValue()
                val stateServoFirebase = dataSnapshot.child("MonitoringControl/Control/Manual/State-Servo").getValue()
                val stateBlowerFirebase_1 = dataSnapshot.child("MonitoringControl/Control/Manual/State-Blower-1").getValue()
                val stateBlowerFirebase_2 = dataSnapshot.child("MonitoringControl/Control/Manual/State-Blower-2").getValue()
                val statePenggilingFirebase = dataSnapshot.child("MonitoringControl/Control/Manual/State-Penggiling").getValue()
                val stateModeManual = dataSnapshot.child("MonitoringControl/Mode/Manual").getValue()
                val stateModeOtomatis = dataSnapshot.child("MonitoringControl/Mode/Otomatis").getValue()

//                TODO("------ Manual Control ------")
                binding.tombolPengaduk.setOnClickListener {
                    if (stateModeManual.toString() == "0")Toast.makeText(
                        activity, "Mohon tekan tombol mulai", Toast.LENGTH_SHORT).show()
                    else if (stateModeManual.toString() == "1"){
                        if (statePengaduk == 0){
                            statePengaduk = 1
                            pengaduk.setValue(1)
                        }else if (statePengaduk == 1){
                            statePengaduk = 0
                            pengaduk.setValue(0)
                        }
                    }
                }
                binding.tombolPengering.setOnClickListener {
                    if (stateModeManual.toString() == "0")Toast.makeText(
                        activity, "Mohon tekan tombol mulai", Toast.LENGTH_SHORT).show()
                    else if (stateModeManual.toString() == "1"){
                        if (stateHeater == 0){
                            stateHeater = 1
                            heater.setValue(1)
                        }else if (stateHeater == 1){
                            stateHeater = 0
                            heater.setValue(0)
                        }
                    }
                }
                binding.tombolServo.setOnClickListener {
                    if (stateModeManual.toString() == "0")Toast.makeText(
                        activity, "Mohon tekan tombol mulai", Toast.LENGTH_SHORT).show()
                    else if (stateModeManual.toString() == "1"){
                        if (stateServo == 0){
                            stateServo = 1
                            servo.setValue(1)
                        }else if (stateServo == 1){
                            stateServo = 0
                            servo.setValue(0)
                        }
                    }
                }
                binding.tombolBlower.setOnClickListener {
                    if (stateModeManual.toString() == "0")Toast.makeText(
                        activity, "Mohon tekan tombol mulai", Toast.LENGTH_SHORT).show()
                    else if (stateModeManual.toString() == "1"){
                        if (stateBlower_1 == 0){
                            stateBlower_1 = 1
                            blower_1.setValue(1)
                        }else if (stateBlower_1 == 1){
                            stateBlower_1 = 0
                            blower_1.setValue(0)
                        }
                    }
                }
                binding.tombolBlower1.setOnClickListener {
                    if (stateModeManual.toString() == "0")Toast.makeText(
                        activity, "Mohon tekan tombol mulai", Toast.LENGTH_SHORT).show()
                    else if (stateModeManual.toString() == "1"){
                        if (stateBlower_2 == 0){
                            stateBlower_2 = 1
                            blower_2.setValue(1)
                        }else if (stateBlower_2 == 1){
                            stateBlower_2 = 0
                            blower_2.setValue(0)
                        }
                    }
                }
                binding.tombolPenggiling.setOnClickListener {
                    if (stateModeManual.toString() == "0")Toast.makeText(
                        activity, "Mohon tekan tombol mulai", Toast.LENGTH_SHORT).show()
                    else if (stateModeManual.toString() == "1"){
                        if (statePenggiling == 0){
                            statePenggiling = 1
                            penggiling.setValue(1)
                        }else if (statePenggiling == 1){
                            statePenggiling = 0
                            penggiling.setValue(0)
                        }
                    }
                }
//                TODO("------ Button Manual ------")
                binding.buttonMulaiManual.setOnClickListener {
                    if (flag_Manual == 0 && flag_Otomatis == 1){
                        val berat = dataSnapshot.child("MonitoringControl/Monitoring/Berat").getValue()
                        beratSesudah = berat.toString() + "Kg"
                        binding.textView27.text = beratSesudah
                    }

                    flag_Manual = 1
                    flag_Otomatis = 0

                    modeOtomatis.setValue(0)
                    modeManual.setValue(1)

                    statePengaduk = 0
                    stateHeater = 0
                    stateServo = 0
                    stateBlower_1 = 0
                    stateBlower_2 = 0
                    statePenggiling = 0

                    pengaduk.setValue(0)
                    heater.setValue(0)
                    servo.setValue(0)
                    blower_1.setValue(0)
                    blower_2.setValue(0)
                    penggiling.setValue(0)

                    val berat = dataSnapshot.child("MonitoringControl/Monitoring/Berat").getValue()
                    beratSebelum = berat.toString() + "Kg"
                    binding.textView18.text = beratSebelum
                    Toast.makeText(activity, "Memulai Manual Control", Toast.LENGTH_SHORT).show()

                }
                binding.buttonStopManual.setOnClickListener {
                    flag_Manual = 0
                    flag_Otomatis = 0

                    modeOtomatis.setValue(0)
                    modeManual.setValue(0)

                    statePengaduk = 0
                    stateHeater = 0
                    stateServo = 0
                    stateBlower_1 = 0
                    stateBlower_2 = 0
                    statePenggiling = 0

                    pengaduk.setValue(0)
                    heater.setValue(0)
                    servo.setValue(0)
                    blower_1.setValue(0)
                    blower_2.setValue(0)
                    penggiling.setValue(0)

                    val berat = dataSnapshot.child("MonitoringControl/Monitoring/Berat").getValue()
                    beratSebelum = berat.toString() + "Kg"
                    binding.textView19.text = beratSebelum
                    Toast.makeText(activity, "Manual Control Berhenti", Toast.LENGTH_SHORT).show()
                }

//                TODO("------ Monitoring ------")
                binding.textView11.text = suhu.toString() + "°C"
                binding.textView12.text = kelembaban.toString() + "%"
//                binding.textView18.text = beratSebelum.toString() + "kg"
//                binding.textView19.text = beratSesudah.toString() + "kg"
//
                binding.textView21.text = suhu.toString() + "°C"
                binding.textView23.text = kelembaban.toString() + "%"
//                binding.textView25.text = beratSebelum.toString() + "kg"
//                binding.textView27.text = beratSesudah.toString() + "kg"

//                TODO("------ Default State ------")
                if (statePengadukFirebase.toString() == "1")binding.textViewPengaduk.text = "Pengaduk: ON"
                else binding.textViewPengaduk.text = "Pengaduk: OFF"
                if (stateHeaterFirebase.toString() == "1")binding.textViewPengering.text = "Heater: ON"
                else binding.textViewPengering.text = "Heater: OFF"
                if (stateServoFirebase.toString() == "1")binding.textViewServo.text = "Servo: ON"
                else binding.textViewServo.text = "Servo: OFF"
                if (stateBlowerFirebase_1.toString() == "1")binding.textViewBlower.text = "Blower 1: ON"
                else binding.textViewBlower.text = "Blower 1: OFF"
                if (stateBlowerFirebase_2.toString() == "1")binding.textViewBlower1.text = "Blower 2: ON"
                else binding.textViewBlower1.text = "Blower 2: OFF"
                if (statePenggilingFirebase.toString() == "1")binding.textViewPenggiling.text = "Penggiling: ON"
                else binding.textViewPenggiling.text = "Penggiling: OFF"
                if (stateModeManual.toString() == "1") binding.textViewModeManual.text = "Mode Manual: Aktif"
                else binding.textViewModeManual.text = "Mode Manual: Non-Aktif"
                if (stateModeOtomatis.toString() == "1") binding.textViewModeOtomatis.text = "Mode Otomatis: Aktif"
                else binding.textViewModeOtomatis.text = "Mode Otomatis: Non-Aktif"

//                TODO("------ Automatic Control ------")
                binding.buttonOtomatisMulai.setOnClickListener {
                    if (flag_Manual == 1 && flag_Otomatis == 0){
                        val berat = dataSnapshot.child("MonitoringControl/Monitoring/Berat").getValue()
                        beratSesudah = berat.toString() + "Kg"
                        binding.textView19.text = beratSesudah
                    }

                    flag_Otomatis = 1
                    flag_Manual = 0

                    modeOtomatis.setValue(1)
                    modeManual.setValue(0)

                    if (flag_Otomatis == 1 ) {
                        val berat = dataSnapshot.child("MonitoringControl/Monitoring/Berat").getValue()
                        beratSebelum = berat.toString() + "Kg"
                        binding.textView25.text = beratSebelum

                        if (flag_Otomatis == 1) {
                            // Update Firebase Otomatis  = 1
                            modeOtomatis.setValue(1)
                        }
                        Toast.makeText(activity, "Memulai Control Otomatis", Toast.LENGTH_SHORT).show()
                    }
                }
                binding.buttonOtomatisStop.setOnClickListener {
                    flag_Otomatis = 0
                    flag_Manual = 0

                    modeOtomatis.setValue(0)
                    modeManual.setValue(0)

                    if (flag_Otomatis == 0) {
                        val berat = dataSnapshot.child("MonitoringControl/Monitoring/Berat").getValue()

                        beratSesudah = berat.toString() + "Kg"
                        binding.textView27.text = beratSesudah
                        if (flag_Otomatis == 0) {
                            // Update Firebase Otomatis  = 0
                            modeOtomatis.setValue(0)
                        }
                        Toast.makeText(activity, "Control Otomatis Terhenti", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w(introFragment.TAG, "Failed to read value.", error.toException())
            }
        }
        database.addValueEventListener(databaseListener)
//        TODO("------ Notification ------")

        return binding.root
    }
}