package com.example.cursach

sealed class OpResult<out T>

class Success<T>(val data: T): OpResult<T>()
class Failure<T>(val reason: Exception): OpResult<T>()