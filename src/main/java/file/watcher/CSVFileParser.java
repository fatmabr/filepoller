package file.watcher;

import au.com.bytecode.opencsv.CSVReader;
import file.watcher.line.Line;
import file.watcher.parser.FileParser;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by bradai on 28/06/2017.
 */
@Component
public abstract class CSVFileParser implements FileParser {


    /**
     * Il ne faut pas parser tout le fichier à la fois.
     * Il faut garder le principe d'itération.
     **
     **/
    CSVReader csvReader;

    public List<Line> parse(File f) {
        List<Line> lines = new ArrayList();
        try {
            csvReader = new CSVReader(new InputStreamReader(new FileInputStream(f), "UTF-8"), ';');
        } catch (IOException e) {
            //TODO add logs
        }
        return lines;
    }


    public Line nextLine() throws IOException {
        final String[] strings = csvReader != null ? csvReader.readNext() : null;
        return strings != null ? newLine(strings) : null;
    }

}
