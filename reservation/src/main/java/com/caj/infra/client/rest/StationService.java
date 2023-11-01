package com.caj.infra.client.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.caj.infra.client.dto.StationDTO;

@Service
public class StationService {
	
	/**
	 * Logger
	 */
	static Logger log = LoggerFactory.getLogger("jsonLogger");

	/**
	 * masterfileUrl
	 */
	@Value("${caj.masterfile.url}")
	String masterfileUrl;
	
	/**
	 * restTemplate
	 */
	@Autowired
	RestTemplate restTemplate;

	public int getStationDistance(String departureId, String destinationId) {
		StringBuilder stringBuilder = new StringBuilder();
		String url = stringBuilder.append(masterfileUrl).append("/api/v1/stations/distance?departureId=")
				.append(departureId).append("&destinationId=").append(destinationId).toString();
		log.info("call masterfile api: {}", url);
		ResponseEntity<StationDTO> response = restTemplate.getForEntity(url, StationDTO.class);
		return response.getBody().getData();
	}
}
