package org.ecommerceshop.config;

import org.ecommerceshop.model.Product;
import org.ecommerceshop.model.User;
import org.ecommerceshop.repository.ProductRepository;
import org.ecommerceshop.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(ProductRepository productRepository, UserRepository userRepository) {
        return args -> {
//            productRepository.save(new Product("Ebook 1", "Description 1", new BigDecimal("9.99")));
//            productRepository.save(new Product("Ebook 2", "Description 2", new BigDecimal("14.99")));
//
//            userRepository.save(new User("user1", "password1"));
//            userRepository.save(new User("user2", "password2"));
        };
    }
}

