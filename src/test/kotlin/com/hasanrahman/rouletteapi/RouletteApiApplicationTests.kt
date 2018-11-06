package com.hasanrahman.rouletteapi

import com.hasanrahman.rouletteapi.services.BetService
import com.hasanrahman.rouletteapi.utility.InsideBets
import com.hasanrahman.rouletteapi.utility.OutsideBets
import com.hasanrahman.rouletteapi.utility.Versions
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
@ActiveProfiles("test")
class RouletteApiApplicationTests {
	
	private var betService = BetService()
	
	@Test
	fun setVersionCheck() {
		
		val result = httpSession?.let { betService.setVersion(Versions.EU_VERSION, it) }
		Assert.assertEquals(Versions.EU_VERSION.toString(), result)
	}
	
	/**
	 * Outside
	 * Bet
	 * Tests
	 */
	
	@Test
	fun firstDozenTest() {
		httpSession?.let { betService.setVersion(Versions.EU_VERSION, it) }
		val result = httpSession?.let { betService.outsideBets(100.00, OutsideBets.FIRST_DOZEN, it) }
		Assert.assertTrue(listOf(300.00, 0.00).contains(result))
	}
	
	@Test
	fun secondDozenTest() {
		httpSession?.let { betService.setVersion(Versions.EU_VERSION, it) }
		val result = httpSession?.let { betService.outsideBets(100.00, OutsideBets.SECOND_DOZEN, it) }
		Assert.assertTrue(listOf(300.00, 0.00).contains(result))
	}
	
	@Test
	fun thirdDozenTest() {
		httpSession?.let { betService.setVersion(Versions.EU_VERSION, it) }
		val result = httpSession?.let { betService.outsideBets(100.00, OutsideBets.THIRD_DOZEN, it) }
		Assert.assertTrue(listOf(300.00, 0.00).contains(result))
	}
	
	@Test
	fun lowTest() {
		httpSession?.let { betService.setVersion(Versions.EU_VERSION, it) }
		val result = httpSession?.let { betService.outsideBets(100.00, OutsideBets.LOW, it) }
		Assert.assertTrue(listOf(200.00, 0.00).contains(result))
	}
	
	@Test
	fun highTest() {
		httpSession?.let { betService.setVersion(Versions.EU_VERSION, it) }
		val result = httpSession?.let { betService.outsideBets(100.00, OutsideBets.HIGH, it) }
		Assert.assertTrue(listOf(200.00, 0.00).contains(result))
	}
	
	@Test
	fun evenTest() {
		httpSession?.let { betService.setVersion(Versions.EU_VERSION, it) }
		val result = httpSession?.let { betService.outsideBets(100.00, OutsideBets.EVEN, it) }
		Assert.assertTrue(listOf(200.00, 0.00).contains(result))
	}
	
	@Test
	fun oddTest() {
		httpSession?.let { betService.setVersion(Versions.EU_VERSION, it) }
		val result = httpSession?.let { betService.outsideBets(100.00, OutsideBets.LOW, it) }
		Assert.assertTrue(listOf(200.00, 0.00).contains(result))
	}
	
	@Test
	fun blackTest() {
		httpSession?.let { betService.setVersion(Versions.EU_VERSION, it) }
		val result = httpSession?.let { betService.outsideBets(100.00, OutsideBets.BLACK, it) }
		Assert.assertTrue(listOf(200.00, 0.00).contains(result))
	}
	
	@Test
	fun redTest() {
		httpSession?.let { betService.setVersion(Versions.EU_VERSION, it) }
		val result = httpSession?.let { betService.outsideBets(100.00, OutsideBets.RED, it) }
		Assert.assertTrue(listOf(200.00, 0.00).contains(result))
	}
	
	@Test
	fun firstColumnTest() {
		httpSession?.let { betService.setVersion(Versions.EU_VERSION, it) }
		val result = httpSession?.let { betService.outsideBets(100.00, OutsideBets.FIRST_COLUMN, it) }
		Assert.assertTrue(listOf(300.00, 0.00).contains(result))
	}
	
