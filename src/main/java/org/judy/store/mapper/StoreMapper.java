package org.judy.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.judy.common.util.ManagerFileDTO;
import org.judy.store.domain.Menu;
import org.judy.store.domain.MenuTopping;
import org.judy.store.domain.Store;
import org.judy.store.domain.Topping;

public interface StoreMapper {

	public List<Store> getStore(String mid);
	
	public Store getStoreOne(Integer sno);
	
	public void updateStore(Store store);
	
	public void delStore(Integer sno);
	
	public void deleteDoc(String muuid);
	
	public void insertStore(Store store);
	
	public void insertStoreImg(ManagerFileDTO dto);
	
	public List<ManagerFileDTO> getStoreImage(Integer sno);
	
	public void deleteStoreImg(Integer sno);
	
	// MENU
	
	public List<Menu> getMenu(@Param("sno") Integer sno, @Param("cno") Integer cno);
	
	public String menuSname(Integer sno);
	
	public Menu getOneMenu(Integer mno);
	
	public void insertMenu(Menu menu);
	
	public void delMenu(Integer mno);
	
	public void updateMenu(Menu menu);
	
	// TOPPING
	
	public List<Topping> selectedTop(Integer mno);
	
	public List<Topping> getTopping(Integer sno);
	
	public Topping getOneTopping(Integer tno);
	
	public void insertTop(Topping topping);
	
	public void delTop(Integer tno);
	
	public void updateTop(Topping topping);
	
	// MenuTopping
	public void exceptTop(MenuTopping menuTopping);
	
	public List<Topping> unSelectTop(Menu menu);
	
	public void addTop(MenuTopping menuTopping);
}
