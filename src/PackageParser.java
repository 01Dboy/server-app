import javax.imageio.IIOException;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.zip.CRC32;

public class PackageParser {

    public Package parse(byte[] packageBytes) {

        ByteArrayInputStream bais = new ByteArrayInputStream(packageBytes);
        DataInputStream dis = new DataInputStream(bais);

        boolean valid = false;

        String data;

        byte[] signatureBytes = new byte[8];
        dis.readFully(signatureBytes);

        byte[] dataTypeBytes = new byte[7];
        dis.readFully(dataTypeBytes);

        int dataLength = dis.readInt();

        byte[] dataBytes = new byte[dataLength];
        dis.readFully(dataBytes);
        data = new String(dataBytes, StandardCharsets.UTF_8);

        long receivedCrc = dis.readInt() & 0xFFFFFFFFL;

        if (verifySignature(packageBytes) == false) {
            System.out.println("Верификация сигнатуры провалена.");
            valid = false;
        }

        if (verifyCrc(dataBytes, dataLength, receivedCrc) == false) {
            System.out.println("Верификация CRC провалена.");
            valid = false;
        }

        return new Package(data, convertDataType(dataTypeBytes), valid);

    }

    private boolean verifySignature(byte[] packageBytes) {

        if (packageBytes.length < ServerConfig.SIGNATURE_LENGTH) {
            return false;
        }

        byte[] signatureBytes = Arrays.copyOf(packageBytes, ServerConfig.SIGNATURE_LENGTH);
        String receivedSignature = new String(signatureBytes, StandardCharsets.UTF_8);

        return receivedSignature.equals(ServerConfig.EXPECTED_SIGNATURE);

    }

    private boolean verifyCrc(byte[] data, int length, long receivedCrc) {
        CRC32 crc = new CRC32();
        crc.update(data, 0, length);
        return crc.getValue() == receivedCrc;
    }

    private int convertDataType(byte[] dataTypeStr) {
        return switch (dataTypeStr) {
            case "CONSOLE":
                return 1;
            case "PLAIN":
                return 2;
            case "JSON":
                return 3;
            default:
                return 0;
        }
    }

}
