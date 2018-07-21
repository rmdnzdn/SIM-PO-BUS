-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Inang: 127.0.0.1
-- Waktu pembuatan: 07 Jul 2017 pada 09.32
-- Versi Server: 5.5.27
-- Versi PHP: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Basis data: `db_otobis_dun`
--

-- --------------------------------------------------------

--
-- Stand-in structure for view `qw_bus`
--
CREATE TABLE IF NOT EXISTS `qw_bus` (
`kd_bus` varchar(12)
,`nama` varchar(50)
,`no_polisi` varchar(12)
,`kd_kelas` varchar(5)
,`nama_kelas` varchar(50)
,`harga_kelas` int(11)
,`status_bus` varchar(15)
);
-- --------------------------------------------------------

--
-- Stand-in structure for view `qw_tiket`
--
CREATE TABLE IF NOT EXISTS `qw_tiket` (
`kd_tiket` varchar(14)
,`kd_bus` varchar(12)
,`nama` varchar(50)
,`no_polisi` varchar(12)
,`kd_trayek` int(11)
,`asal` varchar(50)
,`tujuan` varchar(50)
,`harga_trayek` int(11)
,`nama_trayek` varchar(100)
,`kd_kelas` varchar(5)
,`nama_kelas` varchar(50)
,`harga_kelas` int(11)
,`tanggal_berangkat` date
,`jam_berangkat` varchar(5)
,`harga` int(11)
,`diskon` int(3)
,`jumlah_kursi` int(3)
,`status` varchar(20)
);
-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_bus`
--

CREATE TABLE IF NOT EXISTS `tbl_bus` (
  `kd_bus` varchar(12) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `no_polisi` varchar(12) NOT NULL,
  `kd_kelas` varchar(5) NOT NULL,
  `status_bus` varchar(15) NOT NULL,
  PRIMARY KEY (`kd_bus`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tbl_bus`
--

INSERT INTO `tbl_bus` (`kd_bus`, `nama`, `no_polisi`, `kd_kelas`, `status_bus`) VALUES
('BUS201706001', 'Rosalia Indah JET BUS 2', 'F1999DUN', 'KLS01', 'tersedia'),
('BUS201706002', 'Lorena Lancar Terus 123', 'B7777FRE', 'KLS02', 'tersedia'),
('BUS201706003', 'Pariwisata LIMAS A33', 'D2349WE', 'KLS01', 'tersedia'),
('BUS201706004', 'Doa Ibu 45', 'F4567DA', 'KLS04', 'tersedia'),
('BUS201706005', 'Limas Starlion', 'A1234AF', 'KLS03', 'tiket'),
('BUS201706006', 'Gunung Harta 25A', '2341', 'KLS04', 'tersedia');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_kelas`
--

CREATE TABLE IF NOT EXISTS `tbl_kelas` (
  `kd_kelas` varchar(5) NOT NULL,
  `nama_kelas` varchar(50) NOT NULL,
  `harga_kelas` int(11) NOT NULL,
  PRIMARY KEY (`kd_kelas`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tbl_kelas`
--

