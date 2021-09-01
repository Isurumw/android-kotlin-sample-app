package com.example.maintenance.Dashboard.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.maintenance.Dashboard.MaintenanceRecyclerViewAdapter
import com.example.maintenance.Dashboard.MaintenanceViewModal
import com.example.maintenance.Helpers.RecyclerItemClickListener
import com.example.maintenance.R
import kotlinx.android.synthetic.main.request_list_fragment_first.*

private const val ARG_PARAM_REQUEST_LIST = "param_request_list"

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class RequestListFragment : Fragment(), RecyclerItemClickListener.OnRecyclerClickListener {
    private var TAG = "RequestListFragment"

    private val viewModal: MaintenanceViewModal by activityViewModels()
    private val recyclerViewAdapter = MaintenanceRecyclerViewAdapter(ArrayList())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.request_list_fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_view.layoutManager = LinearLayoutManager(view.context)
        recycler_view.addOnItemTouchListener(RecyclerItemClickListener(view.context, recycler_view, this))
        recycler_view.adapter = recyclerViewAdapter

        observeFromViewModal()
    }

    private fun observeFromViewModal() {
        viewModal.requests.observe(viewLifecycleOwner, Observer { requests ->
            recyclerViewAdapter.loadNewData(requests)
        })
    }

    override fun onItemClick(view: View, position: Int) {
        Log.d(TAG, "The tapped position: $position")
        viewModal.setSelectedRequest(position)
        findNavController().navigate(R.id.action_RequestListFragment_to_ViewRequestFragment)
    }
}