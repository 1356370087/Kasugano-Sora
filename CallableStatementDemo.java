import java.util.Scanner;
public class CallableStatementDemo {

	public static void main(String[] args) {
				Scanner sc = new Scanner(System.in);
				System.out.println("请输入要添加的学生的信息：");
				System.out.print("学号：");
				String stuId = sc.next();
				System.out.print("姓名：");
				String stuName = sc.next();
				System.out.print("性别：");
				String sex = sc.next();
				System.out.print("生日：");
				String birth = sc.next();
				Student stu = new Student(stuId,stuName,sex,birth);
				
				//调用DBConn的调用存储过程添加数据的方法
				if(DBConn.addStudentByCallablement(stu)) {
					System.out.println("添加学生信息成功!");
				}else {
					System.out.println("添加学生信息失败!");
			}
				
				
		}

}
