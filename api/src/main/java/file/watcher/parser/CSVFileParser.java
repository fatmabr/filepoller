package file.watcher.parser;

import au.com.bytecode.opencsv.CSVReader;
import file.watcher.line.Line;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by bradai on 28/06/2017.
 */
@Component
public abstract class CSVFileParser implements FileParser {

    Logger logger = Logger.getLogger(CSVFileParser.class.getSimpleName());

    /**
     * Il ne faut pas parser tout le fichier à la fois.
     * Il faut garder le principe d'itération.
     * *
     **/
    CSVReader csvReader;

    public List<Line> parse(File f) {
        List<Line> lines = new ArrayList();
        try {
            csvReader = new CSVReader(new InputStreamReader(new FileInputStream(f), "UTF-8"), ';');
        } catch (IOException e) {
            logger.log(Level.SEVERE, "In out execption while file " + f.getName() + " parsing : " + e.getStackTrace());
        }
        return lines;
    }


    public Line nextLine() {
        String[] strings = null;
        try {
            strings = (csvReader != null) ? csvReader.readNext() : null;
        } catch (IOException e) {
            logger.log(Level.SEVERE, "In out execption while file parsing : " + e.getStackTrace());
        }
        return strings != null ? newLine(strings) : null;
    }

}
