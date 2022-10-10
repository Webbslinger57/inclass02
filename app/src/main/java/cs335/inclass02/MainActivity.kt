package cs335.inclass02

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var z = supportFragmentManager.findFragmentById(R.id.fragment_shell)

        if(z == null){
            val something = CrimeFragment()
            supportFragmentManager.beginTransaction().add(R.id.fragment_shell, something).commit()
            
        }
    }
}