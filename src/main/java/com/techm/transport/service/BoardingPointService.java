package com.techm.transport.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.techm.transport.entity.BoardingPoint;
import com.techm.transport.entity.SampleData;

@Service
public class BoardingPointService{

	private static List<BoardingPoint> list;

	static{
		list= SampleData.populateBoardingPoints();
	}

	public List<BoardingPoint> getAllBoardingPoints(){
		return list;
	}

	public synchronized boolean addBoardingPoint(String boardPointName) {
		if (getBoardingPointByName(boardPointName)!=null) {
			return false;
		} else {
			list.add(new BoardingPoint((int)SampleData.bpCounter.incrementAndGet(), boardPointName));
			return true;
		}
	}


	public void updateBoardingPoint(BoardingPoint org) {
		if (list.contains(org)) {
			int index = list.indexOf(org);
			list.set(index, org);
		} 
	}

	public void deleteBoardingPoint(Integer id) {
		for (BoardingPoint BoardingPoint : list) {
			if (BoardingPoint.getId().intValue()==id.intValue()) {
				list.remove(BoardingPoint);
				break;
			}
		}
	}

	public BoardingPoint getBoardingPointyId(Integer id) {
		BoardingPoint boardingPoint = null;
		for (BoardingPoint bPoint : list) {
			if (bPoint.getId().intValue()==id.intValue()) {
				boardingPoint = bPoint;
				break;
			}
		}
		return boardingPoint;
	}
	
	public BoardingPoint getBoardingPointByName(String boardPointName) {
		BoardingPoint boardingPoint = null;
		for (BoardingPoint boardPoint : list) {
			if (boardPoint.getName().equalsIgnoreCase(boardPointName)) {
				boardingPoint = boardPoint;
				break;
			}
		}
		return boardingPoint;
	}

}
