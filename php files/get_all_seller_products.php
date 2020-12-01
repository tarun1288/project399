<?php

include 'config.php';

$pid=$_GET["pid"];
$productname=$_GET["productname"];
$price=$_GET["price"];
$description=$_GET["description"];
$photo=$_GET["photo"];
$quantity=$_GET["quantity"];
$cid=$_GET["cid"];
$seller_email=$_GET["email"];


//creating connection
$con=mysqli_connect($hostname, $username, $password,$dbname);
mysqli_query ($con,"set character_set_results='utf8'");
$teams_query = "SELECT * from table_products where seller_email=$email;";
//executing query with connection
$teams_res = mysqli_query($con,$teams_query);
$rows = array();
//output
while($row = mysqli_fetch_assoc($teams_res)) {
$rows[] = array('pid' => $row['pid'],'productname' => $row['productname'],'price' => $row['price'],'description' => $row['description'],'photo' => $row['photo']
,'quantity' => $row['quantity'],'cid' => $row['cid'],'seller_email' => $row['email']);
}

echo json_encode($rows);
//Json End
mysqli_close($con);
?>

