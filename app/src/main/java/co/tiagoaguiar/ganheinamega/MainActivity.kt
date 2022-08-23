package co.tiagoaguiar.ganheinamega

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText: EditText = findViewById(R.id.edit_number)
        val txtResult: TextView = findViewById(R.id.txt_result)
        val btnGenerate: Button = findViewById(R.id.btn_generate)

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


    }

}
