<?php

include 'config.php';
 
$name=$_GET["name"];
$price=$_GET["price"];
$description=$_GET["description"];
$quantity=$_GET["quantity"];
$photo=$_GET["photo"];
$category=$_GET["category"];
$total_price=$_GET["total_price"];
$uname=$_GET["uname"];
$pid=$_GET["pid"];
$oid=$_GET["oid"];
$d=date('Y-m-d');

$con=mysqli_connect($hostname, $username, $password,$dbname);
mysqli_query ($con,"set character_set_results='utf8'");

      $query_dis1="update table_products set available_count=available_count-".$quantity ." where pid=".$pid;


 mysqli_query($con,$query_dis1);      
           
$query_dis="insert into tb_cart(name, price, category, description, photo, quantity, total_price, uname, pid, order_id, order_date, status) 
values('$name', '$price', '$category', '$description', '$photo', '$quantity', '$total_price', '$uname', '$pid', '$oid', '$d', 'Order Processing');";
//echo $query_dis; 

$retval_dis = mysqli_query($con,$query_dis);

mysqli_close($con);

echo '{"message":"Order Placed successfully.","status":"true"}';
?>