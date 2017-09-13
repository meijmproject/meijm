/**
 * 
 */
package com.yh.hr.res.dictionary;


/**
 * 基础字典常量
 * 
 * @author zhangqp
 * @version 1.0, 16/08/17
 */

public class DicConstants {

	public static final String	YHRS0001	= "YHRS0001";	// 性别
	public static final String  YHRS0001_1 = "1";//男
	public static final String  YHRS0001_2 = "2";//女
	public static final String	YHRS0002	= "YHRS0002";	// 有效证件类别
	public static final String	YHRS0002_1	= "1";			// 居民身份证
	
	/** 标识码 */
	public static final String	YHRS0003	= "YHRS0003";	// 标识码，是否干部、是否退休等
	/** 标识码 - 否 */
	public static final String	YHRS0003_0	= "0";	// 否
	/** 标识码 - 是 */
	public static final String	YHRS0003_1	= "1";	// 是
	
	/** 户籍类型 */
	public static final String	YHRS0118	= "YHRS0118";	
	/** 非农业户口 */
	public static final String	YHRS0118_0	= "0";	
	/** 农业户口 */
	public static final String	YHRS0118_1	= "1";	
	
	/** 津贴类别编码YHRS1003 */
	public static final String	YHRS1003	= "YHRS1003";	
	
	public static final String	YHRS0004	= "YHRS0004";	// 民族
	public static final String	YHRS0004_1	= "01";	// 汉族
	public static final String	YHRS0005	= "YHRS0005";	// 国家和地区代码
	public static final String	YHRS0005_A156 = "A156";	    // 中国
	public static final String	YHRS0006	= "YHRS0006";	// 中国行政区划
	public static final String	YHRS0007	= "YHRS0007";	// 健康状况
	
	public static final String	YHRS0008	= "YHRS0008";	// 婚姻状况
	public static final String	YHRS0008_20	= "20";	// 已婚
	
	public static final String	YHRS0009	= "YHRS0009";	// 人员状态
	public static final String	YHRS0009_110	= "110";	// 在职
	public static final String	YHRS0009_120	= "120";	// 试用期
	public static final String	YHRS0009_130	= "130";	// 长期病休
	public static final String  YHRS0009_202	= "202";	// 调离本市机关单位
	public static final String  YHRS0009_207	= "207";	// 退休
	public static final String  YHRS0009_206	= "206";	// 离休
	public static final String  YHRS0009_212	= "212";	// 退职
	public static final String  YHRS0009_299   = "299";    //其他不在职
	public static final String  YHRS0009_209   = "209";    //自然减员(死亡)
	public static final String  YHRS0009_203   = "203";    //辞职
	public static final String  YHRS0009_204   = "204";    //辞退
	public static final String  YHRS0009_205   = "205";    //开除
	public static final String  YHRS0009_401   = "401";    //调至本市其他事业单位
	public static final String  YHRS0009_400   = "400";    //调出市本级单位
	
	public static final String  YHRS0009_300   = "300";    //保留工资关系
	/** 人员类别 */
	public static final String	YHRS0010		= "YHRS0010";	// 人员类别
	public static final String	YHRS0010_01		= "01";	// 正式人员
	public static final String	YHRS0010_02		= "02";	// 聘用人员
	public static final String	YHRS0010_03		= "03";	// 返聘人员
	public static final String	YHRS0010_04		= "04";	// 本院规培学员
	
	/** 人员类别 -委培人员*/
	public static final String	YHRS0010_05		= "05";	
	
	public static final String	YHRS0011	= "YHRS0011";	// 港澳台侨属标识
	public static final String	YHRS0012	= "YHRS0012";	// 编制类型
	public static final String	YHRS0012_1	= "1";	// 行政类型
	public static final String	YHRS0012_2	= "2";	// 事业编制
	public static final String	YHRS0013	= "YHRS0013";	// 拨款方式
	//public static final String	YHRS0114	= "YHRS0114";	// 原工作单位经费来源
	public static final String	YHRS0013_11	= "11";			// 全额补助
	public static final String	YHRS0013_21	= "21";			// 定额补助
	public static final String	YHRS0013_31	= "31";			// 自收自支
	public static final String	YHRS0014	= "YHRS0014";	// 个人身份
	
	public static final String	YHRS0015	= "YHRS0015";	// 职务级别代码
	//公务员职务级别
	public static final String	YHRS0015_101 = "101";     // 国家级正职
	public static final String	YHRS0015_102 = "102";     // 国家级副职
	public static final String	YHRS0015_111 = "111";     // 省部级正职
	public static final String	YHRS0015_112 = "112";     // 省部级副职
	public static final String	YHRS0015_121 = "121";     // 厅局级正职
	public static final String	YHRS0015_122 = "122";     // 厅局级副职
	public static final String	YHRS0015_131 = "131";     // 县处级正职
	public static final String	YHRS0015_132 = "132";     // 县处级副职
	public static final String	YHRS0015_141 = "141";     // 乡科级正职
	public static final String	YHRS0015_142 = "142";     // 乡科级副职
	public static final String	YHRS0015_150 = "150";     // 科员级
	public static final String	YHRS0015_160 = "160";     // 办事员级
	public static final String	YHRS0015_199 = "199";     // 未定职公务员

	/**事业单位职务层次编码[事业单位新增职务层次代码]*/
	public final static String YHRS0015_211 		= "211";//事业单位_一级职员（省部正职）
	public final static String YHRS0015_212 		= "212";//事业单位_二级职员（省部副职）
	public final static String YHRS0015_221 		= "221";//事业单位_三级职员（局级正职）
	public final static String YHRS0015_222 		= "222";//事业单位_四级职员（局级副职）
	public final static String YHRS0015_231 		= "231";//事业单位_五级职员（处级正职）
	public final static String YHRS0015_232 		= "232";//事业单位_六级职员（处级副职）
	public final static String YHRS0015_241 		= "241";//事业单位_七级职员（正科）
	public final static String YHRS0015_242 		= "242";//事业单位_八级职员（副科）
	public final static String YHRS0015_250 		= "250";//事业单位_九级职员（科员）
	public final static String YHRS0015_260 		= "260";//事业单位_十级职员（办事员）
	public final static String YHRS0015_299 		= "299";//事业单位_未定级职员
    
    public final static String YHRS0015_510			="510";	//高级技师 
    public final static String YHRS0015_520			="520";	//技师  
    public final static String YHRS0015_530			="530";	//高级工  
    public final static String YHRS0015_540			="540";	//中级工  
    public final static String YHRS0015_550			="550";	//初级工
    public final static String YHRS0015_560			="560";//普通工人
	public static final String YHRS0015_570 		="570"; //	技术工人-无职级
	