	@Test
	fun secondColumnTest() {
		httpSession?.let { betService.setVersion(Versions.EU_VERSION, it) }
		val result = httpSession?.let { betService.outsideBets(100.00, OutsideBets.SECOND_COLUMN, it) }
		Assert.assertTrue(listOf(300.00, 0.00).contains(result))
	}
	
	@Test
	fun thirdColumnTest() {
		httpSession?.let { betService.setVersion(Versions.EU_VERSION, it) }
		val result = httpSession?.let { betService.outsideBets(100.00, OutsideBets.THIRD_COLUMN, it) }
		Assert.assertTrue(listOf(300.00, 0.00).contains(result))
	}
	
	/**
	 * Inside
	 * Bet
	 * Tests
	 */
	
	@Test
	fun singleTest() {
		httpSession?.let { betService.setVersion(Versions.EU_VERSION, it) }
		val result = httpSession?.let { betService.insideBets(100.00, InsideBets.SINGLE, arrayOf("9"), it) }
		Assert.assertTrue(listOf(3600.00, 0.00).contains(result))
	}
	
	@Test
	fun splitTest() {
		httpSession?.let { betService.setVersion(Versions.EU_VERSION, it) }
		val result = httpSession?.let { betService.insideBets(100.00, InsideBets.SPLIT, arrayOf("2", "5"), it) }
		Assert.assertTrue(listOf(1800.00, 0.00).contains(result))
	}
	
	@Test
	fun streetTest() {
		httpSession?.let { betService.setVersion(Versions.EU_VERSION, it) }
		val result = httpSession?.let { betService.insideBets(100.00, InsideBets.STREET, arrayOf("7", "8", "9"), it) }
		Assert.assertTrue(listOf(1200.00, 0.00).contains(result))
	}
	
	@Test
	fun squareTest() {
		httpSession?.let { betService.setVersion(Versions.EU_VERSION, it) }
		val result =
			httpSession?.let { betService.insideBets(100.00, InsideBets.SQUARE, arrayOf("8", "9", "11", "12"), it) }
		Assert.assertTrue(listOf(900.00, 0.00).contains(result))
	}
	
	@Test
	fun doubleStreetTest() {
		httpSession?.let { betService.setVersion(Versions.EU_VERSION, it) }
		val result = httpSession?.let {
			betService.insideBets(
				100.00,
				InsideBets.DOUBLE_STREET,
				arrayOf("7", "8", "9", "10", "11", "12"),
				it
			)
		}
		Assert.assertTrue(listOf(600.00, 0.00).contains(result))
	}
	
	@Test
	fun trioest() {
		httpSession?.let { betService.setVersion(Versions.EU_VERSION, it) }
		val result = httpSession?.let { betService.insideBets(100.00, InsideBets.TRIO, arrayOf("0", "1", "2"), it) }
		Assert.assertTrue(listOf(1200.00, 0.00).contains(result))
	}
	
	@Test
	fun basketEuTest() {
		httpSession?.let { betService.setVersion(Versions.EU_VERSION, it) }
		val result =
			httpSession?.let { betService.insideBets(100.00, InsideBets.BASKET, arrayOf("0", "1", "2", "3"), it) }
		Assert.assertTrue(listOf(900.00, 0.00).contains(result))
	}
	
	@Test
	fun basketUsTest() {
		httpSession?.let { betService.setVersion(Versions.US_VERSION, it) }
		val result =
			httpSession?.let { betService.insideBets(100.00, InsideBets.BASKET, arrayOf("00", "0", "1", "2", "3"), it) }
		Assert.assertTrue(listOf(700.00, 0.00).contains(result))
	}
	
	
	companion object {
		private val mockHttpServletRequest = MockHttpServletRequest()
		val httpSession = mockHttpServletRequest.session
	}
	
}
