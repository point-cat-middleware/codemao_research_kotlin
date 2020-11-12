package cn.codemao.research.refactor.service

import cn.codemao.research.refactor.domain.entity.User

/**
 *
 *
 * @author switch
 * @since 2020/11/12
 */

interface UserService {
    fun getByUserId(userId: Int): User
}