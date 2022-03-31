
public class BookManager {
	String ReaderID;
	String ReaderName;
	String Sex;
	String Identitycard;
	String TelephoneNumber;
	String BookID;
	String BookName;
	String Author;
	String Publisher;
	String PublishedDate;
	Float Price;
	String BookState;
	String BorrowDate;
	String ReturnDate;

	
	
	 BookManager() {
		
	}
	
	
	 BookManager(String ReaderID, String ReaderName, String Sex,String Identitycard, String TelephoneNumber,String bookID, String bookName, String author,
			String publisher, String publishedDate, Float price,String BookState, String borrowDate, String returnDate
			    ) {
		
		this.ReaderID = ReaderID;
		this.ReaderName = ReaderName;
		this.Sex = Sex;
		this.Identitycard = Identitycard;
		this.TelephoneNumber = TelephoneNumber;
		this.BookID = bookID;
		this.BookName = bookName;
		this.Author = author;
		this.Publisher = publisher;
		this.PublishedDate = publishedDate;
		this.Price = price;
		this.BookState = BookState;
		this.BorrowDate = borrowDate;
		this.ReturnDate = returnDate;
		
	}



	public void printBookManager() {
					System.out.println(ReaderID+"\t"+ReaderName+"\t"+Sex+"\t"+Identitycard+"\t"+TelephoneNumber+"\t"+BookID+"\t"+BookName+"\t"+Author+"\t"+Publisher+"\t"+
							           PublishedDate+"\t"+Price+"\t"+BookState+"\t"+BorrowDate+"\t"+ReturnDate);
	}
	

}
