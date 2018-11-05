package com.hasanrahman.rouletteapi.controllers

import com.hasanrahman.rouletteapi.services.BetService
import com.hasanrahman.rouletteapi.utility.versions
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
		@RequestParam(value = "version") version: versions,
		httpSession: HttpSession
	) = betService.setVersion(version, httpSession)
	
	@GetMapping("getVersion")
	fun getVersion(httpSession: HttpSession) = betService.getVersion(httpSession)
	
}