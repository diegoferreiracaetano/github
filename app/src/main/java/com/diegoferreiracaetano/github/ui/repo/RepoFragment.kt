package com.diegoferreiracaetano.github.ui.repo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.diegoferreiracaetano.domain.repo.Repo
import com.diegoferreiracaetano.github.R
import com.diegoferreiracaetano.github.databinding.FragmentRepoBinding
import com.diegoferreiracaetano.github.ui.MainActivity
import com.diegoferreiracaetano.github.ui.pull.PullFragment
import com.diegoferreiracaetano.github.ui.repo.adapter.RepoViewHolder
import kotlinx.android.synthetic.main.fragment_repo.*
import org.koin.androidx.viewmodel.ext.android.viewModel



class RepoFragment : Fragment(),RepoViewHolder.OnItemClickListener{


    val viewModel: RepoViewModel by viewModel()
    private lateinit var binding: FragmentRepoBinding
    private var pullFragment : PullFragment? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_repo, container, false)
        binding.setLifecycleOwner(this@RepoFragment)
        binding.viewModel = viewModel
        binding.callback = this
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        pullFragment = activity!!.supportFragmentManager.findFragmentById(R.id.containerFragment) as PullFragment ?

        when {
            resources.getBoolean(R.bool.has_two_panes) -> when {
                viewModel.itemSelected.value == null -> loadDefaultRecord()
                else -> loadLastItemSelected()
            }
            else -> cleanToolbar()
        }
    }

    override fun onItemClick(view: View,repo: Repo) {
        viewModel.itemSelected.value = repo
        when {
            resources.getBoolean(R.bool.has_two_panes) -> pullFragment?.display(repo.name,repo.owner.name)
            else -> {
                val bundle = Bundle()
                bundle.putSerializable(PullFragment.EXTRA_REPO_NAME, repo.name)
                bundle.putSerializable(PullFragment.EXTRA_OWNER_NAME, repo.owner.name)
                Navigation.findNavController(view).navigate(R.id.action_next, bundle)
            }
        }
    }

    private fun loadDefaultRecord(){
        viewModel.result.observe(this, Observer {
            if(it.size >0) {
                val repo = it[0]
                if(repo != null) {
                    viewModel.itemSelected.value = repo
                    pullFragment?.display(repo.name, repo.owner.name)
                }
            }
        })
    }
    private fun loadLastItemSelected(){
        val repo  = viewModel.itemSelected.value
        if(repo != null) {
            pullFragment?.display(repo.name, repo.owner.name)
        }
    }
    private fun cleanToolbar(){
        (activity as MainActivity).supportActionBar?.subtitle = ""
    }
}
