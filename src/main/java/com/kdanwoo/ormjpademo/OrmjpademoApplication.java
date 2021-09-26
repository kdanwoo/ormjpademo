package com.kdanwoo.ormjpademo;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.kdanwoo.ormjpademo.jpademo.Hello;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OrmjpademoApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrmjpademoApplication.class, args);
    }

    @Bean
    Hibernate5Module hibernate5Module() {
        Hibernate5Module hibernate5Module = new Hibernate5Module();
        //hibernate5Module.configure(Hibernate5Module.Feature.FORCE_LAZY_LOADING,true);
        // 이 옵션을 키면 order -> member ,
        // member -> orders 양방향 연관관계를 계속 로딩하게 된다. 따라서 @JsonIgnore 옵션을 한곳에 주어야 한다.
        // 엔티티를 직접 노출할 때는 양방향 연관관계가 걸린 곳은 꼭! 한곳을 @JsonIgnore 처리 해야 한다. 안그러면 양쪽을 서로 호출하면서 무한 루프가 걸린다.


        return hibernate5Module;
    }

}
