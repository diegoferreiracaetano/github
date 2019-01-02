package com.diegoferreiracaetano.github.mock

import com.diegoferreiracaetano.domain.owner.Owner
import com.diegoferreiracaetano.domain.pull.Pull
import com.diegoferreiracaetano.domain.repo.Repo
import java.util.*

object MocksTest {

    val owner = Owner(1, "Test ", "http://www.diego.com.br")

    val repo = Repo(1, "Repo Name", "description", 100, 200, owner)

    val pull = Pull(1, "Title Pull", Date(), "lalalalla oaoananbinba ", "http://www.gogle.com.br", owner, owner.name, repo.name)
}
