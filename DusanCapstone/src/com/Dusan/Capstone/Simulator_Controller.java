package com.Dusan.Capstone;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

public class Simulator_Controller implements Initializable{

	public String up_arrow = "../../../../resource/arrow-up.png";
	public String down_arrow = "../../../../resource/arrow-down.png";
	
	
	BuildingData my_data = null;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	public void setData(BuildingData input)
	{
		this.my_data = input;
	}

}