	public final static String YHRS0015_411 		= "411";		//事业单位专业技术职务级别-正高级(教授级)
	public final static String YHRS0015_412 		= "412";		//事业单位专业技术职务级别-副高级(副教授级)
	public final static String YHRS0015_420 		= "420";		//事业单位专业技术职务级别-中级(讲师级)
	public final static String YHRS0015_430 		= "430";		//事业单位专业技术职务级别-初级
	public final static String YHRS0015_434 		= "434";		//事业单位专业技术职务级别-助理级(助教级)
	public final static String YHRS0015_435 		= "435";		//事业单位专业技术职务级别-员级(教员级)
	public final static String YHRS0015_499 		= "499";		//事业单位专业技术职务级别-无职级
	
	
	public static final String	YHRS0016	= "YHRS0016";	// 公务员类别
	public static final String	YHRS0017	= "YHRS0017";	// 进入公务员队伍方式
	public static final String	YHRS0017_1	= "已登记";
	public static final String	YHRS0017_2	= "未登记";	
	
	
	public static final String	YHRS0018	= "YHRS0018";	// 公务员登记标识
	public static final String	YHRS0018_1	= "1";			// 已登记
	public static final String	YHRS0018_2	= "2";			// 未登记
	public static final String	YHRS0019	= "YHRS0019";	// 列入公务员法实施范围管理类别标识
	public static final String	YHRS0020	= "YHRS0020";	// 暂缓登记原因
	public static final String	YHRS0021	= "YHRS0021";	// 单位增减员代码
	public static final String	YHRS0022	= "YHRS0022";	// 事业单位岗位类别
	public static final String	YHRS0022_1	= "1";	// 事业单位岗位类别-管理类
	public static final String	YHRS0022_2	= "2";	// 事业单位岗位类别-专业技术类
	public static final String	YHRS0022_3	= "3";	// 事业单位岗位类别-工勤技能类

	public static final String	YHRS0023	= "YHRS0023";	// 事业单位岗位级别
	public static final String	YHRS0124	= "YHRS0124";	// 院内岗位级别
	public static final String	YHRS0124_1	= "1";	// 临床科室
	public static final String	YHRS0124_101	= "101";	// 正高级
	public static final String	YHRS0124_102	= "102";	// 副高级
	public static final String	YHRS0124_103	= "103";	// 中级
	public static final String	YHRS0124_104	= "104";	// 初级(师级）
	public static final String	YHRS0124_105	= "105";	// 初级(士级）
	public static final String	YHRS0124_106	= "106";	// 见习期
	public static final String	YHRS0124_2	= "2";	// 行政管理与后勤
	public static final String	YHRS0124_3	= "3";	// 工勤
	public static final String	YHRS0124_4	= "4";	// 研究人员
	public static final String	YHRS0124_401	= "401";	// 研究员（正高）
	public static final String	YHRS0124_402	= "402";	// 副研究人员（副高）
	public static final String	YHRS0124_403	= "403";	// 助理研究员（中级）
	public static final String	YHRS0124_404	= "404";	// 研究实习员（初级）
	
	
	public static final String	YHRS0024	= "YHRS0024";	// 与本人关系
	
	public static final String	YHRS0024_0	= "0";	//本人或户主
	public static final String	YHRS0024_1	= "1";	//配偶
	public static final String	YHRS0024_11	= "11";	//夫
	public static final String	YHRS0024_12	= "12";	//妻
	
	public static final String	YHRS0025	= "YHRS0025";	// 政治面貌
	public static final String	YHRS0025_12	= "12";	// 无党派人士
	public static final String	YHRS0025_13	= "13";	// 群众
	public static final String	YHRS0025_99	= "99";	// 其他
	public static final String	YHRS0025_01	= "01";	// 中国共产党党员
	public static final String	YHRS0025_02	= "02";	// 中国共产党预备党员
	public static final String	YHRS0025_03	= "03";	// 中国共产主义青年团团员
	public static final String	YHRS0026	= "YHRS0026";	// 任职状态
	/**  在任 */
	public static final String	YHRS0026_001	= "001";
	/**  不在任 */
	public static final String	YHRS0026_002	= "002";
	
	public static final String	YHRS0027	= "YHRS0027";	// 职务类别
	
	/** 职务属性 */
	public static final String	YHRS0028	= "YHRS0028";	// 职务属性
	public static final String	YHRS0028_10	= "10";	// 单位领导正职
	public static final String	YHRS0028_11	= "11";	// 单位领导副职
	public static final String	YHRS0028_21	= "21";	// 内设机构领导正职
	public static final String	YHRS0028_22	= "22";	// 内设机构领导副职
	public static final String	YHRS0028_31	= "31";	// 非领导
	public static final String	YHRS0028_41	= "41";	// 护士长正职
	public static final String	YHRS0028_42	= "42";	// 护士长副职
	public static final String	YHRS0028_51	= "51";	// 药师长正职
	public static final String	YHRS0028_52	= "52";	// 药师长副职
	public static final String	YHRS0028_61	= "61";	// 技师长正职
	public static final String	YHRS0028_62	= "62";	// 技师长副职
	public static final String	YHRS0028_9	= "9";	// 其他
	
	/** 领导标识 */  	
	public static final String	YHRS1001 = "YHRS1001";	//领导标识
	public static final String	YHRS1001_101 = "101";	//非领导
	/** 单位领导职务 */  
	public static final String	YHRS0028_010110		= "010110";
	/** 内设机构领导职务 */    
	public static final String	YHRS0028_010120		= "010120";
	/**  非领导 */   
	public static final String	YHRS0028_030		= "030";
	
	public static final String	YHRS0029	= "YHRS0029";	// 干部职务名称
	public static final String	YHRS0030	= "YHRS0030";	// 专业技术职务名称
	public static final String	YHRS0031	= "YHRS0031";	// 技术工种
	public static final String	YHRS0032	= "YHRS0032";	// 任职方式
	public static final String	YHRS0032_5	= "5";			// 录用
	public static final String	YHRS0033	= "YHRS0033";	// 任职原因
	public static final String	YHRS0033_12	= "12";			// 聘任干部
	public static final String	YHRS0034	= "YHRS0034";	// 职务变动类别
	public static final String	YHRS0034_11	= "11";			// 新定职
	public static final String	YHRS0035	= "YHRS0035";	// 免职辞职原因
	public static final String	YHRS0035_23	= "23";	// 免职辞职原因-因调出免职
	public static final String	YHRS0035_32	= "32";	// 免职辞职原因-因受处分撤职
	public static final String	YHRS0035_43	= "43";	// 免职辞职原因-因受处分降职
	public static final String YHRS0035_6 =  "6"; //辞退
	public static final String YHRS0035_7 =  "7"; //开除
	
