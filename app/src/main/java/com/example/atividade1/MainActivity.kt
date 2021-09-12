package com.example.atividade1

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.atividade1.dataroom.AppDatabase
import com.example.atividade1.dataroom.IMC
import com.example.demodatabase.adapters.ImcListAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var db: AppDatabase;

    private lateinit var recyclerView:RecyclerView;
    private lateinit var adapter: ImcListAdapter;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnCalcular).setOnClickListener { calculatIMC() }

        db = Room.databaseBuilder(this, AppDatabase::class.java, "atividade1-db-room").allowMainThreadQueries().build()


        recyclerView = findViewById<RecyclerView>(R.id.recyclerview)

        adapter = ImcListAdapter()
        adapter.setUsuarioDelete {
            db.imcDao().delete(it)
            atualizaTela()
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // primeiro carregamento de tela
        atualizaTela()
    }

    private fun calculatIMC(){
        val edtNome = findViewById<EditText>(R.id.edtNome)
        val edtAltura = findViewById<EditText>(R.id.edtAltura)
        val edtPeso = findViewById<EditText>(R.id.edtPeso)

        if (edtAltura.text.toString() != "" && edtPeso.text.toString() != ""){
            val name = edtNome.text.toString();
            val altura = (edtAltura.text.toString().toFloat())/100
            val peso = edtPeso.text.toString().toInt()
            var result = ""

            val imc = peso/(altura*altura)

            if (imc < 18.5){
                result = "Seu IMC é de: "+String.format("%.2f", imc)+" isso indica magreza"
            }else if (imc >= 18.5 && imc < 24.9){
                result = "Seu IMC é de: "+String.format("%.2f", imc)+" isso indica normal"
            }else if (imc >= 24.9 && imc < 30){
                result = "Seu IMC é de: "+String.format("%.2f", imc)+" isso indica sobrepeso"
            }else if (imc >= 30 && imc < 35){
                result = "Seu IMC é de: "+String.format("%.2f", imc)+" isso indica obesidade Grau I"
            } else if (imc >= 35 && imc < 40){
                result = "Seu IMC é de: "+String.format("%.2f", imc)+" isso indica obesidade Grau II"
            }else{
                result = "Seu IMC é de: "+String.format("%.2f", imc)+" isso indica obesidade Grau III"
            }

            salvaImc(name, peso, altura, imc, result)

            atualizaTela()
        }
    }

    override fun onDestroy() {
        db.close()
        super.onDestroy()
    }

    fun atualizaTela() {
        val encontrados:List<IMC> = db.imcDao().getResult();
        adapter.submitList(encontrados)
    }

    fun salvaImc(name:String, peso:Int, altura: Float, imc: Float, result: String) {
        val novoImc:IMC = IMC(null, name, peso, altura, imc, result)
        db.imcDao().insertAll(novoImc)
    }


}