package kr.nagaza.nagazaserver.domain.service

import kr.nagaza.nagazaserver.domain.model.Cafe
import kr.nagaza.nagazaserver.domain.repository.CafeRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on

@ExtendWith(MockitoExtension::class)
class CafeServiceTest {
    @Test
    fun `getAllCafes() 테스트`() {
        // given
        val cafes = listOf(
            Cafe(
                id = "1",
                name = "test",
                description = "test",
                address = "test",
                addressOne = "test",
                addressTwo = "test",
                franchiseId = "test",
                locationLat = 0.0,
                locationLng = 0.0,
                phoneNumber = "test",
                webUrl = "test",
            )
        )
        val cafeRepository = mock<CafeRepository> {
            on { findAll() } doReturn cafes
        }
        val cafeService = CafeService(cafeRepository)
        // when
        val retrievedCafes = cafeService.getAllCafes()
        // then
        assert(retrievedCafes == cafes)
    }

    @Test
    fun `getAllByAddresses() 테스트`() {
        // given
        val cafes = listOf(
            Cafe(
                id = "1",
                name = "test",
                description = "test",
                address = "test",
                addressOne = "test",
                addressTwo = "test",
                franchiseId = "test",
                locationLat = 0.0,
                locationLng = 0.0,
                phoneNumber = "test",
                webUrl = "test",
            )
        )
        val cafeRepository = mock<CafeRepository> {
            on { findAllByAddresses(any(), any()) } doReturn cafes
        }
        val cafeService = CafeService(cafeRepository)
        // when
        val retrievedCafes = cafeService.getAllByAddresses("test", "test")
        // then
        assert(retrievedCafes == cafes)
    }

    @Test
    fun `getById() 테스트`() {
        // given
        val cafe = Cafe(
            id = "1",
            name = "test",
            description = "test",
            address = "test",
            addressOne = "test",
            addressTwo = "test",
            franchiseId = "test",
            locationLat = 0.0,
            locationLng = 0.0,
            phoneNumber = "test",
            webUrl = "test",
        )
        val cafeRepository = mock<CafeRepository> {
            on { findById(any()) } doReturn cafe
        }
        val cafeService = CafeService(cafeRepository)
        // when
        val retrievedCafe = cafeService.getById("1")
        // then
        assert(retrievedCafe == cafe)

    }
}
