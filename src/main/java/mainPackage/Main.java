package mainPackage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import businessLayer.Order;
import presentationLayer.LogInGUI;

public class Main implements Serializable {
	public static void main(String[] args)  throws Exception {
		LogInGUI view = new LogInGUI(1400, 800);
		/*Order o1 = new Order(23, "abc", new Date(121, 4, 1, 0, 0));
		System.out.println(o1.getOrderDate().toString());
		Order o2 = new Order();
		System.out.println(o1.hashCode());
		System.out.println(o2.hashCode());*/
		/*String s1 = "Hell";
		String s2 = "hello";
		System.out.println(s2.contains(s1));*/
		
		
		/*ArrayList<String> s = new ArrayList<>();
		s.add("ana este ana");
		s.add("cine este ana");
		s.add("idk");
		s.add("blabla");
		s.add("ana merge");
		//{"ana este ana", "cine este ana", "idk", "blabla", "ana merge"};
		String txt = "ana";
		List<String> filterString = (List<String>) s.stream().filter(p -> p.contains(txt)).collect(Collectors.toList());
		System.out.println(filterString.size());*/
		
	}
}