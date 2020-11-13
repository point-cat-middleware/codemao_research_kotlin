package cn.codemao.research.refactor.controller

import cn.codemao.research.refactor.domain.entity.User
import cn.codemao.research.refactor.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


/**
 * @author switch
 * @since 2020/11/13
 */
@RestController
@RequestMapping("/user")
class UserKotlinController(
        private val userService: UserService
) {

    @GetMapping("/kotlin/{userId}")
    fun getByUserId(@PathVariable("userId") userId: Int): User {
        return userService.getByUserId(userId)
    }
}