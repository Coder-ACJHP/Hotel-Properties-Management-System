package com.coder.hms.dao;

import java.util.List;

import com.coder.hms.entities.Posting;

public interface PostingDAO {

	public void savePosting(Posting posting);
	
	public boolean deletePosting(long theId);
	
	public Posting getPostingById(long Id);
	
	public List<Posting> getAllPostingsByRoomNumber(String roomNumber);
	
	public String getTotalDollarForOneDay(String date);
	
	public String getTotalLiraPostingsForOneDay(String date);
	
	public String getTotalEuroPostingsForOneDay(String date);
	
	public String getTotalPoundPostingsForOneDay(String date);
	
	public String getTotalCreditPostingsForOneDay(String date);
}
