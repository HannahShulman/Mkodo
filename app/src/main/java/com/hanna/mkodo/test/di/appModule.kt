import android.app.Application
import androidx.room.Room
import com.hanna.mkodo.test.data.datasource.db.AppDatabase
import com.hanna.mkodo.test.data.datasource.db.DrawDao
import com.hanna.mkodo.test.data.datasource.network.Api
import com.hanna.mkodo.test.data.repositories.DrawsRepository
import com.hanna.mkodo.test.presenter.viewmodels.DrawDetailsViewModel
import com.hanna.mkodo.test.presenter.viewmodels.DrawsListViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { provideDatabase(androidApplication()) }
    single { provideDrawDao(get()) }
    single { DrawsRepository(get(), get()) }
    single { Api(get()) }
    viewModel { DrawsListViewModel(get()) }
    viewModel { DrawDetailsViewModel(get()) }
}

fun provideDatabase(application: Application): AppDatabase {
    return Room.databaseBuilder(application, AppDatabase::class.java, "draws_database")
        .fallbackToDestructiveMigration()
        .build()
}

fun provideDrawDao(database: AppDatabase): DrawDao {
    return database.drawDao()
}