package com.example.eventapp.config;

import com.example.eventapp.exception.UnauthorizedException;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

@Component
public class SessionUtil {

    public Long getUserId(
            HttpSession session
    ) {

        Object userId =
                session.getAttribute("userId");

        if (userId == null) {

            throw new UnauthorizedException(
                    "Giriş yapmanız gerekiyor"
            );
        }

        return (Long) userId;
    }
}
