package cs335.inclass02

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import java.util.*

class crimeDialogueFragment: DialogFragment() {

    interface Callbacks{
        fun onDateSelected(date:Date)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
       /* val newPopup = AlertDialog.Builder(activity)

        var x = LayoutInflater.from(context).inflate(R.layout.fragment_crime, null)

        newPopup.setView(x)

        newPopup.setTitle("AnotherTest")
        newPopup.setMessage("This is a test")
        //newPopup.setCancelable(true)
        newPopup.setPositiveButton("Okay", null)
        newPopup.setNegativeButton("Cancel", null)

        val alert = newPopup.create()
        return alert*/

        val stuffIGot = arguments?.getSerializable("startDate") as Date

        val cal = Calendar.getInstance()
        cal.time = stuffIGot
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)

        val listener = DatePickerDialog.OnDateSetListener{ view, year, month, dayOfMonth ->
            val asOneDate: Date = GregorianCalendar(year, month, dayOfMonth).time

            targetFragment?.let{
                (it as Callbacks).onDateSelected(asOneDate)
            }
        }

        return DatePickerDialog(requireContext(), listener, year,month,day)
    }

    companion object{
        fun makeDialog(someDate: Date):crimeDialogueFragment{

            val stuffIWant = Bundle()
            stuffIWant.putSerializable("startDate", someDate)

            return crimeDialogueFragment().apply{
                arguments = stuffIWant
            }
        }
    }
}