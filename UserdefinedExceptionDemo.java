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
	      		    	     throw new IllegalAgeException("�Ƿ����������쳣");
	      		    }else {
	      		    		  System.out.println("����Ϸ����������");
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
