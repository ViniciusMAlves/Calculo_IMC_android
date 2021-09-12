package com.example.atividade1.dataroom

import androidx.room.*

@Dao
interface ImcDao {
    @Query("SELECT * FROM resultIMC WHERE id = :usuarioId")
    fun getImc(usuarioId: String): IMC

    @Query("SELECT * FROM resultIMC")
    fun getAll(): List<IMC>

    @Query("SELECT name, result FROM resultIMC")
    fun getResult(): List<IMC>

    @Query("SELECT * FROM resultIMC WHERE name LIKE :name")
    fun loadAllByName(name: String): List<IMC>

    @Insert
    fun insertAll(vararg imc: IMC)

    @Delete
    fun delete(imc: IMC)
}
