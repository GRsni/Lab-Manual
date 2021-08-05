package uca.esi.manual.screens.chest

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
import uca.esi.manual.BuildConfig
import uca.esi.manual.R
import uca.esi.manual.databinding.ChestFragmentBinding
import uca.esi.manual.models.labs.BaseLab
import uca.esi.manual.utils.printLab

class ChestFragment : Fragment() {

    private lateinit var viewModel: ChestViewModel

    private lateinit var viewModelFactory: ChestViewModelFactory

    private lateinit var binding: ChestFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.chest_fragment,
            container,
            false
        )

        viewModelFactory = ChestViewModelFactory(
            ChestFragmentArgs.fromBundle(requireArguments()).lab
        )

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(ChestViewModel::class.java)

        binding.chestViewModel = viewModel
        binding.lifecycleOwner = this

        binding.textoBaul.text = viewModel.materialsText.resolve(requireContext())
        binding.imagenBaul.setImageDrawable(
            AppCompatResources.getDrawable(
                requireContext(),
                viewModel.chestImage
            )
        )

        viewModel.eventButtonPressed.observe(viewLifecycleOwner, {
            if (it) {
                launchMaterialSelector()
                viewModel.onButtonPressedComplete()
            }
        })

        if (BuildConfig.DEBUG) {
            viewModel.lab.observe(viewLifecycleOwner, {
                if (it != null) {
                    printLab(it)
                }
            })
        }
        return binding.root
    }

    private fun launchMaterialSelector() {
        when (viewModel.lab.value!!.labType) {
            BaseLab.LabType.PANDEO -> {
                NavHostFragment.findNavController(this).navigate(
                    ChestFragmentDirections.actionChestFragmentToMaterialsPandeo(
                        viewModel.lab.value!!
                    )
                )
            }
            BaseLab.LabType.TORSION -> {
                NavHostFragment.findNavController(this).navigate(
                    ChestFragmentDirections.actionChestFragmentToMaterialsTorsionFragment(
                        viewModel.lab.value!!
                    )
                )
            }
            else -> Toast.makeText(
                activity,
                R.string.error_practica_no_implementada,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}