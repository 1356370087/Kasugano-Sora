import java.sql.*;
import java.util.*;

public class DBConn {
	         static Connection conn;
	         
	         //��̬��
	         static {
	        	       //�������ݿ����ӵķ���
	        	 conn = JDBCon();
           }
	         
	         
	//���ݿ����ӵķ���
		public static Connection JDBCon() {
			try {
				//--2 ������������
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				//--3 �����ַ���
				String url="jdbc:sqlserver://localhost:1433;databaseName=StudentDB";
				//--4 �������ݿ�����
				Connection conn=DriverManager.getConnection(url,"sa","");
				System.out.println("���ݿ����ӳɹ�");
				return conn;
			  //--5 �쳣����
			} catch (ClassNotFoundException e) {
				System.out.println("���ݿ����������Ҳ����쳣");
				e.printStackTrace();
				return null;
			} catch (SQLException e) {
				System.out.println("���ݿ������쳣");
				e.printStackTrace();
				return null;
			}
		}
		
		//��������ѧ�����ݵķ���
		public static Vector<Student> queryAllStudents(){
			try {
				    //�����Ự����
				Statement stmt = conn.createStatement();
				//ִ��SQL��ѯ
				ResultSet rs = stmt.executeQuery("Select * from Student");
				//װ�ز�ѯ�����Vector����
				Vector<Student> data = new Vector<Student>();
				while(rs.next()) {
					String stuId = rs.getString(1);
					String stuName = rs.getString(2);
					String sex = rs.getString(3);
					String birth = rs.getString(4);
					Student s = new Student(stuId,stuName,sex,birth);
					data.add(s);
				}
				//�رս����
				rs.close();
				stmt.close();
				return data;
			}catch(SQLException e) {
				System.out.println("���ݷ����쳣");
				e.printStackTrace();
				return null;
			}
		}
		
		//�����ѧ���ķ���
		public static boolean addNewStudent(Student newStu){
			try {
				                 //�����Ự����
				Statement stmt = conn.createStatement();
				String sql = "Insert into Student values('"+newStu.stuId+"','"+newStu.stuName+
						                                 "','"+newStu.sex+"','"
						                                       +newStu.birth+"')";
				System.out.println(sql);//��ӡsql��䣬����﷨
				//ִ��SQL��䣬������Ӱ�������
				int r = stmt.executeUpdate(sql);
				stmt.close();
				if(r>0) {
					         return true;
				}else {
					         return false;
				}
			}catch(SQLException e) {
				System.out.println("���ݷ����쳣");
				e.printStackTrace();
				return false;
			}
		}
		//�޸�ѧ���ķ���
		public static boolean updateStudent(String stuId,String field,String newValue) {
			try {
				          //�����Ự����
				Statement stmt = conn.createStatement();
				String sql = "Update Student set "+field +"='"+newValue
						+"'where stuId='"+stuId+"'";
				System.out.println(sql);//��ӡsql��䣬����﷨
				//ִ��SQL��䣬������Ӱ�������
				int r = stmt.executeUpdate(sql);
				stmt.close();
				if(r>0) {
					         return true;
				}else {
					         return false;
				}
				
				
			}catch(SQLException e) {
				           System.out.println("���ݷ����쳣");
				           e.printStackTrace();
				           return false;
			}
		}
		//ɾ��ѧ���ķ���
				public static boolean deleteStudent(String stuId) {
					try {
						          //�����Ự����
						Statement stmt = conn.createStatement();
						String sql = "delete from Student where stuId='"+stuId+"'";
						System.out.println(sql);//��ӡsql��䣬����﷨
						//ִ��SQL��䣬������Ӱ�������
						int r = stmt.executeUpdate(sql);
						stmt.close();
						if(r>0) {
							         return true;
						}else {
							         return false;
						}
						
						
					}catch(SQLException e) {
						           System.out.println("���ݷ����쳣");
						           e.printStackTrace();
						           return false;
					}
				}
				//ʹ�õ��ô洢����������ݵķ���
				public static boolean addStudentByCallablement(Student stu) {
					try {
								//�������ô洢���̻Ự����
								CallableStatement cstmt = conn.prepareCall("{call insertData(?,?,?,?)}");
								//��pstmt���ò���
								cstmt.setString(1,stu.stuId);
								cstmt.setString(2,stu.stuName);
								cstmt.setString(3,stu.sex);
								cstmt.setString(4,stu.birth);
								//ִ��SQL��䣬������Ӱ�������
								int r = cstmt.executeUpdate();
								cstmt.close();
								if(r>0) {
										return true;
							}else {
										return false;
							}
						
					}catch(SQLException ex) {
						System.out.println("���ݷ����쳣");
						ex.printStackTrace();
						return false;
				}
				}
				
             }
