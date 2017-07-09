package file.watcher.handler;

import file.watcher.processor.AbstractFileProcessor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by bradai on 28/06/2017.
 */
@Component
public class ProcessorRegistry implements BeanFactoryAware , InitializingBean{

    private ListableBeanFactory beanFactory;

    Map<String, AbstractFileProcessor> processorMap;

    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (ListableBeanFactory) beanFactory;
    }

    public void afterPropertiesSet() throws Exception {
        processorMap = beanFactory.getBeansOfType(AbstractFileProcessor.class);
    }

    public AbstractFileProcessor matchProcessor(String fileName){
        for (AbstractFileProcessor abstractFileProcessor : processorMap.values()) {
            final Pattern compile = Pattern.compile(abstractFileProcessor.getFilePattern());
            final Matcher matcher = compile.matcher(fileName);
            if (matcher.matches()) {
                return abstractFileProcessor;
            }
        }
        //TODO replace by a business exception the file does not match any configured processor.
        return null;
    }
}
