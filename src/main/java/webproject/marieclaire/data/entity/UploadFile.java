package webproject.marieclaire.data.entity;

import javax.persistence.Embeddable;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@Embeddable
public class UploadFile {

    private String uploadFileName;

    private String storeFileName;

//    private boolean exist;

    protected UploadFile() {

    }

    public UploadFile(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }

}
