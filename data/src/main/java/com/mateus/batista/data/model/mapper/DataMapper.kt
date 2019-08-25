package com.mateus.batista.data.model.mapper

abstract class DataMapper<in T, out D> {
    abstract fun toDomain(data: T) : D
}