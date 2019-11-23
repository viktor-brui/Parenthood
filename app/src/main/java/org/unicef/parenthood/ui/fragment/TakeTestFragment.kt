package org.unicef.parenthood.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import org.unicef.parenthood.R
import org.unicef.parenthood.databinding.FragmentTakeTestBinding
import org.unicef.parenthood.ui.viewmodel.TakeTestViewModel
import org.unicef.parenthood.util.toast

/**
 * A simple [Fragment] subclass.
 */
class TakeTestFragment : Fragment() {

    private val args: TakeTestFragmentArgs by navArgs()

    private val viewModel: TakeTestViewModel by viewModels()

    private lateinit var binding: FragmentTakeTestBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTakeTestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSubmit.setOnClickListener {
            toast("Test was passed")
            findNavController().popBackStack(R.id.articlesContainerFragment, false)
        }

        viewModel.getTest(args.testId)
        viewModel.test.observe(viewLifecycleOwner) { questions ->
            binding.qAndA = questions
            binding.executePendingBindings()
        }
    }
}
