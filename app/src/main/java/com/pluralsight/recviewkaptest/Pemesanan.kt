package com.pluralsight.recviewkaptest

data class Pemesanan(
    var nama : String,
    var kategori : String,
    var item : List<ItemPemesanan>
)