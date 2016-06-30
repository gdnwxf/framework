package framwork.mybatis;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
  *  
  * @author <a href="mailto:wangch@zjport.gov.cn">wangch</a>
  * @version $Id$   
  * @since 2.0
  */
public class MapTest {

	public static void main(String[] args) {
		Map<String ,String> map2 = new HashMap<String ,String>(2);
		Map<String ,String> map = new LinkedHashMap<String ,String>(2);
		map.put("dsa", "add");
		map.put("a321", "add");
		map.put("b321", "add");
		
		map2.put("dsa", "add");
		map2.put("a321", "add");
		map2.put("b321", "add");
		map.get("dsa");
		System.out.println( Arrays.toString(map.keySet().toArray(new String[0])));
		System.out.println( Arrays.toString(map2.keySet().toArray(new String[0])));
		
	}
}
