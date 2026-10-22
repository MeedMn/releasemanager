package com.project.releasemanager.controller

import com.project.releasemanager.dto.DeployRequest
import com.project.releasemanager.dto.ServiceVersion
import com.project.releasemanager.service.ReleaseService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ReleaseController(private val service: ReleaseService) {

    @PostMapping("/deploy")
    fun deploy(@RequestBody request: DeployRequest): Long =
        service.deploy(request.name, request.version)

    @GetMapping("/services")
    fun services(@RequestParam systemVersion: Long): List<ServiceVersion> =
        service.getServices(systemVersion)
}