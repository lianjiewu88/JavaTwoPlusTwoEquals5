package qrCode;



import java.awt.Color;  
import java.awt.Graphics2D;  
import java.awt.image.BufferedImage;  
import java.io.File;  
import java.io.IOException;  
  
import javax.imageio.ImageIO;  
import com.swetake.util.Qrcode;  
  
public class GucasQRCodeEncoder {  
      
    public static int max_data_size_small = 84;  
    public static int max_data_size_large = 500;  
      
    /** 
     *  
     * @param srcValue 
     * @param qrcodePicfilePath 
     * @return 
     */  
    public static boolean encode(String srcValue, String qrcodePicfilePath) {  
        return  encode_84(srcValue, qrcodePicfilePath);  
    }  
      
    /** 
     * Encoding the information to a QRCode, size of the information must be less than 84 byte. 
     * @param srcValue 
     * @param qrcodePicfilePath 
     * @return 
     */  
    public static boolean encode_84(String srcValue, String qrcodePicfilePath) {  
        int MAX_DATA_LENGTH = max_data_size_small; // the max size of QR code data source
        byte[] d = srcValue.getBytes();  
        int dataLength = d.length;  
        int imageWidth = 113; // image size must >= QR code size, or else it will 
                              // not be correctly decoded
        int imageHeight = imageWidth;  
        BufferedImage bi = new BufferedImage(imageWidth, imageHeight,  
                BufferedImage.TYPE_INT_RGB);  
        Graphics2D g = bi.createGraphics();  
        g.setBackground(Color.WHITE);  
        g.clearRect(0, 0, imageWidth, imageHeight);  
        g.setColor(Color.BLACK);  
        if (dataLength > 0 && dataLength <= MAX_DATA_LENGTH) {  

            Qrcode qrcode = new Qrcode();  
            //错误修正容量   
            //L水平   7%的字码可被修正  
            //M水平   15%的字码可被修正  
            //Q水平   25%的字码可被修正  
            //H水平   30%的字码可被修正  
            //QR码有容错能力，QR码图形如果有破损，仍然可以被机器读取内容，最高可以到7%~30%面积破损仍可被读取。  
            
            qrcode.setQrcodeErrorCorrect('M'); // L, Q, H, by default
            
            qrcode.setQrcodeEncodeMode('B'); // A, N, Ĭ��  
            qrcode.setQrcodeVersion(5); // 37 bytes, (37-1)*3+2+3-1+1 = 113
            boolean[][] b = qrcode.calQrcode(d);  
            int qrcodeDataLen = b.length;  
            for (int i = 0; i < qrcodeDataLen; i++) {  
                for (int j = 0; j < qrcodeDataLen; j++) {  
                    if (b[j][i]) {  
                        g.fillRect(j * 3 + 2, i * 3 + 2, 3, 3); 
                        
                    }  
                }  
            }  
            System.out.println("QR code data length" + qrcodeDataLen);  
        } else {  
            System.out.println("Generate QRCode image error! Data size is " + dataLength +", it is lager than 84 bytes.");  
            return false;  
        }  
        g.dispose();  
        bi.flush();  
        /* generate image */  
        File f = new File(qrcodePicfilePath);  
        String suffix = f.getName().substring(f.getName().indexOf(".")+1, f.getName().length());  
        try {  
            ImageIO.write(bi, suffix, f); //"png"  
        } catch (IOException ioe) {  
            System.out.println("Generate QRCode image error!" + ioe.getMessage());  
            return false;  
        }  
  
        return true;  
    }  
      
    /** 
     * Encoding the information to a QRCode, size of the information must be less tah 500 byte. 
     * @param srcValue 
     * @param qrcodePicfilePath 
     * @return 
     */  
    public static boolean encode_500(String srcValue, String qrcodePicfilePath) {  
        int MAX_DATA_LENGTH = max_data_size_large; 
        byte[] d = srcValue.getBytes();  
        int dataLength = d.length;  
        int imageWidth = 269; 
        int imageHeight = imageWidth;  
        BufferedImage bi = new BufferedImage(imageWidth, imageHeight,  
                BufferedImage.TYPE_INT_RGB);  
        Graphics2D g = bi.createGraphics();  
        g.setBackground(Color.WHITE);  
        g.clearRect(0, 0, imageWidth, imageHeight);  
        g.setColor(Color.BLACK);  
        if (dataLength > 0 && dataLength <= MAX_DATA_LENGTH) {  
            /* ��ɶ�ά�� */  
            Qrcode qrcode = new Qrcode();  
            qrcode.setQrcodeErrorCorrect('M'); 
            qrcode.setQrcodeEncodeMode('B'); 
            qrcode.setQrcodeVersion(18); // 0<= version <=40; 89�ֽ�,  
                                            // (89-1)*3+2+3-1+1 = 269  
            boolean[][] b = qrcode.calQrcode(d);  
            int qrcodeDataLen = b.length;  
            for (int i = 0; i < qrcodeDataLen; i++) {  
                for (int j = 0; j < qrcodeDataLen; j++) {  
                    if (b[j][i]) {  
                        g.fillRect(j * 3 + 2, i * 3 + 2, 3, 3); 
                    }  
                }  
            }  
            System.out.println("QR code size" + qrcodeDataLen);  
        } else {  
            return false;  
        }  
        g.dispose();  
        bi.flush();  
        /* generate image */  
        File f = new File(qrcodePicfilePath);  
        String suffix = f.getName().substring(f.getName().indexOf(".")+1, f.getName().length());  
        System.out.println(suffix);  
        try {  
            ImageIO.write(bi, suffix, f); //"png"  
        } catch (IOException ioe) {  
            System.out.println("Generate QRCode image error!" + ioe.getMessage());  
            return false;  
        }  
  
        return true;  
    }  
      
    public static void main(String[] args) throws Exception {  
        System.out.println("trying to encode QRCode...");
        String data = "亲，这是用来生成二维码的测试字符串。。。";  
        System.out.println("total number of source string: " + data.getBytes().length);  
        GucasQRCodeEncoder.encode(data, "c:\\temp\\A1.JPG");  
        System.out.println("encoded string: " + GucasQRCodeDecoder.decode("c:\\temp\\A1.JPG"));  
    }  
}  