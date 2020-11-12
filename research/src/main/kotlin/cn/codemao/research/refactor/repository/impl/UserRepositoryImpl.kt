package cn.codemao.research.refactor.repository.impl

import cn.codemao.research.refactor.common.util.ZonedDateTimeUtil.now
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
    override fun getByUserId(userId: Int): User = User(userId, "Switch", 18, now())
}