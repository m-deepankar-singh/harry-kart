package com.atg.harrykart.controller;

import com.atg.harrykart.service.HarryKartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * HaryKartController Used for reading XML as an Input for REST API and returns top horse rankings as JSON Response
 * which are participated in the HarryKart.
 * @author Parasuram
 */
@RestController
@RequestMapping("/api")
public class HarryKartController {

  @Autowired
  private HarryKartService harryKartService;
  /**
   * Returns list of the top winning horses with its names and rankings.
   * @return   ResponseEntity<List<HarryKartResponse>> Returns Top Winning Horses.
   */
  @PostMapping(path="/horses",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_XML_VALUE)
  public ResponseEntity<?> getWinningHorses(@RequestBody String harryKartStr) {
    try {
      return new ResponseEntity<>(harryKartService.getWinningHorses(harryKartStr), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
    }
  }
}
