<?php

include 'config.php';




 
//creating connection
$con=mysqli_connect($hostname, $username, $password,$dbname);
mysqli_query ($con,"set character_set_results='utf8'");
$teams_query = "SELECT DISTINCT(order_id), uname, order_date, status from `tb_cart` ";
//executing query with connection
$teams_res = mysqli_query($con,$teams_query);
$rows = array();
//output
while($row = mysqli_fetch_assoc($teams_res)) {
$rows[] = array('order_id' => $row['order_id'],'uname' => $row['uname'],'order_date' => $row['order_date'],'status' => $row['status']);
}

echo json_encode($rows);
//Json End
mysqli_close($con);
?>

