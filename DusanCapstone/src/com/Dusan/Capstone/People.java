package com.Dusan.Capstone;

public class People {
	public int people_num = 0;
	public int moving = 0; // true�� ������, false�� �Ʒ�����
	
	public boolean check = false;
	
	public synchronized void setPeople(boolean option, int num)
	{
		if(option == true)
		{
			people_num = (int) (Math.random()*6);
			//0~5�� ���̷�
			
			int tmp = (int) (Math.random() *2);
			if(tmp==0)
				moving = 1;
			else
				moving = -1;
			
			// 0�̸� ��� 1�̸� �ϰ�
			
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
