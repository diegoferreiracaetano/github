package com.diegoferreiracaetano.github.ui.repo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.diegoferreiracaetano.domain.repo.Repo
import com.diegoferreiracaetano.github.R
import com.diegoferreiracaetano.github.databinding.FragmentRepoBinding
import com.diegoferreiracaetano.github.ui.repo.adapter.RepoViewHolder
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepoFragment : Fragment(), RepoViewHolder.OnItemClickListener {

    val viewModel: RepoViewModel by viewModel()
    private lateinit var binding: FragmentRepoBinding

    override fun onCreateView(inflater: LayoutInflater, cont: ViewGroup?, savedState: Bundle?): View {

        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_repo, cont, false)
        binding.setLifecycleOwner(this@RepoFragment)
        binding.viewModel = viewModel
        binding.callback = this
        return binding.root
    }

    override fun onItemClick(view: View, repo: Repo) {
        val action = RepoFragmentDirections.actionNext(repo.name, repo.owner.name)
        Navigation.findNavController(view).navigate(action)
    }
}
