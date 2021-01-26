package com.example.workoutappitc

import com.example.workoutappitc.api.models.*

class UtilsData {
    companion object {
        val user = User("Paula Clothier", "https://images.pexels.com/photos/416809/pexels-photo-416809.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260", 1345, "Beginner")
        private val eventsList: List<Events> = listOf(Events("Monday", 1, true), Events("Tuesday", null), Events("Wednesday", null)
        , Events("Thursday", 2), Events("Friday", null), Events("Saturday", null), Events("Sunday", 3))
        val week = Week(18, 5, eventsList)

        val equipmentOne: List<Equipment> = listOf(Equipment("matt"), Equipment("scale"))
        val workoutOne = Workout("Fire Me Up Cardio", "https://images.pexels.com/photos/3771815/pexels-photo-3771815.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260", 15, equipmentOne)
        val recipeOne = Recipe("Cold Soba Shrimp Salad", "https://images.pexels.com/photos/953710/pexels-photo-953710.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260")
        val mindsetOne = Mindset("Meditation for beginners","https://images.pexels.com/photos/4431101/pexels-photo-4431101.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260")
        val rootOne = Root(workoutOne, recipeOne, mindsetOne, "If you ever feel any pain during an exercise, stop and check to see if you are doing it correctly. Getting an injury...", true)

        val equipmentTwo: List<Equipment> = listOf(Equipment("No equipment"), Equipment("No equipment"))
        val workoutTwo = Workout("Body Combat", "https://images.pexels.com/photos/927437/pexels-photo-927437.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500", 24, equipmentTwo)
        val recipeTwo = Recipe("Protein pancakes", "https://images.pexels.com/photos/376464/pexels-photo-376464.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260")
        val mindsetTwo = Mindset("Meditation for beginners","https://images.pexels.com/photos/3326366/pexels-photo-3326366.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260")
        val rootTwo = Root(workoutTwo, recipeTwo, mindsetTwo, "If you ever feel any pain during an exercise, stop and check to see if you are doing it correctly. Getting an injury...", false)

        val equipmentThree: List<Equipment> = listOf(Equipment("matt"), Equipment("weights"))
        val workoutThree = Workout("Push Through Shoulders & Back", "https://images.pexels.com/photos/1552242/pexels-photo-1552242.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260", 30, equipmentThree)
        val recipeThree = Recipe("Broccolini Pasta", "https://images.pexels.com/photos/5745757/pexels-photo-5745757.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260")
        val mindsetThree = Mindset("Meditation for beginners","https://images.pexels.com/photos/2908175/pexels-photo-2908175.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260")
        val rootThree = Root(workoutThree, recipeThree, mindsetThree, "If you ever feel any pain during an exercise, stop and check to see if you are doing it correctly. Getting an injury...", false)
    }
}