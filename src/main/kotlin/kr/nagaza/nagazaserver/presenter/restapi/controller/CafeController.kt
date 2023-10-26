package kr.nagaza.nagazaserver.presenter.restapi.controller

import kr.nagaza.nagazaserver.domain.service.CafeService
import kr.nagaza.nagazaserver.presenter.restapi.api.CafeApi
import kr.nagaza.nagazaserver.presenter.restapi.dto.response.CafeResponse
import org.springframework.stereotype.Controller

@Controller
class CafeController(
    private val cafeService: CafeService,
): CafeApi {
    override fun getCafes(): List<CafeResponse> {
        return cafeService.getAllCafes().map(CafeResponse::fromModel)
    }

    override fun getCafeByCafeId(cafeId: String): CafeResponse {
        return cafeService.getById(cafeId).let(CafeResponse::fromModel)
    }
}
