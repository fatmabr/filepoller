package file.poller.sample;

import file.watcher.line.Line;
import file.watcher.processor.AbstractFileProcessor;

/**
 * Created by bradai on 28/06/2017.
 */
public class HiearchyPortforlioProcessor extends AbstractFileProcessor {

    @Override
    public void process(Line line) {
        if (!validate(line)){
            return;
        }
        System.out.print("Line: " + ((HiearchyPortfolioLine)line).getCompoCode());
    }

    @Override
    public boolean validate(Line line) {
        return true;
    }

}
