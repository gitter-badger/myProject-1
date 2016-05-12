package com.sainsburys.webscrapper.service;

import com.sainsburys.webscrapper.model.Word;
import org.springframework.stereotype.Service;

@Service
public class WordService {

    public Word createWord(String text) {
        Word word = new Word();
        word.setText(text);

      /*  try {

            Document doc = Jsoup.connect("http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html").get();
            Elements allProducts = doc.select("div.productInfo");


            Element element = allProducts.get(0);



        } catch (IOException e) {
            e.printStackTrace();
        }
*/
        return word;
    }

}
