package com.nhom43.quanlychungcubackendgradle.controller;

import com.nhom43.quanlychungcubackendgradle.controller._CanHoController;
import com.nhom43.quanlychungcubackendgradle.controller.impl.CustomUtils;
import com.nhom43.quanlychungcubackendgradle.dto._CanHoDto;
import com.nhom43.quanlychungcubackendgradle.mapper.EntityMapper;
import com.nhom43.quanlychungcubackendgradle.mapper._CanHoMapper;
import com.nhom43.quanlychungcubackendgradle.service._CanHoService;
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
public class _CanHoControllerTest {
    private static final String ENDPOINT_URL = "/_-can-hos";
    @InjectMocks
    private _CanHoController _canhoController;
    @Mock
    private _CanHoService _canhoService;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(_canhoController)
                //.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                //.addFilter(CustomFilter::doFilter)
                .build();
    }

    @Test
    public void findAllByPage() throws Exception {
        Page<_CanHoDto> page = new PageImpl<>(Collections.singletonList(_CanHoBuilder.getDto()));

        Mockito.when(_canhoService.findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(page);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.content", Matchers.hasSize(1)));

        Mockito.verify(_canhoService, Mockito.times(1)).findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any());
        Mockito.verifyNoMoreInteractions(_canhoService);

    }

    @Test
    public void getById() throws Exception {
        Mockito.when(_canhoService.findById(ArgumentMatchers.anyLong())).thenReturn(_CanHoBuilder.getDto());

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
        Mockito.verify(_canhoService, Mockito.times(1)).findById("1");
        Mockito.verifyNoMoreInteractions(_canhoService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(_canhoService.save(ArgumentMatchers.any(_CanHoDto.class))).thenReturn(_CanHoBuilder.getDto());

        mockMvc.perform(
                MockMvcRequestBuilders.post(ENDPOINT_URL)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(CustomUtils.asJsonString(_CanHoBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(_canhoService, Mockito.times(1)).save(ArgumentMatchers.any(_CanHoDto.class));
        Mockito.verifyNoMoreInteractions(_canhoService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(_canhoService.update(ArgumentMatchers.any(), ArgumentMatchers.anyLong())).thenReturn(_CanHoBuilder.getDto());

        mockMvc.perform(
                MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(CustomUtils.asJsonString(_CanHoBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(_canhoService, Mockito.times(1)).update(ArgumentMatchers.any(_CanHoDto.class), ArgumentMatchers.anyLong());
        Mockito.verifyNoMoreInteractions(_canhoService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(_canhoService).deleteById(ArgumentMatchers.anyLong());
        mockMvc.perform(
                MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(CustomUtils.asJsonString(_CanHoBuilder.getIds()))).andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(_canhoService, Mockito.times(1)).deleteById(Mockito.anyLong());
        Mockito.verifyNoMoreInteractions(_canhoService);
    }
}