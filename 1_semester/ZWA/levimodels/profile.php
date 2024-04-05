<?php
session_start();
if (!isset($_SESSION['user'])) {
    header('Location: index.php');
} 
?>
<?php
include 'vendor/connect.php';

?>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>profile</title>
    <!-- styles -->
    <link rel="stylesheet" href="styles/reset.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="styles/burger.css">
    <link rel="stylesheet" href="styles/fonts.css">
    <link rel="stylesheet" href="styles/index.css">
    <link rel="stylesheet" href="styles/media.css">
    <link rel="stylesheet" href="styles/models.css">
    <link rel="stylesheet" href="styles/modules.css">
    <link rel="stylesheet" href="styles/buttons.css">
    <!-- /styles -->
    <!-- script -->
    <script src="js/change-mode.js"></script>
    <!-- /script -->
</head>
<body>

    <!-- Профиль -->

<!-- header with burger menu, theme-switch button,name of company, log out button, some of your data -->
<header>
  <div class="left_header">
        <div>
          <p>LeviModels</p>
        </div>
        <div>
          <div class='d1' id='target'><div  class='d2'></div></div>
        </div>
  </div>
  <div class="right_header">
    <div class="right_hamburger">
        <div class="hamburger-menu">
          <input id="menu__toggle" type="checkbox">
          <label class="menu__btn" for="menu__toggle">
            <span class="fun_span"></span>
          </label>
          <ul class="menu__box">
          <!-- your data and log out button -->
            <li><a class="menu__item"><?= $_SESSION['user']['login'] ?></a></li>
            <li><a class="menu__item"><?= $_SESSION['user']['date'] ?></a></li>
            <li><a href="create.php" class="menu__item">Upload</a></li>
            <li><a href="vendor/logout.php" class="menu__item">log out</a></li>
          <!-- /your data -->
          </ul>
        </div>
    </div>
  </div>
</header>
<!-- /header -->
<div class="block_light">
    <div class='wrapper'>
        <h1 class = "slogan" style="font-family: Cinzel;">we can teach<br>you can learn</h1>
    </div>
</div>
<!--my 3d projects which are here. you can use buttons to download. with adaptive design -->
<h1 style="font-size:60px; text-align:center;">3-D models</h1>
  <div class="container my-5">
  <table class="table">
  <thead>
    <tr>
      <th scope="col">id</th>
      <th scope="col">name</th>
      <th scope="col">link</th>
      <th scope="col">file type</th>
      <th scope="col">author</th>
    </tr>
  </thead>
  <tbody>
    <?php
    $sql = "Select * from `models`";
    $result = mysqli_query($connect,$sql);
    $num = mysqli_num_rows($result);
    $numberPages=5;
    $totalPages = ceil($num/$numberPages);
    // echo $totalPages;
    //creating pagination buttons
    for ($btn=1; $btn<=$totalPages; $btn++){
    echo '<a href="profile.php?page='.$btn.'"><button class="btn btn-dark mx-1 mb-3">'.$btn.'</button></a>';
    }
    if (isset($_GET['page'])){
      $page=$_GET['page'];
      // echo $page;
    } else {
      $page=1;
    }

    $startinglimit = ($page-1)*$numberPages;
    $sql="Select * from `models` LIMIT " .$startinglimit.','.$numberPages;
    $result = mysqli_query($connect,$sql);

    while($row=mysqli_fetch_assoc($result)){
      echo '<tr>
      <th scope="row">'.htmlspecialchars($row['id']).'</th>
      <td>'.htmlspecialchars($row['name']).'</td>
      <td><a href='.htmlspecialchars($row['linka']).'>download</a></td>
      <td>.'.htmlspecialchars($row['type']).'</td>
      <td>'.htmlspecialchars($row['log']).'</td>
    </tr>';
    }
    ?>
  </tbody>
  </table>
</div>
  <!-- /models -->
</body>
</html>