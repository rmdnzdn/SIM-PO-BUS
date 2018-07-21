<?php 
	if (!isset($_GET['menu'])) {
	    header("location:index.php?menu=penjualantiket");
	} 
	//manggil di table tiket
	@$kd_tiket = $_GET['kd_tiket'];
	@$sql = mysql_query("SELECT * FROM qw_tiket WHERE kd_tiket = '$kd_tiket'");
	@$r = mysql_fetch_array($sql);

	//maggil di table pembelian
	@$today = date("dmY");
	@$j = mysql_query("SELECT * FROM tbl_pembelian WHERE kd_pembelian LIKE '%$today%' ORDER BY kd_pembelian DESC");
	@$k = mysql_fetch_array($j);

	//manggil di table user
	@$sql1 = mysql_query("SELECT * FROM tbl_user WHERE username ='$_SESSION[user]'");
	@$id_agen = mysql_fetch_array($sql1);

	if ($k == "") {
		@$urut = "001";
	}else{
		@$urut = substr($k['kd_pembelian'], 10,3)+1;
		if (@$urut < 10) {
			@$urut="00$urut";
		}elseif ( $urut < 100) {
			@$urut="0$urut";
		}
	}		
	@$kd_pembelian = "PT$today$urut";
	@$tanggal = date("Y-m-d");
	@$alamat = "?menu=penjualantiket";

	@$field = array(
		'kd_pembelian'=> @$kd_pembelian,
		'kd_tiket'=> @$kd_tiket,
		'tanggal_pembelian'=> @$tanggal,
		'nama'=> @$_POST['tnama'],
		'no_hp'=> @$_POST['tnohp'],
		'email'=> @$_POST['temail'],
		'alamat'=> @$_POST['talamat'],
		'harga'=> @$_POST['tharga'],
		'jumlah_beli'=> @$_POST['tjumlah'],
		'total_harga'=> @$_POST['ttotal'],
		'id_agen'=> @$id_agen[0]
		);

	@$field_detail = array(
		'kd_pembelian'=>@$kd_pembelian,
		'nama_bus'=>@$r['nama'],
		'no_polisi'=>@$r['no_polisi'],
		'nama_kelas'=>@$r['nama_kelas'],
		'asal'=>@$r['asal'],
		'tujuan'=>@$r['tujuan'],
		'nama_trayek'=>@$r['nama_trayek'],
		'tanggal_berangkat'=>@$r['tanggal_berangkat'],
		'jam_berangkat'=>@$r['jam_berangkat'],
		'harga_tiket'=>@$r['harga'],
		'diskon'=>@$r['diskon']
		);

	if (isset($_POST['bselesai'])) {
		if ($_POST['tjumlah'] > $r['jumlah_kursi']) {
			$aksi->pesan("Jumlah Kusi tersisa ".$r['jumlah_kursi']);		
		}elseif ($_POST['tjumlah']==0 || $_POST['tjumlah']=="") {
			$aksi->pesan("Tidak Bisa Nol atau Kosong");
		}else{
			mysql_query("UPDATE tbl_tiket SET status = 'habis' WHERE jumlah_kursi <= 0");
			$aksi->simpanlsg("tbl_pembelian_detail",$field_detail,"#");
			$aksi->simpanlsg("tbl_pembelian",$field,"#");
			echo "<script>window.open('../agen/tiket_print.php?kd_pembelian=$kd_pembelian');document.location.href='$alamat'</script>";

		}
	}


