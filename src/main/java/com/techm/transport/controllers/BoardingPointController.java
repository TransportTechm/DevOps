package com.techm.transport.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import com.techm.transport.entity.BoardingPoint;
import com.techm.transport.entity.SampleData;
import com.techm.transport.service.BoardingPointService;

@Controller
@RequestMapping("transport/1.0")
public class BoardingPointController {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BoardingPointService service;


	@GetMapping("boardpoints/{id}")
	public ResponseEntity<BoardingPoint> getBoardingPoint(@PathVariable("id") Integer id){
		LOGGER.info("Getting BoardingPoint details of id-" + id);
		BoardingPoint org = service.getBoardingPointyId(id);
		return new ResponseEntity<BoardingPoint>(org, HttpStatus.OK);
	}

	@GetMapping("boardpoints")
	public ResponseEntity<List<BoardingPoint>> getAllBoardingPoint(){
		LOGGER.info("Getting details of all BoardingPoints");
		List<BoardingPoint> orgs = service.getAllBoardingPoints();
		return new ResponseEntity<List<BoardingPoint>>(orgs, HttpStatus.OK);
	}

	@PostMapping("boardpoints/{name}")
	public ResponseEntity<Void> addBoardingPoint(@PathVariable("name") String name, UriComponentsBuilder builder){
		LOGGER.info("Adding BoardingPoint. BoardingPoint name -"+ name);
		boolean flag = service.addBoardingPoint(name);
		if (!flag) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		HttpHeaders headers = new HttpHeaders();
//		headers.setLocation(builder.path("/org/{id}").buildAndExpand(bp.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
}
