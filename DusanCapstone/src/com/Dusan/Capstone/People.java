package com.Dusan.Capstone;

public class People {
	public int people_num = 0;
	public int moving = 0; // true는 위방향, false는 아래방향
	
	public boolean check = false;
	
	public synchronized void setPeople(boolean option, int num)
	{
		if(option == true)
		{
			people_num = (int) (Math.random()*6);
			//0~5명 사이로
			
			int tmp = (int) (Math.random() *2);
			if(tmp==0)
				moving = 1;
			else
				moving = -1;
			
			// 0이면 상승 1이면 하강
			
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
