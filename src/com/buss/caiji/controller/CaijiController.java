package com.buss.caiji.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.apache.log4j.Logger;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.buss.caiji.entity.CaijiCategoryEntity;
import com.buss.caiji.entity.ProductEntity;
import com.buss.caiji.method.GroupMethod;
import com.buss.caiji.service.CaijiCategoryServiceI;
import com.buss.caiji.service.ProductServiceI;

/**   
 * @Title: Controller
 * @Description: 类目表
 * @author onlineGenerator
 * @date 2015-08-06 19:04:51
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/caijiController")
public class CaijiController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(CaijiCategoryController.class);
	@Autowired
	private CaijiCategoryServiceI caijiCategoryService;
	@Autowired
	private ProductServiceI productService;
	@Autowired
	private SystemService systemService;
	
	static String img = null;
	
	static String p_id = null;

	static String p_name = null;

	static String p_url = null;

	static String p_img = null;
	
	static String p_price = null;
	
	static String p_dsr = null;//评价
	
	static String p_faceback = null;
	
	static String p_orders = null;
	
	static String s_id = null;
	
	static String s_name = null;
	
	static String s_url = null;
	
	static String s_faceback = null;
	
	static String s_facebackper = null;
	
	static String discount = null;
	
	
	static Statement stmt = null;
	
	static Connection conn = null;
	
	static int count = 0;
	
	static int c_orders = 0;
	
	//是否需要修改或者增加一个字段，从1开始一直到最后一个产品ID.现有的按页排名仍然不变。通过这个新增ID来比较大小
	static int all_index = 1;
	/**
	 * 开始采集
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * 
	 */

	@RequestMapping(params = "run")
	public void run() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		logger.info("开始采集");
		List<CaijiCategoryEntity> categoryEntities = caijiCategoryService.loadAll(CaijiCategoryEntity.class);
		for(CaijiCategoryEntity caijiCategoryEntity:categoryEntities){
			for(int i=1;i<=caijiCategoryEntity.getCatchPage();i++){
				if(i!=1){
					doCatch(caijiCategoryEntity.getUrl().replace(".html", "/"+i+".html"),caijiCategoryEntity.getCId(),caijiCategoryEntity.getCName(),i);
				}else{
					doCatch(caijiCategoryEntity.getUrl(),caijiCategoryEntity.getCId(),caijiCategoryEntity.getCName(),i);
				}
			}
		}
		logger.info("采集结束");
	}
	
	
	private void doCatch(String urlstr, Integer category_id,
			String category_name,int page) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		try {
			System.out.println(urlstr);
	        // 创建一个url对象来指向 该网站链接 括号里()装载的是该网站链接的路径
	        URL url = new URL(urlstr);
	        // InputStreamReader 是一个输入流读取器 用于将读取的字节转换成字符
	        InputStreamReader isr = new InputStreamReader(url.openStream(),
	                "gb2312"); // 统一使用utf-8 编码模式
	        // 使用 BufferedReader 来读取 InputStreamReader 转换成的字符
	        BufferedReader br = new BufferedReader(isr);
	        String strRead = ""; // 新增一个空字符串strRead来装载 BufferedReader 读取到的内容


	     // 定义3个正则 用于匹配我们需要的数据
	        String all = "(\\d{1,2}\\.\\d{1,2}\\.\\d{4})";
	        String href = "<a\\s*.*)(href\\s*=\\s*[\"']*([^>\\s'\"]*)[\"']*)";
	        String title = "<a[^>]*>([^<]*)</a>";
	        String imgRegular= "src=\"([^\"]*)\"";
	        String priceRegular= "<span[^>]*>([^<]*)</span>";
	        String dsrRegular = "title=\"([^\"]*)\"";
	        String orderRegular= "<em[^>]*>([^<]*)</em>";
	        // 创建一个GroupMethod类的对象 gMethod 方便后期调用其类里的 regularGroup方法
	        GroupMethod gMethod = new GroupMethod();
	        boolean flag = false;
	        // 开始读取数据 如果读到的数据不为空 则往里面读
	        String sql = "";
	        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	        boolean start = false;
	        int index = 1;
	        
	        while ((strRead = br.readLine()) != null) {
	        	//从ul开始解析html
	        	if(strRead.contains("<ul")){
	        		System.out.println("开始");
	        		start = true;
	        		index = 1;
	        	}
	        	if(start){
	        		//判断是否含有class = product  取url和id,name
	        		if(strRead.contains("<a  class=\"product")||strRead.contains("itemprop=\"name\"")){
	        			String pattern= "href=\"([^\"]*)\"";
	        			Pattern pKey = Pattern.compile(pattern, 2 | Pattern.DOTALL); 
	        			Matcher mKey = pKey.matcher(strRead); 
	        			if(mKey.find()){ 
	        				//截取 url 中   ‘http://www.aliexpress.com/item/’ 这部分
	        				p_url = mKey.group(1);
	        				if(p_url!=null&&p_url.length()>0){
	            				if(p_url.split("/").length>5){
	            					p_id = p_url.split("/")[5].replace(".html", "");
	            				}
	            				
	            			}
	            			p_name = gMethod.regularGroup(title, strRead);
	            			p_name = p_name.replace("'", "");
	            			p_url = p_url.replace("http://www.aliexpress.com/item/", "");
	        			}
	        		}
	        		//判断是否含有class = picCore  取图片地址
	        		if(strRead.contains("picCore pic-Core-v")||strRead.contains("picCore")){
	        			img = gMethod.regularGroup(imgRegular, strRead);
	        		}
	        		//判断是否含有class = value    取价格
	        		if(strRead.contains("<span class=\"value")){
	        			p_price = gMethod.regularGroup(priceRegular, strRead);
	        		}
	        		//判断是否含有class = rate    取折扣
	        		if(strRead.contains("<span class=\"rate")){
	        			discount = gMethod.regularGroup(priceRegular, strRead).replace("|", "").replace("NULL", "");
	        		}
	        		
	        		//判断是否含有class=star star-s  取评价
	        		if(strRead.contains("<span class=\"star star-s")){
	        			p_dsr = gMethod.regularGroup(dsrRegular, strRead).substring(13, 16);
	        		}
	        		//判断是否含有title= Feedback    取销量faceback
	        		if(strRead.contains("title=\"Feedback")||strRead.contains("class=\"rate-num\"")){
	        			p_faceback = gMethod.regularGroup(title, strRead).replace("Feedback", "").replace("(", "").replace(")", "");
	        			if(p_faceback.contains("|")){
	        				p_faceback = p_faceback.split("|")[0];
	        			}
	        		}
	        		//判断是否含有<em title="Total Orders">    取排序orders
	        		if(strRead.contains("<em title=\"Total Orders\">")){
	        			p_orders = gMethod.regularGroup(orderRegular, strRead).trim().replace("Order  (", "").replace("Orders  (", "").replace("Order (", "").replace("Orders (", "").replace(")", "");
	        		}
	        		//判断是否含有class="store"    取store相关的值
	        		if(strRead.contains("class=\"store\"")){
	        			String pattern= "href=\"([^\"]*)\"";
	        			String storeName= "title=\"([^\"]*)\"";
	        			Pattern pKey = Pattern.compile(pattern, 2 | Pattern.DOTALL); 
	        			Matcher mKey = pKey.matcher(strRead); 
	        			if(mKey.find()){ 
	        				s_url = mKey.group(1);
	        				if(s_url!=null&&s_url.length()>0){
	            				if(s_url.split("/").length==5){
	            					s_id = s_url.split("/")[4].replace(".html", "");
	            				}else if(s_url.split("/").length>5){
	            					s_id = s_url.split("/")[5].split("-")[0];
	            				}
	            			}
	            			s_name = gMethod.regularGroup(storeName, strRead);
	            			s_name = s_name.replace("'", "\'");
	        			}
	        		}
	        		//判断是否含有class="score-dot"   取store_faceback   store_facebackper相关的值
	        		if(strRead.contains("class=\"score-dot\"")){
	        			String feedbackscore= "feedbackscore=\"([^\"]*)\"";
	        			String sellerpositivefeedbackpercentage= "sellerpositivefeedbackpercentage=\"([^\"]*)\"";
	        			
	        			s_faceback = gMethod.regularGroup(feedbackscore, strRead);
	        			s_facebackper = gMethod.regularGroup(sellerpositivefeedbackpercentage, strRead);
	        		}
	        		
	        		if(strRead.contains("</li>")){
	        			if(p_id!=null&&!p_orders.equals("0")){
	        				ProductEntity product = new ProductEntity();
	        				product.setProductId(p_id);
	        				product.setProductName(p_name);
	        				product.setProductUrl(p_url);
	        				product.setProductImg(p_img);
	        				product.setProductPrice(p_price);
	        				product.setProductDsr(p_dsr);
	        				product.setProductFaceback(p_faceback);
	        				product.setProductOrdersToday(p_orders);
//	        				product.setCategoryOrders(++c_orders);
	        				product.setStoreId(s_id);
	        				product.setStoreName(s_name);
//	        				product.setStoreFaceback(s_faceback);
//	        				product.setStoreFacebackper(s_facebackper);
	        				product.setCategoryId(category_id.toString());
	        				product.setDate(new Date());
//	        				product.setDiscount(discount);
//	        				product.setAllIndex(all_index++);

//	        				productService.getSession().
	        				productService.save(product);
	        				systemService.addLog("采集", Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
	        				p_name = null;
	        				p_url = null;
	        				img = null;
	        				p_price = null;
	        				p_dsr = null;
	        				p_faceback = null;
	        				p_id = null;
	        				p_orders = "0";
	        				s_id = null;
	        				s_name = null;
	        				s_faceback = null;
	        				s_facebackper = null;
	        				discount = null;
	        				if(++count % 10000 == 0) {
	        					Runnable r = new Runnable(){  
	        					public void run(){  
		    							 /*try {
	//										ps.executeBatch();
	//										ps.clearBatch();
										} catch (SQLException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}*/
	        						}  
	    						}; 
	    						Thread t = new Thread(r);  
	    						t.start();  
	        			        System.out.println("执行ps");
	        			    }
	        			}
	        		}
	        		
	        		if(strRead.contains("</li>")){
	        			index++;
	        			if(index>=37){
	        				System.out.println("结束"+strRead);
	        				index = 1;
	        				start = false;
	        				return;
	        			}
	        		}
	        	}
	        }
	        // 当读完数据后 记得关闭 BufferReader
	        br.close();
	    } catch (IOException e) {
	        // 如果出错 抛出异常
	    	logger.error(e.getMessage());
	        e.printStackTrace();
	    }
		
	}
}
