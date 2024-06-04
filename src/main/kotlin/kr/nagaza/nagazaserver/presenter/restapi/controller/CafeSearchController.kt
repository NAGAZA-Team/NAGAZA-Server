package kr.nagaza.nagazaserver.presenter.restapi.controller

import kr.nagaza.nagazaserver.domain.service.CafeRoomService
import kr.nagaza.nagazaserver.domain.service.CafeService
import kr.nagaza.nagazaserver.presenter.restapi.api.CafeSearchApi
import kr.nagaza.nagazaserver.presenter.restapi.dto.response.CafeAreaFilterResponse
import kr.nagaza.nagazaserver.presenter.restapi.dto.response.CafeResponse
import org.springframework.stereotype.Controller

@Controller
class CafeSearchController(
    private val cafeService: CafeService,
    private val cafeRoomService: CafeRoomService,
) : CafeSearchApi {
    override fun searchCafe(query: String): List<CafeResponse> {
        return cafeService
            .searchCafeByQuery(query)
            .map(CafeResponse::fromModel)
    }

    override fun searchCafeByAddress(
        addressOne: String,
        addressTwo: String,
    ): List<CafeResponse> {
        return cafeService.getAllByAddresses(
            addressOne = addressOne,
            addressTwo = addressTwo,
        ).map(CafeResponse::fromModel)
    }

    override fun getCafeAreaFilters(): List<CafeAreaFilterResponse> {
        val cafes =
            cafeService
                .getAllCafes()
                .filter { it.addressOne != null && it.addressTwo != null }
        val addrOneGroup =
            cafes
                .groupBy { it.addressOne!! } // i.e. 서울시 전체
        val addrTwoGroup =
            cafes
                .groupBy { it.addressOne!! to it.addressTwo!! } // i.e. 강남구 전체

        val resultList = addrTwoGroup.toMutableMap()
        resultList["전국" to "전국 전체"] = cafes
        addrOneGroup.forEach { (addrOne, relatedCafes) ->
            resultList[addrOne to "$addrOne 전체"] = relatedCafes
        }

        return resultList
            .map {
                CafeAreaFilterResponse(
                    addressOne = it.key.first,
                    addressTwo = it.key.second,
                    roomCount = it.value.size,
                )
            }
    }
}
