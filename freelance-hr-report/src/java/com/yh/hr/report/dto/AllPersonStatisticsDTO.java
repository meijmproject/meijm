package com.yh.hr.report.dto;

/**
 * 全院员工汇总统计 - DTO
 * @author liul
 * @date 2017-3-2
 */
public class AllPersonStatisticsDTO {
	private Long deptOid;//科室代码
	private String deptName;//科室名称
	private String wardType;//病区类型
	private int yiszgCount; //医师正高
	private int yisfgCount; //医师副高
	private int yiszjCount; //医师中级
	private int yiscjCount; //医师初级
	private int yishjCount; //执业医师合计
	private int yissxCount; //见习医师
	private int jiszgCount; //技师正高
	private int jisfgCount;//技师副高
	private int jiszjCount;//技师中级
	private int jiscjCount;//技师初级
	private int jishjCount;//技师合计
	private int jissxCount;//实习技师
	private int yaoszgCount; //药师正高
	private int yaosfgCount;//药师副高
	private int yaoszjCount;//药师中级
	private int yaoscjCount;//药师初级
	private int yaoshjCount;//药师合计
	private int yaossxCount;//实习药师
	private int yjyzgCount; //研究员正高
	private int yjyfgCount;//研究员副高
	private int yjyzjCount;//研究员中级
	private int yjycjCount;//研究员初级
	private int yjyhjCount;//研究员合计
	private int qtCount;//其他
	private int fhxjCount; //非护人员小计
	private int fhbsxlCount;//非护博士学历
	private int fhssxlCount;//非护硕士学历
	private int fhbkxlCount;//非护本科学历
	private int fhdzxlCount;//非护大专学历
	private int fhqtxlCount;//非护其他学历
	private String fhnlCount;//非护平均年龄
	private int fhSex1Count; //非护男
	private int fhSex2Count;//非护女
	private int fhgpCount;//非护规培人员
	private int hszgCount;//护士正高
	private int hsfgCount;//护士副高
	private int hszjCount;//护士中级
	private int hscjCount;//护士初级
	private int hssxCount;//护士见习
	private int hsxjCount;//护士小计
	private int hsbsxlCount;//护士博士学历
	private int hsssxlCount;//护士硕士学历
	private int hsbkxlCount;//护士本科学历
	private int hsdzxlCount;//护士大专学历
	private int hsqtxlCount;//护士其他学历
	private String hsnlCount;//护士平均年龄
	private int hsSex1Count; //护士男
	private int hsSex2Count;//护士女
	private int totalCount;//总计
	private String remark; //备注
	
	
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
	public int getYiszgCount() {
		return yiszgCount;
	}
	public void setYiszgCount(int yiszgCount) {
		this.yiszgCount = yiszgCount;
	}
	public int getYisfgCount() {
		return yisfgCount;
	}
	public void setYisfgCount(int yisfgCount) {
		this.yisfgCount = yisfgCount;
	}
	public int getYiszjCount() {
		return yiszjCount;
	}
	public void setYiszjCount(int yiszjCount) {
		this.yiszjCount = yiszjCount;
	}
	public int getYiscjCount() {
		return yiscjCount;
	}
	public void setYiscjCount(int yiscjCount) {
		this.yiscjCount = yiscjCount;
	}
	public int getYishjCount() {
		return yishjCount;
	}
	public void setYishjCount(int yishjCount) {
		this.yishjCount = yishjCount;
	}
	public int getYissxCount() {
		return yissxCount;
	}
	public void setYissxCount(int yissxCount) {
		this.yissxCount = yissxCount;
	}
	public int getJiszgCount() {
		return jiszgCount;
	}
	public void setJiszgCount(int jiszgCount) {
		this.jiszgCount = jiszgCount;
	}
	public int getJisfgCount() {
		return jisfgCount;
	}
	public void setJisfgCount(int jisfgCount) {
		this.jisfgCount = jisfgCount;
	}
	public int getJiszjCount() {
		return jiszjCount;
	}
	public void setJiszjCount(int jiszjCount) {
		this.jiszjCount = jiszjCount;
	}
	public int getJiscjCount() {
		return jiscjCount;
	}
	public void setJiscjCount(int jiscjCount) {
		this.jiscjCount = jiscjCount;
	}
	public int getJishjCount() {
		return jishjCount;
	}
	public void setJishjCount(int jishjCount) {
		this.jishjCount = jishjCount;
	}
	public int getJissxCount() {
		return jissxCount;
	}
	public void setJissxCount(int jissxCount) {
		this.jissxCount = jissxCount;
	}
	public int getYaoszgCount() {
		return yaoszgCount;
	}
	public void setYaoszgCount(int yaoszgCount) {
		this.yaoszgCount = yaoszgCount;
	}
	public int getYaosfgCount() {
		return yaosfgCount;
	}
	public void setYaosfgCount(int yaosfgCount) {
		this.yaosfgCount = yaosfgCount;
	}
	public int getYaoszjCount() {
		return yaoszjCount;
	}
	public void setYaoszjCount(int yaoszjCount) {
		this.yaoszjCount = yaoszjCount;
	}
	public int getYaoscjCount() {
		return yaoscjCount;
	}
	public void setYaoscjCount(int yaoscjCount) {
		this.yaoscjCount = yaoscjCount;
	}
	public int getYaoshjCount() {
		return yaoshjCount;
	}
	public void setYaoshjCount(int yaoshjCount) {
		this.yaoshjCount = yaoshjCount;
	}
	public int getYaossxCount() {
		return yaossxCount;
	}
	public void setYaossxCount(int yaossxCount) {
		this.yaossxCount = yaossxCount;
	}
	public int getYjyzgCount() {
		return yjyzgCount;
	}
	public void setYjyzgCount(int yjyzgCount) {
		this.yjyzgCount = yjyzgCount;
	}
	public int getYjyfgCount() {
		return yjyfgCount;
	}
	public void setYjyfgCount(int yjyfgCount) {
		this.yjyfgCount = yjyfgCount;
	}
	public int getYjyzjCount() {
		return yjyzjCount;
	}
	public void setYjyzjCount(int yjyzjCount) {
		this.yjyzjCount = yjyzjCount;
	}
	public int getYjycjCount() {
		return yjycjCount;
	}
	public void setYjycjCount(int yjycjCount) {
		this.yjycjCount = yjycjCount;
	}
	public int getYjyhjCount() {
		return yjyhjCount;
	}
	public void setYjyhjCount(int yjyhjCount) {
		this.yjyhjCount = yjyhjCount;
	}
	public int getQtCount() {
		return qtCount;
	}
	public void setQtCount(int qtCount) {
		this.qtCount = qtCount;
	}
	public int getFhxjCount() {
		return fhxjCount;
	}
	public void setFhxjCount(int fhxjCount) {
		this.fhxjCount = fhxjCount;
	}
	public int getFhbsxlCount() {
		return fhbsxlCount;
	}
	public void setFhbsxlCount(int fhbsxlCount) {
		this.fhbsxlCount = fhbsxlCount;
	}
	public int getFhssxlCount() {
		return fhssxlCount;
	}
	public void setFhssxlCount(int fhssxlCount) {
		this.fhssxlCount = fhssxlCount;
	}
	public int getFhbkxlCount() {
		return fhbkxlCount;
	}
	public void setFhbkxlCount(int fhbkxlCount) {
		this.fhbkxlCount = fhbkxlCount;
	}
	public int getFhdzxlCount() {
		return fhdzxlCount;
	}
	public void setFhdzxlCount(int fhdzxlCount) {
		this.fhdzxlCount = fhdzxlCount;
	}
	public int getFhqtxlCount() {
		return fhqtxlCount;
	}
	public void setFhqtxlCount(int fhqtxlCount) {
		this.fhqtxlCount = fhqtxlCount;
	}
	public String getFhnlCount() {
		return fhnlCount;
	}
	public void setFhnlCount(String fhnlCount) {
		this.fhnlCount = fhnlCount;
	}
	public int getFhSex1Count() {
		return fhSex1Count;
	}
	public void setFhSex1Count(int fhSex1Count) {
		this.fhSex1Count = fhSex1Count;
	}
	public int getFhSex2Count() {
		return fhSex2Count;
	}
	public void setFhSex2Count(int fhSex2Count) {
		this.fhSex2Count = fhSex2Count;
	}
	public int getFhgpCount() {
		return fhgpCount;
	}
	public void setFhgpCount(int fhgpCount) {
		this.fhgpCount = fhgpCount;
	}
	public int getHszgCount() {
		return hszgCount;
	}
	public void setHszgCount(int hszgCount) {
		this.hszgCount = hszgCount;
	}
	public int getHsfgCount() {
		return hsfgCount;
	}
	public void setHsfgCount(int hsfgCount) {
		this.hsfgCount = hsfgCount;
	}
	public int getHszjCount() {
		return hszjCount;
	}
	public void setHszjCount(int hszjCount) {
		this.hszjCount = hszjCount;
	}
	public int getHscjCount() {
		return hscjCount;
	}
	public void setHscjCount(int hscjCount) {
		this.hscjCount = hscjCount;
	}
	public int getHssxCount() {
		return hssxCount;
	}
	public void setHssxCount(int hssxCount) {
		this.hssxCount = hssxCount;
	}
	public int getHsxjCount() {
		return hsxjCount;
	}
	public void setHsxjCount(int hsxjCount) {
		this.hsxjCount = hsxjCount;
	}
	public int getHsbsxlCount() {
		return hsbsxlCount;
	}
	public void setHsbsxlCount(int hsbsxlCount) {
		this.hsbsxlCount = hsbsxlCount;
	}
	public int getHsssxlCount() {
		return hsssxlCount;
	}
	public void setHsssxlCount(int hsssxlCount) {
		this.hsssxlCount = hsssxlCount;
	}
	public int getHsbkxlCount() {
		return hsbkxlCount;
	}
	public void setHsbkxlCount(int hsbkxlCount) {
		this.hsbkxlCount = hsbkxlCount;
	}
	public int getHsdzxlCount() {
		return hsdzxlCount;
	}
	public void setHsdzxlCount(int hsdzxlCount) {
		this.hsdzxlCount = hsdzxlCount;
	}
	public int getHsqtxlCount() {
		return hsqtxlCount;
	}
	public void setHsqtxlCount(int hsqtxlCount) {
		this.hsqtxlCount = hsqtxlCount;
	}
	public String getHsnlCount() {
		return hsnlCount;
	}
	public void setHsnlCount(String hsnlCount) {
		this.hsnlCount = hsnlCount;
	}
	public int getHsSex1Count() {
		return hsSex1Count;
	}
	public void setHsSex1Count(int hsSex1Count) {
		this.hsSex1Count = hsSex1Count;
	}
	public int getHsSex2Count() {
		return hsSex2Count;
	}
	public void setHsSex2Count(int hsSex2Count) {
		this.hsSex2Count = hsSex2Count;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
	
}
