package edu.ccrm.util;

import java.io.IOException;
import java.nio.file.*;

public class RecursionUtils {

    public static long getDirectorySize(Path directory) throws IOException {
        if (!Files.exists(directory) || !Files.isDirectory(directory)) {
            throw new IOException("Directory does not exist: " + directory);
        }

        return Files.walk(directory)
                .filter(Files::isRegularFile)
                .mapToLong(path -> {
                    try {
                        return Files.size(path);
                    } catch (IOException e) {
                        return 0L;
                    }
                })
                .sum();
    }
}
