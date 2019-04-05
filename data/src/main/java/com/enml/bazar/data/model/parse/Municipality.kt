package com.enml.bazar.data.model.parse

import com.enml.bazar.data.model.interfaces.IMunicipality
import com.parse.ParseClassName
import com.parse.ParseObject

@ParseClassName("Municipality")
class Municipality : ParseObject (), IMunicipality {

}