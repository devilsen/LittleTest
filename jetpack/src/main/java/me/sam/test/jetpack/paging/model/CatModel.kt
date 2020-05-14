package me.sam.test.jetpack.paging.model

data class CatModel(
        val id: String,
        val url: String,
        val height: Int,
        val width: Int,
        val breeds: List<Breed>
)