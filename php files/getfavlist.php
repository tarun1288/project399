<?php

include 'config.php';

$email=$_GET["email"];

//creating connection
$con=mysqli_connect($hostname, $username, $password,$dbname);
mysqli_query ($con,"set character_set_results='utf8'");
$teams_query = "SELECT * FROM `fav_list` join table_products on fav_list.pid =table_products.pid where email='$email' and status='Like'";
//executing query with connection
$teams_res = mysqli_query($con,$teams_query);
$rows = array();
//output
while($row = mysqli_fetch_assoc($teams_res)) {
$rows[] = array('pid' => $row['pid'],'productname' => $row['productname'],'price' => $row['price'],'description' => $row['description'],'photo' => $row['photo']
,'quantity' => $row['quantity']
,'cid' => $row['cid'],'seller_email' => $row['seller_email'],'email' => $row['email']
,'status' => $row['status']);
}

echo json_encode($rows);
//Json End
mysqli_close($con);
?>

