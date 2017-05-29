import java.util.HashMap;
import java.util.Map;

public class Test {
	public static void main(String[] args){
		Map<Character,Character> m = new HashMap<Character, Character>();
		char[] charArray = "asdfads".toCharArray();
		for (char c : charArray) {
			if( m.containsKey(new Character(c))) {
				System.out.println("tiene duplicados");
				return;
			}
		}
		System.out.println("no tiene duplicados");
	}

}
