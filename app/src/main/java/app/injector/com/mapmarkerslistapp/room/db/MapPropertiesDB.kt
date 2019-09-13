package app.injector.com.mapmarkerslistapp.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import app.injector.com.mapmarkerslistapp.room.dao.PointDao
import app.injector.com.mapmarkerslistapp.room.model.Point

@Database(entities = arrayOf(Point::class), version = 1)
abstract class MapPropertiesDB  : RoomDatabase() {

    abstract fun pointDao() : PointDao

    companion object {
        val DB_NAME = "map_points.db"
    }
}