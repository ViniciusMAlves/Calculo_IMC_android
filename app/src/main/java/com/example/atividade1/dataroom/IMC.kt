package com.example.atividade1.dataroom

import androidx.room.*

@Entity(tableName = "resultIMC")
data class IMC(
    @PrimaryKey(autoGenerate = true) var id: Int?,
    @ColumnInfo(name = "name") var name: String?,
    @ColumnInfo(name = "peso") var peso: Int?,
    @ColumnInfo(name = "altura") var altura:  Float?,
    @ColumnInfo(name = "IMC") var imc:  Float?,
    @ColumnInfo(name = "result") var result:  String?
) {
    override fun toString(): String {
        return "$id - $name - $peso - $altura - $imc - $result"
    }
}
