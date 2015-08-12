package com.njyb.gbdbas.view;

import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.AbstractUrlBasedView;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;
/**
 * 自定义velcotiy解析视图1
 * @author jiahp
 *
 */
@SuppressWarnings("all")
public class MyViewVelocityResolve extends VelocityViewResolver {
	
	@Override
	protected AbstractUrlBasedView buildView(String viewName) throws Exception {
		return super.buildView(viewName);
	}

	@Override
	protected void initApplicationContext() {
		super.initApplicationContext();
	}

	@Override
	protected Class requiredViewClass() {
		return super.requiredViewClass();
	}

	@Override
	public void setDateToolAttribute(String dateToolAttribute) {
		super.setDateToolAttribute(dateToolAttribute);
	}

	@Override
	public void setNumberToolAttribute(String numberToolAttribute) {
		super.setNumberToolAttribute(numberToolAttribute);
	}

	@Override
	public void setToolboxConfigLocation(String toolboxConfigLocation) {
		super.setToolboxConfigLocation(toolboxConfigLocation);
	}

	@Override
	public void setAllowRequestOverride(boolean allowRequestOverride) {
		super.setAllowRequestOverride(allowRequestOverride);
	}

	@Override
	public void setAllowSessionOverride(boolean allowSessionOverride) {
		super.setAllowSessionOverride(allowSessionOverride);
	}

	@Override
	public void setExposeRequestAttributes(boolean exposeRequestAttributes) {
		super.setExposeRequestAttributes(exposeRequestAttributes);
	}

	@Override
	public void setExposeSessionAttributes(boolean exposeSessionAttributes) {
		super.setExposeSessionAttributes(exposeSessionAttributes);
	}

	@Override
	public void setExposeSpringMacroHelpers(boolean exposeSpringMacroHelpers) {
		super.setExposeSpringMacroHelpers(exposeSpringMacroHelpers);
	}

	@Override
	protected boolean canHandle(String viewName, Locale locale) {
		return super.canHandle(viewName, locale);
	}

	@Override
	protected View createView(String arg0, Locale arg1) throws Exception {
		return super.createView(arg0, arg1);
	}

	@Override
	public Map<String, Object> getAttributesMap() {
		return super.getAttributesMap();
	}

	@Override
	protected Object getCacheKey(String viewName, Locale locale) {
		return super.getCacheKey(viewName, locale);
	}

	@Override
	protected String getContentType() {
		return super.getContentType();
	}

	@Override
	public int getOrder() {
		return super.getOrder();
	}

	@Override
	protected String getPrefix() {
		return super.getPrefix();
	}

	@Override
	protected String getRequestContextAttribute() {
		return super.getRequestContextAttribute();
	}

	@Override
	protected String getSuffix() {
		return super.getSuffix();
	}

	@Override
	protected Class getViewClass() {
		return super.getViewClass();
	}

	@Override
	protected String[] getViewNames() {
		return super.getViewNames();
	}

	@Override
	protected boolean isRedirectContextRelative() {
		return super.isRedirectContextRelative();
	}

	@Override
	protected boolean isRedirectHttp10Compatible() {
		return super.isRedirectHttp10Compatible();
	}

	@Override
	protected View loadView(String viewName, Locale locale) throws Exception {
		return super.loadView(viewName, locale);
	}

	@Override
	public void setAttributes(Properties props) {
		super.setAttributes(props);
	}

	@Override
	public void setAttributesMap(Map<String, ?> attributes) {
		super.setAttributesMap(attributes);
	}

	@Override
	public void setContentType(String contentType) {
		super.setContentType(contentType);
	}

	@Override
	public void setExposePathVariables(Boolean exposePathVariables) {
		super.setExposePathVariables(exposePathVariables);
	}

	@Override
	public void setOrder(int order) {
		super.setOrder(order);
	}

	@Override
	public void setPrefix(String prefix) {
		super.setPrefix(prefix);
	}

	@Override
	public void setRedirectContextRelative(boolean redirectContextRelative) {
		super.setRedirectContextRelative(redirectContextRelative);
	}

	@Override
	public void setRedirectHttp10Compatible(boolean redirectHttp10Compatible) {
		super.setRedirectHttp10Compatible(redirectHttp10Compatible);
	}

	@Override
	public void setRequestContextAttribute(String requestContextAttribute) {
		super.setRequestContextAttribute(requestContextAttribute);
	}

	@Override
	public void setSuffix(String suffix) {
		super.setSuffix(suffix);
	}

	@Override
	public void setViewClass(Class viewClass) {
		super.setViewClass(viewClass);
	}

	@Override
	public void setViewNames(String[] viewNames) {
		super.setViewNames(viewNames);
	}

	@Override
	public void clearCache() {
		super.clearCache();
	}

	@Override
	public int getCacheLimit() {
		return super.getCacheLimit();
	}

	@Override
	public boolean isCache() {
		return super.isCache();
	}

	@Override
	public boolean isCacheUnresolved() {
		return super.isCacheUnresolved();
	}

	@Override
	public void removeFromCache(String arg0, Locale arg1) {
		super.removeFromCache(arg0, arg1);
	}

	@Override
	public View resolveViewName(String arg0, Locale arg1) throws Exception {
		return super.resolveViewName(arg0, arg1);
	}

	@Override
	public void setCache(boolean cache) {
		super.setCache(cache);
	}

	@Override
	public void setCacheLimit(int cacheLimit) {
		super.setCacheLimit(cacheLimit);
	}

	@Override
	public void setCacheUnresolved(boolean cacheUnresolved) {
		super.setCacheUnresolved(cacheUnresolved);
	}

	@Override
	protected void initApplicationContext(ApplicationContext context) {
		super.initApplicationContext(context);
	}

	protected void initServletContext(ServletContext servletContext) {
		super.initServletContext(servletContext);
	}

	@Override
	protected boolean isContextRequired() {
		return super.isContextRequired();
	}

	@Override
	protected Class requiredContextClass() {
		return super.requiredContextClass();
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
	}
	
}
