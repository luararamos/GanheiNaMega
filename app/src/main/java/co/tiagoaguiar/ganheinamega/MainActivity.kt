package co.tiagoaguiar.ganheinamega

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText: EditText = findViewById(R.id.edit_number)
        val txtResult: TextView = findViewById(R.id.txt_result)
        val btnGenerate: Button = findViewById(R.id.btn_generate)

        prefs = getSharedPreferences("db", Context.MODE_PRIVATE)
        val result = prefs.getString("result", null)

        // if -> let
//        if (result !=  null){
//            txtResult.text = "Ultima aposta: $result"
//        }
        result?.let {
            txtResult.text = "Ultima aposta: $it"
        }

        btnGenerate.setOnClickListener {
            val text = editText.text.toString()
            numberGenerator(text, txtResult)
        }

    }

    private fun numberGenerator(text: String, txtResult: TextView) {
        // Primeiro verificamos a falha
        // Assim não ficamos com vários if e else
        if (text.isEmpty()) {
            Toast.makeText(this, "Informe um número entre 6 e 15", Toast.LENGTH_LONG).show()
            return
        }
        val qtd = text.toInt()
        if (qtd < 6 || qtd > 15) {
            Toast.makeText(this, "Informe um número entre 6 e 15", Toast.LENGTH_LONG).show()
            return
        }
        //Depois o sucesso
        val numbers = mutableSetOf<Int>()
        val random = Random
        while (true) {
            val number = random.nextInt(60)
            numbers.add(number + 1)
            if (numbers.size == qtd) {
                break
            }
        }
        // 1 - 2 - 3 -4 6
        txtResult.text = numbers.joinToString(" - ")

        val editor = prefs.edit()
        editor.putString("result", txtResult.text.toString())
        // commit -> salvar de forma sincrona ( bloquear a interface) e retorna se teve sucesso ou não
        // apply -> salvar de forma assincrona (nao vai bloquear a interface) e não retorna se teve sucesso ou não
        editor.apply()


        //alternativa 2
//        prefs.edit().apply {
//            putString("result", txtResult.text.toString())
//            apply()
//        }

    }

}
