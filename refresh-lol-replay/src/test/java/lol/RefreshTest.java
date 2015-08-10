package lol;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class RefreshTest {
    @Test
    public void refresh() throws Exception {
        String userName = System.getProperty("userName") == null ? "icearrows" : System.getProperty("userName");
        RestTemplate t = new RestTemplate();
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("userName", userName);
        map.add("force", "true");
        String r = t.postForEntity("http://www.op.gg/summoner/ajax/spectator/", map, String.class).getBody();
        String id = r.replaceAll("(?is).*gameId=([0-9]+).*", "$1");

        String recordUrl = "http://www.op.gg/summoner/ajax/requestRecording.json/gameId=" + id;
        println("URL=" + recordUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.130 Safari/537.36");
        headers.set(":authority:", "www.op.gg");
        headers.set(":method:", "GET");
        headers.set(":path:", "/summoner/ajax/requestRecording.json/gameId=" + id);
        headers.set(":scheme:", "http");
        headers.set("upgrade-insecure-requests", "1");
        HttpEntity entity = new HttpEntity(headers);
        println(t.exchange(recordUrl, HttpMethod.GET, entity, String.class).getBody());
    }

    private void println(Object o) {
        System.out.println(o.toString());
    }
}
