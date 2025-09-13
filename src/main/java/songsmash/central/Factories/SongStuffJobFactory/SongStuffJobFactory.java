package songsmash.central.Factories.SongStuffJobFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
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

    private JobRepository jobRepository;

   // private ItemReader<String> songLinkItemReader;

    @Autowired
    private PlatformTransactionManager transactionManager;
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    Job_MultiPlaylist buildMultiPlaylistX = new Job_MultiPlaylist();

    @Autowired
    JobLauncher jobLauncher;
    String userOption;
    public void setUserOption(String option){
        this.userOption = option;
    }

    public List<String> determinerTree(String option, Long trackingId, String tempFilePath){

        switch(option){
            case "MULTI_PLAYLIST" -> {
                System.out.println("starting job: " + option);

                try {

                    File file = new File(tempFilePath);

                    if (!file.exists()) {
                        throw new IllegalArgumentException("File not found at path: " + tempFilePath);
                    }

                    if (!file.getName().endsWith(".txt")) {
                        throw new IllegalArgumentException("Invalid file type. Only .txt files are supported.");
                    }






                    JobParameters jobParameters = new JobParametersBuilder()
                            .addString("selectedOption", option)
                            .addLong("trackingId", trackingId)
                            .addString("filePath", tempFilePath) // Add file path here
                            .toJobParameters();

                    Job buildMultiPlaylistJob = applicationContext.getBean("buildMultiPlaylistX", Job.class);
                    JobExecution jobExecution = jobLauncher.run(buildMultiPlaylistJob, jobParameters);
                    System.out.println("Job Status: " + jobExecution.getStatus());

                    System.out.println("Job Status: " + jobExecution.getStatus());
                    return null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
            case "SUMN_ELSE" -> {
                System.out.println("output is a List. Elements: " + option);
                return null;
            }
            case "SUMN_ELSEX" -> {
                System.out.println("output is a File: " + (option));
                return null;
            }
            default -> System.out.println("Input is of type: " + option.getClass().getName());

        }
        return null;

    }




    @Bean("textFileUploadedJob")
    public List<String> textFileUploadedJob(String tempFilePath, String selectedOption) {
        Long trackingId= Math.abs(UUID.randomUUID().getMostSignificantBits());
        determinerTree(selectedOption, trackingId, tempFilePath);


        return null;
    }

    @Bean("buildMultiPlaylistX")
    public Job buildMultiPlaylistX(JobParameters jobParameters) {
        return new JobBuilder("buildMultiPlaylistX_Run", jobRepository)
                .start(buildMultiPlaylistX.parseInputStep( jobRepository, transactionManager))
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
