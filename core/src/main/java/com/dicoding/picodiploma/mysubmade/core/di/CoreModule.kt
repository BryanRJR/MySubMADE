package com.dicoding.picodiploma.mysubmade.core.di

import androidx.room.Room
import com.dicoding.picodiploma.mysubmade.core.local.room.MovieDatabase
import com.dicoding.picodiploma.mysubmade.core.remote.RemoteDataSource
import com.dicoding.picodiploma.mysubmade.core.remotenet.ApiService
import com.dicoding.picodiploma.mysubmade.core.repository.IMovieRepository
import com.dicoding.picodiploma.mysubmade.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<MovieDatabase>().movieDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("submission".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            MovieDatabase::class.java, "movie.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        val hostname = "api.movie.id"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/lhU4GGHQ4GpKVKc2XN0fcEyOFlH1NicVfnd8+3oPI2U=")
            .build()

        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { com.dicoding.picodiploma.mysubmade.core.local.DataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IMovieRepository> {
        com.dicoding.picodiploma.mysubmade.core.source.MovieRepository(
            get(),
            get(),
            get()
        )
    }
}