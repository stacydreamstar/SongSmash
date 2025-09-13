package songsmash.central.controllers;
import Vo.Option;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import songsmash.central.services.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.ResponseEntity;
import songsmash.central.services.SongStuffService.SongStuffService;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/inter/songsmash")
public class SongSmashController {


    @Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    private SongStuffService songStuffServiceFileUploaded;
    private SongStuffService songStuffServicexUploaded;

    List<String> dummyListResponse;


//when u get a txt file what do w it
    @PostMapping("/songstuff/txt_file")
    public ResponseEntity<List<String>> handleSongStuffPostRequest(@RequestParam("file") MultipartFile file, @RequestBody Option option) {

        String doOption = option.getUserOption();;

        try {
            String tempFilePath = saveToTempLocation(file);


            //taskExecutor.execute(new songStuffServiceFileUploaded.textFileUploaded(file));
            taskExecutor.execute(new StartDaProcessMPF(tempFilePath, doOption));
            return ResponseEntity.ok(dummyListResponse);


        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }

    }

    public String saveToTempLocation(MultipartFile file) throws Exception {
        // Define the temporary directory and file path
        String tempDir = System.getProperty("java.io.tmpdir");
        String tempFilePath = tempDir + File.separator + file.getOriginalFilename();

        // Save the file to the temporary location
        File tempFile = new File(tempFilePath);
        file.transferTo(tempFile);

        // Return the path of the saved file
        return tempFilePath;
    }




    private class StartDaProcessMPF implements Runnable {


        private MultipartFile file;
        private String option;

        private String tempFilePath;




        public StartDaProcessMPF(String tempFilePath, String option) {
            //this.file = file;
            this.option = option;
            this.tempFilePath = tempFilePath;
        }


        @Override
        public void run() {
            // Process the file here
            System.out.println("Processing file: " + file.getOriginalFilename());
            // Add your file processing logic here
            songStuffServiceFileUploaded.textFileUploaded(tempFilePath, option);
        }
    }











}
