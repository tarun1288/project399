<?php

include 'config.php';
 
$pid=$_GET["pid"];
$pname=$_GET["pname"];
$email=$_GET["email"];
$rating=$_GET["rating"];
$r=$_GET["reason"];


$con=mysqli_connect($hostname, $username, $password,$dbname);
mysqli_query ($con,"set character_set_results='utf8'");
$query_json = "SELECT * from table_feedback where pid='$pid' AND email='$email';";
            $result = mysqli_query($con,$query_json);
            $row = mysqli_fetch_array($result);
            if(!$row)
            {
            
          
$query_dis="insert into table_feedback(pid, name, email, reason, rating) values('$pid', '$pname', '$email', '$r', '$rating');";
//echo $query_dis; 

$retval_dis = mysqli_query($con,$query_dis);

mysqli_close($con);

echo '{"message":"Feedback is Submited successfully.","status":"true"}';
}else{
   echo '{"message":"feedback is given already.","status":"false"}'; 
}
?>