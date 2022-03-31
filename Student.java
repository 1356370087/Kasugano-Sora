//—ß…˙¿‡
public class Student {
	     String stuId;
	     String stuName;
	     String sex;
	     String birth;
	     
	     

	public Student() {
			
		}



	public Student(String stuId, String stuName, String sex, String birth) {
			
			this.stuId = stuId;
			this.stuName = stuName;
			this.sex = sex;
			this.birth = birth;
		}

	public void printStuInfo() {
		    System.out.println(stuId+"\t"+stuName+"\t"+sex+"\t"+birth);
	
	}

}
