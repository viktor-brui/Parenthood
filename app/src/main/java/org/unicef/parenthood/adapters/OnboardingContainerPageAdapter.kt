package org.unicef.parenthood.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_onboarding_one.view.*
import org.unicef.parenthood.R

class OnboardingContainerPageAdapter :
    RecyclerView.Adapter<OnboardingContainerPageAdapter.PagerVH>() {

    private val elements = listOf(
        R.string.onboarding_step_one to R.drawable.s1,
        R.string.onboarding_step_two to R.drawable.s2,
        R.string.onboarding_step_three to R.drawable.s3
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerVH =
        PagerVH(
            LayoutInflater.from(parent.context).inflate(
                R.layout.fragment_onboarding_one,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = elements.size // +

    override fun onBindViewHolder(holder: PagerVH, position: Int) {
        val (res, image) = elements[position]
        holder.itemView.run {
            tv_caption.text = resources.getString(res)
            img_screenshot.setImageResource(image)
        }
    }

    class PagerVH(itemView: View) : RecyclerView.ViewHolder(itemView)
}