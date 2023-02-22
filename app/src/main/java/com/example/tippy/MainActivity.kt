package com.example.tippy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AbsSeekBar
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import java.time.temporal.TemporalAmount

private const val TAG= "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var  etBaseAmount:EditText
    private  lateinit var seekBarTip: SeekBar

    private  lateinit var tvTipPercent :TextView
    private  lateinit var tvTipAmount:TextView
    private  lateinit var tvTotalAmount :TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etBaseAmount= findViewById(R.id.etBaseAmount)
        seekBarTip= findViewById(R.id.seekBarTip)
        tvTipPercent= findViewById(R.id.tvTipPercentageLabel)
        tvTipAmount= findViewById(R.id.tvTipAmount)
        tvTotalAmount= findViewById(R.id.tvTotalAmount)


        seekBarTip?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int,
                                           fromUser: Boolean) {
               Log.i("MainActivity","progress is $progress")
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
//                Toast.makeText(applicationContext, "seekbar touch started!", Toast.LENGTH_SHORT).show()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
//                Toast.makeText(applicationContext, "seekbar touch stopped!", Toast.LENGTH_SHORT).show()
            }
        })




    }
}