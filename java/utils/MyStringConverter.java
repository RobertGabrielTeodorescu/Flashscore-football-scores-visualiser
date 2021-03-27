package utils;

import javafx.util.StringConverter;

public class MyStringConverter extends javafx.util.StringConverter<Integer> {


    @Override
    public String toString(Integer object) {
        return object.toString();
    }

    @Override
    public Integer fromString(String string) {
        return Integer.parseInt(string);
    }
}
