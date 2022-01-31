# Wunder Mobility Assignment

A project which allows the user to rent a car from map.

used [Flow, coroutines,Dagger hilt, clean code,Retrofit, and more..]

# Built With:

    Kotlin - As a programming language.
    Coroutines - For multithreading while handling requests to the server and local database.
    Dagger hilt - dependency injection framework.
    Retrofit - A type-safe HTTP client for Android and Java
    Multidex - To enable creating multi dex files because of using set of libraries that reached the maximum size of single dex file.
    Model-View-ViewModel(MVVM) - Offers an implementation of observer design pattern.
    Clean Architecture - Applying Clean Architecture and Solid Principles to build a robust, maintainable, and testable application.

# Questions:

1-)Describe possible performance optimizations for your Code? To make the code more scalable i could
have user The abi filter splits and generates APKs specific to the device architectures.
Per-architecture-based APKs are smaller in size since the native code included for the device
architecture is compiled in the specific output APK only. and also using The density filter splits
and generates APKs based on specific device densities and screen sizes. In the density block,
provide a list of desired screen densities and compatible screen sizes that you want to include in
the APK generation.

2-)Which things could be done better, than you’ve done it? 
To use Jetpack Compose as it enables You to use
If statements to choose what should be rendered, and also can use loops to display a view multiple
times.  also use functional methods like filter, map, and many others.