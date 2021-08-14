package uca.esi.manual.screens.title

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.content.Intent.ACTION_SEND
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import uca.esi.manual.R
import uca.esi.manual.databinding.AboutFragmentBinding

class AboutFragment : DialogFragment() {

    private val recipient: String = "manual.laboratorio.rme.uca@gmail.com"

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val binding = AboutFragmentBinding.inflate(LayoutInflater.from(context))
        return AlertDialog.Builder(requireActivity())
            .setView(binding.root)
            .setMessage(R.string.text_acerca_de)
            .setPositiveButton(
                R.string.boton_enviar_email
            ) { _, _ ->
                sendEmailIntent()
            }
            .setNeutralButton(R.string.boton_salir) { _, _ -> //User cancelled the dialog
            }
            .create()
    }

    private fun sendEmailIntent() {
        val mIntent = Intent(ACTION_SEND)
        mIntent.data = Uri.parse("mailto:")
        mIntent.type = "text/plain"
        mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
        startActivity(Intent.createChooser(mIntent, "Selecciona el cliente de email..."))
    }
}