package com.gbr.auction.http

import com.gbr.auction.exceptions.BadDomainException
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler


@ControllerAdvice
class ErrorHandling {

    private val logger = KotlinLogging.logger {}

    @ExceptionHandler(BadDomainException::class)
    fun handleValidationCodeException(ex: BadDomainException): ResponseEntity<Collection<String>> {
        logger.warn("bad domain exception returned [${ex.errors.joinToString(",")}]")
        return ResponseEntity(ex.errors, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(Throwable::class)
    fun handleThrowable(ex: Throwable): ResponseEntity<String> {
        logger.error(ex.message, ex)
        return ResponseEntity("internal server error", HttpStatus.INTERNAL_SERVER_ERROR)
    }

}