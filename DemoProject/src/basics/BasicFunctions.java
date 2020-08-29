package basics;

public class BasicFunctions {
	public static void main(String[] args) throws StringIndexOutOfBoundsException{
//		System.out.println("test");
//		String name = "This is a new string";
//		System.out.println(name.charAt(0));
//		
//		System.out.println(name.length());
//		
//		System.out.println(name.substring(0,5));
//		System.out.println(name.indexOf("another"));
		
		String str = new String("This is a another string");
		String newString = "";
//		System.out.println(str.length());
		
//		System.out.println(str.charAt(10));
		
		for(int i = str.length()-1; i>=0;i--)
		{
			newString = newString + str.charAt(i);
//		
//			System.out.println(str.charAt(i));
		}
		System.out.println(newString);

			
	}

}
