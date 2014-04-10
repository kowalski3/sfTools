import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class MultiMap{

	Map<Integer, ArrayList<String>> joolsMap = new HashMap<Integer, ArrayList<String>>();
	
	private void launch(){
		joolsMap.put(1, new ArrayList<String>());
		joolsMap.get(1).add("Julian");
		joolsMap.get(1).add("Jools");
		joolsMap.get(1).add("Jules");
		joolsMap.put(2, new ArrayList<String>());
		joolsMap.get(2).add("Andrea");
		joolsMap.get(2).add("Andre");
		joolsMap.get(2).add("Dre");
		
		for(ArrayList<String> nextList : joolsMap.values()){
			for(String nextString : nextList){
				System.out.println(nextString);	
			}
		System.out.println();
		
		}
	}


	public static void main(String[] args){
		new TestMultiMap().launch();
	}

}