	public static final String	YHRS0036	= "YHRS0036";	// 职务类型
	public static final String	YHRS0036_1	= "1";	// 机关行政职务
	public static final String	YHRS0036_4	= "4";	// 事业单位管理职务
	public static final String	YHRS0036_5	= "5";	// 事业单位专业技术职务
	public static final String	YHRS0036_6	= "6";	// 事业单位工人职务
	
	
	public static final String	YHRS0037	= "YHRS0037";	// 试用类别
	public static final String YHRS0037_3  = "3";          //录用
	public static final String YHRS0037_5  = "5";          //取消录用
	
	
	public static final String	YHRS0039	= "YHRS0039";	// 学历
	/** 研究生 */
	public static final String	YHRS0039_1	= "1";	// 研究生
	/** 大学本科 */
	public static final String	YHRS0039_2	= "2";	// 大学本科
	/** 大专 */
	public static final String	YHRS0039_3	= "3";	// 大专
	/** 中等专科 */
	public static final String	YHRS0039_4	= "4";	// 中等专科
	/** 职业高中 */
	public static final String	YHRS0039_5	= "5";	// 职业高中
	/** 技工学校 */
	public static final String	YHRS0039_6	= "6";	// 技工学校
	/** 普通高中 */
	public static final String	YHRS0039_7	= "7";	// 普通高中
	/** 初中 */
	public static final String	YHRS0039_8	= "8";	// 初中
	/** 小学 */
	public static final String	YHRS0039_9	= "9";	// 小学
	/** 博士研究生 */
	public static final String	YHRS0039_10	= "10";	// 博士研究生
	/** 硕士研究生 */
	public static final String	YHRS0039_11	= "11";	// 硕士研究生
	/** 研究生班 */
	public static final String	YHRS0039_12	= "12";	// 研究生班
	/** 博士后 */
	public static final String	YHRS0039_13	= "13";	// 博士后
	
	
	public static final String	YHRS0040	= "YHRS0040";	// 学位
	/** 学位-学士 */
	public static final String	YHRS0040_1	= "1";	// 学士
	/** 学位-双学士 */
	public static final String	YHRS0040_2	= "2";	// 双学士
	/** 学位-硕士 */
	public static final String	YHRS0040_3	= "3";	// 硕士
	/** 学位-博士 */
	public static final String	YHRS0040_4	= "4";	// 博士
	/** 学位-博士后 */
	public static final String	YHRS0040_5	= "5";	// 博士后

	
	public static final String	YHRS0041	= "YHRS0041";	// 学历等级
	/** 学历等级 - 博士研究生 */
	public static final String YHRS0041_1 = "1";		//学历等级 - 博士研究生
	/** 学历等级 - 硕士研究生 */
	public static final String YHRS0041_2 = "2";		//学历等级 - 硕士研究生
	/** 学历等级 - 双学士,研究生班毕业及未获硕士学位的研究生,本科六年以上 */
	public static final String YHRS0041_3 = "3";		//学历等级 - 双学士,研究生班毕业及未获硕士学位的研究生,本科六年以上
	/** 学历等级 - 大学本科的毕业生 */
	public static final String YHRS0041_4 = "4";		//学历等级 - 大学本科的毕业生
	/** 学历等级 - 大学专科的毕业生 */
	public static final String YHRS0041_5 = "5";		//学历等级 - 大学专科的毕业生
	/** 学历等级 - 高中,中专及中技毕业生 */
	public static final String YHRS0041_6 = "6";		//学历等级 - 高中,中专及中技毕业生
	/** 学历等级 - 初中毕业生以下 */
	public static final String YHRS0041_7 = "7";		//学历等级 - 初中毕业生以下
	
	public static final String	YHRS0042	= "YHRS0042";	// 学习形式
	
	/**   正规高等院校 */
	public static final String	YHRS0042_01	= "01";
	
	public static final String	YHRS9004	= "YHRS9004";	// 职业资格名称
	public static final String	YHRS0043	= "YHRS0043";	// 教育类别
	
	/** 全日制教育  **/
	public static final String	YHRS0043_1	= "1";	
	/** 在职教育  **/
	public static final String	YHRS0043_2	= "2";	
	
	public static final String	YHRS9003	= "YHRS9003";	// 院校类别
	public static final String	YHRS9003_1	= "1";	// 211
	public static final String	YHRS9003_2	= "2";	// 985
	public static final String	YHRS9003_9	= "9";	// 其他
	public static final String	YHRS0047	= "YHRS0047";	// 专业技术资格等级
	public static final String	YHRS0047_411	= "411";	// 正高级
	public static final String	YHRS0047_412	= "412";	// 副高级
	public static final String	YHRS0047_420	= "420";	// 中级
	public static final String	YHRS0047_434	= "434";	// 助理级
	public static final String	YHRS0047_435	= "435";	// 员级
	public static final String	YHRS0048	= "YHRS0048";	// 取得资格途径
	public static final String	YHRS0049	= "YHRS0049";	// 职业资格等级
	public static final String	YHRS0049_1	= "1";	// 职业资格一级（高级技师）
	public static final String	YHRS0049_2	= "2";	// 职业资格二级（技师）
	public static final String	YHRS0049_3	= "3";	// 职业资格三级（高级）
	public static final String	YHRS0049_4	= "4";	// 职业资格四级（中级）
	public static final String	YHRS0049_5	= "5";	// 职业资格五级（初级）
	public static final String	YHRS0050	= "YHRS0050";	// 教育培训性质
	public static final String	YHRS0051	= "YHRS0051";	// 培训离岗状态
	public static final String	YHRS0052	= "YHRS0052";	// 从学单位类别
	public static final String	YHRS0053	= "YHRS0053";	// 培训类别
	public static final String	YHRS0054	= "YHRS0054";	// 语种名称
	public static final String	YHRS0055	= "YHRS0055";	// 语种熟练程度
	public static final String	YHRS0056	= "YHRS0056";	// 衔称
	public static final String	YHRS0057	= "YHRS0057";	// 离退类别
	public static final String	YHRS0058	= "YHRS0058";	// 提前退休原因
	public static final String	YHRS0059	= "YHRS0059";	// 离退休费支付单位
	public static final String	YHRS0060	= "YHRS0060";	// 离退休审批类型
	public static final String	YHRS0061	= "YHRS0061";	// 离退休符合条例
	public static final String	YHRS0062	= "YHRS0062";	// 军队职务级别
	public static final String	YHRS0063	= "YHRS0063";	// 军队文职专业技术级别
	public static final String	YHRS0064	= "YHRS0064";	// 军队非专业技术文职级别
	public static final String	YHRS0065	= "YHRS0065";	// 军队专业技术等级
	public static final String	YHRS0066	= "YHRS0066";	// 退役前军队职务
	public static final String	YHRS0067	= "YHRS0067";	// 退役类别
	public static final String	YHRS0068	= "YHRS0068";	// 安置类别
	
	public static final String	YHRS0068_1	= "1";	// 自主择业
	public static final String	YHRS0068_2	= "2";	// 政策安置
	
	public static final String	YHRS0069	= "YHRS0069";	// 考核类别
	public static final String	YHRS0070	= "YHRS0070";	// 考核结论
	public static final String YHRS0070_1  = "1"; //优秀
	public static final String YHRS0070_2  = "2"; //称职
	public static final String YHRS0070_3  = "3"; //基本称职
	public static final String YHRS0070_4  = "4"; //不称职
	public static final String YHRS0070_5  = "5"; //不确定考核等级
	public static final String YHRS0070_6  = "6"; //未参加考核
	
	public static final String	YHRS0123	= "YHRS0123";	// 事业考核结论
	public static final String YHRS0123_01  = "01"; //优秀
	public static final String YHRS0123_02  = "02"; //合格
	public static final String YHRS0123_04  = "04"; //不合格
	public static final String YHRS0123_05  = "05"; //不定等次
	public static final String YHRS0123_06  = "06"; //未参加考核
	public static final String YHRS0123_07  = "07"; //缓定等次
	
