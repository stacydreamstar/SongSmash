package songsmash.central.controllers;
import Vo.Option;
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


//when u get a txt file what do w it
    @PostMapping("/songstuff/txt_file")
    public ResponseEntity<List<String>> handleSongStuffPostRequest(@RequestParam("file") MultipartFile file, @RequestBody Option option) {

        String doOption = option.getUserOption();;

        try {
            String tempFilePath = "/tmp/" + file.getOriginalFilename();
            //taskExecutor.execute(new songStuffServiceFileUploaded.textFileUploaded(file));
            taskExecutor.execute(new StartDaProcessMPF(tempFilePath, doOption));
            return ResponseEntity.ok(dummyListResponse);


        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }

    }




    private class StartDaProcessMPF implements Runnable {


        private MultipartFile file;
        private String option;




        public StartDaProcessMPF(MultipartFile file, String option) {
            this.file = file;
            this.option = option;
        }


        @Override
        public void run() {
            // Process the file here
            System.out.println("Processing file: " + file.getOriginalFilename());
            // Add your file processing logic here
            songStuffServiceFileUploaded.textFileUploaded(file, option);
        }
    }











}
