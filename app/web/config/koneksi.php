<?php 
	$host = "localhost"; 
	$username = "root"; 
	$password = ""; 
	$database = "db_otobis_dun";
	mysql_connect($host,$username,$password) or die ("Harap Periksa Koneksi Database Anda!");
	mysql_select_db($database) or die ("Database Tidak Ditemukan!");
?>