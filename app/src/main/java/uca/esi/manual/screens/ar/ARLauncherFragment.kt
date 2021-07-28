package uca.esi.manual.screens.ar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import uca.esi.manual.R
import uca.esi.manual.databinding.ArLauncherFragmentBinding
import uca.esi.manual.utils.ViewModelString

class ARLauncherFragment : Fragment() {

    private lateinit var viewModel: ARLauncherViewModel
    private lateinit var viewModelFactory: ARLauncherViewModelFactory

    private lateinit var binding: ArLauncherFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.ar_launcher_fragment,
            container,
            false
        )

        viewModelFactory = ARLauncherViewModelFactory(
            ARLauncherFragmentArgs.fromBundle(requireArguments()).lab
        )
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(ARLauncherViewModel::class.java)

        binding.arLauncherViewModel = viewModel
        binding.lifecycleOwner = this

        binding.textoPreparaMaquina.text =
            getFullText(viewModel.textMachine, viewModel.textPosition)

        binding.imagenPosicionQR.setImageDrawable(
            AppCompatResources.getDrawable(
                requireContext(),
                viewModel.qrImageId
            )
        )

        return binding.root
    }

    private fun getFullText(machineText: ViewModelString, positionText: ViewModelString): String {
        return machineText.resolve(requireContext()) + " " +
                positionText.resolve(requireContext())
    }


}