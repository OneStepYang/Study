package com.harvey.test;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @program: Study
 * @ClassName : UserControllerTest
 * @description: UserController测试类
 * @author: Harvey
 * @create: 2020-02-24 21:00
 */
public class UserControllerTest extends BaseWebJunit4Test{
    @Test
    public void test() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        this.mockHttpSession = new MockHttpSession();
        mockMvc.perform(MockMvcRequestBuilders.post("/User/init")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .session(mockHttpSession))
                .andExpect(status().isOk())
                .andExpect(content().string("登陆成功"))
                .andDo(print())
                .andReturn().getResponse().getContentAsString();
    }
}