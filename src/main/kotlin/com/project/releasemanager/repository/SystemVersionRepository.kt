package com.project.releasemanager.repository

import com.project.releasemanager.model.SystemVersion
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SystemVersionRepository : JpaRepository<SystemVersion, Long> {
    fun findFirstByOrderByNumberDesc(): SystemVersion?
}