package ru.netology.nmedia

import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

class NewEditPostActivityContract : ActivityResultContract<String, String?>() {
    override fun createIntent(context: Context, input: String): Intent =
        Intent(context, NewEditPostActivity::class.java).apply {
            this.putExtra(Intent.EXTRA_TEXT, input)
        }

    override fun parseResult(resultCode: Int, intent: Intent?): String? =
        intent?.getStringExtra(Intent.EXTRA_TEXT)

}