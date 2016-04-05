package basic;

/*
 *  byte是一个字节保存的，有8个位，即8个0、1。
 *  Java中的一个byte，其范围是-128~127的，而Integer.toHexString的参数本来是int，
 *  如果不进行&0xff，那么当一个byte会转换成int时，对于负数，会做位扩展，举例来说，
 *  一个byte的-1(即0xff)，会被转换成int的-1(即0xffffffff)，那么转化出的结果就不是我们想要的了。
 */
public class Byte2Hex {

	public static String bytes2HexString(byte[] b) {
		String ret = "";
		for (int i = 0; i < b.length; i++) {
			// b[ i ] & 0xFF 运算后得出的仍然是个int
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			ret += hex.toUpperCase();
		}
		return ret;
	}

	public static void main(String[] args) {
		byte[] a = new byte[1];
		a[0] = -1;
		System.out.println(a);// [B@2a139a55
		System.out.println(a[0]); // 1
		System.out.println(Integer.toHexString(a[0])); // ffffffff - wrong!
		System.out.println(Integer.toHexString(a[0] & 0xff)); // ffffffff - wrong!

	}

}
