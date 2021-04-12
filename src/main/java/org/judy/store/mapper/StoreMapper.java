package org.judy.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.judy.store.domain.Menu;
import org.judy.store.domain.Store;
import org.judy.store.domain.Topping;

public interface StoreMapper {

	public Store getStore(String mid);
	
	
	public void deleteDoc(String muuid);
	
	public void insertStore(Store store);
	
	// MENU
	
	public List<Menu> getMenu(@Param("sno") Integer sno, @Param("cno") Integer cno);
	
	public Topping getOneTopping(Integer tno);
	
	public Menu getOneMenu(Integer mno);
	
	public void insertMenu(Menu menu);
	
	public void delMenu(Integer mno);
	
	public void updateMenu(Menu menu);
	
	// TOPPING
	
	public List<Topping> getTopping(Integer sno);
	
	public void insertTop(Topping topping);
	
	public void delTop(Integer tno);
	
	public void updateTop(Topping topping);
}
