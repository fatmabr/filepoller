package file.poller.sample;

import file.watcher.line.Line;
import file.watcher.parser.CSVFileParser;

/**
 * Created by bradai on 28/06/2017.
 */
public class HiearchyPortfolioParser extends CSVFileParser {

    public Line newLine(String[] line) {
        if (line != null && line.length != 0) {
            return new HiearchyPortfolioLine(line);
        } else {
            return null;
        }
    }


}
