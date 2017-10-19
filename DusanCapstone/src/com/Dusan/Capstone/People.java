package com.Dusan.Capstone;

public class People {
	public int people_num = 0;
	public boolean moving = true; // true는 위방향, false는 아래방향
	
	public boolean check = false;
	
	public void setPeople(int max)
	{
		people_num = (int) (Math.random()*10);
		//0~9명 사이로
		
		int tmp = (int) (Math.random() *2);
		if(tmp==0)
			moving = true;
		else
			moving = false;
		
		// 0이면 상승 1이면 하강
		
		if(people_num!=0)
			check = true; // 중복 생성을 막기 위해, check가 true일 경우 더이상 생성 X
		
		// 그리고 가져갈때 check를 false로 바꿔야한다.
	}
}
