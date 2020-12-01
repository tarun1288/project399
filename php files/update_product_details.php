<?php
if (!is_dir('images/')) {
    mkdir('images/', 0777, true);
}
include 'config.php';
 
$pid=$_POST["pid"];
$pname=$_POST["name"];
$price=$_POST["price"];
$description=$_POST["description"];
$quantity=$_POST["quantity"];
$seller_email=$_POST["seller_email"];
//$cid=$_POST["cid"];



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

            
            $picimg_url="http://cegepfashionstore.com/fashionstore/".$picimg_url;
            //productname=$productname,price=$price,description=$description,quantity=$quantity,seller_email=$seller_email,photo='$picimg_url'
$query_dis="update table_products set productname=$pname, price=$price, description=$description, quantity=$quantity, photo='$picimg_url' where pid=$pid";
//echo $query_dis; 

$retval_dis = mysqli_query($con,$query_dis);

mysqli_close($con);

echo '{"message":"Updated successfully.","status":"true"}';

?>