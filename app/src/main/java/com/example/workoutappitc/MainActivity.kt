package com.example.workoutappitc

import android.graphics.ImageDecoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.workoutappitc.adapters.WeekEventsAdapter
import com.example.workoutappitc.api.RetrofitClient
import com.example.workoutappitc.api.UtilsApi
import com.example.workoutappitc.api.models.Events
import com.example.workoutappitc.api.models.Root
import com.example.workoutappitc.api.models.User
import com.example.workoutappitc.api.models.Week
import com.example.workoutappitc.interfaces.OnItemClickListener
import okhttp3.internal.Util
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), OnItemClickListener {

    private lateinit var headerImage: ImageView
    private lateinit var userGreetings: TextView
    private lateinit var userName: TextView
    private lateinit var userLevel: TextView
    private lateinit var currentDate: TextView
    private lateinit var recyclerHeader: RecyclerView
    private lateinit var currentDay: TextView
    private lateinit var imageWorkout: ImageView
    private lateinit var workoutText: TextView
    private lateinit var imageMindset: ImageView
    private lateinit var mindsetText: TextView
    private lateinit var recipeText: TextView
    private lateinit var imageRecipe: ImageView
    private lateinit var user: User
    private lateinit var workoutAdvice: TextView
    private lateinit var userPoints: TextView
    private lateinit var workoutTime: TextView
    private lateinit var dumbbellText: TextView
    private lateinit var weightText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        headerImage = findViewById(R.id.iv_header)
        userGreetings = findViewById(R.id.tv_greetings)
        userName = findViewById(R.id.tv_user_name)
        userLevel = findViewById(R.id.tv_user_level)
        currentDate = findViewById(R.id.tv_date)
        recyclerHeader = findViewById(R.id.header_recycler)
        currentDay = findViewById(R.id.tv_day)
        imageWorkout = findViewById(R.id.iv_workout)
        workoutText = findViewById(R.id.tv_workout)
        imageMindset = findViewById(R.id.iv_mindset)
        mindsetText = findViewById(R.id.tv_mindset)
        recipeText = findViewById(R.id.tv_recipe)
        imageRecipe = findViewById(R.id.iv_recipe)
        user = UtilsData.user
        workoutAdvice = findViewById(R.id.tv_workout_advice)
        userPoints = findViewById(R.id.tv_points)
        workoutTime = findViewById(R.id.tv_time)
        dumbbellText = findViewById(R.id.tv_dumbbell)
        weightText = findViewById(R.id.tv_weight)

        fillHeader()
        getCurrentDate()
        getWeekEventsLocal()
        firstDay()
    }

    private fun getUserWithRetrofit() {
        val api = RetrofitClient.getRetrofitClient.create(UtilsApi::class.java)
        val call = api.getUser("1")
        call.enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.d("onFailure", t.message.toString())
            }
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    Log.d("MainActivityState", "Retrofit Started")
                    AppData.user = response.body()
                    fillHeader()
                }
            }
        })
    }

   private fun fillHeader() {
       Glide.with(this).load(user.image).into(headerImage)
       when(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) {
           in 5..11 -> userGreetings.text = "Good Morning"
           in 12..16 -> userGreetings.text = "Good Afternoon"
           else  -> userGreetings.text = "Good Evening"
       }
       userName.text = user.name
       userLevel.text = user.workout_level
       userPoints.text = user.points.toString()
   }

    private fun getCurrentDate() {
        val calendar = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat("MMMM")
        val monthName = simpleDateFormat.format(calendar.time)
        val currentDay = calendar.get(Calendar.DAY_OF_MONTH)
        currentDate.text = "$currentDay" + " " + monthName
    }

    private fun getWeekEventsWithRetrofit() {
        val api = RetrofitClient.getRetrofitClient.create(UtilsApi::class.java)
        val call = api.getWeekEvents()
        call.enqueue(object : Callback<Week> {
            override fun onFailure(call: Call<Week>, t: Throwable) {
                Log.d("onFailure", t.message.toString())
            }
            override fun onResponse(call: Call<Week>, response: Response<Week>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    val adapter = WeekEventsAdapter(data!!.events, this@MainActivity, this@MainActivity)
                    recyclerHeader.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
                        this.adapter = adapter
                    }
                }
            }
        })
    }

    private fun getWeekEventsLocal() {
        val adapter = WeekEventsAdapter(UtilsData.week.events, this@MainActivity, this@MainActivity)
        recyclerHeader.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            this.adapter = adapter
        }
    }

    private fun firstDay() {
        Glide.with(this).load(UtilsData.rootOne.workout.background).into(imageWorkout)
        workoutText.text = UtilsData.rootOne.workout.title
        Glide.with(this).load(UtilsData.rootOne.mindset.background).into(imageMindset)
        mindsetText.text = UtilsData.rootOne.mindset.title
        Glide.with(this).load(UtilsData.rootOne.recipe.background).into(imageRecipe)
        recipeText.text = UtilsData.rootOne.recipe.title
        workoutAdvice.text = UtilsData.rootOne.workout_tip
        dumbbellText.text = UtilsData.rootOne.workout.equipment[0].item
        weightText.text = UtilsData.rootOne.workout.equipment[1].item
    }

    override fun onItemClickListener(event: Events) {
        currentDay.text = event.day
        when(event.eventId) {
            1 -> {
                Glide.with(this).load(UtilsData.rootOne.workout.background).into(imageWorkout)
                workoutText.text = UtilsData.rootOne.workout.title
                Glide.with(this).load(UtilsData.rootOne.mindset.background).into(imageMindset)
                mindsetText.text = UtilsData.rootOne.mindset.title
                Glide.with(this).load(UtilsData.rootOne.recipe.background).into(imageRecipe)
                recipeText.text = UtilsData.rootOne.recipe.title
                workoutAdvice.text = UtilsData.rootOne.workout_tip
                workoutTime.text = UtilsData.rootOne.workout.time.toString() + "min"
                dumbbellText.text = "5kx2"
                weightText.text = "scale"
            }
            2 -> {
                Glide.with(this).load(UtilsData.rootTwo.workout.background).into(imageWorkout)
                workoutText.text = UtilsData.rootTwo.workout.title
                Glide.with(this).load(UtilsData.rootTwo.mindset.background).into(imageMindset)
                mindsetText.text = UtilsData.rootTwo.mindset.title
                Glide.with(this).load(UtilsData.rootTwo.recipe.background).into(imageRecipe)
                recipeText.text = UtilsData.rootTwo.recipe.title
                workoutAdvice.text = UtilsData.rootTwo.workout_tip
                workoutTime.text = UtilsData.rootTwo.workout.time.toString() + "min"
                dumbbellText.text = "5kx2"
                weightText.text = "scale"
            }
            3 -> {
                Glide.with(this).load(UtilsData.rootThree.workout.background).into(imageWorkout)
                workoutText.text = UtilsData.rootThree.workout.title
                Glide.with(this).load(UtilsData.rootThree.mindset.background).into(imageMindset)
                mindsetText.text = UtilsData.rootOne.mindset.title
                Glide.with(this).load(UtilsData.rootThree.recipe.background).into(imageRecipe)
                recipeText.text = UtilsData.rootThree.recipe.title
                workoutAdvice.text = UtilsData.rootThree.workout_tip
                workoutTime.text = UtilsData.rootThree.workout.time.toString() + "min"
                dumbbellText.text = "5kx2"
                weightText.text = "scale"
            }
            else -> {
                imageWorkout.setImageResource(R.drawable.ic_baseline_image_24)
                imageMindset.setImageResource(R.drawable.ic_baseline_image_24)
                imageRecipe.setImageResource(R.drawable.ic_baseline_image_24)
                workoutText.text = ""
                mindsetText.text = ""
                recipeText.text = ""
                workoutAdvice.text = ""
                workoutTime.text = ""
                dumbbellText.text = ""
                weightText.text = ""
            }
        }
    }

    private fun getWeekDataWithRetrofit(event: Events) {
        val api = RetrofitClient.getRetrofitClient.create(UtilsApi::class.java)
        val call = api.getSelectedEvent(event.eventId.toString())
        call.enqueue(object : Callback<Root> {
            override fun onFailure(call: Call<Root>, t: Throwable) {
                Log.d("onFailure", t.message.toString())
            }
            override fun onResponse(call: Call<Root>, response: Response<Root>) {
                if (response.isSuccessful) {
                    Log.d("onResponse", response.code().toString())
                }
            }
        })
    }
}