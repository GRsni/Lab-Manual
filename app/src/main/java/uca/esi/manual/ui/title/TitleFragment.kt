package uca.esi.manual.ui.title

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uca.esi.manual.R
import uca.esi.manual.databinding.TitleFragmentBinding


class TitleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: TitleFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.title_fragment, container, false
        )

        binding.buttonStart.setOnClickListener {
            findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToLoginFragment())
        }

        binding.buttonAbout.setOnClickListener {
            findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToAboutFragment())
        }
        return binding.root
    }
}