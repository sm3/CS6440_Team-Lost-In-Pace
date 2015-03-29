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
    float:left;
    left: 20%;
    position: relative;
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
    padding: 35px;
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
}

div#popup_1, div#popup_2, div#popup_3, div#popup_4, div#popup_5, div#popup_6,
div#popup_7, div#popup_8 {
    border: 2px solid black;
    background-color: white;
    margin: 20px;
    padding: 20px;
}

</style> 

</head>
<body>

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
        <form id="text1">Enter Search Criteria:</br> <input type="text"></br><input type="submit" value="Submit">
        </form>
        <p id="date"></p>
    </div>
</section>

<section>
    <button class="popup_1_open">
        <h2>${name}</h2>
        <div class="desc">Add a description here</div>
    </button>
    <button class="popup_2_open">
        <h2>Heading 2</h2>
        <div class="desc">Add a description here</div>
    </button>
    <button class="popup_3_open">
        <h2>Heading 3</h2>
        <div class="desc">Add a description here</div>
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
    <p>Popup 3 stuff here</p>
    <button class="popup_3_close">Close</button>
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



<script src="http://code.jquery.com/jquery-1.8.2.min.js"></script>

<script src="http://vast-engineering.github.io/jquery-popup-overlay/jquery.popupoverlay.js"></script>

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

</body>
</html> 