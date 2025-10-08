public class PackageProcessing {

    public PackageProcessing(Package pack) {
        switch (pack.getDataType()) {
            case 1: consolePrinting(pack.getData()); break;
        }
    }

    private void consolePrinting(String data) {
        System.out.println("Вывод данных:");
        System.out.println(data);
        System.out.println("Вывод данных завершён.");
    }

}
