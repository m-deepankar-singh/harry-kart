package com.atg.harrykart.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WinningHorsesResponse {
    private List<HarryKartResponse> ranking;
}
