package multiThreadAsynchronous;



/*
 * 
 * http://www.jb51.net/article/72892.htm
 * 
1. 在JAVA平台,实现异步调用的角色有如下三个角色:调用者 提货单   真实数据
一个调用者在调用耗时操作,不能立即返回数据时,先返回一个提货单.然后在过一断时间后凭提货单来获取真正的数据.
去蛋糕店买蛋糕，不需要等蛋糕做出来（假设现做要很长时间），只需要领个提货单就可以了（去干别的事情），等到蛋糕做好了，再拿提货单取蛋糕就可以了。

2.  这里的main类就相当于“顾客”，host就相当于“蛋糕店”，顾客向“蛋糕店”定蛋糕就相当于“发请求request”，
返回的数据data是FutureData的实例，就相当于提货单，而不是真正的“蛋糕”。
在过一段时间后（sleep一段时间后），调用data1.getContent()，也就是拿提货单获取执行结果。

*
*/

public class MainClient {
	public static void main(String[] args) {
		System.out.println("main BEGIN");
		Host host = new Host();
		Data data1 = host.request(10, 'A');
		Data data2 = host.request(20, 'B');
		Data data3 = host.request(30, 'C');
		System.out.println("main otherJob BEGIN");
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
		}
		
		System.out.println("main otherJob END");
		
		System.out.println("data1 = " + data1);
		System.out.println("data2 = " + data2);
		System.out.println("data3 = " + data3);
		/*System.out.println("data1 = " + data1.getContent());
		System.out.println("data2 = " + data2.getContent());
		System.out.println("data3 = " + data3.getContent());*/
		System.out.println("main END");
	}
}
