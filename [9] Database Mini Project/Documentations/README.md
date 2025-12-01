Dokumentasi singkat tugas 9 : Aswan Warehouse(Product Management)

Untuk bagian tampilan, sebagian besar berasal dari AI karena frontend bukan bidang saya mungkin, jadi fokus ke backend saja :v
Yang paling menonjol disini adalah adanya konsep dto, dimana ini semacam layer tambahan yang memisahkan user agar tidak mengakses entity secara langsung.
DTO atau data transfer object ini bekerja dengan membatasi agar field2 entity lebih terjaga dari akses langsung ke user. Ada 2 bagian utama dari DTO ini, yakni request dan
response. Request adalah wrapper/perwakilan dari entity yang mana berasal dari inputan user, yang mana hanya mengirimkan data2 yang memang harus di input manual. Sedangkan 
response biasanya lebih lengkap dari request, dan biasanya juga disertai dengan keamanan tambahan seperti encoding/hashing jika data2nya berupa data sensitif(dalam tugas ini
gak ada sih..). Adapun Mapper yang sebenarnya hanya cara modularisasi method2 untuk DTO, yang mana memudahkan kita juga untuk maintain, juga disana saya buat dalam bentuk 
method static agar gak perlu instansiasi. Di bagian service juga terdapat logika mengubah Entity -> Response, yang mana hanya cara kerjanya secara garis besar saja yang saya
pahami, intinya mirip dengan .map di js lalu panggil function pengkonversi kemudian dijadikan list agar sesuai dengan return dan kebutuhan model, adapun jika kita tidak menjadikannya List maupun bentuk lain yang merupakkan terminal, maka method .stream tidak akan berjalan.

Untuk bagian validasi, masih ada sebagian yang mengandalkan html langsung, hanya sebagian kecil yang dari sisi java Controller / Service. Di bagian Controller ada validasi jumlah huruf yang minimal 3 untuk nama produ, yang mana menurut saya ini merupakkan validasi yang pas untuk layer Controller. Di bagian service, ada validasi harga minimal. Menurut saya, validasi ini pas ditaruh di service karena disini adalah letak business logicnya. Cara kerjanya ialah dengan throw Illegal Exception, yang mana dapat ditangkap di layer Controller karena disana terdapat error handling sederhana dengan try-catch.

Untuk bagian notif memang cukup sederhana saja karena ternyata cukup merepotkan juga, apalagi menyangkut ke tampilan :) jadi ya cuman notif agar validasi/error handling bisa berguna/sampai ke user saja. Untuk confirm masih pakai js confirm(msg), karena cukup ribet juga bikinya apalagi hanya kepakai sekali jadi ya lain kali saja saya buatnya hehe.

Untuk fitur seperti search, saya memanfaatkan fitur dari Jpa yang bisa menghasilkan query ... where like %keyword%. Untuk paginasi, saya masih ingin mendalami dahulu, karena menurut saya ini cukup penting, namun dikarenakan tenggat tugas ini juga singkat jadi saya lepas saja, sebab kalau main di web banyak distraksi tampilan, yang sebenarnya gak terlalu ganggu, namun karena struktur kodenya udah matang(agak malas nambahin pageable dan parameter2nya), jadi ya gitulah...

Sekian, kurang lebihnya mohon maaf, terima kasih...
