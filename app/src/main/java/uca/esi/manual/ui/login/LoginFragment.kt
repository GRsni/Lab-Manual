package uca.esi.manual.ui.login

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
import timber.log.Timber
import uca.esi.manual.BuildConfig
import uca.esi.manual.R
import uca.esi.manual.databinding.LoginFragmentBinding
import uca.esi.manual.models.UserListResponse

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel

    private lateinit var binding: LoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.login_fragment, container, false
        )

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        addDatabaseResponseObserver()

        addEventEmptyDataObserver()

        addEventWrongDataObserver()

        addEventCorrectDataObserver()

        binding.loginViewModel = viewModel
        binding.lifecycleOwner = this

        binding.buttonLoginAnon.setOnClickListener {
            loginAnon()
        }

        binding.campoIdentificacion.addTextChangedListener {
            if (it != null) {
                viewModel.setUsername(it)
            }
        }

        binding.campoClave.addTextChangedListener {
            if (it != null) {
                viewModel.setPassword(it)
            }
        }

        return binding.root
    }

    private fun addEventCorrectDataObserver() {
        viewModel.eventCorrectData.observe(viewLifecycleOwner, { dataIsCorrect ->
            if (dataIsCorrect) {
                loginWithID()
                viewModel.onCorrectDataComplete()
            }
        })
    }

    private fun addEventWrongDataObserver() {
        viewModel.eventWrongData.observe(viewLifecycleOwner, { dataIsWrong ->
            if (dataIsWrong) {
                Toast.makeText(
                    activity,
                    R.string.error_datos_alumno_vacios,
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
                    R.string.error_datos_alumno_vacios,
                    Toast.LENGTH_SHORT
                ).show()
                viewModel.onEmptyDataComplete()
            }
        })
    }

    private fun addDatabaseResponseObserver() {
        viewModel.response.observe(viewLifecycleOwner, {

            if (it.exception != null) {
                Toast.makeText(
                    activity,
                    R.string.error_lectura_firebase,
                    Toast.LENGTH_SHORT
                ).show()
                Timber.w("Error reading from database: %s", it.exception!!.message)
            } else {
                if (BuildConfig.DEBUG) {
                    print(it)
                }
            }
        })
    }


    private fun loginWithID() {
        NavHostFragment.findNavController(this).navigate(
            LoginFragmentDirections.actionLoginFragmentToLabSelectionFragment(
                viewModel.username.value.toString()
            )
        )
    }

    private fun loginAnon() {
        NavHostFragment.findNavController(this).navigate(
            LoginFragmentDirections.actionLoginFragmentToLabSelectionFragment("u99999999")
        )
    }

    private fun print(response: UserListResponse) {
        response.users?.let { users ->
            users.forEach { (user, key) ->
                Timber.i("%s: %s", user, key)
            }
        }
        response.exception?.let {
            Timber.i(it)
        }
    }

}