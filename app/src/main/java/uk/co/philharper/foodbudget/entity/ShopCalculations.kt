package uk.co.philharper.foodbudget.entity

class ShopCalculations(shops: List<Shop>) {

    var totalSpent = 0.0

    init {
        for (shop in shops) {
            totalSpent += shop.price
        }
    }
}