package uca.esi.manual.screens.ar

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import uca.esi.manual.R
import uca.esi.manual.activities.main.MainActivityViewModel
import uca.esi.manual.ar.UnityPlayerActivity
import uca.esi.manual.databinding.ArLauncherFragmentBinding
import uca.esi.manual.models.labs.BaseLab
import uca.esi.manual.utils.ViewModelString
import uca.esi.manual.utils.printLabIfDebug

/**
 * A r launcher fragment
 *
 * @constructor Create empty A r launcher fragment
 */
class ARLauncherFragment : Fragment() {

    private lateinit var viewModel: ARLauncherViewModel
    private lateinit var viewModelFactory: ARLauncherViewModelFactory

    private lateinit var binding: ArLauncherFragmentBinding

    private lateinit var activityViewModel: MainActivityViewModel

    /**
     * On create view
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

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

        printLabIfDebug(viewModel.lab)

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

        binding.buttonExit.text = getBackButtonText().resolve(requireContext())

        binding.buttonExit.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(
                ARLauncherFragmentDirections.actionARLauncherFragmentToCalculationsDataFragment(
                    viewModel.lab
                )
            )
        }

        addEventLaunchObserver()

        return binding.root
    }


    /**
     * Get back button text
     *
     * @return
     */
    private fun getBackButtonText(): ViewModelString {
        return if (activityViewModel.arExecuted) {
            ViewModelString(R.string.boton_continuar)
        } else {
            ViewModelString(R.string.boton_saltar)
        }
    }

    /**
     * Get full text
     *
     * @param machineText
     * @param positionText
     * @return
     */
    private fun getFullText(machineText: ViewModelString, positionText: ViewModelString): String {
        return machineText.resolve(requireContext()) + " " +
                positionText.resolve(requireContext())
    }


    /**
     * Add event launch observer
     *
     */
    private fun addEventLaunchObserver() {
        viewModel.eventLaunchAR.observe(viewLifecycleOwner, { launch ->
            if (launch) {
                activityViewModel.arExecuted = true
                viewModel.onLaunchARComplete()
                binding.buttonExit.text = getBackButtonText().resolve(requireContext())
                launchARActivity(viewModel.lab)
            }
        })
    }

    /**
     * Launch a r activity
     *
     * @param lab
     */
    private fun launchARActivity(lab: BaseLab) {
        val i = Intent(activity, UnityPlayerActivity::class.java)
        i.putExtra("data", lab.data)
        i.putExtra("type", BaseLab.getIntFromType(lab.labType))
        i.putExtra("place", lab.isInLab)
        startActivity(i)
    }
}