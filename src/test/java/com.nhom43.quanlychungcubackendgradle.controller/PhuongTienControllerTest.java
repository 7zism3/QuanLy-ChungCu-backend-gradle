package com.nhom43.quanlychungcubackendgradle.controller;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.nhom43.quanlychungcubackendgradle.controller.PhuongTienController;
import com.nhom43.quanlychungcubackendgradle.controller.impl.CustomUtils;
import com.nhom43.quanlychungcubackendgradle.dto.PhuongTienDto;
import com.nhom43.quanlychungcubackendgradle.entity.PhuongTien;
import com.nhom43.quanlychungcubackendgradle.mapper.EntityMapper;
import com.nhom43.quanlychungcubackendgradle.mapper.PhuongTienMapper;
import com.nhom43.quanlychungcubackendgradle.service.PhuongTienService;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;

@Transactional
public class PhuongTienControllerTest {
    private static final String ENDPOINT_URL = "/phuong-tiens";
    @InjectMocks
    private PhuongTienController phuongtienController;
    @Mock
    private PhuongTienService phuongtienService;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(phuongtienController)
                //.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                //.addFilter(CustomFilter::doFilter)
                .build();
    }

    @Test
    public void findAllByPage() throws Exception {
        Page<PhuongTienDto> page = new PageImpl<>(Collections.singletonList(PhuongTienBuilder.getDto()));

        Mockito.when(phuongtienService.findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(page);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.content", Matchers.hasSize(1)));

        Mockito.verify(phuongtienService, Mockito.times(1)).findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any());
        Mockito.verifyNoMoreInteractions(phuongtienService);

    }

    @Test
    public void getById() throws Exception {
        Mockito.when(phuongtienService.findById(ArgumentMatchers.anyLong())).thenReturn(PhuongTienBuilder.getDto());

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
        Mockito.verify(phuongtienService, Mockito.times(1)).findById("1");
        Mockito.verifyNoMoreInteractions(phuongtienService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(phuongtienService.save(ArgumentMatchers.any(PhuongTienDto.class))).thenReturn(PhuongTienBuilder.getDto());

        mockMvc.perform(
                MockMvcRequestBuilders.post(ENDPOINT_URL)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(CustomUtils.asJsonString(PhuongTienBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(phuongtienService, Mockito.times(1)).save(ArgumentMatchers.any(PhuongTienDto.class));
        Mockito.verifyNoMoreInteractions(phuongtienService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(phuongtienService.update(ArgumentMatchers.any(), ArgumentMatchers.anyLong())).thenReturn(PhuongTienBuilder.getDto());

        mockMvc.perform(
                MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(CustomUtils.asJsonString(PhuongTienBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(phuongtienService, Mockito.times(1)).update(ArgumentMatchers.any(PhuongTienDto.class), ArgumentMatchers.anyLong());
        Mockito.verifyNoMoreInteractions(phuongtienService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(phuongtienService).deleteById(ArgumentMatchers.anyLong());
        mockMvc.perform(
                MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(CustomUtils.asJsonString(PhuongTienBuilder.getIds()))).andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(phuongtienService, Mockito.times(1)).deleteById(Mockito.anyLong());
        Mockito.verifyNoMoreInteractions(phuongtienService);
    }
}