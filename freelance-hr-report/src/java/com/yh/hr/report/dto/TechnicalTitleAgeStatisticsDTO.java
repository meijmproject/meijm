package com.yh.hr.report.dto;

/**
 * 卫生技术人员依据职称等级汇总的年龄、性别架构表 - DTO
 * @author liul
 * @date 2017-3-7
 */
public class TechnicalTitleAgeStatisticsDTO {
	private String positionLevelCode;//院内岗位级别代码
	private String positionLevelName;//院内岗位级别名称
	private int age1;//25岁以下
	private int age2;//26-30岁
	private int age3;//31-35岁
	private int age4;//36-40岁
	private int age5;//41-45岁
	private int age6;//46-50岁
	private int age7;//51-55岁
	private int age8;//56岁以上
	private int sex1;//男
	private int sex2;//女
	private int total;//合计
	private Double bl;//比例
	private String avgAge;//平均年龄（岁）
	
	public String getPositionLevelCode() {
		return positionLevelCode;
	}
	public void setPositionLevelCode(String positionLevelCode) {
		this.positionLevelCode = positionLevelCode;
	}
	public String getPositionLevelName() {
		return positionLevelName;
	}
	public void setPositionLevelName(String positionLevelName) {
		this.positionLevelName = positionLevelName;
	}
	public int getAge1() {
		return age1;
	}
	public void setAge1(int age1) {
		this.age1 = age1;
	}
	public int getAge2() {
		return age2;
	}
	public void setAge2(int age2) {
		this.age2 = age2;
	}
	public int getAge3() {
		return age3;
	}
	public void setAge3(int age3) {
		this.age3 = age3;
	}
	public int getAge4() {
		return age4;
	}
	public void setAge4(int age4) {
		this.age4 = age4;
	}
	public int getAge5() {
		return age5;
	}
	public void setAge5(int age5) {
		this.age5 = age5;
	}
	public int getAge6() {
		return age6;
	}
	public void setAge6(int age6) {
		this.age6 = age6;
	}
	public int getAge7() {
		return age7;
	}
	public void setAge7(int age7) {
		this.age7 = age7;
	}
	public int getAge8() {
		return age8;
	}
	public void setAge8(int age8) {
		this.age8 = age8;
	}
	public int getSex1() {
		return sex1;
	}
	public void setSex1(int sex1) {
		this.sex1 = sex1;
	}
	public int getSex2() {
		return sex2;
	}
	public void setSex2(int sex2) {
		this.sex2 = sex2;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public Double getBl() {
		return bl;
	}
	public void setBl(Double bl) {
		this.bl = bl;
	}
	public String getAvgAge() {
		return avgAge;
	}
	public void setAvgAge(String avgAge) {
		this.avgAge = avgAge;
	}
	
}
