package uk.co.philharper.foodbudget.entity

import com.google.firebase.Timestamp
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.*


class ShopCalculationsTest {

    private val currentTestDate = 1549822376L
    private val dateLastYear = 1519043516L
    private val dateLastWeek = 1549044776L
    private val dateLastMonth = 1547143976L
    private val firstWeekInYear = 1546366376L

    private val weekInMilliseconds = 604800L

    @Mock
    lateinit var currentCalendar: Calendar

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        Mockito.`when`(this.currentCalendar.get(Calendar.DAY_OF_MONTH)).thenReturn(10)
        Mockito.`when`(this.currentCalendar.get(Calendar.WEEK_OF_YEAR)).thenReturn(6)
        Mockito.`when`(this.currentCalendar.get(Calendar.MONTH)).thenReturn(1)
        Mockito.`when`(this.currentCalendar.get(Calendar.YEAR)).thenReturn(2019)
    }

    @Test
    fun yearTotalIsPopulatedWithCurrentYearsTotalSpend() {
        var shops = ArrayList<Shop>()
        shops.add(Shop("A", 2.0f, Timestamp(dateLastYear, 0)))
        shops.add(Shop("B", 1.0f, Timestamp(currentTestDate, 0)))

        val shopCalculations = ShopCalculations(shops,currentCalendar)

        assertThat(shopCalculations.yearTotal, `is`(equalTo(1.0)))
    }

    @Test
    fun currentWeekTotalIsPopulatedWithCurrentWeeksTotalSpend() {
        var shops = ArrayList<Shop>()
        shops.add(Shop("A", 2.0f, Timestamp(dateLastWeek, 0)))
        shops.add(Shop("B", 1.0f, Timestamp(currentTestDate, 0)))

        val shopCalculations = ShopCalculations(shops, currentCalendar)

        assertThat(shopCalculations.currentWeekTotal, `is`(equalTo(1.0)))
    }

    @Test
    fun currentMonthTotalIsPopulatedWithCurrentMonthsTotalSpend() {
        var shops = ArrayList<Shop>()
        shops.add(Shop("A", 2.0f, Timestamp(dateLastMonth, 0)))
        shops.add(Shop("B", 1.0f, Timestamp(currentTestDate, 0)))

        val shopCalculations = ShopCalculations(shops, currentCalendar)

        assertThat(shopCalculations.currentWeekTotal, `is`(equalTo(1.0)))
    }

    @Test
    fun weeklyAverageCalculatesAverageSpend() {
        var shops = ArrayList<Shop>()
        var week = firstWeekInYear
        shops.add(Shop("A", 2.0f, Timestamp(week, 0)))
        shops.add(Shop("B", 3.0f, Timestamp(week + (weekInMilliseconds * 1), 0)))
        shops.add(Shop("C", 4.0f, Timestamp(week + (weekInMilliseconds * 2), 0)))
        shops.add(Shop("D", 5.0f, Timestamp(week + (weekInMilliseconds * 3), 0)))
        shops.add(Shop("E", 6.0f, Timestamp(week + (weekInMilliseconds * 4), 0)))
        shops.add(Shop("F", 7.0f, Timestamp(week + (weekInMilliseconds * 5), 0)))

        val shopCalculations = ShopCalculations(shops, currentCalendar)

        assertThat(shopCalculations.weeklyAverage, `is`(equalTo(4.5)))
    }

    @Test
    fun monthlyAverageIsCalculatedTakingDayOfMonthIntoAccount() {
        var shops = ArrayList<Shop>()
        shops.add(Shop("A", 50.0f, Timestamp(dateLastMonth, 0)))
        shops.add(Shop("B", 50.0f, Timestamp(currentTestDate, 0)))

        val shopCalculations = ShopCalculations(shops, currentCalendar)

        assertThat(shopCalculations.monthlyAverage, `is`(equalTo(50.0)))
    }

}