package songsmash.central.processors;

import org.springframework.batch.item.ItemProcessor;

public class SongLinkItemProcessor implements ItemProcessor<String, String> {
    @Override
    public String process(String item) throws Exception {


        // Here you can add any processing logic if needed
        return item.trim(); // For example, just trim whitespace
    }


}