INSERT INTO `tbl_kelas` (`kd_kelas`, `nama_kelas`, `harga_kelas`) VALUES
('KLS01', 'Super Executive', 100000),
('KLS02', 'Executive', 75000),
('KLS03', 'Bisnis', 50000),
('KLS04', 'Ekonomi', 25000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_pembelian`
--

CREATE TABLE IF NOT EXISTS `tbl_pembelian` (
  `kd_pembelian` varchar(13) NOT NULL,
  `kd_tiket` varchar(14) NOT NULL,
  `tanggal_pembelian` date NOT NULL,
  `waktu_pembelian` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `nama` varchar(100) NOT NULL,
  `no_hp` varchar(13) NOT NULL,
  `email` varchar(100) NOT NULL,
  `alamat` text NOT NULL,
  `harga` int(11) NOT NULL,
  `jumlah_beli` int(3) NOT NULL,
  `total_harga` int(11) NOT NULL,
  `id_agen` varchar(12) NOT NULL,
  PRIMARY KEY (`kd_pembelian`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tbl_pembelian`
--

INSERT INTO `tbl_pembelian` (`kd_pembelian`, `kd_tiket`, `tanggal_pembelian`, `waktu_pembelian`, `nama`, `no_hp`, `email`, `alamat`, `harga`, `jumlah_beli`, `total_harga`, `id_agen`) VALUES
('PT28062017001', 'TKT20170628002', '2017-06-28', '2017-06-27 23:20:27', 'aku', '123', 'aku@gmail.com', 'aku dirumah', 400000, 10, 4000000, '3'),
('PT28062017002', 'TKT20170628001', '2017-06-28', '2017-06-27 23:21:14', 'dia', '321', 'dia@gmail.com', 'dia ada di pantai', 450000, 10, 4500000, '3'),
('PT28062017003', 'TKT20170628003', '2017-06-28', '2017-06-27 23:23:14', 'ramdan', '083811941421', 'radmanzidun@gmail.com', 'jalan cikopo selatan  RT 01/01 desa sukamahi kec.megamendung kab.Bogor', 325000, 20, 6500000, '3'),
('PT29062017001', 'TKT20170629001', '2017-06-29', '2017-06-28 18:23:13', 'zidun', '084121', 'radmanzidun@gmail.com', 'ciawi', 325000, 10, 3250000, '3'),
('PT29062017002', 'TKT20170629001', '2017-06-29', '2017-06-28 18:24:06', 'muhammad ramdan', '08481231', 'ramdan@gmail.com', 'sukabirus', 325000, 15, 4875000, '3'),
('PT29062017003', 'TKT20170629002', '2017-06-29', '2017-06-28 18:54:58', 'ura', '08951898789', 'ura@gmail.com', 'pasir munacang', 500000, 20, 10000000, '3'),
('PT29062017004', 'TKT20170629003', '2017-06-29', '2017-06-28 23:09:05', 'muhammad zidun', '083811941421', 'ramdanzidun@gmail.com', 'sukabirus atas ', 300000, 10, 3000000, '3'),
('PT29062017005', 'TKT20170629004', '2017-06-29', '2017-06-28 23:13:40', 'ura', '091231', 'ura123@gmail.com', 'pasir muncang', 400000, 30, 12000000, '3'),
('PT29062017006', 'TKT20170629004', '2017-06-29', '2017-06-28 23:17:50', 'endan', '25121999', 'endan@gmail.com', 'dibumi atau diakhir', 400000, 2, 800000, '3'),
('PT30062017001', 'TKT20170629003', '2017-06-30', '2017-06-30 09:48:53', 'momo', '123', 'momo@gmail.com', 'momo dirumah', 300000, 7, 2100000, '6'),
('PT30062017002', 'TKT20170629003', '2017-06-30', '2017-06-30 09:48:53', 'momo', '123', 'momo@gmail.com', 'momo dirumah', 300000, 7, 2100000, '6'),
('PT30062017003', 'TKT20170629003', '2017-06-30', '2017-06-30 09:50:02', 'wer', '5325', '34@ga.col', '432', 300000, 1, 300000, '4');

--
-- Trigger `tbl_pembelian`
--
DROP TRIGGER IF EXISTS `hapus_beli`;
DELIMITER //
CREATE TRIGGER `hapus_beli` AFTER DELETE ON `tbl_pembelian`
 FOR EACH ROW UPDATE tbl_tiket SET jumlah_kursi = jumlah_kursi+OLD.jumlah_beli, status="tersedia" WHERE kd_tiket = OLD.kd_tiket
//
DELIMITER ;
DROP TRIGGER IF EXISTS `tambah_beli`;
DELIMITER //
CREATE TRIGGER `tambah_beli` AFTER INSERT ON `tbl_pembelian`
 FOR EACH ROW UPDATE tbl_tiket SET jumlah_kursi = jumlah_kursi - NEW.jumlah_beli WHERE kd_tiket = NEW.kd_tiket
//
DELIMITER ;
DROP TRIGGER IF EXISTS `ubah_beli`;
DELIMITER //
CREATE TRIGGER `ubah_beli` AFTER UPDATE ON `tbl_pembelian`
 FOR EACH ROW UPDATE tbl_tiket SET jumlah_kursi = (jumlah_kursi+OLD.jumlah_beli)-NEW.jumlah_beli WHERE kd_tiket = OLD.kd_tiket
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_pembelian_detail`
--

CREATE TABLE IF NOT EXISTS `tbl_pembelian_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `kd_pembelian` varchar(13) NOT NULL,
  `nama_bus` varchar(50) NOT NULL,
  `no_polisi` varchar(12) NOT NULL,
  `nama_kelas` varchar(50) NOT NULL,
  `asal` varchar(50) NOT NULL,
  `tujuan` varchar(50) NOT NULL,
  `nama_trayek` varchar(100) NOT NULL,
  `tanggal_berangkat` date NOT NULL,
  `jam_berangkat` varchar(5) NOT NULL,
  `harga_tiket` int(11) NOT NULL,
  `diskon` int(3) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=13 ;

--
-- Dumping data untuk tabel `tbl_pembelian_detail`
--

INSERT INTO `tbl_pembelian_detail` (`id`, `kd_pembelian`, `nama_bus`, `no_polisi`, `nama_kelas`, `asal`, `tujuan`, `nama_trayek`, `tanggal_berangkat`, `jam_berangkat`, `harga_tiket`, `diskon`) VALUES
(1, 'PT28062017001', 'Pariwisata LIMAS A33', 'D2349WE', 'Super Executive', 'Bogor', 'Bandung', 'Bogor - Bandung', '2017-07-01', '09:00', 400000, 0),
(2, 'PT28062017002', 'Rosalia Indah JET BUS 2', 'F1999DUN', 'Super Executive', 'Jakarta', 'Surabaya', 'Jakarta - Surabaya', '2017-06-30', '12:00', 450000, 0),
(3, 'PT28062017003', 'Lorena Lancar Terus 123', 'B7777FRE', 'Executive', 'Bogor', 'Yogyakarta', 'Bogor - Yogyakarta', '2017-06-28', '12:00', 325000, 0),
(4, 'PT29062017001', 'Doa Ibu 45', 'F4567DA', 'Ekonomi', 'Bogor', 'Bandung', 'Bogor - Bandung', '2017-06-30', '06:30', 325000, 0),
(5, 'PT29062017002', 'Doa Ibu 45', 'F4567DA', 'Ekonomi', 'Bogor', 'Bandung', 'Bogor - Bandung', '2017-06-30', '06:30', 325000, 0),
(6, 'PT29062017003', 'Pariwisata LIMAS A33', 'D2349WE', 'Super Executive', 'Jakarta', 'Bali', 'Jakarta - Bali', '2017-06-29', '08:00', 500000, 0),
(7, 'PT29062017004', 'Limas Starlion', 'A1234AF', 'Bisnis', 'Bogor', 'Yogyakarta', 'Bogor - Yogyakarta', '2017-06-30', '12:45', 300000, 0),
(8, 'PT29062017005', 'Rosalia Indah JET BUS 2', 'F1999DUN', 'Super Executive', 'Bogor', 'Bandung', 'Bogor - Bandung', '2017-07-02', '08:30', 400000, 0),
(9, 'PT29062017006', 'Rosalia Indah JET BUS 2', 'F1999DUN', 'Super Executive', 'Bogor', 'Bandung', 'Bogor - Bandung', '2017-07-02', '08:30', 400000, 0),
(10, 'PT30062017001', 'Limas Starlion', 'A1234AF', 'Bisnis', 'Bogor', 'Yogyakarta', 'Bogor - Yogyakarta', '2017-06-30', '12:45', 300000, 0),
(11, 'PT30062017002', 'Limas Starlion', 'A1234AF', 'Bisnis', 'Bogor', 'Yogyakarta', 'Bogor - Yogyakarta', '2017-06-30', '12:45', 300000, 0),
(12, 'PT30062017003', 'Limas Starlion', 'A1234AF', 'Bisnis', 'Bogor', 'Yogyakarta', 'Bogor - Yogyakarta', '2017-06-30', '12:45', 300000, 0);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_perjalanan`
--

CREATE TABLE IF NOT EXISTS `tbl_perjalanan` (
  `kd_perjalanan` varchar(23) NOT NULL,
  `kd_tiket` varchar(14) NOT NULL,
  `kd_bus` varchar(12) NOT NULL,
  `sopir_1` varchar(12) NOT NULL,
  `sopir_2` varchar(12) NOT NULL,
  `jumlah_penumpang` int(3) NOT NULL,
  `pendapatan` int(11) NOT NULL,
  `tanggal_perjalanan` date NOT NULL,
  `status` text NOT NULL,
  PRIMARY KEY (`kd_perjalanan`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tbl_perjalanan`
--

INSERT INTO `tbl_perjalanan` (`kd_perjalanan`, `kd_tiket`, `kd_bus`, `sopir_1`, `sopir_2`, `jumlah_penumpang`, `pendapatan`, `tanggal_perjalanan`, `status`) VALUES
('2017-06-2812:00B7777FRE', 'TKT20170628003', 'BUS201706002', 'SPR201706002', 'SPR201706001', 20, 6500000, '2017-06-28', 'selesai'),
('2017-06-3012:00F1999DUN', 'TKT20170628001', 'BUS201706001', 'SPR201706003', 'SPR201706004', 10, 4500000, '2017-06-30', 'selesai'),
('2017-07-0109:00D2349WE', 'TKT20170628002', 'BUS201706003', 'SPR201706001', 'SPR201706003', 10, 4000000, '2017-07-01', 'selesai'),
('2017-06-3006:30F4567DA', 'TKT20170629001', 'BUS201706004', 'SPR201706004', 'SPR201706001', 25, 8125000, '2017-06-30', 'selesai'),
('2017-06-2908:00D2349WE', 'TKT20170629002', 'BUS201706003', 'SPR201706002', 'SPR201706004', 20, 10000000, '2017-06-29', 'selesai'),
('2017-07-0208:30F1999DUN', 'TKT20170629004', 'BUS201706001', 'SPR201706004', 'SPR201706002', 32, 12800000, '2017-07-02', 'selesai');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_perjalanan_detail`
--

CREATE TABLE IF NOT EXISTS `tbl_perjalanan_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `kd_perjalanan` varchar(23) NOT NULL,
  `lokasi` text NOT NULL,
  `waktu_lokasi` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=30 ;

--
-- Dumping data untuk tabel `tbl_perjalanan_detail`
--

INSERT INTO `tbl_perjalanan_detail` (`id`, `kd_perjalanan`, `lokasi`, `waktu_lokasi`) VALUES
(1, '2017-06-2812:00B7777FRE', 'pemberangkatan awal', '2017-06-27 23:24:02'),
(2, '2017-06-2812:00B7777FRE', 'Pom bensin ciawi 123', '2017-06-27 23:26:37'),
(3, '2017-06-2812:00B7777FRE', 'masuk gerbang TOL Cipali ', '2017-06-27 23:27:03'),
(5, '2017-06-2812:00B7777FRE', 'Rest Area Tol Cipali KM 59', '2017-06-27 23:27:30'),
(8, '2017-06-2812:00B7777FRE', 'Pom Bensin Yogyakarkata', '2017-06-27 23:31:30'),
(7, '2017-06-2812:00B7777FRE', 'Keluar TOL cipali', '2017-06-27 23:30:54'),
(9, '2017-06-2812:00B7777FRE', 'Terminal Yogykarta', '2017-06-27 23:31:43'),
(10, '2017-06-2812:00B7777FRE', 'Sampai Tujuan', '2017-06-27 23:31:51'),
(11, '2017-06-3012:00F1999DUN', 'pemberangkatan awal', '2017-06-27 23:51:20'),
(12, '2017-06-3012:00F1999DUN', 'Sampai Tujuan', '2017-06-28 16:13:37'),
(13, '2017-07-0109:00D2349WE', 'pemberangkatan awal', '2017-06-28 16:24:48'),
(14, '2017-07-0109:00D2349WE', 'Sampai Tujuan', '2017-06-28 16:26:18'),
(15, '2017-06-3006:30F4567DA', 'pemberangkatan awal', '2017-06-28 18:29:05'),
(16, '2017-06-3006:30F4567DA', 'Rest Area cimacan- cinajur', '2017-06-28 18:29:55'),
(17, '2017-06-3006:30F4567DA', 'pom bensin rajamandala', '2017-06-28 18:31:53'),
(18, '2017-06-3006:30F4567DA', 'terminal buah batu', '2017-06-28 18:32:21'),
(19, '2017-06-3006:30F4567DA', 'Sampai Tujuan', '2017-06-28 18:32:28'),
(20, '2017-06-2908:00D2349WE', 'pemberangkatan awal', '2017-06-28 18:56:35'),
(21, '2017-06-2908:00D2349WE', 'Masuk Tol Cikampek', '2017-06-28 18:57:24'),
(22, '2017-06-2908:00D2349WE', 'Rest Area Tol cikampek KM 100', '2017-06-28 19:09:22'),
(23, '2017-06-2908:00D2349WE', 'Pom Bensin tol Cipali', '2017-06-28 19:13:29'),
(24, '2017-06-2908:00D2349WE', 'Pelabuhan Bali', '2017-06-28 19:14:32'),
(25, '2017-06-2908:00D2349WE', 'Terminal Nusa Dua Bali', '2017-06-28 19:14:49'),
(26, '2017-06-2908:00D2349WE', 'Sampai Tujuan', '2017-06-28 19:14:59'),
(27, '2017-07-0208:30F1999DUN', 'pemberangkatan awal', '2017-06-28 23:18:54'),
(28, '2017-07-0208:30F1999DUN', 'Res', '2017-07-07 03:30:10'),
(29, '2017-07-0208:30F1999DUN', 'Sampai Tujuan', '2017-07-07 03:30:52');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_sopir`
--

CREATE TABLE IF NOT EXISTS `tbl_sopir` (
  `kd_sopir` varchar(12) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `alamat` text NOT NULL,
  `no_hp` varchar(13) NOT NULL,
  `no_sim` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `jabatan` varchar(5) NOT NULL,
  `status_sopir` varchar(20) NOT NULL,
  PRIMARY KEY (`kd_sopir`),
  UNIQUE KEY `username` (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tbl_sopir`
--

INSERT INTO `tbl_sopir` (`kd_sopir`, `nama`, `alamat`, `no_hp`, `no_sim`, `username`, `password`, `jabatan`, `status_sopir`) VALUES
('SPR201706002', 'sopir Cantik', 'bogor', '456', '456', 'sop', 'pir123', 'sopir', 'tersedia'),
('SPR201706003', 'Upir', 'lebka', '43421', '12332', 'ko', 'ko', 'sopir', 'tersedia'),
('SPR201706004', 'asf', 'd', '0', '3', '75', '74', 'sopir', 'tersedia'),
('SPR201706001', 'Sopir Ganzzz', 'ciawi', '123', '123', 'sopir', 'sopir123', 'sopir', 'tersedia'),
('SPR201706005', 'Sopir Baik', 'jakarta', '09471', '093123521', 'baik', 'baik123', 'sopir', 'tersedia'),
('SPR201706006', 'Sopir Jahat', 'bogor', '0023484', '43241351', 'jahat', 'jahat123', 'sopir', 'tersedia');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_tiket`
--

CREATE TABLE IF NOT EXISTS `tbl_tiket` (
  `kd_tiket` varchar(14) NOT NULL,
  `kd_bus` varchar(12) NOT NULL,
  `kd_trayek` int(11) NOT NULL,
  `kd_kelas` varchar(5) NOT NULL,
  `tanggal_berangkat` date NOT NULL,
  `jam_berangkat` varchar(5) NOT NULL,
  `harga` int(11) NOT NULL,
  `diskon` int(3) NOT NULL,
  `jumlah_kursi` int(3) NOT NULL,
  `status` varchar(20) NOT NULL,
  PRIMARY KEY (`kd_tiket`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tbl_tiket`
--

INSERT INTO `tbl_tiket` (`kd_tiket`, `kd_bus`, `kd_trayek`, `kd_kelas`, `tanggal_berangkat`, `jam_berangkat`, `harga`, `diskon`, `jumlah_kursi`, `status`) VALUES
('TKT20170628001', 'BUS201706001', 3, 'KLS01', '2017-06-30', '12:00', 450000, 0, 30, 'selesai'),
('TKT20170628002', 'BUS201706003', 1, 'KLS01', '2017-07-01', '09:00', 400000, 0, 35, 'selesai'),
('TKT20170628003', 'BUS201706002', 4, 'KLS02', '2017-06-28', '12:00', 325000, 0, 0, 'habis'),
('TKT20170629001', 'BUS201706004', 1, 'KLS04', '2017-06-30', '06:30', 325000, 0, 15, 'selesai'),
('TKT20170629002', 'BUS201706003', 2, 'KLS01', '2017-06-29', '08:00', 500000, 0, 10, 'selesai'),
('TKT20170629003', 'BUS201706005', 4, 'KLS03', '2017-07-10', '12:45', 300000, 0, 5, 'tersedia'),
('TKT20170629004', 'BUS201706001', 1, 'KLS01', '2017-07-02', '08:30', 400000, 0, 18, 'selesai');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_trayek`
--

CREATE TABLE IF NOT EXISTS `tbl_trayek` (
  `kd_trayek` int(11) NOT NULL AUTO_INCREMENT,
  `asal` varchar(50) NOT NULL,
  `tujuan` varchar(50) NOT NULL,
  `harga_trayek` int(11) NOT NULL,
  `nama_trayek` varchar(100) NOT NULL,
  PRIMARY KEY (`kd_trayek`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data untuk tabel `tbl_trayek`
--

INSERT INTO `tbl_trayek` (`kd_trayek`, `asal`, `tujuan`, `harga_trayek`, `nama_trayek`) VALUES
(1, 'Bogor', 'Bandung', 300000, 'Bogor - Bandung'),
(2, 'Jakarta', 'Bali', 400000, 'Jakarta - Bali'),
(3, 'Jakarta', 'Surabaya', 350000, 'Jakarta - Surabaya'),
(4, 'Bogor', 'Yogyakarta', 250000, 'Bogor - Yogyakarta'),
(5, 'Surabaya', 'Jakarta', 360000, 'Surabaya - Jakarta'),
(6, 'Palembang', 'Jakarta', 450000, 'Palembang - Jakarta');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_user`
--

CREATE TABLE IF NOT EXISTS `tbl_user` (
  `kd_user` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(100) NOT NULL,
  `alamat` text NOT NULL,
  `email` varchar(100) NOT NULL,
  `no_hp` varchar(13) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `jabatan` varchar(8) NOT NULL,
  PRIMARY KEY (`kd_user`),
  UNIQUE KEY `username` (`username`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data untuk tabel `tbl_user`
--

INSERT INTO `tbl_user` (`kd_user`, `nama`, `alamat`, `email`, `no_hp`, `username`, `password`, `jabatan`) VALUES
(1, 'Muhammad Ramdan zidun', 'Ciawi', 'ramdanzidun@gmail.com', '083811941421', 'admin', 'admin123', 'admin'),
(2, 'Direktur ', 'di bumi,air dan udara', 'direk@gmail.com', '0214124', 'direktur', 'direktur123', 'direktur'),
(3, 'Haji Barkah', 'ciawi', 'barcha@gmail.com', '0934', 'agen', 'agen123', 'agen'),
(4, 'uu', 'uu', 'uu', '092', 'ugen', 'ugen123', 'agen'),
(5, 'Zidun', 'ciaiw', 'roro', '093', 'zidun', 'zidun123', 'admin'),
(6, 'aku', 'aku', 'aku@gmail.com', '09334', 'aku', 'aku123', 'agen');

-- --------------------------------------------------------

--
-- Struktur untuk view `qw_bus`
--
DROP TABLE IF EXISTS `qw_bus`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `qw_bus` AS select `tbl_bus`.`kd_bus` AS `kd_bus`,`tbl_bus`.`nama` AS `nama`,`tbl_bus`.`no_polisi` AS `no_polisi`,`tbl_kelas`.`kd_kelas` AS `kd_kelas`,`tbl_kelas`.`nama_kelas` AS `nama_kelas`,`tbl_kelas`.`harga_kelas` AS `harga_kelas`,`tbl_bus`.`status_bus` AS `status_bus` from (`tbl_bus` join `tbl_kelas` on((`tbl_kelas`.`kd_kelas` = `tbl_bus`.`kd_kelas`)));

-- --------------------------------------------------------

--
-- Struktur untuk view `qw_tiket`
--
DROP TABLE IF EXISTS `qw_tiket`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `qw_tiket` AS select `tbl_tiket`.`kd_tiket` AS `kd_tiket`,`tbl_bus`.`kd_bus` AS `kd_bus`,`tbl_bus`.`nama` AS `nama`,`tbl_bus`.`no_polisi` AS `no_polisi`,`tbl_trayek`.`kd_trayek` AS `kd_trayek`,`tbl_trayek`.`asal` AS `asal`,`tbl_trayek`.`tujuan` AS `tujuan`,`tbl_trayek`.`harga_trayek` AS `harga_trayek`,`tbl_trayek`.`nama_trayek` AS `nama_trayek`,`tbl_kelas`.`kd_kelas` AS `kd_kelas`,`tbl_kelas`.`nama_kelas` AS `nama_kelas`,`tbl_kelas`.`harga_kelas` AS `harga_kelas`,`tbl_tiket`.`tanggal_berangkat` AS `tanggal_berangkat`,`tbl_tiket`.`jam_berangkat` AS `jam_berangkat`,`tbl_tiket`.`harga` AS `harga`,`tbl_tiket`.`diskon` AS `diskon`,`tbl_tiket`.`jumlah_kursi` AS `jumlah_kursi`,`tbl_tiket`.`status` AS `status` from (((`tbl_tiket` join `tbl_bus` on((`tbl_bus`.`kd_bus` = `tbl_tiket`.`kd_bus`))) join `tbl_trayek` on((`tbl_trayek`.`kd_trayek` = `tbl_tiket`.`kd_trayek`))) join `tbl_kelas` on((`tbl_kelas`.`kd_kelas` = `tbl_tiket`.`kd_kelas`)));

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
