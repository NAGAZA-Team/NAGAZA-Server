package kr.nagaza.nagazaserver.infrastructure.ulid

import com.github.f4b6a3.ulid.UlidCreator
import kr.nagaza.nagazaserver.domain.repository.IdentifierProvider
import org.springframework.stereotype.Component

@Component
class UlidIdentifierProvider : IdentifierProvider {
    override fun generate(): String {
        return UlidCreator.getMonotonicUlid().toString()
    }
}
