import java.sql.*;
import java.util.*;
public class DBCon {
			static Connection conn;
			//静态块(最优先被加载)
			static {
						conn=JDBCon();
			}

	public static Connection JDBCon() {
		 	 try {
		 		 	  //--2加载驱动程序
		 		      Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		 		      //--3定义连接字符串
		 		      String url = "jdbc:sqlserver://localhost:1433; databaseName=BookDB";
		 		      //--4创建数据库连接
		 		      conn = DriverManager.getConnection(url,"sa","");
		 		      //System.out.println("数据库连接成功");
		 		      return conn;
		 		  //--5捕获异常
		 	 }catch(ClassNotFoundException e) {
		 		 		System.out.println("驱动程序无法加载异常");
		 		 		e.printStackTrace();
		 		 		return null;
		 	 }catch(SQLException e) {
		 		 		System.out.println("数据库无法访问异常");
		 		 		e.printStackTrace();
		 		 		return null;
		 	 }
	   }
		 	 
		 	 //查询所有图书信息的方法
		 	 public static Vector<Book> queryAllBooks(){
		 		 		try {
		 		 				//创建会话对象
		 		 				Statement stmt = conn.createStatement();
		 		 				//定义SQL语句
		 		 				String sql = "Select * from Book";
		 		 				//执行查询，返回结果集
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
		 		 					System.out.println("数据访问异常");
		 		 					ex.printStackTrace();
		 		 					return null;
		 		 		}
		 	     }
		 		 			  
		 		 		    //查询图书编号是否存在方法
		 		 				public static boolean queryBookID(String BookID) {
		 		 						try {
		 		 								  //创建会话对象
		 		 								Statement stmt = conn.createStatement();
		 		 								//定义SQL语句
		 		 								String sql = "Select * from Book where BookID='"+BookID+"'";
		 		 								//执行查询，返回结果集
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
		 		 								System.out.println("数据访问异常");
		 		 								ex.printStackTrace();
		 		 								return false;
		 		 						}
		 		 				}
		 		 				
