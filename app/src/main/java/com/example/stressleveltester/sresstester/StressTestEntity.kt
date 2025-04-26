package com.example.stressleveltester.sresstester

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stress_tests")
data class StressTestEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val heartRate: String,
    val bloodPressure: String,
    val sleepDuration: String,
    val energyLevel: Float,
    val anxietyLevel: Float,
    val moodChanges: String,
    val breathingRate: String,
    val copingMechanism: String,
    val result: String,
    val preventiveMeasures: String,
    val dateTime: String
)
