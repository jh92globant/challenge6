package com.globant.challenge6.login.domain.exception

open class DomainException(override val message: String = "") : Throwable(message)
object BadRequestException: DomainException()
object NotFoundException: DomainException()
object UnauthorizedException: DomainException()
object TimeoutException: DomainException()
object InternalErrorException: DomainException()
object UnknownError: DomainException()
object KeyDataStoreNotFoundException: DomainException()