package org.unicef.parenthood.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.unicef.parenthood.R

class OnboardingContainerPageAdapter :
    RecyclerView.Adapter<OnboardingContainerPageAdapter.EventViewHolder>() {
    val eventList = listOf("0", "1", "2")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        EventViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.fragment_onboarding_main,
                parent,
                false
            )
        )

    override fun getItemCount() = eventList.count()
    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
    }

    class EventViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}