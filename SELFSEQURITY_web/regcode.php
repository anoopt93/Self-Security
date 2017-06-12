<?php
	$name=$_POST['name'];
	$phno=$_POST['phno'];
	$email=$_POST['email'];
	$pass=$_POST['pass'];
	$cnpass=$_POST['cnpass'];
	
	$myServer = "windflower.arvixe.com";
	$myUser = "student";
	$myPass = "123456";
	$myDB = "db_spychild"; 

	
	//connection to the database
	$dbhandle = mssql_connect($myServer, $myUser, $myPass)
	  or die("Couldn't connect to SQL Server on $myServer"); 
	
	//select a database to work with
	$selected = mssql_select_db($myDB, $dbhandle)
	  or die("Couldn't open database $myDB"); 
	
	//declare the SQL statement that will query the database vno =KL-7-C-4859
	//$query = "insert into tb_log (username,pswd,type,status) values('$name','$cnpass','user','0')";
	
	//execute the SQL query and return records
	if($pass==$cnpass)
	{
		$ch=mssql_query("select * from tb_log where username='$phno' or pswd='$pass'");
		$chcount=mssql_num_rows($ch);
		if($chcount==0)
		{	
			$insreg=mssql_query("insert into tb_userreg (name,email,phone)values('$name','$email','$phno')");
			$mx=mssql_query("select max(id) from tb_userreg");
			$fetmx=mssql_fetch_row($mx);
			$maxid=$fetmx[0];
			
			$query="insert into tb_log (id,name,username,pswd,type,status) values('$maxid','$name','$phno','$cnpass','user','0')";
			$result = mssql_query($query)or die("insertion error!!");
		    echo"<script>window.location.href='registration.php?m=Registration Successfull.Please wait for approval';</script>";
		}
		else
		{
		     echo"<script>window.location.href='registration.php?m=phone number or password already exist!!';</script>";
			//echo "username and password already exist!!";
		}
	}
	else
	{
		echo"<script>window.location.href='registration.php?m=passwords not matching!!';</script>";
		//echo "passwords not matching!!";
	}


?>
