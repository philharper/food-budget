package uk.co.philharper.foodbudget.entity

import java.util.*


class ShopCalculations(shops: List<Shop>) {

    val currentCalendar = Calendar.getInstance()

    var totalSpent = 0.0
    var currentWeekTotal = 0.0
    var currentMonthTotal = 0.0

    init {

        for (shop in shops) {
            addPriceToTotalSpend(shop)
            addPriceToCurrentWeekTotalIfThisWeek(shop)
            addPriceToCurrentMonthTotal(shop)
        }
    }

    private fun addPriceToTotalSpend(shop: Shop) {
        totalSpent += shop.price
    }

    private fun addPriceToCurrentWeekTotalIfThisWeek(shop: Shop) {
        if (isShopInCurrentWeek(currentCalendar, shop)) {
            currentWeekTotal += shop.price
        }
    }

    private fun isShopInCurrentWeek(currentCalendar: Calendar, shop: Shop): Boolean {
        val shopCalendar = createShopCalendar(shop)
        return currentCalendar.get(Calendar.YEAR) == shopCalendar.get(Calendar.YEAR) && currentCalendar.get(Calendar.WEEK_OF_YEAR) == shopCalendar.get(
            Calendar.WEEK_OF_YEAR
        )
    }

    private fun addPriceToCurrentMonthTotal(shop: Shop) {
        if (isShopInCurrentMonth(currentCalendar, shop)) {
            currentMonthTotal += shop.price
        }
    }

    private fun isShopInCurrentMonth(currentCalendar: Calendar, shop: Shop): Boolean {
        val shopCalendar = createShopCalendar(shop)
        return currentCalendar.get(Calendar.YEAR) == shopCalendar.get(Calendar.YEAR) && currentCalendar.get(Calendar.MONTH) == shopCalendar.get(
            Calendar.MONTH
        )
    }

    private fun createShopCalendar(shop: Shop): Calendar {
        val shopCalendar = Calendar.getInstance()
        shopCalendar.time = Date(shop.date.toDate().time)
        return shopCalendar
    }
}