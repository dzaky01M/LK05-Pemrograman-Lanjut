# MediGuard Integration Gateway - LK05 Pemrograman Lanjutan

PT Asuransi BPJS adalah perusahaan asuransi kesehatan besar yang memiliki server pusat data nasabah. Mereka bekerja sama dengan ratusan Rumah Sakit Mitra Keluarga di seluruh negeri. Sistem ini dibangun menggunakan **Java Generics** untuk menciptakan satu jalur API yang aman, fleksibel, dan tahan terhadap perubahan struktur data di masa depan.

## Permasalahan

1. **Ketidakseragaman Data**: Beberapa rumah sakit meminta data pasien versi lama (V1), sementara yang lain sudah menggunakan versi baru (V2) yang memiliki field tambahan seperti riwayat alergi.
2. **Kebocoran Data**: Terkadang rumah sakit menerima data sensitif seperti Nomor KTP atau Diagnosa Penyakit Khusus yang seharusnya disembunyikan tergantung pada level akses dokter yang login.
3. **Kode yang Rapuh**: Tim backend harus membuat endpoint API berbeda untuk setiap jenis permintaan data sehingga kode menjadi sangat berulang dan sulit dirawat.

## Rancangan Sistem

1. **Struktur Data Berjenjang (Bounded Types)**: Terdapat interface dasar `MedicalRecord` yang wajib dimiliki semua data medis. Terdapat interface `Versioned` yang memiliki method `getVersion()`. Semua data yang ditransfer harus mengimplementasikan `MedicalRecord` & `Versioned`. Terdapat 2 implementasi data yaitu `PatientProfileV1` dan `PatientProfileV2` dimana V2 memiliki field tambahan riwayat alergi.
2. **Generic API Response dengan Keamanan**: Terdapat kelas `SecureResponse<T>` yang membungkus data dengan mekanisme Data Masking otomatis. Menggunakan Generic Constraint untuk memastikan hanya data dengan level keamanan tertentu yang bisa diproses (`T extends MedicalRecord & Confidential`). Interface `Confidential` memiliki method `getSecurityLevel()` dengan level PUBLIC, RESTRICTED, dan SECRET.
3. **Generic Gateway Service**: Terdapat satu kelas service `IntegrationGateway<T>` dengan method tunggal `fetchData(String patientId, int requesterClearanceLevel)`. Jika `requesterClearanceLevel` lebih rendah dari `data.getSecurityLevel()`, maka field sensitif otomatis di-masking sebelum dibungkus ke `SecureResponse`. Proses masking terjadi secara generik tanpa penggunaan `instanceof` secara eksplisit di dalam Gateway.

## Class

1. **MedicalRecord**
   Class ini berfungsi sebagai reprensentasi data rekam medis pasien yang menyimpan informasi penting seperti `patientId` dan `securityLevel`. Data iini digunakan untuk mengidentifikasi pasien serta menentukan tingkat keamanan akses terhadap informasi medis dalam sistem.
2. **Versioned**
   Interface ini digunakan untuk menandai bahwa suatu data memiliki versi. Dengan adanya versioning, sistem dapat melacak perubahan data dan memastikan bahwa data yang digunakan adalah versi yang sesuai.
4. **Confidential**
   Interface ini berfungsi untuk menangani keamanan data sensitif. Method maskSensitiveData() digunakan untuk menyembunyikan informasi penting agar tetap aman ketika diakses oleh pengguna dengan hak akses terbatas.
6. **PatientProfileV1**
   Class ini merupakan implementasi awal dari profil pasien yang menggabungkan data rekam medis, versioning, dan keamanan data. Class ini mampu menyimpan data pasien sekaligus melakukan masking terhadap data sensitif sesuai kebutuhan.
8. **PatientProfileV2**
   Class ini merupakan pengembangan dari versi sebelumnya (PatientProfileV1) dengan kemungkinan penambahan fitur atau perbaikan struktur data. Meskipun demikian, class ini tetap mempertahankan kemampuan utama seperti pengelolaan data, versioning, dan keamanan informasi.
10. **SecureResponse**
    Class ini digunakan sebagai pembungkus hasil proses sistem. Di dalamnya terdapat informasi status keberhasilan, data yang dikembalikan, serta pesan tambahan, sehingga output sistem menjadi lebih terstruktur dan mudah dipahami.
12. **IntegrationGateway**
    Class ini berfungsi sebagai penghubung utama dalam sistem yang menangani proses pengambilan data pasien. Dengan menggunakan generic dan multiple bound, class ini memastikan bahwa data yang diproses memiliki kemampuan untuk identifikasi, versioning, dan keamanan. Method fetchData() akan melakukan validasi ID, pengecekan level keamanan, serta masking data jika diperlukan sebelum mengembalikan hasil dalam bentuk SecureResponse.
14. **MainHospital**
    Class ini merupakan entry point dari program yang digunakan untuk menjalankan simulasi sistem. Di dalamnya dilakukan pembuatan objek, pemanggilan proses melalui IntegrationGateway, serta menampilkan hasil ke pengguna.
    
## Author

Kelompok 6 / Kelas C :

1. Abraham Samuelson Siregar [255150200111030]
2. Muhammad Dzaky Nuril Mubin [255150201111019]
3. Ridho Alfarizi [255150207111039]
4. Hanif Maulana [255150207111042]
5. Hamam Yusuf Abdulloh [255150207111050]
