package com.enml.bazar.data.model.parse

import com.enml.bazar.data.model.interfaces.ISubCategory
import com.parse.ParseClassName
import com.parse.ParseObject

@ParseClassName("SubCategory")
class SubCategory : ParseObject(), ISubCategory {
}