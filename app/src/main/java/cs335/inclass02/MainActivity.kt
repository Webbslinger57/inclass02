package cs335.inclass02

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity(), CrimeListFragment.Callbacks{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var z = supportFragmentManager.findFragmentById(R.id.fragment_shell)

        if(z == null){
            val something = CrimeListFragment()
            supportFragmentManager.beginTransaction().add(R.id.fragment_shell, something).commit()

        }
    }

    override fun onCrimeSelected(selectedID: UUID) {
        val newThing = CrimeFragment.newInstance(selectedID)
        val manager = supportFragmentManager
        manager.beginTransaction().replace(R.id.fragment_shell, newThing).addToBackStack(null).commit()
    }
}