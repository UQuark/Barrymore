package me.uquark.barrymore.service;

import me.uquark.barrymore.algorithm.Algorithm;
import me.uquark.barrymore.avatar.IAvatar;
import me.uquark.barrymore.ui.UserOrder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class AbstractService {
    protected final ArrayList<String> dictionaryStrings = new ArrayList<String>();
    protected final ArrayList<String> dictionaryValues = new ArrayList<String>();
    protected final IAvatar avatar;

    protected void loadDictionary(String strings, String values) throws IOException {
        InputStream stringsStream = getClass().getResourceAsStream(strings);
        InputStream valuesString = getClass().getResourceAsStream(values);

        Scanner scanner = new Scanner(stringsStream);
        while (scanner.hasNextLine()) {
            String word = scanner.nextLine();
            dictionaryStrings.add(word);
        }

        scanner.close();
        stringsStream.close();

        scanner = new Scanner(valuesString);
        while (scanner.hasNextLine()) {
            String word = scanner.nextLine();
            dictionaryValues.add(word);
        }

        scanner.close();
        stringsStream.close();
    }

    protected abstract void process(ArrayList<String> keywords);

    public AbstractService(IAvatar avatar) {
        this.avatar = avatar;
    }

    public void userOrder(UserOrder uo) {
        String[] words  = uo.message.split(" ");
        ArrayList<String> keywords = new ArrayList<String>();
        for (String inWord : words) {
            for (int i=0; i < dictionaryStrings.size(); i++) {
                String dictWord = dictionaryStrings.get(i);
                String common = Algorithm.longestCommonSubstring(inWord, dictWord);
                double confidence = ((double) common.length() * 2) / ((double) (inWord.length() + dictWord.length()));
                if (confidence >= 0.7)
                    keywords.add(dictionaryValues.get(i));
            }
        }
        process(keywords);
    }

    public double analyzeMessage(String message) {
        String[] words  = message.split(" ");
        double confidence = 0;
        for (String inWord : words) {
            for (String dictWord : dictionaryStrings) {
                String common = Algorithm.longestCommonSubstring(inWord, dictWord);
                confidence += ((double) common.length() * 2) / ((double) (inWord.length() + dictWord.length()));
            }
        }
        return confidence;
    }
}
