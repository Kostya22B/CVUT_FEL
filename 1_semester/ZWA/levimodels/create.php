<?php
session_start();
if (!isset($_SESSION['user'])) {
    header('Location: index.php');
} 
?>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>add</title>
    <link rel="stylesheet" href="styles/registr.css">
    <link rel="stylesheet" href="styles/buttons.css">
</head>
<!-- register form -->
<body>
  <div class="container">
    <div id="registration">
      <form action="vendor/add.php" method="post" enctype="multipart/form-data" id="form">
        <h1>Add model</h1>
         <!-- message that will show you succesfull login or error messages if you input invalid data -->
        <?php
            if (isset($_SESSION['message'])) {
                echo '<p class="msg"> ' . $_SESSION['message'] . ' </p>';
            }
            unset($_SESSION['message']);
        ?>
        <!-- validation using html 5 -->
        <div class = "form-control">
        <p>Input name of your model</p>
        <input id="mname" value="<?php if(isset($_GET['name'])){echo htmlspecialchars($_GET['name']);}?>" class="input pass" type="text" name="name" required="required" placeholder="name">
        <span class="error"></span>    
        </div>
        <div class = "form-control">
        <p>input link to online disk</p>
        <input id="mlinka" value = "<?php if(isset($_GET['linka'])){echo htmlspecialchars($_GET['linka']);}?>" class="input pass" name="linka" type="link" required="required" placeholder="https:/3d-model.org">
        <span class="error"></span>
        </div>
        <div class = "form-control">
        <p>input type of file</p>
        <input id="mtype" value = "<?php if(isset($_GET['type'])){echo htmlspecialchars($_GET['type']);}?>" class="input pass" name="type" type="text" required="required" placeholder="obj">
        <span class="error"></span>
        </div>
        <div class = "buttons">
        <!-- add button with submit, exit button  -->
        <button class="button_log_sign" type="submit">Add model</button>
            <a class="button_log_sign" href="profile.php">Exit</a>
        </div>
    </form>
    <!-- kontrola formuláře pomocí JavaScriptu on submit -->
    <!-- na (všech!) elementech ve formuláři, při zadávání dat -->
    <!-- stránka použitelná i bez JavaScriptu  -->
    </div>
  </div>
  <script src="js/add.js"></script>
</body>
</html>