package com.gbr.auction.gateways.mongo.converters

import com.gbr.auction.fixtures.AuctionFixtures
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest
class AuctionConverterTests {

    @Test
    fun `convert from auction to document`() {
        //given
        val auction = AuctionFixtures.buildAnAuction()
        //when
        val auctionMongo = AuctionConverter.toDocument(auction)
        //then
        assertEquals(auction.id, auctionMongo.id)
        assertEquals(auction.startTime, auctionMongo.startTime)
        assertEquals(auction.endTime, auctionMongo.endTime)
        assertEquals(auction.initialPrice, auctionMongo.initialPrice)
        assertEquals(auction.product, auctionMongo.product)
        assertEquals(auction.step, auctionMongo.step)
        assertEquals(auction.bid, auctionMongo.bid)
    }

    @Test
    fun `convert from document to auction`() {
        //given
        val auctionMongo = AuctionConverter.toDocument(AuctionFixtures.buildAnAuction())
        //when
        val auction = AuctionConverter.fromDocument(auctionMongo)
        //then
        assertEquals(auctionMongo.id, auction.id)
        assertEquals(auctionMongo.startTime, auction.startTime)
        assertEquals(auctionMongo.endTime, auction.endTime)
        assertEquals(auctionMongo.initialPrice, auction.initialPrice)
        assertEquals(auctionMongo.product, auction.product)
        assertEquals(auctionMongo.step, auction.step)
        assertEquals(auctionMongo.bid, auction.bid)
    }

}