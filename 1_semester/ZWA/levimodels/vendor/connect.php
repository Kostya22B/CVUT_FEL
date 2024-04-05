<?php
    $connect = mysqli_connect('ip', 'name', 'pwd', 'name');
    if (!$connect) {
        die('Error connect to DataBase');
    }
?>