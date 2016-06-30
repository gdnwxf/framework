package com.wch.annotation.advance.test;

import java.io.Serializable;

import com.wch.annotation.web.PathVariable;

public class UserInfo implements Serializable{
	
	
	 /**    */
	private static final long serialVersionUID = -6989540413863453449L;
	private String name;
	private String id;
	private String agent;
	
	/**  
	 *@return  the name
	 */
	public String getName() {
		return name;
	}
	
	/** 
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**  
	 *@return  the id
	 */
	public String getId() {
		return id;
	}
	
	/** 
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**  
	 *@return  the agent
	 */
	public String getAgent() {
		return agent;
	}
	
	/** 
	 * @param agent the agent to set
	 */
	public void setAgent(@PathVariable("agent") String agent) {
		this.agent = agent;
	}
	
	

}