	public static final String	YHRS0071	= "YHRS0071";	// 未参加考核原因
	public static final String	YHRS0072	= "YHRS0072";	// 参加考核不定等次原因
	public static final String YHRS0072_1  = "1";//新录(聘)用的人员在试用期间，应对起其进行考核，但在年度考核时只写评语，不定等次
	public static final String YHRS0072_3  = "3";//受记过，记大过，降级，撤职处分的公务员，对其进行考核，但在受处分期间只写评语，不定等次
	
	public static final String	YHRS0073	= "YHRS0073";	// 不晋升原因
	public static final String	YHRS0074	= "YHRS0074";	// 党内职务
	public static final String	YHRS0075	= "YHRS0075";	// 异常事项类别
	public static final String	YHRS0076	= "YHRS0076";	// 奖励类别
	public static final String	YHRS0077	= "YHRS0077";	// 授予荣誉称号级别
	public static final String	YHRS0077_9	= "9";	// 授予荣誉称号级别
	
	public static final String	YHRS0078	= "YHRS0078";	// 奖励原因类别
	public static final String	YHRS0079	= "YHRS0079";	// 奖励批准单位类别
	public static final String	YHRS0080	= "YHRS0080";	// 享受奖励待遇类别
	public static final String	YHRS0082	= "YHRS0082";	// 处分原因
	public static final String	YHRS0083	= "YHRS0083";	// 处分原因类别
	public static final String	YHRS0084	= "YHRS0084";	// 处分批准机关类别
	public static final String	YHRS0085	= "YHRS0085";	// 休假类型
	public static final String	YHRS0086	= "YHRS0086";	// 休假待遇类型
	public static final String	YHRS0087	= "YHRS0087";	// 伤残等级
	public static final String	YHRS0088	= "YHRS0088";	// 工资系列
	public static final String	YHRS0089	= "YHRS0089";	// 工资变动类型
	public static final String	YHRS1002	= "YHRS1002";	// 其他工资变动类型

	public static final String	YHRS0081	= "YHRS0081";	// 处分名称
	public static final String	YHRS0081_14	= "14";	// 国家公务员纪律处分-降级
	public static final String	YHRS0081_17	= "17";	// 国家公务员纪律处分-撤职
	public static final String	YHRS0081_19	= "19";	// 国家公务员纪律处分-开除
	public static final String	YHRS0081_1	= "1";	// 国家公务员纪律处分
	public static final String	YHRS0081_5	= "5";	// 中国人民解放军军人纪律处分
	public static final String	YHRS0081_6	= "6";	// 企业职工纪律处分
	public static final String	YHRS0081_26	= "26";	// 事业单位工作人员处分-开除
	public static final String YHRS0081_10= "10";//国家公务员纪律处分--警告
	public static final String YHRS0081_12= "12";//国家公务员纪律处分--记过
	public static final String YHRS0081_13= "13";//国家公务员纪律处分--记大过
	
	/** 单位性质 */
	public static final String	YHRS0090	 = "YHRS0090";	// 单位性质
	public static final String	YHRS0090_101 = "101";		// 行政机关
	public static final String	YHRS0090_102 = "102";		// 行政执法
	public static final String	YHRS0090_103 = "103";		// 参公管理类事业单位
	/** 事业单位 */
	public static final String	YHRS0090_104 = "104";		// 事业单位
	public static final String	YHRS0090_105 = "105";		// 群团机关
	
	//101、行政机关；102、行政执法；103、参公单位；105、群团机关
	/**
	 * 机关单位性质集合
	 */
	public static final String[] JG_UNIT_KINDS = new String[]{YHRS0090_101, YHRS0090_102, YHRS0090_103, YHRS0090_105};
	/**
	 * 事业单位性质集合
	 */
	public static final String[] SY_UNIT_KINDS = new String[]{YHRS0090_104};
	
	/**系统类别*/
	public static final String	YHRS0091	= "YHRS0091";	// 系统类别
	/**系统类别-政府系统*/
	public static final String	YHRS0091_3	= "3";			// 系统类别-政府系统（政府系统外全部归为党群系统）
	
	public static final String	YHRS0092	= "YHRS0092";	// 机关机构类别
	public static final String	YHRS0093	= "YHRS0093";	// 机构级别
	public static final String	YHRS0094	= "YHRS0094";	// 机关下设机构编码（中编办）
	public static final String	YHRS0095	= "YHRS0095";	// 单位属性（中编办）（机关）
	public static final String	YHRS0096	= "YHRS0096";	// 机关编码（中编办）
	public static final String	YHRS0097	= "YHRS0097";	// 机构隶属关系（事业）
	public static final String	YHRS0098	= "YHRS0098";	// 所属行业（中编办）（事业）
	public static final String	YHRS0099	= "YHRS0099";	// 事业单位类型（中编办）（事业）
	public static final String	YHRS0100	= "YHRS0100";	// 单位属性（中编办）（事业）
	
	/** 机构状态 */
	public static final String	YHRS0101	= "YHRS0101";	// 机构状态
	/** 机构状态-新建*/
	public static final String	YHRS0101_1	= "1";			
	/** 机构状态-正常*/
	public static final String	YHRS0101_2	= "2";			
	/** 机构状态-撤销*/
	public static final String	YHRS0101_3	= "3";			
	
	/** 内设机构类型 */
	public static final String	YHRS0102	= "YHRS0102";	// 内设机构类型
	/** 内设机构类型-单位领导 */
	public static final String	YHRS0102_1	= "1";	// 单位领导
	/** 内设机构类型-内设机构 */
	public static final String	YHRS0102_2	= "2";	// 内设机构
	/** 内设机构类型-其他在职人员 */
	public static final String	YHRS0102_3	= "3";	// 其他在职人员
	
	public static final String	YHRS0103	= "YHRS0103";	// 死亡类型
	public static final String	YHRS0104	= "YHRS0104";	// 死亡原因
	public static final String	YHRS0105	= "YHRS0105";	// 聘用条件
	
	public static final String	YHRS0105_4000	= "4000";	// 军转干部安置
	
	public static final String	YHRS0106	= "YHRS0106";	// 工作单位类别
	public static final String	YHRS0106_11	= "11";	// 本市市直机关

	public static final String	YHRS0106_21	= "21";	// 市外机关单位
	public static final String	YHRS0108	= "YHRS0108";	// 考生类型
	public static final String	YHRS0108_01	= "01";	//应届毕业生
	public static final String	YHRS0109	= "YHRS0109";	// 离退后享受待遇类别
	public static final String	YHRS0110	= "YHRS0110";	// 进入事业单位方式
	public static final String	YHRS0111	= "YHRS0111";	// 特殊岗位类别
	public static final String	YHRS9001	= "YHRS9001";	// 用户类型
	public static final String	YHRS9001_01  = "01";   //个人用户
	public static final String	YHRS9001_02  = "02";   //单位用户
	public static final String	YHRS9001_03  = "03";   //科室用户
	public static final String	YHRS9001_04  = "04";   //审批用户
	public static final String	YHRS9001_08  = "08";   //普通用户
	public static final String	YHRS9001_09  = "09";   //管理员
	public static final String	YHRS9002	= "YHRS9002";	// 用户状态
	public static final String	YHRS9002_0	= "0";	// 正常
	public static final String	YHRS9002_1	= "1";	// 初始
	public static final String	YHRS9002_2	= "2";	// 冻结
	public static final String	YHRS9002_3	= "3";	// 失效
	public static final String	YHRS0112	= "YHRS0112";	// 调动类型
	
