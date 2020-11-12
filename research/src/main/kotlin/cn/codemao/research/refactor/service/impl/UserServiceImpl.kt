package cn.codemao.research.refactor.service.impl

import cn.codemao.research.refactor.domain.entity.User
import cn.codemao.research.refactor.repository.UserRepository
import cn.codemao.research.refactor.service.UserService
import org.springframework.stereotype.Service

/**
 *
 *
 * @author switch
 * @since 2020/11/12
 */
@Service
class UserServiceImpl(
        private val userRepository: UserRepository
) : UserService {
    override fun getByUserId(userId: Int): User = userRepository.getByUserId(userId)
}