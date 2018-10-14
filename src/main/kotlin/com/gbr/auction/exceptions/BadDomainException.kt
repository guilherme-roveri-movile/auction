package com.gbr.auction.exceptions

class BadDomainException(val errors: Set<String>) : RuntimeException()
