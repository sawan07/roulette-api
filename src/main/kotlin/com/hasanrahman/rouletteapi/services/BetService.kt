package com.hasanrahman.rouletteapi.services

import com.hasanrahman.rouletteapi.utility.Const
import com.hasanrahman.rouletteapi.utility.versions
import org.springframework.stereotype.Service
import javax.servlet.http.HttpSession


@Service
class BetService {
	/**
	 * Setting the version of the roulette
	 * @param version: versions (either US or EU)
	 * @param httpSession: HttpSession to save the version value
	 * @return saved version value
	 */
	fun setVersion(version: versions, httpSession: HttpSession): String {
		httpSession.setAttribute(Const.VERSION, version)
		return getVersion(httpSession)
	}
	
	fun getVersion(httpSession: HttpSession): String =if(httpSession.getAttribute(Const.VERSION)!=null)httpSession.getAttribute(Const.VERSION).toString() else ""
	
}