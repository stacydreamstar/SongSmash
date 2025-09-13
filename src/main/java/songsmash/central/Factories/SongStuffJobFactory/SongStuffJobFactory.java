package songsmash.central.Factories.SongStuffJobFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class SongStuffJobFactory{

    @Autowired
    String inputType;





    public List<String> textFileUploaded(MultipartFile file) {
        Long trackingId= Math.abs(UUID.randomUUID().getMostSignificantBits());

        return null;
    }



}
