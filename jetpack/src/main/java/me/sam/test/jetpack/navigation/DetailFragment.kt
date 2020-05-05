package me.sam.test.jetpack.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_detail.*

import me.sam.test.jetpack.R
import me.sam.test.jetpack.livedata.LiveDataWithViewModel

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class DetailFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        backHomeBtn.setOnClickListener { v ->
            val controller = Navigation.findNavController(v)
            controller.navigate(R.id.action_detailFragment_to_homeFragment)
        }

        val model = ViewModelProvider(requireActivity()).get(LiveDataWithViewModel::class.java)
        model.number.observe(viewLifecycleOwner, Observer {
            detailNumberTxt.text = model.number.value.toString()
        })

        addBtn.setOnClickListener {
            model.addNumber(2)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                DetailFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
