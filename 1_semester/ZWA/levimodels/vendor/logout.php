<?php
header('Location: ../index.php');// changing header to log in form
session_start();
$_SESSION['message'] = "";
unset($_SESSION['user']);//destroying session
?>