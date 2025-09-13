package songsmash.central.Factories.SongStuffJobFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    JobLauncher jobLauncher;
    String userOption;
    public void setUserOption(String option){
        this.userOption = option;
    }

    public List<String> determinerTree(String input, Long trackingId, MultipartFile file){

        switch(input){
            case "MULTI_PLAYLIST" -> {
                System.out.println("starting job: " + input);

                try {
                    JobParameters jobParameters = new JobParametersBuilder()
                            .addString("fileName", file.getOriginalFilename())
                            .addString("selectedOption", input)
                            .addLong("trackingId", trackingId)
                            .toJobParameters();
                    JobExecution jobExecution = jobLauncher.run(exampleJob(), jobParameters);
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
    public List<String> textFileUploadedJob(MultipartFile file, String selectedOption) {
        Long trackingId= Math.abs(UUID.randomUUID().getMostSignificantBits());
        determinerTree(selectedOption, trackingId, file);


        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addString("fileName", file.getOriginalFilename())
                    .addString("selectedOption", selectedOption)
                    .addLong("trackingId", trackingId)
                    .toJobParameters();

            JobExecution jobExecution = jobLauncher.run(exampleJob(), jobParameters);
            System.out.println("Job Status: " + jobExecution.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }



}
