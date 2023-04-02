package com.atg.harrykart.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * HarryKartVO is an input data for HarryKary HorseRacing Game to find the top positions in the game
 * @author Parasuram
 */
@Getter
@Setter
public class HarryKartVO {
    private static double TRACK_LENGTH = 1000.0;
    private int lane;
    private String horseName;
    private int baseSpeed;
    List<Integer> lanePowers;

    /**
     * Returns getTotalTime of each Horse in the HarryKart Racing Game
     * @return Double Returns getTotalTime of each Horse
     */
    public Double getTotalTime() {
        int currentSpeed = baseSpeed;
        if(currentSpeed <= 0) {
            return Double.MAX_VALUE;
        }
        Double totalTime = TRACK_LENGTH/currentSpeed;

        for(int i=0;i<lanePowers.size();i++) {
            currentSpeed += lanePowers.get(i);
            if(currentSpeed <= 0) {
                return Double.MAX_VALUE;
            }
            totalTime += TRACK_LENGTH/currentSpeed;
        }
        return totalTime;
    }
}
