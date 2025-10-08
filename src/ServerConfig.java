import java.nio.charset.StandardCharsets;

public class ServerConfig {

    public static final String HOST = "localhost";
    public static final int PORT = 8080;

    public static final String EXPECTED_SIGNATURE = "SIGNAT99";
    public static final byte[] SIGNATURE_BYTES = EXPECTED_SIGNATURE.getBytes(StandardCharsets.UTF_8);
    public static final int SIGNATURE_LENGTH = 8;

    public static final int DATA_TYPE_LENGTH = 7;

}
