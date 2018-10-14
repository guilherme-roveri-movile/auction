package com.gbr.auction.gateways

import com.gbr.auction.domains.Auction

interface AuctionGateway {
    fun save(auction: Auction): Auction
}