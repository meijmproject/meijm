package com.yh.hr.report.dto;

/**
 * 科主任及护士长系列人员汇总 - DTO
 * @author liul
 * @date 2017-3-7
 */
public class MatronStatisticsDTO {
	private Long deptOid;//科室代码
	private String deptName;//科室名称
	private String wardType;//病区类型
	private int kzrzwzCount; //科主任 职位 正
	private int kzrzwfCount; //科主任 职位 副
	private String kzrzwzxmCount; //科主任 职位 正 姓名
	private String kzrzwfxmCount; //科主任 职位 副 姓名
	private int kzrzczgCount; //科主任 职称 正高
	private int kzrzcfgCount; //科主任 职称 副高
	private int kzrzczjCount; //科主任 职称 中级
	private int kzrzccjCount;//科主任 职称 初级
	private int kzrxlbsCount;//科主任 学历 博士
	private int kzrxlssCount;//科主任 学历 硕士
	private int kzrxlbkCount;//科主任 学历 本科
	private int kzrxldzCount;//科主任 学历 大专
	private int kzrhjCount; //科主任 合计
	private int kzrSex1Count;//科主任 性别 男
	private int kzrSex2Count;//科主任 性别 女
	private String kzrnlCount;//科主任 平均年龄（岁）
	
	private int hszzwzCount; //护士长 职位 正
	private int hszzwfCount; //护士长 职位 副
	private String hszzwzxmCount; //护士长 职位 正 姓名
	private String hszzwfxmCount; //护士长 职位 副 姓名
	private int hszzczgCount; //护士长 职称 正高
	private int hszzcfgCount; //护士长 职称 副高
	private int hszzczjCount; //护士长 职称 中级
	private int hszzccjCount;//护士长 职称 初级
	private int hszxlbsCount;//护士长 学历 博士
	private int hszxlssCount;//护士长 学历 硕士
	private int hszxlbkCount;//护士长 学历 本科
	private int hszxldzCount;//护士长 学历 大专
	private int hszhjCount; //护士长 合计
	private int hszSex1Count;//护士长 性别 男
	private int hszSex2Count;//护士长 性别 女
	private String hsznlCount;//护士长 平均年龄（岁）
	public Long getDeptOid() {
		return deptOid;
	}
	public void setDeptOid(Long deptOid) {
		this.deptOid = deptOid;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getWardType() {
		return wardType;
	}
	public void setWardType(String wardType) {
		this.wardType = wardType;
	}
	public int getKzrzwzCount() {
		return kzrzwzCount;
	}
	public void setKzrzwzCount(int kzrzwzCount) {
		this.kzrzwzCount = kzrzwzCount;
	}
	public int getKzrzwfCount() {
		return kzrzwfCount;
	}
	public void setKzrzwfCount(int kzrzwfCount) {
		this.kzrzwfCount = kzrzwfCount;
	}
	public String getKzrzwzxmCount() {
		return kzrzwzxmCount;
	}
	public void setKzrzwzxmCount(String kzrzwzxmCount) {
		this.kzrzwzxmCount = kzrzwzxmCount;
	}
	public String getKzrzwfxmCount() {
		return kzrzwfxmCount;
	}
	public void setKzrzwfxmCount(String kzrzwfxmCount) {
		this.kzrzwfxmCount = kzrzwfxmCount;
	}
	public int getKzrzczgCount() {
		return kzrzczgCount;
	}
	public void setKzrzczgCount(int kzrzczgCount) {
		this.kzrzczgCount = kzrzczgCount;
	}
	public int getKzrzcfgCount() {
		return kzrzcfgCount;
	}
	public void setKzrzcfgCount(int kzrzcfgCount) {
		this.kzrzcfgCount = kzrzcfgCount;
	}
	public int getKzrzczjCount() {
		return kzrzczjCount;
	}
	public void setKzrzczjCount(int kzrzczjCount) {
		this.kzrzczjCount = kzrzczjCount;
	}
	public int getKzrzccjCount() {
		return kzrzccjCount;
	}
	public void setKzrzccjCount(int kzrzccjCount) {
		this.kzrzccjCount = kzrzccjCount;
	}
	public int getKzrxlbsCount() {
		return kzrxlbsCount;
	}
	public void setKzrxlbsCount(int kzrxlbsCount) {
		this.kzrxlbsCount = kzrxlbsCount;
	}
	public int getKzrxlssCount() {
		return kzrxlssCount;
	}
	public void setKzrxlssCount(int kzrxlssCount) {
		this.kzrxlssCount = kzrxlssCount;
	}
	public int getKzrxlbkCount() {
		return kzrxlbkCount;
	}
	public void setKzrxlbkCount(int kzrxlbkCount) {
		this.kzrxlbkCount = kzrxlbkCount;
	}
	public int getKzrxldzCount() {
		return kzrxldzCount;
	}
	public void setKzrxldzCount(int kzrxldzCount) {
		this.kzrxldzCount = kzrxldzCount;
	}
	public int getKzrhjCount() {
		return kzrhjCount;
	}
	public void setKzrhjCount(int kzrhjCount) {
		this.kzrhjCount = kzrhjCount;
	}
	public int getKzrSex1Count() {
		return kzrSex1Count;
	}
	public void setKzrSex1Count(int kzrSex1Count) {
		this.kzrSex1Count = kzrSex1Count;
	}
	public int getKzrSex2Count() {
		return kzrSex2Count;
	}
	public void setKzrSex2Count(int kzrSex2Count) {
		this.kzrSex2Count = kzrSex2Count;
	}
	public String getKzrnlCount() {
		return kzrnlCount;
	}
	public void setKzrnlCount(String kzrnlCount) {
		this.kzrnlCount = kzrnlCount;
	}
	public int getHszzwzCount() {
		return hszzwzCount;
	}
	public void setHszzwzCount(int hszzwzCount) {
		this.hszzwzCount = hszzwzCount;
	}
	public int getHszzwfCount() {
		return hszzwfCount;
	}
	public void setHszzwfCount(int hszzwfCount) {
		this.hszzwfCount = hszzwfCount;
	}
	public String getHszzwzxmCount() {
		return hszzwzxmCount;
	}
	public void setHszzwzxmCount(String hszzwzxmCount) {
		this.hszzwzxmCount = hszzwzxmCount;
	}
	public String getHszzwfxmCount() {
		return hszzwfxmCount;
	}
	public void setHszzwfxmCount(String hszzwfxmCount) {
		this.hszzwfxmCount = hszzwfxmCount;
	}
	public int getHszzczgCount() {
		return hszzczgCount;
	}
	public void setHszzczgCount(int hszzczgCount) {
		this.hszzczgCount = hszzczgCount;
	}
	public int getHszzcfgCount() {
		return hszzcfgCount;
	}
	public void setHszzcfgCount(int hszzcfgCount) {
		this.hszzcfgCount = hszzcfgCount;
	}
	public int getHszzczjCount() {
		return hszzczjCount;
	}
	public void setHszzczjCount(int hszzczjCount) {
		this.hszzczjCount = hszzczjCount;
	}
	public int getHszzccjCount() {
		return hszzccjCount;
	}
	public void setHszzccjCount(int hszzccjCount) {
		this.hszzccjCount = hszzccjCount;
	}
	public int getHszxlbsCount() {
		return hszxlbsCount;
	}
	public void setHszxlbsCount(int hszxlbsCount) {
		this.hszxlbsCount = hszxlbsCount;
	}
	public int getHszxlssCount() {
		return hszxlssCount;
	}
	public void setHszxlssCount(int hszxlssCount) {
		this.hszxlssCount = hszxlssCount;
	}
	public int getHszxlbkCount() {
		return hszxlbkCount;
	}
	public void setHszxlbkCount(int hszxlbkCount) {
		this.hszxlbkCount = hszxlbkCount;
	}
	public int getHszxldzCount() {
		return hszxldzCount;
	}
	public void setHszxldzCount(int hszxldzCount) {
		this.hszxldzCount = hszxldzCount;
	}
	public int getHszhjCount() {
		return hszhjCount;
	}
	public void setHszhjCount(int hszhjCount) {
		this.hszhjCount = hszhjCount;
	}
	public int getHszSex1Count() {
		return hszSex1Count;
	}
	public void setHszSex1Count(int hszSex1Count) {
		this.hszSex1Count = hszSex1Count;
	}
	public int getHszSex2Count() {
		return hszSex2Count;
	}
	public void setHszSex2Count(int hszSex2Count) {
		this.hszSex2Count = hszSex2Count;
	}
	public String getHsznlCount() {
		return hsznlCount;
	}
	public void setHsznlCount(String hsznlCount) {
		this.hsznlCount = hsznlCount;
	}
	
	
	
}
