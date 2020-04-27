package com.pearldroidos.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var operator = ""
    private var oldNumber = ""
    private var isNewOperation = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun btnNumberEvent(view: View) {
        if (isNewOperation) {
            Log.d("Test", "In" + edtNumber.text.toString())
            edtNumber.setText("0")
        }

        isNewOperation = false

        val btnSelected = view as Button
        var number: String = edtNumber.text.toString()


        when (btnSelected.id) {
            btnZero.id -> number += "0"
            btnOne.id -> number += "1"
            btnTwo.id -> number += "2"
            btnThree.id -> number += "3"
            btnFour.id -> number += "4"
            btnFive.id -> number += "5"
            btnSix.id -> number += "6"
            btnSeven.id -> number += "7"
            btnEight.id -> number += "8"
            btnNine.id -> number += "9"
        }

        showText(number)
    }


    fun btnOperator(view: View) {
        val btnSelected = view as Button

        if (oldNumber != "") {
            sumUp()
        }

        when (btnSelected.id) {
            btnPlus.id -> operator = "+"
            btnMinus.id -> operator = "-"
            btnMulti.id -> operator = "*"
            btnDivide.id -> operator = "/"
        }


        oldNumber = edtNumber.text.toString()
        isNewOperation = true
    }

    fun btnEqual(view: View) {
        sumUp()
    }

    fun btnPercent(view: View) {
        val number: Double = edtNumber.text.toString().toDouble()

        edtNumber.setText((number / 100).toString())
    }

    fun btnClear(view: View) {
        edtNumber.setText("0")
        operator = ""
        oldNumber = ""
        isNewOperation = true
    }

    fun btnMinusPlus(view: View) {
        var number: String = edtNumber.text.toString()

        when {
            edtNumber.text.toString() == "0" -> "0"
            edtNumber.text[0] == '-' -> number = number.removePrefix("-")
            else -> number = "-$number"
        }

        showText(number)
    }

    fun btnDot(view: View) {
        var number: String = edtNumber.text.toString()
        if (!number.contains(".")) number += "." //TODO: prevent adding more than one

        showText(number)
        isNewOperation = false
    }

    private fun sumUp() {
        val newNumber = edtNumber.text.toString()
        var finalNumber: Double? = 0.0

        when (operator) {
            "+" -> finalNumber = oldNumber.toDouble() + newNumber.toDouble()
            "-" -> finalNumber = oldNumber.toDouble() - newNumber.toDouble()
            "*" -> finalNumber = oldNumber.toDouble() * newNumber.toDouble()
            "/" -> finalNumber = oldNumber.toDouble() / newNumber.toDouble()
        }

        if (finalNumber.toString().contains(".0")) {
            edtNumber.setText(finalNumber.toString().removeSuffix(".0"))
        } else if (finalNumber.toString().split(".")[1].length < 5) {
            edtNumber.setText(finalNumber.toString())
        } else {
            edtNumber.setText(String.format("%.5f", finalNumber))
        }

        oldNumber = ""
        isNewOperation = true
    }

    private fun showText(number: String) {
        edtNumber.setText(
            if (number.length == 1 && number[0] == '0') "0" else if (number.contains(".")) number else number.removePrefix(
                "0"
            )
        )
    }

}
