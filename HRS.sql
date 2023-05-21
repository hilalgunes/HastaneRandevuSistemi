DROP DATABASE HRS;

Create database HRS;

Use HRS;

/*Hasta tablosunun varsa drop edilmesi ve yoksa oluþturulmasý*/
DROP TABLE IF EXISTS Hasta;
Create Table Hasta(

/*Hasta tablosunun niteliklerinin belirlenmesi*/
PatTC nchar(11) not null primary key,    /*PatTC niteliðinin birincil anahtar olarak belirlenmesi*/
Ad nvarchar(30) not null,
Soyad nvarchar(20) not null,
Cinsiyet bit not null,
DogumTarihi date not null,
Sifre varchar(12) not null,
Yas AS (
    CONCAT(
	   DATEDIFF(year, DogumTarihi, GETDATE()),
	   DATEDIFF(month, DogumTarihi, GETDATE()) %12, /* Derived Yaþ niteliðinin birleþtirme iþlemi yapan CONCAT ve Tarih Farký alan DATEDIFF fonksiyonlarý kullanýlarak günümüz tarihi alan GETDATE fonksiyonuna göre hesaplanmasý*/
	   DATEDIFF(day, DogumTarihi, GETDATE()) %30,
);


DROP TABLE IF EXISTS HastaTelefonNumarasi;
Create Table HastaTelefonNumarasi(

PatTC nchar(11) primary key not null,
TelefonNumarasi nchar(11) not null,

/*HastaTelefonNumarasi tablosunun yabancý anahtarlarýnýn ve referanslarýnýn belirlenmesi*/
FOREIGN KEY(PatTC) REFERENCES Hasta(PatTC),
);


DROP TABLE IF EXISTS HastaEmail;
Create Table HastaEmail(

PatTC nchar(11) not null primary key,
Email nvarchar(30) not null,

FOREIGN KEY(PatTC) REFERENCES Hasta(PatTC),
);


DROP TABLE IF EXISTS Doktor;
Create Table Doktor(

DocTC nchar(11) not null primary key,
Ad nvarchar(30) not null,
Soyad nvarchar(20) not null,
Cinsiyet bit not null,
DogumTarihi date not null,
Sifre varchar(12) not null,
PolyID tinyint not null,
HospID smallint not null,
Yas AS (
    CONCAT(
	   DATEDIFF(year, DogumTarihi, GETDATE()),
	   DATEDIFF(month, DogumTarihi, GETDATE()) %12,	  /* Derived Yaþ niteliðinin birleþtirme iþlemi yapan CONCAT ve Tarih Farký alan DATEDIFF fonksiyonlarý kullanýlarak günümüz tarihi alan GETDATE fonksiyonuna göre hesaplanmasý*/
	   DATEDIFF(day, DogumTarihi, GETDATE()) %30 )),

FOREIGN KEY(PolyID) REFERENCES Poliklinik(PolyID),
FOREIGN KEY(HospID) REFERENCES Hastane(HospID)
);


DROP TABLE IF EXISTS DoktorTelefonNumarasi;
Create Table DoktorTelefonNumarasi(

DocTC nchar(11) not null primary key,
TelefonNumarasi nchar(11) not null,

FOREIGN KEY(DocTC) REFERENCES Doktor(DocTC),
);


DROP TABLE IF EXISTS DoktorEmail;
Create Table DoktorEmail(

DocTC nchar(11) not null primary key,
Email nvarchar(30) not null,

FOREIGN KEY(DocTC) REFERENCES Doktor(DocTC),
);


DROP TABLE IF EXISTS Hastane;
Create Table Hastane(

HospID smallint not null primary key,
Ad nvarchar(60) not null,
);


DROP TABLE IF EXISTS HastaneAd;
Create Table HastaneAd(

Ad nvarchar(60) not null primary key,
Il nvarchar(14) not null,
Ilce nvarchar(20) not null,
Mahalle nvarchar(20) not null,
CaddeSokak nvarchar (20) not null,

FOREIGN KEY(Ad) REFERENCES Hastane(Ad),
);


DROP TABLE IF EXISTS Poliklinik;
Create Table Poliklinik(

PolyID tinyint not null primary key,
Ad nvarchar(25) not null,
);


DROP TABLE IF EXISTS Randevu;
Create Table Randevu(

AppID bigint not null primary key,
Tarih date not null,
Saat time not null,
PatTC nchar(11) not null,

FOREIGN KEY(PatTC) REFERENCES Hasta(PatTC),
);


DROP TABLE IF EXISTS RandevuTarih;
Create Table RandevuTarih(

Tarih date not null,
Saat time not null,
PatTC nchar(11) not null,
DocTC nchar(11) not null,

PRIMARY KEY(Tarih,Saat,PatTC)

FOREIGN KEY(Tarih) REFERENCES Randevu(Tarih),
FOREIGN KEY(Saat) REFERENCES Randevu(Saat),
FOREIGN KEY(PatTC) REFERENCES Hasta(PatTC),
FOREIGN KEY(DocTC) REFERENCES Doktor(DocTC)
);


DROP TABLE IF EXISTS RandevuTC;
Create Table RandevuTC(

DocTC nchar(11) not null primary key,
HospID smallint not null,
PolyID tinyint not null,

FOREIGN KEY(DocTC) REFERENCES Doktor(DocTC),
FOREIGN KEY(HospID) REFERENCES Hastane(HospID),
FOREIGN KEY(PolyID) REFERENCES Poliklinik(PoliklinikID)
);


DROP TABLE IF EXISTS Muayene;
Create Table Muayene(

DocTC nchar(11) not null,
PatTC nchar(11) not null,

PRIMARY KEY(DocTC, PatTC),

FOREIGN KEY(DocTC) REFERENCES Doktor(DocTC),
FOREIGN KEY(PatTC) REFERENCES Hasta(PatTC),
);


DROP TABLE IF EXISTS DoktorUzmanlikAlani;
Create Table DoktorUzmanlikAlani(

DocTC nchar(11) not null,
UzmanlikAlani nvarchar(25) not null,

PRIMARY KEY(DocTC, UzmanlikAlani),

FOREIGN KEY(DocTC) REFERENCES Doktor(DocTC),
);


DROP TABLE IF EXISTS DoktorUnvan;
Create Table DoktorUnvan(

DocTC nchar(11) not null,
Unvan nvarchar(15) not null,

PRIMARY KEY(DocTC, Unvan),

FOREIGN KEY(DocTC) REFERENCES Doktor(DocTC),
);


DROP TABLE IF EXISTS DoktorDiploma;
Create Table DoktorDiploma(

DocTC nchar(11) not null primary key,
DiplomaNumarasi nchar(14) not null,

FOREIGN KEY(DocTC) REFERENCES Doktor(DocTC),
);


DROP TABLE IF EXISTS DoktorDiplomaNumarasi;
Create Table DoktorDiplomaNumarasi(

DiplomaNumarasi nchar(14) not null primary key,
Universite nvarchar(50) not null,
Tarih date not null,

FOREIGN KEY(DiplomaNumarasi) REFERENCES DoktorDiploma(DiplomaNumarasi)
);


DROP TABLE IF EXISTS HastaneIletisim;
Create Table HastaneIletisim(

TelefonNumarasi nchar(11) not null,
Email nvarchar(30) not null,

PRIMARY KEY(TelefonNumarasi, Email)
);


DROP TABLE IF EXISTS HastaneÝletisimTelefon;
Create Table HastaneÝletisimTelefon(

TelefonNumarasi nchar(11) not null primary key,
HospID smallint not null,

FOREIGN KEY(TelefonNumarasi) REFERENCES HastaneÝletisim(TelefonNumarasi),
FOREIGN KEY(HospID) REFERENCES Hastane(HospID)
);


DROP TABLE IF EXISTS Bulunma;
Create Table Bulunma(

HospID smallint not null,
PolyID tinyint not null,

PRIMARY KEY(HospID, PolyID)

FOREIGN KEY(HospID) REFERENCES Hastane(HospID),
FOREIGN KEY(PolyID) REFERENCES Poliklinik(PolyID)
);


DROP TABLE IF EXISTS Yonlendirme;
Create Table Yonlendirme(

YonlendirenPoliklinikID tinyint,
YonlendirilenPoliklinikID tinyint,
Tarih date not null,

PRIMARY KEY(YonlendirenPoliklinikID, YonlendirilenPoliklinikID, Tarih)
);


/*Tablolardaki niteliklere veri kayýt ekleme iþlemi*/
INSERT INTO Hasta(PatTC, Ad, Soyad, Cinsiyet, DogumTarihi, Sifre) VALUES('11111111111','Fatih','Özkurt',0,'21-02-2002','123456esdf');
INSERT INTO Hasta(PatTC, Ad, Soyad, Cinsiyet, DogumTarihi, Sifre) VALUES('22222222222','Ýdris','Küçük',0,'5-11-2001','123456esdf');
INSERT INTO Hasta(PatTC, Ad, Soyad, Cinsiyet, DogumTarihi, Sifre) VALUES('33333333333','Petek Mislina','Aydýn',1,'6-11-2001','123456esdf');
INSERT INTO Hasta(PatTC, Ad, Soyad, Cinsiyet, DogumTarihi, Sifre) VALUES('44444444444','Hilal','Güneþ',1,'7-11-2001','123456esdf');
INSERT INTO Hasta(PatTC, Ad, Soyad, Cinsiyet, DogumTarihi, Sifre) VALUES('55555555555','Tuðrulhan','Terzi',0,'5-11-2001','123456esdf');
INSERT INTO Hasta(PatTC, Ad, Soyad, Cinsiyet, DogumTarihi, Sifre) VALUES('66666666666','Fatih','Mesihoðlu',0,'22-02-2002','123456esdf');
INSERT INTO Hasta(PatTC, Ad, Soyad, Cinsiyet, DogumTarihi, Sifre) VALUES('77777777777','Miray','Özkurt',1,'9-01-2003','123456esdf');
INSERT INTO Hasta(PatTC, Ad, Soyad, Cinsiyet, DogumTarihi, Sifre) VALUES('88888888888','Ceyda','Sefer',1,'10-01-2003','123456esdf');
INSERT INTO Hasta(PatTC, Ad, Soyad, Cinsiyet, DogumTarihi, Sifre) VALUES('99999999999','Emre','Nasuhoðlu',0,'12-02-200','123456esdf');

INSERT INTO HastaTelefonNumarasi(PatTC, TelefonNumarasi) VALUES('11111111111','534XXXXXXXX');
INSERT INTO HastaTelefonNumarasi(PatTC, TelefonNumarasi) VALUES('22222222222','533YYYYYYYY');
INSERT INTO HastaTelefonNumarasi(PatTC, TelefonNumarasi) VALUES('33333333333','536ZZZZZZZZ');
INSERT INTO HastaTelefonNumarasi(PatTC, TelefonNumarasi) VALUES('44444444444','532FFFFFFFF');
INSERT INTO HastaTelefonNumarasi(PatTC, TelefonNumarasi) VALUES('55555555555','531CCCCCCCC');
INSERT INTO HastaTelefonNumarasi(PatTC, TelefonNumarasi) VALUES('66666666666','555AAAAAAAA');
INSERT INTO HastaTelefonNumarasi(PatTC, TelefonNumarasi) VALUES('77777777777','589BBBBBBBB');
INSERT INTO HastaTelefonNumarasi(PatTC, TelefonNumarasi) VALUES('88888888888','568VVVVVVVV');
INSERT INTO HastaTelefonNumarasi(PatTC, TelefonNumarasi) VALUES('99999999999','586GGGGGGGG');

INSERT INTO HastaEmail(PatTC, Email) VALUES('11111111111','fatih.ozkurt21@gmail.com');
INSERT INTO HastaEmail(PatTC, Email) VALUES('22222222222','kucukidris@gmail.com');
INSERT INTO HastaEmail(PatTC, Email) VALUES('33333333333','mislinaydin@gmail.com');
INSERT INTO HastaEmail(PatTC, Email) VALUES('44444444444','hilalgunes@gmail.com');
INSERT INTO HastaEmail(PatTC, Email) VALUES('55555555555','terziphotography@gmail.com');
INSERT INTO HastaEmail(PatTC, Email) VALUES('66666666666','fatihhhozkurt@gmail.com');
INSERT INTO HastaEmail(PatTC, Email) VALUES('77777777777','mehmetkerim@gmail.com');
INSERT INTO HastaEmail(PatTC, Email) VALUES('88888888888','denizsefer@gmail.com');
INSERT INTO HastaEmail(PatTC, Email) VALUES('99999999999','nasuhogludeniz@hotmail.com');

INSERT INTO Doktor(DocTC, Ad, Soyad, Cinsiyet, DogumTarihi, Sifre,PolyID,HospID) VALUES('12345678912','Efe','Aydal',0,'21-02-2002','123456esdf',9,5);
INSERT INTO Doktor(DocTC, Ad, Soyad, Cinsiyet, DogumTarihi, Sifre,PolyID,HospID) VALUES('12332165498','Ýdris','Büyük',0,'22-03-2001','654321iaa',8,6);
INSERT INTO Doktor(DocTC, Ad, Soyad, Cinsiyet, DogumTarihi, Sifre,PolyID,HospID) VALUES('21354687912','Barýþ','Ateþ',0,'23-04-2000','987654b21',6,7);
INSERT INTO Doktor(DocTC, Ad, Soyad, Cinsiyet, DogumTarihi, Sifre,PolyID,HospID) VALUES('32165498778','Emre','Upuzun',0,'24-05-1999','456789ezs',4,1);
INSERT INTO Doktor(DocTC, Ad, Soyad, Cinsiyet, DogumTarihi, Sifre,PolyID,HospID) VALUES('45612365498','Þerife','Tüfekçi',1,'25-06-1998','789123s43',7,2);
INSERT INTO Doktor(DocTC, Ad, Soyad, Cinsiyet, DogumTarihi, Sifre,PolyID,HospID) VALUES('78945612321','Birkan','Nasuhoðlu',0,'26-07-1997','789321bb',5,5);
INSERT INTO Doktor(DocTC, Ad, Soyad, Cinsiyet, DogumTarihi, Sifre,PolyID,HospID) VALUES('56489732123','Aytaç','Öksüz',0,'27-08-1996','a987321a',1,8);
INSERT INTO Doktor(DocTC, Ad, Soyad, Cinsiyet, DogumTarihi, Sifre,PolyID,HospID) VALUES('12345542316','Miray','Majetic',1,'28-09-1995','987m123m',1,3);
INSERT INTO Doktor(DocTC, Ad, Soyad, Cinsiyet, DogumTarihi, Sifre,PolyID,HospID) VALUES('99875642315','Ece','Ozan',1,'29-10-1994','9d87123de',2,3);

INSERT INTO DoktorTelefonNumarasi(DocTC, TelefonNumarasi) VALUES('12345678912','534XXXXXXXX');
INSERT INTO DoktorTelefonNumarasi(DocTC, TelefonNumarasi) VALUES('12332165498','533YYYYYYYY');
INSERT INTO DoktorTelefonNumarasi(DocTC, TelefonNumarasi) VALUES('21354687912','536ZZZZZZZZ');
INSERT INTO DoktorTelefonNumarasi(DocTC, TelefonNumarasi) VALUES('32165498778','532FFFFFFFF');
INSERT INTO DoktorTelefonNumarasi(DocTC, TelefonNumarasi) VALUES('45612365498','531CCCCCCCC');
INSERT INTO DoktorTelefonNumarasi(DocTC, TelefonNumarasi) VALUES('78945612321','555AAAAAAAA');
INSERT INTO DoktorTelefonNumarasi(DocTC, TelefonNumarasi) VALUES('56489732123','589BBBBBBBB');
INSERT INTO DoktorTelefonNumarasi(DocTC, TelefonNumarasi) VALUES('12345542316','568VVVVVVVV');
INSERT INTO DoktorTelefonNumarasi(DocTC, TelefonNumarasi) VALUES('99875642315','586GGGGGGGG');

INSERT INTO DoktorEmail(DocTC, Email) VALUES('12345678912','efeaydal@gmail.com');
INSERT INTO DoktorEmail(DocTC, Email) VALUES('12332165498','buyukidris@gmail.com');
INSERT INTO DoktorEmail(DocTC, Email) VALUES('21354687912','baronowic@gmail.com');
INSERT INTO DoktorEmail(DocTC, Email) VALUES('32165498778','emreup@gmail.com');
INSERT INTO DoktorEmail(DocTC, Email) VALUES('45612365498','kizilsavci@gmail.com');
INSERT INTO DoktorEmail(DocTC, Email) VALUES('78945612321','nasuhoglu@gmail.com');
INSERT INTO DoktorEmail(DocTC, Email) VALUES('56489732123','bioinformaticadam@gmail.com');
INSERT INTO DoktorEmail(DocTC, Email) VALUES('12345542316','arnavutsarisi@gmail.com');
INSERT INTO DoktorEmail(DocTC, Email) VALUES('99875642315','eceozannn@gmail.com');

INSERT INTO Hastane(HospID, Ad) VALUES(1,'Murat Kölük Devlet Hastanesi');
INSERT INTO Hastane(HospID, Ad) VALUES(2,'Dr.Sadi Konuk Eðitim ve Araþtýrma Hastanesi');
INSERT INTO Hastane(HospID, Ad) VALUES(3,'Ali Osman Sönmez Devlet Hastanesi');
INSERT INTO Hastane(HospID, Ad) VALUES(4,'Çekirge Devlet Hastanesi');
INSERT INTO Hastane(HospID, Ad) VALUES(5,'Sakarya Üniversitesi Eðitim ve Araþtýrma Hastanesi');
INSERT INTO Hastane(HospID, Ad) VALUES(6,'Sakarya Sadýka Sabancý Devlet Hastanesi');
INSERT INTO Hastane(HospID, Ad) VALUES(7,'Bilecik Eðitim ve Araþtýrma Hastanesi');
INSERT INTO Hastane(HospID, Ad) VALUES(8,'Sivas Devlet Hastanesi');
INSERT INTO Hastane(HospID, Ad) VALUES(9,'Numune Hastanesi');
INSERT INTO Hastane(HospID, Ad) VALUES(10,'Ardahan Devlet Hastanesi');

INSERT INTO HastaneAd(Ad, Il, Ilçe, Mahalle, CaddeSokak) Values('Murat Kölük Devlet Hastanesi','Ýstanbul','Avcýlar','Gümüþpala','Yeni Yuva');
INSERT INTO HastaneAd(Ad, Il, Ilçe, Mahalle, CaddeSokak) Values('Dr.Sadi Konuk Eðitim ve Araþtýrma Hastanesi','Ýstanbul','Bakýrköy','Zuhuratbaba','Tevfik Saðlam');
INSERT INTO HastaneAd(Ad, Il, IÝlçe, Mahalle, CaddeSokak) Values('Ali Osman Sönmez Devlet Hastanesi','Bursa','Osmangazi','Hüdavendigar','Prof.Dr.Orhan Karmýþ');
INSERT INTO HastaneAd(Ad, Il, Ilçe, Mahalle, CaddeSokak) Values('Çekirge Devlet Hastanesi','Bursa','Osmangazi','Hüdavendigar','Dobruca');
INSERT INTO HastaneAd(Ad, Il, Ilçe, Mahalle, CaddeSokak) Values('Sakarya Üniversitesi Eðitim ve Araþtýrma Hastanesi','Sakarya','Adapazarý','Þirinevler','Saðlýk');
INSERT INTO HastaneAd(Ad, Il, Ilçe, Mahalle, CaddeSokak) Values('Sakarya Sadýka Sabancý Devlet Hastanesi','Sakarya','Serdivan','Ýstiklal','Muhsin Yazýcýoðlu');
INSERT INTO HastaneAd(Ad, Il, Ilçe, Mahalle, CaddeSokak) Values('Bilecik Eðitim ve Araþtýrma Hastanesi','Bilecik','Merkez','Ertuðrulgazi','Fatih Sultan Mehmet');
INSERT INTO HastaneAd(Ad, Il, Ilçe, Mahalle, CaddeSokak) Values('Sivas Devlet Hastanesi','Sivas','Merkez','Örtülüpýnar','Ýnönü');
INSERT INTO HastaneAd(Ad, Il, Ilçe, Mahalle, CaddeSokak) Values('Numune Hastanesi','Sivas','Merkez','Yeþilyurt','Þifa');
INSERT INTO HastaneAd(Ad, Il, Ilçe, Mahalle, CaddeSokak) Values('Ardahan Devlet Hastanesi','Ardahan','Merkez','Ýnönü','Sugöze');

INSERT INTO Poliklinik(PolyID, Ad) VALUES(1,'Dermatoloji');
INSERT INTO Poliklinik(PolyID, Ad) VALUES(2,'Dahiliye');
INSERT INTO Poliklinik(PolyID, Ad) VALUES(3,'Göz Hastalýklarý');
INSERT INTO Poliklinik(PolyID, Ad) VALUES(4,'Genel Cerrahi');
INSERT INTO Poliklinik(PolyID, Ad) VALUES(5,'Nöroloji');
INSERT INTO Poliklinik(PolyID, Ad) VALUES(6,'Fizik Tedavi ve Rehabilitasyon');
INSERT INTO Poliklinik(PolyID, Ad) VALUES(7,'Kardiyoloji');
INSERT INTO Poliklinik(PolyID, Ad) VALUES(8,'Psikoloji');
INSERT INTO Poliklinik(PolyID, Ad) VALUES(9,'Üroloji');
INSERT INTO Poliklinik(PolyID, Ad) VALUES(10,'Jinekoloji');
INSERT INTO Poliklinik(PolyID, Ad) VALUES(11,'Aðýz ve Diþ Saðlýðý');
INSERT INTO Poliklinik(PolyID, Ad) VALUES(12,'Ortopedi ve Travmatoloji');

INSERT INTO Randevu(AppID, Tarih, Saat, PatTC) VALUES (1,'12-05-2023','09:00','11111111111');
INSERT INTO Randevu(AppID, Tarih, Saat, PatTC) VALUES (2,'13-05-2023','09:21','22222222222');
INSERT INTO Randevu(AppID, Tarih, Saat, PatTC) VALUES (3,'14-05-2023','09:10','33333333333');
INSERT INTO Randevu(AppID, Tarih, Saat, PatTC) VALUES (4,'14-05-2023','09:20','44444444444');
INSERT INTO Randevu(AppID, Tarih, Saat, PatTC) VALUES (5,'26-01-2009','11:30','55555555555');
INSERT INTO Randevu(AppID, Tarih, Saat, PatTC) VALUES (6,'19-09-2022','13:20','66666666666');
INSERT INTO Randevu(AppID, Tarih, Saat, PatTC) VALUES (7,'21-02-2022','16:30','77777777777');
INSERT INTO Randevu(AppID, Tarih, Saat, PatTC) VALUES (8,'22-02-2022','16:20','88888888888');
INSERT INTO Randevu(AppID, Tarih, Saat, PatTC) VALUES (9,'23-02-2022','16:10','99999999999');

INSERT INTO RandevuTarih(Tarih, Saat, PatTC, DocTC) VALUES('12-05-2023','09:00','11111111111','12345678912','');
INSERT INTO RandevuTarih(Tarih, Saat, PatTC, DocTC) VALUES('12-05-2023','09:21','22222222222','12332165498','');
INSERT INTO RandevuTarih(Tarih, Saat, PatTC, DocTC) VALUES('12-05-2023','09:10','33333333333','21354687912','');
INSERT INTO RandevuTarih(Tarih, Saat, PatTC, DocTC) VALUES('12-05-2023','09:20','44444444444','32165498778','');
INSERT INTO RandevuTarih(Tarih, Saat, PatTC, DocTC) VALUES('12-05-2023','11:30','55555555555','45612365498','');
INSERT INTO RandevuTarih(Tarih, Saat, PatTC, DocTC) VALUES('12-05-2023','13:20','66666666666','78945612321','');
INSERT INTO RandevuTarih(Tarih, Saat, PatTC, DocTC) VALUES('12-05-2023','16:30','77777777777','56489732123','');
INSERT INTO RandevuTarih(Tarih, Saat, PatTC, DocTC) VALUES('12-05-2023','16:20','88888888888','12345542316','');
INSERT INTO RandevuTarih(Tarih, Saat, PatTC, DocTC) VALUES('12-05-2023','16:10','99999999999','99875642315','');

INSERT INTO RandevuTC(DocTC, HospID, PolyID) VALUES('12345678912',9 ,5);
INSERT INTO RandevuTC(DocTC, HospID, PolyID) VALUES('12332165498',8 ,6);
INSERT INTO RandevuTC(DocTC, HospID, PolyID) VALUES('21354687912',6 ,7);
INSERT INTO RandevuTC(DocTC, HospID, PolyID) VALUES('32165498778',4 ,1);
INSERT INTO RandevuTC(DocTC, HospID, PolyID) VALUES('45612365498',7 ,2);
INSERT INTO RandevuTC(DocTC, HospID, PolyID) VALUES('78945612321',5 ,5);
INSERT INTO RandevuTC(DocTC, HospID, PolyID) VALUES('56489732123',1 ,8);
INSERT INTO RandevuTC(DocTC, HospID, PolyID) VALUES('12345542316',1 ,3);
INSERT INTO RandevuTC(DocTC, HospID, PolyID) VALUES('99875642315',2 ,3);

INSERT INTO Muayene(DocTC, PatTC) VALUES('12345678912','11111111111');
INSERT INTO Muayene(DocTC, PatTC) VALUES('12332165498','22222222222');
INSERT INTO Muayene(DocTC, PatTC) VALUES('21354687912','33333333333');
INSERT INTO Muayene(DocTC, PatTC) VALUES('32165498778','44444444444');
INSERT INTO Muayene(DocTC, PatTC) VALUES('45612365498','55555555555');
INSERT INTO Muayene(DocTC, PatTC) VALUES('78945612321','66666666666');
INSERT INTO Muayene(DocTC, PatTC) VALUES('56489732123','77777777777');
INSERT INTO Muayene(DocTC, PatTC) VALUES('12345542316','88888888888');
INSERT INTO Muayene(DocTC, PatTC) VALUES('99875642315','99999999999');

INSERT INTO DoktorUzmanlikAlani(DocTC, UzmanlikAlani) VALUES('12345678912','Nöroloji');
INSERT INTO DoktorUzmanlikAlani(DocTC, UzmanlikAlani) VALUES('12332165498','Fizik Tedavi ve Rehabilitasyon');
INSERT INTO DoktorUzmanlikAlani(DocTC, UzmanlikAlani) VALUES('12332165498','Ortopedi ve Travmatoloji');
INSERT INTO DoktorUzmanlikAlani(DocTC, UzmanlikAlani) VALUES('21354687912','Kardiyoloji');
INSERT INTO DoktorUzmanlikAlani(DocTC, UzmanlikAlani) VALUES('32165498778','Dermatoloji');
INSERT INTO DoktorUzmanlikAlani(DocTC, UzmanlikAlani) VALUES('45612365498','Dahiliye');
INSERT INTO DoktorUzmanlikAlani(DocTC, UzmanlikAlani) VALUES('78945612321','Nöroloji');
INSERT INTO DoktorUzmanlikAlani(DocTC, UzmanlikAlani) VALUES('56489732123','Psikoloji');
INSERT INTO DoktorUzmanlikAlani(DocTC, UzmanlikAlani) VALUES('12345542316','Göz Hastalýklarý');
INSERT INTO DoktorUzmanlikAlani(DocTC, UzmanlikAlani) VALUES('12345542316','Nöroloji');
INSERT INTO DoktorUzmanlikAlani(DocTC, UzmanlikAlani) VALUES('99875642315','Göz Hastalýklarý');

INSERT INTO DoktorUnvan(DocTC, Unvan) VALUES('12345678912','Profesör');
INSERT INTO DoktorUnvan(DocTC, Unvan) VALUES('12332165498','Uzman');
INSERT INTO DoktorUnvan(DocTC, Unvan) VALUES('12332165498','Doçent');
INSERT INTO DoktorUnvan(DocTC, Unvan) VALUES('21354687912','Ordinaryus');
INSERT INTO DoktorUnvan(DocTC, Unvan) VALUES('32165498778','Profesör');
INSERT INTO DoktorUnvan(DocTC, Unvan) VALUES('45612365498','Doçent');
INSERT INTO DoktorUnvan(DocTC, Unvan) VALUES('78945612321','Yardýmcý Doçent');
INSERT INTO DoktorUnvan(DocTC, Unvan) VALUES('56489732123','Profesör');
INSERT INTO DoktorUnvan(DocTC, Unvan) VALUES('12345542316','Yardýmcý Doçent');
INSERT INTO DoktorUnvan(DocTC, Unvan) VALUES('12345542316','Doçent');
INSERT INTO DoktorUnvan(DocTC, Unvan) VALUES('99875642315','Profesör');

INSERT INTO DoktorDiploma(DocTC, DiplomaNumarasi) VALUES('12345678912','56F84S4F5S813A');
INSERT INTO DoktorDiploma(DocTC, DiplomaNumarasi) VALUES('12332165498','13486F4A8S6D2F');
INSERT INTO DoktorDiploma(DocTC, DiplomaNumarasi) VALUES('12332165498','4986SA4F5H32W1');
INSERT INTO DoktorDiploma(DocTC, DiplomaNumarasi) VALUES('21354687912','967SFDAADF4896');
INSERT INTO DoktorDiploma(DocTC, DiplomaNumarasi) VALUES('32165498778','FAC843896ZAFTQ');
INSERT INTO DoktorDiploma(DocTC, DiplomaNumarasi) VALUES('45612365498','ADWQ8F68S3W2SD');
INSERT INTO DoktorDiploma(DocTC, DiplomaNumarasi) VALUES('78945612321','FF8E8AZ86S2Q8S');
INSERT INTO DoktorDiploma(DocTC, DiplomaNumarasi) VALUES('56489732123','11F8A4F135S89Q');
INSERT INTO DoktorDiploma(DocTC, DiplomaNumarasi) VALUES('12345542316','S4A86CAF56843Z');
INSERT INTO DoktorDiploma(DocTC, DiplomaNumarasi) VALUES('12345542316','1SA7868A6Q2WE1');
INSERT INTO DoktorDiploma(DocTC, DiplomaNumarasi) VALUES('99875642315','15SAFFG8J6I2T1');

INSERT INTO DoktorDiplomaNumarasi(DiplomaNumarasi, Universite,Tarih) VALUES('12345678912','Bayburt Üniversitesi','07-06-1987');
INSERT INTO DoktorDiplomaNumarasi(DiplomaNumarasi, Universite,Tarih) VALUES('12332165498','Sivas Cumhuriyet Üniversitesi','08-08-1988');
INSERT INTO DoktorDiplomaNumarasi(DiplomaNumarasi, Universite,Tarih) VALUES('12332165498','Ortadoðu Teknik Üniversitesi','10-08-2000');
INSERT INTO DoktorDiplomaNumarasi(DiplomaNumarasi, Universite,Tarih) VALUES('21354687912','Karadeniz Teknik Üniversitesi','05-07-1977');
INSERT INTO DoktorDiplomaNumarasi(DiplomaNumarasi, Universite,Tarih) VALUES('32165498778','Niþantaþý Üniversitesi','16-09-2022');
INSERT INTO DoktorDiplomaNumarasi(DiplomaNumarasi, Universite,Tarih) VALUES('45612365498','Medipol Üniversitesi','23-08-2019');
INSERT INTO DoktorDiplomaNumarasi(DiplomaNumarasi, Universite,Tarih) VALUES('78945612321','Sakarya Üniversitesi','27-06-2003');
INSERT INTO DoktorDiplomaNumarasi(DiplomaNumarasi, Universite,Tarih) VALUES('56489732123','Sivas Cumhuriyet Üniversitesi','12-07-2015');
INSERT INTO DoktorDiplomaNumarasi(DiplomaNumarasi, Universite,Tarih) VALUES('12345542316','Sakarya Üniversitesi','12-07-2015');
INSERT INTO DoktorDiplomaNumarasi(DiplomaNumarasi, Universite,Tarih) VALUES('12345542316','Karadeniz Teknik Üniversitesi','23-08-2019');
INSERT INTO DoktorDiplomaNumarasi(DiplomaNumarasi, Universite,Tarih) VALUES('99875642315','Karadeniz Teknik Üniversitesi','19-05-2022');

INSERT INTO HastaneIletisim(TelefonNumarasi, Email) VALUES('02124129000','istanbuldhs40@saglik.gov.tr');
INSERT INTO HastaneIletisim(TelefonNumarasi, Email) VALUES('02124129000','42istanbuldhs@saglik.gov.tr');
INSERT INTO HastaneIletisim(TelefonNumarasi, Email) VALUES('02124147171','istanbuleah3.biis@saglik.gov.tr');
INSERT INTO HastaneIletisim(TelefonNumarasi, Email) VALUES('02242956000','bursa@saglik.gov.tr');
INSERT INTO HastaneIletisim(TelefonNumarasi, Email) VALUES('02242956001','bursa@saglik.gov.tr');
INSERT INTO HastaneIletisim(TelefonNumarasi, Email) VALUES('02242956002','bursa@saglik.gov.tr');
INSERT INTO HastaneIletisim(TelefonNumarasi, Email) VALUES('02242956003','bursa@saglik.gov.tr');
INSERT INTO HastaneIletisim(TelefonNumarasi, Email) VALUES('02242956004','bursa@saglik.gov.tr');
INSERT INTO HastaneIletisim(TelefonNumarasi, Email) VALUES('02242948100','brscekirgedh.iletisim@saglik.gov.tr');
INSERT INTO HastaneIletisim(TelefonNumarasi, Email) VALUES('02242393636','brscekirgedh.iletisim@saglik.gov.tr');
INSERT INTO HastaneIletisim(TelefonNumarasi, Email) VALUES('02648884000','sakaryaeah@saglik.gov.tr');
INSERT INTO HastaneIletisim(TelefonNumarasi, Email) VALUES('02642293160','sadikadh.iletisim@saglik.gov.tr');
INSERT INTO HastaneIletisim(TelefonNumarasi, Email) VALUES('02642293161','sadikadh.iletisim@saglik.gov.tr');
INSERT INTO HastaneIletisim(TelefonNumarasi, Email) VALUES('02282022011','bilecikeah@saglik.gov.tr');
INSERT INTO HastaneIletisim(TelefonNumarasi, Email) VALUES('03462217036','sivas.dh@saglik.gov.tr');
INSERT INTO HastaneIletisim(TelefonNumarasi, Email) VALUES('03462150833','svsnumuneh.iletisim@saglik.gov.tr');
INSERT INTO HastaneIletisim(TelefonNumarasi, Email) VALUES('03462150844','svsnumuneh.iletisim@saglik.gov.tr');
INSERT INTO HastaneIletisim(TelefonNumarasi, Email) VALUES('04782113044','ardahandhs1@saglik.gov.tr');
INSERT INTO HastaneIletisim(TelefonNumarasi, Email) VALUES('04782112821','ardahandhs1@saglik.gov.tr');
INSERT INTO HastaneIletisim(TelefonNumarasi, Email) VALUES('04782113500','ardahandhs1@saglik.gov.tr');
INSERT INTO HastaneIletisim(TelefonNumarasi, Email) VALUES('04782112909','ardahandhs1@saglik.gov.tr');
INSERT INTO HastaneIletisim(TelefonNumarasi, Email) VALUES('04782115448','ardahandhs1@saglik.gov.tr');

INSERT INTO HastaneIletisimTelefon(TelefonNumarasi, HospID) VALUES('02124129000',1);
INSERT INTO HastaneIletisimTelefon(TelefonNumarasi, HospID) VALUES('02124129000',1);
INSERT INTO HastaneIletisimTelefon(TelefonNumarasi, HospID) VALUES('02124147171',2);
INSERT INTO HastaneIletisimTelefon(TelefonNumarasi, HospID) VALUES('02242956000',3);
INSERT INTO HastaneIletisimTelefon(TelefonNumarasi, HospID) VALUES('02242956001',3);
INSERT INTO HastaneIletisimTelefon(TelefonNumarasi, HospID) VALUES('02242956002',3);
INSERT INTO HastaneIletisimTelefon(TelefonNumarasi, HospID) VALUES('02242956003',3);
INSERT INTO HastaneIletisimTelefon(TelefonNumarasi, HospID) VALUES('02242956004',3);
INSERT INTO HastaneIletisimTelefon(TelefonNumarasi, HospID) VALUES('02242948100',4);
INSERT INTO HastaneIletisimTelefon(TelefonNumarasi, HospID) VALUES('02242393636',4);
INSERT INTO HastaneIletisimTelefon(TelefonNumarasi, HospID) VALUES('02648884000',5);
INSERT INTO HastaneIletisimTelefon(TelefonNumarasi, HospID) VALUES('02642293160',6);
INSERT INTO HastaneIletisimTelefon(TelefonNumarasi, HospID) VALUES('02642293161',6);
INSERT INTO HastaneIletisimTelefon(TelefonNumarasi, HospID) VALUES('02282022011',7);
INSERT INTO HastaneIletisimTelefon(TelefonNumarasi, HospID) VALUES('03462217036',8);
INSERT INTO HastaneIletisimTelefon(TelefonNumarasi, HospID) VALUES('03462150833',9);
INSERT INTO HastaneIletisimTelefon(TelefonNumarasi, HospID) VALUES('03462150844',9);
INSERT INTO HastaneIletisimTelefon(TelefonNumarasi, HospID) VALUES('04782113044',10);
INSERT INTO HastaneIletisimTelefon(TelefonNumarasi, HospID) VALUES('04782112821',10);
INSERT INTO HastaneIletisimTelefon(TelefonNumarasi, HospID) VALUES('04782113500',10);
INSERT INTO HastaneIletisimTelefon(TelefonNumarasi, HospID) VALUES('04782112909',10);
INSERT INTO HastaneIletisimTelefon(TelefonNumarasi, HospID) VALUES('04782115448',10);

INSERT INTO Bulunma(HospID, PolyID) VALUES(1,1); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(1,2); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(1,3); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(1,4); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(1,5); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(1,6); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(1,7); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(1,8); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(1,9); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(1,10); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(1,11); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(1,12); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(2,1); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(2,2); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(2,3); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(2,4); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(2,5); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(2,6); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(2,7); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(2,8); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(2,9); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(2,10); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(2,11); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(2,12); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(3,1); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(3,2); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(3,3); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(3,4); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(3,5); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(3,6); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(3,7); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(3,8); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(3,9); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(3,10); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(3,11); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(3,12); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(4,1); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(4,2); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(4,3); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(4,4); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(4,5); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(4,6); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(4,7); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(4,8); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(4,9); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(4,10); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(4,11); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(4,12); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(5,1); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(5,2); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(5,3); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(5,4); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(5,5); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(5,6); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(5,7); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(5,8); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(5,9); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(5,10); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(5,11); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(5,12); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(6,1); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(6,2); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(6,3); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(6,4); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(6,5); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(6,6); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(6,7); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(6,8); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(6,9); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(6,10); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(6,11); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(6,12); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(7,1); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(7,2); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(7,3); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(7,4); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(7,5); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(7,6); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(7,7); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(7,8); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(7,9); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(7,10); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(7,11); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(7,12); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(8,1); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(8,2); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(8,3); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(8,4); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(8,5); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(8,6); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(8,7); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(8,8); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(8,9); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(8,10); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(8,11); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(8,12); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(9,1); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(9,2); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(9,3); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(9,4); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(9,5); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(9,6); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(9,7); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(9,8); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(9,9); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(9,10); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(9,11); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(9,12); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(10,1); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(10,2); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(10,3); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(10,4); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(10,5); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(10,6); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(10,7); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(10,8); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(10,9); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(10,10); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(10,11); 
INSERT INTO Bulunma(HospID, PolyID) VALUES(10,12); 

INSERT INTO Yonlendirme(YonlendirenPoliklinikID,YonlendirilenPoliklinikID,Tarih) VALUES(4,1,'21-02-2002');
INSERT INTO Yonlendirme(YonlendirenPoliklinikID,YonlendirilenPoliklinikID,Tarih) VALUES(2,1,'17-08-2023');
INSERT INTO Yonlendirme(YonlendirenPoliklinikID,YonlendirilenPoliklinikID,Tarih) VALUES(4,3,'18-05-2023');
INSERT INTO Yonlendirme(YonlendirenPoliklinikID,YonlendirilenPoliklinikID,Tarih) VALUES(5,6,'18-05-2023');


/*Aktif Randevularým Sorgusu*/
Select Hastane.Ad as HastaneAdý, Poliklinik.Ad as PolikinikAdý, Doktor.Ad as DoktorAdý, Randevu.Tarih as RandevuTarihi, Randevu.Saat as RandevuSaati
From Randevu
Join Poliklinik on Randevu.PolyID = Poliklinik.PolyID
Join Doktor on Randevu.DocTC = Doktor.DocTC
Join Hastane on Randevu.HospID = Hastane.HospID
Where Randevu.Tarih >= CURRENT_DATE and Randevu.Tarih >= CURRENT_TIME;

/*Geçmiþ Randevularým Sorgusu*/
Select Hastane.Ad as HastaneAdý, Poliklinik.Ad as PolikinikAdý, Doktor.Ad as DoktorAdý, Randevu.Tarih as RandevuTarihi, Randevu.Saat as RandevuSaati
From Randevu
Join Poliklinik on Randevu.PolyID = Poliklinik.PolyID
Join Doktor on Randevu.DocTC = Doktor.DocTC
Join Hastane on Randevu.HospID = Hastane.HospID
Where Randevu.Tarih <= CURRENT_DATE and Randevu.Tarih <= CURRENT_TIME;