?>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Form Penjualan Tiket Perjalanan</title>
<body>
<br><br>
<br><br>
	<div class="container-fluid" id="mrg">
		<div class="row">
			<div class="panel panel-primary" >
				<div class="panel-heading"><center><b>Penjualan Tiket - No.Penjualan : <?php echo $kd_pembelian." -  Tanggal : ".$tanggal ?></b></center></div>
				<div class="panel-body">
					<form method="post">
						<div class="col-md-12">	
							<div class="col-md-5">	
								<div class="form-group">
									<label>PILIH KODE TIKET :</label>
									<div class="input-group">
										<span class="input-group-addon"><div class="glyphicon glyphicon-tags"></div></span>
										<input type="text" name="tkode" value="<?php echo $r[0]; ?>" required placeholder="Kode Tiket" class="form-control" readonly tabindex="1" autocomplete="off">
										<span class="input-group-btn">
											<a href="?menu=daftartiket" class="btn btn-primary">TAMPIL</a>
											<!-- <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#tiket">TAMPIL</button> -->
										</span>
									</div>
								</div>
								<div class="form-group">
									<!-- <label>HARGA TIKET :</label> -->
									<div class="input-group">
										<span class="input-group-addon"><div class="glyphicon glyphicon-text-size"></div></span>
										<input type="text" name="tharga" id="tharga" value="<?php echo $r['harga'] ?>" required placeholder="Harga Tiket" readonly class="form-control">
									</div>
								</div>
								<div class="form-group">
									<!-- <label>JUMLAH BELI :</label> -->
									<div class="input-group">
										<span class="input-group-addon"><div class="glyphicon glyphicon-text-size"></div></span>
										<input type="text" name="tjumlah" id="tjumlah" value="<?php echo @$edit['jumlah_beli']; ?>" class="form-control" required placeholder="Jumlah Beli" maxlength="5" autofocus onkeypress='return event.charCode >= 48 && event.charCode <= 57' autocomplete="off" tabindex="0" autofocus>	
									</div>
								</div>

								<div class="form-group">
									<!-- <label>TOTAL HARGA :</label> -->
									<div class="input-group">
										<span class="input-group-addon"><div class="glyphicon glyphicon-text-size"></div></span>
										<input type="text" name="ttotal" id="ttotal" value="<?php echo @$edit['total']; ?>" class="form-control" required placeholder="Total Harga" readonly onkeypress='return event.charCode >= 48 && event.charCode <= 57' autpcomplete="off" tabindex="1">	
									</div>
								</div>
							</div>
							<div class="col-md-7">
								<br>
								<table class="table table-hovered table-striped">
									<thead id="pri"><th colspan="6"><center>Detail Tiket - <?php echo $r['kd_tiket'];; ?></center></th></thead>
									<tbody id="bg-def">
										<tr>
											<td>Nama Bus</td>
											<td>: <?php echo $r['nama']; ?></td>
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
											<td>: Rp. <?php echo number_format($r['harga'],0,'','.'); ?></td>
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
						</div>	
						<div class="col-md-12">
							<div class="panel panel-primary">
								<div class="panel-heading"><center><b>Masukan Data Diri</b></center></div>
								<div class="panel-body">
									<div class="col-md-6">	

									</div>
									<div class="col-md-6">	</div>
									<div class="form-group">
										<label>NAMA :</label>
										<div class="input-group">
											<span class="input-group-addon"><div class="glyphicon glyphicon-user"></div></span>
											<input type="text" name="tnama" required placeholder="Masukan Nama" tabindex="0" class="form-control">
										</div>
									</div>
									<div class="form-group">
									<label>NO TELEPON :</label>
										<div class="input-group" style="margin:0 2px;">
											<span class="input-group-addon"><div class="glyphicon glyphicon-phone-alt"></div></span>
											<input type="text" name="tnohp" value="<?php echo @$edit['no_telp_user']; ?>" class="form-control" placeholder="No Telepon" required autocomplete="off"  maxlength="13" onkeypress='return event.charCode >= 48 && event.charCode <= 57'>
										</div>
									</div>
									<div class="form-group">
										<label>EMAIL :</label>
										<div class="input-group">
											<span class="input-group-addon"><div class="glyphicon glyphicon-envelope"></div></span>
											<input type="email" name="temail" required placeholder="Masukan Email" tabindex="0" class="form-control">
										</div>
									</div>
									<div class="form-group">
									<label>ALAMAT :</label>
										<div class="input-group" style="margin:0 2px;">
											<span class="input-group-addon"><div class="glyphicon glyphicon-home"></div></span>
											<textarea name="talamat" placeholder="Alamat" class="form-control" required autocomplete="off"><?php echo @$edit['alamat_user']?></textarea>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							 <button type="submit" name="bselesai" class="btn btn-primary btn-lg btn-block">SELESAI</button>						
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>