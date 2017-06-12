<html>
<head>
<link rel="stylesheet" type="text/css" href="style.css">
<script src="jquery-1.9.1.min.js"></script>
<script>
$(document).ready(function(){
$('.form').find('input, textarea').on('keyup blur focus', function(e) {

  var $this = $(this),
    label = $this.prev('label');

  if (e.type === 'keyup') {
    if ($this.val() === '') {
      label.removeClass('active highlight');
    } else {
      label.addClass('active highlight');
    }
  } else if (e.type === 'blur') {
    if ($this.val() === '') {
      label.removeClass('active highlight');
    } else {
      label.removeClass('highlight');
    }
  } else if (e.type === 'focus') {

    if ($this.val() === '') {
      label.removeClass('highlight');
    } else if ($this.val() !== '') {
      label.addClass('highlight');
    }
  }

});

$('.tab a').on('click', function(e) {

  e.preventDefault();

  $(this).parent().addClass('active');
  $(this).parent().siblings().removeClass('active');

  target = $(this).attr('href');


});
});
</script>
<style>
#linka
{
	position:absolute;
	background-color:rgba(19, 35, 47, 0.9);
	padding:20px 20px 20px 20px;
	font-size:large;
	margin-left:600px;
	margin-top:-70px;
	color:white;
	border-radius:5px;
	box-shadow:2px 2px 10px #000000;
}
#linka:hover
{
	background:white;
	color:black;
	box-shadow:2px 2px 10px #000000;
}
</style>

</head>
<body>
<a href="index.php" id="linka">Back To Home</a>

<div class="form">
           
       <div id="signup">   
          <h1>Sign Up for Free</h1>
          
          <form action="regcode.php" method="post">		
		          <div class="field-wrap">
		            <label>
		              Enter Your Name<span class="req">*</span>
		            </label>
		            <input type="text" required autocomplete="off" name="name"/>
		          </div>
		
		          <div class="field-wrap">
		            <label>
		              Enter Your Phone Number<span class="req">*</span>
		            </label>
		            <input type="text" required autocomplete="off" name="phno" pattern="\d*" minlength="10" maxlength="10"/>
		          </div> 
		
		          <div class="field-wrap">
		            <label>
		              Email Address<span class="req">*</span>
		            </label>
		            <input type="email" required autocomplete="off" name="email"/>
		          </div>
		          
		          <div class="field-wrap">
		            <label>
		              Enter A Password<span class="req">*</span>
		            </label>
		            <input type="password" required autocomplete="off" name="pass"/>
		          </div>
		
		          <div class="field-wrap">
		            <label>
		              Reenter Password<span class="req">*</span>
		            </label>
		            <input type="password"required autocomplete="off" name="cnpass"/>
		          </div>
		          
		          <input type="submit" class="button button-block" value="Sign Up"/>          
          </form>

       </div>
        
             <?php 
			$msg=$_GET['m'];
			if($msg=="Error")
			{
			?>
			 <font color="red" size="4px">
			 <?php
			 print($msg); 
			 }
			 
			 else
			 {
			 ?>
			 </font>
			  <font color="white" size="4px">
			 <?php 
			 print($msg); 
			 }
			?>
           </font>
       
</div> <!-- /form -->
</body>
</html>
