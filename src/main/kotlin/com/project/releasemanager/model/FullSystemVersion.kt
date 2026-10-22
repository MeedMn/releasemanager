package com.project.releasemanager.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne

@Entity
class FullSystemVersion (
    @ManyToOne
    val systemVersion: SystemVersion,
    val serviceName: String,
    val serviceVersion: Int,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
)