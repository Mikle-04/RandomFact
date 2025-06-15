**RandomFact** — это Android-приложение, которое показывает случайные бесполезные факты из публичного API и позволяет сохранять их в избранное. Построено с использованием **чистой архитектуры**, **Koin**, **Retrofit**, **Room**, **Jetpack Compose** и **Flow**.

---

## 🚀 Функционал

✅ Получение случайного факта из сети  
✅ Добавление факта в избранное  
✅ Просмотр всех избранных фактов  
✅ Удаление отдельных фактов или очистка всего списка  
✅ Bottom Navigation для переключения экранов  
✅ Реактивный UI на базе Jetpack Compose

---

## 🗂️ Архитектура

Проект реализует **Clean Architecture**, разделённую на 3 слоя:

- **Data**
    - `FactApi` — Retrofit API для получения фактов
    - `FactEntity` + `FactDao` + `FactDatabase` — Room для локального хранения
    - `FactRepositoryImpl` — реализация репозитория

- **Domain**
    - `Fact` — доменная модель
    - `FactRepository` — интерфейс репозитория
    - `GetRandomFactUseCase` — бизнес-логика

- **Presentation**
    - `FactViewModel` — управление состоянием экрана
    - `FactScreen` — UI для фактов
    - `FavoritesScreen` — UI для избранного
    - `MainScreen` — контейнер с Bottom Navigation

---

## ⚙️ Используемые технологии

- 🔹 **Koin** — Dependency Injection
- 🔹 **Retrofit** — сетевые запросы
- 🔹 **Room** — локальная база данных
- 🔹 **Kotlin Coroutines & Flow** — асинхронность и реактивность
- 🔹 **Jetpack Compose** — современный UI toolkit
- 🔹 **Navigation Compose** — навигация между экранами

---

## 🏗️ Запуск проекта

1️⃣ Клонируйте репозиторий:
```bash
git clone https://github.com/yourusername/RandomFact.git