package com.example.atividade1

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnCalcular).setOnClickListener { calculatIMC() }
    }

    private fun calculatIMC(){
        val edtAltura = findViewById<EditText>(R.id.edtAltura)
        val edtPeso = findViewById<EditText>(R.id.edtPeso)
        val result = findViewById<TextView>(R.id.lblResultado)

        if (edtAltura.text.toString() != "" && edtPeso.text.toString() != ""){
            val altura = (edtAltura.text.toString().toFloat())/100
            val peso = edtPeso.text.toString().toInt()

            val imc = peso/(altura*altura)

            if (imc < 18.5){
                result.text = "Seu IMC é de: "+String.format("%.2f", imc)+" isso indica magreza"
            }else if (imc >= 18.5 && imc < 24.9){
                result.text = "Seu IMC é de: "+String.format("%.2f", imc)+" isso indica normal"
            }else if (imc >= 24.9 && imc < 30){
                result.text = "Seu IMC é de: "+String.format("%.2f", imc)+" isso indica sobrepeso"
            }else if (imc >= 30 && imc < 35){
                result.text = "Seu IMC é de: "+String.format("%.2f", imc)+" isso indica obesidade Grau I"
            } else if (imc >= 35 && imc < 40){
                result.text = "Seu IMC é de: "+String.format("%.2f", imc)+" isso indica obesidade Grau II"
            }else{
                result.text = "Seu IMC é de: "+String.format("%.2f", imc)+" isso indica obesidade Grau III"
            }
        }else{
            result.text = "Por favor preencha os campos"
        }


    }


}