import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;
import java.util.Enumeration;

public class ListJar {
    public static void main(String[] args) throws Exception {
        String jarPath = "C:\\Users\\Egorka\\.gradle\\caches\\forge_gradle\\deobf_dependencies\\curse\\maven\\legendary-survival-overhaul-840254\\7603852_mapped_official_1.20.1\\legendary-survival-overhaul-840254-7603852_mapped_official_1.20.1.jar";
        try (ZipFile zipFile = new ZipFile(jarPath)) {
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                String name = entry.getName();
                if (name.toLowerCase().contains("bodydamage") || name.toLowerCase().contains("capability")) {
                    System.out.println(name);
                }
            }
        }
    }
}
