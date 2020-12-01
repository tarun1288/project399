<?php

include 'config.php';
 
$name=$_GET["name"];
$con=mysqli_connect($hostname, $username, $password,$dbname);
mysqli_query ($con,"set character_set_results='utf8'");
$query_dis="insert into table_registration(name) values('$name');";
//echo $query_dis; 

$retval_dis = mysqli_query($con,$query_dis);

mysqli_close($con);


?>