	public static final String YHRS1000 = "YHRS1000";//工资历史生效标识
	/**
	 * 工资历史生效标识 - 失效
	 */
	public static final String YHRS1000_0 = "0";		//失效
	/**
	 * 工资历史生效标识 - 生效
	 */
	public static final String YHRS1000_1 = "1";		//生效
	/**
	 * 工资历史生效标识 - 计算值
	 */
	public static final String YHRS1000_3 = "3";		//工资历史计算值
	/**
	 * 工资历史生效标识 - 修正值
	 */
	public static final String YHRS1000_4 = "4";		//工资历史修正值
	/**
	 * 工资历史生效标识 - 重算值
	 */
	public static final String YHRS1000_5 = "5";		//工资历史重算值
	
	public static final String YHRS0088_010 = "010"; 	//工资系列 - 机关公务员
	public static final String YHRS0088_020 = "020"; 	//工资系列 - 机关技术工人
	public static final String YHRS0088_030 = "030"; 	//工资系列 - 机关普通工人
	public static final String YHRS0088_040 = "040"; 	//工资系列 - 事业单位管理人员
	public static final String YHRS0088_050 = "050"; 	//工资系列 - 事业单位专业技术人员
	public static final String YHRS0088_060 = "060"; 	//工资系列 - 事业单位技术工人
	public static final String YHRS0088_070 = "070"; 	//工资系列 - 事业单位普通工人
	
	public static final String YHRS0089_100 = "100";	//工资变动类型-新参加工作
	public static final String YHRS0089_101 = "101";	//工资变动类型-转正定级
	public static final String YHRS0089_102 = "102";	//工资变动类型-交流调动
	public static final String YHRS0089_103 = "103";	//工资变动类型-军转安置
	public static final String YHRS0089_104 = "104";	//工资变动类型-同系列职务变动
	public static final String YHRS0089_105 = "105";	//工资变动类型-跨系列职务变动
	public static final String YHRS0089_106 = "106";	//工资变动类型-学历变动
	public static final String YHRS0089_107 = "107";	//工资变动类型-年度考核正常晋升
	public static final String YHRS0089_108 = "108";	//工资变动类型-奖励
	public static final String YHRS0089_109 = "109";	//工资变动类型-惩处
	public static final String YHRS0089_110 = "110";	//工资变动类型-工资更正
	public static final String YHRS0089_111 = "111";	//工资变动类型-2006年7月工资改革
	public static final String YHRS0089_112 = "112";	//工资变动类型-工资标准调整
	public static final String YHRS0089_113 = "113";	//工资变动类型-其他
	public static final String YHRS0089_201 = "201";	//工资变动类型-工龄变更
	public static final String YHRS0089_202 = "202";	//工资变动类型-高定2个级别工资档次
	public static final String YHRS0089_203 = "203";	//工资变动类型-高定1个级别工资档次
	public static final String YHRS0089_204 = "204";	//工资变动类型-低定2个级别工资档次
	public static final String YHRS0089_205 = "205";	//工资变动类型-低定1个级别工资档次
	public static final String YHRS0089_206 = "206";	//工资变动类型-高定2薪级
	public static final String YHRS0089_207 = "207";	//工资变动类型-高定1薪级
	public static final String YHRS0089_208 = "208";	//工资变动类型-低定2薪级
	public static final String YHRS0089_209 = "209";	//工资变动类型-低定1薪级
	public static final String YHRS0089_210 = "210";	//工资变动类型-浮动1薪级
	public static final String YHRS0089_211 = "211";	//工资变动类型-取消浮动1薪级
	public static final String YHRS0089_212 = "212";	//工资变动类型-固定浮动薪级
	public static final String YHRS0089_213 = "213";	//工资变动类型-特殊教育津贴
	public static final String YHRS0089_214 = "214";	//工资变动类型-取消特殊教育津贴
	
	/**业务状态*/
	public static final String	YHRS2001	= "YHRS2001";	// 业务状态
	public static final String	YHRS2001_U600	= "U600";	// 待确认调出
	public static final String	YHRS2001_U601	= "U601";	// 已确认调出
	public static final String	YHRS2001_U602	= "U602";	// 撤销调出
	public static final String	YHRS2001_U609	= "U609";	// 调出终止
	public static final String	YHRS2001_SU600	= "SU600";	// 待确认调出(事业)
	public static final String	YHRS2001_SU601	= "SU601";	// 已确认调出(事业)
	public static final String	YHRS2001_SU602	= "SU602";	// 撤销调出(事业)
	public static final String	YHRS2001_SU609	= "SU609";	// 调出终止(事业)
	/**在办部门*/
	public static final String	YHRS2002	= "YHRS2002";	// 在办部门
	/**办理结果*/
	public static final String	YHRS2003	= "YHRS2003";	// 办理结果
	
	public static final String	YHRS2003_0	= "0";		//审核同意
	public static final String	YHRS2003_1	= "1";		//审核不同意
	public static final String	YHRS2003_2	= "2";		//异常办结（业务终止)
	
	public static final String	YHRS0110_1	=    "1";	// 公开招聘
	public static final String	YHRS0110_2	=    "2";	// 人才引进
	public static final String	YHRS0110_3	=    "3";	// 军转
	public static final String	YHRS0110_4	=    "4";	// 调入
	public static final String	YHRS0110_5	=    "5";	// 公开选调
	public static final String	YHRS0110_6	=    "6";	// 外招
	public static final String	YHRS0110_7	=    "7";	// 毕业生派遣
	public static final String	YHRS0110_8	=    "8";	// 招工
	public static final String	YHRS0110_9	=    "9";	// 其他
	
	public static final String	YHRS0111_1	= "1";	// 特殊岗位类别-教师
	public static final String	YHRS0111_2	= "2";	// 特殊岗位类别-护士
	
	public static final String  YHRS0069_2 = "2";  //考核类别  - 年度考核
	public static final String  YHRS0069_1 = "1";  //考核类别  - 年度考核
	


	public static final String	YHRS0062_6161	= "6161";	// 军队职务级别-正团职(副旅职)
	public static final String	YHRS0062_6163	= "6163";	// 军队职务级别-副团职
	public static final String	YHRS0062_6171	= "6171";	// 军队职务级别-正营职
	public static final String	YHRS0062_6173	= "6173";	// 军队职务级别-副营职
	public static final String	YHRS0062_6181	= "6181";	// 军队职务级别-正连职
	public static final String	YHRS0062_6183	= "6183";	// 军队职务级别-副连职
	public static final String	YHRS0062_6191	= "6191";	// 军队职务级别-正排职

