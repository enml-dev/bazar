package com.enml.bazar.data.model.interfaces

interface IItem {
    var title: String
    var description: String
    var price: Int
    // New or Used
    var status: String
    var category : ICategory?
}