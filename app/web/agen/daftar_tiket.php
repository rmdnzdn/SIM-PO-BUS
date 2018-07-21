<?php  
	@$table = "qw_tiket";
	@$tgl = date("Y-m-d");

	if (isset($_POST['trayek'])) {
		@$tra = $_POST['trayek'];
		@$cari = "WHERE nama_trayek LIKE '%$tra%' AND status = 'tersedia' AND jumlah_kursi > 0 AND  tanggal_berangkat >= '$tgl'";
	}elseif (isset($_POST['bcari'])) {
		@$text = $_POST['tcari'];
		@$cari = "WHERE kd_tiket LIKE '%$text%'  OR nama LIKE '%$text%' OR  kd_bus LIKE '%$text%' OR no_polisi LIKE '%$text%' OR kd_trayek LIKE '%$text%' OR asal LIKE '%$text%' OR tujuan LIKE '%$text%' OR harga_trayek LIKE '%$text%' OR kd_kelas LIKE '%$text%' OR nama_kelas LIKE '%$text%' OR harga_kelas LIKE '%$text%' OR tanggal_berangkat LIKE '%$text%' OR jam_berangkat LIKE '%$text%' OR harga LIKE '%$text%' OR diskon LIKE '%$text%' OR jumlah_kursi LIKE '%$text%' AND status ='tersedia' AND jumlah_kursi > 0 AND  tanggal_berangkat >= '$tgl'";  
	}else{
		@$cari = "WHERE status = 'tersedia' AND jumlah_kursi > 0 AND  tanggal_berangkat >=  '$tgl'";
	}
?>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Form Daftar Tiket Perjalanan</title>
<body>
<br><br>
<br><br>
	<div class="container-fluid" id="mrg">
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-primary">
					<div class="panel-heading"><center><b>Daftar Tiket Perjalanan BUS</b></center></div>
					<div class="panel-body">
						<form method="post">
							<div class="table-responsive">
								<table class="table-hover">
									<div class="col-md-3">
										<select name="trayek" class="form-control" onchange="submit()">
											<option>Cari Berdasarkan Trayek</option>
											<?php  
												$b = $aksi->tampil("tbl_trayek","","");
												foreach ($b as $a) {
												
											?>
												<option value="<?php echo $a[4]; ?>" <?php if($a[4]==@$_POST['trayek']){echo "selected";} ?>><?php echo $a[4]; ?></option>		
											<?php	}
											?>
										</select>
									</div>
									<div class="col-md-9" style="margin-bottom:10px;">
										<div class="input-group">
											<input type="text" name="tcari" value="<?php echo @$text; ?>" class="form-control" placeholder="Cari Tiket" maxlength="50">
											<span class="input-group-btn">
												<button type="submit"  name="bcari" class="btn btn-primary"><div class="glyphicon glyphicon-search"></div></button>
												<a href="" class="btn btn-success">
													<div class="glyphicon glyphicon-refresh"></div> Refresh
												</a>
											</span>
										</div>
									</div>
								</table>
								<hr>
							</div>
							<div class="col-md-12">
								<?php  
									$a = $aksi->tampil("qw_tiket",$cari,"ORDER BY tanggal_berangkat ASC");
									if ($a=="") {
										echo "<center>Data Tidak Ada</center>";
									}else{
										foreach ($a as $data) {
									
								?>
										<div class="col-md-3">
											<div class="panel panel-primary" style="background-color:#deecdf;">
												<div class="panel-heading"><center><b><?php echo $data[8] ?></b></center></div>
												<div class="panel-body">
													<table class="table">
														<tr><td align="center"><b><?php echo $data[2]."<br>".$data[10]." - ".$data[3] ?></b></td></tr>
														<tr><td align="center"><b>Rp. <?php echo number_format($data[14],0,'','.'); ?></b></td></tr>
														<tr><td align="center"><b>SISA KURSI : <?php echo $data[16]; ?></b></td></tr>
														<tr><td align="center">Tujuan :<?php echo $data[6]."<br>Berangkat :".$data[12]."<br> Jam : ".$data[13] ?></td></tr>
													</table>
												</div>
												<a href="?menu=penjualantiket&kd_tiket=<?php echo $data[0]; ?>">
													<div class="panel-footer text-primary">
														<span class="pull-left"><b>Beli Tiket</b></span>
										                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
										                <div class="clearfix"></div>
													</div>
												</a>
											</div>
										</div>

								<?php } } ?>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>