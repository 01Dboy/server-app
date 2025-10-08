import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class PackageReceiving {

    public boolean send(byte[] packet) {
        try (Socket socket = new ServerSocket(ServerConfig.PORT).accept();
             InputStream input = socket.getInputStream()) {

            input.

            return true;
        } catch (IOException e) {
            return false;
        }

}
