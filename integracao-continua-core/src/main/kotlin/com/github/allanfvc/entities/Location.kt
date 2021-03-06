package com.github.allanfvc.entities

class Location(val lat: Double, val lng: Double) {
    constructor(): this(Double.MIN_VALUE, Double.MIN_VALUE)
}
