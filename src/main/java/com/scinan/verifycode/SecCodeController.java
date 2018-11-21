package com.scinan.verifycode;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.geom.QuadCurve2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.scinan.utils.CaptchaUtils;


public class SecCodeController extends HttpServlet {
	protected static final Log logger = LogFactory.getLog(SecCodeController.class);
	public static Map<String,Integer> ipCountMap = new HashMap<String, Integer>();
	 /**
	 * 
	 */
	private static final long serialVersionUID = 2407909436519538233L;
	
    private static final String CAPTCHA_ATTR = CaptchaUtils.class.getName().concat(".CAPTCHA");

    // 默认的验证码大小
    private static final int WIDTH = 108, HEIGHT = 40;
    // 验证码随机字符数组
    protected static final char[] charArray = "3456789ABCDEFGHJKMNPQRSTUVWXY".toCharArray();
    // 验证码字体
    private static final Font[] RANDOM_FONT = new Font[] {
            new Font("nyala", Font.BOLD, 38),
            new Font("Arial", Font.BOLD, 32),
            new Font("Bell MT", Font.BOLD, 32),
            new Font("Credit valley", Font.BOLD, 34),
            new Font("Impact", Font.BOLD, 32),
            new Font(Font.MONOSPACED, Font.BOLD, 40)
    };

	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException{
		
		//ByteArrayOutputStream output = new ByteArrayOutputStream();
		//String code = drawImg(output);
		
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        String vCode = drawGraphic(image);
        vCode = vCode.toUpperCase();// 转成大写重要
		
		System.out.println(vCode);
		logger.info("code is : " + vCode);
		HttpSession session= request.getSession();
        session.setAttribute("dc_sessionSecCode",vCode);
        //session.setMaxInactiveInterval(30*60);
        response.setHeader("Pragma","no-cache");
        response.setHeader("Cache-Control","no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        ServletOutputStream sos = null;
        try {
            sos = response.getOutputStream();
            ImageIO.write(image, "JPEG", sos);
            sos.flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            closeQuietly(sos);
        }
		
		return;
	 }
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException{
	 doGet(request,response);
	 }
	
	/**
     * 生成验证码
     */
    public static void generate(HttpServletRequest request, HttpServletResponse response) {
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        String vCode = drawGraphic(image);
        vCode = vCode.toUpperCase();// 转成大写重要

        HttpSession session = request.getSession();
//        session.setAttribute(CAPTCHA_ATTR, vCode);
        session.setAttribute("dc_sessionSecCode", vCode);

        response.setHeader("Pragma","no-cache");
        response.setHeader("Cache-Control","no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        ServletOutputStream sos = null;
        try {
            sos = response.getOutputStream();
            ImageIO.write(image, "JPEG", sos);
            sos.flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            closeQuietly(sos);
        }
    }
	
	
	private String drawImg(ByteArrayOutputStream output){
		String code = "";
		for(int i=0; i<4; i++){
			code += randomChar();
		}
		int width = 70;
		int height = 25;
		BufferedImage bi = new BufferedImage(width,height,BufferedImage.TYPE_3BYTE_BGR);
		Font font = new Font("Times New Roman",Font.PLAIN,20);
		Graphics2D g = bi.createGraphics();
		g.setFont(font);
		Color color = new Color(66,2,82);
		g.setColor(color);
		g.setBackground(new Color(226,226,240));
		g.clearRect(0, 0, width, height);
		FontRenderContext context = g.getFontRenderContext();
		Rectangle2D bounds = font.getStringBounds(code, context);
		double x = (width - bounds.getWidth()) / 2;
		double y = (height - bounds.getHeight()) / 2;
		double ascent = bounds.getY();
		double baseY = y - ascent;
		g.drawString(code, (int)x, (int)baseY);
		g.dispose();
		try {
			ImageIO.write(bi, "jpg", output);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return code;
	}
	
	private char randomChar(){
		Random r = new Random();
		String s = "ABCDEFGHJKLMNPRSTUVWXYZ0123456789";
		return s.charAt(r.nextInt(s.length()));
	}
	
	

    private static String drawGraphic(BufferedImage image){
        // 获取图形上下文
        Graphics2D g = image.createGraphics();

        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        // 图形抗锯齿
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // 字体抗锯齿
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // 设定背景色，淡色
        g.setColor(getRandColor(210, 250));
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // 生成随机类
        Random random = new Random();
        // 画小字符背景
        Color color = null;
        for(int i = 0; i < 20; i++){
            color = getRandColor(120, 200);
            g.setColor(color);
            String rand = String.valueOf(charArray[random.nextInt(charArray.length)]);
            g.drawString(rand, random.nextInt(WIDTH), random.nextInt(HEIGHT));
            color = null;
        }
        // 取随机产生的认证码(4位数字)
        String sRand = "";
        for (int i = 0; i < 4; i++){
            String rand = String.valueOf(charArray[random.nextInt(charArray.length)]);
            sRand += rand;
            //旋转度数 最好小于45度
            int degree = random.nextInt(28);
            if (i % 2 == 0) {
                degree = degree * (-1);
            }
            //定义坐标
            int x = 22 * i, y = 21;
            //旋转区域
            g.rotate(Math.toRadians(degree), x, y);
            //设定字体颜色
            color = getRandColor(20, 130);
            g.setColor(color);
            //设定字体，每次随机
            g.setFont(RANDOM_FONT[random.nextInt(RANDOM_FONT.length)]);
            //将认证码显示到图象中
            g.drawString(rand, x + 8, y + 10);
            //旋转之后，必须旋转回来
            g.rotate(-Math.toRadians(degree), x, y);
        }
        //图片中间曲线，使用上面缓存的color
        g.setColor(color);
        //width是线宽,float型
        BasicStroke bs = new BasicStroke(3);
        g.setStroke(bs);
        //画出曲线
        QuadCurve2D.Double curve = new QuadCurve2D.Double(0d, random.nextInt(HEIGHT - 8) + 4, WIDTH / 2, HEIGHT / 2, WIDTH, random.nextInt(HEIGHT - 8) + 4);
        g.draw(curve);
        // 销毁图像
        g.dispose();
        return sRand;
    }

    /**
     * 给定范围获得随机颜色
     */
    private static Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    /**
     * 仅能验证一次，验证后立即删除session
     * @param request 控制器
     * @param userInputCaptcha 用户输入的验证码
     * @return 验证通过返回 true, 否则返回 false
     */
    public static boolean validate(HttpServletRequest request, String userInputCaptcha) {
        HttpSession session = request.getSession(false);
        if (null == session) {
            return false;
        }
        // 转成大写重要
        userInputCaptcha = userInputCaptcha.toUpperCase();
        String code = (String) session.getAttribute(CAPTCHA_ATTR);
        boolean result = userInputCaptcha.equals(code);
        if (result) {
            session.removeAttribute(CAPTCHA_ATTR);
        }
        return result;
    }

    public static void closeQuietly(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException ioe) {
            // ignore
        }
    }
    
    
}