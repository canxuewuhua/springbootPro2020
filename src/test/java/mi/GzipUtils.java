package mi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPOutputStream;

public class GzipUtils {
    private static final Logger logger = LoggerFactory.getLogger(GzipUtils.class);

    public static byte[] compress(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip;
        try {
            gzip = new GZIPOutputStream(out);
            gzip.write(str.getBytes(StandardCharsets.UTF_8.name()));
            gzip.close();
        } catch (IOException e) {
            logger.error("gzip compress error.", e);
        }
        return out.toByteArray();
    }
}
