package qrCode;


import java.awt.image.BufferedImage;  
import java.io.File;  
import java.io.IOException;  
import javax.imageio.ImageIO;  
import jp.sourceforge.qrcode.QRCodeDecoder;  
import jp.sourceforge.qrcode.data.QRCodeImage;  
  
public class GucasQRCodeDecoder {  
    public GucasQRCodeDecoder() {  
          
    }  
      
    /** 
     * decode qrcode image. 
     * @param qrcodePicfilePath 
     * @return decoding value. 
     */  
    public static String decode(String qrcodePicfilePath) {  
        /* ��ȡ��ά��ͼ����� */  
        File imageFile = new File(qrcodePicfilePath);  
        BufferedImage image = null;  
        try {  
            image = ImageIO.read(imageFile);  
        } catch (IOException e) {  
            System.out.println("Decoding failed, read QRCode image error: " + e.getMessage());  
            return null;  
        }  
        /* 
        try { 
            String decodedData = new String(decoder.decode(new J2SEImageGucas(image)), "GBK"); 
            System.out.println(decodedData); 
        } catch (DecodingFailedException dfe) { 
            System.out.println("Error: " + dfe.getMessage()); 
        } catch (UnsupportedEncodingException e) { 
            e.printStackTrace(); 
        } 
        */  
        /* ���ά�� */  
        QRCodeDecoder decoder = new QRCodeDecoder();  
        String decodedData = new String(decoder.decode(new J2SEImageGucas(image)));  
        return decodedData;  
    }  
  
    public static void main(String[] args) {  
        QRCodeDecoder decoder = new QRCodeDecoder();  
        File imageFile = new File("qrcode//TestQRCode.png");  
        BufferedImage image = null;  
        try {  
            image = ImageIO.read(imageFile);  
        } catch (IOException e) {  
            System.out.println("Error: " + e.getMessage());  
        }  
          
        System.out.println("ʶ���ά��");  
          
        String decodedData = new String(decoder.decode(new J2SEImageGucas(image)));  
        System.out.println(decodedData);  
          
    }  
}  
  
class J2SEImageGucas implements QRCodeImage {  
    BufferedImage image;  
  
    public J2SEImageGucas(BufferedImage image) {  
        this.image = image;  
    }  
  
    public int getWidth() {  
        return image.getWidth();  
    }  
  
    public int getHeight() {  
        return image.getHeight();  
    }  
  
    public int getPixel(int x, int y) {  
        return image.getRGB(x, y);  
    }  
}  