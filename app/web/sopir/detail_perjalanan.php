<?php  
	@$kd_perjalanan = $_GET['kd_perjalanan'];

	@$jalan = mysql_fetch_array(mysql_query("SELECT * FROM tbl_perjalanan WHERE kd_perjalanan = '$kd_perjalanan'"));

	@$pir1 = mysql_fetch_array(mysql_query("SELECT * FROM tbl_sopir WHERE kd_sopir = '$jalan[3]'"));
	@$pir2 = mysql_fetch_array(mysql_query("SELECT * FROM tbl_sopir WHERE kd_sopir = '$jalan[4]'"));
	
	@$tiket = mysql_fetch_array(mysql_query("SELECT * FROM qw_tiket WHERE kd_tiket = '$jalan[1]'"));
?>
 <!DOCTYPE html>
 <html>
 <head>
 	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE-edge">
	<meta name="viewport" content="window=device-width, initial-scale=1">
	<title>Detail Penjualan TIKET</title>
 </head>
 <body>
 <br><br><br><br>
	<div class="container">
		<div class="row">
			<div class="panel panel-primary">
				<div class="panel-heading"><center>Detail Perjalanan :&nbsp;<?php echo @$kd_perjalanan ?></center></div>
			<div class="panel-body">
				<div class="col-md-12">
					<table class="table table-hovered table-striped">
						<thead id="pri"><th colspan="6"><center>Detail BUS - <?php echo $tiket['kd_bus'];; ?></center></th></thead>
						<tbody id="bg-def">
							<tr>
								<td>Nama Bus</td>
								<td>: <?php echo $tiket['nama']; ?></td>
								<td>Trayek</td>	
								<td>: <?php echo $tiket['nama_trayek']; ?></td>
							</tr>
							<tr>
								<td>Kelas</td>
								<td>: <?php echo $tiket['nama_kelas'] ?></td>
								<td>Sopir Pertama</td>
								<td>: <?php echo $pir1['nama']; ?></td>
							</tr>
							<tr>
								<td>No. Polisi</td>
								<td>: <?php echo $tiket['no_polisi']; ?></td>
								<td>Sopir Kedua</td>
								<td>: <?php echo$pir2['nama']?></td>
							</tr>
							<tr>
								<td>Tanggal Berangkat</td>
								<td>: <?php echo $tiket['tanggal_berangkat']; ?></td>
								<td>Jumlah Penumpang</td>
								<td>: <?php echo number_format($jalan['jumlah_penumpang'],0,'','.'); ?></td>
							</tr>
							<tr>
								<td>Jam Berangkat</td>
								<td>: <?php echo $tiket['jam_berangkat'] ?></td>
								<td>Total Pendapatan</td>
								<td>: Rp. <?php echo number_format($jalan['pendapatan'],0,'','.'); ?></td>
							</tr>
							
						</tbody>
					</table>
				</div>
				<div class="col-md-12">
					<div class="table table-responsive">	
						<table class="table table-bordered table-hover table-striped">
							<thead id="pri">
								<th width="5%"><center>No.</center></th>
								<th width="20%"><center>Kode Perjalanan</center></th>
								<th width="20%"><center>Waktu Pembaruan</center></th>
								<th ><center>Lokasi</center></th>
							</thead>
							<tbody>
								<?php  
									$sql = $aksi->tampil("tbl_perjalanan_detail"," WHERE kd_perjalanan = '$kd_perjalanan' ","ORDER BY waktu_lokasi DESC");
									@$no = 0;
									if ($sql =="") {
										echo "<tr><td align='center' colspan='4'>Data Tidak Ada</td></tr>";
									}else{
										foreach ($sql as $data) {
											$no++;
								?>
									<tr>
										<td><center><?php echo $no; ?>.</center></td>
										<td><center><?php echo $data[1]; ?></center></td>
										<td><center><?php echo $data[3]; ?></center></td>
										<td><?php echo $data[2]?></td>
									</tr>
								<?php } } 
								?>
								<tr>
									<td colspan="7">
										<a href="?menu=perjalanan" class="btn btn-block btn-primary"><h4><b>K&nbsp;E&nbsp;M&nbsp;B&nbsp;A&nbsp;L&nbsp;I</b></h4></a>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
 </body>
 </html>