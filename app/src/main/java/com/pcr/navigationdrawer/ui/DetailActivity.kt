package com.pcr.navigationdrawer.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.pcr.navigationdrawer.R
import com.pcr.navigationdrawer.model.Film
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_FILM = "extra_film"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val film: Film? = intent.extras?.getParcelable(EXTRA_FILM)

        film?.let {
            Glide
                .with(this)
                .load(it.gambar)
                .into(detail_cover)

            detail_tahun.text = it.tahun
            detail_judul.text = it.judul
            detail_deskripsi.text = it.deskripsi
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                this.finish()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}