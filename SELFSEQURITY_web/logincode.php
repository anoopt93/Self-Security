<?php  
	session_start();
	
	$uname=$_POST['uname'];
	$pass=$_POST['pass'];
	
include("connect.php");
	
		$ch=mssql_query("select * from tb_log where username='$uname' and pswd='$pass'");
		$chcount=mssql_num_rows($ch);
		echo $chcount;
		if($chcount==0)
		{	
		    echo"<script>window.location.href='login.php?m=Invalid Username or password !!';</script>";
		}
		else
		{
		   $_SESSION['val']='456';
		   // echo"<script>window.location.href='Admin/index.php;</script>";
		   header("location:Admin/index.php");
		}
	


?>
