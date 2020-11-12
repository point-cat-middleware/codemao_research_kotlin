package cn.codemao.research.refactor.domain.entity

import java.time.ZonedDateTime

/**
 *
 *
 * @author switch
 * @since 2020/11/12
 */
data class User(
        val id: Int,
        val name: String,
        val age: Int,
        val time: ZonedDateTime
)