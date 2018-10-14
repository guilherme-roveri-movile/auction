package com.gbr.auction.http

import com.gbr.auction.domains.Auction
import com.gbr.auction.usecases.CreateAuction
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController("/auctions")
class AuctionController(@Autowired val createAuction: CreateAuction) {

    @PostMapping
    fun create(auction: Auction): Auction {
        return createAuction.execute(auction)
    }
}