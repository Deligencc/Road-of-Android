package sdut.ligong.demo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class FileReadAndFileWrite {
	
	static Scanner input = new Scanner(System.in);
	
	public static boolean mIsTransfer = false; // 判断是否是转账状态 false-否
	
	static Double transferMoney = 0.0;  //转账的钱

	/**
	 * 将用户名为userName 保存到数据库中 如果用户名在数据库中存在
	 */
	public static boolean saveUserData(final String userName) throws Exception {
		// 读取文件
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream("c:\\dataBase\\userData.txt")));
		// 写文件
		PrintWriter 
		
		pw = new PrintWriter("c:\\dataBase\\userData4.txt");

		// 判断文件是否存在
		boolean mIsExists = false; // 默认用户名不存在
		String line = null;
		while ((line = br.readLine()) != null) {
			if (userName.equals(line)) {
				mIsExists = true;
			}
			pw.println(line);
			pw.flush();
		}
		if (mIsExists) {
			mIsExists = !mIsExists;
			return false; // 用户名已存在
		} else { // 将用户名写入文件中
			User u = new User(userName);
			pw.println(u.getId());
			pw.flush();
			pw.println(u.getUserName());
			pw.flush();
			pw.println(u.getPassword());
			pw.flush();
			pw.println(u.getMoney());
			pw.flush();
			pw.println(u.getCreateAccountData());
			pw.flush();
		}
		br.close();
		pw.close();

		// 将userData4中的内容写入到userData中
		BufferedReader br2 = new BufferedReader(new InputStreamReader(
				new FileInputStream("c:\\dataBase\\userData4.txt")));
		PrintWriter pw2 = new PrintWriter("c:\\dataBase\\userData.txt");
		line = null;
		while ((line = br2.readLine()) != null) {
			pw2.println(line);
			pw2.flush();
		}
		br2.close();
		pw2.close();
		br = new BufferedReader(new InputStreamReader(new FileInputStream(
				"c:\\dataBase\\userData4.txt")));
		System.out.println("---------------------创建后的用户名----------------");
		int count = 0;
		while ((line = br.readLine()) != null) {
			if (count % 5 == 0) {
				
				System.out
						.println("------------------------------------------");
			}
			count = count + 1;

			System.out.println(line);

		}
		System.out.println("---------------------显示结束-----------------------");
		System.out.println();
		System.out.println();
		br.close();
		// copyUserDataToUserData4();
		// copyUserData4ToUserData();
		return true;
	}

	/**
	 * 读取用户的编号
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String readCount() throws Exception {
		BufferedReader br2 = new BufferedReader(new InputStreamReader(
				new FileInputStream("c:\\dataBase\\userData.txt")));
		PrintWriter pw2 = new PrintWriter("c:\\dataBase\\userData4.txt");
		int count = 0, count1 = 0;
		String line = null;
		while ((line = br2.readLine()) != null) {
			if (count % 5 == 0) {
				count1++;
				pw2.println(count1);
				pw2.flush();
			} else {
				pw2.println(line);
				pw2.flush();
			}
			count++;
		}
		int C = count1 + 1;
		pw2.close();
		br2.close();
		copyUserData4ToUserData();
		copyUserDataToUserData4();
		return C + "";
	
	}

	/**
	 * 根据编号 从数据库中找对应编号的用户信息
	 * 
	 * @param index
	 * @throws Exception
	 */
	public static void findNumUser(int index) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream("c:\\dataBase\\userData.txt")));
		String line = null;
		int count = 0;
		boolean isFind = false;
		while ((line = br.readLine()) != null) {
			if (count % 5 == 0) {
				if (line.equals("" + index)) {
					isFind = true;
					System.out.println("账号编号:" + line);
					for (int i = 0; i < 4; i++) {
						line = br.readLine();
						if (i == 0) {
							System.out.println("用户名为:" + line);
						} else if (i == 1) {
							System.out.println("账户密码为:" + line);
						} else if (i == 2) {
							System.out.println("账户余额为:" + line + "元");
						} else if (i == 3) {
							System.out.println("创建账户日期为:" + line);
						}

					}
					break;
				}
			} else {
				isFind = false;
			}
			count += 1;

		}

		br.close();
		if (!isFind) {
			System.out.println("未找到该用户");
			Thread.sleep(2000);
			System.out.println();
			System.out.println();
		}
	}

	/**
	 * 根据姓名查找对应的用户信息
	 * 
	 * @param index
	 * @throws Exception
	 */
	public static void findNameUser(String index) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream("c:\\dataBase\\userData.txt")));
		String line = null;
		String preline = null;
		boolean isChanged = false;
		int count = 4;
		boolean isFind = false;
		while ((line = br.readLine()) != null) {
			if (count % 5 == 0) {
				if (line.equals(index)) {
					isFind = true;
					isChanged = true;
					break;
				}
			} else {
				// System.out.println("line ====== "+line+"  count==="+count);
			}
			count += 1;
			preline = line;
		}
		br.close();
		if (!isFind) {
			System.out.println("未找到该用户");
			Thread.sleep(2000);
			System.out.println();
			System.out.println();
		}
		if (isChanged) {
			// System.out.println("用户的编号为"+preline);
			findNumUser(Integer.parseInt(preline));
		}
	}

	/**
	 * 按照用户名编号修改
	 * 
	 * @param index
	 * @throws Exception
	 */
	public static void modifyNumUser(int index) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream("c:\\dataBase\\userData4.txt")));
		PrintWriter pw2 = new PrintWriter("c:\\dataBase\\userData.txt");
		String line = null;
		int count = 0;
		boolean isSucceed = false;
		boolean isChanged = true; // 判断是否改写数据库
		while ((line = br.readLine()) != null) {
			if (count % 5 == 0) {
				if (line.equals("" + index)) {
					System.out.println("是否要修改用户密码 1-是,0-否");
					int choose = input.nextInt();
					isSucceed = true;
					if (choose == 1) {
						line = br.readLine();
						String line1 = br.readLine();
						System.out.println("请输入用户初始密码");
						String line3 = input.next();
						if (line3.equals(line1)) {
							System.out.println("验证成功!");
							System.out.println("请输入要修改的密码");
							String password = input.next();
							System.out.println("用户编号:" + index);
							pw2.println(index);
							pw2.flush();
							System.out.println("用户名:" + line);
							pw2.println(line);
							pw2.flush();
							System.out.println("用户密码:" + password);
							pw2.println(password);
							pw2.flush();
							line = br.readLine();
							System.out.println("用户余额:" + line);
							pw2.println(line);
							pw2.flush();
							line = br.readLine();
							System.out.println("账户创建日期:" + line);
							pw2.println(line);
							pw2.flush();
						} else {
							System.out.println("密码错误!");
							Thread.sleep(1000);
							isChanged = false; 
							break;

						}

					} else {
						System.out.println("退出查询");
						isChanged = false; // 不改变文件
						break;
					}
				} else {
					//System.out.println("匹配名称line=="+line+"count=="+count);
					pw2.println(line);
					pw2.flush();
				}
			} else {
				//System.out.println("5的倍数line=="+line+"count=="+count);
				pw2.println(line);
				pw2.flush();
			}
			count = count + 1;

		}
		if (!isSucceed) {
			System.out.println("未找到用户");
		}
		br.close();
		pw2.close();
		if (!isChanged) {
			copyUserData4ToUserData();
		}
		copyUserDataToUserData4();
		copyUserData4ToUserData();
	}

	/**
	 * 按照用户姓名修改密码
	 * 
	 * @param name
	 * @throws Exception
	 */
	public static void modifyNameUser(String name) throws Exception {
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream("c:\\dataBase\\userData.txt")));
		String line = null;
		String preline = null;
		int count = 4;
		boolean isFind = false;
		while ((line = br.readLine()) != null) {
			if (count % 5 == 0) {
				if (line.equals(name)) {
					isFind = true;
					modifyNumUser(Integer.parseInt(preline));
				}

			} else {

			}
			count += 1;
			preline = line;
		}
		br.close();
		if (!isFind) {
			System.out.println("未找到该用户");
			Thread.sleep(2000);
			System.out.println();
			System.out.println();
		}

		

	}

	public static void deleteNameUser(String name) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream("c:\\dataBase\\userData.txt")));
		String line = null;
		String preline = null;
		int count = 4;
		boolean isFind = false;
		while ((line = br.readLine()) != null) {
			if (count % 5 == 0) {
				if (line.equals(name)) {
					isFind = true;
					// System.out.println("用户编号:" + preline);
					deleteNumUser(Integer.parseInt(preline));
				}

			} else {

			}
			count += 1;
			preline = line;
		}
		br.close();
		if (!isFind) {
			System.out.println("未找到该用户");
			Thread.sleep(2000);
			System.out.println();
			System.out.println();
		}

	}

	/**
	 * 按照用户名编号修改
	 * 
	 * @param index
	 * @throws Exception
	 */
	public static void deleteNumUser(int index) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream("c:\\dataBase\\userData4.txt")));
		PrintWriter pw2 = new PrintWriter("c:\\dataBase\\userData.txt");
		String line = null;
		int count = 0;
		boolean isSucceed = false;
		boolean isChanged = false;
		while ((line = br.readLine()) != null) {
			if (count % 5 == 0) {
				if (line.equals("" + index)) {
					System.out.println("是否要删除用户 1-是,0-否");
					int choose = input.nextInt();
					isSucceed = true;
					if (choose == 1) {
						line = br.readLine();
						String line1 = br.readLine();
						System.out.println("请输入用户初始密码");
						String line3 = input.next();
						if (line3.equals(line1)) {
							System.out.println("删除成功!");
							Thread.sleep(2000);
							pw2.println("");
							pw2.flush();
							pw2.println("");
							pw2.flush();
							pw2.println("");
							pw2.flush();
							line = br.readLine();
							pw2.println("");
							pw2.flush();
							line = br.readLine();
						} else {
							System.out.println("密码输入错误!");
							isChanged = true; // 文件改变了

						}

					} else {
						System.out.println("退出查询");
					}
				} else {
					pw2.println(line);
					pw2.flush();
				}
			} else {
				pw2.println(line);
				pw2.flush();
			}
			count = count + 1;

		}
		if (!isSucceed) {
			System.out.println("未找到用户");
		}
		br.close();
		pw2.close();
		if (isChanged) {
			copyUserData4ToUserData();
		}
		copyUserDataToUserData4();
		copyUserData4ToUserData();
	}

	/**
	 * 将文件1复制到文件4
	 * 
	 * @throws Exception
	 */
	public static void copyUserDataToUserData4() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream("c:\\dataBase\\userData.txt")));
		PrintWriter pw = new PrintWriter("c:\\dataBase\\userData4.txt");
		String line = null;
		while ((line = br.readLine()) != null) {
			if (!line.equals("")) {
				pw.println(line);
				pw.flush();
			}
		}

		br.close();
		pw.close();
	}

	/**
	 * 将文件4复制到文件1
	 * 
	 * @throws Exception
	 */
	public static void copyUserData4ToUserData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream("c:\\dataBase\\userData4.txt")));
		PrintWriter pw = new PrintWriter("c:\\dataBase\\userData.txt");
		String line = null;
		while ((line = br.readLine()) != null) {
			if (!line.equals("")) {
				pw.println(line);
				pw.flush();
			}
		}

		br.close();
		pw.close();
	}

	public static void saveNumUserOfMoney(int index) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream("c:\\dataBase\\userData4.txt")));
		PrintWriter pw2 = new PrintWriter("c:\\dataBase\\userData.txt");
		String line = null;
		int count = 0;
		Double fee = 0.0;
		String preline = null;
		boolean isChanged = true;
		boolean isSucceed = true;
		if (!mIsTransfer) {
			System.out.println("请输入要存入的金额");
			fee = input.nextDouble();
			if (fee <= 0) {
				System.out.println("金额必须大于0");
				fee = 0.0;
			}
		} else {
			System.out.println("进入转账状态!");
			fee = transferMoney;
		}

		while ((line = br.readLine()) != null) {
			if (count % 5 == 0) {
				if (line.equals("" + index)) { // 找到该编号
					String name = br.readLine(); // 获得用户名
					String password = br.readLine(); // 获得密码
					System.out.println("请输入账号密码");
					String pass = input.next();
					if (!pass.equals(password)) {
						isChanged = false;
						isSucceed = false;
						System.out.println("密码输入错误!");
						Thread.sleep(1200);
						break;
					} else {
						System.out.println("存入金额:" + fee);
						Double sum = Double.parseDouble(br.readLine());
						sum += fee;
						System.out.println("账户余额:" + sum);
						pw2.println(index);
						pw2.flush();
						pw2.println(name);
						pw2.flush();
						pw2.println(password);
						pw2.flush();
						pw2.println(sum);
						pw2.flush();
						line = br.readLine();
						System.out.println("创建账户日期:" + line);
						pw2.println(line);
						pw2.flush();
					}
				} else {
					pw2.println(line);
					pw2.flush();
				}
			} else {
				pw2.println(line);
				pw2.flush();
			}
			count++;
			preline = line;
		}
		br.close();
		pw2.close();
		if (!isChanged) {
			copyUserData4ToUserData();
		}
		copyUserDataToUserData4();
		copyUserData4ToUserData();
	}

	/**
	 * 按照用户名进行存款
	 * 
	 * @param name
	 * @throws Exception
	 */
	public static void saveNameUserOfMoney(String name) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream("c:\\dataBase\\userData4.txt")));
		String line = null;
		String preline = null;
		boolean isChanged = false;
		int count = 4;
		boolean isFind = false;
		Double fee;
