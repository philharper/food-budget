package uk.co.philharper.foodbudget.entity

import java.util.*
import kotlin.collections.HashMap


class ShopCalculations(shops: List<Shop>, calendar: Calendar) {

    val currentCalendar = calendar

    var yearTotal = 0.0
    var yearTotalExcludingCurrentMonth = 0.0
    var currentWeekTotal = 0.0
    var currentMonthTotal = 0.0
    var weeklyAverage = 0.0
    var monthlyAverage = 0.0
    var shopTotals : HashMap<String, Float> = HashMap()

    init {

        for (shop in shops) {
            addPriceToYearTotal(shop)
            addPriceToCurrentWeekTotalIfThisWeek(shop)
            addPriceToCurrentMonthTotal(shop)
            addToShopTotals(shop)
        }

        calculateWeeklyAverage()
        calculateMonthlyAverage()
    }

    private fun addPriceToYearTotal(shop: Shop) {
        if (isShopInCurrentYear(currentCalendar, shop)) {
            yearTotal += shop.price
            addToYearTotalExcludingCurrentMonth(shop)
        }
    }

    private fun addToYearTotalExcludingCurrentMonth(shop: Shop) {
        if (!isShopInCurrentMonth(currentCalendar, shop)) {
            yearTotalExcludingCurrentMonth += shop.price
        }
    }

    private fun isShopInCurrentYear(currentCalendar: Calendar, shop: Shop): Boolean {
        val shopCalendar = createShopCalendar(shop)
        return currentCalendar.get(Calendar.YEAR) == shopCalendar.get(Calendar.YEAR)
    }

    private fun addPriceToCurrentWeekTotalIfThisWeek(shop: Shop) {
        if (isShopInCurrentWeek(currentCalendar, shop)) {
            currentWeekTotal += shop.price
        }
    }

    private fun isShopInCurrentWeek(currentCalendar: Calendar, shop: Shop): Boolean {
        val shopCalendar = createShopCalendar(shop)
        return currentCalendar.get(Calendar.YEAR) == shopCalendar.get(Calendar.YEAR) &&
                currentCalendar.get(Calendar.WEEK_OF_YEAR) == shopCalendar.get(Calendar.WEEK_OF_YEAR)
    }

    private fun addPriceToCurrentMonthTotal(shop: Shop) {
        if (isShopInCurrentMonth(currentCalendar, shop)) {
            currentMonthTotal += shop.price
        }
    }

    private fun isShopInCurrentMonth(currentCalendar: Calendar, shop: Shop): Boolean {
        val shopCalendar = createShopCalendar(shop)
        return currentCalendar.get(Calendar.YEAR) == shopCalendar.get(Calendar.YEAR) && currentCalendar.get(Calendar.MONTH) == shopCalendar.get(Calendar.MONTH)
    }

    private fun createShopCalendar(shop: Shop): Calendar {
        val shopCalendar = Calendar.getInstance()
        shopCalendar.time = Date(shop.date.toDate().time)
        return shopCalendar
    }

    private fun calculateWeeklyAverage() {
        weeklyAverage = yearTotal / currentCalendar.get(Calendar.WEEK_OF_YEAR)
    }

    private fun calculateMonthlyAverage() {
        monthlyAverage = yearTotalExcludingCurrentMonth / currentCalendar.get(Calendar.MONTH)
    }

    private fun addToShopTotals(shop: Shop) {
        if (shopTotals.containsKey(shop.location)) {
            var currentValue = shopTotals[shop.location]
            shopTotals[shop.location] = shop.price.plus(currentValue!!)
        } else {
            shopTotals[shop.location] = shop.price
        }
    }
}