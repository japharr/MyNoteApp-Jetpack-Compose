package com.japharr.mynoteapp.model


import java.time.LocalDateTime
import java.util.*

data class Note  constructor(
    val id: UUID = UUID.randomUUID(),
    val title: String,
    val description: String,
    val entryDate: LocalDateTime = LocalDateTime.now()
)
