package com.example.maintenance.Dashboard.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.maintenance.Dashboard.Adapters.MediaRecyclerViewAdapter
import com.example.maintenance.Dashboard.Adapters.TimelineRecyclerViewAdapter
import com.example.maintenance.Dashboard.MaintenanceViewModal
import com.example.maintenance.Helpers.Models.Request
import com.example.maintenance.Helpers.toFormattedDate
import com.example.maintenance.R
import kotlinx.android.synthetic.main.view_request_fragment_second.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ViewRequestFragment : Fragment() {
    private var TAG = "ViewRequestFragment"

    private val viewModal: MaintenanceViewModal by activityViewModels()
    private val mediaRecyclerViewAdapter = MediaRecyclerViewAdapter(ArrayList())
    private val timelineRecyclerViewAdapter = TimelineRecyclerViewAdapter(ArrayList())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.view_request_fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        view.findViewById<Button>(R.id.button_second).setOnClickListener {
//            findNavController().navigate(R.id.action_ViewRequestFragment_to_RequestListFragment)
//        }

        media_recycler_view.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        media_recycler_view.adapter = mediaRecyclerViewAdapter

        timeline_recycler_view.layoutManager = LinearLayoutManager(view.context)
        timeline_recycler_view.adapter = timelineRecyclerViewAdapter

        observeFromViewModal()
    }

    private fun observeFromViewModal() {
        viewModal.selectedRequests.observe(viewLifecycleOwner, { request ->
            populateFragment(request)
        })
    }

    private fun populateFragment(request: Request) {
        txtDateTime.text = "Reported on ${request.createdAt.toFormattedDate()}"
        txtTitle.text = "${request.area.name} - ${request.problem.name}"
        txtDescription.text = request.details
        // Load the recycler views with new data
        mediaRecyclerViewAdapter.loadNewData(request.attachments)
        timelineRecyclerViewAdapter.loadNewData(request.log)
    }
}