<?php  
	include "../config/koneksi.php";
	include "../library/fungsi.php";
	@session_start();
	date_default_timezone_set("Asia/Jakarta");

	@$aksi = new oop();
	@$bln = $_GET['bln'];
	@$thn = $_GET['thn'];

	@$cari="WHERE MONTH(tanggal_perjalanan)='$bln' AND YEAR(tanggal_perjalanan)='$thn'";
	switch ($bln) {
		case '1': @$bulaninitext="Januari"; break;
		case '2': @$bulaninitext="Februari"; break;
		case '3': @$bulaninitext="Maret"; break;
		case '4': @$bulaninitext="April"; break;
		case '5': @$bulaninitext="Mei"; break;
		case '6': @$bulaninitext="Juni"; break;
		case '7': @$bulaninitext="Juli"; break;
		case '8': @$bulaninitext="Agustus"; break;
		case '9': @$bulaninitext="September"; break;
		case '10': @$bulaninitext="Oktober"; break;
		case '11': @$bulaninitext="Novemmber"; break;
		case '12': @$bulaninitext="Desember"; break;
		default: @$bulaninitext=""; break;
	}

	$blnini=date("m");
	switch ($blnini) {
		case '1': @$blnskrg="Januari"; break;
		case '2': @$blnskrg="Februari"; break;
		case '3': @$blnskrg="Maret"; break;
		case '4': @$blnskrg="April"; break;
		case '5': @$blnskrg="Mei"; break;
		case '6': @$blnskrg="Juni"; break;
		case '7': @$blnskrg="Juli"; break;
		case '8': @$blnskrg="Agustus"; break;
		case '9': @$blnskrg="September"; break;
		case '10': @$blnskrg="Oktober"; break;
		case '11': @$blnskrg="Novemmber"; break;
		case '12': @$blnskrg="Desember"; break;
		default: @$blnskrg=""; break;
	}
	$hrini=date("N");
	switch ($hrini) {
		case '1': @$hrskrg="Senin"; break;
		case '2': @$hrskrg="Selasa"; break;
		case '3': @$hrskrg="Rabu"; break;
		case '4': @$hrskrg="Kamis"; break;
		case '5': @$hrskrg="Jumat"; break;
		case '6': @$hrskrg="Sabtu"; break;
		case '7': @$hrskrg="Minggu"; break;
		default: @$hrskrg=""; break;
	}
?>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, intial-scale=1">
	<title>Cetak Perjalanan Bulan <?php echo $bulaninitext." ".$thn; ?></title>
<body onload="window.print()" style="font-family:'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma,  sans-serif;width:21cm;">
		<table width="100%" border="0" cellpadding="2" cellspacing="0">
			<thead>
				<tr>
				 	<td colspan="8">
				 		<h1 style="margin:0">PO. SETIA BHAKTI</h1>
				 		<h4 style="margin:0;margin-top:4px;">JL. Melawai Raya No.70 Jakarta Pusat</h4>
				 	</td>
				</tr>
				
				<tr><td colspan="8"><hr></td></tr>
				
				<tr>
				 	<td colspan="8" align="center"><h3 align="center">Daftar Riwayat Perjalanan BUS - Bulan <?php echo @$bulaninitext." ".@$thn; ?></h3></td>
				</tr>

				<tr>
					<th width="5%"  style="border:1px solid black">No.</th>
					<th width="10%"  style="border:1px solid black">Tanggal Pemberangkatan</th>
					<th width="10%"  style="border:1px solid black">Kode BUS</th>
					<th width="15%"  style="border:1px solid black">Sopir Pertama</th>
					<th width="15%"  style="border:1px solid black">Sopir Kedua</th>
					<th width="6%" style="border:1px solid black">Jumlah Penumpang</th>
					<th width="10%" style="border:1px solid black">Jumlah Pendapatan</th>
					<th  width="15%"  style="border:1px solid black">Lokasi Terakhir</th>
				</tr>
			</thead>
			<tbody>	
				<?php  
					$sql = $aksi->tampil("tbl_perjalanan",$cari,"ORDER BY tanggal_perjalanan DESC");	
					@$no = 0;
					if ($sql =="") {
						echo "<tr><td align='center' colspan='9'>Data Tidak Ada</td></tr>";
					}else{
						foreach ($sql as $data) {
							$no++;
							@$lok = mysql_fetch_array(mysql_query("SELECT * FROM tbl_perjalanan_detail WHERE kd_perjalanan = '$data[0]' ORDER BY waktu_lokasi DESC"));
							@$pir1 = mysql_fetch_array(mysql_query("SELECT * FROM tbl_sopir WHERE kd_sopir = '$data[3]'"));
							@$pir2 = mysql_fetch_array(mysql_query("SELECT * FROM tbl_sopir WHERE kd_sopir = '$data[4]'"));
							
				?>
					<tr>
						<td align="center" style="border:1px solid black"><?php echo $no; ?>.</td>
						<td align="center" style="border:1px solid black"><?php echo $data[7]; ?></td>
						<td align="center" style="border:1px solid black"><?php echo $data[2]; ?></td>
						<td align="center" style="border:1px solid black"><?php echo $pir1['nama']; ?></td>
						<td align="center" style="border:1px solid black"><?php echo $pir2['nama']; ?></td>
						<td align="right" style="border:1px solid black"><?php echo number_format($data[5],0,'','.'); ?></td>
						<td align="right" style="border:1px solid black"><?php echo number_format($data[6],0,'','.'); ?></td>
						<td align="center" style="border:1px solid black"><?php echo $lok[2]; ?></td>
					</tr>
				<?php } }  ?>
			</tbody>

		</table>
		<table align="right" style="margin-right:40px;">
			<tr><td rowspan="10" width="50px"></td><td>&nbsp;</td></tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td align="center"><?php echo $hrskrg.", ".date(" j ").$blnskrg.date(" Y "); ?></td>
			</tr>
			<tr>
				<td align="center">Hormat Saya,</td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr><td>&nbsp;</td></tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td align="center"><?php echo $_SESSION['nama']; ?></td>
			</tr>
			<tr><td>&nbsp;</td></tr>
		</table>
</body>
</html>