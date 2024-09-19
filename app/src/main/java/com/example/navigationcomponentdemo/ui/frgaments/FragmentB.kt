package com.example.navigationcomponentdemo.ui.frgaments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.navigationcomponentdemo.R
import com.example.navigationcomponentdemo.databinding.FragmentBBinding
import com.example.navigationcomponentdemo.utils.setLeftSlideAnimation

class FragmentB : Fragment() {
    private var binding: FragmentBBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentBBinding.inflate(layoutInflater, container, false)
        .apply {
            binding = this
        }.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val leftAnimation = NavOptions.Builder()
            .setLeftSlideAnimation()
            .build()
        val bundle = Bundle()
        //this is for receiving the information passed from source Fragment
        val textReceive = arguments?.getString("fromFragmentA")
        binding?.welcomeTxt?.text = textReceive
        binding?.btnBackToFragA?.setOnClickListener {
            //For moving back to the source fragment
            findNavController().popBackStack()
        }
        binding?.btnMoveToFragCFromB?.setOnClickListener {
            bundle.putString("fromFragmentB", "Welcome to Fragment C")
            findNavController().navigate(R.id.action_fragmentB_to_fragmentC, bundle, leftAnimation)
        }

    }

}