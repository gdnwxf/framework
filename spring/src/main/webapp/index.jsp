<html>
<body>
<h2>Hello World!</h2>
<form  id="thisForm">
		<input  name="userName" value="123" >  
		<button class="sub" >submit</button>
</form>
</body>
<script type="text/javascript" src="/resource/js/jquery-1.8.3.js"></script>
<script type="text/javascript"> 
	  $(".class").click(function(){
		$.post("/user/add", { name: "John", time: "2pm" },
				   function(data){
		     process(data);
		   }, "json");
	  })
</script>
</html>
