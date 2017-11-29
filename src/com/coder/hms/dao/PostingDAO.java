package com.coder.hms.dao;

import java.util.List;

import com.coder.hms.entities.Posting;

public interface PostingDAO {

	public void savePosting(Posting posting);
	
	public boolean deletePosting(long theId);
	
	public Posting getPostingById(long Id);
	
	public List<Posting> getAllPostingsByRoomNumber(String roomNumber, String localDate);
	
	public String getTotalCashDollarPostingsForOneDay(String date);
	
	public String getTotalCashLiraPostingsForOneDay(String date);
	
	public String getTotalCashEuroPostingsForOneDay(String date);
	
	public String getTotalCashPoundPostingsForOneDay(String date);
	
	public String getTotalCreditLiraPostingsForOneDay(String date);
        
        public String getTotalCreditDollarPostingsForOneDay(String date);
        
        public String getTotalCreditEuroPostingsForOneDay(String date);
        
        public String getTotalCreditPoundPostingsForOneDay(String date);
}
