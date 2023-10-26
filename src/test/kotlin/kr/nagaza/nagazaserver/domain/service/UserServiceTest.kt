package kr.nagaza.nagazaserver.domain.service

import kr.nagaza.nagazaserver.domain.model.SocialProvider
import kr.nagaza.nagazaserver.domain.model.SocialUser
import kr.nagaza.nagazaserver.domain.model.User
import kr.nagaza.nagazaserver.domain.repository.SocialUserRepository
import kr.nagaza.nagazaserver.domain.repository.UserRepository
import kr.nagaza.nagazaserver.infrastructure.jpa.projection.UserSummaryProjection
import kr.nagaza.nagazaserver.infrastructure.jpa.repository.QUserRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

@ExtendWith(MockitoExtension::class)
class UserServiceTest {
    @Test
    fun `getUserInfo() 테스트`() {
        // given
        val userId = "01HDNFJHCNS5E2W35YTB030TJ8"
        val mockUser = User(userId, "test", "test")
        val userRepository = mock<UserRepository> {
            on { findById(userId) } doReturn mockUser
        }
        val userService = UserService(userRepository, mock(), mock())

        // when
        val user = userService.getUserInfo(userId)
        // then
        assert(user.userId == mockUser.userId)
        assert(user.nickname == mockUser.nickname)
        assert(user.profileImageUrl == mockUser.profileImageUrl)
    }

    @Test
    fun `updateNickName() 테스트`() {
        // given
        val userId = "01HDNFJHCNS5E2W35YTB030TJ8"
        val mockUser = User(userId, "test", "test")
        val userRepository = mock<UserRepository> {
            on { findById(userId) } doReturn mockUser
            on { saveUser(any()) } doAnswer { it.arguments[0] as User }
        }
        val userService = UserService(userRepository, mock(), mock())

        // when
        val user = userService.updateNickName(userId, "test2")
        // then
        assert(user.userId == mockUser.userId)
        assert(user.nickname == "test2")
        assert(user.profileImageUrl == mockUser.profileImageUrl)
    }

    @Test
    fun `updateProfileImageUrl() 테스트`() {
        // given
        val userId = "01HDNFJHCNS5E2W35YTB030TJ8"
        val mockUser = User(userId, "test", "test")
        val userRepository = mock<UserRepository> {
            on { findById(userId) } doReturn mockUser
            on { saveUser(any()) } doAnswer { it.arguments[0] as User }
        }
        val userService = UserService(userRepository, mock(), mock())

        // when
        val user = userService.updateProfileImageUrl(userId, "test2")
        // then
        assert(user.userId == mockUser.userId)
        assert(user.nickname == mockUser.nickname)
        assert(user.profileImageUrl == "test2")
    }

    @Test
    fun `quitNagaza() 테스트`() {
        // given
        val userId = "01HDNFJHCNS5E2W35YTB030TJ8"
        val mockUser = User(userId, "test", "test")
        val mockSocialUser = SocialUser(
            provider = SocialProvider.APPLE,
            userId = userId,
            identifier = "test",
        )
        val userRepository = mock<UserRepository> {
            on { findById(userId) } doReturn mockUser
            on { saveUser(any()) } doAnswer { it.arguments[0] as User }
        }
        val socialUserRepository = mock<SocialUserRepository> {
            on { findAllByUserId(userId) } doReturn listOf(mockSocialUser)
            on { deleteUser(any()) } doAnswer { }
        }
        val userService = UserService(userRepository, socialUserRepository, mock())

        // when
        userService.quitNagaza(userId)

        // then ??
    }

    @Test
    fun `getUserSummary() 테스트`() {
        // given
        val userId = "01HDNFJHCNS5E2W35YTB030TJ8"
        val reviewCountPool = listOf(0, 10, 30, 50, 100)
        reviewCountPool.forEachIndexed { index, count ->
            val mockUserSummary = UserSummaryProjection(userId, count.toLong(), 1)
            val qUserRepository = mock<QUserRepository> {
                on { getUserSummary(userId) } doReturn mockUserSummary
            }
            val userService = UserService(mock(), mock(), qUserRepository)

            // when
            val userSummary = userService.getUserSummary(userId)

            // then
            assert(userSummary.reviewCount == mockUserSummary.reviewCount.toInt())
            assert(userSummary.likeCount == mockUserSummary.likeCount.toInt())
            assert(userSummary.gradeLevel == index + 1)
        }
    }
}
