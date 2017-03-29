
import java.io.IOException;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
public class PeResources {
    public static String getResource(String name) {
        try {
            return Resources.toString(Resources.getResource(name), Charsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}