package sdut.ligong.demo;

import java.util.Calendar;
import java.util.Date;

public class User {

	static int count = 1;

	public String id; // �û�Id

	public String userName; // �û�����

	public String password; // �û�����

	private double money; // �û����

	public String CreateAccountData; // �����˻�����
	
	

	public static int getCount() {
		return count;
	}

	public static void setCount(int count) {
		User.count = count;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public String getCreateAccountData() {
		return CreateAccountData;
	}

	public void setCreateAccountData(String createAccountData) {
		CreateAccountData = createAccountData;
	}

	public User() throws Exception { // �޲ι��췽��

		// ��ʼ����������
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		//System.out.println("����ʱ���ǣ�" + new Date());
		String year = String.valueOf(calendar.get(Calendar.YEAR));
		String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
		String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
		String week = String
				.valueOf(7 - (calendar.get(Calendar.DAY_OF_WEEK) - 1));
		CreateAccountData = year + "��" + month + "��" + day + "�գ�����" + week;
		// ��ʼ�����
		String num = FileReadAndFileWrite.readCount();
		if(num==null){
			num="0";
		}
		this.id = num;
		
		// ��ʼ���û����
		this.money = 0.0;
		// ��ʼ���û�����
		password = "123123";
	}

	public User(String userName) throws Exception {
		//��ʼ���û��� 
		this.userName = userName;
		// ��ʼ����������
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		//System.out.println("����ʱ���ǣ�" + new Date());
		String year = String.valueOf(calendar.get(Calendar.YEAR));
		String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
		String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
		String week = String
				.valueOf((calendar.get(Calendar.DAY_OF_WEEK) - 1));
		CreateAccountData = year + "��" + month + "��" + day + "�գ�����" + week;
		// ��ʼ�����
		String num = FileReadAndFileWrite.readCount();
		if(num.equals(null)){
			num="0";
		}
	    this.id = num;
		// ��ʼ���û����
		this.money = 0.0;
		// ��ʼ���û�����
		password = "123123";
	}

}
