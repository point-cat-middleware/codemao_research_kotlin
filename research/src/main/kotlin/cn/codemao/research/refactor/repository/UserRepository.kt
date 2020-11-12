package cn.codemao.research.refactor.repository

import cn.codemao.research.refactor.domain.entity.User

/**
 *
 *
 * @author switch
 * @since 2020/11/12
 */
interface UserRepository {
    fun getByUserId(userId: Int): User
}