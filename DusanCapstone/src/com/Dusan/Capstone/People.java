package com.Dusan.Capstone;

public class People {
	public int people_num = 0;
	public boolean moving = true; // true�� ������, false�� �Ʒ�����
	
	public boolean check = false;
	
	public void setPeople(int max)
	{
		people_num = (int) (Math.random()*10);
		//0~9�� ���̷�
		
		int tmp = (int) (Math.random() *2);
		if(tmp==0)
			moving = true;
		else
			moving = false;
		
		// 0�̸� ��� 1�̸� �ϰ�
		
		if(people_num!=0)
			check = true; // �ߺ� ������ ���� ����, check�� true�� ��� ���̻� ���� X
		
		// �׸��� �������� check�� false�� �ٲ���Ѵ�.
	}
}
