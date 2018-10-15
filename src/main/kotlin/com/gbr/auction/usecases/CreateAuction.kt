package com.gbr.auction.usecases

import com.gbr.auction.domains.Auction
import com.gbr.auction.exceptions.BadDomainException
import com.gbr.auction.gateways.AuctionGateway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.validation.Validator

@Component
class CreateAuction @Autowired constructor(
        val validator: Validator,
        val gateway: AuctionGateway) {

    fun execute(auction: Auction): Auction {
        val result = validator.validate(auction)
        if (result.isNotEmpty())
            throw BadDomainException(result.map { it.messageTemplate }.toSet())
        return gateway.save(auction)
    }

}