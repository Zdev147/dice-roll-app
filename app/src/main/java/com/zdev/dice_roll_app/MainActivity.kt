package com.zdev.dice_roll_app

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var diceImg: ImageView


    val diceFaces = arrayOf(
        R.drawable.dice_1,
        R.drawable.dice_2,
        R.drawable.dice_3,
        R.drawable.dice_4,
        R.drawable.dice_5,
        R.drawable.dice_6
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        diceImg = findViewById(R.id.dice_img)

        val rollDiceBtn: Button = findViewById(R.id.roll_dice_btn)
        rollDiceBtn.setOnClickListener {
            rollDiceBtn.isEnabled = false
            rollDice {
                rollDiceBtn.isEnabled = true
            }
        }
    }

    private fun rollDice(callback: () -> Unit) {
        val random = Random()
        val timer = Timer()
        val countLimit = random.nextInt(30)+1

        var count = 0

        val tt: TimerTask = object : TimerTask() {
            override fun run() {
                count++

                runOnUiThread {
                    diceImg.setImageResource(diceFaces[random.nextInt(6)])
                }

                if (count == countLimit) {
                    timer.cancel()
                    runOnUiThread {
                        callback()
                    }
                }
            }
        }

        timer.scheduleAtFixedRate(tt, 0, 50)
    }

}