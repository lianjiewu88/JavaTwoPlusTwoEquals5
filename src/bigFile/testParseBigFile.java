package bigFile;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;

// 2015-09-04 15:21PM source: http://www.importnew.com/10712.html

public class testParseBigFile {

	public static void main(String[] args) {
		byte[] temp = ",".getBytes();
		byte comma = temp[0];
		TextRowDecoder decoder = new TextRowDecoder(4, comma);
		/* source code of big 
		 * 	EUR/US1,20120102 00:01:30.420,1.29451,1.2949
			EUR/US2,20120103 00:01:30.420,1.29451,1.2949
			EUR/US3,20120103 00:01:30.420,1.29451,1.2949
			EUR/US4,20120103 00:01:30.420,1.29451,1.2949
		 */
		File file = new File("C:\\temp\\big");
		
		FileReader<byte[][]> reader = FileReader.create(decoder, file.listFiles());
		for (List<byte[][]> chunk : reader) {
		  // do something with each chunk
			int size = chunk.size();
			System.out.println("Line item size: " + size);
			for(int i = 0; i <size;i++) {
				byte[][] result = chunk.get(i);
				for( int j = 0; j < result.length; j++) {
					try {
						System.out.println("result: " + new String(result[j], "UTF-8"));
					} catch (UnsupportedEncodingException e) {
						
						e.printStackTrace();
					}
				}
			}
		}
	}

}
