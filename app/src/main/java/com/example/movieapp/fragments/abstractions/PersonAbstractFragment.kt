package com.example.movieapp.fragments.abstractions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.adapter.MoviesAdapter
import com.example.movieapp.databinding.PersonDetailsBinding
import com.example.movieapp.fragments.ErrorFragment
import com.example.movieapp.viewmodels.ActorViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

abstract class PersonAbstractFragment: Fragment() {
    private var _actorId = 0
    private lateinit var _action : NavDirections
    private var _binding : PersonDetailsBinding? = null
    private val binding get() = _binding!!
    private val personViewModel : ActorViewModel by viewModel()
    private var moviesAdapter = MoviesAdapter()

    protected fun setActorId(actorId: Int){
        _actorId = actorId
    }

    protected fun setAction(action: NavDirections){
        _action = action
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        personViewModel.getActorById(_actorId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PersonDetailsBinding.inflate(inflater, container, false)
        personViewModel.exception.observe(viewLifecycleOwner){
            if (it!=null){
                val bundle = Bundle()
                bundle.putString("exceptionName", it.toString())
                bundle.putString("fragmentName", "PersonFragment")
                val errorFragment = ErrorFragment()
                errorFragment.arguments = bundle
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.navHostFragment, errorFragment, "fragmentId")
                    ?.commit()

            }
        }

        personViewModel.actor.observe(viewLifecycleOwner){actor->
            Glide.with(requireView()).load(actor.posterUrl).into(binding.actorDetailsIV)
            binding.actorDetailsNameTV.text = actor.nameRu
            binding.actorDetailsNameOriginalTV.text = actor.nameEn
            binding.actorsDetailsProfessionTV.text = actor.profession
            binding.birthplacePerson.text = actor.birthplace
        }

        binding.aboutPersonBtn.setOnClickListener {
            it.findNavController().navigate(_action)
        }

        binding.actorFilms.adapter = moviesAdapter
        binding.actorFilms.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)

        return binding.root
    }
}