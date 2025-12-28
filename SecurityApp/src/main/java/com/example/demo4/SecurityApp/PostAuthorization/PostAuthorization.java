package com.example.demo4.SecurityApp.PostAuthorization;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component("postAuth")
@RequiredArgsConstructor
public class PostAuthorization {


    public boolean canViewPost(Long postId, Authentication authentication) {
        // role check
        System.out.println(authentication.getPrincipal());

        return authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
    }

}
