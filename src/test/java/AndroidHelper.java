import org.apache.commons.io.IOUtils;

import java.io.IOException;

public class AndroidHelper {

    private String KEY_NAME = "hello-world.keystore";
    private String KEY_ALIAS = "create-bundle-key";
    private String KEY_ALIAS_PASSWORD = "sample-password";
    private String KEY_PASSWORD = "sample-password";

    static void generateApksFromAAB() throws IOException {
        String[] args = new String[] {"apks/scripts/generateAPKS.sh"};
        new ProcessBuilder(args).start();
//        String output = IOUtils.toString(proc.getInputStream());
//        System.out.println("sdafsdfsd");
//        System.out.println(proc.exitValue());
//        System.out.println(output);
    }

    static void installAPKSonDevice() throws IOException {
        String[] args = new String[] {"apks/scripts/installAPKS.sh"};
        new ProcessBuilder(args).start();
    }

    static void waitUntilAPKSCreated() throws IOException {
        String[] args = new String[] {"apks/scripts/waitUntilAPKSCreated.sh"};
        new ProcessBuilder(args).start();
    }
}
