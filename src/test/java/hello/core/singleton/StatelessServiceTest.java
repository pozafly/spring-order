package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatelessServiceTest {

    @Test
    void statelessServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(StatelessServiceTest.TestConfig.class);
        StatelessService statelessService1 = ac.getBean(StatelessService.class);
        StatelessService statelessService2 = ac.getBean(StatelessService.class);

        // ThreadA: A사용자 10000원 주문
        int userA = statelessService1.order("userA", 10000);
        // ThreadB: B사용자 20000원 주문
        int userB = statelessService1.order("userB", 20000);

        Assertions.assertThat(userA).isEqualTo(10000);
        Assertions.assertThat(userB).isEqualTo(20000);
    }

    static class TestConfig {
        @Bean
        public StatelessService statelessService() {
            return new StatelessService();
        }
    }
}
