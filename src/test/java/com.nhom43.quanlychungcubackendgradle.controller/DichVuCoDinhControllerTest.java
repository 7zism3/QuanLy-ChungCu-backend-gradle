package com.nhom43.quanlychungcubackendgradle.controller;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.nhom43.quanlychungcubackendgradle.controller.DichVuCoDinhController;
import com.nhom43.quanlychungcubackendgradle.controller.impl.CustomUtils;
import com.nhom43.quanlychungcubackendgradle.dto.DichVuCoDinhDto;
import com.nhom43.quanlychungcubackendgradle.entity.DichVuCoDinh;
import com.nhom43.quanlychungcubackendgradle.mapper.DichVuCoDinhMapper;
import com.nhom43.quanlychungcubackendgradle.mapper.EntityMapper;
import com.nhom43.quanlychungcubackendgradle.service.DichVuCoDinhService;
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
public class DichVuCoDinhControllerTest {
    private static final String ENDPOINT_URL = "/dich-vu-co-dinhs";
    @InjectMocks
    private DichVuCoDinhController dichvucodinhController;
    @Mock
    private DichVuCoDinhService dichvucodinhService;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(dichvucodinhController)
                //.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                //.addFilter(CustomFilter::doFilter)
                .build();
    }

    @Test
    public void findAllByPage() throws Exception {
        Page<DichVuCoDinhDto> page = new PageImpl<>(Collections.singletonList(DichVuCoDinhBuilder.getDto()));

        Mockito.when(dichvucodinhService.findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(page);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.content", Matchers.hasSize(1)));

        Mockito.verify(dichvucodinhService, Mockito.times(1)).findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any());
        Mockito.verifyNoMoreInteractions(dichvucodinhService);

    }

    @Test
    public void getById() throws Exception {
        Mockito.when(dichvucodinhService.findById(ArgumentMatchers.anyLong())).thenReturn(DichVuCoDinhBuilder.getDto());

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
        Mockito.verify(dichvucodinhService, Mockito.times(1)).findById("1");
        Mockito.verifyNoMoreInteractions(dichvucodinhService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(dichvucodinhService.save(ArgumentMatchers.any(DichVuCoDinhDto.class))).thenReturn(DichVuCoDinhBuilder.getDto());

        mockMvc.perform(
                MockMvcRequestBuilders.post(ENDPOINT_URL)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(CustomUtils.asJsonString(DichVuCoDinhBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(dichvucodinhService, Mockito.times(1)).save(ArgumentMatchers.any(DichVuCoDinhDto.class));
        Mockito.verifyNoMoreInteractions(dichvucodinhService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(dichvucodinhService.update(ArgumentMatchers.any(), ArgumentMatchers.anyLong())).thenReturn(DichVuCoDinhBuilder.getDto());

        mockMvc.perform(
                MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(CustomUtils.asJsonString(DichVuCoDinhBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(dichvucodinhService, Mockito.times(1)).update(ArgumentMatchers.any(DichVuCoDinhDto.class), ArgumentMatchers.anyLong());
        Mockito.verifyNoMoreInteractions(dichvucodinhService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(dichvucodinhService).deleteById(ArgumentMatchers.anyLong());
        mockMvc.perform(
                MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(CustomUtils.asJsonString(DichVuCoDinhBuilder.getIds()))).andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(dichvucodinhService, Mockito.times(1)).deleteById(Mockito.anyLong());
        Mockito.verifyNoMoreInteractions(dichvucodinhService);
    }
}