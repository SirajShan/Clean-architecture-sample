package space.stanton.technicaltest.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import space.stanton.technicaltest.network.PostsAPI
import space.stanton.technicaltest.network.repository.CommentsRepository
import space.stanton.technicaltest.network.repository.PostRepository
import space.stanton.technicaltest.network.repositoryimpl.CommentsRepositoryImpl
import space.stanton.technicaltest.network.repositoryimpl.PostRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun providePostsApi(retrofit: Retrofit): PostsAPI = retrofit.create(PostsAPI::class.java)

    @Provides
    @Singleton
    fun providesPostRepository(postsAPI: PostsAPI): PostRepository {
        return PostRepositoryImpl(postsAPI)
    }

    @Provides
    @Singleton
    fun providesCommentRepository(postsAPI: PostsAPI) : CommentsRepository=  CommentsRepositoryImpl(postsAPI)
}