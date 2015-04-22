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
    padding: 20px 35px 95px 35px;
    border: 1px solid black;
    height: auto;
    width: auto;
    float: left;
    text-align: center;
    left: 5%;
    position: relative;
}	

button.popup_1_open:hover, button.popup_2_open:hover, button.popup_3_open:hover, button.popup_4_open:hover,
button.popup_5_open:hover, button.popup_6_open:hover, button.popup_7_open:hover, button.popup_8_open:hover {
    border: 1px solid blue;
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

<div id="error">
	${error}
</div>

    <button class="popup_1_open" id="pat_square">
        <h2>${name}</h2>
        <div class="desc" id="pat_div">
        </div>
    </button>
    <button class="popup_2_open" id="bmi_square">
        <h2>BMI Exam</h2>
        <div class="desc" id="bmi_div">
        </div>
    </button>
    <button class="popup_3_open" id="vitals_square">
        <h2>Vitals</h2>
        <div class="desc" id="vitals_div">
        </div>
    </button>
    <button class="popup_4_open" id="med_square">
        <h2>Medications</h2>
        <div class="desc" id="med_div">
        </div>
    </button>

    <button class="popup_5_open" id="test1_square">
        <h2>${test1_name} Test</h2>
        <div class="desc">${test1_name} test results: ${test1_value}${test1_units}<br><br></div>
    </button>
    <button class="popup_6_open" id="test2_square">
        <h2>${test2_name} Test</h2>
        <div class="desc">${test2_name} test results: ${test2_value}${test2_units}<br><br></div>
    </button>
    <button class="popup_7_open" id="test3_square">
        <h2>${test3_name} Test</h2>
        <div class="desc">${test3_name} test results: ${test3_value}${test3_units}<br><br></div>
    </button>
    <button class="popup_8_open" id="test4_square">
        <h2>${test4_name} Test</h2>
        <div class="desc">${test4_name} test results: ${test4_value}${test4_units}<br><br></div>
    </button>


<div id="popup_2">
</div>
<div id="popup_3">
</div>
<div id="popup_4">
</div>


<div id="wdiv"></div>
<div id="hdiv"></div>
<div id="bpdiv"></div>
<div id="tempdiv"></div>
<div id="pulsediv"></div>

<script type="text/javascript">
var obs_data = JSON.parse('${data_obs_json}');
var meds_data = JSON.parse('${data_meds_json}');
</script>

<script>

    $(document).ready(function() {
      $('#popup_2').popup();
    });
    
    $(document).ready(function() {
      $('#popup_3').popup();
    });

    $(document).ready(function() {
      $('#popup_4').popup();
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
		document.getElementById("bmi_square").style.background = "orange";
		return status;
	}
	if (bmi >= 30) {
		status = "Obese";
		document.getElementById("bmi_square").style.background = "red";
		return status;
	}
}

function vitals_color(systolic_bp, diastolic_bp, body_temperature, heart_beat) {
	var score = 0;
	if (systolic_bp >= 90 && systolic_bp <= 120) {
		score += 1;
	}
	if (diastolic_bp >= 60 && diastolic_bp <= 80) {
		score += 1;
	}
	if (body_temperature > 97 && body_temperature < 99) {
		score += 1;
	}
	if (heart_beat >= 60 && heart_beat <= 1000) {
		score += 1;
	}
	
	if (score < 3) {
		document.getElementById("vitals_square").style.background = "red";
	}
	if (score === 3) {
		document.getElementById("vitals_square").style.background = "yellow";
	}
	if (score > 3) {
		document.getElementById("vitals_square").style.background = "green";
	}
	return 0;
}

function rand_date() {
	var month = Math.floor(Math.random() * (12 - 1 + 1)) + 1;
	var day = Math.floor(Math.random() * (29 - 1 + 1)) + 1;
	var year = Math.floor(Math.random() * (2000 - 1950 + 1)) + 1950;
	
	return month + "/" + day + "/" + year;
}

function rand_gender() {
	var ran_num = Math.floor(Math.random() * (1 - 0 + 1)) + 0;
	var gender = "";
	if (ran_num === 0) {
		gender = "Female";
	}
	if (ran_num === 1) {
		gender = "Male";
	}
	
	return gender;
}

function add_med_pop() {
	med_info = "";
	for (var i = 0; i < meds_data.medication.length; i++) {
		med_info += "<br><b>Medication Name</b>: " + meds_data.medication[i].value +
					"<br><b>Date Written</b>: " + meds_data.date_written[i].value +
					"<br><b>Prescriber</b>: " + meds_data.prescriber[i].value +
					"<br><b>Status</b>: " + meds_data.status[i].value;
	}
	document.getElementById("popup_4").innerHTML = med_info;
	return 0;
}

</script>


<script>

var last_weight = obs_data.weight[obs_data.weight.length-1].value;
var last_height = obs_data.height[obs_data.height.length-1].value;
var last_sysbp = obs_data.systolic_bp[obs_data.systolic_bp.length-1].value;
var last_diabp = obs_data.diastolic_bp[obs_data.diastolic_bp.length-1].value;
var last_temp = obs_data.body_temperature[obs_data.body_temperature.length-1].value;
var last_pulse = obs_data.heart_beat[obs_data.heart_beat.length-1].value;

document.getElementById("popup_3").appendChild(document.getElementById("wdiv"));
document.getElementById("popup_3").appendChild(document.getElementById("hdiv"));
document.getElementById("popup_3").appendChild(document.getElementById("bpdiv"));
document.getElementById("popup_3").appendChild(document.getElementById("tempdiv"));
document.getElementById("popup_3").appendChild(document.getElementById("pulsediv"));

document.getElementById("bmi_div").innerHTML = "<br>Weight: " + last_weight + "kg" +
												"<br>Height: " + last_height + "cm" +
												"<br>BMI: " + Math.round(bmi_calc(last_height, last_weight)) +
												"<br>BMI Status: " + bmi_status(Math.round(bmi_calc(last_height, last_weight)));


document.getElementById("vitals_div").innerHTML = "Weight: " + last_weight + "kg" +
													"<br>Height: " + last_height + "cm" +
													"<br>Blood Pressure: " + last_sysbp + " / " + last_diabp +
													"<br>Body Temperature: " + last_temp + "C" +
													"<br>Pulse: " + last_pulse + "bpm";

var x = vitals_color(last_sysbp, last_diabp, last_temp, last_pulse);

var gen = rand_gender();
var dob = rand_date();
document.getElementById("pat_div").innerHTML = "Gender: " + gen + 
											"<br>Date of Birth: " + dob;
											
document.getElementById("med_div").innerHTML = "Medication Name: " + meds_data.medication[0].value +
											"<br>Medication Name: " + meds_data.medication[1].value +
											"<br>Medication Name: " + meds_data.medication[2].value;
											
var add_meds = add_med_pop();

document.getElementById("test1_square").style.background = "${test1_color}";
document.getElementById("test2_square").style.background = "${test2_color}";
document.getElementById("test3_square").style.background = "${test3_color}";
document.getElementById("test4_square").style.background = "${test4_color}";


</script>

</body>
</html> 