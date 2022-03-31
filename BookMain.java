import java.util.Scanner;
import java.util.Vector;

public class BookMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String UserName;
		String password;
			
				System.out.println("************��ӭʹ��ͼ�����ϵͳ************");
				do {
				System.out.print("�Ƿ�Ϊ���û�?(Y/N) : ");
				String answer4 = sc.next();
				if(answer4.equals("Y")||answer4.equals("y")) {
					   System.out.print("���������û����û�����");
					   UserName = sc.next();
					   System.out.print("���������û������룺");
					   password = sc.next();
					   Users us = new Users(UserName,password);
					   if(DBCon.addnewUser(us)) {
						   System.out.println("������û��ɹ�!!!");
					   }else {
						   System.out.println("������û�ʧ��!!!");
					   }
					   break;
				}else if(answer4.equals("N")||answer4.equals("n")){
                           break;
					       
				}
				
				}while(true);
				
				
				
				
		 do {
				System.out.print("�������û�����");
				UserName = sc.next();
				System.out.print("���������룺");
				password = sc.next();
				//����DBCon1����֤��½�ķ���
				if(DBCon.validateLogin(UserName,password)) {
					System.out.println("��½�ɹ�!!!");
					break; //�Ƴ�do-whileѭ�����������˵�
					
				}else {
					System.out.println("�û�����������󣬵�½ʧ��!!! ����������!!!"+"\r");
				}
				
			}while(true);
		
		do {
				//��ӡϵͳ�����˵�
				System.out.println("************ͼ�����ϵͳ���˵�************");
				System.out.println("1. ͼ��������Ϣ���");
				System.out.println("2. ���ͼ����Ϣ");
				System.out.println("3. ����ͼ�����޸�ͼ����Ϣ");
				System.out.println("4. ����ͼ����ɾ��ͼ����Ϣ");
				System.out.println("5. ��ѯͼ����Ϣ");
				System.out.println("6. �޸�����");
				System.out.println("7. ����ͼ���Ŷ�����Ӳ�ѯͼ����Ϣ");
				System.out.println("8. ���ݶ��߱�Ŷ�����Ӳ�ѯͼ����Ϣ");
				System.out.println("0. �˳�ϵͳ");
				System.out.println("*****************************************");
				System.out.print("������Ҫѡ��Ĳ˵���ţ�");
				
				int menu = sc.nextInt();
				//����DBCon1���ѯ����ѧ����Ϣ�ķ���
				Vector<Book> data = DBCon.queryAllBooks();
				switch(menu) {
				         case 1:
					        	 	 //���ô�ӡ��Ϣ�ķ���
				        	         printInfo(data);
					        	     break;
				         
				         case 2:
				        	 	 do {
				        	 		 	System.out.println("������Ҫ��ӵ�ͼ����Ϣ��");
				        	 		 	System.out.print("ͼ���ţ�");
				        	 		 	String BookID = sc.next();
				        	 		 	System.out.print("ͼ�����ƣ�");
				        	 		 	String BookName = sc.next();
				        	 		 	System.out.print("���ߣ�");
				        	 		 	String Author = sc.next();
				        	 		 	System.out.print("�����磺");
				        	 		 	String Publisher = sc.next();
				        	 		 	System.out.print("�������ڣ�");
				        	 		 	String PublishedDate = sc.next();
				        	 		 	System.out.print("�۸�");
				        	 		 	Float Price = sc.nextFloat();
				        	 		 	if(BookID!=null&&BookID.length()>0
				        	 		 			     &&BookName!=null&&BookName.length()>0
				        	 		 			     &&Author!=null&&Author.length()>0
				        	 		 			     &&Publisher!=null&&Publisher.length()>0
				        	 		 			     &&PublishedDate!=null&&PublishedDate.length()>0
				        	 		 			     &&Price!=null&&Price>0) {
				        	 		 		
				        	 		 		Book newBook = new Book(BookID,BookName,Author,Publisher,PublishedDate,Price);
				        	 		 	//����DBCon����鼮��Ϣ�ķ���
				        	 		 	if(DBCon.addBook(newBook)) {
				        	 		 			  System.out.println("���ͼ����Ϣ�ɹ�!");
				        	 		 	}else {
				        	 		 			  System.out.println("���ͼ����Ϣʧ��!");
				        	 		 	}
				        	 		 		break;//�˳�do-whileѭ��
				        	 		 
				        	 	 }else {
				        	 		 	    System.out.println("������Ч");
				        	 	 }
				          }while(true);
				           break;//�˳�switch
				           
				         case 3:
				        	       
				        	      System.out.print("������Ҫ�޸�ͼ���ͼ���ţ�"+"\n"+"ͼ���ţ�");
				        	      String BookID = sc.next();
				        	      
				        	      do {
				        	      System.out.println("������Ҫ�޸ĸ�ͼ����ĸ���Ϣ��");
				        	      System.out.println("a.ͼ������ b.���� c.������ d.�������� e.�۸�");
				        	        
				        	      System.out.print("�������ϵ���Ϣ��� (a/b/c/d/e) : ");
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
				        	      System.out.print("�µ�ͼ�����ƣ�");
				        	      String value = sc.next();
						        	      if(value!=null&& value.length()>0) {
						        	    	  		 if(DBCon.queryBookID(BookID)) {
				        	    	  			 	   //����DBCon�޸�ѧ����Ϣ�ķ���
				        	    	  			       if(DBCon.updateBook(BookID,field,value)) {
				        	    	  			    	   		System.out.println("�޸�ͼ����Ϣ�ɹ�!");
				        	    	  			       }else {
				        	    	  			    	   		System.out.println("�޸�ͼ����Ϣʧ��!");
				        	    	  			       }
				        	    	  		 }
				        	      }
						        	      System.out.print("����Ҫ�����޸ĸ�ͼ�����Ϣ��(Y/N)? : ");
						        	      String answer = sc.next();
						        	      if(answer.equals("n")||answer.equals("N")) {
						        	    	  		   break;//�˳�do-whileѭ��
						        	      }
						        	      
				        	       }while(true);
				        	       break;//�˳�case 3
				        	       
				         case 4:
				        	 			  System.out.print("������Ҫɾ��ͼ���ͼ���ţ�"+"\r"+"ͼ���ţ�");
				        	 			  String BookID1 = sc.next();
				        	 			  System.out.print("���Ƿ����Ҫɾ����ͼ���ͼ����Ϣ?(y/n): ");
				        	 			  String answer = sc.next();
				        	 			  if(answer.equals("y")||answer.equals("Y")) {
				        	 				  		 if(DBCon.queryBookID(BookID1)) {
				        	 				  			 	  //����DBCon��ɾ��ͼ����Ϣ�ķ���
				        	 				  			      if(DBCon.deleteBook(BookID1)) {
				        	 				  			    	  	    System.out.println("ɾ��ͼ����Ϣ�ɹ�!!!");
				        	 				  			      }else {
				        	 				  			    	  		System.out.println("ɾ��ͼ����Ϣʧ��!!!");
				        	 				  			      }
				        	 				  		 }else {
				        	 				  			 		  System.out.println("ͼ���Ų�����");
				        	 				  		 }
				        	 			  }
				        	 			  break;
				        	 			  
				         case 5:
				        	      do {
				        	 		   	System.out.println("������Ҫ����ͼ����ĸ���Ϣ����ѯͼ����Ϣ��");
				        	 		   	System.out.println("a.ͼ���� b.ͼ������ c.���� d.������ e.�������� f.�۸�");
				        	 		   	System.out.print("�������ϵ���Ϣ��� (a/b/c/d/e/f) : ");
				        	 		   	String letter = sc.next();
				        	 		   	if(letter.equals("a")) {
				        	 		   	       
		        	 		   	    	       	   System.out.print("ͼ����: ");
		        	 		   	    	       	   String BookID2 = sc.next();
		        	 		   	    	       	   //����DBCon����ݵ���������ѯ��Ϣ�ķ���
		        	 		   	    	       	   data = DBCon.queryBooksByWhere("BookID",BookID2);
		        	 		   	    	           printInfo(data);
		        	 		   	                       
		        	 		   	       
		        	 		   	                  }else if(letter.equals("b")) { 
			        	 		   	                    System.out.print("ͼ�����ƣ�");
			        	 		   	    	   			String BookName1 = sc.next();
			        	 		   	    	   			//����DBCon����ݵ���������ѯ��Ϣ�ķ���
			        	 		   	    	   			data = DBCon.queryBooksByWhere("BookName",BookName1);
			        	 		   	    	   		    printInfo(data);
		        	 		   	    	   		    
		        	 		   	                  }else if(letter.equals("c")) {
		        	 		   	                	  System.out.print("�������ƣ�");
		        	 		   	                	  String Author1 = sc.next();
		        	 		   	                     //����DBCon����ݵ���������ѯ��Ϣ�ķ���
		        	 		   	                	  data = DBCon.queryBooksByWhere("Author", Author1);
		        	 		   	                	  printInfo(data);
		        	 		   	                	  
		        	 		   	                  }else if(letter.equals("d")) {
		        	 		   	                	  	 System.out.print("���������ƣ�");
		        	 		   	                	  	 String Publisher1 = sc.next();
		        	 		   	                	     //����DBCon����ݵ���������ѯ��Ϣ�ķ���
		        	 		   	                	     data = DBCon.queryBooksByWhere("Publisher",Publisher1);
		        	 		   	                	     printInfo(data);
		        	 		   	                	     
		        	 		   	                  }else if(letter.equals("e")) {
				        	 		   	                	System.out.print("�������ѯ����ʼ����: ");
				      				        	       	  String PublishedDate1 = sc.next();
				      				        	       	  System.out.print("�������ѯ�Ľ������ڣ�");
				      				        	       	  String PublishedDate2 = sc.next();
				      				        	       	  //����DBCon���ݳ������ڲ�ѯͼ����Ϣ�ķ���
				      				        	       	  data = DBCon.queryBooksByPublishedDate(PublishedDate1, PublishedDate2);
				      				        	       	  printInfo(data);
				      				        	       	  
		        	 		   	                  }else if(letter.equals("f")) {
		        	 		   	                	  		  System.out.print("�������ѯ����ʼ�۸�");
		        	 		   	                	  		  String Price1 = sc.next();
		        	 		   	                	  		  System.out.print("�������ѯ�Ľ����۸�");
		        	 		   	                	  		  String Price2 = sc.next();
		        	 		   	                	  	      data = DBCon.queryBooksByPrice(Price1, Price2);
		        	 		   	                	  	      printInfo(data);
		        	 		   	                  }
									        	 		   
	
		        	 		   	                  System.out.print("����Ҫ��ѯͼ�����Ϣ��(Y/N)? : ");
		        	 		   	                  String answer3 = sc.next();
		        	 		   	                  if(answer3.equals("n")||answer3.equals("N")) {
		        	 		   	                	  		break;//�˳�do-whileѭ��
		        	 		   	                  }
		        	 		   	                  
		        	                               }while(true);       
		        	 		   	    	       	   break;//�˳�case5
				 	       	   
				         case 6:
				        	 		//�޸�����
				        	        do {
				        	        	  	  System.out.print("����������룺");
				        	        	  	  String oldpassword = sc.next();
				        	        	  	  
				        	        	  	  
				        	        	  	  //�жϾ������Ƿ���ȷ
				        	        	  	  if(oldpassword.equals(password)) {
				        	        	  		  
				        	        	  		System.out.print("�����������룺");
					        	        	  	String newpassword = sc.next();
					        	        	  	System.out.print("���ٴ�����������: ");
					        	        	  	String newpassword2 = sc.next();
				        	        	  		  
					        	        	  	if(newpassword.equals(newpassword2)) {
					        	        	  		    DBCon.changepassword(UserName, newpassword);
					        	        	  		    System.out.println("�����޸ĳɹ�!!!");
					        	        	  		    break;
					        	        	  	
					        	        	  	}else {
					        	        	  		        System.out.println("�����벻һ��!!!");
					        	        	  		        
					        	        	  		        
					        	        	  	}
					        	        	  	
				        	        	  		    
				        	        	  	  }else {
				        	        	  		  		System.out.println("���������!!!");
				        	        	  		  		
				        	        	  	  }
				        	        	  		       
				        	        	  	    
				        	        	  	  
				        	        	  	  
				        	        }while(true);
				        	        break;//�˳�case6
				        	        
				         case 7:
				        	 		do {
				        	 				System.out.println("������Ӳ�ѯ");
				        	 				System.out.print("������Ҫ��ѯͼ�������ͼ���ţ�");
				        	 				String BookID5 = sc.next();
				        	 				
				        	 				Vector<BookManager> data1 = DBCon.queryBookMangerByBookID(BookID5);
				        	 				System.out.println("���߱��\t��������\t�Ա�\t\t���֤��\t\t�绰����\t\tͼ����\t   ͼ������\t  ���� \t������\t\t��������\t\t�۸�\tͼ��״̬\t��������\t\t�黹����");
				        	 				for(int i=0;i<data1.size();i++) {
				        	 								data1.get(i).printBookManager();
				        	 				}
				        	 				System.out.print("�Ƿ�Ҫ������ѯͼ��������Ϣ(y/n)? : ");
				        	 				String answer4 = sc.next();
				        	 				if(answer4.equals("n")||answer4.equals("N")) {
				        	 					        break;
				        	 				}
				        	 		}while(true);
				        	 		break;
				        	 		
				         case 8:
				        	 do {
		        	 				System.out.println("������Ӳ�ѯ");
		        	 				System.out.print("������Ҫ��ѯͼ������Ķ��߱�ţ�");
		        	 				String ReaderNo2 = sc.next();
		        	 				
		        	 				Vector<BookManager> data2 = DBCon.queryBookMangerByReaderNo(ReaderNo2);
		        	 				System.out.println("���߱��\t��������\t�Ա�\t\t���֤��\t\t�绰����\t\tͼ����\t   ͼ������\t  ���� \t������\t\t��������\t\t�۸�\tͼ��״̬\t��������\t\t�黹����");
		        	 				for(int i=0;i<data2.size();i++) {
		        	 								data2.get(i).printBookManager();
		        	 				}
		        	 				System.out.print("�Ƿ�Ҫ������ѯͼ��������Ϣ(y/n)? : ");
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
								  //ѭ����ӡͼ����Ϣ
							   	   System.out.println("ͼ����\tͼ������\t\t ����\t  ������\t\t ��������\t �۸�");
							   	   System.out.println("==========================================================================");
							   	   for(Book b:data) {
							   		   		b.printBookInfo();
							   	   }
							}
								
            }
	
	
   
