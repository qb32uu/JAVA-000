package com.training.week05.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class SpringAddBeanDemo {
    private ApplicationContext context;

    // 2、（必做）写代码实现Spring Bean的装配，方式越多越好（XML、Annotation都可以）,提
//    交到Github。
    public static void main(String[] args) {
        SpringAddBeanDemo demo = new SpringAddBeanDemo();
        demo.function1();
        demo.function2();
        demo.function3();
        demo.function4();
        demo.function5();

    }

    private void show(Student student) {
        System.out.println(student.toString());
        System.out.println(
                "   context.getBeanDefinitionNames() ===>> " + String.join(",", context.getBeanDefinitionNames()));
    }

    /**
     * 自动扫描注解
     */
    public void function1() {
        context = new ClassPathXmlApplicationContext("week05/addBean/applicationContext1.xml");
        show(context.getBean(Student.class));

    }

    /**
     * 配置beans文件
     */
    public void function2() {
        context = new ClassPathXmlApplicationContext("week05/addBean/applicationContext2.xml");
        show(context.getBean(Student.class));
    }

    /**
     * 静态beans工厂
     */
    public void function3() {
        context = new ClassPathXmlApplicationContext("week05/addBean/applicationContext3.xml");
        show(context.getBean(Student.class));
    }

    /**
     * 实例beans工厂
     */
    public void function4() {
        context = new ClassPathXmlApplicationContext("week05/addBean/applicationContext4.xml");
        show(context.getBean(Student.class));
    }

    /**
     * 通过bean注解方式
     */
    public void function5() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(SpringAddBeanDemo.class);
        context.refresh();

        show(context.getBean(Student.class));
        context.close();
    }

    @Bean(name = "student")
    public Student getStudent() {
        return new Student(2, "name");
    }
}
