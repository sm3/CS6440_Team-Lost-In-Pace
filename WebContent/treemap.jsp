<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Prototype 1</title>

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

div#chart_div {
    position: absolute;
    top: 160px;
    left: 22%;
}

</style> 

</head>
<body>
<div id="change"></div>
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

<section>
    <div id="chart_div" style="width: 900px; height: 500px;"></div>
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

<script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">
      google.load("visualization", "1", {packages:["treemap"]});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
        var arr = [
                   ['id',                                     'Parent',                     'Patient (size)', 'Urgency (color)'],
                   ['Chronic Disease',                         null,                                   0,                     0],
                   ['Hypertension',                           'Chronic Disease',                       0,                     0],
                   ['Diabetes',                               'Chronic Disease',                       0,                     0],
                   ['Chronic Obstructive Pulmonary Disease',  'Chronic Disease',                       0,                     0],
                   ['Chronic Congestive Heart Failure',       'Chronic Disease',                       0,                     0],
                   ['BNP',                                    'Chronic Congestive Heart Failure',      0,                     0],
                   ['MRI',                                    'Chronic Congestive Heart Failure',      0,                     0],
                   ['CKMB',                                   'Chronic Congestive Heart Failure',      0,                     0],
                   ['ECG',                                    'Chronic Obstructive Pulmonary Disease', 0,                     0],
                   ['Chest X-Ray',                            'Chronic Obstructive Pulmonary Disease', 0,                     0],
                   ['CT Chest',                               'Chronic Obstructive Pulmonary Disease', 0,                     0],
                   ['LDL',                                    'Diabetes',                              0,                     0],
                   ['HDL',                                    'Diabetes',                              0,                     0],
                   ['HbA1c',                                  'Diabetes',                              0,                     0],
                   ['Protein Urine',                          'Diabetes',                              0,                     0],
                   ['Blood Pressure',                         'Hypertension',                          0,                     0],
                   ['Sodium Urine',                           'Hypertension',                          0,                     0],
                   ['FDG PET CT Scan',                        'Hypertension',                          0,                     0],
                   ['TSH',                                    'Hypertension',                          0,                     0]
                 ];
        
        var patients = JSON.parse('${json}');
                
        var patient_ids = JSON.parse('${pat_ids}');
        
        var tests = JSON.parse('${json_tests}');
        
        var colors = JSON.parse('${json_colors}');
        
        var test_arr = ['BNP','MRI','CKMB','ECG','Chest X-Ray','CT Chest','LDL','HDL','HbA1c','Protein Urine','Sodium Urine','FDG PET CT Scan','TSH'];
        
        var i;
        for (i = 0; i < colors.Colors.length; i++) {
        	var temp = [patients.Patients[i].patientname, tests.Tests[i].value, 1, parseInt(colors.Colors[i].value)];
        	arr.push(temp);
        }
        
        console.log(arr);
        
        var data = google.visualization.arrayToDataTable(arr);

        var tree = new google.visualization.TreeMap(document.getElementById('chart_div'));
        
        var options = {
          minColor: 'green',
          midColor: 'yellow',
          maxColor: 'red',
          headerHeight: 15,
          fontColor: 'black',
          generateTooltip: showFullTooltip,
          minColorValue: 1,
          maxColorValue: 5,
          showScale: true
        };

        tree.draw(data, options);
        
        function showFullTooltip(row, size, value) {
            if ( (data.getValue(row, 0) === 'Diabetes') || (data.getValue(row, 0) === 'Hypertension') ||
                 (data.getValue(row, 0) === 'Chronic Obstructive Pulmonary Disease') || 
                 (data.getValue(row, 0) === 'Chronic Congestive Heart Failure') ||
                 (data.getValue(row, 0) === 'Chronic Disease') ) {
                return null;
            }
            else if ( test_arr.indexOf(data.getValue(row, 0)) > -1 ){
            	return '<div style="background:#fd9; padding:10px; border-style:solid">' +
                '<span style="font-family:Courier"><b>Test: ' + data.getValue(row, 0) +
                '<br>Drill down to patient(s)' + 
                '</b></span><br>';
            }
            else {
                return '<div style="background:#fd9; padding:10px; border-style:solid">' +
                       '<a href="/Pace-3/ServletDash?doctor=${doctors}' +
                       '&pat_id=' + patient_ids[data.getValue(row, 0)] +
                       '&test=' + data.getValue(row, 1) + 
                       '&color=' + data.getValue(row, 3) +
                       '"><span style="font-family:Courier"><b>Go to Patient Dashboard: ' + 
                       data.getValue(row, 0) +
                       '</b></span></a><br>';
            }
        }

      }
</script>

</body>
</html> 