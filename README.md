# Custom Gallery

**Custom Gallery** is a small demo application that presents a modern approach to Android development with up to date tech-stack. The goal of the project is to demonstrate best practices using modern Android development tools and presenting an architecture that is scalable, maintainable, and testable.

## Project characteristics

- 100% [Kotlin](https://kotlinlang.org/)
- Model-View-ViewModel
- Repository Pattern
- A single activity architecture
- [Android Jetpack](https://developer.android.com/jetpack)
- Reactive UI
- Static analysis tools
- [Gradle Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html)
- Material Design
- [GitHub Actions](https://github.com/features/actions)

## Libraries

- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - For managing background threads.
- [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-flow/) - A cold asynchronous data stream that sequentially emits values.
- Jetpack
    - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Store UI-related data that isn't destroyed on app rotations.
    - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Build data objects that notify views when the underlying database changes.
    - [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - Create a UI that automatically responds to lifecycle events.
- [Glide](https://bumptech.github.io/glide/) - An image loading and caching library.

## Architecture

Custom Gallery is based on [MVVM](https://developer.android.com/jetpack/guide#recommended-app-arch) architecture, fetching data from the network and integrating persisted data in the database via repository pattern.


## Getting started

There are a few ways to open this project.

**Android Studio**

1. Android Studio -> File -> New -> Project from Version Control
2. Enter `https://github.com/thezeeshan92/Custom-Gallery.git` into URL field

**Command line + Android Studio**

1. Run `git clone https://github.com/thezeeshan92/Custom-Gallery.git`
2. Android Studio -> File -> Open


## ScreenShots

Gallery Fragment displays all folders and the number of images and videos it contains

![Screenshot_20220615-171209](https://user-images.githubusercontent.com/12368592/173824474-8c51b9fb-6230-4238-a1b1-c8fe8ac06e7d.jpg)

Displays all images in a given folder, in this case "All Images"

![Screenshot_20220615-171218](https://user-images.githubusercontent.com/12368592/173824613-2d3ebc23-0e1e-4af3-a88a-ab97a73631ac.jpg)

