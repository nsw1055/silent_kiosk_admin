package org.judy.store.service;

import java.util.List;
import java.util.stream.Collectors;

import org.judy.store.domain.Menu;
import org.judy.store.domain.Store;
import org.judy.store.domain.Topping;
import org.judy.store.dto.MenuDTO;
import org.judy.store.dto.StoreDTO;
import org.judy.store.dto.ToppingDTO;
import org.judy.store.mapper.StoreMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@RequiredArgsConstructor
@Log4j
public class StoreServiceImpl implements StoreService {

	private final StoreMapper mapper;
	
	@Override
	public StoreDTO getStore(String mid) {
		// TODO Auto-generated method stub
		StoreDTO dto = toDTO(mapper.getStore(mid));
		
		log.info("getStore........");
		return dto;
	}

	@Override
	public void deleteDoc(String muuid) {
		mapper.deleteDoc(muuid);	
	}
	

	@Override
	public Integer insertStore(StoreDTO storeDTO) {
		Store store = toDomain(storeDTO);
		
		mapper.insertStore(store);
		
		return store.getSno();
	}

	// MENU
	
	@Override
	public List<MenuDTO> getMenu(Integer sno, Integer cno) {
		return mapper.getMenu(sno, cno).stream().map(menu -> {
			return toDtoMenu(menu);
		}).collect(Collectors.toList());
	
	}

	@Override
	public String menuSname(Integer sno) {
		
		return mapper.menuSname(sno);
	}
	
	
	@Override
	public MenuDTO getOneMenu(Integer mno) {
		return toDtoMenu(mapper.getOneMenu(mno));
	}

	@Override
	public void insertMenu(MenuDTO menuDTO) {
		Menu menu = toDomMenu(menuDTO);
		mapper.insertMenu(menu);
	}

	@Override
	public void delMenu(Integer mno) {
		mapper.delMenu(mno);
		
	}

	@Override
	public void updateMenu(MenuDTO menuDTO) {
		Menu menu = toDomMenu(menuDTO);
		mapper.updateMenu(menu);
	}

	// TOPPING
	
	@Override
	public List<ToppingDTO> getTopping(Integer sno) {
		return mapper.getTopping(sno).stream().map(topping -> {
			return toDtoTop(topping);
		}).collect(Collectors.toList());
	}
	
	@Override
	public ToppingDTO getOneTopping(Integer tno) {
		return toDtoTop(mapper.getOneTopping(tno));
	}

	@Override
	public void insertTop(ToppingDTO toppingDTO) {
		Topping topping = toDomTop(toppingDTO);
		mapper.insertTop(topping);
		
	}

	@Override
	public void delTop(Integer tno) {
		mapper.delTop(tno);
		
	}

	@Override
	public void updateTop(ToppingDTO toppingDTO) {
		Topping topping = toDomTop(toppingDTO);
		mapper.updateTop(topping);
		
	}



	

	

	
	
}
