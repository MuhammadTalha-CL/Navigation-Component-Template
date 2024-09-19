package com.example.navigationcomponentdemo.ui.frgaments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.navigationcomponentdemo.databinding.FragmentCBinding

class FragmentC : Fragment() {

    private var binding: FragmentCBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentCBinding.inflate(layoutInflater, container, false)
        .apply {
            binding = this
        }.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var textReceive = arguments?.getString("fromFragmentB")
        if (textReceive == null) {
            textReceive = arguments?.getString("fromFragmentA")
            binding?.btnMoveToFragBFromC?.visibility = View.INVISIBLE
        } else
            binding?.btnBackToFragAFromC?.visibility = View.INVISIBLE

        binding?.welcomeTxt?.text = textReceive
        binding?.btnMoveToFragBFromC?.setOnClickListener {
            findNavController().popBackStack()
        }
        binding?.btnBackToFragAFromC?.setOnClickListener {
            findNavController().popBackStack()

        }
    }

}