	/**事业单位岗位类别 - 管理类*/
	public static final String YHRS0023_A1010 = "A1010";
	public static final String YHRS0023_A1010010 = "A1010010"; //管理类 - 一级
	public static final String YHRS0023_A1010020 = "A1010020"; //管理类 - 二级
	public static final String YHRS0023_A1010040 = "A1010040"; //管理类 - 三级
	public static final String YHRS0023_A1010050 = "A1010050"; //管理类 - 四级
	public static final String YHRS0023_A1010060 = "A1010060"; //管理类 - 五级
	public static final String YHRS0023_A1010070 = "A1010070"; //管理类 - 六级
	public static final String YHRS0023_A1010080 = "A1010080"; //管理类 - 七级
	public static final String YHRS0023_A1010090 = "A1010090"; //管理类 - 八级
	public static final String YHRS0023_A1010100 = "A1010100"; //管理类 - 九级
	public static final String YHRS0023_A1010110 = "A1010110"; //管理类 - 十级
	public static final String YHRS0023_A1010190 = "A1010190"; //管理类 - 未定级
	
	/**事业单位岗位类别 - 专业技术类*/
	public static final String YHRS0023_A1020 = "A1020";
	public static final String YHRS0023_A1020010 = "A1020010"; //专业技术类 - 高级
	public static final String YHRS0023_A1020020 = "A1020020"; //专业技术类 - 中级
	public static final String YHRS0023_A1020030 = "A1020030"; //专业技术类 - 初级
	public static final String YHRS0023_A1020090 = "A1020090"; //专业技术类 - 未定级
	public static final String YHRS0023_A1020010_010 = "A1020010010"; //专业技术类 - 高级 - 正高级
	public static final String YHRS0023_A1020010_020 = "A1020010020"; //专业技术类 - 高级 - 副高级
	public static final String YHRS0023_A1020010010_010 = "A1020010010010"; //专业技术类 - 高级 - 正高级 - 一级
	public static final String YHRS0023_A1020010010_020 = "A1020010010020"; //专业技术类 - 高级 - 正高级 - 二级
	public static final String YHRS0023_A1020010010_030 = "A1020010010030"; //专业技术类 - 高级 - 正高级 - 三级
	public static final String YHRS0023_A1020010010_040 = "A1020010010040"; //专业技术类 - 高级 - 正高级 - 四级
	public static final String YHRS0023_A1020010020_010 = "A1020010020010"; //专业技术类 - 高级 - 副高级 - 五级
	public static final String YHRS0023_A1020010020_020 = "A1020010020020"; //专业技术类 - 高级 - 副高级 - 六级
	public static final String YHRS0023_A1020010020_030 = "A1020010020030"; //专业技术类 - 高级 - 副高级 - 七级
	public static final String YHRS0023_A1020020_010 = "A1020020010"; //专业技术类 - 中级 - 八级
	public static final String YHRS0023_A1020020_020 = "A1020020020"; //专业技术类 - 中级 - 九级
	public static final String YHRS0023_A1020020_030 = "A1020020030"; //专业技术类 - 中级 - 十级
	public static final String YHRS0023_A1020030_010 = "A1020030010"; //专业技术类 - 初级 - 十一级
	public static final String YHRS0023_A1020030_020 = "A1020030020"; //专业技术类 - 初级 - 十二级
	public static final String YHRS0023_A1020030_030 = "A1020030030"; //专业技术类 - 初级 - 十三级
	
	/**事业单位岗位类别 - 工勤技能类*/
	public static final String YHRS0023_A1030 = "A1030";
	public static final String YHRS0023_A1030010 = "A1030010"; //工勤技能类 - 技工
	public static final String YHRS0023_A1030020 = "A1030020"; //工勤技能类 - 普工
	public static final String YHRS0023_A1030090 = "A1030090"; //工勤技能类 - 未定级
	public static final String YHRS0023_SKILLWORKER_LEVEL1 = "A1030010010"; //工勤技能类 - 技工 - 一级
	public static final String YHRS0023_SKILLWORKER_LEVEL2 = "A1030010020"; //工勤技能类 - 技工 - 二级
	public static final String YHRS0023_SKILLWORKER_LEVEL3 = "A1030010030"; //工勤技能类 - 技工 - 三级
	public static final String YHRS0023_SKILLWORKER_LEVEL4 = "A1030010040"; //工勤技能类 - 技工 - 四级
	public static final String YHRS0023_SKILLWORKER_LEVEL5 = "A1030010050"; //工勤技能类 - 技工 - 五级

	/*
	public static final String YHRS0115 = "YHRS0115";//定岗原因
	public static final String YHRS0115_1 = "1";//岗位变动
	public static final String YHRS0115_2 = "2";//同岗位续聘
	public static final String YHRS0115_3 = "3";//转正定级
	public static final String YHRS0115_4 = "4";//双肩挑申请
	*/	
	/**	合同类型 */
	public static final String YHRS0115 = "YHRS0115";
	/**	合同类型 -首聘 */
	public static final String YHRS0115_1 = "1";
	/**	合同类型 -续聘 */
	public static final String YHRS0115_3 = "3";
	
	/**	合同签订方式*/
	public static final String YHRS0114 = "YHRS0114";
	/**	合同签订方式 -固定期限 */
	public static final String YHRS0114_1 = "1";
	/**	合同签订方式 -无固定期限*/
	public static final String YHRS0114_2 = "2";
	
	/*
	public static final String YHRS0116 = "YHRS0116";//转正类型
	public static final String YHRS0116_1 = "1";//按期转正
	public static final String YHRS0116_2 = "2";//延期转正
	*/
	
	/**	合同状态 */
	public static final String YHRS0116 = "YHRS0116";
	/**	在聘 */
	public static final String YHRS0116_1 = "1";
	/**	解聘 */
	public static final String YHRS0116_2 = "2";
	
	public static final String YHRS0117 = "YHRS0117";//专业技术资格名称
	public static final String YHRS0119="YHRS0119";//培训方式
	
	/**
	 * 岗位类别
	 * */
	public static final String YHRS0120="YHRS0120";
	public static final String YHRS0120_1="1";//管理岗位
	public static final String YHRS0120_2="2";//专业技术岗位
	public static final String YHRS0120_3="3";//工勤技能岗位
	public static final String YHRS0120_4="4";//双肩挑岗位
	public static final String YHRS0121="YHRS0121";//试用期人员取消聘用类型
	
	public static final String YHRS0122 = "YHRS0122";//单位功能
	public static final String YHRS0122_1 = "1";//主要以专业技术提供社会公益服务
	public static final String YHRS0122_2 = "2";//主要承担社会事务管理职责
	public static final String YHRS0122_3 = "3";//主要承担技能操作和维护、服务保障等职责

	
	/**
	 * 考核工资历史计算结果
	 * */
	public static final String YHRS1004="YHRS1004";
	public static final String YHRS1004_1="1";//满5年晋级
	public static final String YHRS1004_2="2";//满2年晋档
	public static final String YHRS1004_3="3";//同时晋级晋档
	public static final String YHRS1004_4="4";//薪级晋升
	public static final String YHRS1004_5="5";//本年度未晋升
	public static final String YHRS1004_6="6";//计算有误
	public static final String YHRS1004_7="7";//手工修改

	

