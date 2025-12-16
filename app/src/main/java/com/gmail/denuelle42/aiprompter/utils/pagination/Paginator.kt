package com.gmail.denuelle42.aiprompter.utils.pagination

interface Paginator<Key, Item> {
   suspend fun loadNextItems()
   suspend fun reset()
}