package com.enml.bazar.data.model.parse

import com.enml.bazar.data.model.interfaces.ICategory
import com.enml.bazar.data.model.interfaces.IItem
import com.parse.ParseClassName
import com.parse.ParseObject
import com.parse.ktx.delegates.intAttribute
import com.parse.ktx.delegates.stringAttribute

@ParseClassName("Item")
class Item : ParseObject (), IItem {

    override var title: String by stringAttribute()

    override var description: String by stringAttribute()

    override var price: Int by intAttribute()

    // New or Used
    override var status: String by stringAttribute()

    override var category : ICategory?
        get() = get("category") as ICategory?
        set(value) {
            put("category", value as ICategory)
        }


}
