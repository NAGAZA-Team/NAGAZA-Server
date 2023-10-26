package kr.nagaza.nagazaserver.presenter.restapi.controller

import kr.nagaza.nagazaserver.presenter.restapi.api.CafeApi
import kr.nagaza.nagazaserver.presenter.restapi.dto.response.CafeResponse
import org.springframework.stereotype.Controller

@Controller
class CafeController: CafeApi {
    override fun getCafes(): List<CafeResponse> {
        TODO("Not yet implemented")
    }

    override fun getCafeByCafeId(cafeId: String): CafeResponse {
        TODO("Not yet implemented")
    }
}
