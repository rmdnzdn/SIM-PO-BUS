<?php  
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
	<title>Detail Penjualan TIKET</title>
 </head>
 <body>
 <br><br><br><br>
	<div class="container">
		<div class="row">
			<div class="panel panel-primary">
				<div class="panel-heading">Detail Transaksi Penjualan - <?php echo @$kd_pembelian ?></div>
			<div class="panel-body">
				<div class="col-md-12">
					<table class="table table-hovered table-striped">
						<thead id="pri"><th colspan="6"><center>Detail Tiket - <?php echo $data['kd_tiket'];; ?></center></th></thead>
						<tbody id="bg-def">
							<tr>
								<td>Nama Bus</td>
								<td>: <?php echo $r['nama_bus']; ?></td>
								<td>Trayek</td>	
								<td>: <?php echo $r['nama_trayek']; ?></td>
							</tr>
							<tr>
								<td>Kelas</td>
								<td>: <?php echo $r['nama_kelas'] ?></td>
								<td>Kota Asal</td>
								<td>: <?php echo $r['asal']; ?></td>
							</tr>
							<tr>
								<td>No. Polisi</td>
								<td>: <?php echo $r['no_polisi']; ?></td>
								<td>Kota Tujuan</td>
								<td>: <?php echo$r['tujuan']?></td>
							</tr>
							<tr>
								<td>Tanggal Berangkat</td>
								<td>: <?php echo $r['tanggal_berangkat']; ?></td>
								<td>Harga Tiket</td>
								<td>: Rp. <?php echo number_format($r['harga_tiket'],0,'','.'); ?></td>
							</tr>
							<tr>
								<td>Jam Berangkat</td>
								<td>: <?php echo $r['jam_berangkat'] ?></td>
								<td>Diskon</td>
								<td>:<?php echo number_format($r['diskon'],0,'','.'); ?>%</td>
							</tr>
							
						</tbody>
					</table>
				</div>
				<div class="col-md-12">
					<table class="table table-bordered">
						<thead id="pri">
							<th>Tanggal Pembelian</th>
							<th>Nama</th>
							<th>No. Handphone</th>
							<th>Email</th>
							<th>Alamat</th>
							<th>Jumlah Beli</th>
							<th>Total Harga</th>
						</thead>
						<tbody>
							<tr>
								<td><?php echo $data['waktu_pembelian']; ?></td>
								<td><?php echo $data['nama']; ?></td>
								<td><?php echo $data['no_hp']; ?></td>
								<td><?php echo $data['email']; ?></td>
								<td><?php echo $data['alamat']; ?></td>
								<td><?php echo $data['jumlah_beli']; ?></td>
								<td>Rp.<?php echo number_format($data['total_harga'],0,'','.'); ?></td>
							</tr>
						</tbody>
						<form method="post">
							<tr>
								<td colspan="7">
									<a href="../agen/tiket_print.php?kd_pembelian=<?php echo @$data[kd_pembelian]; ?>" target="_blank" class="btn btn-block btn-primary"><h4><b>C&nbsp;E&nbsp;T&nbsp;A&nbsp;K&nbsp;&nbsp;&nbsp;S&nbsp;T&nbsp;R&nbsp;U&nbsp;K&nbsp;&nbsp;&nbsp;&nbsp;P&nbsp;E&nbsp;N&nbsp;J&nbsp;U&nbsp;A&nbsp;L&nbsp;A&nbsp;N</b></h4></a>
									<a href="?menu=daftarpenjualan" class="btn btn-block btn-primary"><h4><b>K&nbsp;E&nbsp;M&nbsp;B&nbsp;A&nbsp;L&nbsp;I</b></h4></a>
								</td>
							</tr>
							</form>
					</table>
				</div>
			</div>
		</div>
	</div>
 </body>
 </html>