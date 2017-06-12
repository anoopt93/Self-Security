<?php 
session_start();
unset($_SESSION['val']);
session_destroy();

header("location:Index.php");
 
?>
