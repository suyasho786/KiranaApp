package com.example.kiranaapp

class KiranaRepository (private val db:KiranaDataBase){
    suspend fun insert(items: KiranaItems)=db.getKiranDao().insert(items)
    suspend fun delete(items: KiranaItems)=db.getKiranDao().delete(items)

    fun getAllItems()=db.getKiranDao().getAllKiranaItems()
}