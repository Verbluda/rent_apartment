package com.example.rent_module.scheduler;

import com.example.rent_module.model.entity.UserEntity;
import com.example.rent_module.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@EnableScheduling
@RequiredArgsConstructor
public class TokenScheduler {

    private final UserRepository userRepository;

    @Scheduled(fixedRate = 1_800_000)
    private void actualiseTokens() {
        log.info("Планировщик начал свою работу");
        List<UserEntity> userEntities = userRepository.findUserEntitiesByTokenIsNotNull();
        if (userEntities.isEmpty()) {
            log.info("Текущих сессий не обнаружено");
            log.info("Планировщик закончил свою работу");
            throw new RuntimeException();
        }
        log.info("Количество пользователей для проверки = {}", userEntities.size());
        for (UserEntity user : userEntities) {
            LocalDateTime tokensDate = getTokensDate(user.getToken());
            if (isActualDate(tokensDate)) {
                user.setToken(null);
                userRepository.save(user);
                log.info("Сессия пользователя {} завершена", user.getUserName());
            } else {
                log.info("Сессия пользователя {} актуальна", user.getUserName());
            }
        }
        log.info("Планировщик закончил свою работу");
    }

    private LocalDateTime getTokensDate(String token) {
        int index = token.indexOf("|") + 1;
        String tokensDate = token.substring(index);
        return LocalDateTime.parse(tokensDate);
    }

    private boolean isActualDate(LocalDateTime tokensDate) {
        return tokensDate.isBefore(LocalDateTime.now());
    }
}
