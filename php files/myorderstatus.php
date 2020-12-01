<?php

include 'config.php';

$oid=$_GET["oid"];


 
//creating connection
$con=mysqli_connect($hostname, $username, $password,$dbname);
mysqli_query ($con,"set character_set_results='utf8'");
$oid_query = "SELECT * FROM `tb_cart` WHERE order_id='$oid' ";
//executing query with connection
$oid_res = mysqli_query($con,$oid_query);
$rows = array();
//output
while($row = mysqli_fetch_assoc($oid_res)) {
$rows[] = array('pid' => $row['pid'],'id' => $row['id'],'name' => $row['name'],'price' => $row['price'],'description' => $row['description'],'photo' => $row['photo']
,'quantity' => $row['quantity'],'total_price' => $row['total_price'],'uname' => $row['uname'],'order_id' => $row['order_id'],'order_date' => $row['order_date'],'status' => $row['status']);
}

echo json_encode($rows);
//Json End
mysqli_close($con);
?>

