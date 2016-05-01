package com.example.hau.dulichviet.models;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by HAU on 11/29/2015.
 */
public class ParseHtml {

    public static ArrayList<String> getImageHtml(String url) {
        String url_image = "";
        ArrayList<String> datas = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(url).get();
            Element div_image = doc.getElementById("image-grid");
            Elements elements = div_image.select("div.item");
            System.out.println(elements.size());
            for (Element image : elements) {
                url_image = image.select("a").select("img").attr("src");
                datas.add(url_image);
            }
            return datas;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
