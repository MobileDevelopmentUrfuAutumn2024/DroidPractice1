package ru.urfu.droidpractice1.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import ru.urfu.droidpractice1.data.ArticleRepository
import ru.urfu.droidpractice1.entity.Article

class ArticleViewModel(
    private val id: String,
    private val repository: ArticleRepository = ArticleRepository,
) : ViewModel() {

    val uiState = repository.articleFlow(id)
        .map { State.Content(it) }
        .stateIn(viewModelScope, SharingStarted.Lazily, State.Loading)

    fun like() = repository.like(id)

    fun dislike() = repository.dislike(id)

    fun view() = repository.view(id)

    sealed interface State {

        data object Loading : State

        data class Content(val article: Article) : State
    }

    class Factory(
        private val id: String,
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ArticleViewModel(id) as T
        }
    }
}
