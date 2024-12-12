# Assignment Week-3

<image src="https://github.com/user-attachments/assets/b06183b7-4c94-4b3e-a81d-600387ff3a35" height="512px"> &nbsp;
<image src="https://github.com/user-attachments/assets/40a030c7-fac0-42e9-a806-005d5d3f8682" height="512px"> &nbsp;
<image src="https://github.com/user-attachments/assets/5bee9b5e-5663-4c75-93e4-27728134d365" height="512px">

## Implementasi Styling UI

- Fonts

  Pada project ini, terdapat dua font yang digunakan, yaitu Sofia dan Onest. Sofia digunakan pada bagian title dari LargeTopAppBar() yang terletak di file /ui/home/HomeAppBar.kt.
  
  Cuplikan kode /ui/theme/Type.kt untuk penggunaan font Sofia:
  ```
  val sofiaFamily = FontFamily(
    Font(R.font.sofia_regular, FontWeight.Normal)
  )
  ```

  Cuplikan kode /ui/home/HomeAppBar.kt untuk penggunaan font Sofia:
  ```
  Text(
    "Storyspace",
    fontFamily = sofiaFamily
  )
  ```  

  Selanjutnya, font Onest digunakan sebagai font default aplikasi.

  Cuplikan kode /ui/theme/Type.kt untuk penggunaan font Onest sebagai font default yang nantinya akan terintegrasi dengan theme dari aplikasi:
  ```
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

  Variabel Typography yang sudah dimodifikasi font-nya di atas akan digunakan pada StoryspaceTheme() untuk diterapkan sebagai tipografi default. Berikut cuplikan kodenya yang terletak di file /ui/theme/Theme.kt.
  ```
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

  StoryspaceTheme() di atas akan digunakan sebagai theme dari aplikasi dengan cara memanggilnya pada ui/MainActivity.kt seperti berikut.
  ```
  setContent {
    StoryspaceTheme(...) {
      MainScreen()
    }
  }
  ```

- Colors

  Pada project ini, terdapat warna-warna hasil generate dari tool https://material-foundation.github.io/material-theme-builder/ yang akan digunakan sebagai theme dari aplikasi.

  Berikut ini adalah cuplikan warna-warna untuk light mode yang terletak di file /ui/theme/Color.kt.
  ```
  val primaryLight = Color(0xFF4C662B)
  val onPrimaryLight = Color(0xFFFFFFFF)
  val primaryContainerLight = Color(0xFFCDEDA3)
  ...
  ```

  Warna-warna hasil generate tadi akan diintegrasikan ke theme aplikasi. Pertama, deklarasikan terlebih dahulu DarkColorScheme dan LightColorScheme. Berikut cuplikan pendeklarasian LightColorScheme yang terletak di file /ui/theme/Theme.kt (untuk pendeklarasian DarkColorScheme dapat dilihat lebih lanjut pada file terkait).
  ```
  private val LightColorScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    ...
  )
  ```

  Setelah itu, DarkColorScheme dan LightColorScheme akan digunakan pada StoryspaceTheme() seperti berikut (kode terletak di /ui/theme/Theme.kt).
  ```
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

  StoryspaceTheme() dapat digunakan dengan cara yang sama seperti pada penjelasan bagian Fonts sebelumnya. Berikut ini adalah cuplikan kodenya yang terletak di file /ui/MainActivity.kt (sedikit catatan, penggunaan `dynamicColor = false` pada cuplikan di bawah dimaksudkan agar fitur dynamic theme tidak aktif sehingga warna pada theme akan mengikuti warna yang telah dideklarasikan sebelumnya).
  ```
  setContent {
    StoryspaceTheme(dynamicColor = false) {
      MainScreen()
    }
  }
  ```

  Selain itu, masing-masing komponen warna di atas juga dapat digunakan dengan cara seperti berikut (kode terletak di file /ui/search/SearchContent.kt).
  ```
  Text(
    "Trending",
    ...
    color = MaterialTheme.colorScheme.onSurfaceVariant,
    ...
  )
  ```

  Pengintegrasian warna dengan theme dimaksudkan agar penggunaan warna menjadi lebih praktis (akan langsung diterapkan pada setiap komponen UI tanpa harus menyesuaikannya lagi berdasarkan peran dari masing-masing komponen warna). Untuk dokumentasi peran dari tiap-tiap komponen warna dapat dilihat di https://m3.material.io/styles/color/roles/.

## Bottom Navigation Bar

Pada project ini, terdapat page Home, Search, dan Profile. Untuk navigasinya sendiri dilakukan menggunakan komponen NavigationBar(). Berikut adalah cuplikan kode penggunaan NavigationBar() yang terletak di file /ui/MainScreen.kt.
```
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
                HomeContent(modifier = Modifier.padding(innerPadding))
            }

            1 -> {
                SearchContent(modifier = Modifier.padding(innerPadding))
            }

            2 -> {
                ProfileContent(modifier = Modifier.padding(innerPadding))
            }
        }
    }
}
```
