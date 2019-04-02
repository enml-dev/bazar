package com.enml.bazar.data.model

import com.parse.ParseClassName
import com.parse.ParseObject
import com.parse.ktx.delegates.stringAttribute

@ParseClassName("Category")
class Category : ParseObject() {

    var name : String by stringAttribute()
    var description : String by stringAttribute()
}