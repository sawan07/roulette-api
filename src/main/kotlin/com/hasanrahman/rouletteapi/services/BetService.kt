package com.hasanrahman.rouletteapi.services

import com.hasanrahman.rouletteapi.exceptions.VersionNotFound
import com.hasanrahman.rouletteapi.utility.*
import org.springframework.stereotype.Service
import java.util.*
import java.util.logging.Logger
import javax.servlet.http.HttpSession


@Service
class BetService {
	
	private val logger = Logger.getLogger(BetService::class.java.name)
	/**
	 * Setting the version of the roulette
	 * @param version: Versions (either US or EU)
	 * @param httpSession: HttpSession to save the version value
	 * @return saved version value
	 */
	fun setVersion(version: Versions, httpSession: HttpSession): String {
		httpSession.setAttribute(Const.VERSION, version)
		return getVersion(httpSession)
	}
	
	fun getVersion(httpSession: HttpSession): String =
		if (httpSession.getAttribute(Const.VERSION) != null)
			httpSession.getAttribute(Const.VERSION).toString()
		else throw VersionNotFound(
			"version not set"
		)
	
	fun outsideBets(amount: Double, outsideBets: OutsideBets, httpSession: HttpSession): Double {
		val number = randomize(getVersion(httpSession)).toInt()
		logger.info("rolled number is $number")
		return when (outsideBets) {
			OutsideBets.BLACK -> if (listOf(
					2,
					4,
					6,
					8,
					10,
					11,
					13,
					15,
					17,
					20,
					22,
					24,
					26,
					28,
					29,
					31,
					33,
					35
				).contains(number)
			) amount * 2 else 0.00
			OutsideBets.RED -> if (listOf(
					1,
					3,
					5,
					7,
					9,
					12,
					14,
					16,
					18,
					19,
					21,
					23,
					25,
					27,
					30,
					32,
					34,
					36
				).contains(
					number
				)
			) amount * 2 else 0.00
			OutsideBets.FIRST_DOZEN -> if (number in 1..12) amount * 3 else 0.00
			OutsideBets.SECOND_DOZEN -> if (number in 13..24) amount * 3 else 0.00
			OutsideBets.THIRD_DOZEN -> if (number in 25..36) amount * 3 else 0.00
			OutsideBets.FIRST_COLUMN -> if (listOf(
					1,
					4,
					7,
					10,
					13,
					16,
					19,
					22,
					25,
					28,
					31,
					34
				).contains(number)
			) amount * 3 else 0.00
			OutsideBets.SECOND_COLUMN -> if (listOf(
					2,
					5,
					8,
					11,
					14,
					17,
					20,
					23,
					26,
					29,
					32,
					35
				).contains(number)
			) amount * 3 else 0.00
			OutsideBets.THIRD_COLUMN -> if (listOf(
					3,
					6,
					9,
					12,
					15,
					18,
					21,
					24,
					27,
					30,
					33,
					36
				).contains(number)
			) amount * 3 else 0.00
			OutsideBets.LOW -> if (number in 1..18) amount * 2 else 0.00
			OutsideBets.HIGH -> if (number in 19..36) amount * 2 else 0.00
			OutsideBets.EVEN -> if (number % 2 == 0) amount * 2 else 0.00
			OutsideBets.ODD -> if (number % 2 == 1) amount * 2 else 0.00
		}
	}
	
	private fun randomize(version: String): String {
		return if (version == Versions.EU_VERSION.toString())
			Random().nextInt(37).toString()
		else {
			numbers[Random().nextInt(38)]
		}
	}
	
	fun insideBets(amount: Double, insideBets: InsideBets, numbers: Array<String>, httpSession: HttpSession): Double {
		val version = getVersion(httpSession)
		val number = randomize(version)
		logger.info("rolled number is $number")
		return if (numbers.contains(number)) {
			when (insideBets) {
				InsideBets.SINGLE -> amount * 36
				InsideBets.SPLIT -> amount * 18
				InsideBets.STREET -> amount * 12
				InsideBets.SQUARE -> amount * 9
				InsideBets.DOUBLE_STREET -> amount * 6
				InsideBets.TRIO -> amount * 12
				InsideBets.BASKET -> if (version == Versions.EU_VERSION.toString()) amount * 9 else amount * 7
			}
		} else 0.00
		
	}
	
}