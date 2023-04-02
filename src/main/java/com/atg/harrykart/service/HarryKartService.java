package com.atg.harrykart.service;

import com.atg.harrykart.model.HarryKartResponse;
import com.atg.harrykart.model.HarryKartVO;
import com.atg.harrykart.model.WinningHorsesResponse;
import com.atg.harrykart.request.Lane;
import com.atg.harrykart.exception.InvalidRequestException;
import com.atg.harrykart.request.HarryKart;
import com.atg.harrykart.request.Loop;
import com.atg.harrykart.request.Participant;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * HarryKartService contains the HarryKart Horses data and compute the top 3 Horses positions in the game
 * @author Parasuram
 */
@Service
public class HarryKartService {

    /**
     * Returns list of the top winning horses with its names and rankings.
     * @return  List<HarryKartResponse> Returns Top Winning Horses.
     */
    public WinningHorsesResponse getWinningHorses(final String harryKartStr) {
        if(Objects.isNull(harryKartStr)|| harryKartStr.isEmpty()){
            throw new InvalidRequestException("Invalid Request Data ");
        }
        HarryKart harryKart = getHarryKart(harryKartStr);
        if(Objects.isNull(harryKart)){
            throw new InvalidRequestException("Unable to Parse Request Data ");
        }
        List<HarryKartVO> harryKartVOS = getHarryKartVOS(harryKart);
        List<HarryKartVO> winningHorses = harryKartVOS.stream()
                .sorted(Comparator.comparingDouble(HarryKartVO::getTotalTime))
                .collect(Collectors.toList()).subList(0, 3);
        List<HarryKartResponse> responses = new LinkedList<>();
        int idx =0;
        for(HarryKartVO harryKartVO : winningHorses) {
            responses.add(new HarryKartResponse(++idx,harryKartVO.getHorseName()));
        }
        WinningHorsesResponse winningHorsesRankings = new WinningHorsesResponse();
        winningHorsesRankings.setRanking(responses);
        return winningHorsesRankings ;
    }

    private static List<HarryKartVO> getHarryKartVOS(HarryKart harryKart) {
        List<HarryKartVO> harryKartVOS = new ArrayList<>();
        for (Participant participant : harryKart.getParticipant()) {
            HarryKartVO harryKartVO = new HarryKartVO();
            harryKartVO.setBaseSpeed(participant.getBaseSpeed());
            harryKartVO.setLane(participant.getLane());
            harryKartVO.setHorseName(participant.getName());
            for (Loop loop : harryKart.getLoop()) {
                for (Lane lane : loop.getLane()) {
                    if(lane.getNumber()== harryKartVO.getLane()){
                       if(harryKartVO.getLanePowers()==null){
                           List<Integer> list = new ArrayList<>();
                           list.add(lane.getText());
                           harryKartVO.setLanePowers(list);
                       }else{
                           harryKartVO.getLanePowers().add(lane.getText());
                       }
                    }
                }
            }
            harryKartVOS.add(harryKartVO);
        }
        return harryKartVOS;
    }
    private static HarryKart getHarryKart(String harryKartStr){
        try {
            JAXBContext context = JAXBContext.newInstance(HarryKart.class);
            Unmarshaller un = context.createUnmarshaller();
            return (HarryKart) un.unmarshal(new StringReader(harryKartStr));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }




}

