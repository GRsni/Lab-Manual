package uca.esi.manual.screens.materials.pandeo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import timber.log.Timber
import uca.esi.manual.BuildConfig
import uca.esi.manual.R
import uca.esi.manual.databinding.MaterialsPandeoFragmentBinding
import uca.esi.manual.utils.printLab


class MaterialsPandeoFragment : Fragment() {

    private lateinit var viewModel: MaterialsPandeoViewModel
    private lateinit var viewModelFactory: MaterialsPandeoViewModelFactory

    private lateinit var binding: MaterialsPandeoFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.materials_pandeo_fragment,
            container,
            false
        )

        viewModelFactory = MaterialsPandeoViewModelFactory(
            MaterialsPandeoFragmentArgs.fromBundle(requireArguments()).lab
        )

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(MaterialsPandeoViewModel::class.java)

        addEventCorrectDataObserver()
        addEventEmptyDataObserver()
        addEventWrongBarDataObserver()
        addEventWrongJointDataObserver()

        binding.materialsPandeoViewModel = viewModel
        binding.lifecycleOwner = this

        binding.campoApoyoArticulado.addTextChangedListener {
            if (it != null) {
                viewModel.setLooseJoints(it)
            }
        }

        binding.campoApoyoEmpotrado.addTextChangedListener {
            if (it != null) {
                viewModel.setFixedJoints(it)
            }
        }

        binding.buttonAnt.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(
                MaterialsPandeoFragmentDirections.actionMaterialsPandeoFragmentToChestFragment(
                    viewModel.lab.value!!
                )
            )
        }
        if (BuildConfig.DEBUG) {
            viewModel.lab.observe(viewLifecycleOwner, {
                if (it != null) {
                    printLab(it)
                }
            })
        }

        return binding.root
    }

    private fun addEventCorrectDataObserver() {
        viewModel.eventCorrectData.observe(viewLifecycleOwner, {
            if (it) {
                Toast.makeText(
                    activity,
                    R.string.datos_correctos,
                    Toast.LENGTH_SHORT
                ).show()
                NavHostFragment.findNavController(this).navigate(
                    MaterialsPandeoFragmentDirections.actionMaterialsPandeoFragmentToARLauncherFragment(
                        viewModel.lab.value!!
                    )
                )
                viewModel.onCorrectDataComplete()
            }
        })
    }

    private fun addEventWrongBarDataObserver() {
        viewModel.eventWrongBarData.observe(viewLifecycleOwner, { dataIsWrong ->
            if (dataIsWrong) {
                Toast.makeText(
                    activity,
                    R.string.error_barra_pandeo,
                    Toast.LENGTH_SHORT
                ).show()
                viewModel.onWrongDataBarComplete()
            }
        })
    }

    private fun addEventWrongJointDataObserver() {
        viewModel.eventWrongJointData.observe(viewLifecycleOwner, { dataIsWrong ->
            if (dataIsWrong) {
                Toast.makeText(
                    activity,
                    R.string.error_apoyos_pandeo,
                    Toast.LENGTH_SHORT
                ).show()
                viewModel.onWrongDataJointComplete()
            }
        })
    }

    private fun addEventEmptyDataObserver() {
        viewModel.eventEmptyData.observe(viewLifecycleOwner, { dataIsEmpty ->
            if (dataIsEmpty) {
                Toast.makeText(
                    activity,
                    R.string.error_materiales_vacios,
                    Toast.LENGTH_SHORT
                ).show()
                viewModel.onEmptyDataComplete()
            }
        })
    }

}