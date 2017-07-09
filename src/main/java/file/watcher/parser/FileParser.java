package file.watcher.parser;

import file.watcher.line.Line;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by bradai on 28/06/2017.
 */
public interface FileParser {
     List<Line> parse(File file);
     Line newLine(String[] line);

    Line nextLine() throws IOException;
}
