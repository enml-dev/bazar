package com.enml.bazar.data.model

import com.parse.ParseClassName
import com.parse.ParseObject
import com.parse.ktx.delegates.intAttribute
import com.parse.ktx.delegates.stringAttribute

@ParseClassName("Item")
class Item : ParseObject () {

    var title: String by stringAttribute()

    var description: String by stringAttribute()

    var price: Int by intAttribute()

    // New or Used
    var status: String by stringAttribute()

    var category : Category?
        get() = get("category") as Category?
        set(value) {
            put("category", value as Category)
        }


}
