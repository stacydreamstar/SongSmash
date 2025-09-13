package songsmash.central.Factories.SongStuffJobFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import java.io.File;
import java.util.UUID;

@Service
@Configuration
@EnableBatchProcessing
public class SongStuffJobFactory{

    Job_MultiPlaylist buildMultiPlaylistX = new Job_MultiPlaylist();

    @Autowired
    JobLauncher jobLauncher;
    String userOption;
    public void setUserOption(String option){
        this.userOption = option;
    }

    public List<String> determinerTree(String input, Long trackingId, @Qualifier("file") String filePath){

        switch(input){
            case "MULTI_PLAYLIST" -> {
                System.out.println("starting job: " + input);

                try {
                    JobParameters jobParameters = new JobParametersBuilder()
                            .addString("filePath", filePath)
                            .addString("selectedOption", input)
                            .addLong("trackingId", trackingId)
                            .toJobParameters();
                    JobExecution jobExecution = jobLauncher.run(buildMultiPlaylistT(), jobParameters);
                    System.out.println("Job Status: " + jobExecution.getStatus());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
            case "SUMN_ELSE" -> {
                System.out.println("output is a List. Elements: " + input);
                return null;
            }
            case "SUMN_ELSEX" -> {
                System.out.println("output is a File: " + (input));
                return null;
            }
            default -> System.out.println("Input is of type: " + input.getClass().getName());
            return null;

        }

    }




    @Bean("textFileUploadedJob")
    public List<String> textFileUploadedJob(String filePath, String selectedOption) {
        Long trackingId= Math.abs(UUID.randomUUID().getMostSignificantBits());
        determinerTree(selectedOption, trackingId, filePath);


        return null;
    }

    @Bean("buildMultiPlaylistT")
    public Job buildMultiPlaylistT() {
        return new JobBuilder("exampleJob")
                .start(buildMultiPlaylistX.parseInputStep())
                .next()
                .build();
    }

//    @Bean
//    public Step exampleStep() {
//        return new StepBuilder("exampleStep")
//                .<String, String>chunk(10)
//                .reader(exampleReader())
//                .processor(exampleProcessor())
//                .writer(exampleWriter())
//                .build();
//    }




}
