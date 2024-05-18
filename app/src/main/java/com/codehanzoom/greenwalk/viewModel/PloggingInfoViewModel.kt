package com.codehanzoom.greenwalk.viewModel

import androidx.lifecycle.ViewModel

class PloggingInfoViewModel : ViewModel() {
    companion object {
        var trashCount: Int = 0
        var imageUrl: String = ""
        var point: Int = 0
    }

    // trashCount getter / setter
    fun getTrashCount(): Int {
        return trashCount
    }

    fun setTrashCount(_trashCount: Int) {
        trashCount = _trashCount
    }

    // imageUrl getter / setter
    fun getImageUrl(): String {
        return imageUrl
    }

    fun setImageUrl(_imageUrl: String) {
        imageUrl = _imageUrl
    }

    // point getter / setter
    fun getPoint(): Int {
        return point
    }

    fun setPoint(_point: Int) {
        point = _point
    }

}