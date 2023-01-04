package Problems.ImageCache;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.ArrayUtils;


public class Main {

    private enum FetchType {
        IN_CACHE, DOWNLOADED
    }

    public static void main(String[] args) {
        solutionCacheLRU(2, 6, List.of(
                "https://i.imgur.com/vwSDStB.jpeg",
                "https://i.imgur.com/d0NPJVp.jpeg",
                "https://i.imgur.com/vwSDStB.jpeg",
                "https://i.imgur.com/ZJgDRDF.jpeg",
                "https://i.imgur.com/vwSDStB.jpeg",
                "https://i.imgur.com/d0NPJVp.jpeg"
        ));
    }

    private static void solutionCacheLRU(long cacheSize, long numOfUrls, List<String> urls) {
        final Cache cache = new Cache(cacheSize);

        for (int i = 0; i < numOfUrls; i++) {
            String url = urls.get(i);

            // Fetch content from cache
            List<Byte> image = cache.fetch(url);
            FetchType fetchType = FetchType.DOWNLOADED;

            // If not present
            if (image == null || image.isEmpty()) {
                image = Arrays.asList(ArrayUtils.toObject(downloadImage(url)));
                cache.save(url, image); // Save in cache
            } else {
                fetchType = FetchType.IN_CACHE;
            }

            System.out.println("%s %s %s \n".formatted(url, fetchType, image.size()));
        }
    }

    private static byte[] downloadImage(String urlString) {
        try {
            URL url = new URL(urlString);
            InputStream in = new BufferedInputStream(url.openStream());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];

            int n = 0;
            while (-1 != (n=in.read(buf)))
            {
                out.write(buf, 0, n);
            }

            out.close();
            in.close();

            return out.toByteArray();
        } catch (IOException e) {
            return new byte[]{};
        }
    }
}
