# Crypto.com Android Take-Home Test

## Overview
This repository contains my implementation of the Crypto.com Android Take-Home Test. 
The task required creating a reusable `CurrencyListFragment` to display Crypto and Fiat currencies with various functionalities. 
Below are the details of the implementation and my approach.

---

## Implementation Details

### Frameworks and Tools Used
- **Jetpack Compose**: For building UI.
- **Multi-Module Architecture**: Implemented to showcase knowledge of modularization, even though it was not required for the given problem statement.
- **Coroutines and Flows**: For asynchronous operations and managing data streams.
- **Koin**: Used for dependency injection.
- **Unit and UI Tests**: Written to ensure the reliability and functionality of the application.
- **Use Cases**: Added in the domain layer to interact with the repository, ensuring a clean separation of business logic and data handling

### Features Implemented
- A `CurrencyListScreen` capable of displaying currencies
    - Crypto: Displays a list of cryptocurrencies.
    - Fiat: Displays a list of fiat currencies.
    - All: Displays a list of all currencies.
    - Provide search functionality with a cancellable UI.
- A `DemoActivity` with buttons to:
    - Clear data in the local database.
    - Insert new data into the local database.
    - Toggle between Currency List A (Crypto) and Currency List B (Fiat).
    - Display purchasable currencies.
- An empty state view when the list is empty or when any .
- Ensured that all IO operations run off the UI thread to maintain smooth execution.

---

## Observations and Learnings

### Architectural Choices
- **Dispatcher Handling**: Dispatchers are handled in the repository implementation (data layer) rather than in the use cases. This keeps the domain layer pure and focused solely on business logic, while the data layer manages data-fetching and processing details.
- **Dependency Injection with Koin**:
    - **`module {}`**: Used to define the dependencies that Koin will provide.
    - **`get()`**: Used to retrieve instances of dependencies.  
    - **`factory {}`**: Creates a new object instance every time it is requested.
    - **`single {}`**: Creates a single object instance that is reused whenever requested.
    - Example:
      ```kotlin
      single<ICurrencyRepository> { ICurrencyRepositoryImpl(get(), get()) }
      ```
      Here, Koin is instructed to provide an instance of the repository and its implementation.

### AI Tools Used
- Installed **GitHub Copilot** in Android Studio to explore its capabilities, especially for debugging test cases. It provided a reasonable amount suggestions during the process.

---

## Installation and Usage

### Prerequisites
- Android Studio Electric Eel or higher
- Kotlin 1.8+

### Steps to Run the Project
1. Clone the repository:
   ```bash
   git clone https://github.com/nasreekar/CryptoCurrency.git
   ```
2. Open the project in Android Studio.
3. Sync the project with Gradle files.
4. Run the application on an emulator or a physical device.

### Running Tests
To execute the Unit and UI tests, run the following command in Android Studio:
```bash
./gradlew testDebugUnitTest
```
Or run the tests directly from the `test` and `androidTest` directories.

---

## Observations About the Test
- The problem statement emphasized reusability and Google’s recommended architecture. I followed this by implementing the app using **Jetpack Compose** and **MVVM architecture**.
- Though multi-module architecture was not a requirement, I implemented it to demonstrate scalability and modularity best practices.
- The use of **Koin** simplified dependency injection. It was new to me but I found it easier to set up and with significantly less boilerplate code compared to Hilt and Dagger, which I have used in the past.

---

## Future Improvements

---

Thank you for reviewing this submission! If you have any questions or suggestions, please feel free to reach out.

