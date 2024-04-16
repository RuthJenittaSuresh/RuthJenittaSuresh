import java.io.*;


interface In1 {

	final int a = 30;

	
	void display();
}


class TestClass implements In1 {

	
	public void display(){ 
	System.out.println("tejal"); 
	}

	
	public static void main(String[] args)
	{
		TestClass t = new TestClass();
		t.display();
		System.out.println(a);
	}
}