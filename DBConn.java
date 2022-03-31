import java.sql.*;
import java.util.*;

public class DBConn {
	         static Connection conn;
	         
	         //静态块
	         static {
	        	       //调用数据库连接的方法
	        	 conn = JDBCon();
           }
	         
	         
	//数据库连接的方法
		public static Connection JDBCon() {
			try {
				//--2 加载驱动程序
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				//--3 连接字符串
				String url="jdbc:sqlserver://localhost:1433;databaseName=StudentDB";
				//--4 创建数据库连接
				Connection conn=DriverManager.getConnection(url,"sa","");
				System.out.println("数据库连接成功");
				return conn;
			  //--5 异常捕获
			} catch (ClassNotFoundException e) {
				System.out.println("数据库驱动程序找不到异常");
				e.printStackTrace();
				return null;
			} catch (SQLException e) {
				System.out.println("数据库连接异常");
				e.printStackTrace();
				return null;
			}
		}
		
		//访问所有学生数据的方法
		public static Vector<Student> queryAllStudents(){
			try {
				    //创建会话对象
				Statement stmt = conn.createStatement();
				//执行SQL查询
				ResultSet rs = stmt.executeQuery("Select * from Student");
				//装载查询结果到Vector对象
				Vector<Student> data = new Vector<Student>();
				while(rs.next()) {
					String stuId = rs.getString(1);
					String stuName = rs.getString(2);
					String sex = rs.getString(3);
					String birth = rs.getString(4);
					Student s = new Student(stuId,stuName,sex,birth);
					data.add(s);
				}
				//关闭结果集
				rs.close();
				stmt.close();
				return data;
			}catch(SQLException e) {
				System.out.println("数据访问异常");
				e.printStackTrace();
				return null;
			}
		}
		
		//添加新学生的方法
		public static boolean addNewStudent(Student newStu){
			try {
				                 //创建会话对象
				Statement stmt = conn.createStatement();
				String sql = "Insert into Student values('"+newStu.stuId+"','"+newStu.stuName+
						                                 "','"+newStu.sex+"','"
						                                       +newStu.birth+"')";
				System.out.println(sql);//打印sql语句，检查语法
				//执行SQL语句，返回受影响的行数
				int r = stmt.executeUpdate(sql);
				stmt.close();
				if(r>0) {
					         return true;
				}else {
					         return false;
				}
			}catch(SQLException e) {
				System.out.println("数据访问异常");
				e.printStackTrace();
				return false;
			}
		}
		//修改学生的方法
		public static boolean updateStudent(String stuId,String field,String newValue) {
			try {
				          //创建会话对象
				Statement stmt = conn.createStatement();
				String sql = "Update Student set "+field +"='"+newValue
						+"'where stuId='"+stuId+"'";
				System.out.println(sql);//打印sql语句，检查语法
				//执行SQL语句，返回受影响的行数
				int r = stmt.executeUpdate(sql);
				stmt.close();
				if(r>0) {
					         return true;
				}else {
					         return false;
				}
				
				
			}catch(SQLException e) {
				           System.out.println("数据访问异常");
				           e.printStackTrace();
				           return false;
			}
		}
		//删除学生的方法
				public static boolean deleteStudent(String stuId) {
					try {
						          //创建会话对象
						Statement stmt = conn.createStatement();
						String sql = "delete from Student where stuId='"+stuId+"'";
						System.out.println(sql);//打印sql语句，检查语法
						//执行SQL语句，返回受影响的行数
						int r = stmt.executeUpdate(sql);
						stmt.close();
						if(r>0) {
							         return true;
						}else {
							         return false;
						}
						
						
					}catch(SQLException e) {
						           System.out.println("数据访问异常");
						           e.printStackTrace();
						           return false;
					}
				}
				//使用调用存储过程添加数据的方法
				public static boolean addStudentByCallablement(Student stu) {
					try {
								//创建调用存储过程会话对象
								CallableStatement cstmt = conn.prepareCall("{call insertData(?,?,?,?)}");
								//给pstmt设置参数
								cstmt.setString(1,stu.stuId);
								cstmt.setString(2,stu.stuName);
								cstmt.setString(3,stu.sex);
								cstmt.setString(4,stu.birth);
								//执行SQL语句，返回受影响的行数
								int r = cstmt.executeUpdate();
								cstmt.close();
								if(r>0) {
										return true;
							}else {
										return false;
							}
						
					}catch(SQLException ex) {
						System.out.println("数据访问异常");
						ex.printStackTrace();
						return false;
				}
				}
				
             }
