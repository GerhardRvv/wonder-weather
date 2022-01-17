
<h1 align="center"> wonder-weather</h1>

## About
The app was made following [MVVM (Model View View-Model)] architecture, with the intention to demonstrate functionality achieve using ViewModels, Retrofit, LiveData and Flow: 

The App will request access to device location(Lat, Long) then it will use that info to obtain the location ID from the closest City and then request the current weather at https://www.metaweather.com/api/ it handles Error, Loading and Success states to control what is displayed in the screen.

## Built with
- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [ktlint](https://ktlint.github.io/) - Anti-bikeshedding Kotlin linter with built-in formatter.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - For asynchronous and more.
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes. 
- [Glide](https://github.com/bumptech/glide) - Glide is a fast and efficient open source media management and image loading framework for Android that wraps media decoding, memory and disk caching, and resource pooling into a simple and easy to use interface..
- [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.
- [Moshi](https://github.com/square/moshi) - A modern JSON library for Kotlin and Java.
- [Moshi Converter](https://github.com/square/retrofit/tree/master/retrofit-converters/moshi) - A Converter which uses Moshi for serialization to and from JSON.
- [Hilt](https://github.com/googlecodelabs/android-hilt) -Dependency injection is a technique widely used in programming and well suited to Android development. By following the principles of dependency injection, you lay the groundwork for a good app architecture..

## License
```
MIT License

Copyright (c) 2022 Gerhard Reyes Vera Vazquez

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
