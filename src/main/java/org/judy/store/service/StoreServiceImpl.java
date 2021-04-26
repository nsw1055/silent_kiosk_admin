package org.judy.store.service;

import java.util.List;
import java.util.stream.Collectors;

import org.judy.common.util.ManagerFileDTO;
import org.judy.store.domain.Menu;
import org.judy.store.domain.MenuTopping;
import org.judy.store.domain.Store;
import org.judy.store.domain.Topping;
import org.judy.store.dto.MenuDTO;
import org.judy.store.dto.MenuToppingDTO;
import org.judy.store.dto.StoreDTO;
import org.judy.store.dto.ToppingDTO;
import org.judy.store.mapper.StoreMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@RequiredArgsConstructor
@Log4j
public class StoreServiceImpl implements StoreService {

	private final StoreMapper mapper;
	
	@Override
	public List<StoreDTO> getStore(String mid) {
		// TODO Auto-generated method stub
		return mapper.getStore(mid).stream().map(store -> {
			return toDTO(store);
		}).collect(Collectors.toList());
	}
	
	@Override
	public StoreDTO getStoreOne(Integer sno) {
		// TODO Auto-generated method stub
		return toDTO(mapper.getStoreOne(sno));
	}
	
	@Override
	public void delStore(Integer sno) {
		mapper.delStore(sno);
	}

	@Override
	public void deleteDoc(String muuid) {
		mapper.deleteDoc(muuid);	
	}
	
	@Override
	@Transactional
	public void updateStore(StoreDTO storeDTO) {
		
		mapper.deleteStoreImg(storeDTO.getSno());
		Store store = toDomain(storeDTO);
			
		storeDTO.getFileDTO().forEach(file ->{
			file.setSno(store.getSno());
			mapper.insertStoreImg(file);
		});	
		mapper.updateStore(store);	
	}	
	
	@Override
	@Transactional
	public Integer insertStore(StoreDTO storeDTO) {
		Store store = toDomain(storeDTO);
		
		mapper.insertStore(store);
		storeDTO.getFileDTO().forEach(file ->{
			file.setSno(store.getSno());
			mapper.insertStoreImg(file);
		});		
		return store.getSno();
	}
	
	@Override
	public List<ManagerFileDTO> getStoreImg(Integer sno) {
		return mapper.getStoreImage(sno);
	}
	

	// MENU
	
	@Override
	public List<MenuDTO> getMenu(Integer sno, Integer cno , String mid) {
		return mapper.getMenu(sno, cno , mid).stream().map(menu -> {
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
	public Integer insertMenu(MenuDTO menuDTO) {
		Menu menu = toDomMenu(menuDTO);
		mapper.insertMenu(menu);
		
		return menu.getMno();
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
	public Integer insertTop(ToppingDTO toppingDTO) {
		Topping topping = toDomTop(toppingDTO);
		mapper.insertTop(topping);
		
		return topping.getTno();
		
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

	@Override
	public List<ToppingDTO> selectedTop(Integer mno) {
		return mapper.selectedTop(mno).stream().map(topping -> {
			return toDtoTop(topping);
		}).collect(Collectors.toList());
	}

	@Override
	public void exceptTop(MenuToppingDTO menuToppingDTO) {
		MenuTopping menuTopping = MenuTopping.builder().mno(menuToppingDTO.getMno()).tno(menuToppingDTO.getTno()).build();
		mapper.exceptTop(menuTopping);
	}

	@Override
	public List<ToppingDTO> unSelectTop(MenuDTO menuDTO) {
		Menu menu = Menu.builder().sno(menuDTO.getSno()).mno(menuDTO.getMno()).build();
		return mapper.unSelectTop(menu).stream().map(topping -> {
			return toDtoTop(topping);
		}).collect(Collectors.toList());
	}

	@Override
	public void addTop(MenuToppingDTO menuToppingDTO) {
		MenuTopping menuTopping = MenuTopping.builder().mno(menuToppingDTO.getMno()).tno(menuToppingDTO.getTno()).build();
		mapper.addTop(menuTopping);
		
	}
	
}
