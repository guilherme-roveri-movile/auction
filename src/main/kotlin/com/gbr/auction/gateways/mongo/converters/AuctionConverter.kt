package com.gbr.auction.gateways.mongo.converters

import com.gbr.auction.domains.Auction
import com.gbr.auction.gateways.mongo.MongoConverter
import com.gbr.auction.gateways.mongo.docs.AuctionMongo

object AuctionConverter : MongoConverter<Auction, AuctionMongo> {

    override fun fromDocument(from: AuctionMongo): Auction {
        val (id, startTime, endTime, initialPrice, step, product, bid) = from
        return Auction(
                id = id,
                startTime = startTime,
                endTime = endTime,
                initialPrice = initialPrice,
                step = step,
                product = product,
                bid = bid
        )
    }

    override fun toDocument(from: Auction): AuctionMongo {
        val (id, startTime, endTime, initialPrice, step, product, bid) = from
        return AuctionMongo(
                id = id,
                startTime = startTime,
                endTime = endTime,
                initialPrice = initialPrice,
                step = step,
                product = product,
                bid = bid
        )
    }
}