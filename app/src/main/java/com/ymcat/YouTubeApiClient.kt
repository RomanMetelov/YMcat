package com.ymcat


import android.content.Context
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import com.google.api.client.http.HttpTransport
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.youtube.YouTube

class YouTubeApiClient(credential: GoogleAccountCredential?, context: Context) {
    private var mYouTube: YouTube

    init {
        val httpTransport: HttpTransport = NetHttpTransport()
        mYouTube = YouTube.Builder(
            httpTransport,
            JacksonFactory.getDefaultInstance(),
            credential
        )
            .setApplicationName(context.getString(R.string.app_name))
            .build()
    } // TODO: Implement methods to fetch playlists, etc.
}
