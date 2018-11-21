<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <title></title>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style>
        body {
            font-family: "Microsoft YaHei";
        }
        .margin0{
            margin: 0;
            padding: 0;
        }
        input,button,select,textarea{
            outline:none;
            border:0;
            -webkit-appearance: none;
        }
        .gry-bg{
            background-color: #E6E9EA;
        }
        .activity-message{
            float: left;
            width: 100%;
            margin: 3px 0 0 0;
            background-color: #fff;
        }
        @media (min-width:780px){
            .activity-message{
                width: 30%;
                margin-left:35%;
            }
        }.message-title{
            float: left;
            width: 88%;
            margin: 3%;
            padding: 0 3% 3% 3%;
            border-bottom: #C6CFD2 solid 1px;
        }
        .big-title{
            float: left;
            width: 100%;
            font-size: 16px;
            font-weight: 600;
            color: #505D62;
        }
        .title-tab{
            color: #73848C;
            line-height: 30px;
            font-size: 14px;
        }
        .title-tab-next{
            color: #40c2fe;
            line-height: 30px;
        }
        .activity-img{
            float: left;
            width: 100%;
        }
        .activity-img>img{
            width: 100%;
            height: auto;
        }
        .activity-content{
            float: left;
            width: 94%;
            padding: 1% 3%;
            color: #73848C;
            font-size: 14px;
            overflow: hidden;
            margin-top: -1em;
        }
        .activity-content img{
        	float:left;
	        max-width:100%;
        }
        .activity-content video{
        	float:left;
        	width:100%;
        }

    </style>
</head>
<body class="margin0 gry-bg">
<div>
    <div class="activity-message">
        <div class="message-title">
            <span class="big-title">${title}</span>
            <span class="title-tab">${timedate}</span>&nbsp;&nbsp;<span class="title-tab-next">威特龙</span>
        </div>
        <div class="activity-img margin0">
            <img src="${thumb_url}">
        </div>
        <div class="activity-content" id="activity-content">
            ${content}
        </div>
    </div>
</div>
</body>
</html>
