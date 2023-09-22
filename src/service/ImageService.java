package service;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import util.PropertiesUtil;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ImageService {
    @Getter
    private static final ImageService INSTANCE = new ImageService();
    @Getter
    private static final String basePath = PropertiesUtil.get("image.base.url");
    @SneakyThrows
    public void upload(String path, InputStream content) {
        try (content) {
            Files.createDirectories(Path.of(basePath, path).getParent());
            Files.write(Path.of(basePath, path), content.readAllBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        }
    }
    @SneakyThrows
    public Optional<InputStream> get(String imgPath) {
        System.out.println(Path.of(basePath, imgPath));
        System.out.println(Files.exists(Path.of(basePath, imgPath)));
        return Files.exists(Path.of(basePath, imgPath))
                ? Optional.of(Files.newInputStream(Path.of(basePath, imgPath))) : Optional.empty();
    }
}
