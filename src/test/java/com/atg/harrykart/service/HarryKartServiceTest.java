
package com.atg.harrykart.service;


import com.atg.harrykart.model.HarryKartResponse;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static com.atg.harrykart.service.Constants.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = HarryKartService.class)
@WebAppConfiguration
public class HarryKartServiceTest {

    @Spy
    private HarryKartService harryKartService;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @SneakyThrows
    public void testReadingFirstInputXMLAndVerifyTopThreeHorseRankings() {
        String xmlAsString = getResourceFile(INPUT_0_XML);
        List<HarryKartResponse> harryKartResponseList = new ArrayList<>();
        harryKartResponseList.add(new HarryKartResponse(1, TIMETOBELUCKY));
        harryKartResponseList.add(new HarryKartResponse(2, HERCULES_BOKO));
        harryKartResponseList.add(new HarryKartResponse(3, CARGO_DOOR));
        when(harryKartService.getWinningHorses(xmlAsString)).thenReturn(harryKartResponseList);
        List<HarryKartResponse> response = harryKartService.getWinningHorses(xmlAsString);
        assertEquals(3, response.size());
    }

    @Test
    @SneakyThrows
    public void testReadingSecondInputXMLAndVerifyTopThreeHorseRankings() {
        String xmlAsString = getResourceFile(INPUT_1_XML);
        List<HarryKartResponse> harryKartResponseList = new ArrayList<>();
        harryKartResponseList.add(new HarryKartResponse(1, WAIKIKI_SILVIO));
        harryKartResponseList.add(new HarryKartResponse(2, TIMETOBELUCKY));
        harryKartResponseList.add(new HarryKartResponse(3, HERCULES_BOKO));
        when(harryKartService.getWinningHorses(xmlAsString)).thenReturn(harryKartResponseList);
        List<HarryKartResponse> response = harryKartService.getWinningHorses(xmlAsString);
        assertEquals(3, response.size());
    }


    @SneakyThrows
    @Test
    public void testReadingThirdInputXMLAndVerifyTopThreeHorseRankings() {
        String xmlAsString = getResourceFile(INPUT_2_XML);
        List<HarryKartResponse> harryKartResponseList = new ArrayList<>();
        harryKartResponseList.add(new HarryKartResponse(1, HERCULES_BOKO));
        harryKartResponseList.add(new HarryKartResponse(2, TIMETOBELUCKY));
        harryKartResponseList.add(new HarryKartResponse(3, WAIKIKI_SILVIO));
        when(harryKartService.getWinningHorses(xmlAsString)).thenReturn(harryKartResponseList);
        List<HarryKartResponse> response = harryKartService.getWinningHorses(xmlAsString);
        assertEquals(3, response.size());
    }


    public String getResourceFile(String fileName) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        FileInputStream fileInputStream = new FileInputStream(file);
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }


}

