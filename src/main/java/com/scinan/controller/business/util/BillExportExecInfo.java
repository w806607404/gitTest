package com.scinan.controller.business.util;

import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.scinan.iot.ddeddo.dao.domain.BillCloseDetail;
import com.scinan.utils.DateUtil;
import com.scinan.utils.StringUtil;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;


/**
 * 功能说明：正商账单统计使用记录导出模板
 */
public class BillExportExecInfo {
		/***************************************************************************
		 * @param fileName
		 *            EXCEL文件名称
		 * @param listTitle
		 *            EXCEL文件第一行列标题集合
		 * @param cList
		 *            EXCEL文件正文数据集合
		 * @return
		 */
		public final static String exportExcel(String fileName, String[] Title,
				List<BillCloseDetail> list,HttpServletResponse response) {
			String result = "系统提示：Excel文件导出成功！";
			// 以下开始输出到EXCEL
			try {
				// 定义输出流，以便打开保存对话框______________________begin
				OutputStream os = response.getOutputStream();// 取得输出流
				response.reset();// 清空输出流
				response.setHeader("Content-disposition","attachment; filename="
								+ new String(fileName.getBytes("GB2312"),
										"ISO8859-1"));
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
				
				WritableFont font2 = new WritableFont(WritableFont.ARIAL,14,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.RED);  
		        WritableCellFormat cellFormat2 = new WritableCellFormat(font2);  
		        cellFormat2.setAlignment(Alignment.CENTRE);  
		        cellFormat2.setBackground(Colour.YELLOW);  
		        cellFormat2.setBorder(Border.ALL, BorderLineStyle.THIN);  
		        cellFormat2.setWrap(true); 
		        
		        WritableFont font3 = new WritableFont(WritableFont.ARIAL,14,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.GREEN);  
		        WritableCellFormat cellFormat3 = new WritableCellFormat(font3);  
		        cellFormat3.setAlignment(Alignment.CENTRE);  
		        cellFormat3.setBorder(Border.ALL, BorderLineStyle.THIN);  
		        cellFormat3.setWrap(true); 
				

				/** ***************以下是EXCEL开头大标题，暂时省略********************* */
				// sheet.mergeCells(0, 0, colWidth, 0);
				// sheet.addCell(new Label(0, 0, "XX报表", wcf_center));
				/** ***************以下是EXCEL第一行列标题********************* */
				for (int i = 0; i < Title.length; i++) {
					sheet.addCell(new Label(i, 0, Title[i], wcf_center));
				}
				/** ***************以下是EXCEL正文数据********************* */
				int i = 1;
//				if(null!=list && list.size()>0){
//					for (BillCloseDetail obj : list) {
//						String deviceId = String.valueOf(obj.getDevice_id());
//						String orderId = String.valueOf(obj.getOrder_id());
//						String order_amount = String.valueOf(obj.getOrder_amount().toString());
//						String belong_amount_str = String.valueOf(obj.getBelong_amount_str());
//						String pay_time = DateUtil.getDate2String(obj.getPay_time(),DateUtil.DATE_PATTERN_STANDARD);
//						
//						String user_nickname = String.valueOf(obj.getUser_nickname());
//						String roleName = String.valueOf(obj.getRole_name());
//						
//						sheet.addCell(new Label(0, i, orderId, wcf_left));
//						sheet.addCell(new Label(1, i, deviceId, wcf_left));
//						sheet.addCell(new Label(2, i, StringUtil.isValidateStr(user_nickname), wcf_left));
//						sheet.addCell(new Label(3, i, StringUtil.isValidateStr(roleName), wcf_left));
//						sheet.addCell(new Label(4, i, order_amount, wcf_left));
//						sheet.addCell(new Label(5, i, belong_amount_str, wcf_left));
//						sheet.addCell(new Label(6, i, pay_time, wcf_left));
//						
//						sheet.setColumnView(0, 30);
//						sheet.setColumnView(1, 30);
//						sheet.setColumnView(2, 30);
//						sheet.setColumnView(3, 30);
//						sheet.setColumnView(4, 30);
//						sheet.setColumnView(5, 30);
//						sheet.setColumnView(6, 30);
//						i++;
//					}
//				}
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
