package cs335.inclass02

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CrimeListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CrimeListFragment : Fragment() {

    interface Callbacks{
        fun onCrimeSelected(selectedID: UUID)

    }

    private var connectionToActivity: Callbacks? = null

    private lateinit var myRecyclerView: RecyclerView

    private var myAdapter: CrimeAdapter? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        connectionToActivity = context as Callbacks?
    }

    override fun onDetach() {
        super.onDetach()
        connectionToActivity = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val x = inflater.inflate(R.layout.fragment_crime_list, container, false)

        myRecyclerView = x.findViewById(R.id.crimeRecycle)
        myRecyclerView.layoutManager = LinearLayoutManager(context)


        myAdapter = CrimeAdapter(CrimeDB.instance.crimeList)
        myRecyclerView.adapter = myAdapter
        return x
    }


    private inner class CrimeHolder(view: View):RecyclerView.ViewHolder(view), View.OnClickListener{
        private lateinit var holderCrime:Crime
        private val crimeTextViewOnHolder: TextView = view.findViewById(R.id.title_textView)
        private val crimeDateViewOnHolder: TextView = view.findViewById(R.id.date_textView)

        init{
            itemView.setOnClickListener(this)
        }

        fun bind(c:Crime){
            holderCrime = c
            crimeTextViewOnHolder.setText(c.title)
            crimeDateViewOnHolder.setText(c.date.toString())
        }

        override fun onClick(v: View?){
            Log.d("Webb", "I clicked" + holderCrime.title)

            connectionToActivity?.onCrimeSelected(holderCrime.id)

            /*val newThing = CrimeFragment.newInstance(holderCrime.id)
            val manager = activity?.supportFragmentManager
            manager?.beginTransaction()?.replace(R.id.fragment_shell, newThing)?.addToBackStack(null)?.commit()
*/
        }
    }

    private inner class CrimeAdapter(var crimes: List<Crime>):RecyclerView.Adapter<CrimeHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeHolder {
            val v = layoutInflater.inflate(R.layout.one_crime_item, parent, false)
            return CrimeHolder(v)
        }

        override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
            holder.bind(crimes[position])
        }

        override fun getItemCount(): Int {
            return crimes.size
        }

    }

}

