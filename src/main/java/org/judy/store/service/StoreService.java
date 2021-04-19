package org.judy.store.service;

import java.util.List;

import org.judy.common.util.ManagerFileDTO;
import org.judy.store.domain.Menu;
import org.judy.store.domain.Store;
import org.judy.store.domain.Topping;
import org.judy.store.dto.MenuDTO;
import org.judy.store.dto.MenuToppingDTO;
import org.judy.store.dto.StoreDTO;
import org.judy.store.dto.ToppingDTO;


public interface StoreService {
	
	List<StoreDTO> getStore(String mid);
	
	void deleteDoc(String muuid);
	
	Integer insertStore(StoreDTO storeDTO);
	
	StoreDTO getStoreOne(Integer sno);
	
	void delStore(Integer sno);
	
	void updateStore(StoreDTO storeDTO);
	
	List<ManagerFileDTO> getStoreImg(Integer sno);
	
	// MENU
	List<MenuDTO> getMenu(Integer sno, Integer cno);
	
	String menuSname(Integer sno);
	
	MenuDTO getOneMenu(Integer mno);
	
	Integer insertMenu(MenuDTO menuDTO);
	
	void delMenu(Integer mno);
	
	void updateMenu(MenuDTO menuDTO);
	
	// TOPPING
	
	List<ToppingDTO> selectedTop(Integer mno);
	
	List<ToppingDTO> unSelectTop(MenuDTO menuDTO);
	
	List<ToppingDTO> getTopping(Integer sno);
	
	ToppingDTO getOneTopping(Integer tno);
	
	Integer insertTop(ToppingDTO toppingDTO);
	
	void delTop(Integer tno);
	
	void updateTop(ToppingDTO toppingDTO);
	
	// MenuTopping
	
	void exceptTop(MenuToppingDTO menuToppingDTO);
	void addTop(MenuToppingDTO menuToppingDTO);

	default StoreDTO toDTO(Store store) {
		StoreDTO dto = StoreDTO.builder()
								.mid(store.getMid())
								.sno(store.getSno())
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
				.sno(dto.getSno())
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
				.cno(dto.getCno())
				.menuName(dto.getMenuName())
				.mprice(dto.getMprice())
				.content(dto.getContent())
				.mimg(dto.getMimg())
				.build();
		return menu;
	}
	default MenuDTO toDtoMenu(Menu menu) {
		MenuDTO dto = MenuDTO.builder()
				.mno(menu.getMno())
				.sno(menu.getSno())
				.cno(menu.getCno())
				.menuName(menu.getMenuName())
				.mprice(menu.getMprice())
				.content(menu.getContent())
				.mimg(menu.getMimg())
				.build();
		return dto;
	}
	
	default Topping toDomTop(ToppingDTO dto) {
		Topping topping = Topping.builder()
				.tno(dto.getTno())
				.sno(dto.getSno())
				.tprice(dto.getTprice())
				.timg(dto.getTimg())
				.tname(dto.getTname())
				.build();
		return topping;
	}
	
	default ToppingDTO toDtoTop(Topping topping) {
		ToppingDTO dto = ToppingDTO.builder()
				.tno(topping.getTno())
				.sno(topping.getSno())
				.tprice(topping.getTprice())
				.timg(topping.getTimg())
				.tname(topping.getTname())
				.build();
		return dto;
	}
	
}
