package sdut.ligong.demo;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MainActivity {

	static Scanner input = new Scanner(System.in);
	
	static boolean mIsFirst = true;

	/**
	 * 主界面
	 * 
	 * @return
	 */
	private static void showMain() {
		if(mIsFirst){
			boolean going = verifyIdentidy();
			if(going){
				mIsFirst = false;
				System.out.println("欢迎进入银行管理系统!");
				System.out.println("1.创建账户");
				System.out.println("2.查询账户");
				System.out.println("3.修改密码");
				System.out.println("4.删除账户");
				System.out.println("5.存钱");
				System.out.println("6.取钱");
				System.out.println("7.转账");
				System.out.println("0.退出");
				System.out.println("请选择你要进行的操作");
			
			}else{
				// 登陆失败
				System.out.println("用户名或密码错误!请5秒后重试");
				for (int i = 0; i < 5; i++) {
					System.out.println("倒计时" + (5 - i));
					try { // 延迟一秒
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				mIsFirst = true;
				showMain();
				
			}
			
		}else{
			System.out.println("欢迎进入银行管理系统!");
			System.out.println("请选择你要进行的操作");
			System.out.println("1.创建账户");
			System.out.println("2.查询账户");
			System.out.println("3.修改用户信息");
			System.out.println("4.删除账户");
			System.out.println("5.存钱");
			System.out.println("6.取钱");
			System.out.println("7.转账");
			System.out.println("0.退出");
			
			
		}
		
			
			

	}

	

	/**
	 * 主程序入口
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		boolean mIsCircle = true;
		do{
			int choose; 
			showMain();
			choose = input.nextInt();
			 switch (choose) {
			 case 1://创建账户
			CreateAccount();
			 break;
			 case 2:  //查询账户
			 searchOrModifyAccountOrDelete("查询");
			 break;
			 case 3:  //修改账户
			searchOrModifyAccountOrDelete("修改");
			 break;
			 case 4:  //删除账户
			searchOrModifyAccountOrDelete("删除");
			 break;
			 case 5:  //存钱
			saveMoney();
			 break;
			 case 6: //取钱
			takeMoney();
			 break;
			 case 7: //转钱
			transferMoney();
			 break;
			 case 0:
				 mIsCircle = false;
			 break;
			 default:
				 mIsCircle = false;
			 break;
			 }
		}while(mIsCircle);
		 

	}


	private static void transferMoney() throws Exception {
		// TODO Auto-generated method stub
		saveAndtakeMoney("转出");
	}

	private static void takeMoney() throws Exception {
		saveAndtakeMoney("取钱");
	}

	private static void saveMoney() throws Exception {
		saveAndtakeMoney("存钱");
	}
	
	public static void saveAndtakeMoney(String key) throws Exception{
		System.out.println("请选择你要"+key+"的方式");
		System.out.println("1.按照用户编号进行"+key);
		System.out.println("2.按照用户名进行"+key);
		int choose = input.nextInt();
		if(choose==1){
			System.out.println("请输入你要"+key+"的用户编号");
			choose = input.nextInt();
			if(key.equals("取钱")){
				FileReadAndFileWrite.takeNumUserOfMoney(choose);
			}else if(key.equals("存钱")){
				FileReadAndFileWrite.saveNumUserOfMoney(choose);
			}else{
				FileReadAndFileWrite.transferNumUserOfMoney(choose);
			}
			
		}else if(choose==2){
			System.out.println("请输入你要"+key+"的用户名");
			String userName = input.next();
			if(key.equals("取钱")){
				FileReadAndFileWrite.takeNameUserOfMoney(userName);
			}else if(key.equals("存钱")){
				FileReadAndFileWrite.saveNameUserOfMoney(userName);
			}else{
				FileReadAndFileWrite.transferNameUserOfMoney(userName);
			}
			
		}
	}

	// 验证身份
	/**
	 * 判断管理员身份
	 * 
	 * @return
	 */
	public static boolean verifyIdentidy() {
		System.out.println("请输入管理员账户");
		String userName = input.next();
		if (userName.equals("admin")) {
			System.out.println("请继续输入管理员密码");
			String password = input.next();
			if (password.equals("123456")) {
				return true;
			}
		}
		return false;
	}

	// 创建新账户
	/**
	 * @return false 代表创建账户失败
	 * @throws Exception
	 */
	private static boolean CreateAccount() throws Exception {

		boolean mIsSucceed = false; // 判断创建账户是否成功 true-成功 false-失败

		System.out.println("你输入您要创建的账户名"); // 输入账户信息

		String userName = input.next();

		// 从数据库中调用数据,判断账户是否存在
		File userData = new File("C:\\dataBase\\userData.txt");

		if (!userData.exists()) { // 如果文件不存在,就创建文件

			userData.createNewFile();
			mIsSucceed = FileReadAndFileWrite.saveUserData(userName); // 在数据库中查询用户信息

		} else { // 如果文件存在,在文件中查询账户

			mIsSucceed = FileReadAndFileWrite.saveUserData(userName); // 在数据库中查询用户信息
		}

		if (mIsSucceed) {

			System.out.println("创建用户名成功!");

		} else {

			System.out.println("已存在用户名!");

		}

		return false;
	}
	
	

	/**
	 * 查找 修改 删除 用户信息
	 */
	private static void searchOrModifyAccountOrDelete(String key) throws Exception {
		System.out.println("请选择" + key + "用户信息的方式  1-编号查询 2-用户名查询");
		int choose = input.nextInt();
		switch (choose) {
		case 1:  //按照编号查找
			System.out.println("请输入要" + key + "的编号:");
			int number = input.nextInt();
			if (key.equals("查询")) {
				searchForNumber(number);
			} else if(key.equals("修改")){
				modifyForNumber(number);
			}else if(key.equals("删除")){
				deleteForNumber(number);
			}

			break;
		case 2:
			System.out.println("请输入要" + key + "的用户名");
			String userName = input.next();
			if (key.equals("查询")) {
				searchForUserName(userName);
			} else if(key.equals("修改")){
				modifyForUserName(userName);
			}else if(key.equals("删除")){
				deleteForUserName(userName);
			}
			break;
		default:
			System.out.println("输入错误!");
			break;
		}

	}
/**
 * 按用户名删除用户信息
 * @param userName
 * @throws Exception 
 */
	private static void deleteForUserName(String userName) throws Exception {
		// TODO Auto-generated method stub
		FileReadAndFileWrite.deleteNameUser(userName);
	}
/**
 * 按用户编号删除用户信息
 * @param number
 * @throws Exception 
 */
	private static void deleteForNumber(int number) throws Exception {
		// TODO Auto-generated method stub
		FileReadAndFileWrite.deleteNumUser(number);
	}

	/**
	 * 按照用户名修改用户信息
	 * 
	 * @param userName
	 * @throws Exception
	 */
	private static void modifyForUserName(String userName) throws Exception {
		FileReadAndFileWrite.modifyNameUser(userName);
	}

	/**
	 * 按照用户编号修改用户信息
	 * 
	 * @param number
	 * @throws Exception
	 */
	private static void modifyForNumber(int number) throws Exception {
		FileReadAndFileWrite.modifyNumUser(number);
	}

	/**
	 * 按用户编号查找用户信息
	 * 
	 * @param userName
	 * @throws Exception
	 */
	private static void searchForUserName(String userName) throws Exception {
		FileReadAndFileWrite.findNameUser(userName);
	}

	/**
	 * 按照用户名查找用户信息
	 * 
	 * @param number
	 * @throws Exception
	 */
	private static void searchForNumber(int number) throws Exception {
		FileReadAndFileWrite.findNumUser(number);
	}

}
