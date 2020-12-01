<?php

include 'config.php';
 
$oid=$_GET["oid"];
$status=$_GET["status"];


$con=mysqli_connect($hostname, $username, $password,$dbname);
mysqli_query ($con,"set character_set_results='utf8'");

$query_dis="update tb_cart set status='$status' where order_id='$oid'";
//echo $query_dis; 

$retval_dis = mysqli_query($con,$query_dis);

mysqli_close($con);

echo '{"message":"Order Updated successfully.","status":"true"}';

?>