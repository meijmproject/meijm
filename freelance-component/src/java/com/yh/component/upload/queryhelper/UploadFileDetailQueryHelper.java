/**
 * 
 */
package com.yh.component.upload.queryhelper;

import java.util.List;

import com.yh.component.taglib.TableTagBean;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import com.yh.component.upload.bo.UploadFileDetail;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DataConverUtils;

/**
 * 
 * @author	zhangqp
 * @version	1.0,	16/11/10
 */

public class UploadFileDetailQueryHelper {


	/**
	 * 查询文件列表，不分页
	 * @param ttb（refRoleCode 必填）
	 * @return
	 * @throws ServiceException
	 */
	public static List<JSONObject> listUploadFile(TableTagBean ttb) throws ServiceException {
		String refRoleCode = ttb.getCondition().get("refRoleCode");
		
		if (StringUtils.isEmpty(refRoleCode)) {
			return null;
		}
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select jufd.file_oid,");
		sql.append("       jufd.upload_file_oid,");
		sql.append("       jufd.file_name,");
		sql.append("       jufd.file_type,");
		sql.append("       juf.ref_code,");
		sql.append("       juf.ref_oid");
		sql.append("       ,jufd.created_by_code");
		sql.append("       ,jufd.created_by_name");
		sql.append("       ,date_format(jufd.created_date,'%Y-%m-%d %H:%i:%s')");
		
		//wm_concat ：行变列，11g返回clob类型，数据库需WMSYS用户及相关函数（暂时找不到更简单的替代方案）
		sql.append("       ,(select group_concat(cast(jura.authority as char)) from yha_upload_ref_auth jura where jura.ref_code = juf.ref_code and jura.ref_role_code = '").append(refRoleCode).append("')");
		sql.append("       ,jus.file_length");
		sql.append("       ,jus.post_complete");
		
		sql.append("  from yha_upload_file_detail jufd left join yha_upload_file juf on jufd.upload_file_oid = juf.upload_file_oid left join yha_upload_status jus on jufd.file_name_target = jus.uuid");
		sql.append(" where 1=1");
		
//		Map<String,Object> params = new HashMap<String,Object>();
		
		if (StringUtils.isNotEmpty(ttb.getCondition().get("viewRefCodes"))) {
			sql.append(" and juf.ref_code in(").append(ttb.getCondition().get("viewRefCodes")).append(")");
		}
		
		if (StringUtils.isNotEmpty(ttb.getCondition().get("refOid"))) {
			sql.append(" and juf.ref_oid = ").append(ttb.getCondition().get("refOid"));
		}
		
		sql.append(" and exists(select 1 from yha_upload_ref_auth jura where jura.ref_code = juf.ref_code and jura.ref_role_code = '").append(refRoleCode).append("') ");
		
		sql.append(" order by juf.created_date desc ");
		
		List<Object[]> list = DaoUtil.listWithSQLByCondition(sql.toString(), null, 0, 0);
		
		return BeanHelper.copyProperties(list, new BeanHelper.PropertiesHandler<Object[], JSONObject>() {

			public JSONObject getValue(Object[] src) throws ServiceException {
				JSONObject json = new JSONObject();
				
				json.put("fileOid", DataConverUtils.toNumber(src[0]));
				json.put("uploadFileOid", DataConverUtils.toNumber(src[1]));
				json.put("fileName", DataConverUtils.toString(src[2]));
				json.put("fileType", DataConverUtils.toString(src[3]));
				json.put("refCode", DataConverUtils.toString(src[4]));
				json.put("refOid", DataConverUtils.toNumber(src[5]));
				
				json.put("createdByCode", DataConverUtils.toString(src[6]));
				json.put("createdByName", DataConverUtils.toString(src[7]));
				json.put("createdDate", DataConverUtils.toString(src[8]));
				
				json.put("authority", DataConverUtils.toString(src[9]));
				json.put("fileLength", DataConverUtils.toNumber(src[10]));
				json.put("postComplete", DataConverUtils.toNumber(src[11]));
				
				return json;
			}});
	}
	/**
	 * 根据uploadFileOid查询UploadFileDetail信息
	 * @param uploadFileOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<UploadFileDetail> listUploadFileDetail(Long uploadFileOid) throws ServiceException {
		String sql=" from UploadFileDetail where uploadFileOid="+uploadFileOid;
		return DaoUtil.find(sql);
	}
}
