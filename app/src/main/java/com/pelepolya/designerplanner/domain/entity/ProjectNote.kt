package com.pelepolya.designerplanner.domain.entity

data class ProjectNote(
    val id: Int,
    val title: String,
    val body: String,
    val status: ProjectStatus
)