package com.mashup.mobalmobal.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.flowable
import com.mashup.mobalmobal.data.dto.PostDto
import com.mashup.mobalmobal.data.paging.PostPagingSource
import com.mashup.mobalmobal.network.service.PostService
import io.reactivex.Flowable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

class PostRepository @Inject constructor(private val service: PostService) {

    @ExperimentalCoroutinesApi
    fun getPosts(): Flowable<PagingData<PostDto>> {
        val config = PagingConfig(
            initialLoadSize = 30,
            pageSize = 30
        )
        return Pager(config) { PostPagingSource(service) }.flowable
    }
}