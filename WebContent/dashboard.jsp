<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Prototype 2</title>

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

button.popup_1_open, button.popup_2_open, button.popup_3_open, button.popup_4_open,
button.popup_5_open, button.popup_6_open, button.popup_7_open, button.popup_8_open {
    margin: 35px;
    padding: 20px 35px 70px 35px;
    border: 1px solid black;
    height: auto;
    width: auto;
    float: left;
    text-align: center;
    left: 5%;
    position: relative;
}	

button.popup_1_open {
    background-color: white;
}

button.popup_2_open {
    background-color: green;
}

button.popup_3_open {
    background-color: yellow;
}

button.popup_4_open {
    background-color: yellow;
}

button.popup_5_open {
    background-color: red;
}

button.popup_6_open {
    background-color: orange;
}

button.popup_7_open {
    background-color: yellow;
}

button.popup_8_open {
    background-color: orange;
}


button.popup_1_open:hover, button.popup_2_open:hover, button.popup_3_open:hover, button.popup_4_open:hover,
button.popup_5_open:hover, button.popup_6_open:hover, button.popup_7_open:hover, button.popup_8_open:hover {
    border: 1px solid black;
    background-color: white;
}

div.desc {
  text-align: center;
  font-weight: normal;
  width: 120px;
  margin: 5px;
  padding: 10px;
  max-height: 30px;
}

h2 {
	margin: 0px;
	padding: 0px;
}

div#popup_1, div#popup_2, div#popup_3, div#popup_4, div#popup_5, div#popup_6,
div#popup_7, div#popup_8 {
    border: 2px solid black;
    background-color: white;
    margin: 20px;
    padding: 20px;
}

div#error {
	position: absolute;
	float: left;
	left: 46%;
	top: 100px;
	color: red;
	margin: 0px;
	padding: 0px;
	font-weight: bold;
}



</style> 

<script src="http://code.jquery.com/jquery-1.8.2.min.js"></script>

<script src="http://vast-engineering.github.io/jquery-popup-overlay/jquery.popupoverlay.js"></script>



<script type="text/javascript"
          src="https://www.google.com/jsapi?autoload={
            'modules':[{
              'name':'visualization',
              'version':'1',
              'packages':['corechart', 'bar', 'line']
            }]
          }"></script>
          
<script type="text/javascript">
var obs_data = JSON.parse('${data_obs_json}');

</script>

<script>
function bmi_calc(height, weight) {
	return weight / (height/100);
}

function bmi_status(bmi) {
	var status = "";
	if (bmi < 25) {
		status = "Normal";
		document.getElementById("bmi_square").style.background = "green";
		return status;
	}
	if (bmi >= 25 && bmi < 30) {
		status = "Overweight";
		document.getElementById("bmi_square").style.background = "yellow";
		return status;
	}
	if (bmi >= 30) {
		status = "Obese";
		document.getElementById("bmi_square").style.background = "red";
		return status;
	}
}

</script>



</head>
<body>

<header>
    <div id="logo">
        <p id="name">PACE</p>
    </div>
    <div id="welcome">
        <p id="wel">Performance Dashboard: </br>
        WELCOME ${doctors}</p>
    </div>
</header>

<section>
    <div id="main">
        <a class="box" href="#" onclick="window.print();">Print</a>
        <a class="box" href="#" onclick="showStuff('text1');">Search</a> 
        <a class="box" href="/Pace-3/index.jsp" >Home</a> 
        <form id="text1" action="ServletSearchId">Search by Patient ID:</br> 
        <input type="text" name="search_id"></br>
        <input type="hidden" name="doctor" value="${doctors}">
        <input type="submit" value="Submit">
        </form>
        <p id="date"></p>
    </div>
</section>

<div id="error">
	${error}
</div>

<section>
    <button class="popup_1_open">
        <h2>${name}</h2>
        <div class="desc">Add a description here</div>
    </button>
    <button class="popup_2_open" id="bmi_square">
        <h2>BMI Exam</h2>
        <div class="desc">
	        <div id="weight">Weight: </div>
	        <div id="height">Height: </div>
	        <div id="bmi_stat">BMI Status: </div>
	        <div id="bmi">BMI: </div>
        </div>
    </button>
    <button class="popup_3_open" id="vitals_square">
        <h2>Vitals</h2>
        <div class="desc">
        <div id="vital_weight">Body Weight: </div>
        <div id="vital_height">Body Height: </div>
        <div id="vital_bp">Blood Pressure: </div>
        <div id="vital_temp">Body Temperature: </div>
        <div id="vital_pulse">Pulse: </div>
        </div>
    </button>
    <button class="popup_4_open">
        <h2>Heading 4</h2>
        <div class="desc">Add a description here</div>
    </button>
