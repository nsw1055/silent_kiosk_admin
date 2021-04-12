package org.judy.store.service;

import java.util.List;

import org.judy.store.domain.Menu;
import org.judy.store.domain.Store;
import org.judy.store.domain.Topping;
import org.judy.store.dto.MenuDTO;
import org.judy.store.dto.StoreDTO;
import org.judy.store.dto.ToppingDTO;


public interface StoreService {
	
	StoreDTO getStore(String mid);
	
	void deleteDoc(String muuid);
	
	// MENU
	List<MenuDTO> getMenu(Integer sno, Integer cno);
	
	void insertMenu(MenuDTO menuDTO);
	
	void delMenu(Integer mno);
	
	void updateMenu(MenuDTO menuDTO);
	
	// TOPPING
	List<ToppingDTO> getTopping(Integer sno);
	
	void insertTop(ToppingDTO toppingDTO);
	
	void delTop(Integer tno);
	
	void updateTop(ToppingDTO toppingDTO);

	default StoreDTO toDTO(Store store) {
		StoreDTO dto = StoreDTO.builder()
								.mid(store.getMid())
								.sname(store.getSname())
								.lat(store.getLat())
								.lng(store.getLng())
								.address(store.getAddress())
								.category(store.getCategory())
								.logoImg(store.getLogoImg())
								.regdate(store.getRegdate())
								.updatedate(store.getUpdatedate())
								.build();
		return dto;
	}
		
	default Store toDomain(StoreDTO dto) {
		Store store = Store.builder()
				.mid(dto.getMid())
				.sname(dto.getSname())
				.lat(dto.getLat())
				.lng(dto.getLng())
				.address(dto.getAddress())
				.category(dto.getCategory())
				.logoImg(dto.getLogoImg())
				.regdate(dto.getRegdate())
				.updatedate(dto.getUpdatedate())
				.build();
		
	return store;
	
	}
	
	default Menu toDomMenu(MenuDTO dto) {
		Menu menu = Menu.builder()
				.mno(dto.getMno())
				.sno(dto.getSno())
				.menuName(dto.getMenuName())
				.mPrice(dto.getMPrice())
				.content(dto.getContent())
				.mImg(dto.getMImg())
				.category(dto.getCategory())
				.build();
		return menu;
	}
	default MenuDTO toDtoMenu(Menu menu) {
		MenuDTO dto = MenuDTO.builder()
				.mno(menu.getMno())
				.sno(menu.getSno())
				.menuName(menu.getMenuName())
				.mPrice(menu.getMPrice())
				.content(menu.getContent())
				.mImg(menu.getMImg())
				.category(menu.getCategory())
				.build();
		return dto;
	}
	
	default Topping toDomTop(ToppingDTO dto) {
		Topping topping = Topping.builder()
				.tno(dto.getTno())
				.sno(dto.getSno())
				.tPrice(dto.getTPrice())
				.tImg(dto.getTImg())
				.tName(dto.getTName())
				.build();
		return topping;
	}
	
	default ToppingDTO toDtoTop(Topping topping) {
		ToppingDTO dto = ToppingDTO.builder()
				.tno(topping.getTno())
				.sno(topping.getSno())
				.tPrice(topping.getTPrice())
				.tImg(topping.getTImg())
				.tName(topping.getTName())
				.build();
		return dto;
	}
	
}
