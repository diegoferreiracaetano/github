package mock

import com.diegoferreiracaetano.domain.owner.Owner
import com.diegoferreiracaetano.domain.pull.Pull
import com.diegoferreiracaetano.domain.repo.Repo
import java.util.*

object Mocks {

    val owner = Owner(1, "Test ", "http://www.diego.com.br")

    val repo = Repo(1, "Repo Name", "description", 100, 200, owner)

    val pull = Pull(1, "Title Pull", Date(), "lalalalla oaoananbinba ", "http://www.gogle.com.br", owner, owner.name, repo.name)


    fun repoList(): List<Repo> {
        val list = mutableListOf<Repo>()
        for (i in 1..10) {
            list.add(Repo(i.toLong(), "Repo Name " + i, "description " + i, 1000 - i, i, ownerList().get(i - 1)))
        }
        return list
    }

    fun pullList(): List<Pull> {
        val list = mutableListOf<Pull>()
        for (i in 1..10) {
            list.add(Pull(i.toLong(), "Title Pull " + i, Date(), "body " + i, "http://www.gogle.com.br",
                    repoList().get(i - 1).owner, repoList().get(i - 1).owner.name, repoList().get(i - 1).name))
        }
        return list
    }

    fun ownerList(): List<Owner> {
        val list = mutableListOf<Owner>()
        for (i in 1..10) {
            list.add(Owner(i.toLong(), "Test " + i, "http://www.diego.com.br"))
        }
        return list
    }


}
