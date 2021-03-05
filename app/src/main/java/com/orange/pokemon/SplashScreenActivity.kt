package com.orange.pokemon

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView

@Suppress("DEPRECATION")
class SplashScreenActivity : AppCompatActivity() {

    private var topAnim: Animation? = null
    private var bottomAnim:Animation? = null
    private var appTitle: TextView? = null
    private var appSubtitle:TextView? = null
    private var lottieAnim:LottieAnimationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.splash_screen)

        //Animations
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)

        //Widgets
        lottieAnim = findViewById(R.id.pikachu_logo)
        appTitle = findViewById(R.id.app_title)
        appSubtitle = findViewById(R.id.app_subtitle)

        //assign animation
        lottieAnim?.animation = topAnim
        appTitle?.animation = bottomAnim
        appSubtitle?.animation = bottomAnim

        Handler(Looper.getMainLooper()).postDelayed(
            {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                //Toast.makeText(this, "Hello", Toast.LENGTH_LONG).show()
            },
            3500,
        )

    }
}

