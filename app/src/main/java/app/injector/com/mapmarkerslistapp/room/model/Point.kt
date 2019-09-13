package app.injector.com.mapmarkerslistapp.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Points")
class Point {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
    @ColumnInfo(name = "name")
    var name : String = ""
    @ColumnInfo(name = "latitude")
    var latitude : Double = 0.0
    @ColumnInfo(name = "longitude")
    var longitude : Double = 0.0
    @ColumnInfo(name = "rating")
    var rating : Float = 0.0f
}