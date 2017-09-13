/**
 * 
 */
package com.yh.hr.info.view.web.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.hr.info.ver.unit.workbench.facade.VerPbPersonWorkbenchFacade;
import com.yh.hr.info.view.facade.ViewPersonFacade;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.yh.component.taglib.TableTagBean;
import com.yh.hr.info.ver.unit.workbench.dto.VerPersonDTO;
import com.yh.platform.core.util.JSONHelper;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.action.BaseAction;

/**
 * 
 * @author zhangqp
 * @version 1.0, 16/08/14
 */

public class ViewPersonAction extends BaseAction {
	private VerPbPersonWorkbenchFacade verPbPersonWorkbenchFacade = (VerPbPersonWorkbenchFacade) SpringBeanUtil
	.getBean("verPbPersonWorkbenchFacade");
	private ViewPersonFacade viewPersonFacade =(ViewPersonFacade) SpringBeanUtil.getBean("viewPersonFacade");

	// 查找出所有需要校核的人员名单
	public ActionForward findAllPerson(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		try {
			TableTagBean ttb = new TableTagBean(request);
			ttb.setPage((Integer.parseInt((ttb.getCondition().get("pageNo").toString()))-1)*10);
			ttb.setPageSize(10);
			ttb.getCondition().put("name",ttb.getCondition().get("name"));
			ttb.getCondition().put("idNo",ttb.getCondition().get("idNo"));
			ttb.getCondition().put("personStatus",ttb.getCondition().get("personStatus"));
			ttb.getCondition().put("unitOid",ttb.getCondition().get("unitOid"));
			List<VerPersonDTO> list = verPbPersonWorkbenchFacade.listVerPerson(ttb);
			JSONObject obj = new JSONObject();
			JSONArray array = new JSONArray();
			obj.put("total", ttb.getTotal());
			if(CollectionUtils.isNotEmpty(list)){
				for (VerPersonDTO dto : list) {
					array.element(JSONHelper.fromObject(dto));
				}
			}
			obj.put("rows", array);
			response.getWriter().print(obj.toString());
		} catch (Exception se) {
			handleException(request, se, null);
			return mapping.findForward("fail");
		}
		return null;
	}

	// 根据人员类别查询人所对应的信息集
	public ActionForward findInByPersonType(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		try {
			String personType = request.getParameter("personType");
			if(!"".equals(personType)){
				StringBuffer ss = new StringBuffer("[");
				viewPersonFacade.findAllPersonList();
				String Stringjson = "{'key':[{'id':'1','name':'基础信息','typecode':'01','typeName':'公务员'}]}";
				JSONArray json = JSONObject.fromObject(Stringjson).getJSONArray(
						"key");
				for (int i = 0; i < json.size(); i++) {
					JSONObject jsonO = (JSONObject) json.get(i);
					if (jsonO.get("typecode").equals(personType)) {
						String stringObject = jsonO.toString();
						ss.append(stringObject);
						ss.append(",");
					}
				}
				String sss = ss.substring(0, ss.length() - 1);
				StringBuffer ssss = new StringBuffer(sss);
				ssss.append("]");
				response.getWriter().write(ssss.toString());
			}else{
				response.getWriter().write(JSONHelper.fromObject(false, "该人员无人员类别,暂无信息集").toString());
			}
		} catch (Exception se) {
			handleException(request, se, null);
			return mapping.findForward("fail");
		}
		return null;
	}

