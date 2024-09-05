package com.example.demo;

import com.example.demo.guestbook.GuestbookController;
import com.example.demo.guestbook.GuestbookDto;
import com.example.demo.guestbook.GuestbookService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.when;

@WebMvcTest(GuestbookController.class)
public class GuestbookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GuestbookService service;

    //MockMvc:mvc 웹의 목테스트 api 제공
    //perform(): 목 수행 메서드, get(url), post()...
    //andDo(): 테스트 시 수행할 내용 작성. print(): 출력
    //andExpect(): 처리결과 상태 지정
    @Test
    void addFormTest() {
        //given

        //when
        try {
            mockMvc.perform(get("/guestbook/add"))
                    .andDo(print())
                    .andExpect(status().isOk()); //then
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void addTest() {
        //given
        String params = "?writer=aaa&pwd=111&content=테스트";
        //when
        try {
            //then
            mockMvc.perform(post("/guestbook/add" + params))
                    .andDo(print())
                    .andExpect(status().is3xxRedirection());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Test
    void listTest() {
        //given
        ArrayList<GuestbookDto> list = new ArrayList<>();
        list.add(new GuestbookDto(1, "aaa", new Date(), "111", "내용 1"));
        list.add(new GuestbookDto(2, "bbb", new Date(), "222", "내용 2"));
        list.add(new GuestbookDto(3, "ccc", new Date(), "333", "내용 3"));

        //when
        //when() 테스팅 시나리오에서 특정 이벤트가 발생.
        //thenReturn(): 이벤트 발생 시 지정한 값 리턴
        when(service.getAll()).thenReturn(list);
        try {
            //then
            mockMvc.perform(get("/guestbook/list"))
                    .andDo(print())
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
