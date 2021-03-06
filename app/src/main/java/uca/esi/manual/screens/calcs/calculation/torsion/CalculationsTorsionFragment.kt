package uca.esi.manual.screens.calcs.calculation.torsion

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import uca.esi.manual.R
import uca.esi.manual.databinding.CalculationsTorsionFragmentBinding
import uca.esi.manual.screens.calcs.data.CalculationsDataFragmentArgs
import uca.esi.manual.utils.showErrorDialog

/**
 * Calculations torsion fragment
 *
 * @constructor Create empty Calculations torsion fragment
 */
class CalculationsTorsionFragment : Fragment() {

    private lateinit var viewModel: CalculationsTorsionViewModel

    private lateinit var viewModelFactory: CalculationsTorsionViewModelFactory

    private lateinit var binding: CalculationsTorsionFragmentBinding

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
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.calculations_torsion_fragment,
            container,
            false
        )

        viewModelFactory = CalculationsTorsionViewModelFactory(
            CalculationsDataFragmentArgs.fromBundle(requireArguments()).lab
        )

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(CalculationsTorsionViewModel::class.java)


        binding.calculationsTorsionViewModel = viewModel
        binding.lifecycleOwner = this

        addEventCorrectDataObserver()

        addEventEmptyDataObserver()

        addEventWrongAmpliDataObserver()

        addEventWrongMomentDataObserver()

        addEventAutocompleteDataObserver()

        binding.campoMomento.addTextChangedListener {
            if (it != null) {
                viewModel.setMomentValue(it)
            }
        }

        binding.campoAmplificador.addTextChangedListener {
            if (it != null) {
                viewModel.setAmpliValue(it)
            }
        }

        binding.buttonBack.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(
                CalculationsTorsionFragmentDirections.actionCalculationsTorsionFragmentBackwards(
                    viewModel.lab
                )
            )
        }

        return binding.root
    }

    /**
     * Add event correct data observer
     *
     */
    private fun addEventCorrectDataObserver() {
        viewModel.eventCorrectData.observe(viewLifecycleOwner, { dataIsCorrect ->
            if (dataIsCorrect) {
                NavHostFragment.findNavController(this).navigate(
                    CalculationsTorsionFragmentDirections.actionCalculationsTorsionFragmentToQuestionsFragment(
                        viewModel.lab
                    )
                )
                viewModel.onCorrectDataComplete()
            }
        })
    }

    /**
     * Add event wrong ampli data observer
     *
     */
    private fun addEventWrongAmpliDataObserver() {
        viewModel.eventWrongAmpliData.observe(viewLifecycleOwner, { dataIsWrong ->
            if (dataIsWrong) {
                showErrorDialog(
                    requireContext(),
                    R.string.titulo_mensaje_error_ampli_torsion,
                    R.string.cuerpo_mensaje_error_ampli_torsion
                )
                viewModel.onWrongAmpliDataComplete()
            }
        })
    }

    /**
     * Add event wrong moment data observer
     *
     */
    private fun addEventWrongMomentDataObserver() {
        viewModel.eventWrongMomentData.observe(viewLifecycleOwner, { dataIsWrong ->
            if (dataIsWrong) {
                showErrorDialog(
                    requireContext(),
                    R.string.titulo_mensaje_error_momento_torsion,
                    R.string.cuerpo_mensaje_error_momento_torsion
                )
                viewModel.onWrongMomentDataComplete()
            }
        })
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
                    R.string.error_valores_vacios,
                    Toast.LENGTH_SHORT
                ).show()
                viewModel.onEmptyDataComplete()
            }
        })
    }

    /**
     * Add event autocomplete data observer
     *
     */
    private fun addEventAutocompleteDataObserver() {
        viewModel.eventAutocompleteData.observe(viewLifecycleOwner, { dataAutocompleted ->
            if (dataAutocompleted) {
                AlertDialog.Builder(activity)
                    .setTitle(R.string.datos_experimento)
                    .setMessage(R.string.datos_automaticos)
                    .setPositiveButton(
                        R.string.boton_aceptar
                    ) { _, _ -> viewModel.onAutocompleteDataComplete() }
                    .show()
            }
        })
    }

}