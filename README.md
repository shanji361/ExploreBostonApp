# Boston City Tour App

## Features

- **Home Screen (Intro)**
  - Welcome screen introducing the Boston City Tour.
  - Button to start the tour and navigate to the Categories screen.

- **Categories Screen**
  - Lists main categories such as Museums, Parks, and Restaurants.
  - Clicking a category navigates to the corresponding List screen.
  - TopAppBar includes a Home button to return directly to Home, clearing the back stack.

- **List Screen**
  - Displays a list of locations for the selected category (e.g., “All Museums”).
  - Uses `NavController.navigate()` with structured route strings.
  - Clicking a location navigates to the Detail screen.

- **Detail Screen**
  - Shows detailed information about a selected location (e.g., MIT Museum).
  - Receives both **category** and **location ID** as navigation arguments.
  - Proper back stack behavior: users can navigate back to the list or directly to Home.
  - Returning Home clears the navigation stack using `popUpTo(..., inclusive = true)`.

- **Navigation**
  - Cleanly implemented with `rememberNavController()` and a separate `NavGraph.kt`.
  - Structured route strings managed with `createRoute()` helper functions in a sealed `Screen` class.
  - Correctly passes and retrieves both String and Int arguments via `NavType`.
  - Supports forward and back navigation.

- **UI Components**
  - Logic disables the back button after reaching Home via a full navigation cycle to prevent unintended navigation.

---

## How to Run 


1. Clone this repository:
   ```
   git clone https://github.com/shanji361/ExploreBostonApp.git
   ```
2. Open the project in Android Studio.

3. Run the app on an emulator or a physical Android device.
   

## Reference 

- Consulted AI in understanding how to pass data (category and location ID) through NavController. Specifically, guidance was provided on using backStackEntry.arguments?.getString() and backStackEntry.arguments?.getInt() to retrieve values inside a composable destination. AI also assisted in how to use createRoute() helper functions. This ensures consistency and prevents typos in route strings. AI clarified that arguments should be included in the route string as placeholders: "locations/{category}" "detail/{category}/{locationId}" It also explained that the placeholders must match the names used in navArgument() when retrieving values. 
- AI Misunderstandings: used arguments?.getString("locationId") for an Int argument, suggest  remember { backStackEntry.arguments?...} which would freeze the argument and prevent recomposition if the route changes.
