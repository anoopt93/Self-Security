<?php
$phno=$_GET['phno'];
include("../connect.php");
$ch=mssql_query("delete from tb_log where username='$phno'");
$ch1=mssql_query("delete from tb_userreg where phone='$phno'");
header("location:userapproval.php");
?>