package gohlengann.apps.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import gohlengann.apps.myapplication.model.Character

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initListeners()
    }

    private fun initListeners() {
        findViewById<View>(R.id.btn_check).setOnClickListener {
            checkCharacters(findViewById<EditText>(R.id.txt_word).text.toString())
        }
    }

    private fun checkCharacters(word: String) {
        val list = ArrayList<Character>()
        word.forEach { character ->
            if (list.none { it.character.equals(character, true) }) {
                list.add(Character(character, 1))
            } else {
                val cache = list.find {
                    it.character.equals(character, true)
                }
                cache?.occurrence = (cache?.occurrence ?: 0) + 1
            }
        }

        if (list.filter { it.occurrence == 1 }.size == list.size) {
            Toast.makeText(this, "No repeating characters.", Toast.LENGTH_SHORT).show()
        } else {
            val first = list.maxByOrNull { it.occurrence }!!
            Toast.makeText(this, "Frequent Character is: ${first.character}", Toast.LENGTH_SHORT)
                .show()
        }
    }
}