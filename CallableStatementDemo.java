import java.util.Scanner;
public class CallableStatementDemo {

	public static void main(String[] args) {
				Scanner sc = new Scanner(System.in);
				System.out.println("������Ҫ��ӵ�ѧ������Ϣ��");
				System.out.print("ѧ�ţ�");
				String stuId = sc.next();
				System.out.print("������");
				String stuName = sc.next();
				System.out.print("�Ա�");
				String sex = sc.next();
				System.out.print("���գ�");
				String birth = sc.next();
				Student stu = new Student(stuId,stuName,sex,birth);
				
				//����DBConn�ĵ��ô洢����������ݵķ���
				if(DBConn.addStudentByCallablement(stu)) {
					System.out.println("���ѧ����Ϣ�ɹ�!");
				}else {
					System.out.println("���ѧ����Ϣʧ��!");
			}
				
				
		}

}
