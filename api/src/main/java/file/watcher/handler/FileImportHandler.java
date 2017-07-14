package file.watcher.handler;

import file.watcher.line.Line;
import file.watcher.parser.FileParser;
import file.watcher.processor.AbstractFileProcessor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

/**
 * Created by bradai on 28/06/2017.
 */


@Component
public class FileImportHandler implements InitializingBean {

    @Autowired
    ProcessorRegistry registry;

    @Value( "${watched.file}" )
    private String watchedDir;

    public void handle(File file) {
        final AbstractFileProcessor fileProcessor = registry.matchProcessor(file.getName());
        if (fileProcessor != null) {
            FileParser fileParser = fileProcessor.getFileParser();
            fileParser.parse(file);
            Line line;
            while ((line = fileParser.nextLine()) != null) {
                fileProcessor.process(line);
            }
        }
    }


    public void afterPropertiesSet() throws Exception {
        Path dir = Paths.get(watchedDir);
        WatchService watcher = FileSystems.getDefault().newWatchService();

        WatchKey key = dir.register(watcher,
                StandardWatchEventKinds.ENTRY_CREATE,
                ENTRY_DELETE,
                ENTRY_MODIFY);
        final FileImportListener importListener = new FileImportListener(watcher, this);
        final Thread thread = new Thread(importListener);
        thread.start();
    }
}
