package com.hasanrahman.rouletteapi.services

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
	
	/**
	 * Getting the current version of roulette
	 *  @param httpSession: HttpSession to save the version value
	 *  @return saved version value
	 */
	fun getVersion(httpSession: HttpSession): String =
		if (httpSession.getAttribute(Const.VERSION) != null)
			httpSession.getAttribute(Const.VERSION).toString()
		else
			Versions.EU_VERSION.toString()
	
	/**
	 * Running the roulette randomizer for outside Bets and returning the winning value
	 * @param amount Double: bet amount
	 * @param outsideBets OutsideBets enum : choice of outside Bet
	 * @param httpSession: HttpSession to save the version value
	 * @return winning amount as Double
	 * All outside bets has a very well defined sets of slot. The random number is checked
	 * inside the list based on the outside bet type and thus the winning is calculated.
	 */
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
	
	/**
	 * Randimizer mechanism
	 * @param version String: current roulette version
	 * @return randomized number in string format
	 */
	private fun randomize(version: String): String {
		//EU version roulette slots range from 0 to 36. So, a basic number randomizer is used.
		return if (version == Versions.EU_VERSION.toString())
			Random().nextInt(37).toString()
		
		/*
		US version roulette has an additional 00 slot. That's why a custom numbers list of string is used
		to get the random slot. US version has total 38 slots. Due to the US version the randomizer is returning
		a string value instead of Int. If 00 is converted to Int, it will become Int(0) instead of Int(00).
		*/
		else {
			numbers[Random().nextInt(38)]
		}
	}
	
	/**
	 * Running the roulette randomizer for inside Bets and returning the winning value
	 * @param amount Double: bet amount
	 * @param insideBets InsideBets enum : choice of inside Bet
	 * @param httpSession: HttpSession to save the version value
	 * @return winning amount as Double
	 * Inside bets do not have a well defined sets of slot. The slots depend on the user
	 * input. The user/Front-end needs to give the set of slots based on the type of inside
	 * bet. The randmizer generates a random number and that is checked with the user inserted
	 * array of slots. Based on the result of that, the winning is calculated.
	 */
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
				//US and EU version returns different payout for BASKET as US version has an extra 00
				InsideBets.BASKET -> if (version == Versions.EU_VERSION.toString()) amount * 9 else amount * 7
			}
		} else 0.00
		
	}
	
}