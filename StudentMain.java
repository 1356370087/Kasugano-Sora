import java.util.Vector;
import java.util.*;
public class StudentMain {

	public static void main(String[] args) {
		do {
			    System.out.println("��ӭʹ��ѧ��ϵͳ");
			    System.out.println("********************************");
			    System.out.println("*  1.��ʾ����ѧ����Ϣ    *");
			    System.out.println("*  2.���һ��ѧ����Ϣ    *");
			    System.out.println("*  3.����ѧ���޸�ѧ����Ϣ    *");
			    System.out.println("*  4.����ѧ��ɾ��ѧ����Ϣ    *");
			    System.out.println("*  0.�˳�ϵͳ            *");
			    System.out.println("*********************************");
			    System.out.print("������˵����: ");
			    Scanner sc = new Scanner(System.in);
			    int menu = sc.nextInt();
			    
			    Vector <Student> data = new Vector <Student>();
			    //��ѯ����ѧ������
			    data = DBConn.queryAllStudents();
			    
			    switch(menu) {
			    case 1:
			    	            //��ӡ��ѧ����Ϣ
					    	System.out.println("ѧ��\t����\t�Ա�\t����");
					    	System.out.println("==============================");
					    	for(Student s:data) {
					    		s.printStuInfo();
					    	}
					    	break;
			    case 2:		
			    			System.out.println("������Ҫ��ӵ���ѧ������Ϣ��");
			    			System.out.print("ѧ��: ");
			    			String stuId = sc.next();
			    			System.out.print("������");
			    			String stuName = sc.next();
			    			System.out.print("�Ա�");
			    			String sex = sc.next();
			    			System.out.print("���գ�");
			    			String birth = sc.next();
			    			Student newStu = new Student(stuId,stuName,sex,birth);
			    			//����DBConn�е�addnewStudent���������ѧ��
			    			if(DBConn.addNewStudent(newStu)) {
			    						System.out.println("���ѧ���ɹ���");
			    						
			    			}else {
			    						System.out.println("���ѧ��ʧ�ܣ�");
			    			}
			    			break;
			    case 3:
			    			System.out.println("������Ҫ�޸ĵ�ѧ����ѧ��: ");
			    			System.out.print("ѧ�ţ�");
			    			String stuId2 = sc.next();
			    			System.out.println("������Ҫ�޸ĵ�ѧ���ĸ���Ϣ");
			    			System.out.println("1.���� \t2.�Ա� \t3.����");
			    			System.out.print("�������޸ĵ�ѧ����Ϣ��ţ�");
			    			int num = sc.nextInt();
			    			String field = "";
			    			if(num==1) {
			    						field = "stuName";
			    						
			    			}else if(num==2) {
			    						field = "sex";
			    						
			    			}else {
			    						field = "birth";
			    						
			    			}
			    				System.out.println("�������µ�ֵ��");
			    				String value = sc.next();
			    				//����DBConn���޸�ѧ����Ϣ�ķ����޸�ѧ��
			    				if(DBConn.updateStudent(stuId2, field, value)) {
			    					System.out.println("�޸�ѧ����Ϣ�ɹ�!");
			    				}else {
			    					System.out.println("�޸�ѧ����Ϣʧ��!");
			    				}
			    				break;
			    case 4:
			    			System.out.println("������Ҫɾ����ѧ����ѧ�ţ�");
			    			System.out.print("ѧ�ţ�");
			    			String stuId3 = sc.next();
			    			//ȷ���Ƿ����Ҫɾ��ѧ��
			    			System.out.println("�����Ҫɾ����ѧ����(y/n): ");
			    			String answer = sc.next();
			    			if(answer.equals("y")||answer.equals("Y")) {
			    				//����DBConn��ɾ��ѧ���ķ���
			    				if(DBConn.deleteStudent(stuId3)) {
			    					System.out.println("ѧ��ɾ���ɹ���");
			    				}else {
			    					System.out.println("ѧ��ɾ��ʧ�ܣ�");
			    				}
			    			}
			    			break;
			    	  
			    case 0:
	    	                System.exit(0);
			    }
			    
			    
			    
		}while(true);

	}

}
