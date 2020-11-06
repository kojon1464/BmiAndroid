package com.example.firstapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.firstapplication.bmi.group.Group
import com.example.firstapplication.bmi.group.Groups
import com.example.firstapplication.databinding.ActivityDescriptionBinding
import java.util.*

class DescriptionActivity : AppCompatActivity() {

    private lateinit var group: Group
    private lateinit var binding: ActivityDescriptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bmi = intent.getDoubleExtra("bmi", -1.0)
        group = Groups().getGroup(bmi)
        binding = ActivityDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            groupIV.setImageResource(group.image)
            groupTV.text =  String.format(Locale.US, group.name, bmi)
            descriptionTV.text = group.description
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}