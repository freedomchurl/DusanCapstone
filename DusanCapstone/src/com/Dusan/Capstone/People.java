package com.Dusan.Capstone;

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
			
			if(this.floor == 1)
				moving = 1;
			
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
