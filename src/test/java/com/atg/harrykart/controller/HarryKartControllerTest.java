package com.atg.harrykart.controller;

import com.atg.harrykart.HarryKartApplication;
import com.atg.harrykart.exception.InvalidRequestException;
import com.atg.harrykart.model.HarryKartResponse;
import com.atg.harrykart.model.WinningHorsesResponse;
import com.atg.harrykart.service.HarryKartService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;
import java.util.List;

import static com.atg.harrykart.service.Constants.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = HarryKartApplication.class)
@WebAppConfiguration
@EnableWebMvc
public class HarryKartControllerTest {

    private final String BASE_URL = "/api/horses";

    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @MockBean
    private HarryKartService harryKartService;

    @Test
    public void testReadingFirstInputXMLAndVerifyTopThreeHorseRankings() throws Exception {
        List<HarryKartResponse> threeTopHorsesWithRanks = new ArrayList<>();
        threeTopHorsesWithRanks.add(new HarryKartResponse(1, TIMETOBELUCKY));
        threeTopHorsesWithRanks.add(new HarryKartResponse(2, HERCULES_BOKO));
        threeTopHorsesWithRanks.add(new HarryKartResponse(3, CARGO_DOOR));
        WinningHorsesResponse winningHorsesRankings = new WinningHorsesResponse();
        winningHorsesRankings.setRanking(threeTopHorsesWithRanks);
        Mockito.when(harryKartService.getWinningHorses(Mockito.any())).thenReturn(winningHorsesRankings);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL).contentType(MediaType.APPLICATION_XML).content(getJsonString(threeTopHorsesWithRanks))).andReturn();
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
        Assert.assertEquals(getHarryKartResponsesForFirstInput(), mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testShouldThrowInValidExceptionWhenInValidRequestGiven() throws Exception {
        List<HarryKartResponse> threeTopHorsesWithRanks = new ArrayList<>();
        threeTopHorsesWithRanks.add(new HarryKartResponse(1, TIMETOBELUCKY));
        threeTopHorsesWithRanks.add(new HarryKartResponse(2, HERCULES_BOKO));
        threeTopHorsesWithRanks.add(new HarryKartResponse(3, CARGO_DOOR));
        doThrow(new InvalidRequestException("Invalid request"))
                .when(harryKartService).getWinningHorses(Mockito.any());
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL)
                .contentType(MediaType.APPLICATION_XML).content(getJsonString(threeTopHorsesWithRanks))).andReturn();
        Assert.assertEquals(417, mvcResult.getResponse().getStatus());
        Assert.assertEquals("Invalid request", mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testReadingSecondInputXMLAndVerifyTopThreeHorseRankings() throws Exception {
        List<HarryKartResponse> threeTopHorsesWithRanks = new ArrayList<>();
        threeTopHorsesWithRanks.add(new HarryKartResponse(1, WAIKIKI_SILVIO));
        threeTopHorsesWithRanks.add(new HarryKartResponse(2, TIMETOBELUCKY));
        threeTopHorsesWithRanks.add(new HarryKartResponse(3, HERCULES_BOKO));
        WinningHorsesResponse winningHorsesRankings = new WinningHorsesResponse();
        winningHorsesRankings.setRanking(threeTopHorsesWithRanks);
        Mockito.when(harryKartService.getWinningHorses(Mockito.any())).thenReturn(winningHorsesRankings);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL).contentType(MediaType.APPLICATION_XML).content(getJsonString(threeTopHorsesWithRanks))).andReturn();
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
        Assert.assertEquals(getHarryKartResponsesForSecondInput(), mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testReadingThirdInputXMLAndVerifyTopThreeHorseRankings() throws Exception {
        List<HarryKartResponse> threeTopHorsesWithRanks = new ArrayList<>();
        threeTopHorsesWithRanks.add(new HarryKartResponse(1, HERCULES_BOKO));
        threeTopHorsesWithRanks.add(new HarryKartResponse(2, TIMETOBELUCKY));
        threeTopHorsesWithRanks.add(new HarryKartResponse(3, WAIKIKI_SILVIO));
        WinningHorsesResponse winningHorsesRankings = new WinningHorsesResponse();
        winningHorsesRankings.setRanking(threeTopHorsesWithRanks);
        Mockito.when(harryKartService.getWinningHorses(Mockito.any())).thenReturn(winningHorsesRankings);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL).contentType(MediaType.APPLICATION_XML).content(getJsonString(threeTopHorsesWithRanks))).andReturn();
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
        Assert.assertEquals(getHarryKartResponsesForThirdInput(), mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testShouldReturnBadRequestWhenInValidInputPassed() throws Exception {
        Mockito.when(harryKartService.getWinningHorses(Mockito.any())).thenReturn(null);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL + "/" + 3).contentType(MediaType.APPLICATION_JSON).content(getJsonString(null))).andReturn();
        Assert.assertEquals(404, mvcResult.getResponse().getStatus());
    }

    private String getHarryKartResponsesForThirdInput() throws JsonProcessingException {
        WinningHorsesResponse winningHorsesRankings = new WinningHorsesResponse();
        List<HarryKartResponse> threeTopHorsesWithRanks = new ArrayList<>();
        threeTopHorsesWithRanks.add(new HarryKartResponse(1, HERCULES_BOKO));
        threeTopHorsesWithRanks.add(new HarryKartResponse(2, TIMETOBELUCKY));
        threeTopHorsesWithRanks.add(new HarryKartResponse(3, WAIKIKI_SILVIO));
        winningHorsesRankings.setRanking(threeTopHorsesWithRanks);
        return new ObjectMapper().writeValueAsString(winningHorsesRankings);
    }

    private String getHarryKartResponsesForSecondInput() throws JsonProcessingException {
        WinningHorsesResponse winningHorsesRankings = new WinningHorsesResponse();
        List<HarryKartResponse> threeTopHorsesWithRanks = new ArrayList<>();
        threeTopHorsesWithRanks.add(new HarryKartResponse(1, WAIKIKI_SILVIO));
        threeTopHorsesWithRanks.add(new HarryKartResponse(2, TIMETOBELUCKY));
        threeTopHorsesWithRanks.add(new HarryKartResponse(3, HERCULES_BOKO));
        winningHorsesRankings.setRanking(threeTopHorsesWithRanks);
        return new ObjectMapper().writeValueAsString(winningHorsesRankings);
    }

    private String getHarryKartResponsesForFirstInput() throws JsonProcessingException {
        WinningHorsesResponse winningHorsesRankings = new WinningHorsesResponse();
        List<HarryKartResponse> threeTopHorsesWithRanks = new ArrayList<>();
        threeTopHorsesWithRanks.add(new HarryKartResponse(1, TIMETOBELUCKY));
        threeTopHorsesWithRanks.add(new HarryKartResponse(2, HERCULES_BOKO));
        threeTopHorsesWithRanks.add(new HarryKartResponse(3, CARGO_DOOR));
        winningHorsesRankings.setRanking(threeTopHorsesWithRanks);
        return new ObjectMapper().writeValueAsString(winningHorsesRankings);

    }

    private String getJsonString(Object object) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(object);
    }
}
