package com.test.anotatudo.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "Notes")
@Parcelize
class Note (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nateTitle: String,
    val nobody: String
        ) : Parcelable