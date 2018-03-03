public class TestMailUser{
	public static void main(String... args){
		
		/*GET mailUser*/
		test1();
		test2();
		test3();
	
	}
	static void test1(){
	MailUser mu=new MailUser();
	mu=MailUser.getUser("jyoti","goel",32,"des");
	System.out.println(mu.userId);
		System.out.println(mu.userName);
		System.out.println(mu.password);
		System.out.println(mu.phoneNo);
		System.out.println(mu.emailId);
	System.out.println(mu.createInsertQuery());

	}
	static void test2(){
	MailUser mu=new MailUser();
	mu=MailUser.getUser("i","goel",32,"des");
	System.out.println(mu.userId);
		System.out.println(mu.userName);
		System.out.println(mu.password);
		System.out.println(mu.phoneNo);
		System.out.println(mu.emailId);

	}
	static void test3(){
	MailUser mu=new MailUser();
	mu=MailUser.getUser("jyoti","goel",32,"des");
	System.out.println(mu.userId);
		System.out.println(mu.userName);
		System.out.println(mu.password);
		System.out.println(mu.phoneNo);
		System.out.println(mu.emailId);

	}
}