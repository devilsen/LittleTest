package me.sam.test.jetpack.navigation

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_home.*
import me.sam.test.jetpack.R
import me.sam.test.jetpack.livedata.LiveDataWithViewModel
import kotlin.math.log

class HomeFragment : Fragment() {

    companion object {
        const val TAG: String = "navi fragment"

        @JvmStatic
        fun newInstance() = HomeFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "onAttach: ")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: ")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.d(TAG, "onCreateView: ")
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG, "onActivityCreated: ")
        val model = ViewModelProvider(requireActivity()).get(LiveDataWithViewModel::class.java)
        model.number.observe(viewLifecycleOwner, Observer { homeNumberTxt.text = model.number.value.toString() })
        addBtn.setOnClickListener { model.addNumber(1) }

        toDetailBtn.setOnClickListener { v ->
            val controller = Navigation.findNavController(v)
            controller.navigate(R.id.action_homeFragment_to_detailFragment)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, "onDetach: ")
    }

}
