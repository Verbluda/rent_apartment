package com.example.auth_module.aspect;

import com.example.auth_module.model.dto.UserRegistrationRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class EmailValidator {

    public static final String POINT_CUT_1 = "execution(* com.example.auth_module.repository.impl.UserCriteriaRepositoryImpl.findUserByEmail(*)) && args(email)";
    public static final String POINT_CUT_2 = "execution(* com.example.auth_module.service.impl.AuthServiceImpl.addUser(*)) && args(user)";

    @Before(value = POINT_CUT_1)
    public void validateEmailForLogging(String email) {
        log.info("-> метод validateEmail начал работу");
        if (!email.matches("^[\\w-\\.]+@[\\w-]+(\\.[\\w-]+)*\\[a-z]{2,}$")) {
            throw new RuntimeException("Недопустимый формат email-вдреса");
        }
        log.info("-> метод validate закончил работу");
    }

    @Before(value = POINT_CUT_2)
    public void validateEmailForRegistration(UserRegistrationRequestDto user) {
        log.info("-> метод validateEmail начал работу");
        if (!user.getEmail().matches("^[\\w-\\.]+@[\\w-]+(\\.[\\w-]+)*\\[a-z]{2,}$")) {
            throw new RuntimeException("Недопустимый формат email-вдреса");
        }
    }
}
