package com.Dusan.Capstone;

public class Elevator {

	public int current_floor; // ���� ���� �˷��ش�.
	public int inner_people; // ���� �ȿ� Ÿ�� �ִ� ����� �˷��ش�. 
	public int low_floor; // ���� ���� ��
	public int high_floor; // ���� ���� �� 
	
	public int moving; // �����̰� �ִ��� �ƴ���, 1�� ���, -1�� �ϰ�, 0�� ����
	
	
	Elevator()
	{
		this.moving = 0;
		this.inner_people = 0;
		this.current_floor = 0;
	}
	
	Elevator(int low, int high)
	{
		this.moving = 0;
		this.current_floor = 0;
		this.inner_people = 0;
		this.low_floor = low;
		this.high_floor = high;	
	}
	
	
	// ���࿡ ����� ���ٸ� ���������ʹ� �������� ������Ѵ�. �̷� �뵵
	public boolean isEmpty()
	{
		if(this.inner_people ==0)
			return true;
		return false;
	}
	
	public void PrintPeople()
	{
		System.out.println(this.inner_people + "���� �ֽ��ϴ�");
	}
	
	// ����� Ÿ�� ������ �κе� �־���Ѵ�. 
	// �׸��� ���������Ͱ� �����̴µ� �ɸ��� �ð��� �ùķ����Ϳ� ����ؾ��Ѵ�.
	
	
	
}
