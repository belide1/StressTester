package stresslevelapp.tesside.belidesravan.sresstester

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface StressTestDao {
    @Insert
    suspend fun insertTest(test: StressTestEntity)

    @Query("SELECT * FROM stress_tests ORDER BY id DESC")
    suspend fun getAllTests(): List<StressTestEntity>

    @Delete
    suspend fun delete(stressItem: StressTestEntity)
}

