package com.example.kiranaapp

import androidx.lifecycle.LiveData
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query

@Dao
interface KiranaAppDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item:KiranaItems):Long

    @Delete
    suspend fun delete(item:KiranaItems):Int

    @Query("SELECT * FROM kirana_items")
    fun getAllKiranaItems():LiveData<List<KiranaItems>>

}
@Entity(tableName = "kirana_items")
data class KiranaItems (
    @ColumnInfo(name = "itemName")
    var itemName: String,
    @ColumnInfo(name = "itemQty")
    var itemQty: Int,
    @ColumnInfo(name = "itemPrice")
    var itemPrice: Int
){
    @PrimaryKey(autoGenerate = true)
    var id : Int? = null
}
