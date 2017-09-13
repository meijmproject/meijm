package com.yh.hr.report.web.action;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.hr.report.utils.Constants;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.hr.component.yngw.util.GwYnInfoConstants;
import com.yh.hr.report.facade.impl.PersonFloorDiagramFacadeImpl;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.action.BaseAction;
/**
 * 全院人员框架图 action
 * @author chenp
 * 2017-03-01
 * 
 */
public class PersonFloorDiagramAction extends BaseAction{
	private PersonFloorDiagramFacadeImpl	personFloorDiagramFacade	= (PersonFloorDiagramFacadeImpl) SpringBeanUtil.getBean("personFloorDiagramFacade");

	/**
	 * 跳转到全院人员框架图查看页面
	 * @param mapping
	 * @param actionform
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goviewFloorPersonDiagramInfo(ActionMapping mapping, ActionForm actionform, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		int qyTotalCount = 0;//全院人员总数
		int wsjs =0;//卫生技术人员数
		
		int nwsjs =0;//非卫生技术人员数
		
		int wsjshl =0;//护理人员数
		
		int wsjshlyjks = 0;//医技科室人员数-护理人员
		
		int wsjshllcks = 0;//临床科室人员数-护理人员
		
		int wsjshllcksmjz = 0;//门急诊-临床科室人员数-护理人员
		int wsjshllckszybf = 0;//住院病房-临床科室人员数-护理人员
		
		int wsjshllckszybfnk = 0;//内科病区-住院病房-临床科室人员数-护理人员
		int wsjshllckszybfwk = 0;//外科病区-住院病房-临床科室人员数-护理人员

		int yjy = 0;//医技药人员数
		
		int yjyyjy = 0;//其他包括研究人员-医技药人员数
		
		int yjyys = 0;//药师-医技药人员数
		
		int yjyysyjs = 0;//药剂科-药师-医技药人员数
		
		int yjyjs = 0;//技师-医技药人员数
		
		int yjyjsyk = 0;//医技科室-技师-医技药人员数
		int yjyjslc = 0;//临床科室-技师-医技药人员数
		int yjyjslczy = 0;//住院病房-临床科室-技师--医技药人员数
		int yjyjslcmz = 0;//门急诊-临床科室-技师--医技药人员数
		
		int yjyyis = 0;//医师 -医技药人员数
		
		int yjyyisyj = 0;//医技科室-医师 -医技药人员数
		int yjyyislc = 0;//临床科室-医师 -医技药人员数
		int yjyyislczy = 0;//住院病房-临床科-医师 -医技药人员数
		
		int yjyyislczynk = 0;//内科病区-住院病房-临床科-医师 -医技药人员数
		int yjyyislczywk = 0;//外科病区-住院病房-临床科-医师 -医技药人员数
		
		int yjyyislcmjz = 0;//门急诊-临床科-医师 -医技药人员数
		
		Float wsjsProportion=0f;//卫生技术人员数所占比例
		Float nwsjsProportion=0f;//非卫生技术人员数所占比例
		Float wsjshlProportion=0f;//护理人员数所占比例
		Float yjyProportion=0f;//医技药人员数所占比例
		Float wsjshlyjksProportion=0f;//医技科室人员数-护理人员所占比例
		Float wsjshllcksProportion=0f;//临床科室人员数-护理人员所占比例
		Float yjyyjyProportion=0f;//其他包括研究人员-医技药人员数所占比例
		Float yjyysProportion=0f;//药师-医技药人员数所占比例
		Float yjyjsProportion=0f;//技师-医技药人员数所占比例
		Float yjyyisProportion=0f;//医师 -医技药人员数所占比例
		 try
	        {
			
		//医技科室人员数-护理人员
		wsjshlyjks = personFloorDiagramFacade.getOfficePersonNumByCond(DicConstants.YHRS0125_4,GwYnInfoConstants.YHRS0113_4,null);
		//门急诊-临床科室人员数-护理人员
		wsjshllcksmjz = personFloorDiagramFacade.getOfficePersonNumByCond(DicConstants.YHRS0125_3,GwYnInfoConstants.YHRS0113_4,null);
		//内科病区-住院病房-临床科室人员数-护理人员
		wsjshllckszybfnk=personFloorDiagramFacade.getOfficePersonNumByCond(DicConstants.YHRS0125_1,GwYnInfoConstants.YHRS0113_4,null);
		//外科病区-住院病房-临床科室人员数-护理人员
		wsjshllckszybfwk=personFloorDiagramFacade.getOfficePersonNumByCond(DicConstants.YHRS0125_2,GwYnInfoConstants.YHRS0113_4,null);
		//住院病房-临床科室人员数-护理人员
		wsjshllckszybf=wsjshllckszybfnk+wsjshllckszybfwk;
		//临床科室人员数-护理人员
		wsjshllcks = wsjshllckszybf+wsjshllcksmjz;
		//护理人员数
		wsjshl =wsjshllcks+wsjshlyjks;
		
		//其他包括研究人员-医技药人员数
		yjyyjy=personFloorDiagramFacade.getOfficePersonNumByCond(null,GwYnInfoConstants.YHRS0113_7, Constants.YJRY);
		
		//药剂科-药师-医技药人员数
		yjyysyjs = personFloorDiagramFacade.getOfficePersonNumByCond(null,GwYnInfoConstants.YHRS0113_3,Constants.HSPSZHP_ORG_TYPE_66);
		//药师-医技药人员数
		yjyys = yjyysyjs;
		//医技科室-技师-医技药人员数
		yjyjsyk = personFloorDiagramFacade.getOfficePersonNumByCond(DicConstants.YHRS0125_4,GwYnInfoConstants.YHRS0113_2,null);
		//住院病房-临床科室-技师--医技药人员数(住院病房人数=内科病区+外科病区)
		yjyjslczy = personFloorDiagramFacade.getOfficePersonNumByCond(DicConstants.YHRS0125_1,GwYnInfoConstants.YHRS0113_2,null)
		+personFloorDiagramFacade.getOfficePersonNumByCond(DicConstants.YHRS0125_2,GwYnInfoConstants.YHRS0113_2,null);
		//门急诊-临床科室-技师--医技药人员数
		yjyjslcmz = personFloorDiagramFacade.getOfficePersonNumByCond(DicConstants.YHRS0125_3,GwYnInfoConstants.YHRS0113_2,null);
		//临床科室-技师-医技药人员数
		yjyjslc = yjyjslczy+yjyjslcmz;
		//技师-医技药人员数
		yjyjs = yjyjslc+yjyjsyk;
		
		//医技科室-医师 -医技药人员数
		yjyyisyj = personFloorDiagramFacade.getOfficePersonNumByCond(DicConstants.YHRS0125_4,GwYnInfoConstants.YHRS0113_1,null);
		//内科病区-住院病房-临床科-医师 -医技药人员数
		yjyyislczynk = personFloorDiagramFacade.getOfficePersonNumByCond(DicConstants.YHRS0125_1,GwYnInfoConstants.YHRS0113_1,null);
		//外科病区-住院病房-临床科-医师 -医技药人员数
		yjyyislczywk = personFloorDiagramFacade.getOfficePersonNumByCond(DicConstants.YHRS0125_2,GwYnInfoConstants.YHRS0113_1,null);
		//门急诊-临床科-医师 -医技药人员数
		yjyyislcmjz = personFloorDiagramFacade.getOfficePersonNumByCond(DicConstants.YHRS0125_3,GwYnInfoConstants.YHRS0113_1,null);
		//住院病房-临床科-医师 -医技药人员数
		yjyyislczy = yjyyislczynk+yjyyislczywk;
		//临床科室-医师 -医技药人员数
		yjyyislc = yjyyislczy+yjyyislcmjz;	
		//医师 -医技药人员数
		yjyyis = yjyyisyj+yjyyislc;
		//医技药人员数
		yjy = yjyyis+yjyjs+yjyys+yjyyjy;
		//非卫生技术人员数
		nwsjs =personFloorDiagramFacade.getOfficePersonNumByCond(null,GwYnInfoConstants.YHRS0113_5,Constants.HSPSZHP_ORG_TYPE_7)
		+personFloorDiagramFacade.getOfficePersonNumByCond(null,GwYnInfoConstants.YHRS0113_6,Constants.HSPSZHP_ORG_TYPE_7);
		//卫生技术人员数
		wsjs =wsjshl+yjy;
		qyTotalCount = wsjs+nwsjs;//全院人员总数
		
		wsjsProportion=calcPercent((float)wsjs,qyTotalCount);//卫生技术人员数所占比例
		nwsjsProportion=calcPercent((float)nwsjs,qyTotalCount);//非卫生技术人员数所占比例
		wsjshlProportion=calcPercent((float)wsjshl,wsjs);//护理人员数所占比例
		yjyProportion=calcPercent((float)yjy,wsjs);//医技药人员数所占比例
		wsjshlyjksProportion=calcPercent((float)wsjshlyjks,wsjshl);//医技科室人员数-护理人员所占比例
		wsjshllcksProportion=calcPercent((float)wsjshllcks,wsjshl);//临床科室人员数-护理人员所占比例
		yjyyjyProportion=calcPercent((float)yjyyjy,yjy);//其他包括研究人员-医技药人员数所占比例
		yjyysProportion=calcPercent((float)yjyys,yjy);//药师-医技药人员数所占比例
		yjyjsProportion=calcPercent((float)yjyjs,yjy);//技师-医技药人员数所占比例
		yjyyisProportion=calcPercent((float)yjyyis,yjy);//医师 -医技药人员数所占比例
		
		request.setAttribute("qyTotalCount", qyTotalCount);
		request.setAttribute("wsjs", wsjs);
		request.setAttribute("nwsjs", nwsjs);
		request.setAttribute("wsjshl", wsjshl);
		request.setAttribute("wsjshlyjks", wsjshlyjks);
		request.setAttribute("wsjshllcks", wsjshllcks);
		request.setAttribute("wsjshllcksmjz", wsjshllcksmjz);
		request.setAttribute("wsjshllckszybf", wsjshllckszybf);
		request.setAttribute("wsjshllckszybfnk", wsjshllckszybfnk);
		request.setAttribute("wsjshllckszybfwk", wsjshllckszybfwk);
		request.setAttribute("yjy", yjy);
		request.setAttribute("yjyyjy", yjyyjy);
		request.setAttribute("yjyys", yjyys);
		request.setAttribute("yjyysyjs", yjyysyjs);
		request.setAttribute("yjyjs", yjyjs);
		request.setAttribute("yjyjsyk", yjyjsyk);
		request.setAttribute("yjyjslc", yjyjslc);
		request.setAttribute("yjyjslczy", yjyjslczy);
		request.setAttribute("yjyjslcmz", yjyjslcmz);
		request.setAttribute("yjyyis", yjyyis);
		request.setAttribute("yjyyisyj", yjyyisyj);
		request.setAttribute("yjyyislc", yjyyislc);
		request.setAttribute("yjyyislczy", yjyyislczy);
		request.setAttribute("yjyyislczynk", yjyyislczynk);
		request.setAttribute("yjyyislczywk", yjyyislczywk);
		request.setAttribute("yjyyislcmjz", yjyyislcmjz);
		
		request.setAttribute("wsjsProportion", wsjsProportion);
		request.setAttribute("nwsjsProportion", nwsjsProportion);
		request.setAttribute("wsjshlProportion", wsjshlProportion);
		request.setAttribute("yjyProportion", yjyProportion);
		request.setAttribute("wsjshlyjksProportion", wsjshlyjksProportion);
		request.setAttribute("wsjshllcksProportion", wsjshllcksProportion);
		request.setAttribute("yjyyjyProportion", yjyyjyProportion);
		request.setAttribute("yjyysProportion", yjyysProportion);
		request.setAttribute("yjyjsProportion", yjyjsProportion);
		request.setAttribute("yjyyisProportion", yjyyisProportion);
        }
	  catch (Exception e) {
			handleException(request, e, null);
			return mapping.findForward("error");
		}
	 return mapping.findForward("success");
	}
	/**
	 * 计算百分比
	 * @param proportion
	 * @param total
	 * @return
	 */
	private static Float calcPercent(Float proportion,
			int total) {
		if(proportion!=null&&total!=0){
			BigDecimal bigDecimal=new BigDecimal(proportion/total*100);
			proportion=bigDecimal.setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
			return proportion;
		}
		proportion = (float) 0;
		return proportion;
	}
}
