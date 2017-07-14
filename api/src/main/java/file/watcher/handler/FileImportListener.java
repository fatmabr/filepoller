package file.watcher.handler;

import file.watcher.handler.FileImportHandler;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;

/**
 * Created by bradai on 28/06/2017.
 */
public class FileImportListener implements Runnable {

    WatchService watcher;
    FileImportHandler handler;

    public FileImportListener(WatchService watcher, FileImportHandler handler) {
        this.watcher = watcher;
        this.handler = handler;
    }

    public void run() {
        while (true) {
            WatchKey key;
            try {
                key = watcher.take();
            } catch (InterruptedException ex) {
                return;
            }

            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind<?> kind = event.kind();

                WatchEvent<Path> ev = (WatchEvent<Path>) event;
                Path fileName = ev.context();

                System.out.println(kind.name() + ": " + fileName);

                if (kind == ENTRY_CREATE) {
                    System.out.println("File imported !!!");

                    handler.handle(fileName.toFile());

                }
            }

            boolean valid = key.reset();
            if (!valid) {
                break;
            }
        }
    }
}
