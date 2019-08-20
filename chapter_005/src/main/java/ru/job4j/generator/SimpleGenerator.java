package ru.job4j.generator;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleGenerator implements Generator {
    private static final Pattern KEYS = Pattern.compile("\\$[{]\\w+[}]");

    @Override
    public String generate(String template, Map<String, String> keys) throws NoKeyException, TooMuchKeysException {
        Set<String> words = new HashSet<>();
        String result = template;
        Map<String, String> localk = new HashMap<>(keys);
        Matcher matcher = KEYS.matcher(template);
        while (matcher.find()) {
            words.add(template.substring(matcher.start() + 2, matcher.end() - 1));
        }
        for (String str: words) {
            String val = localk.get(str);
            if (val != null) {
                result = result.replace("${" + str + "}", val);
                localk.remove(str);
            } else {
                throw new NoKeyException(str);
            }
        }
        if (localk.size() > 0) {
            throw new TooMuchKeysException();
        }
        return result;
    }

}
