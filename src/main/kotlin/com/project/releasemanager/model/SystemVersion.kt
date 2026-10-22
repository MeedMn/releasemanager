package com.project.releasemanager.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class SystemVersion(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val number: Long = 0
)