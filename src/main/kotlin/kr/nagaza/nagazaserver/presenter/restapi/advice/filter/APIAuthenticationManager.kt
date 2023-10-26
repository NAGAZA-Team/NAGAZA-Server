package kr.nagaza.nagazaserver.presenter.restapi.advice.filter

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class APIAuthenticationManager : AuthenticationManager {
    override fun authenticate(authentication: Authentication): Authentication {
        return authentication
    }
}
