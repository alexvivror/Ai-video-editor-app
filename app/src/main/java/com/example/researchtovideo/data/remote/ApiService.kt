package com.example.researchtovideo.data.remote

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * API contract for the Research-to-Video backend.
 * Endpoints match the FastAPI server: projects, sources, script,
 * presentation, voice, avatar, render, status, timeline, output.
 */
interface ApiService {

    @GET("health")
    suspend fun healthCheck(): Map<String, String>

    @POST("projects")
    suspend fun createProject(@retrofit2.http.Body body: Map<String, Any?>): Map<String, Any?>

    @GET("projects/{id}/status")
    suspend fun getProjectStatus(@Path("id") projectId: String): Map<String, Any?>

    @GET("projects/{id}/timeline")
    suspend fun getTimeline(@Path("id") projectId: String): Map<String, Any?>

    @GET("projects/{id}/output")
    suspend fun getOutput(@Path("id") projectId: String, @Query("format") format: String? = null): Map<String, Any?>
}
