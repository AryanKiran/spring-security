package com.example.demo4.SecurityApp.services;


import com.example.demo4.SecurityApp.entities.Session;
import com.example.demo4.SecurityApp.entities.User;
import com.example.demo4.SecurityApp.repositories.SessionRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionService {
    private final SessionRepository sessionRepository;
    private final int SESSION_LIMIT = 2;

    public void generateNewSession(User user,String refreshToken){
        List<Session> sessions = sessionRepository.findByUser(user);
        if(sessions.size() == SESSION_LIMIT){
            sessions.sort(Comparator.comparing(Session::getLastUsedAt));

            Session leastRecentlyUsedSession = sessions.getFirst();
            sessionRepository.delete(leastRecentlyUsedSession);
        }
        Session newSession = Session.builder()
                .user(user)
                .refreshToken(refreshToken)
                .build();

        sessionRepository.save(newSession);

    }

    public void validateSession(String refreshToken){
        Session session = sessionRepository.findByRefreshToken(refreshToken)
                .orElseThrow(()->new SessionAuthenticationException("Session not found for refresh Token: "+refreshToken));
        session.setLastUsedAt(LocalDateTime.now());
        sessionRepository.save(session);

    }

}
