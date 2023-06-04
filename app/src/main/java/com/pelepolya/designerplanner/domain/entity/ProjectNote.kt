package com.pelepolya.designerplanner.domain.entity

data class ProjectNote(
    val title: String,
    val body: String,
    val status: ProjectStatus
)