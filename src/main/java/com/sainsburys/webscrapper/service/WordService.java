package com.sainsburys.webscrapper.service;

import com.sainsburys.webscrapper.model.Word;
import org.springframework.stereotype.Service;

@Service
public class WordService {

    public Word createWord(String text) {
        Word word = new Word();
        word.setText(text);
        return word;
    }

}