		 		 			   //添加图书信息的方法
		 		 			   public static boolean addBook(Book bk) {
		 		 						try {
		 		 								//创建会话对象
		 		 								Statement stmt = conn.createStatement();
		 		 							    String sql = "Insert into Book values('"+ bk.BookID
		 		 							    		                        +"','"+bk.BookName+"','"+bk.Author+"','"
		 		 							    		                        +bk.Publisher+"','"+bk.PublishedDate
		 		 							    		                        +"','"+bk.Price+"')";
		 		 								                           
		 		 						    //System.out.println(sql);
		 		 							    //执行SQL语句，返回受影响的行数
		 		 							    int r = stmt.executeUpdate(sql);
		 		 							    stmt.close();
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
		 		 			   //修改图书信息的方法
		 		 			   public static boolean updateBook(String BookID,String field,String value) {
		 		 				   		   try {
		 		 				   			   		 //创建会话对象
		 		 				   			   Statement stmt = conn.createStatement();
		 		 				   			   String sql = "Update Book set "+field+"='"+value+"' where BookID='"+BookID+"'";
		 		 				   			 //  System.out.println(sql);
		 		 				   			         //执行SQL语句，返回受影响的行数
		 		 				   			   int r = stmt.executeUpdate(sql);
		 		 				   			   stmt.close();
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
		 		 			   //删除图书信息的方法
		 		 			   public static boolean deleteBook(String BookID) {
		 		 				   		   try {
		 		 				   			   		 //创建会话对象
		 		 				   			         Statement stmt = conn.createStatement();
		 		 				   			         String sql = "Delete from Book where BookID ='"+ BookID+"'";
		 		 				   			       //  System.out.println(sql);
		 		 				   			         //执行SQL语句，返回受影响的行数
		 		 				   			         int r = stmt.executeUpdate(sql);
		 		 				   			         stmt.close();
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
		 		 			   
		 		 			   //根据图书编号和图书名称模糊查询图书信息
		 		 			   public static Vector<Book> queryBooksByWhere(String field,String value){
		 		 				          try {
		 		 				        	  		//创建会话对象
		 		 				        	        Statement stmt = conn.createStatement();
		 		 				        	        //定义SQL语句
		 		 				        	        String sql = "Select * from Book where " +field+" like '%"+value+"%'";
		 		 				        	        System.out.println(sql);
		 		 				        	        //执行查询，返回结果集
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
		 		 				        	        		    //将图书对象装载集合中
		 		 				        	        		    data.add(b);
		 		 				        	        		    
		 		 				        	        }
		 		 				        	        //关闭
		 		 				        	        rs.close();
		 		 				        	        stmt.close();
		 		 				        	        return data;
		 		 				          }catch(SQLException ex) {
		 		 				        	  		System.out.println("数据访问异常");
		 		 				        	  		ex.printStackTrace();
		 		 				        	  		return null;
		 		 				          }
		 		 			   }
		 		 			   
		 		 			   //根据出版日期范围查询图书
		 		 			   public static Vector<Book> queryBooksByPublishedDate(String PublishedDate1, String PublishedDate2){
		 		 				   				try {
		 		 				   						  //创建会话对象
		 		 				   					      Statement stmt = conn.createStatement();
		 		 				   					      //定义SQL语句
		 		 				   					      String sql = "Select * from Book where PublishedDate>='"+PublishedDate1
		 		 				   					    		                    +"' and PublishedDate<='"+PublishedDate2+"'";
		 		 				   					      System.out.println(sql);
		 		 				   					//执行查询，返回结果集
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
	 		 				        	        		    //将图书对象装载集合中
	 		 				        	        		    data.add(b);
		 		 				   					      
		 		 				   				      }
				 		 				        	        //关闭
				 		 				        	        rs.close();
				 		 				        	        stmt.close();
				 		 				        	        return data;
				 		 				        	        
		 		 			   }catch(SQLException ex) {
 				        	  		System.out.println("数据访问异常");
 				        	  		ex.printStackTrace();
 				        	  		return null;
		 		 				   				
		 		 			   }
		 		 			   
		 		 			 }
		 		 			   
		 		 			   public static Vector<Book> queryBooksByPrice(String Price1, String Price2){
		 		 				   			try {
		 		 				   					 //创建会话对象
		 		 				   				     Statement stmt = conn.createStatement();
		 		 				   				     String sql = "Select * from Book where Price>='"+Price1
		 		 				   				    		        +"' and Price<='"+Price2+"'";
		 		 				   				     System.out.println(sql);
		 		 				   				     //执行查询，返回结果集
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
		 				        	        		 //将图书对象装载集合中
		 				        	        		 data.add(b);
		 		 				   			}
			 		 				        	     rs.close();
			 		 				        	     stmt.close();
			 		 				        	     return data;
		 		 			   }catch(SQLException ex) {
				        	  		System.out.println("数据访问异常");
				        	  		ex.printStackTrace();
				        	  		return null;
				        	  		
		 		 		} 	  		
		 		 			 }
		 		 			   
		 		 			   //修改密码的方法
		 		 			   public static boolean changepassword(String UserName, String newpassword) {
		 		 				   			try {
		 		 				   					   //创建会话对象
		 		 				   				       Statement stmt = conn.createStatement();
		 		 				   				       String sql = "Update Users set password='"+newpassword
		 		 				   				    		        +"' where UserName='"+UserName+"'";
		 		 				   				     //  System.out.println(sql);
		 		 				   				       //执行SQL，返回受影响的行数
		 		 				   				       int r = stmt.executeUpdate(sql);
		 		 				   				       stmt.close();
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
		 		 			   
		 		 			   public static Vector<BookManager> queryBookMangerByBookID(String BookID5){
		 		 				   					try {
		 		 				   								//创建会话对象
		 		 				   						        Statement stmt = conn.createStatement();
		 		 				   						        String sql = "Select b.ReaderID,b.ReaderName,sex,Identitycard,TelephoneNumber,a.BookID,a.BooKName,Author,Publisher,PublishedDate,Price,BookState,BorrowDate,ReturnDate"+" from Book a,Reader b,Borrow c "+
		 		 				   						        	         "where a.BookID=c.BookID"+" and c.ReaderID=b.ReaderID"+
		 		 				   						        	         " and a.BookID='"+BookID5+"'";
		 		 				   						        System.out.println(sql);
		 		 				   						        //执行查询，返回结果集
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
		 		 				   						             //关闭
		 		 				   						             rs.close();
		 		 				   						             stmt.close();
		 		 				   						             return data1;
		 		 				   						        
		 		 				   						        
		 		 				   						        
		 		 				   						        
		 		 				   					}catch(SQLException ex) {
				 		 				   			      System.out.println("数据访问异常");
								        	  		      ex.printStackTrace();
								        	  		      return null;
		 		 				   					}
		 		 			   }
		 		 			   
		 	
		 		 			 public static Vector<BookManager> queryBookMangerByReaderNo(String ReaderID2){
				   					try {
				   								//创建会话对象
				   						        Statement stmt = conn.createStatement();
				   						        String sql = "Select b.ReaderID,b.ReaderName,Sex,Identitycard,TelephoneNumber,a.BookID,a.BooKName,Author,Publisher,PublishedDate,Price,BookState,BorrowDate,ReturnDate"+" from Book a,Reader b,Borrow c "+
				   						        	         "where a.BookID=c.BookID"+" and c.ReaderID=b.ReaderID"+
				   						        	         " and c.ReaderID='"+ReaderID2+"'";
				   						        System.out.println(sql);
				   						        //执行查询，返回结果集
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
				   						             //关闭
				   						             rs.close();
				   						             stmt.close();
				   						             return data2;
				   						        
				   						        
				   						        
				   						        
				   					}catch(SQLException ex) {
		 				   			      System.out.println("数据访问异常");
				        	  		      ex.printStackTrace();
				        	  		      return null;
				   					}
			   }
		 		 			   
		 		 			   
		 		 			   //验证系统登录的方法
		 		 			   public static boolean validateLogin(String UserName,String password) {
		 		 				   		  try {
		 		 				   			  		  //创建会话对象
		 		 				   			          Statement stmt = conn.createStatement();
		 		 				   			          //写查询登录的SQL语句
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
	 		 							  	    System.out.println("数据访问异常");
	 		 							  	    ex.printStackTrace();
	 		 							  	    return false;
	 		 							  	    
		 		 				   		 }  	    
		 		 			   }
		 		 				   		  
		 		 				  //个人用户注册的方法
		 		 		 		   public static boolean addnewUser(Users us) {
		 		 		 		            	    try {
		 		 		 		            	    			//创建会话对象
		 		 		 		            	    	        Statement stmt = conn.createStatement();
		 		 		 		            	    	        String sql = "Insert into Users values('" + us.UserName
		 		 		 		            	    	        		      +"','"+us.password+"')";
		 		 		 		            	  	        
		 		 		 		            	    	        System.out.println(sql);
		 		 		 		            	    	        //执行SQL语句，返回受影响的行数
		 		 		 		            	    	        int r = stmt.executeUpdate(sql);
		 		 		 		            	    	        stmt.close();
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
		 	 

	


