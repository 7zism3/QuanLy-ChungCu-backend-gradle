package com.nhom43.quanlychungcubackendgradle.controller;

import com.nhom43.quanlychungcubackendgradle.controller.impl.CustomUtils;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Transactional
public class TaiKhoanControllerTest {
    private static final String ENDPOINT_URL = "/tai-khoans";
    @InjectMocks
    private TaiKhoanController taikhoanController;
    @Mock
    private TaiKhoanService taikhoanService;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(taikhoanController)
                //.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                //.addFilter(CustomFilter::doFilter)
                .build();
    }

    @Test
    public void findAllByPage() throws Exception {
        Page<TaiKhoanDto> page = new PageImpl<>(Collections.singletonList(TaiKhoanBuilder.getDto()));

        Mockito.when(taikhoanService.findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(page);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.content", Matchers.hasSize(1)));

        Mockito.verify(taikhoanService, Mockito.times(1)).findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any());
        Mockito.verifyNoMoreInteractions(taikhoanService);

    }

    @Test
    public void getById() throws Exception {
        Mockito.when(taikhoanService.findById(ArgumentMatchers.anyLong())).thenReturn(TaiKhoanBuilder.getDto());

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
        Mockito.verify(taikhoanService, Mockito.times(1)).findById("1");
        Mockito.verifyNoMoreInteractions(taikhoanService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(taikhoanService.save(ArgumentMatchers.any(TaiKhoanDto.class))).thenReturn(TaiKhoanBuilder.getDto());

        mockMvc.perform(
                MockMvcRequestBuilders.post(ENDPOINT_URL)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(CustomUtils.asJsonString(TaiKhoanBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(taikhoanService, Mockito.times(1)).save(ArgumentMatchers.any(TaiKhoanDto.class));
        Mockito.verifyNoMoreInteractions(taikhoanService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(taikhoanService.update(ArgumentMatchers.any(), ArgumentMatchers.anyLong())).thenReturn(TaiKhoanBuilder.getDto());

        mockMvc.perform(
                MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(CustomUtils.asJsonString(TaiKhoanBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(taikhoanService, Mockito.times(1)).update(ArgumentMatchers.any(TaiKhoanDto.class), ArgumentMatchers.anyLong());
        Mockito.verifyNoMoreInteractions(taikhoanService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(taikhoanService).deleteById(ArgumentMatchers.anyLong());
        mockMvc.perform(
                MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(CustomUtils.asJsonString(TaiKhoanBuilder.getIds()))).andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(taikhoanService, Mockito.times(1)).deleteById(Mockito.anyLong());
        Mockito.verifyNoMoreInteractions(taikhoanService);
    }
}