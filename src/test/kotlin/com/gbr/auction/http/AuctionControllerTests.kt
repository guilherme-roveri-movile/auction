package com.gbr.auction.http

import com.gbr.auction.exceptions.BadDomainException
import com.gbr.auction.fixtures.AuctionFixtures
import com.gbr.auction.usecases.CreateAuction
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.time.format.DateTimeFormatter

@ExtendWith(SpringExtension::class)
@WebMvcTest
class AuctionControllerTests(@Autowired val mockMvc: MockMvc) {

    @MockBean
    private lateinit var createAuction: CreateAuction

    @Test
    fun `create a new auction`() {
        val auction = AuctionFixtures.buildAnAuction()
        auction.id = "test"
        whenever(createAuction.execute(any())).thenReturn(auction)
        mockMvc.perform(post("/auctions").content(AuctionFixtures.buildAuctionJson()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("startTime").value(auction.startTime!!.format(DateTimeFormatter.ISO_DATE_TIME)))
                .andExpect(jsonPath("endTime").value(auction.endTime!!.format(DateTimeFormatter.ISO_DATE_TIME)))
                .andExpect(jsonPath("initialPrice").value(auction.initialPrice!!))
                .andExpect(jsonPath("step").value(auction.step!!))
                .andExpect(jsonPath("product").value(auction.product!!))
    }

    @Test
    fun `try to create a new invalid auction`() {
        val expectedError = "auction.product.notBlank"
        whenever(createAuction.execute(any())).thenThrow(BadDomainException(setOf(expectedError)))
        mockMvc.perform(post("/auctions").content(AuctionFixtures.buildInvalidAuctionJson()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.[0]").value(expectedError))
    }

}