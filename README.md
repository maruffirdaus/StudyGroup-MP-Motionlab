# Assignment Week-3

<image src="https://github.com/user-attachments/assets/b06183b7-4c94-4b3e-a81d-600387ff3a35" height="512px"> &nbsp;
<image src="https://github.com/user-attachments/assets/40a030c7-fac0-42e9-a806-005d5d3f8682" height="512px"> &nbsp;
<image src="https://github.com/user-attachments/assets/5bee9b5e-5663-4c75-93e4-27728134d365" height="512px">

## Implementasi Styling UI

- Fonts

  Pada project ini, terdapat dua font yang digunakan, yaitu Sofia dan Onest.

  Sofia digunakan pada bagian title dari LargeTopAppBar() pada fungsi HomeAppBar().
  
  ```
  // Deklarasi font Sofia yang terletak di ~/ui/theme/Type.kt
  
  val sofiaFamily = FontFamily(
    Font(R.font.sofia_regular, FontWeight.Normal)
  )
  ```

  ```
  // Penggunaan font Sofia pada bagian title dari LargeTopAppBar() yang terletak di ~/ui/home/HomeAppBar.kt
  
  Text(
    "Storyspace",
    fontFamily = sofiaFamily
  )
  ```  

  Onest digunakan sebagai font default aplikasi.

  ```
  // Deklarasi font Onest sekaligus pengaplikasiannya pada Typography yang terletak di ~/ui/theme/Type.kt
  
  val onestFamily = FontFamily(
      Font(R.font.onest_black, FontWeight.Black),
      Font(R.font.onest_bold, FontWeight.Bold),
      Font(R.font.onest_extrabold, FontWeight.ExtraBold),
      ...
  )
  
  val baseline = Typography()
  
  val Typography = Typography(
      displayLarge = baseline.displayLarge.copy(fontFamily = onestFamily),
      displayMedium = baseline.displayMedium.copy(fontFamily = onestFamily),
      displaySmall = baseline.displaySmall.copy(fontFamily = onestFamily),
      ...
  )
  ```

  ```
  // Pengaplikasian Typography pada fungsi StoryspaceTheme() yang terletak di ~/ui/theme/Theme.kt
  
  @Composable
  fun StoryspaceTheme(
      ...
  ) {
      ...
  
      MaterialTheme(
          ...
          typography = Typography,
          ...
      )
  }
  ```

  ```
  // Penggunaan StoryspaceTheme() sebagai tema dari aplikasi yang terletak di ~/ui/MainActivity.kt
  
  setContent {
    StoryspaceTheme(...) {
      MainScreen()
    }
  }
  ```

- Colors

  Pada project ini, terdapat warna-warna hasil generate dari tool https://material-foundation.github.io/material-theme-builder/.

  Pengaplikasian warna pada project ini dilakukan dengan cara menyisipkannya langsung ke tema sehingga warna-warna pada komponen UI akan langsung disesuaikan tanpa perlu mengubahnya satu per satu.

  Untuk dokumentasi mengenai warna pada Material Design 3 dapat dilihat di https://m3.material.io/styles/color/.

  ```
  // Deklarasi warna versi dark yang terletak di ~/ui/theme/Color.kt
  
  val primaryDark = Color(0xFFB1D18A)
  val onPrimaryDark = Color(0xFF1F3701)
  val primaryContainerDark = Color(0xFF354E16)
  ...
  ```

  ```
  // Deklarasi warna versi light yang terletak di ~/ui/theme/Color.kt 
  
  val primaryLight = Color(0xFF4C662B)
  val onPrimaryLight = Color(0xFFFFFFFF)
  val primaryContainerLight = Color(0xFFCDEDA3)
  ...
  ```

  ```
  // Deklarasi DarkColorSheme menggunakan fungsi darkColorScheme() yang terletak di ~/ui/theme/Theme.kt
  // Warna versi dark digunakan sebagai parameter dari fungsi darkColorScheme().
  
  private val DarkColorScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    ...
  )
  ```

  ```
  // Deklarasi LightColorSheme menggunakan fungsi lightColorScheme() yang terletak di ~/ui/theme/Theme.kt
  // Warna versi light digunakan sebagai parameter dari fungsi lightColorScheme().
  
  private val LightColorScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    ...
  )
  ```

  ```
  // Pengaplikasian DarkColorScheme dan LightColorScheme pada fungsi StoryspaceTheme() yang terletak di ~/ui/theme/Theme.kt
  
  @Composable
  fun StoryspaceTheme(
      darkTheme: Boolean = isSystemInDarkTheme(),
      ...
  ) {
      val colorScheme = when {
          ...
  
          darkTheme -> DarkColorScheme
          else -> LightColorScheme
      }
  
      MaterialTheme(
          colorScheme = colorScheme,
          ...
      )
  }
  ```

  ```
  // Penggunaan StoryspaceTheme() sebagai tema dari aplikasi yang terletak di ~/ui/MainActivity.kt
  // Parameter `dynamicColor = false` berfungsi untuk menonaktifkan fitur dynamic theme
  
  setContent {
    StoryspaceTheme(dynamicColor = false) {
      MainScreen()
    }
  }
  ```

  Tambahan:

  - Warna-warna yang disisipkan ke tema juga dapat digunakan secara manual.

    ```
    // Penggunaan komponen warna tema secara manual yang terletak di ~/ui/search/SearchContent.kt
    
    Text(
      "Trending",
      ...
      color = MaterialTheme.colorScheme.onSurfaceVariant,
      ...
    )
    ```

## Bottom Navigation Bar

Pada project ini, terdapat page Home, Search, dan Profile. Untuk navigasinya sendiri dilakukan menggunakan komponen NavigationBar().

```
// Penggunaan NavigationBar() yang terletak di ~/ui/MainScreen.kt

@Composable
fun MainScreen() {
    var selectedItem by remember { mutableIntStateOf(0) }
    ...
    Scaffold(
        ...
        bottomBar = {
            val items = listOf("Home", "Search", "Profile")
            val selectedIcons = ...
            val unselectedIcons = ...
            NavigationBar {
                items.forEachIndexed { index, _ ->
                    NavigationBarItem(
                        selected = selectedItem == index,
                        onClick = { selectedItem = index },
                        icon = if (selectedItem == index) selectedIcons[index] else unselectedIcons[index]
                    )
                }
            }
        },
        ...
    ) { innerPadding ->
        when (selectedItem) {
            0 -> {
                HomeContent(...)
            }

            1 -> {
                SearchContent(...)
            }

            2 -> {
                ProfileContent(...)
            }
        }
    }
}
```
