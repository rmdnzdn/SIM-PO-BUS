<?php  
	if (!isset($_GET['menu'])) {
	header("location:index.php?menu=daftarpenjualan");
	}
	@$sql1 = mysql_query("SELECT * FROM tbl_user WHERE username ='$_SESSION[user]'");
	@$id_agen = mysql_fetch_array($sql1);

	@$table = "tbl_pembelian";
	@$alamat = "?menu=daftarpenjualan";
	@$where = "kd = '$_GET[id]'";

	// if (isset($_GET['hapus'])) {
	// 	$aksi->hapus($table,$where,$alamat);
	// 	$aksi->hapus("tbl_transaksi_detail",$where,$alamat);
	// }

	if (isset($_POST['tbulan']) || isset($_POST['ttahun'])) {
		@$bulanini=$_POST['tbulan'];
		@$tahunini=$_POST['ttahun'];
	}else{
		@$bulanini=date("m");
		@$tahunini=date("Y");
	}
	@$cari="WHERE MONTH(tanggal_pembelian)='$bulanini' AND YEAR(tanggal_pembelian)='$tahunini' AND id_agen = '$id_agen[0]'";
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
	<meta http-equiv="X-UA-Compatible" content="IE-edge">
	<meta name="viewport" content="window=device-width, initial-scale=1">
	<title>Form Penjualan Barang</title>
</head>
<body>
<br><br><br><br>
<div class="container-fluid" id="mrg">
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-primary">
				<div class="panel-heading">Daftar Riwayat Penjualan Tiket- Bulan <?php echo @$bulaninitext." ".@$tahunini; ?></div>
				<div class="panel-body">
					<form method="post">
						<table class="table-bordered">  
							<div class="col-md-5" style="margin-left:-10px;">
								<a href="?menu=penjualantiket" class="btn btn-primary btn-block btn-lg">PENJUALAN TIKET</a>
								<!-- <br>
								<label>Tampilkan Data Sebanyak :</label>
								<select>
									<option>10</option>
									<option>25</option>
									<option>50</option>
									<option>100</option>
								<label>Baris</label>
								</select> -->
							</div>
							<div class="col-md-1"></div>
							<div class="col-md-6">
								<div class="input-group">
									<div class="input-group-addon" id="pri">Bulan</div>
									<select name="tbulan" class="form-control" onchange="submit()">
										<?php   
											for ($a=1; $a < 13; $a++) { 
										?>	
											<option value="<?php echo $a; ?>"<?php if($a==$bulanini){echo "selected";}?>><?php echo $a; ?></option>
										<?php 	}?>
									</select>
									<div class="input-group-addon" id="pri">Tahun</div>
									<select name="ttahun" class="form-control" onchange="submit()">
										<?php   
											for ($a=2016; $a < 2031; $a++) { 
										?>	
											<option value="<?php echo $a; ?>"<?php if($a==$tahunini){echo "selected";} ?>><?php echo $a; ?></option>
										<?php 	}?>
									</select>
									<div class="input-group-btn">
										<a href="" class="btn btn-success">
											<div class="glyphicon glyphicon-refresh">Refresh</div>
										</a>
									</div>
								</div>			
							</div>
						</table>
						<br><br>
						<br><br>
						<div class="table table-responsive">
							<table class="table table-hover table-bordered table-striped">
								<thead id="pri">
									<th width="4%"><center>No.</center></th>
									<th>Kode Pembelian</th>
									<th>Tanggal Pembelian</th>
									<th>Nama </th>
									<th>No. Telepon</th>
									<th>Kode Tiket</th>
									<th>Harga Tiket</th>
									<th>Jumlah</th>
									<th>Total Harga</th>
									<th><center>Detail</center></th>
								</thead>
								<tbody>	
									<tr>
										<?php 
											@$t = $aksi->tampil("tbl_pembelian",$cari," ORDER by waktu_pembelian DESC");
											@$no =0;
											if ($t=="") {
												echo "<tr><td colspan='10' align='center'><b>Data Tidak Ada !!!</b></td></tr>";
											}else{
												foreach ($t as $data) {
													$no++;
													
											?>
											<td><center><b><?php echo $no; ?>.</b></center></td>
											<td><?php echo $data[0]; ?></td>
											<td><?php echo $data[2]; ?></td>
											<td><?php echo $data[4]; ?></td>
											<td><?php echo $data[5]; ?></td>
											<td><?php echo $data[1]; ?></td>
											<td align="right"><?php echo number_format($data[8],0,'','.'); ?></td>
											<td align="right"><?php echo number_format($data[9],0,'','.'); ?></td>
											<td align="right"><?php echo number_format($data[10],0,'','.'); ?></td>
		 								<!-- 	<td><a href="?menu=daftarpenjualan&hapus&id=<?php echo $data[0];?>" onClick="return confirm('Anda Yakin Akan Menghapus Transaksi dengan kode <?php echo $data[0] ?> ini ?')"><center><span class="glyphicon glyphicon-trash" id="red"></span></center></a></td> -->
											<!-- <td><a href="?menu=penjualanedit&no_transaksi=<?php echo $data[0]; ?>"><center><span class="glyphicon glyphicon-edit"></span></center></a></td> -->
											<td><center><a href="?menu=detail&kd_pembelian=<?php echo $data[0]; ?>"  class="btn btn-primary btn-xs">Detail</a></center></td>
									</tr>
											<?php 	}} ?>	
								</tbody>
							</table>
						</div>
					</form>
				</div>
				<div class="panel-footer">&nbsp;</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>