	public static final String	YHRS1002_11	= "11";	// 其他工资变动类型 - 高定2个级别工资档次
	public static final String	YHRS1002_12	= "12";	// 其他工资变动类型 - 高定1个级别工资档次
	public static final String	YHRS1002_13	= "13";	// 其他工资变动类型 - 低定2个级别工资档次
	public static final String	YHRS1002_14	= "14";	// 其他工资变动类型 - 低定1个级别工资档次
	public static final String	YHRS1002_21	= "21";	// 其他工资变动类型 - 高定2薪级
	public static final String	YHRS1002_22	= "22";	// 其他工资变动类型 - 高定1薪级
	public static final String	YHRS1002_23	= "23";	// 其他工资变动类型 - 低定2薪级
	public static final String	YHRS1002_24	= "24";	// 其他工资变动类型 - 低定1薪级
	public static final String	YHRS1002_25	= "25";	// 其他工资变动类型 - 浮动1薪级
	public static final String	YHRS1002_26	= "26";	// 其他工资变动类型 - 取消浮动1薪级
	public static final String	YHRS1002_27	= "27";	// 其他工资变动类型 - 固定浮动薪级
	public static final String	YHRS1002_28	= "28";	// 其他工资变动类型 - 特殊教育津贴
	public static final String	YHRS1002_29	= "29";	// 其他工资变动类型 - 取消特殊教育津贴

	/**
	 * 返回学历学位对应的学历等级代码
	 * 
	 * @param edu:学历代码
	 * @param degree:学位代码
	 * @return ：学历等级代码
	 */
	public static String mappingEduLevel(String edu, String degree)
	{
		if(edu!=null && edu.length()>0) {//如果学历不为空
			
			//学历为博士研究生,硕士研究生,研究生班,研究生
			// 研究生,学位为空或学士,双学士
			if ((YHRS0039_13.equals(edu) || YHRS0039_10.equals(edu) || YHRS0039_11.equals(edu) || YHRS0039_12.equals(edu) || YHRS0039_1.equals(edu)) && (degree == null || "".equals(degree) || YHRS0040_1.equals(degree) || YHRS0040_2.equals(degree)))
				return YHRS0041_3;
			// 研究生，硕士学位
			if ((YHRS0039_13.equals(edu) || YHRS0039_10.equals(edu) || YHRS0039_11.equals(edu) || YHRS0039_12.equals(edu) || YHRS0039_1.equals(edu)) && (degree != null && YHRS0040_3.equals(degree)))
				return YHRS0041_2;
			// 研究生，博士学位、博士后
			if ((YHRS0039_13.equals(edu) || YHRS0039_10.equals(edu) || YHRS0039_11.equals(edu) || YHRS0039_12.equals(edu) || YHRS0039_1.equals(edu)) && (degree != null && (YHRS0040_4.equals(degree) || YHRS0040_5.equals(degree))))
				return YHRS0041_1;
	
			// 本科，学位为空或学士学位
			if (YHRS0039_2.equals(edu) && (degree == null || "".equals(degree) || YHRS0040_1.equals(degree)))
				return YHRS0041_4;
			// 本科，双学士学位
			if (YHRS0039_2.equals(edu) && (degree != null && YHRS0040_2.equals(degree)))
				return YHRS0041_3;
			// 本科，硕士学位
			if (YHRS0039_2.equals(edu) && (degree != null && YHRS0040_3.equals(degree)))
				return YHRS0041_2;
			// 本科，博士、博士后学位
			if (YHRS0039_2.equals(edu) && (degree != null && (YHRS0040_4.equals(degree) || YHRS0040_5.equals(degree))))
				return YHRS0041_1;
	
			// 大专
			if (YHRS0039_3.equals(edu))
				return YHRS0041_5;
			
			// 高中中专
			if (YHRS0039_4.equals(edu) || YHRS0039_5.equals(edu) || YHRS0039_6.equals(edu) || YHRS0039_7.equals(edu))
				return YHRS0041_6;
			// 初中以下
			if (YHRS0039_8.equals(edu) || YHRS0039_9.equals(edu))
				return YHRS0041_7;
			return YHRS0041_7;
		}else {//如果学历为空
			if(YHRS0040_1.equals(degree)) return YHRS0041_4;
			if(YHRS0040_2.equals(degree)) return YHRS0041_3;
			if(YHRS0040_3.equals(degree)) return YHRS0041_2;
			if(YHRS0040_4.equals(degree)) return YHRS0041_1;
			if(YHRS0040_5.equals(degree)) return YHRS0041_1;
		}
		return YHRS0041_7;

		// 学历代码
		// 10 博士研究生
		// 11 硕士研究生
		// 12 研究生班
		// 1 研究生
		// 2 大学本科
		// 3 大专
		// 4 中等专科
		// 5 职业高中
		// 6 技工学校
		// 7 普通高中
		// 8 初中
		// 9 小学
		// 99 其他

		// 学位代码
		// 1 学士
		// 2 硕士
		// 3 博士
		// 4 博士后
		// 5 双学士

		// 学历等级代码
		// 1 博士研究生
		// 2 硕士研究生
		// 3 双学士,研究生班毕业及未获硕士学位的研究生,本科六年以上
		// 4 大学本科的毕业生
		// 5 大学专科的毕业生
		// 6 高中,中专及中技毕业生
		// 7 初中毕业生以下

	}
	
	/**
	 * 病区类型
	 * */
	public static final String YHRS0125="YHRS0125";
	/** 内科病区 */
	public static final String YHRS0125_1="1";
	/** 外科病区 */
	public static final String YHRS0125_2="2";
	/** 门诊科室 */
	public static final String YHRS0125_3="3";
	/** 医技科室 */
	public static final String YHRS0125_4="4";
	/** 行政后勤 */
	public static final String YHRS0125_5="5";
	
	/**
	 * 任职职务/职务名称 - 医院
	 */
	public static final String YHRS0126 = "YHRS0126";
	/** 科长 */
	public static final String YHRS0126_1 = "1";
	/** 副科长 */
	public static final String YHRS0126_2 = "2";
	/** 科主任 */
	public static final String YHRS0126_3 = "3";
	/** 科副主任 */
	public static final String YHRS0126_4 = "4";
	/** 负责人 */
	public static final String YHRS0126_5 = "5";
	/** 护士长 */
	public static final String YHRS0126_6 = "6";
	/** 副护士长 */
	public static final String YHRS0126_7 = "7";
	/** 技师长 */
	public static final String YHRS0126_8 = "8";
	/** 副技师长 */
	public static final String YHRS0126_9 = "9";
	
	/**
	 * 执业资格类型 - 医院
	 */
	public static final String YHRS0127 = "YHRS0127";
	/** 执业医师 */
	public static final String YHRS0127_1 = "1";
	/** 执业助理医师 */
	public static final String YHRS0127_2 = "2";
	/** 执业药师 */
	public static final String YHRS0127_3 = "3";
	/** 执业助理药师 */
	public static final String YHRS0127_4 = "4";
	/** 执业护士 */
	public static final String YHRS0127_5 = "5";
	
