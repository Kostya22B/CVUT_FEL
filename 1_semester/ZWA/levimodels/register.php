<?php
    session_start();
    //checking if session is already exists
    //if session exists and you logged in, page will redirect you to the webpage
    if (isset($_SESSION['user'])) {
        header('Location: profile.php');
    }
?>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>registration</title>
    <link rel="stylesheet" href="styles/registr.css">
    <link rel="stylesheet" href="styles/buttons.css">
</head>
<!-- register form -->
<body>
  <div class="container">
    <div id="registration">
      <form action="vendor/signup.php" method="post" enctype="multipart/form-data" id="form">
        <h1>Create an account</h1>
         <!-- message that will show you succesfull login or error messages if you input invalid data -->
        <?php
            if (isset($_SESSION['message'])) {
                echo '<p class="msg"> ' . $_SESSION['message'] . ' </p>';
            }
            unset($_SESSION['message']);
        ?>
        <!-- validation using html 5 -->
        <div class = "form-control">
        <p>login must be from 4 to 16 lowercase symbols</p>
        <input id="login" value="<?php if(isset($_GET['login'])){echo htmlspecialchars($_GET['login']);}?>" class="input pass" type="text"     
        name="login"            placeholder="What's your login"   required="required" pattern="[A-Za-z]{4,16}" title="4 to 16 lowercase letters">
        <span class="error"></span>    
        </div>
        <div class = "form-control">
        <p>input valid email</p>
        <input id="email" value = "<?php if(isset($_GET['login'])){echo htmlspecialchars($_GET['email']);}?>" class="input pass" type="email"    name="email"            
        placeholder="Email address"       required="required" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$">
        <span class="error"></span>
        </div>
        <div class = "form-control">
        <p>input your birthdate</p>
        <input id="date" value = "<?php if(isset($_GET['login'])){echo htmlspecialchars($_GET['date']);}?>" class="input pass" type="date"     name="date" required="required">
        <span class="error"></span>
        </div>
        <div class = "form-control">
        <p>Password must contain at least one  number and one uppercase and lowercase letter, and at least 8 or more characters</p>
        <input id="password" class="input pass" type="password" name="password"         placeholder="Enter the password"  required="required" 
        pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
        title="Must contain at least one  number and one uppercase and lowercase letter, and at least 8 or more characters">
        <span class="error"></span>
        </div>
        <div class = "form-control">
        <p>repeat the password</p>
        <input id="cpassword" class="input pass" type="password" name="password_confirm" placeholder="Repeat the password" required="required" 
        pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}">
        <span class="error"></span>
        </div>
        <div class = "buttons">
        Do you want to get notification?(It is not necessary)
        <input id="notification" type="checkbox">
        <!-- sign up button with submit, log in buttons  -->
        <button class="button_log_sign" type="submit">Sign up</button>
            <p>Have an account yet?</p>
            <a class="button_log_sign" href="index.php">Log in</a>
        </div>
    </form>
    <!-- kontrola formuláře pomocí JavaScriptu on submit -->
    <!-- na (všech!) elementech ve formuláři, při zadávání dat -->
    <!-- stránka použitelná i bez JavaScriptu  -->
    </div>
  </div>
  <script src="js/register.js"></script>
</body>
</html>