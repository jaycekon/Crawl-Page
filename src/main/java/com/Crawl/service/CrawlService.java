package com.Crawl.service;

import com.Crawl.Utils.FetchUtils;
import com.Crawl.model.HttpRequestData;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/8.
 */
public class CrawlService {
    public static List<String> crawl() {
        HttpRequestData data = new HttpRequestData();
        List<String> list = new ArrayList<>();
        try {
            String html = FetchUtils.get(data, "http://www.hanhande.com/manhua/op/1157941.shtml");
            Document document = Jsoup.parse(html);
            Elements elements = document.getElementsByClass("mhpage");
            if (!elements.isEmpty()) {
                Element element = elements.get(0);
                Elements options = element.getElementsByTag("option");
                for (Element e : options) {
                    crawlPage(data, e.val(), list);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return list;
    }


    private static void crawlPage(HttpRequestData data, String url, List<String> list) {
        try {
            String html = FetchUtils.get(data, url);
            Document document = Jsoup.parse(html);
            Elements elements = document.getElementsByClass("nph_photo_view");
            Element child = elements.get(0).getElementsByTag("img").get(0);
            System.out.println(child.attr("src"));
            list.add(child.attr("src"));
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
