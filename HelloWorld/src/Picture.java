public class Picture {
	public static void main(String[] args) {
		Picture p = new Picture();
		p.draw();
	}

	//TODO: Improve this method through using loops
	private void draw() {
		for (int i=1;i<11;i++)
		{
			for (int ii=1;ii<=i;ii++)
			{
				System.out.print("*");
			}
			System.out.println();
		}
		
	}
}
