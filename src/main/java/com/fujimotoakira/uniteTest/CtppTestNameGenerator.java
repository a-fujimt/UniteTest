package com.fujimotoakira.uniteTest;

import java.util.Arrays;

public class CtppTestNameGenerator {

    public static String generate(String path) {
        final String[] split = path.split("/");
        final int index = Arrays.asList(split).indexOf("patched_programs");
        StringBuilder builder = new StringBuilder();
        builder.append(split[index + 3]).append("_")
                .append(split[index + 5]).append("_")
                .append(split[index + 7]);
        return new String(builder);
    }

    public static String generate(String path, String testName) {
        return testName + "_" + generate(path);
    }

}
