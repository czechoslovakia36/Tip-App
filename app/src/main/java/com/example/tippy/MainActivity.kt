package com.example.tippy

import android.animation.ArgbEvaluator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.core.content.ContextCompat

private const val TAG= "MainActivity"

private const val INITIAL_TIP_PERCENT=15

class MainActivity : AppCompatActivity() {

    private lateinit var  etBaseAmount:EditText
    private  lateinit var seekBarTip: SeekBar

    private  lateinit var tvTipPercent :TextView
    private  lateinit var tvTipAmount:TextView
    private  lateinit var tvTotalAmount : TextView

    private lateinit var tvTipDescription :TextView






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etBaseAmount= findViewById(R.id.etBaseAmount)
        seekBarTip= findViewById(R.id.seekBarTip)
        tvTipPercent= findViewById(R.id.tvTipPercentageLabel)
        tvTipAmount= findViewById(R.id.tvTipAmount)
        tvTotalAmount= findViewById(R.id.tvTotalAmount)

        tvTipDescription= findViewById(R.id.tvTipDescription)

//      Initial Setups

        seekBarTip.progress= INITIAL_TIP_PERCENT
        tvTipPercent.text="$INITIAL_TIP_PERCENT%"
        updateTipDescription(INITIAL_TIP_PERCENT)


        seekBarTip?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int,
                                           fromUser: Boolean) {
               Log.i("MainActivity","progress is $progress")
                tvTipPercent.text="$progress%"
                computeTipAndTotal()
                updateTipDescription(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
//                Toast.makeText(applicationContext, "seekbar touch started!", Toast.LENGTH_SHORT).show()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
//                Toast.makeText(applicationContext, "seekbar touch stopped!", Toast.LENGTH_SHORT).show()
            }
        })

        etBaseAmount?.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                Log.i(TAG,"afterTextChanged $s")
                computeTipAndTotal()
            }
        })

    }

    private fun updateTipDescription(tipPercent: Int) {
            val tipDescription = when (tipPercent){
                in 0..9 -> "Poor"
                in 10..14 -> "Acceptable"
                in 15..19 -> "Good"
                in 20..24  -> "Great"
                else -> "Amazing "
            }
        tvTipDescription.text= tipDescription

//        update the color based on the tipPercent
        val color= ArgbEvaluator().evaluate(
            tipPercent.toFloat()/seekBarTip.max,
            ContextCompat.getColor(this,R.color.color_worst_tip),
            ContextCompat.getColor(this,R.color.color_best_tip)


        )
        tvTipDescription.setTextColor(color.toString().toInt())
    }

    private fun computeTipAndTotal() {

        if(etBaseAmount.text.isEmpty()){
            tvTipAmount.text=""
            tvTotalAmount.text=""
            return
        }
//        1.Get the value of the base and tip percent
//        2.Compute the tip and total
//        3.Update the UI

        val baseAmount = etBaseAmount.text.toString().toDouble()
        val tipPercent = seekBarTip.progress

        val tipAmount=baseAmount*tipPercent/100

        val totalAmount= baseAmount+tipAmount

        tvTipAmount.text= "%.2f".format(tipAmount)
        tvTotalAmount.text="%.2f".format(totalAmount)

    }
}