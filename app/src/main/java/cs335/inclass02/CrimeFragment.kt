package cs335.inclass02

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.fragment.app.Fragment
import java.util.*

class CrimeFragment : Fragment(), crimeDialogueFragment.Callbacks  {

    private lateinit var myCrime: Crime
    private lateinit var myButton: Button
    private lateinit var myCheckBox: CheckBox
    private lateinit var myEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myCrime = Crime()
        var x = arguments?.getSerializable("whatever") as UUID
        myCrime = CrimeDB.instance.getCrime(x)?:Crime()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_crime, container, false)

        myButton = v.findViewById(R.id.crime_date) as Button
        myCheckBox = v.findViewById(R.id.crime_solved) as CheckBox
        myEditText = v.findViewById(R.id.crime_title) as EditText

        // declare watcher var

        //myButton.setText(myCrime.date.toString())
        //myButton.isEnabled = false
        myButton.apply {
            text = myCrime.date.toString()
            isEnabled = true
        }

        myButton.setOnClickListener{
            val x = crimeDialogueFragment.makeDialog(myCrime.date)
            x.setTargetFragment(this@CrimeFragment, 1)
            x.show(this@CrimeFragment.requireFragmentManager(), "test")
        }

        myCheckBox.apply{
            isChecked = myCrime.isSolved
            setOnCheckedChangeListener{_ , isChecked->
                myCrime.isSolved = isChecked

                Log.d("Webb", myCrime.toString())
            }
        }
        myEditText.apply{
            //addTextChangedListener(titleWatcher) // <-
        }
        return v
    }

    override fun onStart(){
        super.onStart()

        val titleWatcher = object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                TODO("Not yet implemented")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                myCrime.title = p0.toString()
                Log.d("Webb", myCrime.toString())
            }

            override fun afterTextChanged(p0: Editable?) {
                TODO("Not yet implemented")
            }

        }
    }

    companion object{

        fun newInstance(crimeID: UUID):CrimeFragment{
            var f = CrimeFragment()
            var dataToSend = Bundle()
            dataToSend.putSerializable("whatever", crimeID)

            f.arguments = dataToSend

            return f
        }

    }

    override fun onDateSelected(date:Date){
        myCrime.date = date
        myButton.setText(date.toString())
    }
}