package app.injector.com.mapmarkerslistapp.repositories.impl

import app.injector.com.mapmarkerslistapp.repositories.PointRepository
import app.injector.com.mapmarkerslistapp.room.dao.PointDao
import app.injector.com.mapmarkerslistapp.room.model.Point
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class PointRepositoryImpl @Inject constructor(private val pointDao: PointDao) : PointRepository {

    override fun insert(points: List<Point>): Completable {
        return Completable.fromAction { pointDao.insert(points) }
    }

    override fun getAll(): Single<List<Point>> {
        return pointDao.getAll()
    }

    override fun removeAll(): Completable {
        return Completable.fromAction { pointDao.removeAll() }
    }
}