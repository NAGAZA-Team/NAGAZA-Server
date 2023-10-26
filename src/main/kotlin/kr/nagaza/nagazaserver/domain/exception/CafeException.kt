package kr.nagaza.nagazaserver.domain.exception

class CafeNotFoundException : DomainException(ErrorCode.CAFE_NOT_FOUND)

class CafeRoomNotFoundException : DomainException(ErrorCode.CAFE_ROOM_NOT_FOUND)
