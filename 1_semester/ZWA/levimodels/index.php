<?php
session_start();
//checking if session is already exists
//if session exists and you logged in, page will redirect you to the webpage
if(isset($_SESSION['user'])){
    header('Location: profile.php');
}
?>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>log in</title>
    <!-- connecting the styles -->
    <link rel="stylesheet" href="styles/registr.css">
    <link rel="stylesheet" href="styles/buttons.css">
</head>

<!-- authorisation form -->
<body>
  <div class="container">
    <div id="registration">
    <form action="vendor/signin.php" method="post">
        <h1>Log in</h1>
        <input class="input pass" type="text" name="login" placeholder="Input your login" required="required">
        <input class="input pass" type="password" name="password" placeholder="Input the password">
        <div class = "buttons">
            <button class="button_log_sign" type="submit">Log in</button>
            <p>Do not have an account yet?</p>
            <!-- log in and sign up buttons, the're connected to the back-end part of the website -->
            <a class="button_log_sign" href="register.php">Sign up</a>
        <!-- message that will show you succesfull login or error messages if you input invalid data -->
        <?php
            if (isset($_SESSION['message'])) {
                echo '<p class="msg"> ' . $_SESSION['message'] . ' </p>';
            } 
            unset($_SESSION['message']);
        ?>
        </div>
    </form>
    </div>
</div>
</body>
</html>