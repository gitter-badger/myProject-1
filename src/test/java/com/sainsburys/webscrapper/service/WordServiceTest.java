package com.sainsburys.webscrapper.service;

import com.sainsburys.webscrapper.model.Word;
import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class WordServiceTest {

    WordService wordService = new WordService();

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void shouldCreateWord() throws Exception {

        // Arrange

        // Act
        Word word = wordService.createWord("hello");
        String wordText = word.getText();

        // Assert
        assertThat(wordText).isEqualTo("hello");

    }



}