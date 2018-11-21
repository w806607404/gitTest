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

import com.scinan.iot.yunwa.dao.domain.BusinessOrder;
import com.scinan.utils.DateUtil;
import com.scinan.utils.StringUtil;


/**
 * 功能说明：设备信息导出模板
 */
public class ZhengShangDeviceExportExecInfo {
		/***************************************************************************
		 * @param fileName
		 *            EXCEL文件名称
		 * @param listTitle
		 *            EXCEL文件第一行列标题集合
		 * @param cList
		 *            EXCEL文件正文数据集合
		 * @return
		 */
		public final static String exportExcel(String fileName, String[] Title,List<BusinessOrder> paramsList,HttpServletResponse response) {
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
					for (BusinessOrder obj : paramsList) {
						
						String deviceId = StringUtil.isValidateStr(String.valueOf(obj.getDevice_id()));
						String dealerName = StringUtil.isValidateStr(String.valueOf(obj.getDealer_name()));
						String orderAmounts = StringUtil.isValidateStr(String.valueOf(obj.getOrder_amounts_str()));
						String orderPresents = StringUtil.isValidateStr(String.valueOf(obj.getOrder_present()));
						
						sheet.addCell(new Label(0, i, deviceId, wcf_left));
						sheet.addCell(new Label(1, i, dealerName, wcf_left));
						sheet.addCell(new Label(2, i, orderAmounts, wcf_left));
						
						sheet.addCell(new Label(3, i, DateUtil.minConvertDayHourMin(Double.parseDouble(orderPresents)*60)+"", wcf_left));
						
						sheet.setColumnView(0, 30);
						sheet.setColumnView(1, 30);
						sheet.setColumnView(2, 30);
						sheet.setColumnView(3, 30);
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
