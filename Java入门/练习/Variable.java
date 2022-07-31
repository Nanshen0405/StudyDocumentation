import java.util.Scanner;

public class Variable {
	public static void main(String[] args) {
		 // 创建输入扫描仪，System.in表示接受系统输入的值
		 Scanner in = new Scanner(System.in);
		 // 接受数据
		 System.out.println("请输入一个整数：");
		 int i = in.nextInt();
		 // 输出变量的值
		 System.out.println("你输入的整数为 " + i);
	}
}