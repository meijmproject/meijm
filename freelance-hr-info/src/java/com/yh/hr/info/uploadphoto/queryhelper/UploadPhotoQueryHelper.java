/**
 * 
 */
package com.yh.hr.info.uploadphoto.queryhelper;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yh.component.taglib.TableTagBean;
import com.yh.component.upload.bo.UploadStatus;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DataConverUtils;

import net.sf.json.JSONObject;

/**
 * 删除文件 queryHelper
 * @author	chenjl
 * @version	1.0,	17/03/25
 */

public class UploadPhotoQueryHelper {


	/**
	 * 查询文件列表，不分页
	 * @param ttb（personOid 必填）
	 * @return
	 * @throws ServiceException
	 */
	public static List<JSONObject> listUploadPhoto(TableTagBean ttb) throws ServiceException {
		String personOid = ttb.getCondition().get("personOid");
		String refType = ttb.getCondition().get("refType");
		if (StringUtils.isEmpty(personOid)) {
			return null;
		}
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select jpp.photo_oid,");
		sql.append("       jpp.person_oid,");
		sql.append("       jpp.photo_name,");
		sql.append("       jpp.photo_type,");
		sql.append("       jpp.create_by");
		sql.append("       ,jpp.create_name");
		sql.append("       ,date_format(jpp.create_date,'%Y-%m-%d %H:%i:%s')");
		
		//wm_concat ：行变列，11g返回clob类型，数据库需WMSYS用户及相关函数（暂时找不到更简单的替代方案）
//		sql.append("       ,(select to_char(wm_concat(jura.authority)) from yha_upload_ref_auth jura where jura.ref_code = juf.ref_code and jura.ref_role_code = '").append(personOid).append("')");
		sql.append("       ,jus.file_length");
		sql.append("       ,jus.post_complete");
		sql.append("       ,jpp.picture_type");
		sql.append("       ,jpp.ref_type");
		
		sql.append("  from yhc_pb_photo jpp left join yha_upload_status jus");
		sql.append(" on jpp.photo_code = jus.file_name_remote");
		sql.append(" where jpp.person_oid ="+personOid);
		if(!"0".equals(refType)){
			sql.append(" and jpp.ref_type ='"+refType+"'");
		}
		
//		Map<String,Object> params = new HashMap<String,Object>();
		
		
//		sql.append(" and exists(select 1 from yha_upload_ref_auth jura where jura.ref_code = juf.ref_code and jura.ref_role_code = '").append(personOid).append("') ");
		
		sql.append(" order by jpp.create_date desc ");
		
		List<Object[]> list = DaoUtil.listWithSQLByCondition(sql.toString(), null, 0, 0);
		
		return BeanHelper.copyProperties(list, new BeanHelper.PropertiesHandler<Object[], JSONObject>() {

			public JSONObject getValue(Object[] src) throws ServiceException {
				JSONObject json = new JSONObject();
				
				json.put("photoOid", DataConverUtils.toNumber(src[0]));
				json.put("personOid", DataConverUtils.toNumber(src[1]));
				json.put("photoName", DataConverUtils.toString(src[2])+"."+DataConverUtils.toString(src[3]));
				json.put("photoType", DataConverUtils.toString(src[3]));
				json.put("createBy", DataConverUtils.toString(src[4]));
				json.put("createName", DataConverUtils.toString(src[5]));
				json.put("createdDate", DataConverUtils.toString(src[6]));
				json.put("authority", "9");
				json.put("fileLength", DataConverUtils.toNumber(src[7]));
				json.put("postComplete", DataConverUtils.toNumber(src[8]));
				json.put("pictureType", DataConverUtils.toNumber(src[9]));
				json.put("refType", DataConverUtils.toNumber(src[10]));
				
				return json;
			}});
	}

	public static UploadStatus getUploadStatus(String photoCode)  throws ServiceException{
		return DaoUtil.uniqueResult(" from UploadStatus us where us.fileNameRemote ='"+photoCode+"'");
	}

}
