package uca.esi.manual.screens.login

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
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

/**
 * Login fragment
 *
 * @constructor Create empty Login fragment
 */
class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel

    private lateinit var binding: LoginFragmentBinding

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

        binding.buttonShowPass.setOnClickListener {
            changePasswordVisibility()
        }

        return binding.root
    }

    /**
     * Change password visibility
     *
     */
    private fun changePasswordVisibility() {
        viewModel.changePasswordMask()

        //Change the password text visibility
        binding.campoClave.inputType = when (viewModel.isPasswordMasked) {
            true -> InputType.TYPE_TEXT_VARIATION_PASSWORD or InputType.TYPE_CLASS_TEXT
            false -> InputType.TYPE_CLASS_TEXT
        }
        // Set the cursor back to where the user was typing
        binding.campoClave.setSelection(binding.campoClave.text.length)

        /**
         * Change the icon to show according to the setting:
         * The icon should show the state of the system
         * In this case, when the password is hidden, the eye is closed
         * When the password is visible, then the eye is open
         *
         * Reference: https://ux.stackexchange.com/questions/1318/should-a-toggle-button-show-its-current-state-or-the-state-to-which-it-will-chan
         */
        binding.buttonShowPass.setImageDrawable(
            AppCompatResources.getDrawable(
                requireContext(),
                when (viewModel.isPasswordMasked) {
                    true -> R.drawable.ic_icon_crossed_eye
                    false -> R.drawable.ic_icon_eye
                }
            )
        )
    }

    /**
     * Add event correct data observer
     *
     */
    private fun addEventCorrectDataObserver() {
        viewModel.eventCorrectData.observe(viewLifecycleOwner, { dataIsCorrect ->
            if (dataIsCorrect) {
                loginWithID()
                viewModel.onCorrectDataComplete()
            }
        })
    }

    /**
     * Add event wrong data observer
     *
     */
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

    /**
     * Add event empty data observer
     *
     */
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

    /**
     * Add database response observer
     *
     */
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


    /**
     * Login with i d
     *
     */
    private fun loginWithID() {
        NavHostFragment.findNavController(this).navigate(
            LoginFragmentDirections.actionLoginFragmentToLabSelectionFragment(
                viewModel.username.value.toString()
            )
        )
        viewModel.onCorrectDataComplete()
    }

    /**
     * Login anon
     *
     */
    private fun loginAnon() {
        NavHostFragment.findNavController(this).navigate(
            LoginFragmentDirections.actionLoginFragmentToLabSelectionFragment("u99999999")
        )
        viewModel.onCorrectDataComplete()
    }

    /**
     * Print
     *
     * @param response
     */
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