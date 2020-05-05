package me.sam.test.jetpack.navigation

import android.os.Bundle
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

class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val model = ViewModelProvider(requireActivity()).get(LiveDataWithViewModel::class.java)
        model.number.observe(viewLifecycleOwner, Observer { homeNumberTxt.text = model.number.value.toString() })
        addBtn.setOnClickListener { model.addNumber(1) }

        toDetailBtn.setOnClickListener { v ->
            val controller = Navigation.findNavController(v)
            controller.navigate(R.id.action_homeFragment_to_detailFragment)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}
