package guestbook.dao;

import java.util.List;

import guestbook.vo.GuestVo;


public interface GuestbookDao {
    public List<GuestVo> getList(); 
    public boolean insert(GuestVo vo); 
    public boolean delete(GuestVo vo);
    		GuestVo get(Long no);
}