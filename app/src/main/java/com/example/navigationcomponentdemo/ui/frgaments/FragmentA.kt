package com.example.navigationcomponentdemo.ui.frgaments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.navigationcomponentdemo.R
import com.example.navigationcomponentdemo.databinding.FragmentABinding
import com.example.navigationcomponentdemo.utils.setFadeAnimation
import com.example.navigationcomponentdemo.utils.setLeftSlideAnimation
import com.example.navigationcomponentdemo.utils.setRightSlideAnimation


class FragmentA : Fragment() {

    private var binding: FragmentABinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentABinding.inflate(layoutInflater, container, false)
        .apply {
            binding = this
        }.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rightAnimation = NavOptions.Builder()
            .setRightSlideAnimation()
            .build()
        val fadeAnimation = NavOptions.Builder()
            .setFadeAnimation()
            .build()

        val bundle = Bundle()
        binding?.btnMoveToFragB?.setOnClickListener {

            //this code is for  passing any object or relevant information to next fragment
            //Just like putStringExtra
            bundle.putString("fromFragmentA" , "Welcome to Fragment B")

            //here bundle is holding all the information which is being passed
            //rightAnimation param is for showing transaction with a animation
            findNavController().navigate(R.id.action_fragmentA_to_fragmentB,bundle,rightAnimation)

        }
        binding?.btnMoveToFragC?.setOnClickListener {
            bundle.putString("fromFragmentA" , "Welcome to Fragment C")
            findNavController().navigate(R.id.action_fragmentA_to_fragmentC,bundle,fadeAnimation)
        }
    }

    }
