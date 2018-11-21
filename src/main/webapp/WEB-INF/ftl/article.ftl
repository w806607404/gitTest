<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<title>${title}</title>
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
 <style>
 body {
	font: 14px;
	font-family: "Microsoft YaHei";
	padding: 0;
	margin: 0;
	color: #555555;
}

a,
img { border: 0; }
.container-fluid img {
	width: 100%;
	margin-top: 15px;
	margin-bottom: 15px;
}
.container-fluid {
	float:left;
	width:100%;
	padding-right: 15px;
	padding-left: 15px;
	line-height:24px;
}
@media(min-width:640px){
	body{
		text-align:center;
	}
	.container-fluid {
		width:640px;
		padding:20px;
	}
}
 </style>
</head>
<body>
<div class="container-fluid">
  
  <div class="text"> 
  	${content}
  </div>
  
</div>

</body>
</html>
