package sdut.ligong.demo;

import java.util.Calendar;
import java.util.Date;

public class User {

	static int count = 1;

	public String id; // 用户Id

	public String userName; // 用户姓名

	public String password; // 用户密码

	private double money; // 用户余额

	public String CreateAccountData; // 创建账户日期
	
	

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

	public User() throws Exception { // 无参构造方法

		// 初始化创建日期
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		//System.out.println("现在时间是：" + new Date());
		String year = String.valueOf(calendar.get(Calendar.YEAR));
		String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
		String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
		String week = String
				.valueOf(7 - (calendar.get(Calendar.DAY_OF_WEEK) - 1));
		CreateAccountData = year + "年" + month + "月" + day + "日，星期" + week;
		// 初始化编号
		String num = FileReadAndFileWrite.readCount();
		if(num==null){
			num="0";
		}
		this.id = num;
		
		// 初始化用户余额
		this.money = 0.0;
		// 初始化用户密码
		password = "123123";
	}

	public User(String userName) throws Exception {
		//初始化用户名 
		this.userName = userName;
		// 初始化创建日期
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		//System.out.println("现在时间是：" + new Date());
		String year = String.valueOf(calendar.get(Calendar.YEAR));
		String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
		String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
		String week = String
				.valueOf((calendar.get(Calendar.DAY_OF_WEEK) - 1));
		CreateAccountData = year + "年" + month + "月" + day + "日，星期" + week;
		// 初始化编号
		String num = FileReadAndFileWrite.readCount();
		if(num.equals(null)){
			num="0";
		}
	    this.id = num;
		// 初始化用户余额
		this.money = 0.0;
		// 初始化用户密码
		password = "123123";
	}

}
