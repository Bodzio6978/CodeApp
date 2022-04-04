package com.gmail.bodziowaty6978.kodyzabka.di

import android.app.Application
import androidx.room.Room
import com.gmail.bodziowaty6978.kodyzabka.feature_code.data.data_source.CodeDatabase
import com.gmail.bodziowaty6978.kodyzabka.feature_code.data.repository.CodeRepositoryImp
import com.gmail.bodziowaty6978.kodyzabka.feature_code.domain.repository.CodeRepository
import com.gmail.bodziowaty6978.kodyzabka.feature_code.domain.use_case.*
import com.gmail.bodziowaty6978.kodyzabka.util.ResourceProvider
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app:Application):CodeDatabase = Room.databaseBuilder(
        app,
        CodeDatabase::class.java,
        CodeDatabase.DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun provideCodeRepository(db:CodeDatabase):CodeRepository = CodeRepositoryImp(db.codeDao)

    @Provides
    @Singleton
    fun provideUseCases(repository:CodeRepository, resourceProvider: ResourceProvider):CodeUseCases = CodeUseCases(
        getCodes = GetCodes(repository),
        deleteCode = DeleteCode(repository),
        insertCode = InsertCode(repository,resourceProvider),
        getCodeById = GetCodeById(repository)
    )

    @Provides
    @Singleton
    fun provideResourceProvider(app:Application):ResourceProvider = ResourceProvider(app)
}