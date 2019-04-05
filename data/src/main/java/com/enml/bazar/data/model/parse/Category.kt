package com.enml.bazar.data.model.parse

import com.enml.bazar.data.model.interfaces.ICategory
import com.parse.ParseClassName
import com.parse.ParseObject
import com.parse.ktx.delegates.stringAttribute

@ParseClassName("Category")
class Category : ParseObject(), ICategory {
    override var name : String by stringAttribute()
    override var description : String by stringAttribute()
}