import java.sql.*;
import java.util.*;
public class DBCon {
			static Connection conn;
			//��̬��(�����ȱ�����)
			static {
						conn=JDBCon();
			}

	public static Connection JDBCon() {
		 	 try {
		 		 	  //--2������������
		 		      Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		 		      //--3���������ַ���
		 		      String url = "jdbc:sqlserver://localhost:1433; databaseName=BookDB";
		 		      //--4�������ݿ�����
		 		      conn = DriverManager.getConnection(url,"sa","");
		 		      //System.out.println("���ݿ����ӳɹ�");
		 		      return conn;
		 		  //--5�����쳣
		 	 }catch(ClassNotFoundException e) {
		 		 		System.out.println("���������޷������쳣");
		 		 		e.printStackTrace();
		 		 		return null;
		 	 }catch(SQLException e) {
		 		 		System.out.println("���ݿ��޷������쳣");
		 		 		e.printStackTrace();
		 		 		return null;
		 	 }
	   }
		 	 
		 	 //��ѯ����ͼ����Ϣ�ķ���
		 	 public static Vector<Book> queryAllBooks(){
		 		 		try {
		 		 				//�����Ự����
		 		 				Statement stmt = conn.createStatement();
		 		 				//����SQL���
		 		 				String sql = "Select * from Book";
		 		 				//ִ�в�ѯ�����ؽ����
		 		 				ResultSet rs = stmt.executeQuery(sql);
		 		 				Vector<Book> data = new Vector<Book>();
		 		 				while(rs.next()) {
		 		 					String BookID = rs.getString(1);
		 		 					String BookName = rs.getString(2);
		 		 					String Author = rs.getString(3);
		 		 					String Publisher = rs.getString(4);
		 		 					String PublishedDate = rs.getString(5);
		 		 					Float Price = rs.getFloat(6);
		 		 					Book b = new Book(BookID,BookName,Author,Publisher,PublishedDate,Price);
		 		 					data.add(b);
		 		 					
		 		 				}
		 		 					rs.close();
		 		 					stmt.close();
		 		 					return data;
		 		 		}catch(SQLException ex) {
		 		 					System.out.println("���ݷ����쳣");
		 		 					ex.printStackTrace();
		 		 					return null;
		 		 		}
		 	     }
		 		 			  
		 		 		    //��ѯͼ�����Ƿ���ڷ���
		 		 				public static boolean queryBookID(String BookID) {
		 		 						try {
		 		 								  //�����Ự����
		 		 								Statement stmt = conn.createStatement();
		 		 								//����SQL���
		 		 								String sql = "Select * from Book where BookID='"+BookID+"'";
		 		 								//ִ�в�ѯ�����ؽ����
		 		 								ResultSet rs = stmt.executeQuery(sql);
		 		 								boolean flag = true;
		 		 								if(rs!=null && rs.next()) {
		 		 										flag = true;
		 		 										
		 		 								}else {
		 		 										flag = false;
		 		 								}
		 		 								rs.close();
		 		 								stmt.close();
		 		 								return flag;
		 		 						}catch(SQLException ex) {
		 		 								System.out.println("���ݷ����쳣");
		 		 								ex.printStackTrace();
		 		 								return false;
		 		 						}
		 		 				}
		 		 				
