package com.project.releasemanager.repository

import com.project.releasemanager.model.FullSystemVersion
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FullSystemVersionRepository : JpaRepository<FullSystemVersion, Long> {
    fun findBySystemVersionNumber(number: Long): List<FullSystemVersion>
}