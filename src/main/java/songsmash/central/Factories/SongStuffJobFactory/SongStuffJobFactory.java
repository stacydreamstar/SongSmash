package songsmash.central.Factories.SongStuffJobFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class SongStuffJobFactory{

    String userOption;





    public List<String> textFileUploadedJob(MultipartFile file, String selectedOption) {


        Long trackingId= Math.abs(UUID.randomUUID().getMostSignificantBits());

        return null;
    }



}
