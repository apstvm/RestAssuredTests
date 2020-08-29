package basics;

public class Australia implements CentralTraffic {
	

	@Override
	public void greenGo() {
		// TODO Auto-generated method stub
		System.out.println("Green means go here");
		
	}

	@Override
	public void redStop() {
		// TODO Auto-generated method stub
		System.out.println("Red means stop here");
	}

	@Override
	public void yellowWait() {
		System.out.println("Yellow means wait");
		
	}
	public static void main(String[] args) {
		CentralTraffic traffic = new Australia();
		traffic.greenGo();
		
		
	}


}
