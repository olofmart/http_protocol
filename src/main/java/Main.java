import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

public class Main {
    public static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        final CloseableHttpClient client = HttpClients.createDefault();

        final HttpUriRequest get = new HttpGet("https://raw.githubusercontent.com/" +
                "netology-code/jd-homeworks/master/http/task1/cats");
        try (CloseableHttpResponse response = client.execute(get)) {
            final HttpEntity entity = response.getEntity();
            List<Cat> cats = mapper.readValue(response.getEntity().getContent(), new TypeReference<List<Cat>>() {});
            cats.stream().filter(value -> (value.getUpvotes() != null) && (value.getUpvotes() > 0))
                         .forEach(System.out::println);
        }
    }
}
