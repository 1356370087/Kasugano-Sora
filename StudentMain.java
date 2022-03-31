import java.util.Vector;
import java.util.*;
public class StudentMain {

	public static void main(String[] args) {
		do {
			    System.out.println("欢迎使用学生系统");
			    System.out.println("********************************");
			    System.out.println("*  1.显示所有学生信息    *");
			    System.out.println("*  2.添加一个学生信息    *");
			    System.out.println("*  3.根据学号修改学生信息    *");
			    System.out.println("*  4.根据学号删除学生信息    *");
			    System.out.println("*  0.退出系统            *");
			    System.out.println("*********************************");
			    System.out.print("请输入菜单编号: ");
			    Scanner sc = new Scanner(System.in);
			    int menu = sc.nextInt();
			    
			    Vector <Student> data = new Vector <Student>();
			    //查询所有学生数据
			    data = DBConn.queryAllStudents();
			    
			    switch(menu) {
			    case 1:
			    	            //打印出学生信息
					    	System.out.println("学号\t姓名\t性别\t生日");
					    	System.out.println("==============================");
					    	for(Student s:data) {
					    		s.printStuInfo();
					    	}
					    	break;
			    case 2:		
			    			System.out.println("请输入要添加的新学生的信息：");
			    			System.out.print("学号: ");
			    			String stuId = sc.next();
			    			System.out.print("姓名：");
			    			String stuName = sc.next();
			    			System.out.print("性别：");
			    			String sex = sc.next();
			    			System.out.print("生日：");
			    			String birth = sc.next();
			    			Student newStu = new Student(stuId,stuName,sex,birth);
			    			//调用DBConn中的addnewStudent方法添加新学生
			    			if(DBConn.addNewStudent(newStu)) {
			    						System.out.println("添加学生成功：");
			    						
			    			}else {
			    						System.out.println("添加学生失败：");
			    			}
			    			break;
			    case 3:
			    			System.out.println("请输入要修改的学生的学号: ");
			    			System.out.print("学号：");
			    			String stuId2 = sc.next();
			    			System.out.println("请输入要修改的学生哪个信息");
			    			System.out.println("1.姓名 \t2.性别 \t3.生日");
			    			System.out.print("请输入修改的学生信息编号：");
			    			int num = sc.nextInt();
			    			String field = "";
			    			if(num==1) {
			    						field = "stuName";
			    						
			    			}else if(num==2) {
			    						field = "sex";
			    						
			    			}else {
			    						field = "birth";
			    						
			    			}
			    				System.out.println("请输入新的值：");
			    				String value = sc.next();
			    				//调用DBConn中修改学生信息的方法修改学生
			    				if(DBConn.updateStudent(stuId2, field, value)) {
			    					System.out.println("修改学生信息成功!");
			    				}else {
			    					System.out.println("修改学生信息失败!");
			    				}
			    				break;
			    case 4:
			    			System.out.println("请输入要删除的学生的学号：");
			    			System.out.print("学号：");
			    			String stuId3 = sc.next();
			    			//确认是否真的要删除学生
			    			System.out.println("你真的要删除该学生？(y/n): ");
			    			String answer = sc.next();
			    			if(answer.equals("y")||answer.equals("Y")) {
			    				//调用DBConn里删除学生的方法
			    				if(DBConn.deleteStudent(stuId3)) {
			    					System.out.println("学生删除成功：");
			    				}else {
			    					System.out.println("学生删除失败：");
			    				}
			    			}
			    			break;
			    	  
			    case 0:
	    	                System.exit(0);
			    }
			    
			    
			    
		}while(true);

	}

}
