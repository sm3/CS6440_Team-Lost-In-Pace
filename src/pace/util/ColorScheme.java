package pace.util;

import java.util.Random;

public class ColorScheme {

  private String color;
  private Integer baseline;
  String[] obs_array =  new String[] {"BNP", "MRI", "CKMB", "ECG", "Chest X-Ray",
			"CT Chest", "LDL", "HDL", "HbA1c", "Protein Urine", "Sodium Urine",
			"FDG PET CT Scan", "TSH" };

  public Integer getBaseline() {
	return baseline;
}

  public void setBaseline(Integer baseline) {
	this.baseline = baseline;
}

  public void setColor(String color) {
	this.color = color;
}

  public int getColorValue(String test, String value) {
	  Float i_value = Float.parseFloat(value);
	  int res = 1;
	  
	  if (test.equals(obs_array[0])) {
		  if (i_value <= 100) {
			  res = 1;
			  return res;
		  }
		  if (i_value <= 300 && i_value > 101) {
			  res = 2;
			  return res;
		  }
		  if (i_value <= 600 && i_value > 301) {
			  res = 3;
			  return res;
		  }
		  if (i_value <= 900 && i_value > 601) {
			  res = 4;
			  return res;
		  }
		  if (i_value > 901) {
			  res = 5;
			  return res;
		  }
	  }
	  
	  if (test.equals(obs_array[1]) || test.equals(obs_array[3]) || test.equals(obs_array[4]) || test.equals(obs_array[5]) || test.equals(obs_array[11])) {
		  Random rand = new Random();
		  res = rand.nextInt((5 - 1) + 1) + 1;
		  return res;
	  }
	  
	  if (test.equals(obs_array[2])) {
		  if (i_value <= 1) {
			  res = 1;
			  return res;
		  }
		  if (i_value == 2) {
			  res = 2;
			  return res;
		  }
		  if (i_value == 3) {
			  res = 3;
			  return res;
		  }
		  if (i_value == 4) {
			  res = 4;
			  return res;
		  }
		  if (i_value > 4) {
			  res = 5;
			  return res;
		  }
	  }
	  
	  if (test.equals(obs_array[6])) {
		  if (i_value <= 90 && i_value >= 70) {
			  res = 1;
			  return res;
		  }
		  if (i_value < 70 && i_value >= 60 || i_value <= 100 && i_value > 90) {
			  res = 2;
			  return res;
		  }
		  if (i_value < 60 && i_value >= 50 || i_value <= 110 && i_value > 100) {
			  res = 3;
			  return res;
		  }
		  if (i_value < 50 && i_value >= 40 || i_value <= 120 && i_value > 110) {
			  res = 4;
			  return res;
		  }
		  if (i_value > 120 || i_value < 40) {
			  res = 5;
			  return res;
		  }
	  }
	  
	  if (test.equals(obs_array[7])) {
		  if (i_value > 60) {
			  res = 1;
			  return res;
		  }
		  if (i_value == 60 || i_value == 61) {
			  res = 2;
			  return res;
		  }
		  if (i_value < 60 && i_value >= 50) {
			  res = 3;
			  return res;
		  }
		  if (i_value < 50 && i_value >= 40) {
			  res = 4;
			  return res;
		  }
		  if (i_value < 40) {
			  res = 5;
			  return res;
		  }
	  }
	  
	  if (test.equals(obs_array[8])) {
		  if (i_value < 5) {
			  res = 1;
			  return res;
		  }
		  if (i_value == 5) {
			  res = 2;
			  return res;
		  }
		  if (i_value == 6) {
			  res = 3;
			  return res;
		  }
		  if (i_value >= 7 && i_value <= 9) {
			  res = 4;
			  return res;
		  }
		  if (i_value > 9) {
			  res = 5;
			  return res;
		  }
	  }
	  
	  if (test.equals(obs_array[9])) {
		  if (i_value <= 5) {
			  res = 1;
			  return res;
		  }
		  if (i_value == 6 || i_value == 7) {
			  res = 2;
			  return res;
		  }
		  if (i_value == 8) {
			  res = 3;
			  return res;
		  }
		  if (i_value == 9 || i_value == 10) {
			  res = 4;
			  return res;
		  }
		  if (i_value > 10) {
			  res = 5;
			  return res;
		  }
	  }
	  /*
	  if (test.equals(obs_array[10])) {
		  if (i_value < 120) {
			  res = 1;
			  return res;
		  }
		  if (i_value < 130 && i_value <= 120) {
			  res = 2;
			  return res;
		  }
		  if (i_value < 140 && i_value <= 130) {
			  res = 3;
			  return res;
		  }
		  if (i_value == 140) {
			  res = 4;
			  return res;
		  }
		  if (i_value > 140) {
			  res = 5;
			  return res;
		  }
	  }
	  */
	  if (test.equals(obs_array[10])) {
		  if (i_value > 20) {
			  res = 1;
			  return res;
		  }
		  if (i_value == 20) {
			  res = 2;
			  return res;
		  }
		  if (i_value == 19) {
			  res = 3;
			  return res;
		  }
		  if (i_value == 17 || i_value == 18) {
			  res = 4;
			  return res;
		  }
		  if (i_value < 17) {
			  res = 5;
			  return res;
		  }
	  }
	  
	  if (test.equals(obs_array[12])) {
		  if (i_value <= 1.3 && i_value >= 1.8) {
			  res = 1;
			  return res;
		  }
		  if (i_value < 1.3 && i_value >= 1.0 || i_value <= 2.0 && i_value > 1.8) {
			  res = 2;
			  return res;
		  }
		  if (i_value < 1.0 && i_value >= 0.8 || i_value <= 3.0 && i_value > 2.0) {
			  res = 3;
			  return res;
		  }
		  if (i_value < 0.8 && i_value >= 0.6 || i_value <= 5.0 && i_value > 3.0) {
			  res = 4;
			  return res;
		  }
		  if (i_value > 5.0 || i_value < 0.6) {
			  res = 5;
			  return res;
		  }
	  }
	  
	  
	  return res;
  }
  
  public String colorValueToName(int value) {
	  String res = "";
	  if (value == 1) {
		  res = "green";
		  return res;
	  }
	  if (value == 2) {
		  res = "lightgreen";
		  return res;
	  }
	  if (value == 3) {
		  res = "yellow";
		  return res;
	  }
	  if (value == 4) {
		  res = "orange";
		  return res;
	  }
	  if (value == 5) {
		  res = "red";
		  return res;
	  }
	  
	    
	  return res;
  }


}