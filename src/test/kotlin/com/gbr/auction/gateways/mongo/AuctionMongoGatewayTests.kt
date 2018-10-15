package com.gbr.auction.gateways.mongo

import com.gbr.auction.fixtures.AuctionFixtures
import com.gbr.auction.gateways.AuctionGateway
import com.gbr.auction.gateways.mongo.docs.AuctionMongo
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest
class AuctionMongoGatewayTests @Autowired constructor(val gateway: AuctionGateway, val mongoTemplate: MongoTemplate) {

    @BeforeEach
    fun setup() {
        mongoTemplate.dropCollection(AuctionMongo::class.java)
    }

    @Test
    fun `create a new auction`() {
        //given
        val auction = AuctionFixtures.buildAnAuction()

        //when
        val (id) = gateway.save(auction)

        //then
        assertNotNull(id)
    }
}