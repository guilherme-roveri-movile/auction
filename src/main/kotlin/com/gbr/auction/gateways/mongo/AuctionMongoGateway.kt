package com.gbr.auction.gateways.mongo

import com.gbr.auction.domains.Auction
import com.gbr.auction.gateways.AuctionGateway
import com.gbr.auction.gateways.mongo.converters.AuctionConverter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class AuctionMongoGateway(@Autowired val mongoRepository: AuctionMongoRepository) : AuctionGateway {

    override fun save(auction: Auction): Auction {
        return AuctionConverter.fromDocument(mongoRepository.save(AuctionConverter.toDocument(auction)))
    }

}