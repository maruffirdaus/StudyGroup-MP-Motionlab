# Assignment Week-4

Pada Jetpack Compose terdapat library navigation-compose yang dapat digunakan untuk melakukan navigasi antarlayar. Terdapat dua pendekatan untuk melakukan navigasi menggunakan library navigation-compose.

1. Menggunakan String untuk pendefinisian rute.

    - Metode ini hanya memerlukan dependency `androidx.navigation:navigation-compose`.

    - Terdapat dua komponen utama yang digunakan, yaitu NavHost dan NavController. NavHost berfungsi untuk mendefinisikan semua rute navigasi yang dapat dilakukan dan NavController berfungsi untuk mengontrol navigasi.

    - Contoh penggunaan:

        1. siapkan sebuah fungsi;

            ```
            @Composable
            fun NavGraph() {

            }
            ```

        2. siapkan NavController;

            ```
            @Composable
            fun NavGraph() {
                val navController = rememberNavController()
            }
            ```

        3. siapkan NavHost beserta semua rute navigasi;

            ```
            // HomeScreen.kt

            @Composable
            fun HomeScreen(
                onProfilePictureClick: () -> Unit // Sebuah parameter function type yang dapat dipanggil ketika memerlukan aksi menuju ke ProfileScreen
                onBack(): () -> Unit // Sebuah parameter function type yang dapat dipanggil ketika memerlukan aksi kembali ke destinasi/layar sebelumnya
            ) {
                ...
            }
            ```

            ```
            // ProfileScreen.kt

            @Composable
            fun ProfileScreen(
                username: String,
                onBack(): () -> Unit
            ) {
                ...
            }
            ```

            ```
            // NavGraph.kt

            @Composable
            fun NavGraph() {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestionation = "home" // Destinasi awal
                ) {
                    composable("home") { // Rute home berisi HomeScreen
                        val username = "maruffirdaus"

                        HomeScreen(
                            onProfilePictureClick = {
                                navController.navigate("profile/$username") // Navigasi ke rute profile dengan menambahkan argument username
                            },
                            onBack = {
                                navController.popBackStack() // Merupakan method dari NavController yang berfungsi untuk kembali ke destinasi/layar sebelumnya
                            }
                        )
                    }

                    composable("profile/{username}") { entry -> // Rute profile berisi ProfileScreen dengan username sebagai argument
                        val username = entry.arguments?.getString("username") // Cara untuk mendapatkan argument username

                        ProfileScreen(
                            username = username,
                            onBack = {
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
            ```

        4. fungsi NavGraph dapat dipanggil di MainActivity/entry point pertama dari aplikasi

2. Menggunakan Object/Data Class untuk pendefinisian rute.

    - Metode ini memerlukan dependency `androidx.navigation:navigation-compose` dan `org.jetbrains.kotlinx:kotlinx-serialization-json` serta plugin `org.jetbrains.kotlin.plugin.serialization`.

    - Metode ini mirip dengan metode sebelumnya, hanya saja pendefinisian rute menggunakan Object/Data Class.

    - Contoh penggunaan:

        1. siapkan satu atau beberapa Object/Data Class untuk pendefinisian rute;

            ```
            @Serializable
            object Home

            @Serializable
            data class Profile(
                val username: String
            )
            ```

        2. siapkan NavHost;

            ```
            @Composable
            fun NavGraph() {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestionation = Home
                ) {
                    composable<Home> { // Pendefinisian rute tidak lagi menggunakan String
                        val username = "maruffirdaus"

                        HomeScreen(
                            onProfilePictureClick = {
                                navController.navigate( // Navigasi menggunakan Object/Data Class
                                    Profile(
                                        username = username // Argument bisa disisipkan ke attribute Data Class
                                    )
                                )
                            },
                            onBack = {
                                navController.popBackStack()
                            }
                        )
                    }

                    composable<Profile> { entry ->
                        val args = entry.toRoute<Profile>() // Cara untuk mendapatkan argument

                        ProfileScreen(
                            username = args.username,
                            onBack = {
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
            ```