//		if (!mIsTransfer) {
//			System.out.println("请输入要存入的金额");
//			fee = input.nextDouble();
//			if (fee <= 0) {
//				System.out.println("金额必须大于0");
//				fee = 0.0;
//			}
//		} else {
//			fee = transferMoney;
//		}

	
		while ((line = br.readLine()) != null) {
			if (count % 5 == 0) {
				if (line.equals(name)) {
					isFind = true;
					isChanged = true;
//					System.out.println("name=="+line);
//					System.out.println("preline=="+preline);
					saveNumUserOfMoney(Integer.parseInt(preline));
					break;
				}
			} else {
				// System.out.println("line ====== "+line+"  count==="+count);
			}
			count += 1;
			preline = line;
		}
		br.close();
		if (!isFind) {
			System.out.println("未找到该用户");
			Thread.sleep(2000);
			System.out.println();
			System.out.println();
		}
		if (isChanged) {
			// System.out.println("用户的编号为"+preline);
			findNumUser(Integer.parseInt(preline));
		}

		br.close();
		if (!isChanged) {
			copyUserData4ToUserData();
		}
		copyUserDataToUserData4();
		copyUserData4ToUserData();
	}

	/**
	 * 按照用户名进行取款
	 * 
	 * @param name
	 * @throws Exception
	 */
	public static void takeNameUserOfMoney(String name) throws Exception {
		boolean isSucceed = false;
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream("c:\\dataBase\\userData4.txt")));
		PrintWriter pw2 = new PrintWriter("c:\\dataBase\\userData.txt");
		String line = null;
		String preline = null;
		int count = 3;
		boolean isChanged = true;
		while ((line = br.readLine()) != null) {
			count = count + 1;
			if (count % 5 == 0) {
				if (line.equals(name)) {
					isSucceed = true;

					int choose = 1;
					if (choose == 1) {
						String line3 = null;
						line = br.readLine(); // 获取用户密码
						if(mIsTransfer){
							
							line3 = line;
						}else{
							System.out.println("请输入用户密码");
							 line3 = input.next();
						}
						
						if (line3.equals(line)) { // 验证用户密码
							Double fee = null;
							if(mIsTransfer){
								 fee = transferMoney;
							}else{
								System.out.println("请输入要取出的金额");
								 fee = input.nextDouble();
							}
							
							System.out.println("用户编号:" + preline);
							System.out.println("用户名:" + name);
							pw2.println(name);
							pw2.flush();
							System.out.println("用户密码:" + line3);
							pw2.println(line3);
							pw2.flush();
							line = br.readLine();
							Double sum = Double.parseDouble(line);
							System.out.println("用户余额:" + sum);
							if (fee <= sum) {
								fee = fee;
							} else {
								System.out.println("取款失败");
								fee = 0.0;
								Thread.sleep(1500);
								isChanged = false;
								break;
							}
							System.out.println("要取出的金额为:" + fee);
							sum -= fee;
							System.out.println("取出之后的金额为:" + sum);
							pw2.println(sum);
							pw2.flush();
							line = br.readLine();
							System.out.println("账户创建日期:" + line);
							pw2.println(line);
							pw2.flush();
							isSucceed = true;
						} else {
							System.out.println("用户密码错误");
							line = br.readLine();
							pw2.println(line);
							pw2.flush();
							isChanged = false;
							break;
						}

					} else {
						System.out.println("程序退出");
						isChanged = false;
						break;
					}
				} else {
					pw2.println(line);
					pw2.flush();
				}
			} else {
				pw2.println(line);
				pw2.flush();
			}

			preline = line;

		}
		if (!isSucceed) {
			System.out.println("未找到用户");
		}
		br.close();
		pw2.close();
		if (!isChanged) {
			copyUserData4ToUserData();
		}
		copyUserDataToUserData4();
		copyUserData4ToUserData();
	}

	/**
	 * 按照用户编号进行取款
	 * 
	 * @param index
	 * @throws Exception
	 */
	public static void takeNumUserOfMoney(int index) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream("c:\\dataBase\\userData4.txt")));
		PrintWriter pw2 = new PrintWriter("c:\\dataBase\\userData.txt");
		String line = null;
		int count = 0;
		boolean isSucceed = false;
		boolean isChanged = true;
		while ((line = br.readLine()) != null) {
			if (count % 5 == 0) {
				if (line.equals("" + index)) {
					isSucceed = true;
					int choose = 1;
					if (choose == 1) {
						line = br.readLine();
						String line1 = br.readLine();
						String line3 = null;
						if(mIsTransfer){
							 line3 = line1;
						}else{
							System.out.println("请输入用户初始密码");
							 line3 = input.next();
						}
						
						if (line3.equals(line1)) {
							System.out.println("验证成功!");
							Double fee = 0.0;
							if(mIsTransfer){
								fee = transferMoney;
							}else{
								System.out.println("请输入要取出的金额");
								 fee = input.nextDouble();
							}
							
							System.out.println("用户编号:" + index);
							pw2.println(index);
							pw2.flush();
							System.out.println("用户名:" + line);
							pw2.println(line);
							pw2.flush();
							System.out.println("用户密码:" + line3);
							pw2.println(line3);
							pw2.flush();
							line = br.readLine();
							Double sum = Double.parseDouble(line);
							if (fee <= sum) {
								fee = fee;
							} else {
								fee = 0.0;
								System.out.println("取款失败!");
								Thread.sleep(1500);
								isChanged = false;
								break;
							}
							System.out.println("用户余额取款前余额为" + sum + "元");
							sum -= fee;
							System.out
									.println("取款后" + fee + "元后余额为" + sum + "元");
							pw2.println(sum);
							pw2.flush();
							line = br.readLine();
							System.out.println("账户创建日期:" + line);
							pw2.println(line);
							pw2.flush();
						} else {
							System.out.println("密码输入错误!");
							isChanged = false;
							break;

						}

					} else {
						System.out.println("退出查询");
						isChanged = false;
						break;
					}
				} else {
					pw2.println(line);
					pw2.flush();
				}
			} else {
				pw2.println(line);
				pw2.flush();
			}
			count = count + 1;

		}
		if (!isSucceed) {
			System.out.println("未找到用户");
		}
		br.close();
		pw2.close();
		if(!isChanged){
			copyUserData4ToUserData();
		}
		copyUserDataToUserData4();
		copyUserData4ToUserData();
	}

	public static void transferNameUserOfMoney(String name) throws Exception {
		mIsTransfer = true;
		boolean isSucceed = false;
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream("c:\\dataBase\\userData4.txt")));
		PrintWriter pw2 = new PrintWriter("c:\\dataBase\\userData.txt");
		String line = null;
		String preline = null;
		int count = 3;
		boolean isChanged = true;
		while ((line = br.readLine()) != null) {
			count = count + 1;
			if (count % 5 == 0) {
				if (line.equals(name)) {
					isSucceed = true;

					int choose = 1;
					if (choose == 1) {
						line = br.readLine(); // 获取用户密码
						System.out.println("请输入用户密码");
						String line3 = input.next();
						if (line3.equals(line)) { // 验证用户密码
							transferNumUserOfMoney(Integer.parseInt(preline));
//							
						} else {
							System.out.println("用户密码错误");
							line = br.readLine();
							pw2.println(line);
							pw2.flush();
							Thread.sleep(1500);
							isChanged = false;
							break;
						}

					} else {
						System.out.println("程序退出");
						Thread.sleep(1500);
						isChanged = false;
						break;
					}
				} else {
					pw2.println(line);
					pw2.flush();
				}
			} else {
				pw2.println(line);
				pw2.flush();
			}
			preline = line;

		}
		if (!isSucceed) {
			System.out.println("未找到用户");
		}
		br.close();
		pw2.close();
		if(!isChanged){
			copyUserData4ToUserData();
		}
		copyUserDataToUserData4();
		copyUserData4ToUserData();
		mIsTransfer = !mIsTransfer;
	}

	public static void transferNumUserOfMoney(int index) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream("c:\\dataBase\\userData4.txt")));
		PrintWriter pw2 = new PrintWriter("c:\\dataBase\\userData.txt");
		String line = null;
		int count = 0;
		int cho = 0;
		boolean isSucceed = false;
		boolean isChanged = true;
		while ((line = br.readLine()) != null) {
			if (count % 5 == 0) {
				if (line.equals("" + index)) {
					isSucceed = true;
					int choose = 1;
					if (choose == 1) {
						line = br.readLine();
						String line1 = br.readLine();
						System.out.println("请输入转出账户的初始密码");
						String line3 = input.next();
						if (line3.equals(line1)) {
							System.out.println("验证成功!");
							System.out.println("请输入要转出的金额");
							Double fee = input.nextDouble();
							transferMoney = fee;
							pw2.println(index);
							pw2.flush();
							pw2.println(line);
							pw2.flush();
							pw2.println(line3);
							pw2.flush();
							line = br.readLine();
							Double sum = Double.parseDouble(line);
							if (fee <= sum) {
								fee = fee;
							} else {
								fee = 0.0;
								System.out.println("取款失败!");
								Thread.sleep(1000);
								isChanged = false;
								break;
							}
							System.out.println("用户余额转出前余额为" + sum + "元");
							sum -= fee;
							System.out
									.println("转出" + fee + "元后余额为" + sum + "元");
							pw2.println(sum);
							pw2.flush();
							line = br.readLine();
							// System.out.println("账户创建日期:" + line);
							pw2.println(line);
							pw2.flush();
							System.out.println("请选择要转出账户的方式");
							System.out.println("1.按账户编号转入");
							System.out.println("2.按账户名号转入");
							 cho = input.nextInt();
							mIsTransfer = true;
							isSucceed = true;
							break;

						} else {
							System.out.println("密码输入错误!");
							Thread.sleep(1000);
							isChanged = false;
							break;
						}

					} else {
						System.out.println("退出查询");
						Thread.sleep(1000);
						isChanged = false;
						break;
					}
				} else {
					pw2.println(line);
					pw2.flush();
				}
			} else {
				pw2.println(line);
				pw2.flush();
			}
			count = count + 1;

		}
		if (!isSucceed) {
			System.out.println("未找到用户");
		}
		br.close();
		pw2.close();
		if(!isChanged){
			copyUserData4ToUserData();
		}
		copyUserDataToUserData4();
		copyUserData4ToUserData();
		if (cho == 1) {
			System.out.println("请输入要转入的账户的编号");
			int index1 = input.nextInt();
			takeNumUserOfMoney(index1);
			saveNumUserOfMoney(index1);
			
		} else {
			System.out.println("请输入要转入的账户的名称");
			String name = input.next();
			takeNameUserOfMoney(name);
			saveNameUserOfMoney(name);
			
		}
		mIsTransfer = !mIsTransfer;
	}

}
