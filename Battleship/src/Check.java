
public class Check {

	public static void main(String[] args) {
		BattleshipGame x = new BattleshipGame();
		int[] y = new int[10];
		y = x.getShotsFromUser();
		
		for (int i = 0; i < 10; i++)
		{
			System.out.println(y[i]);
		}
	}
}
