import java.nio.charset.StandardCharsets;

public class Package {

    private final String data;
    private final int dataType;
    private final boolean valid;

    public Package (String data, int dataType, boolean valid) {
        this.data = data;
        this.dataType = dataType;
        this.valid = valid;
    }

    public String getData() {return data;}
    public int getDataType() {return dataType;}
    public boolean getValid() {return valid;}



}
