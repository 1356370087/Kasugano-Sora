class IllegalAgeException extends Exception{
		IllegalAgeException(){
			
		}
	     IllegalAgeException(String message){
	    	 super(message);
	     }
	
}
class Bank{
	      	void approveLoan(int age)throws IllegalAgeException{
	      		    if(age<18||age>=65) {
	      		    	     throw new IllegalAgeException("非法贷款年龄异常");
	      		    }else {
	      		    		  System.out.println("年龄合法，允许贷款");
	      		    }
	      	}
}
public class UserdefinedExceptionDemo {

	public static void main(String[] args) {
		Bank bank = new Bank();
		try {
					//bank.approveLoan(30);
					bank.approveLoan(70);
		}catch(IllegalAgeException ex) {
				ex.printStackTrace();
		}

	}

}
