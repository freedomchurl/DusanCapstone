package com.Dusan.Capstone;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class People {
	public int people_num = 0;
	public int moving = 0; // true�� ������, false�� �Ʒ�����
	public int floor = 0;
	
	public boolean check = false;
	
	public synchronized void setPeople(boolean option, int num)
	{
		
		if(option == true)
		{
			people_num = (int) (Math.random()*11);
			//0~5�� ���̷�
			
			int tmp = (int) (Math.random() *2);
			if(tmp==0)
				moving = 1;
			else
				moving = -1;
			
			// 0�̸� ��� 1�̸� �ϰ�
			//System.out.println("dddddddd");
			//System.out.println("�� people�� ����, " + this.floor);
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
					// ���⼭�� ��Ʈ��ũ ������� �����;� �Ѵ�.
					String str = URLEncoder.encode("�ѱ�","UTF-8");
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
				check = true; // �ߺ� ������ ���� ����, check�� true�� ��� ���̻� ���� X
			else
			{
				moving = 0;
			}
			// �׸��� �������� check�� false�� �ٲ���Ѵ�.
			
			/*
			 * ����� �־ ��� �߰��� �� �ֵ��� �ұ�? �� ������ ������� �ʵ���.
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
