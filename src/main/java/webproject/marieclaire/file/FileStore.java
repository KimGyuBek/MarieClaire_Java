package webproject.marieclaire.file;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import webproject.marieclaire.data.entity.UploadFile;

/**
 * class : FileStore.class
 * 파일 업로드 로직 수행
 */

@Component
@Slf4j
public class FileStore {

    @Value("${file.dir}")
    private String fileDir;

    public UploadFile storeFile(MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            return null;
        }

        String originalFilename = multipartFile.getOriginalFilename();

        String storeFileName = createStoreFileName(originalFilename);

        try {
            multipartFile.transferTo(new File(getFullPath(storeFileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new UploadFile(originalFilename, storeFileName);
    }

    public String getFullPath(String storeFileName) {
        return fileDir + storeFileName;
    }

    private String createStoreFileName(String originalFilename) {
        String ext = extractExt(originalFilename);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }


}
