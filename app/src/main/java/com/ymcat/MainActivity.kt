package com.ymcat

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import com.google.api.services.youtube.YouTubeScopes
import java.util.*


class MainActivity : AppCompatActivity() {


    private val RC_SIGN_IN = 9001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize GoogleSignInOptions
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        // Build the GoogleSignInClient with the options
        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        // Start the Google Sign-In activity when the user clicks a button
        findViewById<View>(R.id.sign_in_button).setOnClickListener {
            signIn(
                mGoogleSignInClient
            )
        }
    }

    private fun signIn(mGoogleSignInClient: GoogleSignInClient) {
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // ...
        if (requestCode == RC_SIGN_IN) {
            // ...
            if (requestCode === RC_SIGN_IN) {
                val task: Task<GoogleSignInAccount> =
                    GoogleSignIn.getSignedInAccountFromIntent(data)
                try {
                    // Successfully signed in
                    val account: GoogleSignInAccount = task.getResult(ApiException::class.java)
                    val credential: GoogleAccountCredential = GoogleAccountCredential
                        .usingOAuth2(this, Collections.singleton(YouTubeScopes.YOUTUBE_READONLY))
                        .setSelectedAccount(account.account)
                    val youTubeApiClient = YouTubeApiClient(credential, this)
                    // TODO: Fetch playlists using youTubeApiClient and update UI.

                    // Successfully signed in
                    // TODO: Handle the successful sign-in, proceed to fetch playlists.
                } catch (e: ApiException) {
                    // Failed to sign in
                    Log.w("TAG_YMCAT", "signInResult:failed code=" + e.statusCode)
                }
            }
        }
    }


}
