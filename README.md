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

## Level Keamanan

| Level | Keterangan |
|---|---|
| 1 | PUBLIC - Data dapat diakses semua pihak |
| 2 | RESTRICTED - Sebagian data disembunyikan |
| 3 | SECRET - Seluruh data sensitif disembunyikan |

## Author

Kelompok ... / Kelas C :

1. Nama [NIM]
2. Nama [NIM]
3. Nama [NIM]
4. Nama [NIM]
5. Nama [NIM]
