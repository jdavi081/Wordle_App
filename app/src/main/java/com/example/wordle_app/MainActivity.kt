package com.example.wordle_app

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import layout.FourLetterWordList
import java.lang.StringBuilder

class MainActivity : AppCompatActivity()
{
    private lateinit var wordToGuess: String
    private var Attempts = 1

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        wordToGuess = FourLetterWordList.getRandomFourLetterWord()

        val Guess = findViewById<TextView>(R.id.guess)
        val GenAnswer = findViewById<TextView>(R.id.answer)
        val NewWord = findViewById<TextView>(R.id.WordGen)
        val UserAnswer = findViewById<EditText>(R.id.enterGuess)
        val ButtonGuess = findViewById<Button>(R.id.button)

        NewWord.text = wordToGuess


        ButtonGuess.setOnClickListener{
            val userWord = UserAnswer.text.toString().uppercase()
            val checkers = checkGuess(userWord)
            val saveText = Guess.text
            val saveAnswer = GenAnswer.text

            Guess.text = StringBuilder(saveText).append("Guess #$Attempts\nGuess #$Attempts Check\n").toString()
            GenAnswer.text = StringBuilder(saveAnswer).append("$userWord\n$checkers\n").toString()


            if(checkers == "0000"){
                ButtonGuess.isEnabled = false
            }
            else if (Attempts == 3){
                ButtonGuess.isEnabled = false
                Toast.makeText(this, "Too many attempts!", Toast.LENGTH_SHORT).show()
                NewWord.visibility = View.VISIBLE
            }
            else{
                Attempts++
            }
        }
    }


    private fun checkGuess(guess: String) : String
    {

        var result = ""
        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            }
            else if (guess[i] in wordToGuess) {
                result += "+"
            }
            else {
                result += "X"
            }
        }
        return result
    }
}