
<head>
<style type="text/css">
.auto-style1 {
	text-align: center;
}
</style>
</head>

<?php
include("head.php");
?>
<table style="width: 100%;background-color:#FFFFFF;" cellpadding="5" cellspacing="5">
	<tr style="color:white">
		<th bgcolor="#663300" class="auto-style1" style="height: 44px; width: 400px;">Name</th>
		<th bgcolor="#663300" class="auto-style1" style="height: 44px; width: 241px;">Phone Number</th>
		<th bgcolor="#663300" class="auto-style1" style="height: 44px; width: 298px;">Email Id</th>
		<td class="auto-style1" style="height: 44px; width: 60px;"></td>
		<td class="auto-style1" style="height: 44px"></td>
	</tr>


<?php
include("../connect.php");
$ch=mssql_query("select * from tb_userreg inner join tb_log on tb_userreg.id=tb_log.id where tb_log.status='0'");
$chcount=mssql_num_rows($ch);
if($chcount=='0')
{
?>	
	<tr style="color:white" bgcolor="#006699">
		<td colspan="5">No Records!!</td>
	</tr>
<?php
}
else
{
	while($fe=mssql_fetch_array($ch))
	{
	?>
		<tr style="color:white">
			<td class="auto-style1" bgcolor="#006699" style="height: 37px; width: 400px;"><?php print $fe[1] ?></td>
			<td class="auto-style1" bgcolor="#006699" style="height: 37px; width: 241px;"><?php print $fe[3] ?></td>
			<td class="auto-style1" bgcolor="#006699" style="height: 37px; width: 298px;"><?php print $fe[2] ?></td>
			<td style="height: 37px; width: 60px;"><a href="approve.php?phno=<?php print $fe[3] ?>" style="text-decoration:none;background-color:#333333;color:white;padding:5px 5px 5px 5px ;border-radius:5px;box-shadow:3px 2px 1px rgba(0,0,0,.7);font-family:'Times New Roman', Times, serif">Approve</a></td>
			<td style="height: 37px" class="auto-style1"><a href="reject.php?phno=<?php print $fe[3] ?>" style="text-decoration:none;background-color:#333333;color:white;padding:5px 5px 5px 5px ;border-radius:5px;box-shadow:3px 2px 1px rgba(0,0,0,.7);font-family:'Times New Roman', Times, serif">Reject</a></td>
		</tr>
	
	<?php
	}
}
?>


<?php
include("footer.php");
?>
</table>
