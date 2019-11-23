package org.unicef.parenthood.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import org.unicef.parenthood.R
import org.unicef.parenthood.databinding.FragmentCreateTestBinding
import org.unicef.parenthood.repository.model.TestEntity
import org.unicef.parenthood.ui.viewmodel.CreateViewModel

/**
 * A simple [Fragment] subclass.
 */
class CreateTestFragment : Fragment() {

    private val args: CreateTestFragmentArgs by navArgs()

    private lateinit var binding: FragmentCreateTestBinding

    private val viewModel: CreateViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateTestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCreate.setOnClickListener {
            val q1 = binding.etQuestionOne.text.toString()
            val ans1 = binding.etAnswerOne.text.toString()
            val q2 = binding.etQuestionTwo.text.toString()
            val ans2 = binding.etAnswerTwo.text.toString()
            val q3 = binding.etQuestionThree.text.toString()
            val ans3 = binding.etAnswerThree.text.toString()
            val list = listOf("$q1|$ans1", "$q2|$ans2", "$q3|$ans3")

            val test = TestEntity(questions = list)
            viewModel.addTest(test, args.articleId)
            findNavController().popBackStack(R.id.articlesContainerFragment, false)
        }

        binding.btnCancelCreate.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}
