package cn.codemao.research.refactor.repository.impl

import cn.codemao.research.refactor.domain.entity.User
import cn.codemao.research.refactor.repository.UserRepository
import org.springframework.stereotype.Repository

/**
 *
 *
 * @author switch
 * @since 2020/11/12
 */
@Repository
class UserRepositoryImpl : UserRepository {
    override fun getByUserId(userId: Int): User = User(userId, "Switch", 18)

    override fun listUsers(): List<User> {
        return listOf(
                User(1, "S1", 18),
                User(2, "S2", 20),
                User(3, "S3", 30),
                User(4, "S4", 13),
                User(5, "S5", 16),
                User(6, "S6", 10),
                User(7, "S7", 50),
                User(8, "S7", 80),
                User(9, "S7", 90),
                User(10, "S7", 100)
        )
    }
}