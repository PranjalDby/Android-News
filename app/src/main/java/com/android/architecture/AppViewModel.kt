package com.android.architecture

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.android.architecture.models.ResponseModels
import com.android.architecture.repository.Repository

class AppViewModel(var category:String,var country: String):ViewModel(){
    private var articleRepo:Repository = Repository()
    private lateinit var articleResponseLiveData:LiveData<ResponseModels>
    fun getDashBoardLive():LiveData<ResponseModels>{
        articleResponseLiveData = articleRepo.getDahsBoard(category = category,country)
        return articleResponseLiveData
    }
}