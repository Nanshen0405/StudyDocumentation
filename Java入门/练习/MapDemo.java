import java.util.Map;
import java.util.HashMap;

/**
 * ��ϰ˫�м���
 * 
 * @anthor wl
 * @date 2023/4/23 23:04
 */
public class MapDemo {
	
	public static void main(String[] args) {
		/*
			V  put(K key, V value)              	���Ԫ��             
			V  remove(Object key)               	���ݼ�ֵɾ����ֵ��Ԫ��      
			void  clear()                       	�Ƴ����еļ�ֵ��Ԫ��       
			boolean  containsKey(Object key)    	�жϼ����Ƿ����ָ���ļ�     
			boolean  containsValue(Object value)	�жϼ����Ƿ����ָ����ֵ     
			boolean  isEmpty()                  	�жϼ����Ƿ�Ϊ��         
			int  size()                         	���ؼ��ϵĳ��ȣ�Ҳ���Ǽ�ֵ�Եĸ���
		*/

		// 1.����Map���϶���
		Map<String, String> map = new HashMap<>();

		// 2.���Ԫ��
		map.put("����", "����");
		map.put("ΤС��", "�彣��");
		String addValue = map.put("��־ƽ", "С��Ů");
		System.out.println("addValue = " + addValue);
		/*
			- ���������ʱ��������ǲ����ڵģ���ôֱ�ӰѼ�ֵ�Զ�����ӵ�Map������
			- ���������ʱ��������Ǵ��ڵģ���ô���ԭ�еļ�ֵ�Զ��󸲸ǣ���ѱ����ǵ�ֵ����
		*/

		String value = map.put("ΤС��", "˫��");
		System.out.println("���滻������ֵ = " + value);

		// ɾ��Ԫ��
		String result = map.remove("����");
		System.out.println("result = " + result);

		// ��ռ���
		map.clear();

		// 3.��ӡ����
		System.out.println(map);

	}
}