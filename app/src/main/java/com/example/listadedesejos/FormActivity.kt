package com.example.listadedesejos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.listadedesejos.models.Desejo
import java.io.Serializable

class FormActivity : AppCompatActivity() {

  // Using an extension property to ease
  // getting the text on EditText
  var EditText.value
    get() = this.text.toString()
    set(value) {
      this.setText(value)
    }

  private lateinit var etDescricao: EditText
  private lateinit var etValor: EditText
  private lateinit var btnSalvar: Button
  private lateinit var btnCancelar: Button

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_form)

    this.etDescricao = findViewById(R.id.etDescricao)
    this.etValor = findViewById(R.id.etValor)
    this.btnSalvar = findViewById(R.id.btnSalvar)
    this.btnCancelar = findViewById(R.id.btnCancelar)

    if (intent.hasExtra("DESEJO_TO_EDIT")) {
      val desejo = intent.getSerializableExtra("DESEJO_TO_EDIT") as Desejo

      this.etDescricao.value = desejo.descricao
      this.etValor.value = desejo.valor.toString()
      this.btnSalvar.text = "Atualizar"
    }

    this.btnSalvar.setOnClickListener(ClickBtnSalvar())
    this.btnCancelar.setOnClickListener(ClickBtnCancelar())
  }

  inner class ClickBtnSalvar: View.OnClickListener {
    override fun onClick(v: View?) {
      val descricao: String = this@FormActivity.etDescricao.value
      val valor: Float = this@FormActivity.etValor.value.toFloat()

      val desejo: Desejo =
          if (intent.hasExtra("DESEJO_TO_EDIT")) {
            val intentDesejo = intent.getSerializableExtra("DESEJO_TO_EDIT") as Desejo
            intentDesejo.descricao = descricao
            intentDesejo.valor = valor
            intentDesejo
          } else {
            val novoDesejo = Desejo(descricao, valor)
            novoDesejo
          }

      val intent = Intent()
      intent.putExtra("DESEJO", desejo)

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