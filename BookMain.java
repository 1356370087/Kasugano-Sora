import java.util.Scanner;
import java.util.Vector;

public class BookMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String UserName;
		String password;
			
				System.out.println("************欢迎使用图书管理系统************");
				do {
				System.out.print("是否为新用户?(Y/N) : ");
				String answer4 = sc.next();
				if(answer4.equals("Y")||answer4.equals("y")) {
					   System.out.print("请输入新用户的用户名：");
					   UserName = sc.next();
					   System.out.print("请输入新用户的密码：");
					   password = sc.next();
					   Users us = new Users(UserName,password);
					   if(DBCon.addnewUser(us)) {
						   System.out.println("添加新用户成功!!!");
					   }else {
						   System.out.println("添加新用户失败!!!");
					   }
					   break;
				}else if(answer4.equals("N")||answer4.equals("n")){
                           break;
					       
				}
				
				}while(true);
				
				
				
				
		 do {
				System.out.print("请输入用户名：");
				UserName = sc.next();
				System.out.print("请输入密码：");
				password = sc.next();
				//调用DBCon1的验证登陆的方法
				if(DBCon.validateLogin(UserName,password)) {
					System.out.println("登陆成功!!!");
					break; //推出do-while循环，进入主菜单
					
				}else {
					System.out.println("用户名或密码错误，登陆失败!!! 请重新输入!!!"+"\r");
				}
				
			}while(true);
		
		do {
				//打印系统的主菜单
				System.out.println("************图书管理系统主菜单************");
				System.out.println("1. 图书完整信息输出");
				System.out.println("2. 添加图书信息");
				System.out.println("3. 根据图书编号修改图书信息");
				System.out.println("4. 根据图书编号删除图书信息");
				System.out.println("5. 查询图书信息");
				System.out.println("6. 修改密码");
				System.out.println("7. 根据图书编号多表连接查询图书信息");
				System.out.println("8. 根据读者编号多表连接查询图书信息");
				System.out.println("0. 退出系统");
				System.out.println("*****************************************");
				System.out.print("请输入要选择的菜单编号：");
				
				int menu = sc.nextInt();
				//调用DBCon1里查询所有学生信息的方法
				Vector<Book> data = DBCon.queryAllBooks();
				switch(menu) {
				         case 1:
					        	 	 //调用打印信息的方法
				        	         printInfo(data);
					        	     break;
				         
				         case 2:
				        	 	 do {
				        	 		 	System.out.println("请输入要添加的图书信息：");
				        	 		 	System.out.print("图书编号：");
				        	 		 	String BookID = sc.next();
				        	 		 	System.out.print("图书名称：");
				        	 		 	String BookName = sc.next();
				        	 		 	System.out.print("作者：");
				        	 		 	String Author = sc.next();
				        	 		 	System.out.print("出版社：");
				        	 		 	String Publisher = sc.next();
				        	 		 	System.out.print("出版日期：");
				        	 		 	String PublishedDate = sc.next();
				        	 		 	System.out.print("价格：");
				        	 		 	Float Price = sc.nextFloat();
				        	 		 	if(BookID!=null&&BookID.length()>0
				        	 		 			     &&BookName!=null&&BookName.length()>0
				        	 		 			     &&Author!=null&&Author.length()>0
				        	 		 			     &&Publisher!=null&&Publisher.length()>0
				        	 		 			     &&PublishedDate!=null&&PublishedDate.length()>0
				        	 		 			     &&Price!=null&&Price>0) {
				        	 		 		
				        	 		 		Book newBook = new Book(BookID,BookName,Author,Publisher,PublishedDate,Price);
				        	 		 	//调用DBCon添加书籍信息的方法
				        	 		 	if(DBCon.addBook(newBook)) {
				        	 		 			  System.out.println("添加图书信息成功!");
				        	 		 	}else {
				        	 		 			  System.out.println("添加图书信息失败!");
				        	 		 	}
				        	 		 		break;//退出do-while循环
				        	 		 
				        	 	 }else {
				        	 		 	    System.out.println("数据无效");
				        	 	 }
				          }while(true);
				           break;//退出switch
				           
				         case 3:
				        	       
				        	      System.out.print("请输入要修改图书的图书编号："+"\n"+"图书编号：");
				        	      String BookID = sc.next();
				        	      
				        	      do {
				        	      System.out.println("请输入要修改该图书的哪个信息：");
				        	      System.out.println("a.图书名称 b.作者 c.出版社 d.出版日期 e.价格");
				        	        
				        	      System.out.print("输入以上的信息编号 (a/b/c/d/e) : ");
				        	      String letter = sc.next();
				        	      String field="";
				        	      if(letter.equals("a")) {
				        	    	  		          field = "BookName";	
				        	      }else if(letter.equals("b")) {
				        	    	  			      field = "Author";
				        	      }else if(letter.equals("c")) {
				        	    	  				  field = "Publisher";
				        	      }else if(letter.equals("d")) {
				        	    	  				  field = "PublishedDate";
				        	      }else if(letter.equals("e")) {
				        	    	  				  field = "Price";
				        	      }
				        	      System.out.print("新的图书名称：");
				        	      String value = sc.next();
						        	      if(value!=null&& value.length()>0) {
						        	    	  		 if(DBCon.queryBookID(BookID)) {
				        	    	  			 	   //调用DBCon修改学生信息的方法
				        	    	  			       if(DBCon.updateBook(BookID,field,value)) {
				        	    	  			    	   		System.out.println("修改图书信息成功!");
				        	    	  			       }else {
				        	    	  			    	   		System.out.println("修改图书信息失败!");
				        	    	  			       }
				        	    	  		 }
				        	      }
						        	      System.out.print("还需要继续修改该图书的信息吗(Y/N)? : ");
						        	      String answer = sc.next();
						        	      if(answer.equals("n")||answer.equals("N")) {
						        	    	  		   break;//退出do-while循环
						        	      }
						        	      
				        	       }while(true);
				        	       break;//退出case 3
				        	       
				         case 4:
				        	 			  System.out.print("请输入要删除图书的图书编号："+"\r"+"图书编号：");
				        	 			  String BookID1 = sc.next();
				        	 			  System.out.print("你是否真的要删除该图书的图书信息?(y/n): ");
				        	 			  String answer = sc.next();
				        	 			  if(answer.equals("y")||answer.equals("Y")) {
				        	 				  		 if(DBCon.queryBookID(BookID1)) {
				        	 				  			 	  //调用DBCon的删除图书信息的方法
				        	 				  			      if(DBCon.deleteBook(BookID1)) {
				        	 				  			    	  	    System.out.println("删除图书信息成功!!!");
				        	 				  			      }else {
				        	 				  			    	  		System.out.println("删除图书信息失败!!!");
				        	 				  			      }
				        	 				  		 }else {
				        	 				  			 		  System.out.println("图书编号不存在");
				        	 				  		 }
				        	 			  }
				        	 			  break;
				        	 			  
				         case 5:
				        	      do {
				        	 		   	System.out.println("请输入要根据图书的哪个信息来查询图书信息：");
				        	 		   	System.out.println("a.图书编号 b.图书名称 c.作者 d.出版社 e.出版日期 f.价格");
				        	 		   	System.out.print("输入以上的信息编号 (a/b/c/d/e/f) : ");
				        	 		   	String letter = sc.next();
				        	 		   	if(letter.equals("a")) {
				        	 		   	       
		        	 		   	    	       	   System.out.print("图书编号: ");
		        	 		   	    	       	   String BookID2 = sc.next();
		        	 		   	    	       	   //调用DBCon里根据单个条件查询信息的方法
		        	 		   	    	       	   data = DBCon.queryBooksByWhere("BookID",BookID2);
		        	 		   	    	           printInfo(data);
		        	 		   	                       
		        	 		   	       
		        	 		   	                  }else if(letter.equals("b")) { 
			        	 		   	                    System.out.print("图书名称：");
			        	 		   	    	   			String BookName1 = sc.next();
			        	 		   	    	   			//调用DBCon里根据单个条件查询信息的方法
			        	 		   	    	   			data = DBCon.queryBooksByWhere("BookName",BookName1);
			        	 		   	    	   		    printInfo(data);
		        	 		   	    	   		    
		        	 		   	                  }else if(letter.equals("c")) {
		        	 		   	                	  System.out.print("作者名称：");
		        	 		   	                	  String Author1 = sc.next();
		        	 		   	                     //调用DBCon里根据单个条件查询信息的方法
		        	 		   	                	  data = DBCon.queryBooksByWhere("Author", Author1);
		        	 		   	                	  printInfo(data);
		        	 		   	                	  
		        	 		   	                  }else if(letter.equals("d")) {
		        	 		   	                	  	 System.out.print("出版社名称：");
		        	 		   	                	  	 String Publisher1 = sc.next();
		        	 		   	                	     //调用DBCon里根据单个条件查询信息的方法
		        	 		   	                	     data = DBCon.queryBooksByWhere("Publisher",Publisher1);
		        	 		   	                	     printInfo(data);
		        	 		   	                	     
		        	 		   	                  }else if(letter.equals("e")) {
				        	 		   	                	System.out.print("请输入查询的起始日期: ");
				      				        	       	  String PublishedDate1 = sc.next();
				      				        	       	  System.out.print("请输入查询的结束日期：");
				      				        	       	  String PublishedDate2 = sc.next();
				      				        	       	  //调用DBCon根据出版日期查询图书信息的方法
				      				        	       	  data = DBCon.queryBooksByPublishedDate(PublishedDate1, PublishedDate2);
				      				        	       	  printInfo(data);
				      				        	       	  
		        	 		   	                  }else if(letter.equals("f")) {
		        	 		   	                	  		  System.out.print("请输入查询的起始价格：");
		        	 		   	                	  		  String Price1 = sc.next();
		        	 		   	                	  		  System.out.print("请输入查询的结束价格：");
		        	 		   	                	  		  String Price2 = sc.next();
		        	 		   	                	  	      data = DBCon.queryBooksByPrice(Price1, Price2);
		        	 		   	                	  	      printInfo(data);
		        	 		   	                  }
									        	 		   
	
		        	 		   	                  System.out.print("还需要查询图书的信息吗(Y/N)? : ");
		        	 		   	                  String answer3 = sc.next();
		        	 		   	                  if(answer3.equals("n")||answer3.equals("N")) {
		        	 		   	                	  		break;//退出do-while循环
		        	 		   	                  }
		        	 		   	                  
		        	                               }while(true);       
		        	 		   	    	       	   break;//退出case5
				 	       	   
				         case 6:
				        	 		//修改密码
				        	        do {
				        	        	  	  System.out.print("请输入旧密码：");
				        	        	  	  String oldpassword = sc.next();
				        	        	  	  
				        	        	  	  
				        	        	  	  //判断旧密码是否正确
				        	        	  	  if(oldpassword.equals(password)) {
				        	        	  		  
				        	        	  		System.out.print("请输入新密码：");
					        	        	  	String newpassword = sc.next();
					        	        	  	System.out.print("请再次输入新密码: ");
					        	        	  	String newpassword2 = sc.next();
				        	        	  		  
					        	        	  	if(newpassword.equals(newpassword2)) {
					        	        	  		    DBCon.changepassword(UserName, newpassword);
					        	        	  		    System.out.println("密码修改成功!!!");
					        	        	  		    break;
					        	        	  	
					        	        	  	}else {
					        	        	  		        System.out.println("新密码不一致!!!");
					        	        	  		        
					        	        	  		        
					        	        	  	}
					        	        	  	
				        	        	  		    
				        	        	  	  }else {
				        	        	  		  		System.out.println("旧密码错误!!!");
				        	        	  		  		
				        	        	  	  }
				        	        	  		       
				        	        	  	    
				        	        	  	  
				        	        	  	  
				        	        }while(true);
				        	        break;//退出case6
				        	        
				         case 7:
				        	 		do {
				        	 				System.out.println("多表连接查询");
				        	 				System.out.print("请输入要查询图书总类的图书编号：");
				        	 				String BookID5 = sc.next();
				        	 				
				        	 				Vector<BookManager> data1 = DBCon.queryBookMangerByBookID(BookID5);
				        	 				System.out.println("读者编号\t读者姓名\t性别\t\t身份证号\t\t电话号码\t\t图书编号\t   图书名称\t  作者 \t出版社\t\t出版日期\t\t价格\t图书状态\t借阅日期\t\t归还日期");
				        	 				for(int i=0;i<data1.size();i++) {
				        	 								data1.get(i).printBookManager();
				        	 				}
				        	 				System.out.print("是否还要继续查询图书总类信息(y/n)? : ");
				        	 				String answer4 = sc.next();
				        	 				if(answer4.equals("n")||answer4.equals("N")) {
				        	 					        break;
				        	 				}
				        	 		}while(true);
				        	 		break;
				        	 		
				         case 8:
				        	 do {
		        	 				System.out.println("多表连接查询");
		        	 				System.out.print("请输入要查询图书总类的读者编号：");
		        	 				String ReaderNo2 = sc.next();
		        	 				
		        	 				Vector<BookManager> data2 = DBCon.queryBookMangerByReaderNo(ReaderNo2);
		        	 				System.out.println("读者编号\t读者姓名\t性别\t\t身份证号\t\t电话号码\t\t图书编号\t   图书名称\t  作者 \t出版社\t\t出版日期\t\t价格\t图书状态\t借阅日期\t\t归还日期");
		        	 				for(int i=0;i<data2.size();i++) {
		        	 								data2.get(i).printBookManager();
		        	 				}
		        	 				System.out.print("是否还要继续查询图书总类信息(y/n)? : ");
		        	 				String answer4 = sc.next();
		        	 				if(answer4.equals("n")||answer4.equals("N")) {
		        	 					        break;
		        	 				}
		        	 		}while(true);
		        	 		break;
				        	 
				        	   
				        	 
				         case 0:
				        	 				System.exit(0);
				
				
				        	        		
					}	    	
				
	          
              	  }while(true);
				
	      }
	
					      public static void printInfo(Vector<Book> data) {
								  //循环打印图书信息
							   	   System.out.println("图书编号\t图书名称\t\t 作者\t  出版社\t\t 出版日期\t 价格");
							   	   System.out.println("==========================================================================");
							   	   for(Book b:data) {
							   		   		b.printBookInfo();
							   	   }
							}
								
            }
	
	
   