</section>

<section>
    <button class="popup_5_open">
        <h2>Heading 5</h2>
        <div class="desc">Add a description here</div>
    </button>
    <button class="popup_6_open">
        <h2>Heading 6</h2>
        <div class="desc">Add a description here</div>
    </button>
    <button class="popup_7_open">
        <h2>Heading 7</h2>
        <div class="desc">Add a description here</div>
    </button>
    <button class="popup_8_open">
        <h2>Heading 8</h2>
        <div class="desc">Add a description here</div>
    </button>
</section>


<div id="popup_1">
    <p>Popup 1 stuff here</p>
    <button class="popup_1_close">Close</button>
</div>
<div id="popup_2">
    <p>Popup 2 stuff here</p>
    <button class="popup_2_close">Close</button>
</div>
<div id="popup_3">
    
</div>
<div id="popup_4">
    <p>Popup 4 stuff here</p>
    <button class="popup_4_close">Close</button>
</div>
<div id="popup_5">
    <p>Popup 5 stuff here</p>
    <button class="popup_5_close">Close</button>
</div>
<div id="popup_6">
    <p>Popup 6 stuff here</p>
    <button class="popup_6_close">Close</button>
</div>
<div id="popup_7">
    <p>Popup 7 stuff here</p>
    <button class="popup_7_close">Close</button>
</div>
<div id="popup_8">
    <p>Popup 8 stuff here</p>
    <button class="popup_8_close">Close</button>
</div>

<div id="wdiv"></div>
<div id="hdiv"></div>
<div id="bpdiv"></div>
<div id="tempdiv"></div>
<div id="pulsediv"></div>


<script>
    $(document).ready(function() {
      $('#popup_1').popup();
    });

    $(document).ready(function() {
      $('#popup_2').popup();
    });
    
    $(document).ready(function() {
      $('#popup_3').popup();
    });

    $(document).ready(function() {
      $('#popup_4').popup();
    });
    
    $(document).ready(function() {
      $('#popup_5').popup();
    });

    $(document).ready(function() {
      $('#popup_6').popup();
    });
    
    $(document).ready(function() {
      $('#popup_7').popup();
    });

    $(document).ready(function() {
      $('#popup_8').popup();
    });
</script>

<script>
document.getElementById("date").innerHTML = Date();
</script>

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


<script type="text/javascript">
  google.setOnLoadCallback(drawChart);

  function drawChart() {
	  
	  var arr = [ ['Visit', 'Weight'] ];
	  
	  
	  var i;
      for (i = 0; i < obs_data.weight.length; i++) {
      	var temp = [i.toString(), parseInt(obs_data.weight[i].value)];
      	arr.push(temp);
      }
	  
    var data = google.visualization.arrayToDataTable(arr);

    var options = {
      title: 'Weight', 
      hAxis: {title: 'Visit'},
      vAxis: {title: 'kg'},
      width: 900,
      height: 500
    };

    var chart = new google.visualization.LineChart(document.getElementById('wdiv'));

    chart.draw(data, options);
  }
</script>

<script type="text/javascript">
  google.setOnLoadCallback(drawChart);

  function drawChart() {
	  
	  var arr = [ ['Visit', 'Height'] ];
	  
	  
	  var i;
      for (i = 0; i < obs_data.height.length; i++) {
      	var temp = [i.toString(), parseInt(obs_data.height[i].value)];
      	arr.push(temp);
      }
	  
    var data = google.visualization.arrayToDataTable(arr);

    var options = {
      title: 'Height',
      hAxis: {title: 'Visit'},
      vAxis: {title: 'cm'},
      width: 900,
      height: 500
    };

    var chart = new google.visualization.LineChart(document.getElementById('hdiv'));

    chart.draw(data, options);
  }
</script>

<script>
google.setOnLoadCallback(drawChart);
function drawChart() {
	
	var arr = [ ['Visit', 'Systolic BP', 'Diastolic BP'] ];
	
	var i;
    for (i = 0; i < obs_data.systolic_bp.length; i++) {
    	var temp = [i.toString(), parseInt(obs_data.systolic_bp[i].value), parseInt(obs_data.diastolic_bp[i].value)];
    	arr.push(temp);
    }
	
  var data = google.visualization.arrayToDataTable(arr);

  var options = {
    title: 'Blood Pressure',
    hAxis: {title: 'Visit'},
    vAxis: {title: 'mmHg'},
    width: 900,
    height: 500
  };

  var chart = new google.charts.Bar(document.getElementById('bpdiv'));

  chart.draw(data, options);
}
</script>

