package qrCode;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

public class Main {
    public static void main(String[] args) {
        ByteArrayOutputStream out = QRCode.from("Hello World").to(ImageType.PNG).stream();

        try {
            FileOutputStream fout = new FileOutputStream(new File(
                    "C:\\temp\\QR_Code.JPG"));

            fout.write(out.toByteArray());

            fout.flush();
            fout.close();

        } catch (FileNotFoundException e) {
            // Do Logging
        } catch (IOException e) {
            // Do Logging
        }
    }
    /* 除了使用QRGen的API来生成数据流，我们还可以使用下面的API来创建QR码：
// get QR file from text using defaults
File file = QRCode.from("Hello World").file();
// get QR stream from text using defaults
ByteArrayOutputStream stream = QRCode.from("Hello World").stream();

// override the image type to be JPG
QRCode.from("Hello World").to(ImageType.JPG).file();
QRCode.from("Hello World").to(ImageType.JPG).stream();

// override image size to be 250x250
QRCode.from("Hello World").withSize(250, 250).file();
QRCode.from("Hello World").withSize(250, 250).stream();

// override size and image type
QRCode.from("Hello World").to(ImageType.GIF).withSize(250, 250).file();
QRCode.from("Hello World").to(ImageType.GIF).withSize(250, 250).stream();


在Java中生成网站链接（URL）的QR码

QR 码最常见的应用便是为网站中一个特定的网页或下载页带来流量。因此，QR码常常会编码URL或网站地址，用户可以通过手机摄像头扫描，并在其浏览器中打开。URL可以直接编码在QR码中。在上面的的Hello World示例中，只需把“Hello World”这个字符串替换为需要编码的URL。下面是代码片段：
ByteArrayOutputStream out = QRCode.from("http://viralpatel.net").to(ImageType.PNG).stream();

*/
    
}