package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generator {

    private final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String DIGITS = "0123456789";
    private String SPECIAL = "(~!@#$%^&*_-+=`|\\(){}[]:;\"'<>,.?/";
    private String CYRILLIC = "АаБбВвГгДдЕеЁёЖжЗзИиЙйКкЛлМмНнОоПпРрСсТтУуФфХхЦцЧчШшЩщЪъЫыЬьЭэЮюЯя";

    Random random = new Random();

    public String randomString(int length) {
        String combination = LOWER + DIGITS + UPPER;
        char[] string = new char[length];
        for(int i = 0; i < length; i++) {
            string[i] = combination.charAt(random.nextInt(combination.length()));
        }
        return new String(string);
    }
    public String randomSpecialString(int length) {
        String combination = SPECIAL;
        char[] string = new char[length];
        for(int i = 0; i < length; i++) {
            string[i] = combination.charAt(random.nextInt(combination.length()));
        }
        return new String(string);
    }
    public String randomCyrillicString(int length) {
        String combination = SPECIAL;
        char[] string = new char[length];
        for(int i = 0; i < length; i++) {
            string[i] = combination.charAt(random.nextInt(combination.length()));
        }
        return new String(string);
    }

    public String randomEmail(int length) {
        String suffix = "@testmail.ru";
        String randomEmail = randomString(length - suffix.length()) + suffix;
        return randomEmail.toLowerCase();
    }

    public String randomName(int length) {
        String prefix = "test-";
        return prefix + randomString(length - prefix.length());
    }

    public String randomPassword(int length) {
        return randomString(length);
    }

    public List<String> getOrderList(List<String> ingredientsList) {
        int size = new Random().nextInt(ingredientsList.size());
        List<String> orderList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int index = new Random().nextInt(ingredientsList.size());
            orderList.add(ingredientsList.get(index));
        }
        return orderList;
    }
}