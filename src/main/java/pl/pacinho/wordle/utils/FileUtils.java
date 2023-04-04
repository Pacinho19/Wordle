package pl.pacinho.wordle.utils;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    @SneakyThrows
    public static List<String> readTxt(File file) {
        List<String> out = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                out.add(line);
            }
        }
        return out;
    }

    @SneakyThrows
    public static File getFileFromResource(String fileName) {
        ClassLoader classLoader = FileUtils.class.getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null)
            throw new IllegalArgumentException("file not found! " + fileName);

        return new File(resource.toURI());
    }
}