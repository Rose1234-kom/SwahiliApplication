package com.example.swahiliapplication.ui

import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.swahiliapplication.R
import com.example.swahiliapplication.Repository
import kotlin.coroutines.CoroutineContext

class LessonListActivity: AppCompatActivity() {

    lateinit var repository: Repository

    val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_list)
    }
}