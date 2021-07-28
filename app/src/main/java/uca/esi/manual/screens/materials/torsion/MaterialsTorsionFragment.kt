package uca.esi.manual.screens.materials.torsion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import uca.esi.manual.R
import uca.esi.manual.databinding.MaterialsTorsionFragmentBinding
import uca.esi.manual.utils.printLabIfDebug

class MaterialsTorsionFragment : Fragment() {

    private lateinit var viewModel: MaterialsTorsionViewModel
    private lateinit var viewModelFactory: MaterialsTorsionViewModelFactory

    private lateinit var binding: MaterialsTorsionFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.materials_torsion_fragment,
            container,
            false
        )

        viewModelFactory = MaterialsTorsionViewModelFactory(
            MaterialsTorsionFragmentArgs.fromBundle(requireArguments()).lab
        )

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(MaterialsTorsionViewModel::class.java)

        addEventCorrectDataObserver()
        addEventEmptyDataObserver()
        addEventWrongDataObserver()

        binding.materialsTorsionViewModel = viewModel
        binding.lifecycleOwner = this

        binding.buttonAnt.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(
                MaterialsTorsionFragmentDirections.actionMaterialsTorsionFragmentToChestFragment(
                    viewModel.lab.value!!
                )
            )
        }

        addCheckBoxListeners()

        viewModel.lab.observe(viewLifecycleOwner, {
            printLabIfDebug(it)
        })


        return binding.root
    }

    private fun addCheckBoxListeners() {
        binding.checkDosGanchos.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onCheckBoxClicked(0, isChecked)
        }
        binding.checkBCircular.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onCheckBoxClicked(1, isChecked)
        }
        binding.checkBRectangular.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onCheckBoxClicked(2, isChecked)
        }
        binding.checkPeGrandes.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onCheckBoxClicked(3, isChecked)
        }
        binding.checkPePeq.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onCheckBoxClicked(4, isChecked)
        }
        binding.checkLlaves.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onCheckBoxClicked(5, isChecked)
        }
        binding.checkAmpli.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onCheckBoxClicked(6, isChecked)
        }
        binding.checkCable.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onCheckBoxClicked(7, isChecked)
        }
    }

    private fun addEventCorrectDataObserver() {
        viewModel.eventCorrectData.observe(viewLifecycleOwner, {
            if (it) {
                Toast.makeText(
                    activity,
                    "Datos correctos, ",
                    Toast.LENGTH_SHORT
                ).show()
                viewModel.onCorrectDataComplete()
            }
        })
    }

    private fun addEventWrongDataObserver() {
        viewModel.eventWrongData.observe(viewLifecycleOwner, { dataIsWrong ->
            if (dataIsWrong) {
                Toast.makeText(
                    activity,
                    R.string.error_materiales_torsion,
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
                    R.string.error_materiales_vacios,
                    Toast.LENGTH_SHORT
                ).show()
                viewModel.onEmptyDataComplete()
            }
        })
    }

}