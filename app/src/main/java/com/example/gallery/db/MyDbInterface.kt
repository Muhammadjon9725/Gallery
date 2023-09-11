package com.example.gallery.db

import com.example.gallery.Mytype

interface MyDbInterface {
    fun getAllImages():ArrayList<Mytype>
    fun  addImage(mytype: Mytype)

}