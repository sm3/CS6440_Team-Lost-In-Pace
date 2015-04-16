<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome</title>

<style>
form#text1 {
    display: none;
    border: 2px solid black;
    background-color: white;
    padding: 10px;
    position: absolute;
    top: 41px;
    left: 83%;
    z-index: 1;
    color: #999966;
}

div#main {
    float: left;
    background-color: #FFCC80;
    width: 100%;
    padding: 0px 10px;
    margin: -10px;
    position: relative;
}

a.box {
    float: right;
    text-decoration: none;
    color: #EDEDED;
    background-color: orange;
    border: 1px solid black;
    padding: 5px;
    margin: 10px 20px;
    position: relative;
    letter-spacing: 1px;
}

div#welcome {
    position: absolute;
	float: left;
	left: 40%;
	top: 5px;
}

p#wel {
    text-align: center;
    position: relative;
    font-weight: bold;
    padding: 0px;
    margin: 0px;
    line-height: 180%;
    left: 20%;
    color: #999966;
    font-size: 22px;
}

div#logo {
    float:left;
    position: relative;
}

p#name {
    font-size: 50px;
    font-weight: bold;
    color: #EDEDED;
    background-color: #FFA500;
    margin: 10px 0px 0px -10px;
    padding: 10px;
    position: relative;
}

div#welcome_form {
	position: absolute;
	border: 1px solid black;
	float: left;
	left: 45%;
	padding: 10px;
	top: 175px;
}

</style> 

</head>
<body>

<header>
    <div id="logo">
        <p id="name">PACE</p>
    </div>
    <div id="welcome">
        <p id="wel">Performance Dashboard: </br>
        WELCOME
        </p>
    </div>
</header>

<section>
    <div id="main">
        <a class="box" href="#" onclick="window.print();">Print</a>
        <a class="box" href="#" onclick="showStuff('text1');">Search</a> 
        <a class="box" href="/Pace-3/ServletHome" >Home</a> 
        <form id="text1" action="ServletSearchId">Search by Patient ID:</br> 
        <input type="text" name="search_id"></br>
        <input type="submit" value="Submit">
        </form>
        <p id="date"></p>
    </div>
</section>

<section>
    <div id="welcome_form">
    	<form id="dr_name" action="ServletOne">Please enter your name:</br>
    		<input type="text" name="doctor"></br>
    		<input type="submit" value="Submit">
    	</form>
    </div>
</section>

<script type="text/javascript">
function showStuff(text) {
    if (document.getElementById(text).style.display === 'block') {
        document.getElementById(text).style.display = 'none';
    }
    else {
        document.getElementById(text).style.display = 'block';
    }
}
</script>

<script>
document.getElementById("date").innerHTML = Date();
</script>

</body>
</html> 