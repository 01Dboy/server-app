import java.nio.charset.StandardCharsets;

public class PackageProcessing {

    public PackageProcessing(Package pack) {
        switch (pack.dataType()) {
            case 1: consolePrinting(pack.data()); break;
        }
    }

    private void consolePrinting(String data) {
        System.out.println("Вывод данных:");
        System.out.println(data);
        System.out.println("Вывод данных завершён.");
    }

    private int convertDataType(byte[] dataTypeStr) {
        String dataType = new String(dataTypeStr, StandardCharsets.UTF_8);

        return switch (dataType) {
            case "CONSOLE":
                return 1;
            case "PLAIN":
                return 2;
            case "JSON":
                return 3;
            default:
                throw new IllegalStateException("Ошибка чтениея dataType: " + dataType);
        };
    }

}
