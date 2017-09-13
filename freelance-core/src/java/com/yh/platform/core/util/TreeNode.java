/**
 * 
 */
package com.yh.platform.core.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

/**
 * 
 * @author zhangqp 
 * @since 2016年5月31日
 */
//@JsonInclude(Include.NON_NULL)//不输出null值的属性
public class TreeNode<T> {

	//把属性全部加到当前json对象中，而不是单独属性对象的值，只能针对pojo
	private T entry;
	private List<TreeNode<T>> children;
	
	private String id;
	private String name;
	
	private boolean leaf = false;
	
	//其他（json）属性
	private Map<String,Object> attributes = new HashMap<String, Object>(0);
	
	public TreeNode() {
	}
	
	public TreeNode(T entry) {
		this.entry = entry;
	}
	
	public T getEntry() {
		return entry;
	}
	
	public void setEntry(T entry) {
		this.entry = entry;
	}
	
	public List<TreeNode<T>> getChildren() {
		return children;
	}
	
	public void setChildren(List<TreeNode<T>> children) {
		this.children = children;
		leaf = CollectionUtils.isEmpty(children);
	}
	public boolean isLeaf() {
		return leaf;
	}
	
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttribute(String attr, Object value) {
		this.attributes.put(attr, value);
	}

	public String getId() {
		return id;
	}

	public void setId(Object id) {
		this.id = id == null ? null : id.toString();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
