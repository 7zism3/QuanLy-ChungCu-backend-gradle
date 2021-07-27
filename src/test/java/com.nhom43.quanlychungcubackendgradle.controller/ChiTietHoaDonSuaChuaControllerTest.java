package com.nhom43.quanlychungcubackendgradle.controller;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.nhom43.quanlychungcubackendgradle.controller.ChiTietHoaDonSuaChuaController;
import com.nhom43.quanlychungcubackendgradle.controller.impl.CustomUtils;
import com.nhom43.quanlychungcubackendgradle.dto.ChiTietHoaDonSuaChuaDto;
import com.nhom43.quanlychungcubackendgradle.entity.ChiTietHoaDonSuaChua;
import com.nhom43.quanlychungcubackendgradle.mapper.ChiTietHoaDonSuaChuaMapper;
import com.nhom43.quanlychungcubackendgradle.mapper.EntityMapper;
import com.nhom43.quanlychungcubackendgradle.service.ChiTietHoaDonSuaChuaService;
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
public class ChiTietHoaDonSuaChuaControllerTest {
    private static final String ENDPOINT_URL = "/chi-tiet-hoa-don-sua-chuas";
    @InjectMocks
    private ChiTietHoaDonSuaChuaController chitiethoadonsuachuaController;
    @Mock
    private ChiTietHoaDonSuaChuaService chitiethoadonsuachuaService;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(chitiethoadonsuachuaController)
                //.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                //.addFilter(CustomFilter::doFilter)
                .build();
    }

    @Test
    public void findAllByPage() throws Exception {
        Page<ChiTietHoaDonSuaChuaDto> page = new PageImpl<>(Collections.singletonList(ChiTietHoaDonSuaChuaBuilder.getDto()));

        Mockito.when(chitiethoadonsuachuaService.findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(page);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.content", Matchers.hasSize(1)));

        Mockito.verify(chitiethoadonsuachuaService, Mockito.times(1)).findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any());
        Mockito.verifyNoMoreInteractions(chitiethoadonsuachuaService);

    }

    @Test
    public void getById() throws Exception {
        Mockito.when(chitiethoadonsuachuaService.findById(ArgumentMatchers.anyLong())).thenReturn(ChiTietHoaDonSuaChuaBuilder.getDto());

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
        Mockito.verify(chitiethoadonsuachuaService, Mockito.times(1)).findById("1");
        Mockito.verifyNoMoreInteractions(chitiethoadonsuachuaService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(chitiethoadonsuachuaService.save(ArgumentMatchers.any(ChiTietHoaDonSuaChuaDto.class))).thenReturn(ChiTietHoaDonSuaChuaBuilder.getDto());

        mockMvc.perform(
                MockMvcRequestBuilders.post(ENDPOINT_URL)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(CustomUtils.asJsonString(ChiTietHoaDonSuaChuaBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(chitiethoadonsuachuaService, Mockito.times(1)).save(ArgumentMatchers.any(ChiTietHoaDonSuaChuaDto.class));
        Mockito.verifyNoMoreInteractions(chitiethoadonsuachuaService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(chitiethoadonsuachuaService.update(ArgumentMatchers.any(), ArgumentMatchers.anyLong())).thenReturn(ChiTietHoaDonSuaChuaBuilder.getDto());

        mockMvc.perform(
                MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(CustomUtils.asJsonString(ChiTietHoaDonSuaChuaBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(chitiethoadonsuachuaService, Mockito.times(1)).update(ArgumentMatchers.any(ChiTietHoaDonSuaChuaDto.class), ArgumentMatchers.anyLong());
        Mockito.verifyNoMoreInteractions(chitiethoadonsuachuaService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(chitiethoadonsuachuaService).deleteById(ArgumentMatchers.anyLong());
        mockMvc.perform(
                MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(CustomUtils.asJsonString(ChiTietHoaDonSuaChuaBuilder.getIds()))).andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(chitiethoadonsuachuaService, Mockito.times(1)).deleteById(Mockito.anyLong());
        Mockito.verifyNoMoreInteractions(chitiethoadonsuachuaService);
    }
}