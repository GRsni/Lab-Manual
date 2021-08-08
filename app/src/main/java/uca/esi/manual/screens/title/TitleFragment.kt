package uca.esi.manual.screens.title

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import timber.log.Timber
import uca.esi.manual.R
import uca.esi.manual.databinding.TitleFragmentBinding
import uca.esi.manual.utils.isDarkThemeOn


class TitleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: TitleFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.title_fragment, container, false
        )

        binding.buttonStart.setOnClickListener {
            findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToLoginFragment())
        }

        binding.esiLogo.setImageDrawable(
            AppCompatResources.getDrawable(
                requireContext(),
                when (requireContext().isDarkThemeOn()) {
                    true -> R.drawable.esi_logo_white
                    false -> R.drawable.logo_esi_new
                }
            )
        )

        binding.buttonAbout.setOnClickListener {
            findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToAboutFragment())
        }
        return binding.root
    }
}