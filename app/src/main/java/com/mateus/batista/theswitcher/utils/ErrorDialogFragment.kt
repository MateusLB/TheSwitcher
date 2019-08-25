package com.mateus.batista.theswitcher.utils

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.mateus.batista.theswitcher.R
import com.mateus.batista.theswitcher.app.BundleExtra.DESCRIPTION_DIALOG
import com.mateus.batista.theswitcher.app.BundleExtra.TITLE_DIALOG
import com.mateus.batista.theswitcher.base.listeners.OnItemDialogErrorClickListener

class ErrorDialogFragment : DialogFragment() {

    var ok: LinearLayout? = null
    var cancel: LinearLayout? = null
    var title: TextView? = null
    var description: TextView? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = activity?.layoutInflater?.inflate(R.layout.dialog_error, null)

        ok = view?.findViewById(R.id.ok)
        cancel = view?.findViewById(R.id.cancel)
        title = view?.findViewById(R.id.title)
        description = view?.findViewById(R.id.description)

        val alertDialog = AlertDialog.Builder(activity)
        alertDialog.setView(view)

        title?.text = arguments?.getString(TITLE_DIALOG)
        description?.text = arguments?.getString(DESCRIPTION_DIALOG)

        ok?.setOnClickListener {
            if (targetFragment != null ) {
                (targetFragment as (OnItemDialogErrorClickListener))
                    .onItemDialogClick()
            }else{
                (activity as (OnItemDialogErrorClickListener))
                    .onItemDialogClick()
            }
            dismiss()
        }
        cancel?.setOnClickListener { dismiss() }
        return alertDialog.create()
    }
}