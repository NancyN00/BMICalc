package com.example.bmicalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weightText = findViewById<EditText>(R.id.etweight)
        val heightText = findViewById<EditText>(R.id.etheight)
        val calcButton = findViewById<Button>(R.id.btncalculate)

        calcButton.setOnClickListener {
            val weight = weightText.text.toString()
            val height = heightText.text.toString()

            if(validateInput(weight,height)) {


                val bmi = weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100))
                //show bmi index with only two decimal places
                val bmi2Digits = String.format("%.2f", bmi).toFloat()
                displayResult(bmi2Digits)
            }
        }
    }
       //this private parameter is used to validate data before calculating height and weight
         private fun validateInput (weight:String?,height:String?):Boolean{

             return when{
                 weight.isNullOrBlank() ->{
                     Toast.makeText(this, "weight is empty", Toast.LENGTH_SHORT).show()
                     return false
                 }

                 height.isNullOrBlank() ->{
                     Toast.makeText(this, "height is empty", Toast.LENGTH_SHORT).show()
                     return false
                 }

                 else ->{
                     return true
                 }
             }
         }

    private fun displayResult(bmi:Float){
        val resultIndex = findViewById<TextView>(R.id.tvindex)
        val resultDescription = findViewById<TextView>(R.id.tvresult)
        val info = findViewById<TextView>(R.id.tvInfo)

           resultIndex.text = bmi.toString()
          info.text = "(Normal range is 18.5 - 24.9)"

        var resultText = ""
        var color = 0

        when {
            bmi<18.50 -> {
                resultText = "Underweight"
                color = R.color.under_weight
            }

            bmi in 18.50..24.99->{
                resultText = "Healthy"
                color = R.color.normal
            }

            bmi in 25.00..29.99->{
                resultText = "Overweight"
                color = R.color.over_weight
            }

            bmi > 29.99 -> {
                resultText = "Obese"
                color = R.color.obese
            }
        }

        resultDescription.setTextColor(ContextCompat.getColor(this,color))
        resultDescription.text = resultText
    }
}