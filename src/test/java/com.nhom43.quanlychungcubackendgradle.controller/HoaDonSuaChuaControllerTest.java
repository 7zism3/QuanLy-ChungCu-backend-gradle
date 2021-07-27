package com.nhom43.quanlychungcubackendgradle.controller;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.nhom43.quanlychungcubackendgradle.controller.HoaDonSuaChuaController;
import com.nhom43.quanlychungcubackendgradle.controller.impl.CustomUtils;
import com.nhom43.quanlychungcubackendgradle.dto.HoaDonSuaChuaDto;
import com.nhom43.quanlychungcubackendgradle.entity.HoaDonSuaChua;
import com.nhom43.quanlychungcubackendgradle.mapper.EntityMapper;
import com.nhom43.quanlychungcubackendgradle.mapper.HoaDonSuaChuaMapper;
import com.nhom43.quanlychungcubackendgradle.service.HoaDonSuaChuaService;
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
public class HoaDonSuaChuaControllerTest {
    private static final String ENDPOINT_URL = "/hoa-don-sua-chuas";
    @InjectMocks
    private HoaDonSuaChuaController hoadonsuachuaController;
    @Mock
    private HoaDonSuaChuaService hoadonsuachuaService;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(hoadonsuachuaController)
                //.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                //.addFilter(CustomFilter::doFilter)
                .build();
    }

    @Test
    public void findAllByPage() throws Exception {
        Page<HoaDonSuaChuaDto> page = new PageImpl<>(Collections.singletonList(HoaDonSuaChuaBuilder.getDto()));

        Mockito.when(hoadonsuachuaService.findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(page);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.content", Matchers.hasSize(1)));

        Mockito.verify(hoadonsuachuaService, Mockito.times(1)).findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any());
        Mockito.verifyNoMoreInteractions(hoadonsuachuaService);

    }

    @Test
    public void getById() throws Exception {
        Mockito.when(hoadonsuachuaService.findById(ArgumentMatchers.anyLong())).thenReturn(HoaDonSuaChuaBuilder.getDto());

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
        Mockito.verify(hoadonsuachuaService, Mockito.times(1)).findById("1");
        Mockito.verifyNoMoreInteractions(hoadonsuachuaService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(hoadonsuachuaService.save(ArgumentMatchers.any(HoaDonSuaChuaDto.class))).thenReturn(HoaDonSuaChuaBuilder.getDto());

        mockMvc.perform(
                MockMvcRequestBuilders.post(ENDPOINT_URL)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(CustomUtils.asJsonString(HoaDonSuaChuaBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(hoadonsuachuaService, Mockito.times(1)).save(ArgumentMatchers.any(HoaDonSuaChuaDto.class));
        Mockito.verifyNoMoreInteractions(hoadonsuachuaService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(hoadonsuachuaService.update(ArgumentMatchers.any(), ArgumentMatchers.anyLong())).thenReturn(HoaDonSuaChuaBuilder.getDto());

        mockMvc.perform(
                MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(CustomUtils.asJsonString(HoaDonSuaChuaBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(hoadonsuachuaService, Mockito.times(1)).update(ArgumentMatchers.any(HoaDonSuaChuaDto.class), ArgumentMatchers.anyLong());
        Mockito.verifyNoMoreInteractions(hoadonsuachuaService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(hoadonsuachuaService).deleteById(ArgumentMatchers.anyLong());
        mockMvc.perform(
                MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(CustomUtils.asJsonString(HoaDonSuaChuaBuilder.getIds()))).andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(hoadonsuachuaService, Mockito.times(1)).deleteById(Mockito.anyLong());
        Mockito.verifyNoMoreInteractions(hoadonsuachuaService);
    }
}