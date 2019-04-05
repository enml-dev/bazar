package com.enml.bazar.data.model.parse

import com.enml.bazar.data.model.interfaces.IItemType
import com.parse.ParseClassName
import com.parse.ParseObject

@ParseClassName("ItemType")
class ItemType : ParseObject (), IItemType {

}