<script type="text/javascript">
  google.setOnLoadCallback(drawChart);

  function drawChart() {
	  
	  var arr = [ ['Visit', 'Body Temperature'] ];
	  
	  
	  var i;
      for (i = 0; i < obs_data.body_temperature.length; i++) {
      	var temp = [i.toString(), parseInt(obs_data.body_temperature[i].value)];
      	arr.push(temp);
      }
	  
    var data = google.visualization.arrayToDataTable(arr);

    var options = {
      title: 'Body Temperature',
      hAxis: {title: 'Visit'},
      vAxis: {title: 'C'},
      width: 900,
      height: 500
    };

    var chart = new google.visualization.LineChart(document.getElementById('tempdiv'));

    chart.draw(data, options);
  }
</script>

<script type="text/javascript">
  google.setOnLoadCallback(drawChart);

  function drawChart() {
	  
	  var arr = [ ['Visit', 'Heart Beat'] ];
	  
	  
	  var i;
      for (i = 0; i < obs_data.heart_beat.length; i++) {
      	var temp = [i.toString(), parseInt(obs_data.heart_beat[i].value)];
      	arr.push(temp);
      }
	  
    var data = google.visualization.arrayToDataTable(arr);

    var options = {
      title: 'Heart Beat',
      hAxis: {title: 'Visit'},
      vAxis: {title: 'bpm'},
      width: 900,
      height: 500
    };

    var chart = new google.visualization.LineChart(document.getElementById('tempdiv'));

    chart.draw(data, options);
  }
</script>

<script type="text/javascript">
  google.setOnLoadCallback(drawChart);

  function drawChart() {
	  
	  var arr = [ ['Visit', 'BMI'] ];
	  
	  
	  var i;
      for (i = 0; i < obs_data.weight.length; i++) {
      	var temp = [i.toString(), Math.round(bmi_calc(obs_data.height[i].value, obs_data.weight[i].value))];
      	arr.push(temp);
      }
	  
    var data = google.visualization.arrayToDataTable(arr);

    var options = {
      title: 'BMI', 
      hAxis: {title: 'Visit'},
      vAxis: {title: 'BMI'},
      width: 900,
      height: 500
    };

    var chart = new google.visualization.LineChart(document.getElementById('popup_2'));

    chart.draw(data, options);
  }
</script>




<script>

document.getElementById("popup_3").appendChild(document.getElementById("wdiv"));
document.getElementById("popup_3").appendChild(document.getElementById("hdiv"));
document.getElementById("popup_3").appendChild(document.getElementById("bpdiv"));
document.getElementById("popup_3").appendChild(document.getElementById("tempdiv"));
document.getElementById("popup_3").appendChild(document.getElementById("pulsediv"));

document.getElementById("weight").innerHTML = "Weight: " + obs_data.weight[obs_data.weight.length-1].value + "kg";
document.getElementById("height").innerHTML = "Height: " + obs_data.height[obs_data.height.length-1].value + "cm";
document.getElementById("bmi").innerHTML = "BMI: " + Math.round(bmi_calc(obs_data.height[obs_data.height.length-1].value, obs_data.weight[obs_data.weight.length-1].value));
document.getElementById("bmi_stat").innerHTML = "BMI Status: " + bmi_status(Math.round(bmi_calc(obs_data.height[obs_data.weight.length-1].value, obs_data.weight[obs_data.weight.length-1].value)));


document.getElementById("vital_weight").innerHTML = "Weight: " + obs_data.weight[obs_data.weight.length-1].value + "kg";
document.getElementById("vital_height").innerHTML = "Height: " + obs_data.height[obs_data.height.length-1].value + "cm";
document.getElementById("vital_bp").innerHTML = "Blood Pressure: " + obs_data.systolic_bp[obs_data.systolic_bp.length-1].value + " / " + obs_data.diastolic_bp[obs_data.diastolic_bp.length-1].value;
document.getElementById("vital_temp").innerHTML = "Body Temperature: " + obs_data.body_temperature[obs_data.body_temperature.length-1].value + "C";
document.getElementById("vital_pulse").innerHTML = "Pulse: " + obs_data.heart_beat[obs_data.heart_beat.length-1].value + "bpm";


</script>

</body>
</html> 