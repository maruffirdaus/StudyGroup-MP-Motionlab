# Assignment Week-1

## Login screen

Berikut hal-hal yang dapat dilakukan di login screen (`MainActivity`):

- Login

  Login dapat dilakukan dengan cara memasukkan username dan password pada text field yang tersedia dan setelah itu menekan tombol Login.

  <image src="https://github.com/user-attachments/assets/98a5b65e-3eac-4bc2-a301-4847c8d5224c" height="512px">

  Apabila username belum terdaftar atau password salah, maka akan muncul pemberitahuan sebagai berikut dan akan muncul pula pemberitahuan pada Logcat.

  <image src="https://github.com/user-attachments/assets/bf53d164-8866-4c49-a582-ecaeb79fa6f3" height="512px"> &nbsp; <image src="https://github.com/user-attachments/assets/b2080d67-65f2-4f1e-989e-fd59902b2457" height="512px">

  Apabila login berhasil, maka aplikasi akan berpindah ke home screen (`HomeActivity`). Pada home screen akan muncul pemberitahuan sebagai berikut dan akan muncul pula pemberitahuan pada Logcat.

  <image src="https://github.com/user-attachments/assets/91c6c066-4bb2-40fc-b44c-b75a9035992b" height="512px">

- Register

  Register dapat dilakukan dengan cara menekan tombol daftar, lalu akan muncul dialog sebagai berikut.

  <image src="https://github.com/user-attachments/assets/1cbc0c91-3713-47de-a256-afac164c134b" height="512px">

  Setelah menekan tombol Konfirmasi, apabila text field masih kosong atau username telah terdaftar, maka akan muncul pemberitahuan sebagai berikut dan akan muncul pula pemberitahuan pada Logcat.

  <image src="https://github.com/user-attachments/assets/36583368-b6b5-4186-b658-b4b980edef4b" height="512px"> &nbsp; <image src="https://github.com/user-attachments/assets/ca7fd726-4f39-4066-92c4-2bcc7b009e03" height="512px">

  Apabila pendaftaran berhasil, maka akan muncul pemberitahuan sebagai berikut dan akan muncul pula pemberitahuan pada Logcat. Username dan password yang didaftarkan akan disimpan sementara ke dalam variabel `usernamePassword: Map<String, String>` pada `MainActivity`.

  <image src="https://github.com/user-attachments/assets/59c9c23a-15ed-40f0-973e-b1fd854fe4de" height="512px">

## Home screen

Pada home screen (`HomeActivity`) terdapat text yang berisikan pesan selamat datang beserta username yang digunakan untuk login dan sebuah button untuk logout, berikut tampilannya.

<image src="https://github.com/user-attachments/assets/3bb115fb-8061-4b3d-90e6-dda69de410aa" height="512px">

- Logout

  Untuk melakukan logout dapat menekan tombol Logout dan setelahnya akan muncul dialog konfirmasi sebagai berikut.

  <image src="https://github.com/user-attachments/assets/b8a1889c-eb04-417d-944d-990acbcbda20" height="512px">

  Setelah menekan tombol Ya, maka aplikasi akan berpindah ke login screen (`MainActivity`). Pada login screen akan muncul pemberitahuan sebagai berikut dan akan muncul pula pemberitahuan pada Logcat.

  <image src="https://github.com/user-attachments/assets/09021b6c-aa13-4243-9557-ba105868a671" height="512px">
