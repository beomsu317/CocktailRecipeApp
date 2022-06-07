# Cocktail Recipe App

<p align="center"> Cocktail Recipe App is an Application based on Modern Android tech-stacks especially focus on Jetpack Compose UI. Fetching data from network and store favorite cocktail in database. </p>

<p align="center"> <img src="media/cocktail_list.png" width="30%">&nbsp;&nbsp;<img src="media/cocktail_info.png" width="30%">&nbsp;&nbsp;<img src="media/search.png" width="30%"> </p>

<img src="media/preview.gif" align="right" width="40%">

## Tech stacks & Libraries

- [Kotlin](https://developer.android.com/kotlin) based, [Kotlin Flow](https://developer.android.com/kotlin/flow) + [Kotlin Coroutines](https://github.com/Kotlin/kotlinx.coroutines) for asynchronous.
- [Material Components](https://github.com/material-components/material-components-android) - Material design library.
- [Dagger Hilt](https://dagger.dev/hilt/) - Dependency injection library.
- [Android Jetpack](https://developer.android.com/jetpack)
  - [Navigation Compose](https://developer.android.com/jetpack/compose/navigation) - Navigate between composables. 
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that is lifecycle aware (didn't destroyed on UI changes).
  - [Room](https://developer.android.com/training/data-storage/room) - ORM which wraps android's native SQLite database.
- [Coil](https://github.com/coil-kt/coil) - Load images from network.
- [Retrofit](https://square.github.io/retrofit/) & [OkHttp3](https://square.github.io/okhttp/) - Construct REST APIs.
- [Kotlinx Serialization](https://github.com/Kotlin/kotlinx.serialization) - Kotlin serialize, deserialize library.
- [Accompanist](https://google.github.io/accompanist/insets/)
  - [System UI Controller](https://google.github.io/accompanist/systemuicontroller/) - To control system UI in composable.
  - [Pager](https://google.github.io/accompanist/pager/) - Paging layouts for Jetpack Compose.
  - [Placeholder](https://google.github.io/accompanist/placeholder/) - Modifier for display 'placeholder' UI while content is loading.
- [Platte](https://developer.android.com/training/material/palette-colors) - To get prominent colors from images.
- [Android Architecture Components](https://developer.android.com/topic/architecture)
  - MVVM Architecture (Declarative UI - ViewModel - Use Case - Model)
  - Repository pattern

## MAD Score

![mad_score](media/mad_score.png)

## Open API

Cocktail Recipe App using the [TheCocktailDB](https://www.thecocktaildb.com) for construct RESTful API.
This DB provides API related drinks and cocktails from around the world.

## License

```
Copyright 2022 beomsu317

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
