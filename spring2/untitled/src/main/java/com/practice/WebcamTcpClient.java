package com.practice;

import com.github.sarxos.webcam.Webcam;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class WebcamTcpClient {
    public static void main(String[] args) throws Exception {
        // Відкриваємо камеру
        Webcam webcam = Webcam.getDefault();
        webcam.open();

        // Підключаємося до Python сервера
        Socket socket = new Socket("127.0.0.1", 9999);
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        while (true) {
            BufferedImage image = webcam.getImage();

            // Конвертуємо у JPEG
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", baos);
            byte[] bytes = baos.toByteArray();

            // Спочатку відправляємо довжину (4 байти), потім самі дані
            out.writeInt(bytes.length);
            out.write(bytes);
            out.flush();

            Thread.sleep(50); // ~20 fps
        }
    }
}
