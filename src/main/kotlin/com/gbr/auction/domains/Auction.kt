package com.gbr.auction.domains

import java.time.LocalDate
import java.time.LocalDateTime
import javax.validation.constraints.AssertTrue
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class Auction(
        var id: String? = null,

        @field:NotNull(message = "auction.startTime.notNull")
        var startTime: LocalDateTime? = null,

        @field:NotNull(message = "auction.endTime.notNull")
        var endTime: LocalDateTime? = null,

        @field:NotNull(message = "auction.initialPrice.notNull")
        var initialPrice: Long? = null,

        @field:NotNull(message = "auction.step.notNull")
        var step: Long? = null,

        @field:NotBlank(message = "auction.product.notBlank")
        var product: String? = null,

        var bid: Bid? = null) {

    @AssertTrue(message = "auction.startTime.invalid")
    fun hasValidStartTime(): Boolean {
        return if (startTime == null) true else
            startTime!!.isAfter(LocalDate.now().plusDays(1).atStartOfDay())
    }

    @AssertTrue(message = "auction.endTime.invalid")
    fun hasValidEndTime(): Boolean {
        return if (endTime == null || startTime == null) true else
            endTime!!.isAfter(startTime)
    }
}

