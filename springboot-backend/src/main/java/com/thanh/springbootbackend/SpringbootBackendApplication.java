package com.thanh.springbootbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SpringbootBackendApplication{

    public static void main(String[] args) {
        SpringApplication.run(SpringbootBackendApplication.class, args);
    }
//    @Autowired
//    UserRepository userRepository;
//    @Autowired
//    PasswordEncoder passwordEncoder;

//    @Override
//    public void run(String... args) throws Exception {
//        // Khi chương trình chạy
//        // Insert vào csdl một user.
////        User user = new User();
////        user.setUsername("thanhnv80");
////        user.setPassword(passwordEncoder.encode("12345"));
////        userRepository.save(user);
////        System.out.println(user);
//    }
}
