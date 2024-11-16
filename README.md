# Belajar Git

## Daftar perintah

### init

- > git init

    Menginisiasi Git repository.

### clone

- > git clone \<URL repository\>

    Men-download repository pada URL tertentu.

### add

- > git add \<nama file\>

    Menambahkan file tertentu ke dalam index.

- > git add .

    Menambahkan semua file ke dalam index.

### status

- > git status

    Menampilkan status repository.

### branch

- > git branch

    Menampilkan daftar seluruh branch.

- > git branch -m \<nama branch\>

    Mengganti nama branch yang di-checkout.

### checkout

- > git checkout \<nama branch\>

    Mengganti branch yang di-checkout ke branch lain.

- > git checkout -b \<nama branch\>

    Membuat branch baru, sekaligus men-checkout-nya.

### commit

- > git commit -m "\<pesan\>"

    Merekam perubahan ke dalam repository dengan pesan tertentu.

### log

- > git log

    Menampilkan daftar commit yang telah dilakukan.

### reset

- > git reset --hard \<commit hash\>

    Mengembalikan repository ke kondisi commit tertentu.

### revert

- > git revert \<commit hash\>

    Mengembalikan repository ke kondisi commit tertentu, sekaligus meng-commit-nya.

### push

- > git push

    Meng-update remote repository dengan local repository pada branch yang di-checkout.

- > git push -f

    Meng-update remote repository dengan local repository pada branch yang di-checkout dengan mengabaikan peringatan yang ada (**tidak disarankan**).

- > git push -u origin \<nama branch\>

    Meng-update remote repository dengan local repository pada branch tertentu.

### pull

- > git pull

    Meng-update local repository dengan remote repository.

## Menggunakan GitHub dengan SSH

1. Men-generate SSH Key

    - Buka Git Bash dan jalankan perintah berikut.

        > ssh-keygen -t rsa -b 4096 -C "\<email akun GitHub\>"

    - Setelah itu, selesaikan semua prompt yang tampil pada Git Bash.

2. Menambahkan SSH Key ke SSH Agent

    - Inisiasi ssh-agent dengan perintah berikut.

        > eval "$(ssh-agent -s)"

    - Tambahkan SSH Key dengan perintah berikut.

        > ssh-add ~/.ssh/id_rsa

        atau perintah berikut, jika SSH Key tidak berada di lokasi default.

        > ssh-add \<lokasi SSH Key\>

3. Menambahkan SSH Key ke akun GitHub

    - Copy key yang telah dibuat sebelumnya dengan perintah berikut (Windows).

        > clip \< ~/.ssh/id_rsa.pub

        atau perintah berikut, jika SSH Key tidak berada di lokasi default.

        > clip \< \<lokasi file .pub dari SSH Key\>

    - Tambahkan key ke akun GitHub pada Settings > SSH and GPG keys.

4. SSH berhasil di-setup dan dapat digunakan

    - Untuk melakukan clone menggunakan SSH, caranya sama seperti pada HTTPS, hanya saja URL yang digunakan adalah URL SSH.

    - Apabila sebelumnya telah meng-clone repository menggunakan HTTPS, dapat gunakan perintah berikut untuk mengubah remote URL ke SSH.

        > git remote set-url origin \<URL SSH\>