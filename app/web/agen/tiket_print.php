<?php  
	include "../config/koneksi.php";
	include "../library/fungsi.php";

	@$aksi = new oop();

	@$kd_pembelian = $_GET['kd_pembelian'];
	@$sql = mysql_query("SELECT * FROM tbl_pembelian WHERE kd_pembelian = '$kd_pembelian' ");
	@$data = mysql_fetch_array($sql);

	@$sql = mysql_query("SELECT * FROM tbl_pembelian_detail WHERE kd_pembelian='$kd_pembelian'");
	@$r = mysql_fetch_array($sql);
?>
 <!DOCTYPE html>
 <html>
 <head>
 	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE-edge">
	<meta name="viewport" content="window=device-width, initial-scale=1">
	<title>Tiket Cetak</title>
	<link rel="stylesheet" type="text/css" href="../css/style.css">
 </head>
 <body onload="window.print()" style="font-family:monospace;">
 <br><br><br><br>
<table width="100%" border="0" cellpadding="2" cellspacing="0">
	<tr><td colspan="6" align="center"><strong>Data Pembelian Tiket BUS <?php echo $r['nama_trayek']; ?></strong></td></tr>
	<tr>
		<td colspan="6">
			<?php for ($i=1; $i <=102 ; $i++) { 
				echo "-";
			} ?>
		</td>
	</tr>
	<tr>
		<td>Nama</td>
		<td colspan="6">: <?php echo $data['nama']; ?></td>
	</tr>
	<tr>
		<td>Alamat Lengkap</td>
		<td colspan="6">: <?php echo $data['alamat']; ?></td>
	</tr>
	<tr>
		<td>No. Handphone</td>
		<td colspan="6">: <?php echo $data['no_hp']; ?></td>
	</tr>
	<tr>
		<td>Email</td>
		<td colspan="6">: <?php echo $data['email']; ?></td>
	</tr>
	<tr>
		<td colspan="6">
			<?php for ($i=1; $i <=102 ; $i++) { 
				echo "-";
			} ?>
		</td>
	</tr>
	<tbody>
		<tr align="center">
			<td>Kode Pembelian</td>
			<td align="left">: <?php echo $data['kd_pembelian']; ?></td>
			<td>Waktu Pembelian</td>
			<td align="left">: <?php echo $data['waktu_pembelian']; ?></td>
			<td>Kode Tiket</td>
			<td align="left">: <?php echo $data['kd_tiket']; ?></td>
		</tr>
	<tr>
		<td colspan="6">
			<?php for ($i=1; $i <=102 ; $i++) { 
				echo "-";
			} ?>
		</td>
	</tr>
	</tbody>
	</table>
	<table width="100%" border="0" cellpadding="2" cellspacing="0">
		<tr>
			<td  style="border:1px solid black"><center>Nama BUS</center></td>
			<td  style="border:1px solid black"><center>No. Polisi</center></td>
			<td  style="border:1px solid black"><center>Kelas BUS</center></td>
			<td  style="border:1px solid black"><center>Trayek</center></td>
			<td style="border:1px solid black"><center>Tanggal Berangkat</center></td>
			<td style="border:1px solid black"><center>Jam</center></td>
		</tr>
		<tr>
			<td  style="border:1px solid black"><?php echo $r['nama_bus']; ?></td>
			<td  style="border:1px solid black"><?php echo $r['no_polisi']; ?></td>
			<td  style="border:1px solid black"><?php echo $r['nama_kelas']; ?></td>
			<td  style="border:1px solid black"><?php echo $r['nama_trayek']; ?></td>
			<td  style="border:1px solid black"><?php echo $r['tanggal_berangkat']; ?></td>
			<td  style="border:1px solid black"><?php echo $r['jam_berangkat']; ?></td>
		</tr>
	</table>
	<table align="right" style="margin-right:100px;margin-top:5px;">
		<tr>
			<td rowspan="4"></td>
			<td  align="right" >Harga Tiket</td>
			<td align="left">: Rp. <?php echo number_format($data['harga'],0,'','.'); ?></td>
		</tr>
		<tr>
			<td  align="right" >Jumlah Beli</td>
			<td align="left">: <?php echo number_format($data['jumlah_beli'],0,'','.'); ?></td>
		</tr>
		<tr>
			<td  align="right" >Diskon</td>
			<td align="left">: <?php echo number_format($r['diskon'],0,'','.'); ?></td>
		</tr>
		<tr>
			<td  align="right" >Total Harga</td>
			<td align="left">: Rp. <?php echo number_format($data['total_harga'],0,'','.'); ?></td>
		</tr>
	</table>

 </body>
 </html>