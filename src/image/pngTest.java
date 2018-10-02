package image;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.sun.imageio.plugins.png.PNGMetadata;

public class pngTest {
	
	static private byte[] getContent(String filePath) throws IOException {  
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
	
	static private void readCustomData(byte[] imageData) throws IOException{
	    ImageReader imageReader = ImageIO.getImageReadersByFormatName("png").next();

	    imageReader.setInput(ImageIO.createImageInputStream(new ByteArrayInputStream(imageData)), true);

	    IIOMetadata metadata = imageReader.getImageMetadata(0);

	    PNGMetadata pngmeta = (PNGMetadata) metadata; 
	    
	    printNode(pngmeta.getStandardChromaNode());
	    
	    printNode(pngmeta.getStandardCompressionNode());
	    
	    printNode(pngmeta.getStandardDataNode());
	    
	    printNode(pngmeta.getStandardDimensionNode());
	    
	    printNode(pngmeta.getStandardDocumentNode());
	    
	    printNode(pngmeta.getStandardTextNode());
	    
	    printNode(pngmeta.getStandardTransparencyNode());
	}
	
	static private void printNode(IIOMetadataNode metanode){
		if (metanode == null)
			return;
		NodeList childNodes = metanode.getChildNodes();
		if( childNodes == null)
			return;
		for (int i = 0; i < childNodes.getLength(); i++) {
	        Node node = childNodes.item(i);
	        NamedNodeMap attribute = node.getAttributes();
	        if( attribute == null)
	        	continue;
	        int length = attribute.getLength();
	        for( int j = 0; j < length; j++){
	        	Node each = attribute.item(j);
	        	String value = each.getNodeValue();
	        	String name = each.getNodeName();
		        System.out.println("Name: " + name + " value: " + value);
	        }
	    }
	}
	
	static public void main(String[] arg) throws IOException{
		byte[] content = getContent("C:\\Users\\i042416\\Desktop\\test\\clipboard1.png");
		readCustomData(content);
	}
}
