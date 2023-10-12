import java.util.Map;
import java.util.HashMap;

/**
 * 练习双列集合
 * 
 * @anthor wl
 * @date 2023/4/23 23:04
 */
public class MapDemo {
	
	public static void main(String[] args) {
		/*
			V  put(K key, V value)              	添加元素             
			V  remove(Object key)               	根据键值删除键值对元素      
			void  clear()                       	移除所有的键值对元素       
			boolean  containsKey(Object key)    	判断集合是否包含指定的键     
			boolean  containsValue(Object value)	判断集合是否包含指定的值     
			boolean  isEmpty()                  	判断集合是否为空         
			int  size()                         	返回集合的长度，也就是键值对的个数
		*/

		// 1.创建Map集合对象
		Map<String, String> map = new HashMap<>();

		// 2.添加元素
		map.put("郭靖", "黄蓉");
		map.put("韦小宝", "沐剑屏");
		String addValue = map.put("尹志平", "小龙女");
		System.out.println("addValue = " + addValue);
		/*
			- 在添加数据时，如果键是不存在的，那么直接把键值对对象添加到Map集合中
			- 在添加数据时，如果键是存在的，那么会把原有的键值对对象覆盖，会把被覆盖的值返回
		*/

		String value = map.put("韦小宝", "双儿");
		System.out.println("被替换下来的值 = " + value);

		// 删除元素
		String result = map.remove("郭靖");
		System.out.println("result = " + result);

		// 清空集合
		map.clear();

		// 3.打印集合
		System.out.println(map);

	}
}