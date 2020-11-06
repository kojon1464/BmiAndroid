package com.example.firstapplication.bmi.group

import com.example.firstapplication.App
import com.example.firstapplication.R
import java.util.*

class Groups {
    private var groups = LinkedList<Group>()

    init {
        val context = App.getContext()
        groups.add(Group(30.0, context.getString(R.string.bmiGroupNameObese), context.getString(R.string.bmiGroupDesObese), context.getColor(R.color.colorBmiRed), R.drawable.obese))
        groups.add(Group(25.0, context.getString(R.string.bmiGroupNameOver), context.getString(R.string.bmiGroupDesOver), context.getColor(R.color.colorBmiYellow), R.drawable.over))
        groups.add(Group(18.5, context.getString(R.string.bmiGroupNameNormal), context.getString(R.string.bmiGroupDesNormal), context.getColor(R.color.colorBmiGreen), R.drawable.normal))
        groups.add(Group(0.0, context.getString(R.string.bmiGroupNameUnder), context.getString(R.string.bmiGroupDesUnder), context.getColor(R.color.colorBmiBlue), R.drawable.under))
    }

     fun getGroup(bmi: Double): Group {
         for (group in groups) {
             if (group.identifier <= bmi) {
                 return group
             }
         }

         throw Exception("Could not find fitting group (bmi below zero)")
    }
}