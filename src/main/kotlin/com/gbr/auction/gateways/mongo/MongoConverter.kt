package com.gbr.auction.gateways.mongo

interface MongoConverter<F, T> {
    fun toDocument(from: F): T
    fun fromDocument(from: T): F
}