package org.letter33.commerce;

import org.springframework.boot.SpringApplication;

public class TestCommerceApplication {

    public static void main(String[] args) {
        SpringApplication.from(CommerceApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
