package com.lieonov.lab1.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ParserImpl implements Parser{

    private String readHtml(String link) throws IOException {
        Document doc = Jsoup.connect(link).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 " +
                "(KHTML, like Gecko) Chrome/140.0.0.0 Safari/537.36")
                .timeout(3000)
                .get();
        return doc.html();
    }

    private String htmlFilter(String html) {
        Pattern p = Pattern.compile("<p>(.*?)</p>", Pattern.DOTALL);
        html = html.replaceAll("<(?s)header[^>]*>.*?</header>", "")
                .replaceAll("<(?s)style[^>]*>.*?</style>","")
                .replaceAll("<(?s)footer[^>]*>.*?</footer>", "");
        Matcher m = p.matcher(html);
        StringBuilder sb = new StringBuilder();

        while (m.find()) {
            sb.append(m.group(1)).append("\n");
        }

        String pText = sb.toString();
        return pText.replaceAll("<[^>]+>", "").replaceAll("&nbsp;", "")
                .replaceAll(" \\[.*?\\]", "");
    }

    private String charLimiter(int limit, String text){
        if(text.length() > limit){
            return text.substring(0, limit);
        }
        else return text;
    }

    @Override
    public String runParser(int limit,String link) throws IOException {
        String html = readHtml(link);
        return charLimiter(limit, htmlFilter(html));
    }
}
