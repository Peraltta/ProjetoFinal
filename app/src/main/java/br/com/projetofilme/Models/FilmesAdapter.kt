package br.com.projetofilme.Models

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.projetofilme.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class FilmesAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items:List<FilmeModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FilmeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout_filme_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is FilmeViewHolder->{
                holder.bind(items.get(position))
            }
        }
    }
    class FilmeViewHolder constructor(
        itemView:View

    ):RecyclerView.ViewHolder(itemView){
        val titulo_filme = itemView.tvTitulo
        val imagem_filme = itemView.img_filme
        val sinopse = itemView.tvSinopse

        fun bind(filmeBind:FilmeModel){

            val requestOption = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
            Glide.with(itemView.context).applyDefaultRequestOptions(requestOption)
                .load(filmeBind.imagem)
                .info(filmeBind.img_filme)
            titulo_filme.setText(filmeBind.original_title)
            titulo_filme.setText(filmeBind.overview)
        }

        }

    fun submitList(filmeList: List<FilmeModel>){
        items = filmeList
    }

    override fun getItemCount(): Int {
        return items.size
    }
}