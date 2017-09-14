package sdut.ligong.demo;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MainActivity {

	static Scanner input = new Scanner(System.in);
	
	static boolean mIsFirst = true;

	/**
	 * ������
	 * 
	 * @return
	 */
	private static void showMain() {
		if(mIsFirst){
			boolean going = verifyIdentidy();
			if(going){
				mIsFirst = false;
				System.out.println("��ӭ�������й���ϵͳ!");
				System.out.println("1.�����˻�");
				System.out.println("2.��ѯ�˻�");
				System.out.println("3.�޸�����");
				System.out.println("4.ɾ���˻�");
				System.out.println("5.��Ǯ");
				System.out.println("6.ȡǮ");
				System.out.println("7.ת��");
				System.out.println("0.�˳�");
				System.out.println("��ѡ����Ҫ���еĲ���");
			
			}else{
				// ��½ʧ��
				System.out.println("�û������������!��5�������");
				for (int i = 0; i < 5; i++) {
					System.out.println("����ʱ" + (5 - i));
					try { // �ӳ�һ��
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
			System.out.println("��ӭ�������й���ϵͳ!");
			System.out.println("��ѡ����Ҫ���еĲ���");
			System.out.println("1.�����˻�");
			System.out.println("2.��ѯ�˻�");
			System.out.println("3.�޸��û���Ϣ");
			System.out.println("4.ɾ���˻�");
			System.out.println("5.��Ǯ");
			System.out.println("6.ȡǮ");
			System.out.println("7.ת��");
			System.out.println("0.�˳�");
			
			
		}
		
			
			

	}

	

	/**
	 * ���������
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
			 case 1://�����˻�
			CreateAccount();
			 break;
			 case 2:  //��ѯ�˻�
			 searchOrModifyAccountOrDelete("��ѯ");
			 break;
			 case 3:  //�޸��˻�
			searchOrModifyAccountOrDelete("�޸�");
			 break;
			 case 4:  //ɾ���˻�
			searchOrModifyAccountOrDelete("ɾ��");
			 break;
			 case 5:  //��Ǯ
			saveMoney();
			 break;
			 case 6: //ȡǮ
			takeMoney();
			 break;
			 case 7: //תǮ
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
		saveAndtakeMoney("ת��");
	}

	private static void takeMoney() throws Exception {
		saveAndtakeMoney("ȡǮ");
	}

	private static void saveMoney() throws Exception {
		saveAndtakeMoney("��Ǯ");
	}
	
	public static void saveAndtakeMoney(String key) throws Exception{
		System.out.println("��ѡ����Ҫ"+key+"�ķ�ʽ");
		System.out.println("1.�����û���Ž���"+key);
		System.out.println("2.�����û�������"+key);
		int choose = input.nextInt();
		if(choose==1){
			System.out.println("��������Ҫ"+key+"���û����");
			choose = input.nextInt();
			if(key.equals("ȡǮ")){
				FileReadAndFileWrite.takeNumUserOfMoney(choose);
			}else if(key.equals("��Ǯ")){
				FileReadAndFileWrite.saveNumUserOfMoney(choose);
			}else{
				FileReadAndFileWrite.transferNumUserOfMoney(choose);
			}
			
		}else if(choose==2){
			System.out.println("��������Ҫ"+key+"���û���");
			String userName = input.next();
			if(key.equals("ȡǮ")){
				FileReadAndFileWrite.takeNameUserOfMoney(userName);
			}else if(key.equals("��Ǯ")){
				FileReadAndFileWrite.saveNameUserOfMoney(userName);
			}else{
				FileReadAndFileWrite.transferNameUserOfMoney(userName);
			}
			
		}
	}

	// ��֤���
	/**
	 * �жϹ���Ա���
	 * 
	 * @return
	 */
	public static boolean verifyIdentidy() {
		System.out.println("���������Ա�˻�");
		String userName = input.next();
		if (userName.equals("admin")) {
			System.out.println("������������Ա����");
			String password = input.next();
			if (password.equals("123456")) {
				return true;
			}
		}
		return false;
	}

	// �������˻�
	/**
	 * @return false �������˻�ʧ��
	 * @throws Exception
	 */
	private static boolean CreateAccount() throws Exception {

		boolean mIsSucceed = false; // �жϴ����˻��Ƿ�ɹ� true-�ɹ� false-ʧ��

		System.out.println("��������Ҫ�������˻���"); // �����˻���Ϣ

		String userName = input.next();

		// �����ݿ��е�������,�ж��˻��Ƿ����
		File userData = new File("C:\\dataBase\\userData.txt");

		if (!userData.exists()) { // ����ļ�������,�ʹ����ļ�

			userData.createNewFile();
			mIsSucceed = FileReadAndFileWrite.saveUserData(userName); // �����ݿ��в�ѯ�û���Ϣ

		} else { // ����ļ�����,���ļ��в�ѯ�˻�

			mIsSucceed = FileReadAndFileWrite.saveUserData(userName); // �����ݿ��в�ѯ�û���Ϣ
		}

		if (mIsSucceed) {

			System.out.println("�����û����ɹ�!");

		} else {

			System.out.println("�Ѵ����û���!");

		}

		return false;
	}
	
	

	/**
	 * ���� �޸� ɾ�� �û���Ϣ
	 */
	private static void searchOrModifyAccountOrDelete(String key) throws Exception {
		System.out.println("��ѡ��" + key + "�û���Ϣ�ķ�ʽ  1-��Ų�ѯ 2-�û�����ѯ");
		int choose = input.nextInt();
		switch (choose) {
		case 1:  //���ձ�Ų���
			System.out.println("������Ҫ" + key + "�ı��:");
			int number = input.nextInt();
			if (key.equals("��ѯ")) {
				searchForNumber(number);
			} else if(key.equals("�޸�")){
				modifyForNumber(number);
			}else if(key.equals("ɾ��")){
				deleteForNumber(number);
			}

			break;
		case 2:
			System.out.println("������Ҫ" + key + "���û���");
			String userName = input.next();
			if (key.equals("��ѯ")) {
				searchForUserName(userName);
			} else if(key.equals("�޸�")){
				modifyForUserName(userName);
			}else if(key.equals("ɾ��")){
				deleteForUserName(userName);
			}
			break;
		default:
			System.out.println("�������!");
			break;
		}

	}
/**
 * ���û���ɾ���û���Ϣ
 * @param userName
 * @throws Exception 
 */
	private static void deleteForUserName(String userName) throws Exception {
		// TODO Auto-generated method stub
		FileReadAndFileWrite.deleteNameUser(userName);
	}
/**
 * ���û����ɾ���û���Ϣ
 * @param number
 * @throws Exception 
 */
	private static void deleteForNumber(int number) throws Exception {
		// TODO Auto-generated method stub
		FileReadAndFileWrite.deleteNumUser(number);
	}

	/**
	 * �����û����޸��û���Ϣ
	 * 
	 * @param userName
	 * @throws Exception
	 */
	private static void modifyForUserName(String userName) throws Exception {
		FileReadAndFileWrite.modifyNameUser(userName);
	}

	/**
	 * �����û�����޸��û���Ϣ
	 * 
	 * @param number
	 * @throws Exception
	 */
	private static void modifyForNumber(int number) throws Exception {
		FileReadAndFileWrite.modifyNumUser(number);
	}

	/**
	 * ���û���Ų����û���Ϣ
	 * 
	 * @param userName
	 * @throws Exception
	 */
	private static void searchForUserName(String userName) throws Exception {
		FileReadAndFileWrite.findNameUser(userName);
	}

	/**
	 * �����û��������û���Ϣ
	 * 
	 * @param number
	 * @throws Exception
	 */
	private static void searchForNumber(int number) throws Exception {
		FileReadAndFileWrite.findNumUser(number);
	}

}
