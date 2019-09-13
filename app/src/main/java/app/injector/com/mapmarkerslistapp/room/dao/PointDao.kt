package app.injector.com.mapmarkerslistapp.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import app.injector.com.mapmarkerslistapp.room.model.Point
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface PointDao {
    @Insert
    fun insert(points : List<Point>)

    @Query("SELECT * FROM Points")
    fun getAll() : Single<List<Point>>

    @Query("DELETE FROM Points")
    fun removeAll()
}