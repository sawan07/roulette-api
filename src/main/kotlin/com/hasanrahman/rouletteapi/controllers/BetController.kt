package com.hasanrahman.rouletteapi.controllers

import com.hasanrahman.rouletteapi.services.BetService
import com.hasanrahman.rouletteapi.utility.InsideBets
import com.hasanrahman.rouletteapi.utility.OutsideBets
import com.hasanrahman.rouletteapi.utility.Versions
import io.swagger.annotations.ApiParam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpSession

@RestController
@RequestMapping("roulette")
class BetController @Autowired constructor(private val betService: BetService) {
	@PostMapping("setVersion")
	fun setVersion(
		@ApiParam(value = "version", required = true)
		@RequestParam(value = "version") version: Versions,
		httpSession: HttpSession
	) = betService.setVersion(version, httpSession)
	
	@GetMapping("getVersion")
	fun getVersion(httpSession: HttpSession) = betService.getVersion(httpSession)
	
	@GetMapping("outsideBet")
	fun outsideBetCount(
		@ApiParam(value = "amount", required = true)
		@RequestParam(value = "amount") amount: Double,
		@ApiParam(value = "outsideBets", required = true)
		@RequestParam(value = "outsideBets") outsideBets: OutsideBets,
		httpSession: HttpSession
	): Double {
		return betService.outsideBets(amount, outsideBets, httpSession)
	}
	
	@GetMapping("insideBet")
	fun insideBetCount(
		@ApiParam(value = "amount", required = true)
		@RequestParam(value = "amount") amount: Double,
		@ApiParam(value = "insideBets", required = true)
		@RequestParam(value = "insideBets") insideBets: InsideBets,
		@ApiParam(value = "numbers", required = true)
		@RequestParam(value = "numbers") numbers: Array<String>,
		httpSession: HttpSession
	): Double {
		return betService.insideBets(amount, insideBets, numbers, httpSession)
	}
	
}