	public ActionForward goVerPersonBasic(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			return mapping.findForward("success");
		} catch (Exception e) {
			handleException(request, e, null);
			return mapping.findForward("error");
		}
	}

	public ActionForward goVerPersonFor(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			return mapping.findForward("success");
		} catch (Exception e) {
			handleException(request, e, null);
			return mapping.findForward("error");
		}
	}

	public ActionForward goVerPersonSchool(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			return mapping.findForward("success");
		} catch (Exception e) {
			handleException(request, e, null);
			return mapping.findForward("error");
		}
	}

	public ActionForward goVerPersonView(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			String Id = request.getParameter("id");
			StringBuffer ss = new StringBuffer("[");
			String Stringjson = "{'key':[{'id':'1','url':'goToViewJgPbPersonPageShow.do?method=goToViewJgPbPersonPage'}"
					+ ",{'id':'2','url':'findListViewPbOfficialPage.do?method=goToViewVerPbOfficialPage'}"
					+ ",{'id':'3','url':'listPbFamilyInfoShow.do?method=find'}"
					+ ",{'id':'4','url':'listPbEducationInfoShow.do?method=find'}"
					+ ",{'id':'5','url':'goListPbProbationShowPage.do?method=listVerJgPbProbationInfo'}"
					+ ",{'id':'6','url':'listPbResumeInfoShow.do?method=listVerPbResumeInfo'}"
					+ ",{'id':'7','url':'listPbRankInfoShow.do?method=listVerPbRankInfo'}"
					+ ",{'id':'8','url':'listPbIndusInfoShow.do?method=listVerPbIndusInfo'}"
					+ ",{'id':'9','url':'viewPbReviewInfoList.do?method=viewPbReviewInfoList'}"
					+ ",{'id':'10','url':'PbRetrieInfo.do?method=listPbRetrieInfo'}"
					+ ",{'id':'11','url':'viewPbProfTechInfoList.do?method=view'}"
					+ ",{'id':'12','url':'viewPbProfessionalInfoList.do?method=view'}"
					+ ",{'id':'13','url':'viewPbVacationInfoList.do?method=view'}"
					+ ",{'id':'14','url':'findListJgPbPositioningPage.do?method=goToViewJgPbPositioningPage'}"
					+ ",{'id':'15','url':'listPbPoliticInfoShow.do?method=find'}"
					+ ",{'id':'16','url':'viewPbRewardInfoList.do?method=viewPbRewardInfoList'}"
					+ ",{'id':'17','url':'viewPbPunishmentInfoList.do?method=viewPbPunishmentInfoList'}"
					+ ",{'id':'18','url':'findPbAllocationInfo.do?method=listPbAllocationInfo'}"
					+ ",{'id':'19','url':'findPbWageInfluence.do?method=PbWageInfluence'}"
					+ ",{'id':'20','url':'findPbMilitaryInfo.do?method=findPbMilitaryInfo'}"
					+ ",{'id':'21','url':'findPbExserviceInfo.do?method=findPbExserviceInfo'}"
					+ ",{'id':'22','url':'listPbLanguageInfoShow.do?method=listVerPbLanguageInfo'}"
					+ ",{'id':'23','url':'goViewList.do?method=goViewList'}"
					
					+ ",{'id':'24','url':'goToViewJgPbPersonPageShow.do?method=goToViewJgPbPersonPage'}"
					+ ",{'id':'25','url':'findListViewPbOfficialPage.do?method=goToViewVerPbOfficialPage'}"
					+ ",{'id':'26','url':'listPbFamilyInfoShow.do?method=find'}"
					+ ",{'id':'27','url':'listPbEducationInfoShow.do?method=find'}"
					+ ",{'id':'28','url':'goListPbProbationShowPage.do?method=listVerJgPbProbationInfo'}"
					+ ",{'id':'29','url':'listPbResumeInfoShow.do?method=listVerPbResumeInfo'}"
					+ ",{'id':'30','url':'listPbRankInfoShow.do?method=listVerPbRankInfo'}"
					+ ",{'id':'31','url':'listPbIndusInfoShow.do?method=listVerPbIndusInfo'}"
					+ ",{'id':'32','url':'viewPbReviewInfoList.do?method=viewPbReviewInfoList'}"
					+ ",{'id':'33','url':'PbRetrieInfo.do?method=listPbRetrieInfo'}"
					+ ",{'id':'34','url':'viewPbProfTechInfoList.do?method=view'}"
					+ ",{'id':'35','url':'viewPbProfessionalInfoList.do?method=view'}"
					+ ",{'id':'36','url':'viewPbVacationInfoList.do?method=view'}"
					+ ",{'id':'37','url':'findListJgPbPositioningPage.do?method=goToViewJgPbPositioningPage'}"
					+ ",{'id':'38','url':'listPbPoliticInfoShow.do?method=find'}"
					+ ",{'id':'39','url':'viewPbRewardInfoList.do?method=viewPbRewardInfoList'}"
					+ ",{'id':'40','url':'viewPbPunishmentInfoList.do?method=viewPbPunishmentInfoList'}"
					+ ",{'id':'41','url':'findPbAllocationInfo.do?method=listPbAllocationInfo'}"
					+ ",{'id':'42','url':'findPbWageInfluence.do?method=PbWageInfluence'}"
					+ ",{'id':'43','url':'findPbMilitaryInfo.do?method=findPbMilitaryInfo'}"
					+ ",{'id':'44','url':'findPbExserviceInfo.do?method=findPbExserviceInfo'}"
					+ ",{'id':'45','url':'listPbLanguageInfoShow.do?method=listVerPbLanguageInfo'}"
					+ ",{'id':'46','url':'goViewList.do?method=goViewList'}"
					
					+ ",{'id':'47','url':'showPbPerson.do?method=showPbPerson&flag=1'}"
					+ ",{'id':'48','url':'listPbEducationInfoShow.do?method=find'}"
					+ ",{'id':'49','url':'listPbFamilyInfoShow.do?method=find'}"
					/*+ ",{'id':'50','url':'findListViewPbOfficialPage.do?method=goToViewVerPbOfficialPage'}"*/
					+ ",{'id':'51','url':'goListPbProbationShowPage.do?method=listVerJgPbProbationInfo'}"
					+ ",{'id':'52','url':'listPbResumeInfoShow.do?method=listVerPbResumeInfo'}"
					+ ",{'id':'53','url':'listPbRankInfoShow.do?method=listVerPbRankInfo'}"
					+ ",{'id':'54','url':'listPbIndusInfoShow.do?method=listVerPbIndusInfo'}"
					+ ",{'id':'55','url':'viewPbReviewInfoList.do?method=viewPbReviewInfoList'}"
					+ ",{'id':'56','url':'PbRetrieInfo.do?method=listPbRetrieInfo'}"
					+ ",{'id':'57','url':'viewPbProfTechInfoList.do?method=view'}"
					+ ",{'id':'58','url':'viewPbProfessionalInfoList.do?method=view'}"
					+ ",{'id':'59','url':'viewPbVacationInfoList.do?method=view'}"
					+ ",{'id':'60','url':'listViewVerSyPostList.do?method=listPbPost'}"
					+ ",{'id':'61','url':'listPbPoliticInfoShow.do?method=find'}"
					+ ",{'id':'62','url':'viewPbRewardInfoList.do?method=viewPbRewardInfoList'}"
					+ ",{'id':'63','url':'viewPbPunishmentInfoList.do?method=viewPbPunishmentInfoList'}"
					+ ",{'id':'64','url':'findPbAllocationInfo.do?method=listPbAllocationInfo'}"
					+ ",{'id':'65','url':'findPbWageInfluence.do?method=PbWageInfluence'}"
					+ ",{'id':'66','url':'findPbMilitaryInfo.do?method=findPbMilitaryInfo'}"
					+ ",{'id':'67','url':'findPbExserviceInfo.do?method=findPbExserviceInfo'}"
					+ ",{'id':'68','url':'listPbLanguageInfoShow.do?method=listVerPbLanguageInfo'}"
					+ ",{'id':'69','url':'goViewList.do?method=goViewList'}"
					
					+ ",{'id':'70','url':'showPbPerson.do?method=showPbPerson&flag=1'}"
					+ ",{'id':'71','url':'listPbEducationInfoShow.do?method=find'}"
					+ ",{'id':'72','url':'listPbFamilyInfoShow.do?method=find'}"
					+ ",{'id':'73','url':'goListPbProbationShowPage.do?method=listVerJgPbProbationInfo'}"
					+ ",{'id':'74','url':'listPbResumeInfoShow.do?method=listVerPbResumeInfo'}"
					+ ",{'id':'75','url':'listPbRankInfoShow.do?method=listVerPbRankInfo'}"
					+ ",{'id':'76','url':'listPbIndusInfoShow.do?method=listVerPbIndusInfo'}"
					+ ",{'id':'77','url':'viewPbReviewInfoList.do?method=viewPbReviewInfoList'}"
					+ ",{'id':'78','url':'PbRetrieInfo.do?method=listPbRetrieInfo'}"
					+ ",{'id':'79','url':'viewPbProfTechInfoList.do?method=view'}"
					+ ",{'id':'80','url':'viewPbProfessionalInfoList.do?method=view'}"
					+ ",{'id':'81','url':'viewPbVacationInfoList.do?method=view'}"
					+ ",{'id':'82','url':'listViewVerSyPostList.do?method=listPbPost'}"
					+ ",{'id':'83','url':'listPbPoliticInfoShow.do?method=find'}"
					+ ",{'id':'84','url':'viewPbRewardInfoList.do?method=viewPbRewardInfoList'}"
					+ ",{'id':'85','url':'viewPbPunishmentInfoList.do?method=viewPbPunishmentInfoList'}"
					+ ",{'id':'86','url':'findPbAllocationInfo.do?method=listPbAllocationInfo'}"
					+ ",{'id':'87','url':'findPbWageInfluence.do?method=PbWageInfluence'}"
					+ ",{'id':'88','url':'findPbMilitaryInfo.do?method=findPbMilitaryInfo'}"
					+ ",{'id':'89','url':'findPbExserviceInfo.do?method=findPbExserviceInfo'}"
					+ ",{'id':'90','url':'listPbLanguageInfoShow.do?method=listVerPbLanguageInfo'}"
					+ ",{'id':'91','url':'goViewList.do?method=goViewList'}"
					+ "]}";

			JSONArray json = JSONObject.fromObject(Stringjson).getJSONArray(
					"key");
			for (int i = 0; i < json.size(); i++) {
				JSONObject jsonO = (JSONObject) json.get(i);
				String id = jsonO.getString("id");
				String stringObject = jsonO.toString();
				if (Id.equals(id)) {
					ss.append(stringObject);
					break;
				}
			}
			ss.append("]");
			response.getWriter().write(ss.toString());
			return null;
		} catch (Exception e) {
			handleException(request, e, null);
			return mapping.findForward("error");
		}
	}
	
	public ActionForward goVerPersonUpdateFindPage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			String Id = request.getParameter("id");
			StringBuffer ss = new StringBuffer("[");
			String Stringjson = "{'key':[{'id':'1','url':'goToViewJgPbPersonPage.do?method=goToViewJgPbPersonPage'}"
					+ ",{'id':'2','url':'goListVerPbOfficialPage.do?method=listVerJgPbOfficialInfo'}"
					+ ",{'id':'3','url':'listPbFamilyInfo.do?method=find'}"
					+ ",{'id':'4','url':'listPbEducationInfo.do?method=find'}"
					+ ",{'id':'5','url':'goListPbProbationPage.do?method=listVerJgPbProbationInfo'}"
					+ ",{'id':'6','url':'listPbResumeInfo.do?method=listVerPbResumeInfo'}"
					+ ",{'id':'7','url':'listPbRankInfo.do?method=listVerPbRankInfo'}"
					+ ",{'id':'8','url':'listPbIndusInfo.do?method=listVerPbIndusInfo'}"
					+ ",{'id':'9','url':'pbReviewInfoList.do?method=list'}"
					+ ",{'id':'10','url':'listPbRetrieInfo.do?method=listPbRetrieInfo'}"
					+ ",{'id':'11','url':'listVerProfTechInfo.do?method=list'}"
					+ ",{'id':'12','url':'listVerProfessionalInfo.do?method=list'}"
					+ ",{'id':'13','url':'listVerVacationInfo.do?method=list'}"
					+ ",{'id':'14','url':'goListJgPbPositioningPage.do?method=listJgPbPositioningInfo'}"
					+ ",{'id':'15','url':'listPbPoliticInfo.do?method=find'}"
					+ ",{'id':'16','url':'listPbRewardInfo.do?method=listPbRewardInfo'}"
					+ ",{'id':'17','url':'listPbPunishmentInfo.do?method=listPbPunishmentInfo'}"
					+ ",{'id':'18','url':'listPbAllocationInfo.do?method=listPbAllocationInfo'}"
					+ ",{'id':'19','url':'PbWageInfluence.do?method=PbWageInfluence'}"
					+ ",{'id':'20','url':'listPbMilitaryInfo.do?method=listPbMilitaryInfo'}"
					+ ",{'id':'21','url':'getPbExserviceInfo.do?method=getPbExserviceInfo'}"
					+ ",{'id':'22','url':'listPbLanguageInfo.do?method=listVerPbLanguageInfo'}"
					+ ",{'id':'23','url':'listPbTrainingInfo.do?method=listVerPbTrainingInfo'}"
					
					+ ",{'id':'24','url':'goToViewJgPbPersonPage.do?method=goToViewJgPbPersonPage'}"
					+ ",{'id':'25','url':'goListVerPbOfficialPage.do?method=listVerJgPbOfficialInfo'}"
					+ ",{'id':'26','url':'listPbFamilyInfo.do?method=find'}"
					+ ",{'id':'27','url':'listPbEducationInfo.do?method=find'}"
					+ ",{'id':'28','url':'goListPbProbationPage.do?method=listVerJgPbProbationInfo'}"
					+ ",{'id':'29','url':'listPbResumeInfo.do?method=listVerPbResumeInfo'}"
					+ ",{'id':'30','url':'listPbRankInfo.do?method=listVerPbRankInfo'}"
					+ ",{'id':'31','url':'listPbIndusInfo.do?method=listVerPbIndusInfo'}"
					+ ",{'id':'32','url':'pbReviewInfoList.do?method=list'}"
					+ ",{'id':'33','url':'listPbRetrieInfo.do?method=listPbRetrieInfo'}"
					+ ",{'id':'34','url':'listVerProfTechInfo.do?method=list'}"
					+ ",{'id':'35','url':'listVerProfessionalInfo.do?method=list'}"
					+ ",{'id':'36','url':'listVerVacationInfo.do?method=list'}"
					+ ",{'id':'37','url':'goListJgPbPositioningPage.do?method=listJgPbPositioningInfo'}"
					+ ",{'id':'38','url':'listPbPoliticInfo.do?method=find'}"
					+ ",{'id':'39','url':'listPbRewardInfo.do?method=listPbRewardInfo'}"
					+ ",{'id':'40','url':'listPbPunishmentInfo.do?method=listPbPunishmentInfo'}"
					+ ",{'id':'41','url':'listPbAllocationInfo.do?method=listPbAllocationInfo'}"
					+ ",{'id':'42','url':'PbWageInfluence.do?method=PbWageInfluence'}"
					+ ",{'id':'43','url':'listPbMilitaryInfo.do?method=listPbMilitaryInfo'}"
					+ ",{'id':'44','url':'getPbExserviceInfo.do?method=getPbExserviceInfo'}"
					+ ",{'id':'45','url':'listPbLanguageInfo.do?method=listVerPbLanguageInfo'}"
					+ ",{'id':'46','url':'listPbTrainingInfo.do?method=listVerPbTrainingInfo'}"
					
					+ ",{'id':'47','url':'showPbPerson.do?method=showPbPerson'}"
					+ ",{'id':'48','url':'listPbEducationInfo.do?method=find'}"
					+ ",{'id':'49','url':'listPbFamilyInfo.do?method=find'}"
					+ ",{'id':'51','url':'goListPbProbationPage.do?method=listVerJgPbProbationInfo'}"
					+ ",{'id':'52','url':'listPbResumeInfo.do?method=listVerPbResumeInfo'}"
					+ ",{'id':'53','url':'listPbRankInfo.do?method=listVerPbRankInfo'}"
					+ ",{'id':'54','url':'listPbIndusInfo.do?method=listVerPbIndusInfo'}"
					+ ",{'id':'55','url':'pbReviewInfoList.do?method=list'}"
					+ ",{'id':'56','url':'listPbRetrieInfo.do?method=listPbRetrieInfo'}"
					+ ",{'id':'57','url':'listVerProfTechInfo.do?method=list'}"
					+ ",{'id':'58','url':'listVerProfessionalInfo.do?method=list'}"
					+ ",{'id':'59','url':'listVerVacationInfo.do?method=list'}"
					+ ",{'id':'60','url':'goVerSyPostList.do?method=listPbPost'}"
					+ ",{'id':'61','url':'listPbPoliticInfo.do?method=find'}"
					+ ",{'id':'62','url':'listPbRewardInfo.do?method=listPbRewardInfo'}"
					+ ",{'id':'63','url':'listPbPunishmentInfo.do?method=listPbPunishmentInfo'}"
					+ ",{'id':'64','url':'listPbAllocationInfo.do?method=listPbAllocationInfo'}"
					+ ",{'id':'65','url':'PbWageInfluence.do?method=PbWageInfluence'}"
					+ ",{'id':'66','url':'listPbMilitaryInfo.do?method=listPbMilitaryInfo'}"
					+ ",{'id':'67','url':'getPbExserviceInfo.do?method=getPbExserviceInfo'}"
					+ ",{'id':'68','url':'listPbLanguageInfo.do?method=listVerPbLanguageInfo'}"
					+ ",{'id':'69','url':'listPbTrainingInfo.do?method=listVerPbTrainingInfo'}"
					
					+ ",{'id':'70','url':'showPbPerson.do?method=showPbPerson'}"
					+ ",{'id':'71','url':'listPbEducationInfo.do?method=find'}"
					+ ",{'id':'72','url':'listPbFamilyInfo.do?method=find'}"
					+ ",{'id':'73','url':'goListPbProbationPage.do?method=listVerJgPbProbationInfo'}"
					+ ",{'id':'74','url':'listPbResumeInfo.do?method=listVerPbResumeInfo'}"
					+ ",{'id':'75','url':'listPbRankInfo.do?method=listVerPbRankInfo'}"
					+ ",{'id':'76','url':'listPbIndusInfo.do?method=listVerPbIndusInfo'}"
					+ ",{'id':'77','url':'pbReviewInfoList.do?method=list'}"
					+ ",{'id':'78','url':'pbRetrieInfoGoCreate.do?method=goContract'}"
					+ ",{'id':'79','url':'listVerProfTechInfo.do?method=list'}"
					+ ",{'id':'80','url':'listVerProfessionalInfo.do?method=list'}"
					+ ",{'id':'81','url':'listVerVacationInfo.do?method=list'}"
					+ ",{'id':'82','url':'goVerSyPostList.do?method=listPbPost'}"
					+ ",{'id':'83','url':'listPbPoliticInfo.do?method=find'}"
					+ ",{'id':'84','url':'listPbRewardInfo.do?method=listPbRewardInfo'}"
					+ ",{'id':'85','url':'listPbPunishmentInfo.do?method=listPbPunishmentInfo'}"
					+ ",{'id':'86','url':'listPbAllocationInfo.do?method=listPbAllocationInfo'}"
					+ ",{'id':'87','url':'PbWageInfluence.do?method=PbWageInfluence'}"
					+ ",{'id':'88','url':'listPbMilitaryInfo.do?method=listPbMilitaryInfo'}"
					+ ",{'id':'89','url':'getPbExserviceInfo.do?method=getPbExserviceInfo'}"
					+ ",{'id':'90','url':'listPbLanguageInfo.do?method=listVerPbLanguageInfo'}"
					+ ",{'id':'91','url':'listPbTrainingInfo.do?method=listVerPbTrainingInfo'}"
					
					+ "]}";

			JSONArray json = JSONObject.fromObject(Stringjson).getJSONArray(
					"key");
			for (int i = 0; i < json.size(); i++) {
				JSONObject jsonO = (JSONObject) json.get(i);
				String id = jsonO.getString("id");
				String stringObject = jsonO.toString();
				if (Id.equals(id)) {
					ss.append(stringObject);
					break;
				}
			}
			ss.append("]");
			response.getWriter().write(ss.toString());
			return null;
		} catch (Exception e) {
			handleException(request, e, null);
			return mapping.findForward("error");
		}
	}
}
