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
	
	public static boolean mIsTransfer = false; // �ж��Ƿ���ת��״̬ false-��
	
	static Double transferMoney = 0.0;  //ת�˵�Ǯ

	/**
	 * ���û���ΪuserName ���浽���ݿ��� ����û��������ݿ��д���
	 */
	public static boolean saveUserData(final String userName) throws Exception {
		// ��ȡ�ļ�
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream("c:\\dataBase\\userData.txt")));
		// д�ļ�
		PrintWriter 
		
		pw = new PrintWriter("c:\\dataBase\\userData4.txt");

		// �ж��ļ��Ƿ����
		boolean mIsExists = false; // Ĭ���û���������
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
			return false; // �û����Ѵ���
		} else { // ���û���д���ļ���
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

		// ��userData4�е�����д�뵽userData��
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
		System.out.println("---------------------��������û���----------------");
		int count = 0;
		while ((line = br.readLine()) != null) {
			if (count % 5 == 0) {
				
				System.out
						.println("------------------------------------------");
			}
			count = count + 1;

			System.out.println(line);

		}
		System.out.println("---------------------��ʾ����-----------------------");
		System.out.println();
		System.out.println();
		br.close();
		// copyUserDataToUserData4();
		// copyUserData4ToUserData();
		return true;
	}

	/**
	 * ��ȡ�û��ı��
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
	 * ���ݱ�� �����ݿ����Ҷ�Ӧ��ŵ��û���Ϣ
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
					System.out.println("�˺ű��:" + line);
					for (int i = 0; i < 4; i++) {
						line = br.readLine();
						if (i == 0) {
							System.out.println("�û���Ϊ:" + line);
						} else if (i == 1) {
							System.out.println("�˻�����Ϊ:" + line);
						} else if (i == 2) {
							System.out.println("�˻����Ϊ:" + line + "Ԫ");
						} else if (i == 3) {
							System.out.println("�����˻�����Ϊ:" + line);
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
			System.out.println("δ�ҵ����û�");
			Thread.sleep(2000);
			System.out.println();
			System.out.println();
		}
	}

	/**
	 * �����������Ҷ�Ӧ���û���Ϣ
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
			System.out.println("δ�ҵ����û�");
			Thread.sleep(2000);
			System.out.println();
			System.out.println();
		}
		if (isChanged) {
			// System.out.println("�û��ı��Ϊ"+preline);
			findNumUser(Integer.parseInt(preline));
		}
	}

	/**
	 * �����û�������޸�
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
		boolean isChanged = true; // �ж��Ƿ��д���ݿ�
		while ((line = br.readLine()) != null) {
			if (count % 5 == 0) {
				if (line.equals("" + index)) {
					System.out.println("�Ƿ�Ҫ�޸��û����� 1-��,0-��");
					int choose = input.nextInt();
					isSucceed = true;
					if (choose == 1) {
						line = br.readLine();
						String line1 = br.readLine();
						System.out.println("�������û���ʼ����");
						String line3 = input.next();
						if (line3.equals(line1)) {
							System.out.println("��֤�ɹ�!");
							System.out.println("������Ҫ�޸ĵ�����");
							String password = input.next();
							System.out.println("�û����:" + index);
							pw2.println(index);
							pw2.flush();
							System.out.println("�û���:" + line);
							pw2.println(line);
							pw2.flush();
							System.out.println("�û�����:" + password);
							pw2.println(password);
							pw2.flush();
							line = br.readLine();
							System.out.println("�û����:" + line);
							pw2.println(line);
							pw2.flush();
							line = br.readLine();
							System.out.println("�˻���������:" + line);
							pw2.println(line);
							pw2.flush();
						} else {
							System.out.println("�������!");
							Thread.sleep(1000);
							isChanged = false; 
							break;

						}

					} else {
						System.out.println("�˳���ѯ");
						isChanged = false; // ���ı��ļ�
						break;
					}
				} else {
					//System.out.println("ƥ������line=="+line+"count=="+count);
					pw2.println(line);
					pw2.flush();
				}
			} else {
				//System.out.println("5�ı���line=="+line+"count=="+count);
				pw2.println(line);
				pw2.flush();
			}
			count = count + 1;

		}
		if (!isSucceed) {
			System.out.println("δ�ҵ��û�");
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
	 * �����û������޸�����
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
			System.out.println("δ�ҵ����û�");
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
					// System.out.println("�û����:" + preline);
					deleteNumUser(Integer.parseInt(preline));
				}

			} else {

			}
			count += 1;
			preline = line;
		}
		br.close();
		if (!isFind) {
			System.out.println("δ�ҵ����û�");
			Thread.sleep(2000);
			System.out.println();
			System.out.println();
		}

	}

	/**
	 * �����û�������޸�
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
					System.out.println("�Ƿ�Ҫɾ���û� 1-��,0-��");
					int choose = input.nextInt();
					isSucceed = true;
					if (choose == 1) {
						line = br.readLine();
						String line1 = br.readLine();
						System.out.println("�������û���ʼ����");
						String line3 = input.next();
						if (line3.equals(line1)) {
							System.out.println("ɾ���ɹ�!");
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
							System.out.println("�����������!");
							isChanged = true; // �ļ��ı���

						}

					} else {
						System.out.println("�˳���ѯ");
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
			System.out.println("δ�ҵ��û�");
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
	 * ���ļ�1���Ƶ��ļ�4
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
	 * ���ļ�4���Ƶ��ļ�1
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
			System.out.println("������Ҫ����Ľ��");
			fee = input.nextDouble();
			if (fee <= 0) {
				System.out.println("���������0");
				fee = 0.0;
			}
		} else {
			System.out.println("����ת��״̬!");
			fee = transferMoney;
		}

		while ((line = br.readLine()) != null) {
			if (count % 5 == 0) {
				if (line.equals("" + index)) { // �ҵ��ñ��
					String name = br.readLine(); // ����û���
					String password = br.readLine(); // �������
					System.out.println("�������˺�����");
					String pass = input.next();
					if (!pass.equals(password)) {
						isChanged = false;
						isSucceed = false;
						System.out.println("�����������!");
						Thread.sleep(1200);
						break;
					} else {
						System.out.println("������:" + fee);
						Double sum = Double.parseDouble(br.readLine());
						sum += fee;
						System.out.println("�˻����:" + sum);
						pw2.println(index);
						pw2.flush();
						pw2.println(name);
						pw2.flush();
						pw2.println(password);
						pw2.flush();
						pw2.println(sum);
						pw2.flush();
						line = br.readLine();
						System.out.println("�����˻�����:" + line);
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
	 * �����û������д��
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
//			System.out.println("������Ҫ����Ľ��");
//			fee = input.nextDouble();
//			if (fee <= 0) {
//				System.out.println("���������0");
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
			System.out.println("δ�ҵ����û�");
			Thread.sleep(2000);
			System.out.println();
			System.out.println();
		}
		if (isChanged) {
			// System.out.println("�û��ı��Ϊ"+preline);
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
	 * �����û�������ȡ��
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
						line = br.readLine(); // ��ȡ�û�����
						if(mIsTransfer){
							
							line3 = line;
						}else{
							System.out.println("�������û�����");
							 line3 = input.next();
						}
						
						if (line3.equals(line)) { // ��֤�û�����
							Double fee = null;
							if(mIsTransfer){
								 fee = transferMoney;
							}else{
								System.out.println("������Ҫȡ���Ľ��");
								 fee = input.nextDouble();
							}
							
							System.out.println("�û����:" + preline);
							System.out.println("�û���:" + name);
							pw2.println(name);
							pw2.flush();
							System.out.println("�û�����:" + line3);
							pw2.println(line3);
							pw2.flush();
							line = br.readLine();
							Double sum = Double.parseDouble(line);
							System.out.println("�û����:" + sum);
							if (fee <= sum) {
								fee = fee;
							} else {
								System.out.println("ȡ��ʧ��");
								fee = 0.0;
								Thread.sleep(1500);
								isChanged = false;
								break;
							}
							System.out.println("Ҫȡ���Ľ��Ϊ:" + fee);
							sum -= fee;
							System.out.println("ȡ��֮��Ľ��Ϊ:" + sum);
							pw2.println(sum);
							pw2.flush();
							line = br.readLine();
							System.out.println("�˻���������:" + line);
							pw2.println(line);
							pw2.flush();
							isSucceed = true;
						} else {
							System.out.println("�û��������");
							line = br.readLine();
							pw2.println(line);
							pw2.flush();
							isChanged = false;
							break;
						}

					} else {
						System.out.println("�����˳�");
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
			System.out.println("δ�ҵ��û�");
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
	 * �����û���Ž���ȡ��
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
							System.out.println("�������û���ʼ����");
							 line3 = input.next();
						}
						
						if (line3.equals(line1)) {
							System.out.println("��֤�ɹ�!");
							Double fee = 0.0;
							if(mIsTransfer){
								fee = transferMoney;
							}else{
								System.out.println("������Ҫȡ���Ľ��");
								 fee = input.nextDouble();
							}
							
							System.out.println("�û����:" + index);
							pw2.println(index);
							pw2.flush();
							System.out.println("�û���:" + line);
							pw2.println(line);
							pw2.flush();
							System.out.println("�û�����:" + line3);
							pw2.println(line3);
							pw2.flush();
							line = br.readLine();
							Double sum = Double.parseDouble(line);
							if (fee <= sum) {
								fee = fee;
							} else {
								fee = 0.0;
								System.out.println("ȡ��ʧ��!");
								Thread.sleep(1500);
								isChanged = false;
								break;
							}
							System.out.println("�û����ȡ��ǰ���Ϊ" + sum + "Ԫ");
							sum -= fee;
							System.out
									.println("ȡ���" + fee + "Ԫ�����Ϊ" + sum + "Ԫ");
							pw2.println(sum);
							pw2.flush();
							line = br.readLine();
							System.out.println("�˻���������:" + line);
							pw2.println(line);
							pw2.flush();
						} else {
							System.out.println("�����������!");
							isChanged = false;
							break;

						}

					} else {
						System.out.println("�˳���ѯ");
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
			System.out.println("δ�ҵ��û�");
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
						line = br.readLine(); // ��ȡ�û�����
						System.out.println("�������û�����");
						String line3 = input.next();
						if (line3.equals(line)) { // ��֤�û�����
							transferNumUserOfMoney(Integer.parseInt(preline));
//							
						} else {
							System.out.println("�û��������");
							line = br.readLine();
							pw2.println(line);
							pw2.flush();
							Thread.sleep(1500);
							isChanged = false;
							break;
						}

					} else {
						System.out.println("�����˳�");
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
			System.out.println("δ�ҵ��û�");
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
						System.out.println("������ת���˻��ĳ�ʼ����");
						String line3 = input.next();
						if (line3.equals(line1)) {
							System.out.println("��֤�ɹ�!");
							System.out.println("������Ҫת���Ľ��");
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
								System.out.println("ȡ��ʧ��!");
								Thread.sleep(1000);
								isChanged = false;
								break;
							}
							System.out.println("�û����ת��ǰ���Ϊ" + sum + "Ԫ");
							sum -= fee;
							System.out
									.println("ת��" + fee + "Ԫ�����Ϊ" + sum + "Ԫ");
							pw2.println(sum);
							pw2.flush();
							line = br.readLine();
							// System.out.println("�˻���������:" + line);
							pw2.println(line);
							pw2.flush();
							System.out.println("��ѡ��Ҫת���˻��ķ�ʽ");
							System.out.println("1.���˻����ת��");
							System.out.println("2.���˻�����ת��");
							 cho = input.nextInt();
							mIsTransfer = true;
							isSucceed = true;
							break;

						} else {
							System.out.println("�����������!");
							Thread.sleep(1000);
							isChanged = false;
							break;
						}

					} else {
						System.out.println("�˳���ѯ");
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
			System.out.println("δ�ҵ��û�");
		}
		br.close();
		pw2.close();
		if(!isChanged){
			copyUserData4ToUserData();
		}
		copyUserDataToUserData4();
		copyUserData4ToUserData();
		if (cho == 1) {
			System.out.println("������Ҫת����˻��ı��");
			int index1 = input.nextInt();
			takeNumUserOfMoney(index1);
			saveNumUserOfMoney(index1);
			
		} else {
			System.out.println("������Ҫת����˻�������");
			String name = input.next();
			takeNameUserOfMoney(name);
			saveNameUserOfMoney(name);
			
		}
		mIsTransfer = !mIsTransfer;
	}

}
