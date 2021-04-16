package org.judy.common.config;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.judy.manager.config.ManagerConfig;
import org.judy.notice.config.NoticeConfig;
import org.judy.store.config.StoreConfig;
import org.judy.time.config.TimeConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] { CommonConfig.class, TimeConfig.class, NoticeConfig.class, ManagerConfig.class, StoreConfig.class, SecurityConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] { ServletConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] { "/" };
	}

	@Override
	protected void customizeRegistration(Dynamic registration) {
		registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");

		MultipartConfigElement multipartConfig = 
				new MultipartConfigElement("C:\\upload\\temp", 
						20971520, 
						41943040,
						20971520);
		
		registration.setMultipartConfig(multipartConfig);
	}
}
