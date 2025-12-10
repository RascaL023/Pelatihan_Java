Di tugas 11 ini, saya membuat projek Spring Boot Java tentang manajemen kelas dengan mahasiswanya. Dimana ini merupakan kelanjutan dari projek sebelumnya yang hanya terdapat satu entity atau model, sedangkan di projek kali ini terdapat 2 entity yang saling berhubungan.

Untuk fitur2 nya memang mengalami kemunduran dari segi UX, ditandai dengan tidak adanya notifikasi dan sedikit validasi. Namun untuk jumlah halamannya lebih banyak(karena instruksi dari soal sih), kemudian saya tetap menggunakkan htmx, namun dengan lebih optimal/tidak terlalu dipaksakan, yakni pada bagian list student dan kelas hanya reload bagian tabelnya saja, bukan satu halaman penuh. Untuk UI nya lebih baik mungkin karena bersumber dari AI dengan penyesuaian, tidak seperti tugas 10 yang sederhana tampilanya karena saya buat pribadi, hanya warna yang dari AI. Fitur2 yang lainya yakni CRUD untuk setiap kelas dan mahasiswa.

Dari segi relasi, saya menggunakkan relasi OneToMany - ManyToOne. Dimana satu Kelas bisa dimiliki/berisi banyak Mahasiswa(OneToMany), dan sebaliknya Mahasiswa hanya bisa memiliki/memasuki satu Kelas saja(ManyToOne). Cara kerja relasinya seperti relasi mysql 2 tabel dengan 1 tabel induk, dan memang pada dasarnya demikian, hanya saja otomatisasi oleh hibernate. Belum ada casecading seperti jika kelas dihapus maka mahasiswa ... karena masih belum mengerti seharusnya bagaimana, sebab dalam ketentuan mahasiswa harus memilih kelas.

Untuk struktur foldernya masih sama seperti sebelum2 nya yakni dengan tambahan layer dto sebagai pembatas antara user <-> repo/entity. Terdapat satu tambahan file yakni ClassDetail sebagai pembantu untuk menampilkan dan akses kelas yang dimiliki oleh Mahasiswa. Sebenarnya bisa saja kita kirim ClassesResponse bersamanaan dengan StudentResponse ketika ingin menampilkan/akses input di form tambah/edit, namun jika demikian, banyak data yang mubazir karena gak dipakai, maka dari itu saya buat class pembantu yang berisikan id dan nama kelasnya.
Berikut tree foldernya:

.
├── HELP.md
├── mvnw
├── mvnw.cmd
├── pom.xml
├── src
│   ├── main
│   │   ├── java
│   │   │   └── id
│   │   │       └── rascal
│   │   │           └── rascal
│   │   │               ├── ClassManagerApplication.java
│   │   │               ├── controller
│   │   │               │   ├── ClassesController.java
│   │   │               │   └── StudentsController.java
│   │   │               ├── dto
│   │   │               │   ├── mappers
│   │   │               │   │   ├── ClassesMapper.java
│   │   │               │   │   ├── ClassSignature.java
│   │   │               │   │   └── StudentsMapper.java
│   │   │               │   ├── requests
│   │   │               │   │   ├── ClassesRequest.java
│   │   │               │   │   └── StudentsRequest.java
│   │   │               │   └── responses
│   │   │               │       ├── ClassesResponse.java
│   │   │               │       └── StudentsResponse.java
│   │   │               ├── entity
│   │   │               │   ├── Classes.java
│   │   │               │   └── Students.java
│   │   │               ├── repository
│   │   │               │   ├── ClassesRepository.java
│   │   │               │   └── StudentsRepository.java
│   │   │               └── service
│   │   │                   ├── ClassesService.java
│   │   │                   └── StudentsService.java
│   │   └── resources
│   │       ├── application.properties
│   │       ├── static
│   │       │   └── css
│   │       │       └── style.css
│   │       └── templates
│   │           ├── add-class.html
│   │           ├── add-student.html
│   │           ├── class-detail.html
│   │           ├── classes.html
│   │           ├── edit-class.html
│   │           ├── edit-student.html
│   │           ├── fragments
│   │           │   └── header.html
│   │           ├── index.html
│   │           ├── parts
│   │           │   ├── class-table.html
│   │           │   └── student-table.html
│   │           └── students.html
│   └── test
│       └── java
│           └── id
│               └── rascal
│                   └── rascal
│                       └── ClassManagerApplicationTests.java
└── target
    ├── classes
    │   ├── application.properties
    │   ├── id
    │   │   └── rascal
    │   │       └── rascal
    │   │           ├── ClassManagerApplication.class
    │   │           ├── controller
    │   │           │   ├── ClassesController.class
    │   │           │   └── StudentsController.class
    │   │           ├── dto
    │   │           │   ├── mappers
    │   │           │   │   ├── ClassesMapper.class
    │   │           │   │   ├── ClassSignature.class
    │   │           │   │   └── StudentsMapper.class
    │   │           │   ├── requests
    │   │           │   │   ├── ClassesRequest.class
    │   │           │   │   └── StudentsRequest.class
    │   │           │   └── responses
    │   │           │       ├── ClassesResponse.class
    │   │           │       └── StudentsResponse.class
    │   │           ├── entity
    │   │           │   ├── Classes.class
    │   │           │   └── Students.class
    │   │           ├── repository
    │   │           │   ├── ClassesRepository.class
    │   │           │   └── StudentsRepository.class
    │   │           └── service
    │   │               ├── ClassesService.class
    │   │               └── StudentsService.class
    │   ├── static
    │   │   └── css
    │   │       └── style.css
    │   └── templates
    │       ├── add-class.html
    │       ├── add-student.html
    │       ├── class-detail.html
    │       ├── classes.html
    │       ├── edit-class.html
    │       ├── edit-student.html
    │       ├── fragments
    │       │   └── header.html
    │       ├── index.html
    │       ├── parts
    │       │   ├── class-table.html
    │       │   └── student-table.html
    │       └── students.html
    ├── generated-sources
    │   └── annotations
    ├── generated-test-sources
    │   └── test-annotations
    ├── maven-status
    │   └── maven-compiler-plugin
    │       ├── compile
    │       │   └── default-compile
    │       │       ├── createdFiles.lst
    │       │       └── inputFiles.lst
    │       └── testCompile
    │           └── default-testCompile
    │               ├── createdFiles.lst
    │               └── inputFiles.lst
    └── test-classes
        └── id
            └── rascal
                └── rascal
                    └── ClassManagerApplicationTests.class

58 directories, 68 files
