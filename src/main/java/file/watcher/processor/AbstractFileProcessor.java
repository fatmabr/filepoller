package file.watcher.processor;

import file.watcher.parser.FileParser;
import file.watcher.line.Line;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by bradai on 28/06/2017.
 */
public abstract class AbstractFileProcessor {

    private String filePattern;
    private FileParser fileParser;

    public abstract void process(Line line);

    public abstract boolean validate(Line line);

    public String getFilePattern() {
        return filePattern;
    }

    public void setFilePattern(String filePattern) {
        this.filePattern = filePattern;
    }

    public FileParser getFileParser() {
        return fileParser;
    }

    public void setFileParser(FileParser fileParser) {
        this.fileParser = fileParser;
    }
}
