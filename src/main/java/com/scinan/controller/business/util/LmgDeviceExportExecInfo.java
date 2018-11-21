package com.scinan.controller.business.util;

import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.scinan.iot.s6000.dao.domain.DeviceInfoBean;
import com.scinan.utils.DateUtil;
import com.scinan.utils.StringUtil;


/**
 * 功能说明：糯米购设备信息导出模板
 */
public class LmgDeviceExportExecInfo {

		public final static String exportExportInfo(String fileName, String[] Title, List<DeviceInfoBean> paramsList, HttpServletResponse response) {
			String result = "系统提示：Excel文件导出成功！";
			// 以下开始输出到EXCEL
			try {
				// 定义输出流，以便打开保存对话框______________________begin
				OutputStream os = response.getOutputStream();// 取得输出流
				response.reset();// 清空输出流
				response.setHeader("Content-disposition","attachment; filename=" + new String(fileName.getBytes("GB2312"),"ISO8859-1"));
				// 设定输出文件头
				response.setContentType("application/msexcel");// 定义输出类型
				// 定义输出流，以便打开保存对话框_______________________end

				/** **********创建工作簿************ */
				WritableWorkbook workbook = Workbook.createWorkbook(os);
				/** **********创建工作表************ */

				WritableSheet sheet = workbook.createSheet("Sheet1", 0);
				/** **********设置纵横打印（默认为纵打）、打印纸***************** */
				jxl.SheetSettings sheetset = sheet.getSettings();
				sheetset.setProtected(false);

				/** ************设置单元格字体************** */
				WritableFont NormalFont = new WritableFont(WritableFont.ARIAL,10);
				WritableFont BoldFont = new WritableFont(WritableFont.ARIAL,10, WritableFont.BOLD);

				/** ************以下设置三种单元格样式，灵活备用************ */
				// 用于标题居中
				WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
				wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
				wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
				wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
				wcf_center.setWrap(false); // 文字是否换行

				// 用于正文居左
				WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
				wcf_left.setBorder(Border.NONE, BorderLineStyle.THIN); // 线条
				wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
				wcf_left.setAlignment(Alignment.CENTRE); // 文字水平对齐
				wcf_left.setWrap(true); // 文字是否换行

				/** ***************以下是EXCEL开头大标题，暂时省略********************* */
				// sheet.mergeCells(0, 0, colWidth, 0);
				// sheet.addCell(new Label(0, 0, "XX报表", wcf_center));
				/** ***************以下是EXCEL第一行列标题********************* */
				for (int i = 0; i < Title.length; i++) {
					sheet.addCell(new Label(i, 0, Title[i], wcf_center));
				}
				/** ***************以下是EXCEL正文数据********************* */
				int i = 1;
				if(null!=paramsList && paramsList.size()>0){
					for (DeviceInfoBean obj : paramsList) {

                        String id = StringUtil.isValidateStr(String.valueOf(obj.getId()));
                        String dealer_name = StringUtil.isValidateStr(String.valueOf(obj.getDealer_name()));
                        String out_water = StringUtil.isValidateStr(String.valueOf(obj.getOut_water()));
                        String lease_day = StringUtil.isValidateStr(String.valueOf(obj.getLease_day()));
                        String open_c_status = StringUtil.isValidateStr(String.valueOf(obj.getOpen_c_status()));
                        
                        String join_time = StringUtil.isValidateStr(DateUtil.getDate2String(obj.getJoin_time(),DateUtil.DATE_PATTERN_STANDARD));
                        String lx_residual_life = StringUtil.isValidateStr(String.valueOf(obj.getLx_residual_life()));
                        String online = StringUtil.isValidateStr(String.valueOf(obj.getOnline()));
                        String province = StringUtil.isValidateStr(String.valueOf(obj.getProvince()));
                        String city = StringUtil.isValidateStr(String.valueOf(obj.getCity()));

						sheet.addCell(new Label(0, i, id, wcf_left));
                        sheet.addCell(new Label(1, i, dealer_name, wcf_left));
                        sheet.addCell(new Label(2, i, out_water+"m³", wcf_left));
                        sheet.addCell(new Label(3, i, lease_day, wcf_left));
                        if("1".equals(open_c_status)){
                        	sheet.addCell(new Label(4, i, "开机", wcf_left));
                        }else{
                        	sheet.addCell(new Label(4, i, "关机", wcf_left));
                        }
                        
                        sheet.addCell(new Label(5, i, join_time, wcf_left));
                        sheet.addCell(new Label(6, i, lx_residual_life, wcf_left));
                        if("1".equals(online)){
                        	sheet.addCell(new Label(7, i, "在线", wcf_left));
                        }else{
                        	sheet.addCell(new Label(7, i, "离线", wcf_left));
                        }
                        
                        sheet.addCell(new Label(8, i, province, wcf_left));
                        sheet.addCell(new Label(9, i, city, wcf_left));

						sheet.setColumnView(0, 20);
						sheet.setColumnView(1, 30);
						sheet.setColumnView(2, 10);
						sheet.setColumnView(3, 10);
						sheet.setColumnView(4, 10);
						sheet.setColumnView(5, 20);
						sheet.setColumnView(6, 20);
						sheet.setColumnView(7, 10);
						sheet.setColumnView(8, 10);
						sheet.setColumnView(9, 10);
						i++;
					}
				}
				/** **********将以上缓存中的内容写到EXCEL文件中******** */
				workbook.write();
				/** *********关闭文件************* */
				workbook.close();

			} catch (Exception e) {
				result = "系统提示：Excel文件导出失败，原因：" + e.toString();
				System.out.println(result);
				e.printStackTrace();
			}
			return result;
		}

		
}