package com.example.listadedesejos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.listadedesejos.models.Desejo
import java.io.Serializable

class FormActivity : AppCompatActivity() {
    private lateinit var etDescricao: EditText
    private lateinit var etValor: EditText
    private lateinit var btnCadastrar: Button
    private lateinit var btnCancelar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        this.etDescricao = findViewById(R.id.etDescricao)
        this.etValor = findViewById(R.id.etValor)
        this.btnCadastrar = findViewById(R.id.btnCadastrar)
        this.btnCancelar = findViewById(R.id.btnCancelar)

        this.btnCadastrar.setOnClickListener(ClickBtnCadastrar())
        this.btnCancelar.setOnClickListener(ClickBtnCancelar())
    }

    inner class ClickBtnCadastrar: View.OnClickListener {
        override fun onClick(v: View?) {
            val descricao: String = this@FormActivity.etDescricao.text.toString()
            val valor: Float = this@FormActivity.etValor.text.toString().toFloat()

            val desejo = Desejo(descricao, valor)

            Log.i("PRINT", desejo.toString())

            val intent = Intent()
            intent.putExtra("DESEJO", desejo as Serializable)

            setResult(RESULT_OK, intent)
            finish()
        }
    }

    inner class ClickBtnCancelar: View.OnClickListener {
        override fun onClick(v: View?) {
            setResult(RESULT_CANCELED)
            finish()
        }
    }

}