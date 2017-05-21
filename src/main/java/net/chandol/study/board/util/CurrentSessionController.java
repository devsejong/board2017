package net.chandol.study.board.util;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrentSessionController {
    @GetMapping("/session/user")
    public Authentication auth(Authentication auth){
        return auth;
    }
}
