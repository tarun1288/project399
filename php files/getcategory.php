<?php

include 'config.php';

$cid=$_GET["cid"];
$cname=$_GET["cname"];



//creating connection
$con=mysqli_connect($hostname, $username, $password,$dbname);
mysqli_query ($con,"set character_set_results='utf8'");
$teams_query = "SELECT * FROM `table_category`";
//executing query with connection
$teams_res = mysqli_query($con,$teams_query);
$rows = array();
//output
while($row = mysqli_fetch_assoc($teams_res)) {
$rows[] = array('cid' => $row['cid'],'cname' => $row['cname']);
}

echo json_encode($rows);
//Json End
mysqli_close($con);
?>

