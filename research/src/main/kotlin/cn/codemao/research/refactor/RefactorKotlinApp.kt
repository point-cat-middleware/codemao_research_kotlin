package cn.codemao.research.refactor

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

/**
 * @author switch
 * @since 2020/11/12
 */
@SpringBootApplication
class RefactorKotlinApp

fun main(args: Array<String>) {
    SpringApplication.run(RefactorKotlinApp::class.java, *args)
}