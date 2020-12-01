<?php

include 'config.php';

$pid=$_GET["pid"];




//creating connection
$con=mysqli_connect($hostname, $username, $password,$dbname);
mysqli_query ($con,"set character_set_results='utf8'");
$f_query = "SELECT * FROM `table_feedback` where pid='$pid'";
//executing query with connection
$f_res = mysqli_query($con,$f_query);
$rows = array();
//output
while($row = mysqli_fetch_assoc($f_res)) {
$rows[] = array('pid' => $row['pid'],'name' => $row['name'],'email' => $row['email'],'rating' => $row['rating'],'reason' => $row['reason']);
}

echo json_encode($rows);
//Json End
mysqli_close($con);
?>

