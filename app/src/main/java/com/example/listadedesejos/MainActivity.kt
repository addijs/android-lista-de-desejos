package com.example.listadedesejos

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.listadedesejos.models.Desejo
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {
  val LIST_VIEW_ADD = 1
  val LIST_VIEW_ITEM_EDIT = 2

  private lateinit var lvDesejos: ListView
  private lateinit var fabAdd: FloatingActionButton
  private lateinit var listaDesejos: List<Desejo>

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    this.listaDesejos = ArrayList()

    this.lvDesejos = findViewById(R.id.lvDesejos)
    this.fabAdd = findViewById(R.id.fabAdd)
    this.lvDesejos.adapter = ArrayAdapter(
        this,
        android.R.layout.simple_list_item_1,
        this.listaDesejos
    )

    this.lvDesejos.onItemClickListener = ClickLista()
    this.lvDesejos.onItemLongClickListener = LongClickLista()
    this.fabAdd.setOnClickListener(ClickFloatingActionButton())

  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)

    if (resultCode == RESULT_OK) {
      if (requestCode == LIST_VIEW_ADD) {
        val intentDesejo = data?.getSerializableExtra("DESEJO") as Desejo
        (this.lvDesejos.adapter as ArrayAdapter<Desejo>).add(intentDesejo)
      }

      if(requestCode == LIST_VIEW_ITEM_EDIT) {
        val intentDesejo = data?.getSerializableExtra("DESEJO") as Desejo

        for (desejo in listaDesejos) {
          if (desejo.id == intentDesejo.id) {
            desejo.descricao = intentDesejo.descricao
            desejo.valor = intentDesejo.valor
            (this.lvDesejos.adapter as ArrayAdapter<Desejo>).notifyDataSetChanged()

            break
          }
        }
      }
    }
  }

  inner class ClickFloatingActionButton: View.OnClickListener {
    override fun onClick(v: View?) {
      val intent = Intent(this@MainActivity, FormActivity::class.java)
      startActivityForResult(intent, LIST_VIEW_ADD)
    }
  }

  inner class ClickLista: AdapterView.OnItemClickListener {
    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
      val desejo = this@MainActivity.listaDesejos[position]
      val intent = Intent(this@MainActivity, FormActivity::class.java)
      intent.putExtra("DESEJO_TO_EDIT", desejo)
      startActivityForResult(intent, LIST_VIEW_ITEM_EDIT)
    }
  }

  inner class LongClickLista: AdapterView.OnItemLongClickListener {
    override fun onItemLongClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long): Boolean {
      val desejo = this@MainActivity.listaDesejos[position]
      (this@MainActivity.lvDesejos.adapter as ArrayAdapter<Desejo>).remove(desejo)

      return true
    }

  }


}