<?php
    session_start();
    require_once 'connect.php';//link database
    // vars of input data
    $login = mysqli_real_escape_string($connect, $_POST['login']);
    $email = mysqli_real_escape_string($connect, $_POST['email']);
    $date = mysqli_real_escape_string($connect, $_POST['date']);
    $password = mysqli_real_escape_string($connect,$_POST['password']);
    $password_confirm = mysqli_real_escape_string($connect, $_POST['password_confirm']);
    //var for saving some of input data
    $user_data = '&login=' . $login . '&email=' . $email . '&date=' . $date;
    //patterns for types
    $login_pattern = '/^[a-z]{4,}/';
    $email_pattern = '/^[\w\d_.+-]+@[\w\d-]+.[\w]+$/';
    $password_pattern = '/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,32}$/';
    //checking if data is not null
    if (empty($login) or empty($email) or empty($date) or empty($password) or empty($password_confirm)) {
        header("Location: ../register.php");
        $_SESSION['message'] = 'Input something';
} else{ 
    if (preg_match($login_pattern,$login)){
    if (preg_match($email_pattern,$email)){
    if (preg_match($password_pattern,$password)){
    //checking if login and email are in database
    $check_user_email = mysqli_query($connect, "SELECT * FROM `users` WHERE `email` = '$email'");//email
    $check_user_login = mysqli_query($connect, "SELECT * FROM `users` WHERE `login` = '$login'");//login
    //if data is is database, it will redirect human to register page with saving login,email,date
    if (mysqli_num_rows($check_user_email) > 0){
        header("Location: ../register.php?error=already in use&$user_data");
        $_SESSION['message'] = 'This email is already in use, try another';
    }
    elseif (mysqli_num_rows($check_user_login) > 0){
        header("Location: ../register.php?error=already in use&$user_data");
        $_SESSION['message'] = 'This login is already in use, try another';
    }
    else {
    if ($password === $password_confirm) { //checking if passwords are same
            $password = md5($password . $password);
        //adding user to database
        mysqli_query($connect, "INSERT INTO `users` (`id`, `login`, `email`, `date`, `password`) VALUES (NULL, '$login',  '$email', '$date', '$password')");
        //redirecting to login page, where you can login using data from registration
        header('Location: ../index.php');
        $_SESSION['message'] = 'You are succesfully signed up!';
        } else {
        //if passwords are not same page will redirect you to register form
        header("Location: ../register.php?error=invalid_data&$user_data");
        $_SESSION['message'] = 'passwords are not same';
    }
}
    } else {header("Location: ../register.php?error=invalid_data&$user_data"); $_SESSION['message'] = 'Invalid format of password';}
    } else {header("Location: ../register.php?error=invalid_data&$user_data"); $_SESSION['message'] = 'Invalid email';}
} else {header("Location: ../register.php?error=invalid_data&$user_data"); $_SESSION['message'] = 'Invalid login, from 4 to 16 lowercase symbols';}
}
?>
