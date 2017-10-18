package com.Dusan.Capstone;
	
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	Simulator208 my208 = new Simulator208();
	Simulator310 my310 = new Simulator310();
	
	// 208,310의 엘리베이터 모습을 담는다.
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			my310.setStage(primaryStage);
			
			my310.StartWindow();
			my208.StartWindow();
			
			
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		/*
		String str = URLEncoder.encode("한글","UTF-8");
		String url = "http://35.201.215.220/read_front.php";
		
		URL _url = new URL(url);
		
		HttpURLConnection conn = (HttpURLConnection) _url.openConnection();
		
		conn.setDoInput(true);
		conn.setDoOutput(false);
		
		conn.setUseCaches(false);
		
		conn.setReadTimeout(20000);
		
		conn.setRequestMethod("GET");
		
		
		StringBuffer sb = new StringBuffer();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		
		for(;;)
		{
			String line = br.readLine();
			
			if(line == null)
				break;
			
			sb.append(line + "\n");
		}
		
		
		br.close();
		
		conn.disconnect();
		
		String getXml = sb.toString();
		
		System.out.println(getXml);
		*/
		
		launch(args);
		
		
		
	}
}
