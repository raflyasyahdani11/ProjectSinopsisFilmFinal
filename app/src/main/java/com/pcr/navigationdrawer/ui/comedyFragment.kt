package com.pcr.navigationdrawer.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.pcr.navigationdrawer.R
import com.pcr.navigationdrawer.adapter.FilmRecyclerViewAdapter
import com.pcr.navigationdrawer.model.Film
import com.pcr.navigationdrawer.utill.Constant
import kotlinx.android.synthetic.main.film_comedy.*
import kotlinx.android.synthetic.main.film_horror.*

class comedyFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.film_comedy, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val queue = Volley.newRequestQueue(requireContext())
        val url = "${Constant.BASE_URL}/film?kategori=comedy"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { json ->
                val responseFilms = json.getJSONArray("data")
                val listFilms = ArrayList<Film>()

                for (i in 0 until responseFilms.length()) {
                    val item = responseFilms.getJSONObject(i)

                    val film = Film(
                        judul = item.getString("judul_film"),
                        deskripsi = item.getString("deskripsi_film"),
                        tahun = item.getString("tahun_rilis"),
                        gambar = item.getString("gambar_film")
                    )
                    listFilms.add(film)
                }

                val adapter = FilmRecyclerViewAdapter(listFilms)

                rv_film_comedy.apply {
                    layoutManager = GridLayoutManager(requireContext(), 3)
                    this.adapter = adapter
                }

                adapter.apply {
                    setOnItemClickListener { film ->
                        val intent = Intent(requireContext(), DetailActivity::class.java)
                        intent.putExtra(DetailActivity.EXTRA_FILM, film)

                        startActivity(intent)
                    }
                    notifyDataSetChanged()
                }

            }, {
                Toast.makeText(requireContext(), "${it.message}", Toast.LENGTH_SHORT).show()
            }
        )
        queue.add(jsonObjectRequest)
    }
}