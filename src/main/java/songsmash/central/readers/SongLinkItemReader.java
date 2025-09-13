package songsmash.central.readers;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.item.ItemReader;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SongLinkItemReader implements ItemReader<String> {



    private final BufferedReader reader;
    private final Pattern songLinkPattern = Pattern.compile("(https?://(?:open\\\\.spotify\\\\.com|youtu(?:\\\\.be|be\\\\.com))/[\\\\w./?=&%-]+)"); // Example pattern for URLs

    public SongLinkItemReader(JobParameters jobParameters) throws Exception {
        String filePath = jobParameters.getString("filePath");

        if (filePath == null || filePath.isEmpty()) {
            throw new IllegalArgumentException("Job parameter 'filePath' is missing or empty.");
        }

        File file = new File(filePath);
        if (!file.exists()) {
            throw new IllegalArgumentException("File not found at path: " + filePath);
        }

        this.reader = new BufferedReader(new FileReader(file));
    }

    @Override
    public String read() throws Exception {
        String line;
        while ((line = reader.readLine()) != null) {
            Matcher matcher = songLinkPattern.matcher(line);
            if (matcher.find()) {
                return matcher.group(); // Return the first valid song link found
            }
        }
        return null; // Signals end of file
    }





}
