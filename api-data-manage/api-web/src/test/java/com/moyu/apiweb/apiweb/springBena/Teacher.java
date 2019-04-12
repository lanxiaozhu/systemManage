package com.moyu.apiweb.apiweb.springBena;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;

/**
 * @Auther: wishm
 * @Date: 2019/4/11 23:50
 * @Description:
 */
public class Teacher implements BeanNameAware, BeanFactoryAware {
    @Override
    public void setBeanName(String s) {
        System.out.println("the setBeanName bean name is : "+s);
    }

    /**
     *  Object  object = beanFactory.getBean(beanName);
     * 我们既然可以通过set来拿到我们要的对象，为什么还要用这个beanFactory呢，道理很简单，因为有些情况是需要动态的去获取 对象的，比如说我有10个银行的处理对象，他们都继承了我的BankService对象，但是具体处理的时候要哪家银行的对象呢？这个依赖于用户的选择。 你可以注入10个BankService实例，然后用if --else来搞，不过那样太坨了。每增加一家银行你都需要改代码。
     * 通过beanFactory的话，那就一行代码搞定，只要给beanName就OK了，动点脑筋吧beanName配置的有规律点，然后根据用户的银行选择，凑出个beanName，大功告成了！
     * @param beanFactory
     * @throws BeansException
     */
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        //我可以通过 getBean 方法 构建出工厂模式 产出的对象类!
        System.out.println("the setBeanFactory bean name is : "+beanFactory.toString());

    }

    public void init(){
        System.out.println("Car's Init...");
    }

    public void destory(){
        System.out.println("Car's Destroy...");
    }
}
