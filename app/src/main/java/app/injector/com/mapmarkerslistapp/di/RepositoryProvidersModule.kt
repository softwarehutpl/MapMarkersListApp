package app.injector.com.mapmarkerslistapp.di

import android.content.Context
import androidx.room.Room
import app.injector.com.mapmarkerslistapp.repositories.PointRepository
import app.injector.com.mapmarkerslistapp.repositories.impl.PointRepositoryImpl
import app.injector.com.mapmarkerslistapp.room.dao.PointDao
import app.injector.com.mapmarkerslistapp.room.db.MapPropertiesDB
import dagger.Module
import dagger.Provides

@Module
open class RepositoryProvidersModule  {

    @Provides
    fun provideMapPropertiesDB(context : Context) : MapPropertiesDB {
        return Room.databaseBuilder(context.applicationContext, MapPropertiesDB::class.java, MapPropertiesDB.DB_NAME).build()
    }

    @Provides
    fun providePointDao(mapPropertiesDB: MapPropertiesDB) : PointDao {
        return mapPropertiesDB.pointDao()
    }

    @Provides
    fun providePointRepository(pointDao: PointDao) : PointRepository {
        return PointRepositoryImpl(pointDao)
    }
}