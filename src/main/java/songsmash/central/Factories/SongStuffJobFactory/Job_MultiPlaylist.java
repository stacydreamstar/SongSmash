package songsmash.central.Factories.SongStuffJobFactory;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableBatchProcessing
public class Job_MultiPlaylist {

    @Bean
    public Job exampleJob() {
        return jobBuilderFactory.get("exampleJob")
                .start(exampleStep())
                .build();
    }

    @Bean("exampleStep")
    public Step exampleStep() {
                .<String, String>chunk(10)
                .reader(exampleReader())
                .processor(exampleProcessor())
                .writer(exampleWriter())
                .build();
    }

    @Bean
    public ItemReader<String> exampleReader() {
        return new ItemReader<>() {
            private int count = 0;

            @Override
            public String read() {
                if (count < 5) {
                    return "Item " + count++;
                }
                return null; // Signals end of data
            }
        };
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
