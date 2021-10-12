package com.jvvladimir.mock.service

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

interface RequestProcessor {

    fun process(request: HttpServletRequest, response: HttpServletResponse): Any?
}