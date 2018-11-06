package com.hasanrahman.rouletteapi.utility

enum class Versions{
	US_VERSION,
	EU_VERSION
}

enum class OutsideBets{
	FIRST_DOZEN,
	SECOND_DOZEN,
	THIRD_DOZEN,
	LOW,
	HIGH,
	EVEN,
	ODD,
	BLACK,
	RED,
	FIRST_COLUMN,
	SECOND_COLUMN,
	THIRD_COLUMN
}

enum class InsideBets {
	SINGLE,
	SPLIT,
	STREET,
	SQUARE,
	DOUBLE_STREET,
	TRIO,
	BASKET
}

val numbers = listOf(
	"0", "00", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16",
	"17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36")