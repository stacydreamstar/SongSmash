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

    String userOption;
    private JobLauncher jobLauncher;





    public List<String> textFileUploadedJob(MultipartFile file, String selectedOption) {


        Long trackingId= Math.abs(UUID.randomUUID().getMostSignificantBits());

        return null;
    }



}
