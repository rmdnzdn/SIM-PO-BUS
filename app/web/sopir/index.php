<?php  
	include "../config/koneksi.php";
	include "../library/fungsi.php";
	date_default_timezone_set("Asia/Jakarta");

	@$aksi = new oop();
	session_start();
	
	@$home = "../index.php";
	@$tipe = "sopir";

	$aksi->ceklogin($home,$tipe);

	if (isset($_GET['logout'])) {
		$aksi->logout();
		$aksi->alamat($home);
	}	


?>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>SOPIR</title>
    <link rel="stylesheet" type="text/css" href="../css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="../css/style.css">
    <!-- <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/bootstrap-theme.min.css"> -->
    <link rel="stylesheet" href="../fonts/glyphicons-halflings-regular.svg">
    <link rel="stylesheet" href="../fonts/fontawesome/style/fontawesome.css">
    <style type="text/css">
    	body{
    		background-color: rgba(0, 0, 0, 0.05);
    	}
    	#hov:hover{
    		display: block;
    		background-color: rgba(0, 0, 0, 0.4);
    		border-radius: 5px 5px;
    		color: #1a242f;
    		transition: 0.3s ease-in-out;
    	}
    </style>
    <script type="text/javascript">
    	window.setTimeout("waktu()",1000);
    	function waktu(){
    		var tanggal = new Date();
    		setTimeout("waktu()",1000);
    		document.getElementById("output").innerHTML = 
    		tanggal.getHours()+":"+tanggal.getMinutes()+":"+tanggal.getSeconds();
    	}
    </script>
    <script language="Javascript">
    	var tanggallengkap = new String();
    	var namahari = ("Minggu Senin Selasa Rabu Kamis Jum'at Sabtu");
    	namahari = namahari.split(" ");
    	var namabulan = ("Januari Februari Maret April Mei Juni Juli Agustus September Oktober November Desember");
    	namabulan = namabulan.split(" ");
    	var tgl = new Date();
    	var hari = tgl.getDay();
    	var tanggal = tgl.getDate();
    	var bulan = tgl.getMonth();
    	var tahun= tgl.getFullYear();
    	tanggallengkap =namahari[hari] + ", " + tanggal + " " + namabulan[bulan] + " " + tahun;

    		var popupWindow = null;
    		function centerredPopup(url,winName,w,h,scroll){
    			LeftPosition = (screen.width) ?(screen.width-w)/2 : 0;
    			TopPosition = (screen.height) ?(screen.height-h)/2 : 0;
    			settings
    			='height='+h+',width='+w+',top='+TopPosition+',left='+LeftPosition+',scrollbars='+scroll+',resizable'
    			popupWindow = window.open(url,winName,settings)
    		}
    </script>
    
</head>
<body>
	<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
				<span class="sr-only">Toogle Navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>	
			<a class="navbar-brand" href="?menu=profil">Profil : <?php echo $_SESSION['nama']; ?></a>
		</div>
		<!-- akhir dari navbar atas -->
		<div class="navbar-collapse collaspse">
			<ul class="nav navbar-nav" id="hov">
				<li>
					<a href="?menu=keberadaan"><div class="glyphicon glyphicon-plane"></div>&nbsp;&nbsp;Update Keberadaan</a>
				</li>
			</ul>
			<ul class="nav navbar-nav" id="hov">
				<li>
					<a href="?menu=perjalanan"><div class="glyphicon glyphicon-menu-hamburger"></div>&nbsp;&nbsp;Daftar Riwayat Perjalanan</a>
				</li>
			</ul>
			<ul class="nav navbar-nav" id="hov">
				<li>
					<a href="?logout" onclick="return confirm('Apakah Anda yakin akan keluar dari akun ?');"><div class="glyphicon glyphicon-circle-arrow-right"></div>&nbsp;&nbsp;Keluar</a>
				</li>
			</ul>

			<ul class="nav navbar-nav navbar-right">
				<li>
					<h4><a style="text-decoration:none;color:white;margin-right:30px;margin-top:10px;" class="nav navbar-nav navbar-right">
						<script language="javascript">document.write(tanggallengkap);</script>
					</h4></a>
				</li>
			</ul>
		</div>
	</nav>
	<?php  
		switch (@$_GET['menu']){
			case 'keberadaan':include 'keberadaan.php'; break;	
			case 'perjalanan':include 'riwayat.php'; break;	
			case 'detail':include 'detail_perjalanan.php'; break;	
			case 'profil':include 'profil.php'; break;	
			default:$aksi->alamat("index.php?menu=keberadaan");break;
		}
	?>
	<script type="text/javascript" src="../js/jquery.min.js"></script>
	<script type="text/javascript" src="../js/bootstrap.min.js"></script>
	<script type="text/javascript" src="../js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="../js/show-password.js"></script>
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