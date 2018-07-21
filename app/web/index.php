<?php

	include "config/koneksi.php";
	include "library/fungsi.php";
	date_default_timezone_set("Asia/Jakarta");
	
	
	@$aksi = new oop();
	session_start();

	@$table = "tbl_user";
	@$user = mysql_real_escape_string($_POST['tuser']);
	@$pass = mysql_real_escape_string($_POST['tpass']);
	@$sql = mysql_fetch_array(mysql_query("SELECT * FROM tbl_user WHERE username = '$user'"));
	@$jabatan = $sql[7];
	if ($jabatan == "agen" || $jabatan=="direktur") {
		if (empty($_SESSION['user']) || empty($_SESSION['jabatan'])) {
		}else{
		switch ($_SESSION['jabatan']) {
				case 'agen':
					$aksi->alamat("agen/");
					break;
				case 'direktur':
					$aksi->alamat("direktur/");
					break;
				default:
					break;
			}
		}

		if (isset($_POST['blogin'])) {
			@$a=$aksi->login($table, $user, $pass, $jabatan, $alamat);
			if(isset($a['0']) && isset($a['1']) && isset($a['2'])){
				switch ($a['2']) {
					case 'agen':
						$aksi->alamat("agen/");
						break;
					case 'direktur':
						$aksi->alamat("direktur/");
						break;
					default:
						
						break;
				}
				
			}
		}

	}else{
		if (empty($_SESSION['user']) || empty($_SESSION['jabatan'])) {
		}else{
		switch ($_SESSION['jabatan']) {
				case 'sopir':
					$aksi->alamat("sopir/");
					break;
				default:
					break;
			}
		}

		if (isset($_POST['blogin'])) {
			@$a=$aksi->login("tbl_sopir", $user, $pass, "sopir", $alamat);
			if(isset($a['0']) && isset($a['1']) && isset($a['2'])){
				switch ($a['2']) {
					case 'sopir':
						$aksi->alamat("sopir/");
						break;
					default:
						
						break;
				}
				
			}
		}
	}

// if (empty($_SESSION['user']) || empty($_SESSION['jabatan'])) {
// }else{
// switch ($_SESSION['jabatan']) {
// 		case 'agen':
// 			$aksi->alamat("agen/");
// 			break;
// 		case 'direktur':
// 			$aksi->alamat("direktur/");
// 			break;
// 		default:
// 			break;
// 	}
// }

// if (isset($_POST['blogin'])) {
// 	@$a=$aksi->login($table, $user, $pass, $jabatan, $alamat);
// 	if(isset($a['0']) && isset($a['1']) && isset($a['2'])){
// 		switch ($a['2']) {
// 			case 'agen':
// 				$aksi->alamat("agen/");
// 				break;
// 			case 'direktur':
// 				$aksi->alamat("direktur/");
// 				break;
// 			default:
				
// 				break;
// 		}
		
// 	}
// }

?>
<!DOCTYPE html>
<html>
<head>
		<meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>PO. SETIA BHAKTI</title>
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="fonts/glyphicons-halflings-regular.svg">
        <style type="text/css">
        	body{
	    		background-color: rgba(44, 62, 80, 0.1);
	    	}
        </style>
       
</head>
<body>
<!--  background-color:#323b44;  biru navy-->
<!--  background-color:#40b581;  hijau tua pastel-->
<!--  background-color:#deecdf;  hijau muda pastel-->
	<div class="container">
		<div class="col-md-3"></div>
		<div class="col-md-6">
			<div class="panel panel-primary" style="margin-top:120px;">
				<div class="panel-heading" style="padding:20px;">
					<div class="col-md-1"></div>
					<div style="font-size:200%;"><strong>PO. SETIA BHAKTI</strong></S></div>
					<div class="col-md-1"></div>
					<div style="font-size:100%;">A P L I K A S I &nbsp; M O N I T O R I N G  &nbsp; B U S &nbsp;</div>
				</div>
				<div class="panel-body">
					<form method="post">
						<div class="form-group" >
							<div class="input-group" style="margin:10px 2px;">
								<span class="input-group-addon"><div class="glyphicon glyphicon-user"></div></span>
								<input type="text" name="tuser" class="form-control" placeholder="Username" maxlength="50" required value="<?php echo @$_POST['tuser']; ?>" tabindex="0" autofocus autocomplete="on">
							</div>
						</div>
						<div class="form-group">
							<div class="input-group col-md-12" style="margin:10px 2px;">
								<div class="input-group-addon"><div class="glyphicon glyphicon-lock"></div></div>
								<input type="password" name="tpass" id="password" class="form-control" placeholder="Password"  tabindex="0" maxlength="100" required value="<?php echo @$_POST['tpass']; ?>" autocomplete="off">
							</div>
						</div>
						<div class="col-md-3"></div>
						<button type="submit" name="blogin" class="btn btn-primary btn-block col-md-6">LOGIN</button>
					</form>
				</div>
				<div class="panel-footer" align="center">
						&copy;2017 - Muhammad Ramdan
				</div>
			</div>
		</div>
		
	</div>

	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/show-password.js"></script>
	<script>
		$(function () {
			$('#password').password().on('show.bs.password', function (e) {
				$('#methods').prop('checked', true);
			}).on('hide.bs.password', function (e) {
				$('#methods').prop('checked', false);
			});
			$('#methods').click(function () {
				$('#password').password('toggle');
			});
		});
	</script>
</body>
</html>