package com.mmk.chat.util

/**
 * Created by mirzemehdi on 1/28/21
 */

//Based on these state either there will be progress or data in the ui or no data view
sealed class UiState {
    object Loading : UiState()
    object HasData : UiState()
    object NoData : UiState()
}
