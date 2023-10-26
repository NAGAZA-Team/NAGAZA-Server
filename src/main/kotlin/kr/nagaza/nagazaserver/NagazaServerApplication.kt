package kr.nagaza.nagazaserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class NagazaServerApplication

fun main(args: Array<String>) {
    runApplication<NagazaServerApplication>(*args)
}