	/**
	 * 院内岗位类别
	 */
	public static final String YHRS0113 = "YHRS0113";
	public static final String YHRS0113_1 = "1";//医生
	public static final String YHRS0113_2 = "2";//技师
	public static final String YHRS0113_3 = "3";//药师
	public static final String YHRS0113_4 = "4";//护理
	public static final String YHRS0113_5 = "5";//管理类
	public static final String YHRS0113_6 = "6";//工勤
	public static final String YHRS0113_7 = "7";//研究
	
	/** 减员类型   */
	public static final String YHRS0128 = "YHRS0128";
	/** 辞职  */
	public static final String YHRS0128_1 = "1";
	/** 辞退  */
	public static final String YHRS0128_2 = "2";
	/** 调离  */
	public static final String YHRS0128_3 = "3";
	/** 开除  */
	public static final String YHRS0128_4 = "4";
	
	
	/** 外出类型  */
	public static final String YHRS0129 = "YHRS0129";
	public static final String YHRS0129_1 = "1";
	public static final String YHRS0129_2 = "2";
	public static final String YHRS0129_3 = "3";
	public static final String YHRS0129_4 = "4";
	public static final String YHRS0129_5 = "5";
	public static final String YHRS0129_6 = "6";

	/** 调休类型  */
	public static final String YHRS0131 = "YHRS0131";
	public static final String YHRS0131_1 = "1";
	public static final String YHRS0131_2 = "2";
	public static final String YHRS0131_3 = "3";

	/** 请休假类型  */
	public static final String YHRS0130 = "YHRS0130";
	public static final String YHRS0130_1 = "1";
	public static final String YHRS0130_2 = "2";
	public static final String YHRS0130_3 = "3";
	public static final String YHRS0130_4 = "4";
	public static final String YHRS0130_5 = "5";
	public static final String YHRS0130_6 = "6";
	public static final String YHRS0130_7 = "7";
	public static final String YHRS0130_8 = "8";
	public static final String YHRS0130_9 = "9";
	public static final String YHRS0130_10 = "10";
	public static final String YHRS0130_11 = "11";
	public static final String YHRS0130_12 = "12";
	public static final String YHRS0130_13 = "13";
	
	public static final String NAME_1 = "工休假";
	public static final String NAME_2 = "年休假";
	public static final String NAME_3 = "丧假";
	public static final String NAME_4 = "探亲假";
	public static final String NAME_5 = "婚假";
	public static final String NAME_6 = "产假";
	public static final String NAME_7 = "计划生育手术假";
	public static final String NAME_8 = "陪护假";
	public static final String NAME_9 = "哺乳假";
	public static final String NAME_10 = "病假";
	public static final String NAME_11 = "事假";
	public static final String NAME_12 = "工伤假";
	public static final String NAME_13 = "延长产假";
	public static final String NAME_14 = "普通外出";
	public static final String NAME_15 = "外出进修";
	public static final String NAME_16 = "调休";
	public static final String YHRS0132_14 = "14";//普通外出
	public static final String YHRS0132_15 = "15";//外出进修
	public static final String YHRS0131_16 = "16";//调休
	
	

	/** 加班类型 */
	public static final String YHRS0132 = "YHRS0132";
	/** 平日加班  */
	public static final String YHRS0132_1 = "1";
	/** 双休日加班  */
	public static final String YHRS0132_2 = "2";
	/** 节假日加班 */
	public static final String YHRS0132_3 = "3";

	/** 请销假状态 */
	public static final String YHRS0133 = "YHRS0133";
	/** 请假 */
	public static final String YHRS0133_1 = "1";
	/** 销假 */
	public static final String YHRS0133_2 = "2";
	
	/** 外出经费来源 */
	public static final String YHRS0134 = "YHRS0134";
	/** 住宿情况 */
	public static final String YHRS0135 = "YHRS0135";
	
	/** 当天假长类别 */
	public static final String YHRS0136 = "YHRS0136";
	/** 1天 */
	public static final String YHRS0136_1 = "1";
	/** 0.75天 */
	public static final String YHRS0136_075 = "0.75";
	/** 0.5天 */
	public static final String YHRS0136_05 = "0.5";
	/** 0.25天 */
	public static final String YHRS0136_025 = "0.25";
	
	/** 用户关系来源类型 */
	public static final String YHRS0137 	= "YHRS0137";
	/** 业务人员OID */
	public static final String YHRS0137_01 	= "01";
	/** 基础人员OID */
	public static final String YHRS0137_02 	= "02";
	/** 科室OID */
	public static final String YHRS0137_03 	= "03";
	/** 单位OID */
	public static final String YHRS0137_04 	= "04";
	
	/** 考勤项目 */
	public static final String YHRS0138 = "YHRS0138";
	
	/** 数据格式检查类型 */
	public static final String YHRS0139 = "YHRS0139";
	/** VARCHAR */
	public static final String YHRS0139_1 = "1";
	/** DATE */
	public static final String YHRS0139_2 = "2";
	
	/** 人员导入检查类型 */
	public static final String YHRS0140 = "YHRS0140";
	/** 必填项检查 */
	public static final String YHRS0140_1 = "1";
	/** 数据项长度检查 */
	public static final String YHRS0140_2 = "2";
	/** 数据格式检查 */
	public static final String YHRS0140_3 = "3";
	/** 字典项检查 */
	public static final String YHRS0140_4 = "4";
	/** 关联性检查 */
	public static final String YHRS0140_5 = "5";
	/** 完整性检查 */
	public static final String YHRS0140_6 = "6";
	/** 科室检查 */
	public static final String YHRS0140_7 = "7";

	/** 字典值展示方式 */
	public static final String YHRS0141 = "YHRS0141";
	/** 下拉框 */
	public static final String YHRS0141_1 = "1";
	/** 模态框 */
	public static final String YHRS0141_2 = "2";

	
	/** 人员导入流程状态 */
	public static final String YHRS0142 = "YHRS0142";
	/** 开始解析文件 */
	public static final String YHRS0142_1 = "1";
	/** 导入前检查中 */
	public static final String YHRS0142_2 = "2";
	/** 导入前检查通过 */
	public static final String YHRS0142_3 = "3";
	/** 导入前检查未通过  */
	public static final String YHRS0142_4 = "4";
	/** 导入中*/
	public static final String YHRS0142_5 = "5";
	/** 导入完成 */
	public static final String YHRS0142_6 = "6";
	/** 数据校核中 */
	public static final String YHRS0142_7 = "7";
	/** 字典映射中 */
	public static final String YHRS0142_8 = "8";
	/** 字典映射完成 */
	public static final String YHRS0142_9 = "9";
	/** 数据校核通过 */
	public static final String YHRS0142_10 = "10";
	/** 数据校核未通过 */
	public static final String YHRS0142_11 = "11";
	/** 入库中 */
	public static final String YHRS0142_12 = "12";
	/** 入库完成 */
	public static final String YHRS0142_13 = "13";
	
	/** 批次隶属类型 */
	public static final String YHRS0143 = "YHRS0143";
	/** 单位*/
	public static final String YHRS0143_1 = "1";
	/** 科室 */
	public static final String YHRS0143_2 = "2";
	/** 地区*/
	public static final String YHRS0143_3 = "3";
	
}
