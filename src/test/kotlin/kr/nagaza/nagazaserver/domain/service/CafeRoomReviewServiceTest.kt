package kr.nagaza.nagazaserver.domain.service

import kr.nagaza.nagazaserver.domain.repository.CafeRoomReviewRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

@ExtendWith(MockitoExtension::class)
class CafeRoomReviewServiceTest {
    @Test
    fun `getAllByUserId() 테스트`() {
        // given
        val cafeRoomReviewRepository = mock<CafeRoomReviewRepository>() {
             on { getAllByUserId(any()) } doReturn listOf()
        }
        val cafeRoomReviewService = CafeRoomReviewService(cafeRoomReviewRepository)
        // when
        val retrievedCafeRoomReviews = cafeRoomReviewService.getAllByUserId("test")
        // then
        assert(retrievedCafeRoomReviews.isEmpty())
    }

    @Test
    fun `getAllByRoomId() 테스트`() {
        // given
        val cafeRoomReviewRepository = mock<CafeRoomReviewRepository>() {
            on { getAllByRoomId(any()) } doReturn listOf()
        }
        val cafeRoomReviewService = CafeRoomReviewService(cafeRoomReviewRepository)
        // when
        val retrievedCafeRoomReviews = cafeRoomReviewService.getAllByRoomId("test")
        // then
        assert(retrievedCafeRoomReviews.isEmpty())
    }
}
