package com.example.randomfact.presentation

import com.example.randomfact.domain.model.Fact
import com.example.randomfact.domain.usecase.GetRandomFactUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FactViewModel(
    private val getRandomFactUseCase: GetRandomFactUseCase
) {
    // Текущее состояние факта
    private val _fact = MutableStateFlow<Fact?>(null)
    val fact: StateFlow<Fact?> = _fact

    // Текущее состояние загрузки
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading


}