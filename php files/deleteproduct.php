<?php

include 'config.php';
  $con=mysqli_connect($hostname, $username, $password,$dbname);

$id=$_GET["id"];
if($_GET["id"]=="")
{
   echo "Error : No Parameter passed " ; 
}
else
{

$query="delete from table_products  where pid='$id' ";

$result = $con->query($query);

if ($result === TRUE)
{
    echo '{"message":"Deleted successfully.","status":"true"}';
}
else
{
    
     echo '{"message":"No Records Found To Delete","status":"false"}';
  
}



mysqli_close($con);
}

?>