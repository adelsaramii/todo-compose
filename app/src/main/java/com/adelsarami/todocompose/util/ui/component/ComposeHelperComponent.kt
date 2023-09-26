package com.adelsarami.todocompose.util.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NeverOverScroll(content: @Composable () -> Unit) {
    (CompositionLocalProvider(
        LocalOverscrollConfiguration provides null
    ) {
        content.invoke()
    })
}