package uk.co.philharper.foodbudget.entity

class Properties(
        var locations: MutableList<String> = mutableListOf(),
        var weeklyBudget: Int = 0
) {
    var id = ""
}