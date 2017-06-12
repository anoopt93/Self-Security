<?php
$phno=$_GET['phno'];
include("../connect.php");
$ch=mssql_query("update tb_log set status='1' where username='$phno'");
header("location:userapproval.php");
?>