package com.gbr.auction.gateways.mongo.docs

import com.gbr.auction.domains.Bid
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "auctions")
data class AuctionMongo(
        var id: String? = null,
        var startTime: LocalDateTime? = null,
        var endTime: LocalDateTime? = null,
        var initialPrice: Long? = null,
        var step: Long? = null,
        var product: String? = null,
        var bid: Bid? = null
)