		 		 			   //���ͼ����Ϣ�ķ���
		 		 			   public static boolean addBook(Book bk) {
		 		 						try {
		 		 								//�����Ự����
		 		 								Statement stmt = conn.createStatement();
		 		 							    String sql = "Insert into Book values('"+ bk.BookID
		 		 							    		                        +"','"+bk.BookName+"','"+bk.Author+"','"
		 		 							    		                        +bk.Publisher+"','"+bk.PublishedDate
		 		 							    		                        +"','"+bk.Price+"')";
		 		 								                           
		 		 						    //System.out.println(sql);
		 		 							    //ִ��SQL��䣬������Ӱ�������
		 		 							    int r = stmt.executeUpdate(sql);
		 		 							    stmt.close();
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
		 		 			   //�޸�ͼ����Ϣ�ķ���
		 		 			   public static boolean updateBook(String BookID,String field,String value) {
		 		 				   		   try {
		 		 				   			   		 //�����Ự����
		 		 				   			   Statement stmt = conn.createStatement();
		 		 				   			   String sql = "Update Book set "+field+"='"+value+"' where BookID='"+BookID+"'";
		 		 				   			 //  System.out.println(sql);
		 		 				   			         //ִ��SQL��䣬������Ӱ�������
		 		 				   			   int r = stmt.executeUpdate(sql);
		 		 				   			   stmt.close();
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
		 		 			   //ɾ��ͼ����Ϣ�ķ���
		 		 			   public static boolean deleteBook(String BookID) {
		 		 				   		   try {
		 		 				   			   		 //�����Ự����
		 		 				   			         Statement stmt = conn.createStatement();
		 		 				   			         String sql = "Delete from Book where BookID ='"+ BookID+"'";
		 		 				   			       //  System.out.println(sql);
		 		 				   			         //ִ��SQL��䣬������Ӱ�������
		 		 				   			         int r = stmt.executeUpdate(sql);
		 		 				   			         stmt.close();
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
		 		 			   
		 		 			   //����ͼ���ź�ͼ������ģ����ѯͼ����Ϣ
		 		 			   public static Vector<Book> queryBooksByWhere(String field,String value){
		 		 				          try {
		 		 				        	  		//�����Ự����
		 		 				        	        Statement stmt = conn.createStatement();
		 		 				        	        //����SQL���
		 		 				        	        String sql = "Select * from Book where " +field+" like '%"+value+"%'";
		 		 				        	        System.out.println(sql);
		 		 				        	        //ִ�в�ѯ�����ؽ����
		 		 				        	        ResultSet rs = stmt.executeQuery(sql);
		 		 				        	        Vector<Book> data = new Vector<Book>();
		 		 				        	        while(rs.next()) {
		 		 				        	        		    String BookID = rs.getString(1);
		 		 				        	        		    String BookName = rs.getString(2);
		 		 				        	        		    String Author = rs.getString(3);
		 		 				        	        		    String Publisher = rs.getString(4);
		 		 				        	        		    String PublishedDate = rs.getString(5);
		 		 				        	        		    Float Price = rs.getFloat(6);
		 		 				        	        		    Book b = new Book(BookID,BookName,Author,Publisher,PublishedDate,Price);
		 		 				        	        		    //��ͼ�����װ�ؼ�����
		 		 				        	        		    data.add(b);
		 		 				        	        		    
		 		 				        	        }
		 		 				        	        //�ر�
		 		 				        	        rs.close();
		 		 				        	        stmt.close();
		 		 				        	        return data;
		 		 				          }catch(SQLException ex) {
		 		 				        	  		System.out.println("���ݷ����쳣");
		 		 				        	  		ex.printStackTrace();
		 		 				        	  		return null;
		 		 				          }
		 		 			   }
		 		 			   
		 		 			   //���ݳ������ڷ�Χ��ѯͼ��
		 		 			   public static Vector<Book> queryBooksByPublishedDate(String PublishedDate1, String PublishedDate2){
		 		 				   				try {
		 		 				   						  //�����Ự����
		 		 				   					      Statement stmt = conn.createStatement();
		 		 				   					      //����SQL���
		 		 				   					      String sql = "Select * from Book where PublishedDate>='"+PublishedDate1
		 		 				   					    		                    +"' and PublishedDate<='"+PublishedDate2+"'";
		 		 				   					      System.out.println(sql);
		 		 				   					//ִ�в�ѯ�����ؽ����
				 		 				        	        ResultSet rs = stmt.executeQuery(sql);
				 		 				        	        Vector<Book> data = new Vector<Book>();
				 		 				        	        while(rs.next()) {
	 		 				        	        		    String BookID = rs.getString(1);
	 		 				        	        		    String BookName = rs.getString(2);
	 		 				        	        		    String Author = rs.getString(3);
	 		 				        	        		    String Publisher = rs.getString(4);
	 		 				        	        		    String PublishedDate = rs.getString(5);
	 		 				        	        		    Float Price = rs.getFloat(6);
	 		 				        	        		    Book b = new Book(BookID,BookName,Author,Publisher,PublishedDate,Price);
	 		 				        	        		    //��ͼ�����װ�ؼ�����
	 		 				        	        		    data.add(b);
		 		 				   					      
		 		 				   				      }
				 		 				        	        //�ر�
				 		 				        	        rs.close();
				 		 				        	        stmt.close();
				 		 				        	        return data;
				 		 				        	        
		 		 			   }catch(SQLException ex) {
 				        	  		System.out.println("���ݷ����쳣");
 				        	  		ex.printStackTrace();
 				        	  		return null;
		 		 				   				
		 		 			   }
		 		 			   
		 		 			 }
		 		 			   
		 		 			   public static Vector<Book> queryBooksByPrice(String Price1, String Price2){
		 		 				   			try {
		 		 				   					 //�����Ự����
		 		 				   				     Statement stmt = conn.createStatement();
		 		 				   				     String sql = "Select * from Book where Price>='"+Price1
		 		 				   				    		        +"' and Price<='"+Price2+"'";
		 		 				   				     System.out.println(sql);
		 		 				   				     //ִ�в�ѯ�����ؽ����
			 		 				        	     ResultSet rs = stmt.executeQuery(sql);
			 		 				        	     Vector<Book> data = new Vector<Book>();
			 		 				        	     while(rs.next()) {
		 				        	        		 String BookID = rs.getString(1);
		 				        	        		 String BookName = rs.getString(2);
		 				        	        		 String Author = rs.getString(3);
		 				        	        		 String Publisher = rs.getString(4);
		 				        	        		 String PublishedDate = rs.getString(5);
		 				        	        		 Float Price = rs.getFloat(6);
		 				        	        		 Book b = new Book(BookID,BookName,Author,Publisher,PublishedDate,Price);
		 				        	        		 //��ͼ�����װ�ؼ�����
		 				        	        		 data.add(b);
		 		 				   			}
			 		 				        	     rs.close();
			 		 				        	     stmt.close();
			 		 				        	     return data;
		 		 			   }catch(SQLException ex) {
				        	  		System.out.println("���ݷ����쳣");
				        	  		ex.printStackTrace();
				        	  		return null;
				        	  		
		 		 		} 	  		
		 		 			 }
		 		 			   
		 		 			   //�޸�����ķ���
		 		 			   public static boolean changepassword(String UserName, String newpassword) {
		 		 				   			try {
		 		 				   					   //�����Ự����
		 		 				   				       Statement stmt = conn.createStatement();
		 		 				   				       String sql = "Update Users set password='"+newpassword
		 		 				   				    		        +"' where UserName='"+UserName+"'";
		 		 				   				     //  System.out.println(sql);
		 		 				   				       //ִ��SQL��������Ӱ�������
		 		 				   				       int r = stmt.executeUpdate(sql);
		 		 				   				       stmt.close();
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
		 		 			   
		 		 			   public static Vector<BookManager> queryBookMangerByBookID(String BookID5){
		 		 				   					try {
		 		 				   								//�����Ự����
		 		 				   						        Statement stmt = conn.createStatement();
		 		 				   						        String sql = "Select b.ReaderID,b.ReaderName,sex,Identitycard,TelephoneNumber,a.BookID,a.BooKName,Author,Publisher,PublishedDate,Price,BookState,BorrowDate,ReturnDate"+" from Book a,Reader b,Borrow c "+
		 		 				   						        	         "where a.BookID=c.BookID"+" and c.ReaderID=b.ReaderID"+
		 		 				   						        	         " and a.BookID='"+BookID5+"'";
		 		 				   						        System.out.println(sql);
		 		 				   						        //ִ�в�ѯ�����ؽ����
		 		 				   						        ResultSet rs = stmt.executeQuery(sql);
		 		 				   						        Vector<BookManager> data1 = new Vector<BookManager>();
		 		 				   						        while(rs.next()) {
								 		 				   						        String ReaderID = rs.getString(1);
							 				   						        			String ReaderName = rs.getString(2);
							 				   						        			String Sex = rs.getString(3);
							 				   						        			String Identitycard = rs.getString(4);
							 				   						        			String TelephoneNumber = rs.getString(5);
							 				   						        			String BookID = rs.getString(6);
							 				   						        			String BookName = rs.getString(7);
							 				   						        			String Author = rs.getString(8);
							 				   						        			String Publisher = rs.getString(9);
							 				   						        			String PublishedDate = rs.getString(10);
							 				   						        			Float Price = rs.getFloat(11);
							 				   						        			String BookState = rs.getString(12);
							 				   						        			String BorrowDate = rs.getString(13);
							 				   						        			String ReturnDate = rs.getString(14);
							 				   						        			
		 		 				   						        			BookManager bmr = new BookManager(ReaderID,ReaderName,Sex,Identitycard,TelephoneNumber,BookID,BookName,Author,
		 		 				   						        					          Publisher,PublishedDate,Price,BookState,BorrowDate,ReturnDate);
		 		 				   						        			data1.add(bmr);
		 		 				   						             }
		 		 				   						             //�ر�
		 		 				   						             rs.close();
		 		 				   						             stmt.close();
		 		 				   						             return data1;
		 		 				   						        
		 		 				   						        
		 		 				   						        
		 		 				   						        
		 		 				   					}catch(SQLException ex) {
				 		 				   			      System.out.println("���ݷ����쳣");
								        	  		      ex.printStackTrace();
								        	  		      return null;
		 		 				   					}
		 		 			   }
		 		 			   
		 	
		 		 			 public static Vector<BookManager> queryBookMangerByReaderNo(String ReaderID2){
				   					try {
				   								//�����Ự����
				   						        Statement stmt = conn.createStatement();
				   						        String sql = "Select b.ReaderID,b.ReaderName,Sex,Identitycard,TelephoneNumber,a.BookID,a.BooKName,Author,Publisher,PublishedDate,Price,BookState,BorrowDate,ReturnDate"+" from Book a,Reader b,Borrow c "+
				   						        	         "where a.BookID=c.BookID"+" and c.ReaderID=b.ReaderID"+
				   						        	         " and c.ReaderID='"+ReaderID2+"'";
				   						        System.out.println(sql);
				   						        //ִ�в�ѯ�����ؽ����
				   						        ResultSet rs = stmt.executeQuery(sql);
				   						        Vector<BookManager> data2 = new Vector<BookManager>();
				   						        while(rs.next()) {
				 		 				   						        String ReaderID = rs.getString(1);
			 				   						        			String ReaderName = rs.getString(2);
			 				   						        			String Sex = rs.getString(3);
			 				   						        			String Identitycard = rs.getString(4);
			 				   						        			String TelephoneNumber = rs.getString(5);
			 				   						        			String BookID = rs.getString(6);
			 				   						        			String BookName = rs.getString(7);
			 				   						        			String Author = rs.getString(8);
			 				   						        			String Publisher = rs.getString(9);
			 				   						        			String PublishedDate = rs.getString(10);
			 				   						        			Float Price = rs.getFloat(11);
			 				   						        			String BookState = rs.getString(12);
			 				   						        			String BorrowDate = rs.getString(13);
			 				   						        			String ReturnDate = rs.getString(14);
			 				   						        			
				   						        			BookManager bmr = new BookManager(ReaderID,ReaderName,Sex,Identitycard,TelephoneNumber,BookID,BookName,Author,
				   						        					          Publisher,PublishedDate,Price,BookState,BorrowDate,ReturnDate);
				   						        			data2.add(bmr);
				   						             }
				   						             //�ر�
				   						             rs.close();
				   						             stmt.close();
				   						             return data2;
				   						        
				   						        
				   						        
				   						        
				   					}catch(SQLException ex) {
		 				   			      System.out.println("���ݷ����쳣");
				        	  		      ex.printStackTrace();
				        	  		      return null;
				   					}
			   }
		 		 			   
		 		 			   
		 		 			   //��֤ϵͳ��¼�ķ���
		 		 			   public static boolean validateLogin(String UserName,String password) {
		 		 				   		  try {
		 		 				   			  		  //�����Ự����
		 		 				   			          Statement stmt = conn.createStatement();
		 		 				   			          //д��ѯ��¼��SQL���
		 		 				   			          String sql = "Select * from Users where UserName='"+UserName
		 		 				   			        		                   +"' and password='"+password+"'";
		 		 				   			        //  System.out.println(sql);
		 		 				   			          ResultSet rs = stmt.executeQuery(sql);
		 		 				   			          boolean flag = true;
		 		 				   			          if(rs!=null && rs.next()) {
		 		 				   			        	  		   flag = true;
		 		 				   			          }else {
		 		 				   			        	  		   flag = false;
		 		 				   			          }
		 		 				   			          rs.close();
		 		 				   			          stmt.close();
		 		 				   			          return flag;
		 		 				   		  }catch(SQLException ex) {
	 		 							  	    System.out.println("���ݷ����쳣");
	 		 							  	    ex.printStackTrace();
	 		 							  	    return false;
	 		 							  	    
		 		 				   		 }  	    
		 		 			   }
		 		 				   		  
		 		 				  //�����û�ע��ķ���
		 		 		 		   public static boolean addnewUser(Users us) {
		 		 		 		            	    try {
		 		 		 		            	    			//�����Ự����
		 		 		 		            	    	        Statement stmt = conn.createStatement();
		 		 		 		            	    	        String sql = "Insert into Users values('" + us.UserName
		 		 		 		            	    	        		      +"','"+us.password+"')";
		 		 		 		            	  	        
		 		 		 		            	    	        System.out.println(sql);
		 		 		 		            	    	        //ִ��SQL��䣬������Ӱ�������
		 		 		 		            	    	        int r = stmt.executeUpdate(sql);
		 		 		 		            	    	        stmt.close();
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
		 	 

	


