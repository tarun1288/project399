<?php

include 'config.php';
$uname=$_GET["uname"];
$con=mysqli_connect($hostname, $username, $password,$dbname);
mysqli_query ($con,"set character_set_results='utf8'");
$query_json ="SELECT * from table_registration where email='$uname'" ;
$retval_json = mysqli_query($con,$query_json);
$rows = array();
while($row = mysqli_fetch_assoc($retval_json)) {
$rows[] = array('uid' => $row['uid'],'name' => $row['name'],'gender' => $row['gender'],'dob' => $row['dob'],'email' => $row['email'],'phone' => $row['phone'],'password' => $row['password'],'pic' => $row['pic']);
}

echo json_encode($rows);
//Json End
mysqli_close($con);
?>