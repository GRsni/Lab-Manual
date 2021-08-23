package uca.esi.manual.screens.selection

import android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import timber.log.Timber
import uca.esi.manual.R
import uca.esi.manual.databinding.LabSelectionFragmentBinding
import uca.esi.manual.models.Constants

/**
 * Lab selection fragment
 *
 * @constructor Create empty Lab selection fragment
 */
class LabSelectionFragment : Fragment() {

    private lateinit var viewModel: LabSelectionViewModel
    private lateinit var viewModelFactory: LabSelectionViewModelFactory

    private lateinit var binding: LabSelectionFragmentBinding

    /**
     * On create view
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.lab_selection_fragment,
            container,
            false
        )

        viewModelFactory = LabSelectionViewModelFactory(
            LabSelectionFragmentArgs.fromBundle(requireArguments()).userId
        )

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(LabSelectionViewModel::class.java)

        binding.labSelectionViewModel = viewModel
        binding.lifecycleOwner = this

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            binding.explicacion.justificationMode = JUSTIFICATION_MODE_INTER_WORD
        }

        addEventEmptyDataObserver()

        addTorsionPressObserver()

        addPandeoPressObserver()

        addTraccionPressObserver()

        addFlexionPressObserver()

        return binding.root
    }

    /**
     * Add event empty data observer
     *
     */
    private fun addEventEmptyDataObserver() {
        viewModel.eventEmptyData.observe(viewLifecycleOwner, { dataIsEmpty ->
            if (dataIsEmpty) {
                Toast.makeText(
                    activity,
                    R.string.error_lugar_practica_vacio,
                    Toast.LENGTH_SHORT
                ).show()
                viewModel.onEmptyDataComplete()
            }
        })
    }

    /**
     * Add torsion press observer
     *
     */
    private fun addTorsionPressObserver() {
        viewModel.eventTorsionPress.observe(viewLifecycleOwner, { torsionPressed ->
            if (torsionPressed) {
                launchLab(Constants.TORSION_LAB_ID)
                viewModel.onTorsionClickComplete()
            }
        })
    }

    /**
     * Add pandeo press observer
     *
     */
    private fun addPandeoPressObserver() {
        viewModel.eventPandeoPress.observe(viewLifecycleOwner, { pandeoPressed ->
            if (pandeoPressed) {
                launchLab(Constants.PANDEO_LAB_ID)
                viewModel.onPandeoClickComplete()
            }
        })
    }

    /**
     * Add traccion press observer
     *
     */
    private fun addTraccionPressObserver() {
        viewModel.eventTraccionPress.observe(viewLifecycleOwner, { traccionPressed ->
            if (traccionPressed) {
                //Traccion lab not implemented
                //launchLab(Constants.TRACCION_LAB_ID)
                Toast.makeText(context, R.string.error_practica_no_implementada, Toast.LENGTH_SHORT)
                    .show()
                viewModel.onTraccionClickComplete()
            }
        })
    }

    /**
     * Add flexion press observer
     *
     */
    private fun addFlexionPressObserver() {
        viewModel.eventFlexionPress.observe(viewLifecycleOwner, { flexionPressed ->
            if (flexionPressed) {
                //Flexion lab not implemented
                //launchLab(Constants.FLEXION_LAB_ID)
                Toast.makeText(context, R.string.error_practica_no_implementada, Toast.LENGTH_SHORT)
                    .show()
                viewModel.onFlexionClickComplete()
            }
        })
    }

    /**
     * Launch lab
     *
     * @param labType
     */
    private fun launchLab(labType: Int) {
        if (viewModel.checkPlaceSelectedNotEmpty()) {
            Timber.i("Inlab: ${viewModel.inLab.value}")
            NavHostFragment.findNavController(this).navigate(
                LabSelectionFragmentDirections.actionLabSelectionFragmentToLabExplanationFragment(
                    viewModel.userId, viewModel.inLab.value!!, labType
                )
            )
        }
    }
}