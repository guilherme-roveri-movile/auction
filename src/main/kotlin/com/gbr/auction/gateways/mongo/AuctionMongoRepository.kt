package com.gbr.auction.gateways.mongo

import com.gbr.auction.gateways.mongo.docs.AuctionMongo
import org.springframework.data.mongodb.repository.MongoRepository

interface AuctionMongoRepository : MongoRepository<AuctionMongo, String>