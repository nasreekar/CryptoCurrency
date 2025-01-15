# Crypto.com Android Take-Home Test

## Overview
This repository contains my implementation of the [Crypto.com](https://crypto.com/) Android Take-Home Test. 
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

<!--
## Observations and Learnings

### Architectural Choices
- **Dispatcher Handling**: Dispatchers are handled in the repository implementation (data layer) rather than in the use cases. This keeps the domain layer pure and focused solely on business logic, while the data layer manages data-fetching and processing details.
- **Dependency Injection with Koin**:
    - **`module {}`**: Used to define the dependencies Koin will provide.
    - **`get()`**: Used to retrieve instances of dependencies.  
    - **`factory {}`**: Creates a new object instance whenever requested.
    - **`single {}`**: Creates a single object instance that is reused whenever requested.
    - Example:
      ```kotlin
      single<ICurrencyRepository> { ICurrencyRepositoryImpl(get(), get()) }
      ```
      Here, Koin is instructed to provide an instance of the repository and its implementation.

### AI Tools Used
- Installed **GitHub Copilot** in Android Studio to explore its capabilities, especially for auto-completion of new composables and comments. It provided a reasonable amount of suggestions during the process.

-->

## Installation and Usage

<!--
### Prerequisites
- Android Studio Electric Eel or higher
- Kotlin 1.8+
-->

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

## Observations/Future Improvements About the Test
- The problem statement emphasized reusability and Google’s recommended architecture. I followed this by implementing the app using **Jetpack Compose** and **MVVM architecture**.
- Although multi-module architecture was not a requirement, I implemented it to demonstrate scalability and modularity best practices. Given the simplicity of the problem statement—displaying five buttons and handling actions—I opted for an app, data, and domain module structure. This setup maintains a clean separation of concerns, ensuring better testability and reusability. While this structure is sufficient for smaller projects, it can be further enhanced by adopting a feature-based module structure in larger applications. Feature modules offer benefits like faster build times, improved scalability, and pluggability, making the architecture adaptable to growing business needs.
- The use of **Koin** simplified dependency injection. It was new to me, but I found it easier to set up and with significantly less boilerplate code than Hilt and Dagger, which I have used.
- In the current project, the data module organizes files logically (e.g., DAO, entity, mapper, etc.) for a Room database as it is the only data source. In future, if we decide to introduce additional data sources like network API, it may become cumbersome. To improve scalability and maintainability, the data module can be restructured into two main sub-packages: persistentData and networkData. This structure clearly separates concerns, making it easier to understand and maintain. 
For example:
```
data
│
├── persistentData
│   ├── dao
│   │   └── CurrencyDao.kt
│   ├── database
│   │   └── CurrencyDatabase.kt
│   ├── entity
│   │   └── CurrencyEntity.kt
│   └── mapper
│       └── CurrencyMapper.kt
│
├── networkData
│   ├── api
│   │   └── CurrencyApi.kt
│   ├── dto
│   │   └── CurrencyDto.kt
│   └── mapper
│       └── CurrencyNetworkMapper.kt
│
└── repository
    └── CurrencyRepositoryImpl.kt
```
- To improve the usage of multiple use cases in ViewModel and prevent it from becoming cluttered, I used [interactor approach](https://github.com/nasreekar/CryptoCurrency/tree/feature/introduce-interactor-for-usecases). This approach groups all use cases into a single class, reducing the number of dependencies injected into the ViewModel making it cleaner and scalable (can simply add a use case to interactor without affecting the view model code)

---

## Screenshots

<img src="https://github.com/user-attachments/assets/3f83f412-88b0-478b-9f56-484f8d1e883b" alt="Landing_Page" width="250" height="500"/>

<img src="https://github.com/user-attachments/assets/80c73a52-8757-4b28-b1a2-e5dcf8ff0871" alt="Delete_Data" width="250" height="500"/>

<img src="https://github.com/user-attachments/assets/0bffa1ea-7db8-45b0-80e6-fd2edd84271c" alt="Insert_Data" width="250" height="500"/>

<img src="https://github.com/user-attachments/assets/a0f40dca-0fa1-4fc2-b3f5-f4be54c8eecb" alt="Empty_Data" width="250" height="500"/>

<img src="https://github.com/user-attachments/assets/0bd67ee0-821f-48bd-8a11-1297be92d705" alt="Crypto_Data" width="250" height="500"/>

<img src="https://github.com/user-attachments/assets/25728932-00dd-47a2-896c-d966c069354a" alt="Crypto_Search_Success" width="250" height="500"/>

<img src="https://github.com/user-attachments/assets/dab2a66f-5d2a-45b6-a951-67bfab46f02e" alt="Crypto_Search_No_Results" width="250" height="500"/>

<img src="https://github.com/user-attachments/assets/b3f315f3-723f-4eab-bb86-14e50343502a" alt="Fiat_Data" width="250" height="500"/>

<img src="https://github.com/user-attachments/assets/ca190121-35a0-49cd-b88a-639d794714f3" alt="All_Data" width="250" height="500"/>

---

## Complete Folder Structure 
Tool: [tree](https://formulae.brew.sh/formula/tree), command: tree -I 'build|.gradle|gradle' -P '*.kt|*.java|*.kts'

![App_Module](https://github.com/user-attachments/assets/ae488f53-530d-4eaa-9fbe-7df8d52ad2d4)


![Data_Module](https://github.com/user-attachments/assets/e6afbb3a-2169-475b-8286-cced308e99e5)


![Domain_Module](https://github.com/user-attachments/assets/12989e7c-8552-4e16-934e-23691658f7bd)


---

Thank you for reviewing this submission! If you have any questions or suggestions, please feel free to reach out.

