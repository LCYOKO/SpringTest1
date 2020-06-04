package test.myBeanFactoryPostProcess;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;
import test.pojo.Teacher;

/**
 * @author l
 * @create 2020-05-23-14:46
 */

public class MyBeanPostProcesser implements BeanFactoryPostProcessor {
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        GenericBeanDefinition definition = (GenericBeanDefinition)configurableListableBeanFactory.getBeanDefinition("student");
          definition.setBeanClass(Teacher.class);
    }
}
