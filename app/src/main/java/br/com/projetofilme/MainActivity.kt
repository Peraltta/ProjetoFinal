package br.com.projetofilme

import android.os.Bundle
import android.widget.Adapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.projetofilme.Models.FilmeModel
import br.com.projetofilme.Models.FilmesAdapter
import br.com.projetofilme.Models.RepositorioFilmes
import br.com.projetofilme.Models.Spacingitem
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.GsonBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var filmeAdapter:FilmesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inicializarRecycleView()
        adicionarDataSet()

        val textView = findViewById<TextView>(R.id.recycle_view)

        val queue = Volley.newRequestQueue(this)
        val url = "https://api.themoviedb.org/3/movie/550?api_key=64eabeb442d12de394230da8aa838c9c"

        val stringRequest = StringRequest(Request.Method.GET, url,
                { response ->

                    val gson = GsonBuilder().create()
                    val result = gson.fromJson(response.toString(),Array<FilmeModel>::class.java)
                    textView.text = result.firstOrNull()?.original_title
                    textView.text = result.firstOrNull()?.overview

                },
                {
                    textView.text = "Erro ao executar"
                })
        queue.add(stringRequest)

    }

    private fun adicionarDataSet(){
        val data = RepositorioFilmes.createDataSet()
        filmeAdapter.submitList(data)
    }

    fun inicializarRecycleView(){
        recycle_view.apply{
            layoutManager = LinearLayoutManager(this@MainActivity)
            val toSpacing = Spacingitem(30)
            addItemDecoration(toSpacing)
            filmeAdapter = FilmesAdapter()
            Adapter = filmeAdapter
        }
    }
}