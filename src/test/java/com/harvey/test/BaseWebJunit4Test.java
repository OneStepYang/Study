package com.harvey.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @program: Study
 * @ClassName : BaseWebJunit4Test
 * @description: 基础测试类
 * @author: Harvey
 * @create: 2020-02-24 21:02
 */
@RunWith(SpringJUnit4ClassRunner.class)//使用Spring Test组件进行单元测试
@WebAppConfiguration
// 如果不想测试产生的数据保存下来。可以开启事务默认回滚
@Rollback
@Transactional(transactionManager="DataSourceTransactionManager")
public class BaseWebJunit4Test {

    protected MockMvc mockMvc;
    protected MockHttpSession mockHttpSession;

    @Autowired
    protected WebApplicationContext context;

    /*@Before
    public void initMockMvc() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        this.mockHttpSession = new MockHttpSession();
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("account", "kyle")
                .param("password", "123456")
                .session(mockHttpSession))
                .andExpect(status().isOk())
                .andExpect(content().string("登陆成功"))
                .andDo(print())
                .andReturn().getResponse().getContentAsString();
    }*/

    @Test
    public void test() throws Exception {

    }

}