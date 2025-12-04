package com.gmail.denuelle42.aiprompter.di.modules

import androidx.datastore.core.DataStore
import com.gmail.denuelle42.aiprompter.utils.security.UserPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenProvider @Inject constructor(
    dataStore: DataStore<UserPreferences>
) {

    /**
     * @Volatile means:
     * - Always read the **latest** value written by ANY thread
     * - Prevents the CPU from caching old values
     * - Ensures visibility across threads
     *
     * -> Without @Volatile, one thread could update `token`, but 
     *    another thread (like OkHttp interceptor thread) might still
     *    see the old value due to memory caching.
     */
    @Volatile
    private var token: String? = null

    init {
        /**
         * We launch a coroutine that ALWAYS listens to DataStore changes.
         * Whenever the token in DataStore updates:
         *   → we update `token` in RAM immediately.
         *
         * This means:
         * - tokenProvider.getToken() is O(1)
         * - We never read from disk inside OkHttp
         * - No more runBlocking()
         */
        CoroutineScope(Dispatchers.IO).launch {
            dataStore.data.collect { prefs ->
                token = prefs.token    // atomic WRITE of a single variable
            }
        }
    }

    /**
     * Atomic READ:
     * - Reading a single variable is safe
     * - @Volatile guarantees the latest value is returned
     */
    fun getToken(): String? = token
}
