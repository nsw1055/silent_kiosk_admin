package org.judy.manager.mapper;

import java.util.List;

import org.judy.common.util.PageDTO;
import org.judy.manager.domain.Manager;

public interface ManagerMapper {

	public List<Manager> getManagerList(PageDTO pageDTO);
	
	public List<Manager> delManagerList(PageDTO pageDTO);
	
	public List<Manager> appManagerList(PageDTO pageDTO);
	
	public Manager selectOne(String mid);
	
	public void registerMan(Manager manager);
	
	public int totalMan(PageDTO pageDTO);
	
	public void deleteMan(String mid);
	
	public int enabled(String mid);
	
	public void approval(String mid);
	
	public void updateMan(Manager manager);
	
}
