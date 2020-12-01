<?php
if (!is_dir('images/')) {
    mkdir('images/', 0777, true);
}
include 'config.php';
 
$productname=$_POST["productname"];
$price=$_POST["price"];
$description=$_POST["description"];
$quantity=$_POST["quantity"];
$cid=$_POST["cid"];
$seller_email=$_POST["seller_email"];


$result = array("success" => $_FILES["file"]["name"]);
$file_path = basename( $_FILES['file']['name']);
$picimg_url='images/'.$file_path;
if(move_uploaded_file($_FILES['file']['tmp_name'], 'images/'.$file_path)) {
    $result = array("success" => "File successfully uploaded");
} else{
    $result = array("success" => "error uploading file");
}
$con=mysqli_connect($hostname, $username, $password,$dbname);
mysqli_query ($con,"set character_set_results='utf8'");
/*$query_json = "SELECT * from table_registration where email=$email;";
            $result = mysqli_query($con,$query_json);
            $row = mysqli_fetch_array($result);
            if(!$row)
            {*/
            
            $picimg_url="http://cegepfashionstore.com/fashionstore/".$picimg_url;
$query_dis="insert into table_products(productname, price, description, photo, quantity, cid, seller_email,available_count) values($productname, $price, $description, '$picimg_url', $quantity, $cid, $seller_email,$quantity);";
//echo $query_dis; 

$retval_dis = mysqli_query($con,$query_dis);

mysqli_close($con);

echo '{"message":"Product is added successfully.","status":"true"}';
/*}else{
   echo '{"message":"Username is already exist.","status":"false"}'; 
}
?>*/