package com.gbr.auction.usecases

import com.gbr.auction.domains.Auction
import com.gbr.auction.exceptions.BadDomainException
import com.gbr.auction.fixtures.AuctionFixtures
import com.gbr.auction.gateways.AuctionGateway
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDateTime


@ExtendWith(SpringExtension::class)
@SpringBootTest
class CreateAuctionTest(@Autowired val createAuction: CreateAuction) {

    @MockBean
    private lateinit var gateway: AuctionGateway

    @Test
    fun `constraints validation - all not null`() {
        //when
        val exception = Assertions.assertThrows(BadDomainException::class.java) {
            createAuction.execute(Auction())
        }

        val errors = listOf(
                "auction.startTime.notNull",
                "auction.endTime.notNull",
                "auction.initialPrice.notNull",
                "auction.step.notNull",
                "auction.product.notBlank"
        )
        //then
        Assertions.assertTrue(
                exception.errors.containsAll(errors))
    }

    @Test
    fun `constraints validation - startTime invalid`() {
        //given
        val auction = AuctionFixtures.buildAnAuction()
        auction.startTime = LocalDateTime.now().minusHours(1)
        //when
        val exception = Assertions.assertThrows(BadDomainException::class.java) {
            createAuction.execute(auction)
        }
        //then
        Assertions.assertTrue(
                exception.errors.contains("auction.startTime.invalid"))
    }

    @Test
    fun `constraints validation - endTime invalid`() {
        //given
        val auction = AuctionFixtures.buildAnAuction()
        auction.startTime = LocalDateTime.now()
        auction.endTime = LocalDateTime.now().minusDays(1)
        //when
        val exception = Assertions.assertThrows(BadDomainException::class.java) {
            createAuction.execute(auction)
        }
        //then
        Assertions.assertTrue(
                exception.errors.contains("auction.endTime.invalid"))
    }

    @Test
    fun `create auction with success`() {
        //given
        val auction = AuctionFixtures.buildAnAuction()

        //when
        createAuction.execute(auction)

        //then
        verify(gateway, times(1)).save(auction)
    }


}