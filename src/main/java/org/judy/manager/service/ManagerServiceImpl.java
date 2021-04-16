package org.judy.manager.service;

import java.util.List;
import java.util.stream.Collectors;

import org.judy.common.util.PageDTO;
import org.judy.manager.domain.Manager;
import org.judy.manager.dto.ManagerDTO;
import org.judy.manager.mapper.ManagerMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {



	private final ManagerMapper mapper;
	
	

	@Override
	public ManagerDTO selectOne(String mid) {
		
	
		return toDTO(mapper.selectOne(mid));
		
	
	}


	@Override
	public List<ManagerDTO> delManagerList(PageDTO pageDTO) {
		return mapper.delManagerList(pageDTO).stream().map(manager -> {
			return toDTO(manager);
		}).collect(Collectors.toList());
	}
	
	
	@Override
	public List<ManagerDTO> getManagerList(PageDTO pageDTO) {
		return mapper.getManagerList(pageDTO).stream().map(manager -> {
			return toDTO(manager);
		}).collect(Collectors.toList());
	}
	
	@Override
	public List<ManagerDTO> appManagerList(PageDTO pageDTO) {
		return mapper.appManagerList(pageDTO).stream().map(manager -> {
			return toDTO(manager);
		}).collect(Collectors.toList());
	}

	@Override
	public int totalMan(PageDTO pageDTO) {
		log.info("totalMan..........");
		return mapper.totalMan(pageDTO);
	}

	@Override
	public void enabled(String mid) {
		log.info("enabled...........");
		mapper.enabled(mid);
	}


	@Override
	public void approval(String mid) {
		log.info("approval..............");
		mapper.approval(mid);
	}
	

	@Override
	public void registerMan(ManagerDTO managerDTO) {
		log.info("registerMan.....");
		Manager manager = toDomain(managerDTO);
		mapper.registerMan(manager);
		
	}


	@Override
	public void updateMan(ManagerDTO managerDTO) {
		Manager manager = toDomain(managerDTO);
		mapper.updateMan(manager);
}


	


	

}
