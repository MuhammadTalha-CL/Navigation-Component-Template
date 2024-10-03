package com.example.navigationcomponentdemo.ui.frgaments.creditcard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.navigationcomponentdemo.databinding.FragmentCBinding
import com.example.navigationcomponentdemo.apisetup.BGStateHolder
import com.example.navigationcomponentdemo.utils.isNetworkAvailable
import kotlinx.coroutines.launch

class CreditCardFragment : Fragment() {

    private var binding: FragmentCBinding? = null
    private val creditCardViewModel: CreditCardViewModel by viewModels()
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


        if (isNetworkAvailable(requireContext()))
            apirequests()
        else
            Toast.makeText(
                requireContext(), "No Internet Connection Available", Toast.LENGTH_SHORT
            ).show()


        collectors()
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

    private fun apirequests() {
        creditCardViewModel.getCreditCardData()
    }

    private fun collectors() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                launch {
                    creditCardViewModel.creditCardData.collect {
                        it.apply {
                            when (this) {

                                is BGStateHolder.Loading -> {
                                }

                                is BGStateHolder.Success -> {
                                    it.data?.let { data ->
                                        if (data.id != null) {
                                            Log.d(
                                                "OnResponse",
                                                "collectors: ${data.creditCardNumber}"
                                            )
                                        }
                                    }
                                }

                                is BGStateHolder.Error -> {
                                    Toast.makeText(
                                        requireContext(), this.message, Toast.LENGTH_SHORT
                                    ).show()
                                    Log.d("OnResponse", "collectors Error: ${this.message}")

                                }

                                else -> {}
                            }
                        }
                    }
                }
            }
        }
    }
}