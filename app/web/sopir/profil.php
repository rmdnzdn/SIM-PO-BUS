<?php
	date_default_timezone_set("Asia/Jakarta");
 
	if (!isset($_GET['menu'])) {
	    header("location:index.php?menu=profil");
	}

	@$table ="tbl_sopir";
	@$alamat = "?menu=profil";
	@$username = $_SESSION['user'];
	@$where = "username = '$username'";
	@$field = array(
			'password' 	=>$_POST['tpass'],
			'nama'    	=>$_POST['tnama'],
			'alamat' 	=>$_POST['talamat'],
			'no_hp' 	=>$_POST['thp'],
			'no_sim' 	=>$_POST['tsim']
		);
	@$sql = mysql_query("SELECT * FROM tbl_sopir  WHERE $where");
	@$data = mysql_fetch_array($sql);

	if (isset($_POST['bsimpan'])) {
		$aksi->ubah($table,$field,$where,$alamat);
		$_SESSION['user'] = $_POST['tuser'];
		$_SESSION['nama'] = $_POST['tnama'];
	}
?>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Form Data Diri</title>
</head>
<body>
<br><br><br><br>
<div class="container">
	<div class="row">
		<div class="col-md-3"></div>
		<div class="col-md-6">
			<div class="panel panel-primary">
				<div class="panel-heading" align="center">
					<h3>Pengaturan Data Diri</h3>
				</div>
				<div class="panel-body">
				<form method="post">
					<div class="form-group">
						<div class="input-group">
							<span class="input-group-addon"><div class="glyphicon glyphicon-user"></div></span>
							<input type="text" name="tkode" value="<?php echo $data['kd_sopir']; ?>" class="form-control" placeholder="Kode Sopir" readonly required tabindex="1" maxlength="12">
						</div>
					</div>
					<div class="form-group">
						<div class="input-group">
							<span class="input-group-addon"><div class="glyphicon glyphicon-user"></div></span>
							<input type="text" name="tuser" value="<?php echo $data['username']; ?>" class="form-control" placeholder="Us" readonly required tabindex="1" maxlength="20">
						</div>
					</div>
					<div class="form-group">
						<div class="input-group col-md-12">
							<span class="input-group-addon"><div class="glyphicon glyphicon-lock"></div></span>
							<input type="password" name="tpass" id="password" value="<?php echo $data['password']; ?>" class="form-control" placeholder="Password Anda" required tabindex="0" autofocus maxlength="50">
						</div>
					</div>
					<div class="form-group">
						<div class="input-group">
							<span class="input-group-addon"><div class="glyphicon glyphicon-text-size"></div></span>
							<input type="text" name="tnama" value="<?php echo $data['nama']; ?>" class="form-control" placeholder="Nama Anda" required tabindex="0" maxlength="50">
						</div>
					</div>
					<div class="form-group">
						<div class="input-group">
							<span class="input-group-addon"><div class="glyphicon glyphicon-credit-card"></div></span>
							<input type="text" name="tsim" value="<?php echo $data['no_sim']; ?>" class="form-control" placeholder="No.SIM Anda" required tabindex="0" maxlength="50" onkeypress="return event.charCode >= 48 && event.charCode <= 57">
						</div>
					</div>
					<div class="form-group">
						<div class="input-group">
							<span class="input-group-addon"><div class="glyphicon glyphicon-education"></div></span>
							<input type="text" name="tjabatan" value="<?php echo $data['jabatan']; ?>" class="form-control" placeholder="Jabatan Anda" required tabindex="1" maxlength="13" readonly>
						</div>
					</div>
					<div class="form-group">
						<div class="input-group">
							<span class="input-group-addon"><div class="glyphicon glyphicon-home"></div></span>
							<textarea class="form-control" name="talamat" required tabindex="0"><?php echo $data['alamat']; ?></textarea>
						</div>
					</div>
					<div class="form-group">
						<div class="input-group">
							<span class="input-group-addon"><div class="glyphicon glyphicon-phone-alt"></div></span>
							<input type="text" name="thp" value="<?php echo $data['no_hp']; ?>" class="form-control" placeholder="No.Handphone Anda" required tabindex="0" maxlength="13" onkeypress="return event.charCode >= 48 && event.charCode <= 57">
						</div>
					</div>
					<div class="form-group">
						<div class="input-group col-md-12">
							<button type="submit" name="bsimpan" class="btn btn-primary btn-lg btn-block">SIMPAN PERUBAHAN</button>
						</div>
					</div>
					</form>
				</div>
				<div class="panel-footer">&nbsp;</div>
			</div>
		</div>
		<div class="col-md-3"></div>
	</div>
</div>
</body>
</html>