package kr.nagaza.nagazaserver.domain.service

import kr.nagaza.nagazaserver.domain.model.Cafe
import kr.nagaza.nagazaserver.domain.model.CafeRoom
import kr.nagaza.nagazaserver.domain.repository.CafeRepository
import kr.nagaza.nagazaserver.domain.repository.CafeRoomRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

@ExtendWith(MockitoExtension::class)
class CafeRoomServiceTest {
    @Test
    fun `getRoomsByCafeId() 테스트`() {
        // given
        val cafeId = "1"
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
        val cafeRepository = mock<CafeRepository>() {
            on { findById(cafeId) } doReturn cafe
        }
        val cafeRoomRepository = mock<CafeRoomRepository>() {
            on { getAllRoomByCafeId(cafeId) } doReturn listOf()
        }
        val cafeRoomService = CafeRoomService(cafeRepository, cafeRoomRepository)
        // when
        val cafeRooms = cafeRoomService.getRoomsByCafeId(cafeId)
        // then
        assert(cafeRooms.isEmpty())
    }

    @Test
    fun `getRoomById() 테스트`() {
        // given
        val roomId = "1"
        val cafeRoom = CafeRoom(
            roomId = roomId,
            cafeId = "1",
            genre = "test",
            description = "test",
            timeout = 0,
            recommendedUserCnt = 0,
            roomImgUrl = null
        )
        val cafeRoomRepository = mock<CafeRoomRepository>() {
            on { findByRoomId(roomId) } doReturn cafeRoom
        }
        val cafeRoomService = CafeRoomService(mock(), cafeRoomRepository)
        // when
        val retrievedCafeRoom = cafeRoomService.getRoomByRoomId(roomId)
        // then
        assert(retrievedCafeRoom == cafeRoom)
    }
}
