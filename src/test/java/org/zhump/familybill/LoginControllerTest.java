package org.zhump.familybill;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

/**
 * @author zhmp
 * @description 说明一下这个类是干嘛的
 * @date $ $
 */
public class LoginControllerTest extends BaseControllerTest{

    /**
     *登录
     * @throws Exception
     */
    @Test
    public void login()throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.post("/login/")
                        .param("accountName","sysadmin")
                        .param("password","admin000000")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
        )
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();
    }
}
