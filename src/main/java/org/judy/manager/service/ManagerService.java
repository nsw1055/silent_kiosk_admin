package org.judy.manager.service;

import java.util.List;

import org.judy.common.util.PageDTO;
import org.judy.manager.domain.Manager;
import org.judy.manager.dto.ManagerDTO;

public interface ManagerService {

	List<ManagerDTO> getManagerList(PageDTO pageDTO);
	
	List<ManagerDTO> delManagerList(PageDTO pageDTO);
	
	List<ManagerDTO> appManagerList(PageDTO pageDTO);
	
	ManagerDTO selectOne(String mid);
	
	int totalMan(PageDTO pageDTO);
	
	void enabled(String mid);
	
	void approval(String mid);
	
	void registerMan(ManagerDTO managerDTO);
	
	default ManagerDTO toDTO(Manager manager) {
		ManagerDTO dto = ManagerDTO.builder()
								.mid(manager.getMid())
								.mpw(manager.getMpw())
								.email(manager.getEmail())
								.phone(manager.getPhone())
								.enabled(manager.getEnabled())
								.approval(manager.getApproval())
								.cdn(manager.getCdn())
								.health(manager.getHealth())
								.hygiene(manager.getHygiene())
								.license(manager.getLicense())
								.regdate(manager.getRegdate())
								.updatedate(manager.getUpdatedate())
								.build();
		return dto;
	}
	
	default Manager toDomain(ManagerDTO dto) {
		Manager manager = Manager.builder()
							.mid(dto.getMid())
							.mpw(dto.getMpw())
							.email(dto.getEmail())
							.phone(dto.getPhone())
							.cdn(dto.getCdn())
							.health(dto.getHealth())
							.hygiene(dto.getHygiene())
							.license(dto.getLicense())
							.enabled(dto.getEnabled())
							.approval(dto.getApproval())
							.regdate(dto.getRegdate())
							.updatedate(dto.getUpdatedate())
							.build();
	return manager;
	}
	
}
