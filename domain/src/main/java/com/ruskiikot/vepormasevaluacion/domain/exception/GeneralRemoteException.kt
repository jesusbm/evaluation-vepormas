package com.ruskiikot.vepormasevaluacion.domain.exception

data class GeneralRemoteException(override val message: String) : Exception(message)
