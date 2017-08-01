package com.coder.hms.dao;

import java.util.List;

import com.coder.hms.entities.Posting;

public interface PostingDAO {

	public void savePosting(Posting posting);
	
	public void deletePosting(long theId);
	
	public Posting getPostingById(long Id);
	
	public List<Posting> getAllPostingsByRoomNumber(String roomNumber);
}
