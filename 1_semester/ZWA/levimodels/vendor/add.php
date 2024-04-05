<?php
    session_start();
    require_once 'connect.php';//link database
    // vars of input data
    $name = mysqli_real_escape_string($connect, $_POST['name']);
    $linka = mysqli_real_escape_string($connect, $_POST['linka']);
    $type = mysqli_real_escape_string($connect, $_POST['type']);
    $log = strval($_SESSION['user']['login']);
    //var for saving some of input data
    $user_data = '&name=' . $name . '&linka=' . $linka . '&type=' . $type;
    //checking if data is not null
    if (empty($name) or empty($linka) or empty($type)){
        header("Location: ../create.php");
        $_SESSION['message'] = 'Input something';
}
else{ 
    $linka_reg = '/^https?:\\/\\/(?:www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b(?:[-a-zA-Z0-9()@:%_\\+.~#?&\\/=]*)$/';
    $type_reg = '/^[a-z]{3,4}$/';
        if (preg_match($linka_reg,$linka)){
            if (preg_match($type_reg,$type)){
    //checking if login and email are in database
    $check_user_linka = mysqli_query($connect, "SELECT * FROM `models` WHERE `linka` = '$linka'");//login
    //if data is is database, it will redirect human to register page with saving login,email,date
    if (mysqli_num_rows($check_user_linka) > 0){
        header("Location: ../create.php?error=already in use&$user_data");
        $_SESSION['message'] = 'This link already exists';
    } 
    else {
        //adding model to database
        mysqli_query($connect, "INSERT INTO `models` (`id`, `name`, `linka`, `type`, `log`) VALUES (NULL, '$name',  '$linka', '$type', '$log')");
        //redirecting to profile page, where you can check if model was added
        header('Location: ../create.php');
        $_SESSION['message'] = 'You are succesfully added model';
        }
    } else {header("Location: ../create.php?error=invalid_data&$user_data"); $_SESSION['message'] = 'Invalid type of file';} 
    } else {header("Location: ../create.php?error=invalid_data&$user_data"); $_SESSION['message'] = 'Invalid link';}
}
?>
