package com.example.flight.tabMenu.myFlight

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flight.R
import com.example.flight.RequestHttp
import kotlinx.android.synthetic.main.fragment_my_flight.*


class MyFlightFragment : Fragment() {

    private lateinit var myFlightFragmentViewModel: MyFlightViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myFlightFragmentViewModel = ViewModelProviders.of(this).get(MyFlightViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        val root = inflater.inflate(R.layout.fragment_my_flight, container, false)

        var usersList=arrayListOf<Reservations>()

        val recyclerView = root.findViewById<RecyclerView>(R.id.recyclerViewReservations)

        //3º) Indico la disposición en la que se mostrarán los items en el RecyclerView (P.Ej: GridLayout de 2 columnas)
        val layoutManagerStudents = GridLayoutManager(root.context, 1)
        recyclerView.setLayoutManager(layoutManagerStudents)

        RequestHttp.getAllAirports(root.context,usersList,recyclerView)

        return root
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): MyFlightFragment {
            return MyFlightFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}