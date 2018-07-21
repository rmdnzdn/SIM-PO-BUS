 <?php 
	date_default_timezone_set("Asia/Jakarta");
	@$alamat = "?menu=keberadaan";
	@$table = "tbl_perjalanan_detail";
	@$where = "id = '$_GET[id]'";

	@$us = $_SESSION['user'];
	@$sopir= mysql_fetch_array(mysql_query("SELECT * FROM tbl_sopir WHERE username = '$us' "));

	@$jalan = mysql_fetch_array(mysql_query("SELECT * FROM tbl_perjalanan WHERE status <> 'selesai' AND (sopir_1 LIKE '%$sopir[0]%' OR sopir_2 LIKE  '%$sopir[0]%')"));
	
	@$bus = mysql_fetch_array(mysql_query("SELECT * FROM tbl_bus WHERE kd_bus = '$jalan[2]'"));

	@$tiket = mysql_fetch_array(mysql_query("SELECT * FROM tbl_tiket WHERE kd_tiket = '$jalan[1]'"));

	@$trayek = mysql_fetch_array(mysql_query("SELECT * FROM tbl_trayek WHERE kd_trayek = '$tiket[2]'"));

	@$field = array(
			'kd_perjalanan' => $jalan[0],
			'lokasi' => $_POST['tlokasi']
		);

	if (isset($_POST['simpan'])) {
		if ($jalan[0] == "" || $_POST['tlokasi']=="") {
			$aksi->pesan("Lengkapi Data Terleboh Dahulu !!!");
		}else{
			$aksi->simpan($table,$field,$alamat);
		}
	}
	if (isset($_GET['edit'])) {
		@$edit = $aksi->edit($table, $where);
	}
	if (isset($_POST['ubah'])) {
		$aksi->ubah($table,$field,$where,$alamat);
	}
	if (isset($_GET['hapus'])) {
		$aksi->hapus($table,$where,$alamat);
	}

	if (isset($_POST['selesai'])) {
		@$a = mysql_query("SELECT * FROM tbl_perjalanan_detail WHERE kd_perjalanan = '$jalan[0]' AND lokasi = 'Sampai Tujuan'");
		@$b = mysql_num_rows($a);
		if ($b > 0) {
			mysql_query("UPDATE tbl_perjalanan SET status = 'selesai' WHERE kd_perjalanan = '$jalan[0]'");
			mysql_query("UPDATE tbl_tiket SET status = 'selesai' WHERE kd_tiket = '$tiket[0]'");
			mysql_query("UPDATE tbl_bus SET status_bus = 'tersedia' WHERE kd_bus = '$bus[0]'");
			mysql_query("UPDATE tbl_sopir SET status_sopir = 'tersedia' WHERE kd_sopir = '$jalan[3]' OR kd_sopir = '$jalan[4]'");
			$aksi->pesan("PERJALANAN BUS $bus[1] Telah selesai");
			$aksi->alamat($alamat);
		}
	}


?>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, intial-scale=1">
	<title>Keberadaan BUS</title>
</head>
<body>
<br><br><br><br>
<div class="container-fluid" id="mrg">
	<div class="row">
	<?php if ($jalan[0] == "") { ?>
		<div class="alert alert-dismissible alert-danger">
		  <button type="button" class="close" data-dismiss="alert">&times;</button>
		  <strong>Login Berhasil !<b><?php echo $_SESSION['nama']; ?></b></strong>
		  <br>
			Saat Ini Anda Tidak Memiliki Jadwal Perjalanan<br>Jika Anda Ingin keluar,klik <a href="?logout" onclick="return confirm('Apakah Anda yakin akan keluar dari akun ?');"><span id="blue"><b>Disini</b></span></a>
		</div>
		
	<?php }else{ ?>
		<div class="panel panel-primary">
			<div class="panel-heading" style="height:40px;"><center>Form Pengupdate Lokasi Perjalanan BUS : &nbsp;<?php echo $bus[1]." - ".$bus[2] ; ?> &nbsp; Trayek : <?php 	echo $trayek[4]; ?></center></div>
			<div class="panel-body">
				<form method="post">
					<div class="col-md-12">
						<div class="form-group">
							<!-- <label>TOTAL HARGA :</label> -->
							<div class="input-group">
								<span class="input-group-addon"><div class="glyphicon  glyphicon-map-marker"></div></span>

								<input type="text" name="tlokasi"  value="<?php echo @$edit['lokasi']; ?>" class="form-control" list="list" placeholder="Masukan Lokasi BUS Saat Ini" tabindex="0">	
								<datalist id="list">
									<option>Sampai Tujuan</option>
								</datalist>

								<div class="input-group-btn">
									<?php  if($_GET['id']==""){	 ?>
										<button type="submit" class="btn btn-primary" name="simpan" tabindex="0">SIMPAN</button>
									<?php 	}else{ ?>
										<button type="submit" class="btn btn-success" name="ubah" tabindex="0">UBAH</button>
									<?php 	} ?>
								</div>

								<div class="input-group-btn">
									<button class="btn btn-danger" type="submit" name="selesai" tabindex="0">SELESAI PERJALANAN</button>
								</div>
							</div>
						</div>
					</div>
					<div class="table table-responsive">	
						<table class="table table-bordered table-hover table-striped">
							<thead id="pri">
								<th width="7%"><center>No.</center></th>
								<th><center>Kode Perjalanan</center></th>
								<th ><center>Waktu Pembaruan</center></th>
								<th width="50%" ><center>Lokasi</center></th>
								<th  width="10%" colspan="2"><center>Aksi</center></th>
							</thead>
							<tbody>
								<?php  
									$sql = $aksi->tampil("tbl_perjalanan_detail"," WHERE kd_perjalanan = '$jalan[0]'","ORDER BY waktu_lokasi DESC");
									@$no = 0;
									if ($sql =="") {
										echo "<tr><td align='center' colspan='6'>Data Tidak Ada</td></tr>";
									}else{
										foreach ($sql as $data) {
											$no++;
								?>
									<tr>
										<td><center><?php echo $no; ?>.</center></td>
										<td><center><?php echo $data[1]; ?></center></td>
										<td><center><?php echo $data[3]; ?></center></td>
										<td><?php echo $data[2]?></td>
										<td><center><a href="?menu=keberadaan&edit&id=<?php echo $data[0]; ?>" class="btn btn-success btn-xs">Edit</a></center></td>
										<td><center><a href="?menu=keberadaan&hapus&id=<?php echo $data[0]; ?>" class="btn btn-danger btn-xs" onClick="return confirm('Yakin akan menghapus lokasi tersebut ?');">Hapus</a></center></td>
										
									</tr>
								<?php } } 
								?>
							</tbody>
						</table>
					</div>
					
				</form>
			</div>
			<div class="panel-footer">&nbsp;</div>
		</div>
		<?php 	} ?>
	</div>
</div>
</body>
</html>