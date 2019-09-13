package app.injector.com.mapmarkerslistapp.repositories

import app.injector.com.mapmarkerslistapp.room.model.Point
import io.reactivex.Completable
import io.reactivex.Single

interface PointRepository {
    fun insert(points : List<Point>) : Completable
    fun getAll() : Single<List<Point>>
    fun removeAll() : Completable
}