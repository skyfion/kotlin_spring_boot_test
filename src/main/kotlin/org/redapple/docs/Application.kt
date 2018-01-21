package org.redapple.docs

import org.jetbrains.exposed.spring.SpringTransactionManager
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import javax.sql.DataSource

@SpringBootApplication
open class Application {
    @Bean
    open fun transactionManager(dataSource: DataSource) = SpringTransactionManager(dataSource)
}

fun main(args: Array<String>) {

    SpringApplication.run(Application::class.java, *args)
}