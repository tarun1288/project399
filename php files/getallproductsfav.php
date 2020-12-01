<?php

include 'config.php';

$email=$_GET['email'];

//creating connection
$con=mysqli_connect($hostname, $username, $password,$dbname);
mysqli_query ($con,"set character_set_results='utf8'");


$t_query="SELECT CASE WHEN status IS NULL THEN 'Unlike' ELSE status END AS status, table_products.pid,productname,price,description,photo,quantity,cid,seller_email,available_count,fav_list.email FROM `table_products` LEFT JOIN fav_list on table_products.pid=fav_list.pid  AND fav_list.email='$email' GROUP BY table_products.pid";

//executing query with connection
$t_res = mysqli_query($con,$t_query);
$rows = array();
//output
while($row = mysqli_fetch_assoc($t_res)) {
$rows[] = array('pid' => $row['pid'],'productname' => $row['productname'],'price' => $row['price'],'description' => $row['description'],'photo' => $row['photo']
,'quantity' => $row['quantity']
,'cid' => $row['cid'],'seller_email' => $row['seller_email'],'email' => $row['email']
,'status' => $row['status']);
}

echo json_encode($rows);
//Json End
mysqli_close($con);
?>

