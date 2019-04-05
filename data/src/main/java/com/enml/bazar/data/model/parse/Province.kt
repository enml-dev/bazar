package com.enml.bazar.data.model.parse

import com.enml.bazar.data.model.interfaces.IProvince
import com.parse.ParseClassName
import com.parse.ParseObject

@ParseClassName("Province")
class Province : ParseObject(), IProvince {

}