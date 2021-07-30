package uca.esi.manual.screens.ar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import uca.esi.manual.R
import uca.esi.manual.activities.main.MainActivityViewModel
import uca.esi.manual.databinding.ArLauncherFragmentBinding
import uca.esi.manual.utils.ViewModelString

class ARLauncherFragment : Fragment() {

    private lateinit var viewModel: ARLauncherViewModel
    private lateinit var viewModelFactory: ARLauncherViewModelFactory

    private lateinit var binding: ArLauncherFragmentBinding

    private lateinit var activityViewModel: MainActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        activityViewModel =
            ViewModelProvider(requireActivity()).get(MainActivityViewModel::class.java)

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

        binding.buttonNext.text = getBackButtonText().resolve(requireContext())

        binding.buttonNext.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(
                ARLauncherFragmentDirections.actionARLauncherFragmentToCalculationsDataFragment(
                    viewModel.lab.value!!
                )
            )
        }

        addEventLaunchObserver()

        return binding.root
    }


    private fun getBackButtonText(): ViewModelString {
        return if (activityViewModel.arModule.executed) {
            ViewModelString(R.string.boton_terminado)
        } else {
            ViewModelString(R.string.boton_saltar)
        }
    }

    private fun getFullText(machineText: ViewModelString, positionText: ViewModelString): String {
        return machineText.resolve(requireContext()) + " " +
                positionText.resolve(requireContext())
    }


    private fun addEventLaunchObserver() {
        viewModel.eventLaunchAR.observe(viewLifecycleOwner, { launch ->
            if (launch) {
                Toast.makeText(
                    activity,
                    "Lanzando RA", Toast.LENGTH_SHORT
                ).show()
                activityViewModel.arModule.executed = true
                binding.buttonAR.text = getBackButtonText().resolve(requireContext())
            }
        })
    }

}