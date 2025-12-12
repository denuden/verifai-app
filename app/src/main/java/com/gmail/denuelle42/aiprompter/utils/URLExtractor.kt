package com.gmail.denuelle42.aiprompter.utils

import com.gmail.denuelle42.aiprompter.data.remote.models.fact_check.LinkMetaDataModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup
import javax.inject.Inject
import javax.inject.Named

val urlRegex = "(https?://[\\w\\-._~:/?#\\[\\]@!$&'()*+,;=%]+)".toRegex()

/**
 * detect URLs
 */

fun extractUrl(text: String): String? {
    return urlRegex.find(text)?.value
}

/**
 * Gets MetaData
 */
@ViewModelScoped
class LinkPreviewFetcher @Inject constructor(
    @Named("preview") private val client: OkHttpClient
) {
    private val cache = mutableMapOf<String, LinkMetaDataModel>()

    suspend fun fetch(url: String): LinkMetaDataModel? {
        cache[url]?.let { return it }

        return withContext(Dispatchers.IO) {
            try {
                // 1. Follow redirects & get final URL
                val finalUrl = resolveRedirect(url) ?: url

                // 2. Fetch HTML
                val html = Jsoup.connect(finalUrl)
                    .userAgent("Mozilla/5.0")
                    .ignoreContentType(true)
                    .get()

                // 3. Parse OG metadata
                val meta = LinkMetaDataModel(
                    url = url,
                    finalUrl = finalUrl,
                    image = html.select("meta[property=og:image]").attr("content").ifBlank { null },
                    title = html.select("meta[property=og:title]").attr("content").ifBlank { null },
                    description = html.select("meta[property=og:description]").attr("content")
                        .ifBlank { null }
                )

                cache[url] = meta
                meta
            } catch (e: Exception) {
                null
            }
        }
    }

    private suspend fun resolveRedirect(url: String): String? =
        withContext(Dispatchers.IO) {
            try {
                val request = Request.Builder().url(url).build()
                client.newCall(request).execute().use { it.request.url.toString() }
            } catch (e: Exception) {
                null
            }
        }
}
