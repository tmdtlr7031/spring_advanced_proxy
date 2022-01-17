package hello.proxy.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BasicTest {

    @Test
    void basicConfig() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BasicConfig.class);

        // A는 빈으로 등록된다.
        A a = applicationContext.getBean("beanA", A.class);
        a.hellA();

        // B는 빈으로 등록되지 않는다.
        // 해당 로직을 실행했을 때 예외가 NoSuchBeanDefinitionException의 인스턴스가 맞는지 체크 (예외가 터져야 성공하는 테스트)
        Assertions.assertThatThrownBy(() -> applicationContext.getBean(B.class))
                .isInstanceOf(NoSuchBeanDefinitionException.class);
    }

    @Slf4j
    @Configuration
    static class BasicConfig {
        @Bean(name = "beanA")
        public A a() {
            return new A();
        }

    }

    @Slf4j
    static class A {
        public void hellA() {
            log.info("hello A");
        }
    }

    @Slf4j
    static class B {
        public void hellB() {
            log.info("hello B");
        }
    }
}
