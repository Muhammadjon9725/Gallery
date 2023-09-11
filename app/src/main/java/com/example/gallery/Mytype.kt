package com.example.gallery

class Mytype {
    var id:Int? = 0
    var name:String? = null
    var imageUri:String? = null

    constructor(name: String?, imageUri: String?) {
        this.name = name
        this.imageUri = imageUri
    }

    constructor(id: Int?, name: String?, imageUri: String?) {
        this.id = id
        this.name = name
        this.imageUri = imageUri
    }

}