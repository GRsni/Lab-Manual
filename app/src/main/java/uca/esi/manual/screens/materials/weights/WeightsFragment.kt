package uca.esi.manual.screens.materials.weights

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
import uca.esi.manual.databinding.WeightsFragmentBinding
import uca.esi.manual.models.labs.TorsionLab

class WeightsFragment : Fragment() {

    private lateinit var viewModel: WeightsViewModel

    private lateinit var viewModelFactory: WeightsViewModelFactory

    private lateinit var binding: WeightsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.weights_fragment,
            container,
            false
        )

        viewModelFactory = WeightsViewModelFactory(
            WeightsFragmentArgs.fromBundle(requireArguments()).lab
        )

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(WeightsViewModel::class.java)

        addEventCorrectDataObserver()

        addEventEmptyDataObserver()

        addEventWrongDataObserver()

        binding.weightsViewModel = viewModel
        binding.lifecycleOwner = this

        binding.campoSoporte.addTextChangedListener {
            if (it != null) {
                viewModel.setSupport(it)
            }
        }

        binding.campoPesa5.addTextChangedListener {
            if (it != null) {
                viewModel.setLoad5N(it)
            }
        }

        binding.campoPesa10.addTextChangedListener {
            if (it != null) {
                viewModel.setLoad10N(it)
            }
        }

        binding.campoPesa20.addTextChangedListener {
            if (it != null) {
                viewModel.setLoad20N(it)
            }
        }

        binding.buttonBack.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(
                WeightsFragmentDirections.actionWeightsFragmentToMaterialsTorsionFragment(
                    viewModel.lab.value!!
                )
            )
        }

        binding.textoCargaRandom.text = setLoadText()

        return binding.root
    }

    private fun setLoadText(): String {
        val baseString = viewModel.loadText.resolve(requireContext())
        return String.format(baseString, (viewModel.lab.value!! as TorsionLab).weights)
    }

    private fun addEventCorrectDataObserver() {
        viewModel.eventCorrectData.observe(viewLifecycleOwner, { dataIsCorrect ->
            if (dataIsCorrect) {
                NavHostFragment.findNavController(this).navigate(
                    WeightsFragmentDirections.actionWeightsFragmentToARLauncherFragment(
                        viewModel.lab.value!!
                    )
                )
                viewModel.onCorrectDataComplete()
            }
        })
    }

    private fun addEventWrongDataObserver() {
        viewModel.eventWrongData.observe(viewLifecycleOwner, { dataIsWrong ->
            if (dataIsWrong) {
                Toast.makeText(
                    activity,
                    R.string.error_pesas_torsion,
                    Toast.LENGTH_SHORT
                ).show()
                viewModel.onWrongDataComplete()
            }
        })
    }

    private fun addEventEmptyDataObserver() {
        viewModel.eventEmptyData.observe(viewLifecycleOwner, { dataIsEmpty ->
            if (dataIsEmpty) {
                Toast.makeText(
                    activity,
                    R.string.error_soporte_pesas_vacio,
                    Toast.LENGTH_SHORT
                ).show()
                viewModel.onEmptyDataComplete()
            }
        })
    }
}