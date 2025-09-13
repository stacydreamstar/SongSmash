package songsmash.central.services.SongStuffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import songsmash.central.factories.SongStuffJobFactory.SongStuffJobFactory;


import java.util.List;
import java.util.UUID;

@Service
public class SongStuffService<T> {

    @Autowired
    SongStuffJobFactory songStuffJobFactory= new SongStuffJobFactory();




    //use determine tree at the end to provide type of input specification we are eventusally going to give out
    public void determineTree(T output){


        String inputClass = output.getClass().getSimpleName();

        switch(inputClass){
            case "String" -> {
                System.out.println("output is a String: " + output);
                return;
            }
            case "ArrayList" -> {
                System.out.println("output is a List. Elements: " + output);
            }
            case "File" -> {
                System.out.println("output is a File: " + ((java.io.File) output).getName());
            }

            default -> System.out.println("Input is of type: " + output.getClass().getName());

        }

    }



@Bean("textFileUploaded")
    public List<String> textFileUploaded(String tempFilePath, String option) {



        Long trackingId= Math.abs(UUID.randomUUID().getMostSignificantBits());
        return  songStuffJobFactory.textFileUploadedJob(tempFilePath, option);



    }


}
