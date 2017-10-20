package com.Dusan.Capstone;

public class Elevator {

	public int current_floor; // 현재 층을 알려준다.
	public int inner_people; // 현재 안에 타고 있는 사람을 알려준다. 
	
	public int max_people = 15; 
	public double max_weigth = 600;
	public double current_wiegth;
	
	
	public int low_floor; // 가장 낮은 층
	public int high_floor; // 가장 높은 층 
	
	public int moving; // 움직이고 있는지 아닌지, 1은 상승, -1은 하강, 0은 정지
	
	
	Elevator()
	{
		this.moving = 0;
		this.inner_people = 0;
		this.current_floor = 1;
		this.current_wiegth = 0;
	}
	
	Elevator(int low, int high)
	{
		this.moving = 0;
		this.current_floor = 1;
		this.inner_people = 0;
		this.low_floor = low;
		this.high_floor = high;	
		this.current_wiegth = 0;
	}
	
	
	// 만약에 사람이 없다면 엘리베이터는 움직임을 멈춰야한다. 이런 용도
	public boolean isEmpty()
	{
		if(this.inner_people ==0)
			return true;
		return false;
	}
	
	public void PrintPeople()
	{
		//System.out.println(this.inner_people + "명이 있습니다");
	}
	
	// 사람이 타고 내리는 부분도 넣어야한다. 
	// 그리고 엘리베이터가 움직이는데 걸리는 시간도 시뮬레이터에 고려해야한다.
	
	
	
}
