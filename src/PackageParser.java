import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class PackageParser {

    public Package parse(byte[] packageBytes) throws IOException {

        ByteArrayInputStream bais = new ByteArrayInputStream(packageBytes);
        DataInputStream dis = new DataInputStream(bais);
        byte[] source = packageBytes;

        byte[] signatureBytes = new byte[8];
        dis.readFully(signatureBytes);
        String signature = new String(signatureBytes, StandardCharsets.UTF_8);

        byte[] dataTypeBytes = new byte[7];
        dis.readFully(dataTypeBytes);
        String dataType = new String(dataTypeBytes, StandardCharsets.UTF_8);

        int dataLength = dis.readInt();

        byte[] dataBytes = new byte[dataLength];
        dis.readFully(dataBytes);
        String data = new String(dataBytes, StandardCharsets.UTF_8);

        long receivedCrc = dis.readInt() & 0xFFFFFFFFL;

        return new Package(signature, dataType, dataLength, data, receivedCrc, source);

    }
}
