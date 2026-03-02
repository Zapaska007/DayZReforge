import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.io.File;

public class Inspect {
    public static void main(String[] args) throws Exception {
        File file = new File(
                "C:\\Users\\Egorka\\.gradle\\caches\\forge_gradle\\deobf_dependencies\\curse\\maven\\legendary-survival-overhaul-840254\\7603852_mapped_official_1.20.1\\legendary-survival-overhaul-840254-7603852_mapped_official_1.20.1.jar");
        URL url = file.toURI().toURL();
        URLClassLoader loader = new URLClassLoader(new URL[] { url });
        Class<?> clazz = loader.loadClass("sfiomn.legendarysurvivaloverhaul.util.CapabilityUtil");
        for (Method m : clazz.getDeclaredMethods()) {
            if (m.getName().contains("BodyDamage")) {
                System.out.println(m.getName() + " -> " + m.getReturnType().getName());
            }
        }
    }
}
