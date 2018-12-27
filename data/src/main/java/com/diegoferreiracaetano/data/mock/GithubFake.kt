package com.diegoferreiracaetano.data.mock

import com.diegoferreiracaetano.data.remote.github.pull.PullRemoteEntity
import com.diegoferreiracaetano.data.remote.github.repo.RepoRemoteEntity
import com.diegoferreiracaetano.data.remote.github.repo.RepoResponseRemoteEntity
import com.diegoferreiracaetano.domain.owner.Owner
import java.util.*

object GithubFake {

    val owner1 = Owner(1, "Diego", "http://www.diego.com.br")
    val owner2 = Owner(2, "Lucio", "http://www.diego.com.br")
    val owner3 = Owner(3, "Marcio", "http://www.diego.com.br")
    val owner4 = Owner(4, "Victor", "http://www.diego.com.br")
    val owner5 = Owner(5, "Test ", "http://www.diego.com.br")


    val repo1 = RepoRemoteEntity(1, "Repository 1", "description 1", 2000, 2000, owner1)
    val repo2 = RepoRemoteEntity(2, "Repository 2", "description 2", 100, 1000, owner2)
    val repo3 = RepoRemoteEntity(3, "Repository 3", "description 3", 100, 1000, owner3)
    val repo4 = RepoRemoteEntity(4, "Repository 4", "description 4", 100, 1000, owner4)
    val repo5 = RepoRemoteEntity(5, "Repository 5", "description 5", 100, 1000, owner5)
    val repo6 = RepoRemoteEntity(6, "Repository 5", "description 6", 100, 1000, owner5)
    val repo7 = RepoRemoteEntity(7, "Repository 5", "description 7", 100, 1000, owner5)
    val repo8 = RepoRemoteEntity(8, "Repository 5", "description 8", 100, 1000, owner5)
    val repo9 = RepoRemoteEntity(9, "Repository 5", "description 9", 100, 1000, owner5)

    val pull1 = PullRemoteEntity(1, "Title Pull 1", Date(), "body 1", "http://www.gogle.com.br", owner1, owner1.name, repo1.name)
    val pull2 = PullRemoteEntity(2, "Title Pull 2", Date(), "body 2 ", "http://www.gogle.com.br", owner2, owner2.name, repo2.name)
    val pull3 = PullRemoteEntity(3, "Title Pull 3", Date(), "body 3 ", "http://www.gogle.com.br", owner3, owner3.name, repo3.name)
    val pull4 = PullRemoteEntity(4, "Title Pull 4", Date(), "body 4 ", "http://www.gogle.com.br", owner4, owner4.name, repo4.name)
    val pull5 = PullRemoteEntity(5, "Title Pull 5", Date(), "body 5 ", "http://www.gogle.com.br", owner5, owner5.name, repo5.name)


    val repoResponse = RepoResponseRemoteEntity(listOf(repo1, repo2, repo3, repo4, repo5, repo6, repo7, repo8, repo9))

    val pullList = listOf(pull1, pull2, pull3, pull4, pull5)
}
