package com.hasanrahman.rouletteapi.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Roulette version not found")
class VersionNotFound(message: String = "") : RuntimeException(message)