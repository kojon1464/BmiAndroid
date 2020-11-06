package com.example.firstapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.firstapplication.bmi.BmiImperial
import com.example.firstapplication.bmi.BmiInterface
import com.example.firstapplication.bmi.BmiMetric
import com.example.firstapplication.bmi.Unit
import com.example.firstapplication.bmi.group.Groups
import com.example.firstapplication.databinding.ActivityMainBinding
import com.example.firstapplication.history.History
import com.example.firstapplication.history.HistoryEntry
import java.time.Instant
import java.util.*

const val STATE_BMI = "bmi"
const val STATE_COLOR = "bmiColor"
const val STATE_UNIT = "unit"

class MainActivity : AppCompatActivity() {

    private lateinit var unit: Unit
    private lateinit var bmiCalculator: BmiInterface
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        unit = Unit.METRIC
        setUnits(unit)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(STATE_BMI, binding.bmiTV.text.toString())
        outState.putInt(STATE_COLOR, binding.bmiTV.currentTextColor)
        outState.putSerializable(STATE_UNIT, unit)

        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        unit = savedInstanceState.getSerializable(STATE_UNIT) as Unit
        setUnits(unit)

        binding.bmiTV.text = savedInstanceState.getString(STATE_BMI)
        binding.bmiTV.setTextColor(savedInstanceState.getInt(STATE_COLOR))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.bmi_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.unitsMI -> {
                changeUnits()
                return true
            }
            R.id.historyMI -> {
                val intent = Intent(this, HistoryActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun changeUnits() {
        unit = when (unit) {
            Unit.METRIC -> Unit.IMPERIAL
            else -> Unit.METRIC
        }
        setUnits(unit)
    }

    private fun setUnits(unit: Unit) {
        bmiCalculator = when (unit) {
            Unit.IMPERIAL -> {
                BmiImperial()
            }
            else -> {
                BmiMetric()
            }
        }
        binding.apply {
            heightTV.text = getString(R.string.height, unit.height_unit)
            massTV.text = getString(R.string.mass, unit.mass_unit)
            heightET.error = null
            massET.error = null
        }
    }

    fun count(view: View) {
        binding.apply {

            if (heightET.text.isBlank()) {
                heightET.error = getString(R.string.height_is_empty)
            } else if (heightET.text.toString().toInt() < bmiCalculator.getMinHeight()) {
                heightET.error = getString(R.string.height_lower) + bmiCalculator.getMinHeight()
            } else if (heightET.text.toString().toInt() > bmiCalculator.getMaxHeight()) {
                heightET.error = getString(R.string.height_grater) + bmiCalculator.getMaxHeight()
            }

            if (massET.text.isBlank()) {
                massET.error = getString(R.string.mass_is_empty)
            } else if (massET.text.toString().toInt() < bmiCalculator.getMinWeight()) {
                massET.error = getString(R.string.height_lower) + bmiCalculator.getMinWeight()
            } else if (massET.text.toString().toInt() > bmiCalculator.getMaxWeight()) {
                massET.error = getString(R.string.height_grater) + bmiCalculator.getMaxWeight()
            }

            if (heightET.error.isNullOrEmpty() && massET.error.isNullOrEmpty()){
                val bmi = bmiCalculator.calculate(heightET.text.toString().toInt(), massET.text.toString().toInt())
                bmiTV.text = String.format(Locale.US, "%.2f", bmi)
                bmiTV.setTextColor(Groups().getGroup(bmi).color)

                saveInHistory(massET.text.toString().toInt(), heightET.text.toString().toInt(), bmi)
            }
        }
    }

    private fun saveInHistory(mass: Int, height: Int, bmi: Double) {
        val history = History.getHistory()

        history.add(0, HistoryEntry(mass, height, bmi, Instant.now().toEpochMilli(), unit))
        if(history.size > 10) {
            history.removeAt(history.size - 1)
        }

        History.saveHistory(history)
    }

    fun viewDescription(view: View) {
        val bmi = binding.bmiTV.text.toString().toDoubleOrNull()
        if(bmi == null) {
            val toast = Toast.makeText(this, getString(R.string.calculate_bmi), Toast.LENGTH_SHORT)
            toast.show()
            return
        }

        val intent = Intent(this, DescriptionActivity::class.java)
        intent.putExtra("bmi", bmi)
        startActivity(intent)
    }
}