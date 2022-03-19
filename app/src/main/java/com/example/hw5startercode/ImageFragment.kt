package com.example.hw5startercode

import android.app.WallpaperManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import com.bumptech.glide.Glide
import java.io.IOException
import android.graphics.drawable.BitmapDrawable
import androidx.core.graphics.drawable.toBitmap


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"


/**
 * A simple [Fragment] subclass.
 * Use the [ImageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */


class ImageFragment : Fragment() {

    private lateinit var wallpaper: ImageView
    private var number: Int = 0
    private lateinit var button: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            number = it.getInt(ARG_PARAM1)
        }
    }

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_image, container, false)
        button = view.findViewById(R.id.button)

        wallpaper = view.findViewById(R.id.wallpaper)
        getPicture(number, requireActivity()) { url ->
            Glide.with(requireContext())
                .load(url)
                .centerCrop()
                .into(wallpaper)
        }

        button.setOnClickListener {
            val wallpaperManager: WallpaperManager =
                WallpaperManager.getInstance(activity?.applicationContext)
            try {

               wallpaperManager.setBitmap(wallpaper.drawable.toBitmap())
            } catch (e: IOException){
                e.printStackTrace()
            }
        }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param number Parameter 1.
         * @return A new instance of fragment ImageFragment.
         */
        @JvmStatic
        fun newInstance(number: Int) =
            ImageFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, number)
                }
            }
    }
}