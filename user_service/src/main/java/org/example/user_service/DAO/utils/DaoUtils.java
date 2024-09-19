package org.example.user_service.DAO.utils;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DaoUtils {
    public Optional<String> getKeyName(String message) {
        String keyPattern = "Key \\(([^)]+)\\)=\\(([^)]+)\\)";
        Pattern pattern = Pattern.compile(keyPattern);
        Matcher matcher = pattern.matcher(message);

        if(matcher.find()){
            return Optional.of(matcher.group(1));
        }
        return Optional.empty();

    }
}
