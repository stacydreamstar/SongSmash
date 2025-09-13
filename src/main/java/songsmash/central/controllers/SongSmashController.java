package songsmash.central.controllers;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import songsmash.central.services.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.ResponseEntity;
import songsmash.central.services.SongStuffService.SongStuffService;

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



    @PostMapping("/songstuff/txt_file")
    public ResponseEntity<List<String>> handleSongStuffPostRequest(@RequestParam("file") MultipartFile file) {

        try {
            //taskExecutor.execute(new songStuffServiceFileUploaded.textFileUploaded(file));
            taskExecutor.execute(new StartDaProcess(file));
            return ResponseEntity.ok(dummyListResponse);


        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }

    }




    private class StartDaProcess implements Runnable {



        private MultipartFile file;
        public StartDaProcess(MultipartFile file) {
            this.file = file;
        }

        private String otherX;
        public StartDaProcess(String string) {
            this.otherX = otherX;
        }

        @Override
        public void run() {
            // Process the file here
            System.out.println("Processing file: " + file.getOriginalFilename());
            // Add your file processing logic here
            songStuffServiceFileUploaded.textFileUploaded(file);
        }
    }











}
