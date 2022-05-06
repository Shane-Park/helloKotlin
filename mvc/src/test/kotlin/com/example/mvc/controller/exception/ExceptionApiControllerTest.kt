package com.example.mvc.controller.exception

import com.example.mvc.model.http.UserRequest
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.util.LinkedMultiValueMap

@WebMvcTest
@AutoConfigureMockMvc
class ExceptionApiControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun helloTest() {
        mockMvc.perform(
            get("/api/exception/hello")
        ).andExpect(
            status().isOk
        ).andExpect(
            content().string("hello")
        ).andDo(print())
    }

    @Test
    fun getTest() {
        val queryParams = LinkedMultiValueMap<String, String>()
        queryParams.add("name", "shane")
        queryParams.add("age", "100")

        mockMvc.perform(
            get("/api/exception").queryParams(queryParams)
        ).andExpect(
            status().isOk
        ).andExpect(
            content().string("shane 100")
        ).andDo(print())
    }

    @Test
    fun getFailTest() {
        val queryParams = LinkedMultiValueMap<String, String>()
        queryParams.add("name", "shane")
        queryParams.add("age", "5")

        mockMvc.perform(
            get("/api/exception").queryParams(queryParams)
        ).andExpect(
            status().isBadRequest
        ).andExpect(
            content().contentType("application/json")
        ).andExpect(
            jsonPath("\$.result_code").value("FAIL")
        ).andExpect(
            jsonPath("\$.errors[0].field").value("age")
        ).andExpect(
            jsonPath("\$.errors[0].value").value("5")
        ).andDo(print())
    }

    @Test
    fun postTest() {

        val userRequest = UserRequest().apply {
            this.name = "shane"
            this.age = 10
            this.phoneNumber = "010-1234-1234"
            this.address = "CA"
            this.email = "shane@tistory.com"
            this.createdDate = "2022-05-07 10:00:00"
        }

        val json = jacksonObjectMapper().writeValueAsString(userRequest)

        mockMvc.perform(
            post("/api/exception")
                .content(json)
                .contentType("application/json")
                .accept("application/json")
        ).andExpect(
            status().isOk
        ).andExpect(
            jsonPath("\$.name").value("shane")
        ).andExpect(
            jsonPath("\$.age").value("10")
        ).andExpect(
            jsonPath("\$.email").value("shane@tistory.com")
        ).andDo(print())
    }

    @Test
    fun postFailTest() {

        val userRequest = UserRequest().apply {
            this.name = "shane"
            this.age = -1
            this.phoneNumber = "010-1234-1234"
            this.address = "CA"
            this.email = "shane@tistory.com"
            this.createdDate = "2022-05-07 10:00:00"
        }

        val json = jacksonObjectMapper().writeValueAsString(userRequest)

        mockMvc.perform(
            post("/api/exception")
                .content(json)
                .contentType("application/json")
                .accept("application/json")
        ).andExpect(
            status().isBadRequest
        ).andExpect(
            jsonPath("\$.errors[0].field").value("age")
        ).andExpect(
            jsonPath("\$.errors[0].value").value("-1")
        ).andDo(print())
    }

}
