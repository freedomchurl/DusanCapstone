package com.Dusan.Capstone;

public class Building {

	public Elevator[] elevator = null;
	
	public String building_name = null;
	
	
	
	Building()
	{
		
	}
	
	Building(int num)
	{
		// num�� ������������ ����
		
		elevator = new Elevator[num]; // num������ŭ ���������� �迭�� �����Ѵ�.
		
		// ����ٰ� ������ �迭�� ���ο� ��ü�� �� �����ؾ��Ѵ�. 
	}
	
	Building(int num, String name)
	{
		elevator = new Elevator[num];
		
		this.building_name = new String(name); // �� ������ �̸��� �����Ѵ�.
		
		
	}
}
