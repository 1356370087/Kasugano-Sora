//图书类
public class Book {
	   String BookID;
	   String BookName;
	   String Author;
	   String Publisher;
	   String PublishedDate;
	   Float Price;
	   
	 Book() {
		
	}
	
	
	 Book(String BookID, String BookName, String Author, String Publisher, String PublishedDate, Float Price) {
		this.BookID = BookID;
		this.BookName = BookName;
		this.Author = Author;
		this.Publisher = Publisher;
		this.PublishedDate = PublishedDate;
		this.Price = Price;
	}
	
		//打印学生信息的方法
		public void printBookInfo() {
					System.out.println(BookID+"\t"+BookName+"\t"+Author+"\t"+Publisher+"\t"+PublishedDate+"\t"+Price);
					
		}
	
}
