package songsmash.central.factories.SongStuffJobFactory;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import org.springframework.transaction.PlatformTransactionManager;




@Configuration
@EnableBatchProcessing
@Component
public class Job_MultiPlaylist {


    @Qualifier("songLinkItemReader") ItemReader<String> songLinkItemReader;
    @Qualifier("songLinkItemProcessor") ItemProcessor<String, String> songLinkItemProcessor;




    @Bean("parseInputStep")
    public Step parseInputStep(JobRepository jobRepository, PlatformTransactionManager transactionManager
                               ) {
        return new StepBuilder("parseInputStep", jobRepository)
                .<String, String>chunk(1, transactionManager)
                .reader(songLinkItemReader)
                .processor(exampleProcessor())
                .writer(exampleWriter())
                .build();
    }







    @Bean
    public ItemProcessor<String, String> exampleProcessor() {
        return item -> "Processed " + item;
    }

    @Bean
    public ItemWriter<String> exampleWriter() {
        return items -> items.forEach(System.out::println);
    }
}
