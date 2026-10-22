package com.project.releasemanager.service

import com.project.releasemanager.dto.ServiceVersion
import com.project.releasemanager.model.FullSystemVersion
import com.project.releasemanager.model.SystemVersion
import com.project.releasemanager.repository.FullSystemVersionRepository
import com.project.releasemanager.repository.SystemVersionRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.RuntimeException

@Service
class ReleaseService(
    private val systemVersions: SystemVersionRepository,
    private val fullVersions: FullSystemVersionRepository
) {
    @Transactional
    fun deploy(name: String, version: Int): Long {
        val latest = systemVersions.findFirstByOrderByNumberDesc()
        val services = mutableMapOf<String, Int>()
        if (latest != null) {
            fullVersions.findBySystemVersionNumber(latest.number)
                .forEach { services[it.serviceName] = it.serviceVersion }
            if (services[name] == version) return latest.number
        }

        services[name] = version
        val newVersion = systemVersions.save(SystemVersion())
        fullVersions.saveAll(services.map { FullSystemVersion(newVersion, it.key, it.value) })
        return newVersion.number
    }

    fun getServices(systemVersion: Long): List<ServiceVersion> {
        if (!systemVersions.existsById(systemVersion)) {
            throw RuntimeException("System version $systemVersion not found")
        }
        return fullVersions.findBySystemVersionNumber(systemVersion)
            .map { ServiceVersion(it.serviceName, it.serviceVersion) }
            .sortedBy { it.name }
    }
}
