<?php

    session_start();
    include 'connect.php';
    // creating new vars to check it
    $login = $_POST['login'];
    $password = md5($_POST['password'] . $_POST['password']);
    $sanitized_login = mysqli_real_escape_string($connect, $login);
    $sanitized_password = mysqli_real_escape_string($connect, $password);
    // check if data is in database and correct
    $check_user = mysqli_query($connect, "SELECT * FROM `users` WHERE `login` = '$sanitized_login' AND `password` = '$sanitized_password'");
    if (mysqli_num_rows($check_user) > 0) {
        $user = mysqli_fetch_assoc($check_user);
        // creating the session
        $_SESSION['user'] = [
            "id" => htmlspecialchars($user['id']),
            "email" => htmlspecialchars($user['email']),
            "date" => htmlspecialchars($user['date']),
            "login" => htmlspecialchars($user['login'])
        ];
        // redirecting to the webpage
        header('Location: ../profile.php');
    }
    // if data is incorrect, redirecting to login form(cleaning all input data for security purposes)
     else {
        header('Location: ../index.php');
        $_SESSION['message'] = 'Invalid login or password';
    }
    ?>
<pre>
    <?php
    print_r($check_user);
    print_r($user);
    ?>
</pre>
