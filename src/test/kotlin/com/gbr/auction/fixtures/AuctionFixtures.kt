package com.gbr.auction.fixtures

import com.gbr.auction.domains.Auction
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object AuctionFixtures {
    fun buildAnAuction(): Auction {
        return Auction(
                startTime = LocalDate.now().plusDays(2).atTime(0, 0),
                endTime = LocalDate.now().plusDays(2).atTime(23, 59),
                initialPrice = 100000000,
                step = 100000,
                product = "Tesla"
        )
    }

    fun buildAuctionJson(): String {
        val auction = buildAnAuction()
        return "{\n" +
                "  \"startTime\": \"${auction.startTime!!.format(DateTimeFormatter.ISO_DATE_TIME)}\",\n" +
                "  \"endTime\": \"${auction.endTime!!.format(DateTimeFormatter.ISO_DATE_TIME)}\",\n" +
                "  \"initialPrice\": ${auction.initialPrice},\n" +
                "  \"step\": ${auction.step},\n" +
                "  \"product\": \"${auction.product}\"\n" +
                "}"
    }
}