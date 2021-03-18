package com.example.listadedesejos

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.listadedesejos.models.Desejo
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var lvDesejos: ListView
    private lateinit var fabAdd: FloatingActionButton
    private lateinit var listaDesejos: List<Desejo>

    val LIST_VIEW = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.listaDesejos = ArrayList()

        this.lvDesejos = findViewById(R.id.lvDesejos)
        this.fabAdd = findViewById(R.id.fabAdd)

        this.fabAdd.setOnClickListener(ClickFloatingActionButton())

        this.lvDesejos.adapter = ArrayAdapter<Desejo>(
            this,
            android.R.layout.simple_list_item_1,
            this.listaDesejos
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            if (requestCode == LIST_VIEW) {
                val desejo = data?.getSerializableExtra("DESEJO") as Desejo

                (this.lvDesejos.adapter as ArrayAdapter<Desejo>).add(desejo)
            }
        }
    }

    inner class ClickFloatingActionButton: View.OnClickListener {
        override fun onClick(v: View?) {
            val intent = Intent(this@MainActivity, FormActivity::class.java)
            startActivityForResult(intent, LIST_VIEW)
        }
    }
}