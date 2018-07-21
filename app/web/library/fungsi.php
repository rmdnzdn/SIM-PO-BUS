<?php  
	
	class oop{
		function alamat($redirect){
			echo "<script>document.location.href='$redirect';</script>";
		}

		function simpan($table,array $field, $alamat){
			$sql = "INSERT INTO $table SET";
			foreach ($field as $key => $value) {
			  	$sql.=" $key = '$value',";
			  }  
			$sql = rtrim($sql, ',');
			$jalan = mysql_query($sql);
			if($jalan){
				echo "<script>alert('Data Berhasil Disimpan !!!');document.location.href='$alamat'</script>";
			}else{
				echo mysql_error();
			}
		}

		function simpanlsg($table, array $field,$alamat){
			$sql="INSERT INTO $table SET";
			foreach($field as $key => $value){
				$sql.=" $key = '$value',";
			}
			$sql=rtrim($sql,',');
			$jalan=mysql_query($sql);
			if($jalan){
				echo "<script>document.location.href='$alamat'</script>";
			}else{
				echo "<script>alert('Data Gagal Disimpan');document.location.href='$alamat'</script>";
			}
		}

		function pesan($pesan){
			echo "<script>alert('$pesan');</script>";
		}

		function redirect($alamat){
			echo "<script>document.location.href='$alamat'</script>";
		}

		function tampil($table,$cari,$urut){
			$sql="SELECT * FROM $table $cari $urut";
			$tampil=mysql_query($sql);
			while($data=mysql_fetch_array($tampil))
				$isi[]=$data;
			return @$isi;
		}

		function hapus($table,$where,$alamat){
			$sql = "DELETE FROM $table WHERE $where";
			$jalan = mysql_query($sql);
			if($jalan){
				echo "<script>alert('Berhasil Dihapus !!!');document.location.href='$alamat'</script>";
			}else{
				echo mysql_error();
			}
		}

		function edit($table,$where){
			$sql = "SELECT * FROM $table WHERE $where";
			$jalan = mysql_fetch_array(mysql_query($sql));
			return $jalan;
		}

		function ubah($table, array $field,$where,$alamat){
			$sql = "UPDATE $table SET";
			foreach ($field as $key => $value) {
				$sql.=" $key = '$value',";
			}
			$sql = rtrim($sql,',');
			$sql.= "WHERE $where";
			$jalan = mysql_query($sql);
			if($jalan){
				echo "<script>alert('Berhasil Diubah !!!');document.location.href='$alamat'</script>";
			}else{
				echo mysql_error();
			}
		}

		function login($table, $user, $pass, $jabatan, $alamat){
			$qw=mysql_query("SELECT * FROM $table WHERE username='$user'");
			$cekuser=mysql_num_rows($qw);
			if ($cekuser > 0) {
				$cekusera['0']=1; //user
				$qw2=mysql_query("SELECT * FROM $table WHERE username='$user' AND password='$pass'");
				$cekpass=mysql_num_rows($qw2);
				if ($cekpass > 0) {
					$cekusera['1']=1; //pass
					$qw3=mysql_query("SELECT * FROM $table WHERE username='$user' AND password='$pass' AND jabatan='$jabatan'");
					$dat=mysql_fetch_array($qw3);
					$ceklvl=mysql_num_rows($qw3);
					if ($ceklvl > 0) {
						echo "<script>alert('Login Berhasil, Selamat Datang $dat[nama_user]');</script>";
						$cekusera['2']=$dat['jabatan']; //Jabatan
						$_SESSION['user']=$dat['username'];
						$_SESSION['nama']=$dat['nama'];
						$_SESSION['jabatan']=$dat['jabatan'];
					} else {
						$cekusera['2']=0;
						echo "<script>alert('Login Gagal !! Periksa Kembali Jabatan Anda ');</script>";
					}
				} else {
					$cekusera['1']=0;
					echo "<script>alert('Login Gagal !! Periksa Kembali Password Anda ');</script>";
				}
			}else{
				$cekusera['0']=0;
					echo "<script>alert('Login Gagal !! Periksa Kembali Username Anda');</script>";
			}
			return $cekusera;
		}
		
		function ceklogin($home,$jabatan){
			if (empty($_SESSION['user']) || empty($_SESSION['jabatan'])) {
				echo "<script>alert('Harap Login Terlebih dahulu');document.location.href='$home';</script>";
				// header("location:$home");
			}else{
				if ($_SESSION['jabatan']!==$jabatan) {
					echo "<script>alert('Anda tidak bisa mengakses halaman ini');document.location.href='$home';</script>";
				}
			}
		}

		function logout(){
			session_destroy();
			echo "<script>alert('Log Out Berhasil');</script>";
		}
	}

?>