package com.example.submission4.ui.tv


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isEmpty
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.submission4.R
import com.example.submission4.adapter.TvAdapter
import kotlinx.android.synthetic.main.fragment_tv.*
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class TvFragment : Fragment() {
    private lateinit var adapter: TvAdapter
    private lateinit var tvViewModel: TvViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = TvAdapter()
        adapter.notifyDataSetChanged()

        rv_tvs.layoutManager = LinearLayoutManager(context)
        rv_tvs.adapter = adapter

        tvViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            TvViewModel::class.java)
        if (rv_tvs.isEmpty()){
            showLoadingTv(true)
            val language = Locale.getDefault().getDisplayLanguage()
            if (language == "English") {
                val locale = "en-US"
                tvViewModel.setTvShow(locale)
            } else {
                val locale = "id-ID"
                tvViewModel.setTvShow(locale)
            }

        }

        tvViewModel.getTv().observe(viewLifecycleOwner, androidx.lifecycle.Observer { filmItems ->
            if (filmItems != null ){
                adapter.setData(filmItems)
                showLoadingTv(false)

            }
        })

    }

    private fun showLoadingTv(state: Boolean) {
        if (state) {
            progressBarTv.visibility = View.VISIBLE
        } else {
            progressBarTv.visibility = View.GONE
        }
    }
}
