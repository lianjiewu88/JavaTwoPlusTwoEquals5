package image;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.metadata.IIOMetadata;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sun.imageio.plugins.png.PNGMetadata;
public class pngTest {
	
	static public byte[] getContent(String filePath) throws IOException {  
        File file = new File(filePath);  
        long fileSize = file.length();  
        if (fileSize > Integer.MAX_VALUE) {  
            System.out.println("file too big...");  
            return null;  
        }  
        FileInputStream fi = new FileInputStream(file);  
        byte[] buffer = new byte[(int) fileSize];  
        int offset = 0;  
        int numRead = 0;  
        while (offset < buffer.length  
        && (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {  
            offset += numRead;  
        }  

        if (offset != buffer.length) {  
        	fi.close();
        	throw new IOException("Could not completely read file "  
                    + file.getName());  
        }  
        fi.close();  
        return buffer;  
    }  
	
	static public String readCustomData(byte[] imageData, String key) throws IOException{
	    ImageReader imageReader = ImageIO.getImageReadersByFormatName("png").next();

	    imageReader.setInput(ImageIO.createImageInputStream(new ByteArrayInputStream(imageData)), true);

	    IIOMetadata metadata = imageReader.getImageMetadata(0);

	    //this cast helps getting the contents
	    PNGMetadata pngmeta = (PNGMetadata) metadata; 
	    NodeList childNodes = pngmeta.getStandardTextNode().getChildNodes();

	    for (int i = 0; i < childNodes.getLength(); i++) {
	        Node node = childNodes.item(i);
	        String keyword = node.getAttributes().getNamedItem("keyword").getNodeValue();
	        String value = node.getAttributes().getNamedItem("value").getNodeValue();
	        if(key.equals(keyword)){
	            return value;
	        }
	    }
	    return null;
	}
	
	static public void main(String[] arg) throws IOException{
		byte[] content = getContent("C:\\Users\\i042416\\Desktop\\test\\clipboard1.png");
		readCustomData(content, "dummy");
	}
}
