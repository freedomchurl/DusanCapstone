package com.Dusan.Capstone;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class People {
	public int people_num = 0;
	public int moving = 0; // true는 위방향, false는 아래방향
	public int floor = 0;
	
	public boolean check = false;
	
	public synchronized void setPeople(boolean option, int num)
	{
		
		if(option == true)
		{
			people_num = (int) (Math.random()*11);
			//0~5명 사이로
			
			int tmp = (int) (Math.random() *2);
			if(tmp==0)
				moving = 1;
			else
				moving = -1;
			
			// 0이면 상승 1이면 하강
			//System.out.println("dddddddd");
			//System.out.println("이 people의 층은, " + this.floor);
			if(this.floor == 1)
			{
				/*
				System.out.println("dddddddd");
				
				Thread myThread = new Thread(){
					
					public void run()
					{
						System.out.println("dddddddd");
					try
					{
					// 여기서는 네트워크 통신으로 가져와야 한다.
					String str = URLEncoder.encode("한글","UTF-8");
					String url = "http://35.201.215.220/read_left_front.php";
					
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
					
					}catch(Exception e){}
					}
				};
				myThread.start();
				*/
				
				moving = 1;
			}
			
			if(this.floor ==6)
				moving = -1;
			
			
			if(people_num!=0)
				check = true; // 중복 생성을 막기 위해, check가 true일 경우 더이상 생성 X
			else
			{
				moving = 0;
			}
			// 그리고 가져갈때 check를 false로 바꿔야한다.
			
			/*
			 * 사람이 있어도 계속 추가될 수 있도록 할까? 단 방향은 변경되지 않도록.
			 */
		}
		else
		{
			people_num = num;
			
			if(people_num == 0)
			{
				check = false;
				moving = 0;
			}
			
		}
	}
}
