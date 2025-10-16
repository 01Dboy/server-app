import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class PackageReceiving {

    public byte[] tryingReceiving(ServerSocket serverSocket) {
        System.out.println("Попытка подключения... /n");
        try (Socket socket = serverSocket.accept();
             InputStream in = socket.getInputStream();) {
            System.out.println("Подключение прошло успешно.");
            System.out.println("Пакет передается расшифровщику.");
            return in.readAllBytes();
        } catch (IOException e) {
            System.out.println("Ошибка подключения: ");
            e.printStackTrace();
            return null;
        }
    }

}






