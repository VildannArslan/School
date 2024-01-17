## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).


## --------------------------



## Proje Açıklama Metni

Menü Formu;

* Bu form diğer alt formlara link vermek niteliğinde olacak ister button ister başka link şeklinde diğer formları çağırabilirsiniz,


Ders Formu;
* Hem Kayıt hem listeleme ekranı (tepede bir textbox üzeriden arama fonksiyonu olacak bulunan sonuç list üzerinde gelecek)

* Formun üzerinde Ders Class içeriğinden oluşacak alanlar (DersKodu, DersAd, DersDonem, OgretimGorevlisi vb..) yer alacak

* Ders ile ilgili temel bilgileri girildikten sonra kaydet butonu ile arka tarafta input ile girilen verilerin ".json, .csv, .xml vb.." istenilen dosya uzantısına göre içerik kaydedilecek


Öğrenci Formu;
* Hem Kayıt hem listeleme ekranı (tepede bir textbox üzeriden arama fonksiyonu olacak bulunan sonuç list üzerinde gelecek)

* Formun üzerinde Ogrenci Class içeriğinden oluşacak alanlar (OgrenciNo, OgrenciAd, OgrenciSoyad, OgrenciBolum, OgrenciDersler vb..)  OgrenciDersler alanı Combobox olup içerği Ders formundan kaydettiğiniz dersler yer alacak

* Ayrıca Ogrenci ile ilgili temel bilgileri girildikten sonra kaydet butonu ile arka tarafta input ile girilen verilerin ".json, .csv, .xml vb.." istenilen dosya uzantısına göre içerik kaydedilecek


Öğretim Görevlisi Kayıt Formu
* Hem Kayıt hem listeleme ekranı (tepede bir textbox üzeriden arama fonksiyonu olacak bulunan sonuç list üzerinde gelecek)

* Formun üzerinde OgretimGorevlisi Class içeriğinden oluşacak alanlar (OgretmenNo, Ad, Soyad, Bolum(Bilg Prog., Mobil Programlama), VerdigiDersler vb..)  OgrenciDersler alanı Combobox olup içerği Ders formundan kaydettiğiniz dersler yer alacak

* Bilgileri girildikten sonra kaydet butonu ile arka tarafta input ile girilen verilerin ".json, .csv, .xml vb.." istenilen dosya uzantısına göre içerik kaydedilecek



## Çalıştırmak İçin

Java kurulu ortamda proje açılır. 

Eğer ortamda bulunmuyorsa json-20231013.jar isimli jar dosyası (şu anda entegre) ve varsa uyarın veren gerekli diğer kütüphaneler import edilir.

App.java'da bulunan main metodu çalıştırılır.