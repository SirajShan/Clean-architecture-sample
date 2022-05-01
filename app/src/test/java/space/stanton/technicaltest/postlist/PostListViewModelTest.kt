package space.stanton.technicaltest.postlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockito_kotlin.mock
import io.reactivex.Single
import junit.framework.TestCase
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito
import space.stanton.technicaltest.model.Data
import space.stanton.technicaltest.model.DataState
import space.stanton.technicaltest.model.PostItem
import space.stanton.technicaltest.network.interactor.PostInteractor
import space.stanton.technicaltest.network.model.PostsItem

class PostListViewModelTest {

    private lateinit var viewModel: PostListViewModel

    private val mockInteractor = mock<PostInteractor> {}

    @Rule
    @JvmField
    val rxSchedulersOverrideRule: RxSchedulersOverrideRule = RxSchedulersOverrideRule()

    @Rule
    @JvmField
    val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Test
    fun `get post item list succeeds`() {
        // given
        Mockito.`when`(mockInteractor.getPosts()).thenReturn(Single.just(arrayListOf(PostsItem("",1,"",1))))

        // when
        viewModel = PostListViewModel(mockInteractor)

        // then
        Assert.assertEquals(Data<ArrayList<PostsItem>>(dataState = DataState.SUCCESS, arrayListOf(PostsItem("",1,"",1)),message =""), viewModel.posts.value)
    }
}