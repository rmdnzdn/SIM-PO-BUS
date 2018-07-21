 <?php 
	date_default_timezone_set("Asia/Jakarta");
	@$alamat = "?menu=perjalanan";

	if (isset($_POST['txt_bulan']) || isset($_POST['txt_tahun'])) {
		@$bulanini=$_POST['txt_bulan'];
		@$tahunini=$_POST['txt_tahun'];
	}else{
		@$bulanini=date("m");
		@$tahunini=date("Y");
	}
	@$cari="WHERE MONTH(tanggal_perjalanan)='$bulanini' AND YEAR(tanggal_perjalanan)='$tahunini'";
	switch ($bulanini) {
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
?>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, intial-scale=1">
	<title>Laporan Perjalanan Bulan - <?php echo @$bulaninitext." ".@$tahunini; ?></title>
</head>
<body>
<br><br><br><br>
<div class="container-fluid" id="mrg">
	<div class="row">
		<div class="panel panel-primary">
			<div class="panel-heading" style="height:40px;">
				<h3 class="panel-title pull-left">Laporan Perjalanan BUS - Bulan <?php echo @$bulaninitext." ".@$tahunini ; ?></h3>
				<!-- <div class="pull-right">
					<a href="../laporan/cetak_keuangan.php?bln=<?php echo $bulanini; ?>&thn=<?php echo $tahunini; ?>" target="_blank" style="color:white;"><div class="glyphicon glyphicon-print"></div>&nbsp;Cetak Laporan</a>
					&nbsp;&nbsp;
					<a href="../laporan/cetak_pdf.php?menu=keuangan&bln=<?php echo $bulanini; ?>&thn=<?php echo $tahunini; ?>" target="_blank" style="color:white;"><div class="glyphicon glyphicon-floppy-save"></div>&nbsp;Simpan PDF</a>
					&nbsp;&nbsp;
					<a href="#" target="_blank" style="color:white;"><div class="glyphicon glyphicon-floppy-save"></div>&nbsp;Simpan Excel</a>
					&nbsp;&nbsp;
				</div> -->
			</div>
			<div class="panel-body">
				<form method="post">
					<div class="col-md-12" style="margin-left:-30px;margin-bottom:10px;">
						<div class="col-md-8">
							<div class="input-group">
								<div class="input-group-addon" id="pri">Bulan</div>
								<select name="txt_bulan" class="form-control" onchange="submit()">
									<?php 
									for ($a=1; $a < 13; $a++) {
									?>
									<option value="<?php echo $a; ?>" <?php if($a==$bulanini){echo "selected";} ?>><?php echo $a; ?></option>
									<?php } ?>
								</select>
								<div class="input-group-addon" id="pri">Tahun</div>
								<select name="txt_tahun" class="form-control" onchange="submit()">
									<?php 
									for ($a=2016; $a < 2031; $a++) {
									?>
									<option value="<?php echo $a; ?>" <?php if($a==$tahunini){echo "selected";} ?>><?php echo $a; ?></option>
									<?php } ?>
								</select>
								<div class="input-group-btn">
									<a href="" class="btn btn-success">
										<div class="glyphicon glyphicon-refresh"></div> Refresh
									</a>
								</div>
							</div>
						</div>
						<div class="col-md-4">						
							<a href="../direktur/cetak_perjalanan.php?bln=<?php echo $bulanini; ?>&thn=<?php echo $tahunini; ?>" target="_blank" class="btn btn-success"><div class="glyphicon glyphicon-print"></div>&nbsp;Cetak</a>
						</div>
					</div>
					
					<table class="table table-bordered table-hover table-striped">
						<thead id="pri">
							<th width="3%"><center>No.</center></th>
							<th width="10%"><center>Tanggal Pemberangkatan</center></th>
							<th width="10%"><center>Kode BUS</center></th>
							<th><center>Sopir Pertama</center></th>
							<th><center>Sopir Kedua</center></th>
							<th  width="10%"><center>Jumlah Penumpang</center></th>
							<th  width="15%"><center>Jumlah Pendapatan</center></th>
							<th width="15%"><center>Lokasi Terakhir</center></th>
							<th width="7%"><center>Detail</center></th>
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
									<td><center><?php echo $no; ?>.</center></td>
									<td><center><?php echo $data[7]; ?></center></td>
									<td><?php echo $data[2]; ?></td>
									<td><?php echo $pir1['nama'] ?></td>
									<td><?php echo $pir2['nama'] ?></td>
									<td><?php echo number_format(@$data[5],0,'','.'); ?></td>
									<td><?php echo number_format(@$data[6],0,'','.'); ?></td>
									<td><center><?php echo $lok[2] ?></center></td>
									<td><center><a href="?menu=detailperjalanan&kd_perjalanan=<?php echo $data[0]; ?>" class="btn btn-primary btn-xs">Detail</a></center></td>
								</tr>
							<?php } } 
							?>
						</tbody>
					</table>
				</form>
			</div>
			<div class="panel-footer">&nbsp;</div>
		</div>
	</div>
</div>
</body>
</html>