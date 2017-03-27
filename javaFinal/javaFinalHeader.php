<html>
<head>

<img id="head" src="blackjack.jpg"></img>

<h1 id="headTitle">Blackjack Party!</h1>

<ul>
	
  <li><a href="finalHome.php">My Account</a></li>
  <li><a href="finalTeams.php">Blackjack</a></li>
  <li><a href="finalPlayers.php">Store</a></li>
  <li><a href="finalSchedule.php">Help</a></li>
      <div id="statusButton">
  <?php 
		//If the user is signed in it will display an option to sign out. If they are not it will show an option to sign in.
		if(empty($_SESSION['userID'])){
			echo "<li ><a href = 'finalLogin.php'>Sign In</a></li>";
		}
		else{
			echo "<li ><a href = 'finalLogout.php'>Sign Out</a></li>";
			}
	?>
	</div>
	
</ul>


</head>
</html>