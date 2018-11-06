package com.hasanrahman.rouletteapi.services

import com.hasanrahman.rouletteapi.utility.Const
import com.hasanrahman.rouletteapi.utility.OutsideBets
import com.hasanrahman.rouletteapi.utility.Versions
import com.hasanrahman.rouletteapi.utility.numbers
import org.springframework.stereotype.Service
import java.util.*
import javax.servlet.http.HttpSession


@Service
class BetService {
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
	
	fun getVersion(httpSession: HttpSession): String =if(httpSession.getAttribute(Const.VERSION)!=null)httpSession.getAttribute(Const.VERSION).toString() else ""
	
	fun outsideBets(amount:Int, outsideBets: OutsideBets, httpSession: HttpSession): Int{
		val numberString=randomize(getVersion(httpSession))
		return 0
	}
	
	private fun randomize(version: String): String {
		return if(version== Versions.EU_VERSION.toString())
			Random().nextInt(37).toString()
		else{
			numbers[Random().nextInt(38)]
		}
	}
	
}