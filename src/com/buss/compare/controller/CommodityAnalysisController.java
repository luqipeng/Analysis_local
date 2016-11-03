package com.buss.compare.controller;
import com.alibaba.fastjson.JSONObject;
import com.buss.caiji.entity.StoreProductEntity;
import com.buss.caiji.service.StoreProductServiceI;
import com.buss.caiji.util.Constant;
import com.buss.compare.entity.CommodityAnalysisCompareEntity;
import com.buss.compare.entity.CommodityAnalysisEntity;
import com.buss.compare.entity.SkuProductEntity;
import com.buss.compare.service.CommodityAnalysisServiceI;
import com.buss.compare.service.SkuProductServiceI;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.context.annotation.Scope;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import java.io.OutputStream;
import java.io.PrintWriter;

import org.jeecgframework.core.util.BrowserUtils;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.TemplateExportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.vo.TemplateExcelConstants;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.xmlbeans.impl.xb.xsdschema.TotalDigitsDocument.TotalDigits;
import org.jeecgframework.core.util.ResourceUtil;
import java.io.IOException;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.util.Map;
import org.jeecgframework.core.util.ExceptionUtil;



/**   
 * @Title: Controller
 * @Description: commodity_analysis
 * @author onlineGenerator
 * @date 2016-02-04 11:04:36
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/commodityAnalysisController")
public class CommodityAnalysisController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(CommodityAnalysisController.class);

	@Autowired
	private CommodityAnalysisServiceI commodityAnalysisService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private SkuProductServiceI skuProductService;
	@Autowired
	private StoreProductServiceI storeProductService;
	private String message;
	
	/**
	 * 注入一个sessionFactory属性,并注入到父类(HibernateDaoSupport)
	 * **/
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public Session getSession() {
		// 事务必须是开启的(Required)，否则获取不到
		return sessionFactory.getCurrentSession();
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * commodity_analysis列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "commodityAnalysis")
	public ModelAndView commodityAnalysis(HttpServletRequest request) {
		return new ModelAndView("com/buss/compare/commodityAnalysisList");
	}
	
	
	/**
	 * commodity_analysis列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "compare_")
	public ModelAndView compare_(HttpServletRequest request) {
		return new ModelAndView("com/buss/compare/compare");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(CommodityAnalysisEntity commodityAnalysis,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(CommodityAnalysisEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, commodityAnalysis, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.commodityAnalysisService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	@RequestMapping(params = "getTimes")
	public void getTimes(CommodityAnalysisEntity commodityAnalysis,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(CommodityAnalysisEntity.class, dataGrid);
		Criteria crit = cq.getDetachedCriteria().getExecutableCriteria(
				getSession());
		crit.setProjection(Projections.distinct(Projections.property("time")));
		crit.addOrder(Order.asc("time"));
		
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		try {
			PrintWriter pw=response.getWriter();
			pw.write(crit.list().toString());
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	@RequestMapping(params = "getStores")
	public void getStores(SkuProductEntity skuProductEntity,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(SkuProductEntity.class, dataGrid);
		Criteria crit = cq.getDetachedCriteria().getExecutableCriteria(
				getSession());
		crit.setProjection(Projections.distinct(Projections.property("storeName")));
		crit.addOrder(Order.asc("storeName"));

		try {
			PrintWriter pw=response.getWriter();
			pw.write(crit.list().toString());
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * easyui AJAX请求数据
	 * 商品对比
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid2")
	public void datagrid2(CommodityAnalysisEntity commodityAnalysis,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(CommodityAnalysisEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, commodityAnalysis, request.getParameterMap());
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		String sort = request.getParameter("sort");
		String order = request.getParameter("order");
		String curTime = request.getParameter("curTime");
		String priTime = request.getParameter("priTime");
		String store = request.getParameter("store");
		int total = 0,pageEnd;
		if(curTime==null||"".equals(curTime)){
			return;
		}
		try{
		//自定义追加查询条件
			cq.eq("time", curTime);
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		
		cq.add();
//		this.commodityAnalysisService.getDataGridReturn(cq, true);
		//start jdbc search
		Statement st=null;
        ResultSet rs=null;
        Statement stC=null;
        ResultSet rsC=null;
        String sql = "";
        String sqlCount = "select count(*) from commodity_analysis this_ inner join commodity_analysis this2_ on this_.c_id=this2_.c_id " +
				"where this_.time="+curTime+" and this2_.time = "+priTime;
        sql = "select this_.id as id,this_.SEARCH_EXPOSURE as searchExposureCur,this2_.SEARCH_EXPOSURE as searchExposurePri,ROUND(this_.SEARCH_EXPOSURE/this2_.SEARCH_EXPOSURE,2) as searchExposureRate,this_.SEARCH_EXPOSURE-this2_.SEARCH_EXPOSURE as searchExposureDif," +
				"this_.VIEW_NUM as viewNumCur,this2_.VIEW_NUM as viewNumPri,ROUND(this_.VIEW_NUM/this2_.VIEW_NUM,2) as viewNumRate,this_.VIEW_NUM-this2_.VIEW_NUM as viewNumDif," +
				"this_.BUYER_RATE as buyerRateCur,this2_.BUYER_RATE as buyerRatePri,ROUND(this_.BUYER_RATE/this2_.BUYER_RATE,2) as buyerRateRate,ROUND(this_.BUYER_RATE-this2_.BUYER_RATE,2) as buyerRateDif," +
				"this_.SEARCH_HITS as searchHitsCur,this2_.SEARCH_HITS as searchHitsPri,ROUND(this_.SEARCH_HITS/this2_.SEARCH_HITS,2) as searchHitsRate,ROUND(this_.SEARCH_HITS-this2_.SEARCH_HITS,2) as searchHitsDif," +
				"this_.ADD_SHOPPING_CART_PERSON as addShoppingCartPersonCur,this2_.ADD_SHOPPING_CART_PERSON as addShoppingCartPersonPri,ROUND(this_.ADD_SHOPPING_CART_PERSON/this2_.ADD_SHOPPING_CART_PERSON,2) as addShoppingCartPersonRate,this_.ADD_SHOPPING_CART_PERSON-this2_.ADD_SHOPPING_CART_PERSON as addShoppingCartPersonDif," +
				"this_.COLLECTION_PERSON as collectionPersonCur,this2_.COLLECTION_PERSON as collectionPersonPri,ROUND(this_.COLLECTION_PERSON/this2_.COLLECTION_PERSON,2) as collectionPersonRate,this_.COLLECTION_PERSON-this2_.COLLECTION_PERSON as collectionPersonDif," +
				"this_.PAY_ORDERS as payOrdersCur,this2_.PAY_ORDERS as payOrdersPri,ROUND(this_.PAY_ORDERS/this2_.PAY_ORDERS,2) as payOrdersRate,ROUND(this_.PAY_ORDERS-this2_.PAY_ORDERS,2) as payOrdersDif," +
				"this_.PAY_MONEY as payMoneyCur,this2_.PAY_MONEY as payMoneyPri,ROUND(this_.PAY_MONEY/this2_.PAY_MONEY,2) as payMoneyRate,ROUND(this_.PAY_MONEY-this2_.PAY_MONEY,2) as payMoneyDif," +
				"this_.REFUND_ORDERS as refundOrdersCur,this2_.REFUND_ORDERS as refundOrdersPri,ROUND(this_.REFUND_ORDERS/this2_.REFUND_ORDERS,2) as refundOrdersRate,ROUND(this_.REFUND_ORDERS-this2_.REFUND_ORDERS,2) as refundOrdersDif," +
				"this_.REFUND_AMOUNT as refundAmountCur,this2_.REFUND_AMOUNT as refundAmountPri,ROUND(this_.REFUND_AMOUNT/this2_.REFUND_AMOUNT,2) as refundAmountRate,ROUND(this_.REFUND_AMOUNT-this2_.REFUND_AMOUNT,2) as refundAmountDif," +
				"skuproduct2_.PRODUCT_ID as productId,skuproduct2_.PRODUCT_NAME as productName,skuproduct2_.SKU as sku,skuproduct2_.STORE_NAME as storeName " +
				"from commodity_analysis this_ inner join commodity_analysis this2_ on this_.c_id=this2_.c_id left outer join sku_product skuproduct2_  on this_.C_ID=skuproduct2_.PRODUCT_ID " +
				"where this_.time="+curTime+" and this2_.time = "+priTime;
//				+" order by this_.id desc limit 10";
       if(sort!=null && !"".equals(sort)){
    	   sql += " order by "+ sort.replace("sku.", "") + " " + order;
       }
       
        try {
			con=getConnection();
			stC=con.createStatement();
			rsC=stC.executeQuery(sqlCount);
			if(rsC.next()){
				total = rsC.getInt(1);
				dataGrid.setTotal(total);
			}
			if(page > 1){
				sql += " limit " + (page-1)*rows + "," + rows;
			}else{
				 sql += " limit "+rows;
			}
			st=con.createStatement();
			rs=st.executeQuery(sql);
			
			List<CommodityAnalysisCompareEntity> result = new ArrayList<CommodityAnalysisCompareEntity>();
			while(rs.next()){
				CommodityAnalysisCompareEntity commodityAnalysisCompareEntity = new CommodityAnalysisCompareEntity();
				SkuProductEntity sku = new SkuProductEntity();
				sku.setProductId(rs.getString("productId"));
				sku.setProductName(rs.getString("productName"));
				sku.setSku(rs.getString("sku"));
				sku.setStoreName(rs.getString("storeName"));
				commodityAnalysisCompareEntity.setSku(sku);
				commodityAnalysisCompareEntity.setId(rs.getInt(1));
				//曝光量
				commodityAnalysisCompareEntity.setSearchExposureCur(rs.getString(2));
				commodityAnalysisCompareEntity.setSearchExposurePri(rs.getString(3));
				commodityAnalysisCompareEntity.setSearchExposureRate(rs.getString(4));
				commodityAnalysisCompareEntity.setSearchExposureDif(rs.getString(5));
				//浏览量
				commodityAnalysisCompareEntity.setViewNumCur(rs.getString(6));
				commodityAnalysisCompareEntity.setViewNumPri(rs.getString(7));
				commodityAnalysisCompareEntity.setViewNumRate(rs.getString(8));
				commodityAnalysisCompareEntity.setViewNumDif(rs.getString(9));
				
				//转化率
				commodityAnalysisCompareEntity.setBuyerRateCur(rs.getString(10));
				commodityAnalysisCompareEntity.setBuyerRatePri(rs.getString(11));
				commodityAnalysisCompareEntity.setBuyerRateRate(rs.getString(12));
				commodityAnalysisCompareEntity.setBuyerRateDif(rs.getString(13));
				
				//点击率
				commodityAnalysisCompareEntity.setSearchHitsCur(rs.getString(14));
				commodityAnalysisCompareEntity.setSearchHitsPri(rs.getString(15));
				commodityAnalysisCompareEntity.setSearchHitsRate(rs.getString(16));
				commodityAnalysisCompareEntity.setSearchHitsDif(rs.getString(17));
		
				//加购数
				commodityAnalysisCompareEntity.setAddShoppingCartPersonCur(rs.getString(18));
				commodityAnalysisCompareEntity.setAddShoppingCartPersonPri(rs.getString(19));
				commodityAnalysisCompareEntity.setAddShoppingCartPersonRate(rs.getString(20));
				commodityAnalysisCompareEntity.setAddShoppingCartPersonDif(rs.getString(21));
				
				//收藏数
				commodityAnalysisCompareEntity.setCollectionPersonCur(rs.getString(22));
				commodityAnalysisCompareEntity.setCollectionPersonPri(rs.getString(23));
				commodityAnalysisCompareEntity.setCollectionPersonRate(rs.getString(24));
				commodityAnalysisCompareEntity.setCollectionPersonDif(rs.getString(25));
		
				//支付订单数
				commodityAnalysisCompareEntity.setPayOrdersCur(rs.getString(26));
				commodityAnalysisCompareEntity.setPayOrdersPri(rs.getString(27));
				commodityAnalysisCompareEntity.setPayOrdersRate(rs.getString(28));
				commodityAnalysisCompareEntity.setPayOrdersDif(rs.getString(29));
				
				//支付金额
				commodityAnalysisCompareEntity.setPayMoneyCur(rs.getString(30));
				commodityAnalysisCompareEntity.setPayMoneyPri(rs.getString(31));
				commodityAnalysisCompareEntity.setPayMoneyRate(rs.getString(32));
				commodityAnalysisCompareEntity.setPayMoneyDif(rs.getString(33));
		
				//退款订单数
				commodityAnalysisCompareEntity.setRefundOrdersCur(rs.getString(34));
				commodityAnalysisCompareEntity.setRefundOrdersPri(rs.getString(35));
				commodityAnalysisCompareEntity.setRefundOrdersRate(rs.getString(36));
				commodityAnalysisCompareEntity.setRefundOrdersDif(rs.getString(37));
		
				//退款金额
				commodityAnalysisCompareEntity.setRefundAmountCur(rs.getString(38));
				commodityAnalysisCompareEntity.setRefundAmountPri(rs.getString(39));
				commodityAnalysisCompareEntity.setRefundAmountRate(rs.getString(40));
				commodityAnalysisCompareEntity.setRefundAmountDif(rs.getString(41));
		
				
				result.add(commodityAnalysisCompareEntity);
			}
			dataGrid.setResults(result);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//end
		
		/*List<CommodityAnalysisEntity> list = dataGrid.getResults();
		List<CommodityAnalysisCompareEntity> result = new ArrayList<CommodityAnalysisCompareEntity>();
		DecimalFormat df = new DecimalFormat("######0.00");   
		DecimalFormat df2 = new DecimalFormat("######0");
		for(CommodityAnalysisEntity cur:list){
			CommodityAnalysisCompareEntity commodityAnalysisCompareEntity = new CommodityAnalysisCompareEntity();
			commodityAnalysisCompareEntity.setSku(cur.getSku());
			List<CommodityAnalysisEntity> commodityAnalysisEntities = commodityAnalysisService.findHql("from CommodityAnalysisEntity where c_Id = ? and time = ?", cur.getC_Id(), priTime);
			CommodityAnalysisEntity pri = null;
			if(commodityAnalysisEntities.size()>0){
				pri = commodityAnalysisEntities.get(0);
			}else{
				commodityAnalysisCompareEntity.setSearchExposureCur(cur.getSearchExposure());
				commodityAnalysisCompareEntity.setViewNumCur(cur.getViewNum());
				result.add(commodityAnalysisCompareEntity);
				continue;
			}
			
			//曝光量
			commodityAnalysisCompareEntity.setSearchExposureCur(cur.getSearchExposure());
			commodityAnalysisCompareEntity.setSearchExposurePri(pri.getSearchExposure());
			if(Double.parseDouble(cur.getSearchExposure()) !=0 && Double.parseDouble(pri.getSearchExposure()) !=0 )
			commodityAnalysisCompareEntity.setSearchExposureRate(df.format(Double.parseDouble(cur.getSearchExposure())/Double.parseDouble(pri.getSearchExposure())));
			commodityAnalysisCompareEntity.setSearchExposureDif(df2.format(Double.parseDouble(cur.getSearchExposure())-Double.parseDouble(pri.getSearchExposure())));
			
			//浏览量
			commodityAnalysisCompareEntity.setViewNumCur(cur.getViewNum());
			commodityAnalysisCompareEntity.setViewNumPri(pri.getViewNum());
			if(Double.parseDouble(cur.getViewNum()) !=0 && Double.parseDouble(pri.getViewNum()) !=0 )
			commodityAnalysisCompareEntity.setViewNumRate(df.format(Double.parseDouble(cur.getViewNum())/Double.parseDouble(pri.getViewNum())));
			commodityAnalysisCompareEntity.setViewNumDif(df2.format(Double.parseDouble(cur.getViewNum())-Double.parseDouble(pri.getViewNum())));
			
			//转化率
			commodityAnalysisCompareEntity.setBuyerRateCur(cur.getBuyerRate());
			commodityAnalysisCompareEntity.setBuyerRatePri(pri.getBuyerRate());
			if(Double.parseDouble(cur.getBuyerRate().replace("%", "")) !=0 && Double.parseDouble(pri.getBuyerRate().replace("%", "")) !=0 )
			commodityAnalysisCompareEntity.setBuyerRateRate(df.format(Double.parseDouble(cur.getBuyerRate().replace("%", ""))/Double.parseDouble(pri.getBuyerRate().replace("%", ""))));
			commodityAnalysisCompareEntity.setBuyerRateDif(df2.format(Double.parseDouble(cur.getBuyerRate().replace("%", ""))-Double.parseDouble(pri.getBuyerRate().replace("%", ""))));
			
			//点击率
			commodityAnalysisCompareEntity.setSearchHitsCur(cur.getSearchHits());
			commodityAnalysisCompareEntity.setSearchHitsPri(pri.getSearchHits());
			if(Double.parseDouble(cur.getSearchHits().replace("%", "")) !=0 && Double.parseDouble(pri.getSearchHits().replace("%", "")) !=0 )
			commodityAnalysisCompareEntity.setSearchHitsRate(df.format(Double.parseDouble(cur.getSearchHits().replace("%", ""))/Double.parseDouble(pri.getSearchHits().replace("%", ""))));
			commodityAnalysisCompareEntity.setSearchHitsDif(df2.format(Double.parseDouble(cur.getSearchHits().replace("%", ""))-Double.parseDouble(pri.getSearchHits().replace("%", ""))));
	
			//加购数
			commodityAnalysisCompareEntity.setAddShoppingCartPersonCur(cur.getAddShoppingCartPerson());
			commodityAnalysisCompareEntity.setAddShoppingCartPersonPri(pri.getAddShoppingCartPerson());
			if(Double.parseDouble(cur.getAddShoppingCartPerson()) !=0 && Double.parseDouble(pri.getAddShoppingCartPerson()) !=0 )
			commodityAnalysisCompareEntity.setAddShoppingCartPersonRate(df.format(Double.parseDouble(cur.getAddShoppingCartPerson())/Double.parseDouble(pri.getAddShoppingCartPerson())));
			commodityAnalysisCompareEntity.setAddShoppingCartPersonDif(df2.format(Double.parseDouble(cur.getAddShoppingCartPerson())-Double.parseDouble(pri.getAddShoppingCartPerson())));
			
			//收藏数
			commodityAnalysisCompareEntity.setCollectionPersonCur(cur.getCollectionPerson());
			commodityAnalysisCompareEntity.setCollectionPersonPri(pri.getCollectionPerson());
			if(Double.parseDouble(cur.getCollectionPerson()) !=0 && Double.parseDouble(pri.getCollectionPerson()) !=0 )
			commodityAnalysisCompareEntity.setCollectionPersonRate(df.format(Double.parseDouble(cur.getCollectionPerson())/Double.parseDouble(pri.getCollectionPerson())));
			commodityAnalysisCompareEntity.setCollectionPersonDif(df2.format(Double.parseDouble(cur.getCollectionPerson())-Double.parseDouble(pri.getCollectionPerson())));
	
			//支付订单数
			commodityAnalysisCompareEntity.setPayOrdersCur(cur.getPayOrders());
			commodityAnalysisCompareEntity.setPayOrdersPri(pri.getPayOrders());
			if(Double.parseDouble(cur.getPayOrders()) !=0 && Double.parseDouble(pri.getPayOrders()) !=0 )
			commodityAnalysisCompareEntity.setPayOrdersRate(df.format(Double.parseDouble(cur.getPayOrders())/Double.parseDouble(pri.getPayOrders())));
			commodityAnalysisCompareEntity.setPayOrdersDif(df2.format(Double.parseDouble(cur.getPayOrders())-Double.parseDouble(pri.getPayOrders())));
			
			//支付金额
			commodityAnalysisCompareEntity.setPayMoneyCur(cur.getPayMoney());
			commodityAnalysisCompareEntity.setPayMoneyPri(pri.getPayMoney());
			if(Double.parseDouble(cur.getPayMoney()) !=0 && Double.parseDouble(pri.getPayMoney()) !=0 )
			commodityAnalysisCompareEntity.setPayMoneyRate(df.format(Double.parseDouble(cur.getPayMoney())/Double.parseDouble(pri.getPayMoney())));
			commodityAnalysisCompareEntity.setPayMoneyDif(df2.format(Double.parseDouble(cur.getPayMoney())-Double.parseDouble(pri.getPayMoney())));
	
			//退款订单数
			commodityAnalysisCompareEntity.setRefundOrdersCur(cur.getRefundOrders());
			commodityAnalysisCompareEntity.setRefundOrdersPri(pri.getRefundOrders());
			if(Double.parseDouble(cur.getRefundOrders()) !=0 && Double.parseDouble(pri.getRefundOrders()) !=0 )
			commodityAnalysisCompareEntity.setRefundOrdersRate(df.format(Double.parseDouble(cur.getRefundOrders())/Double.parseDouble(pri.getRefundOrders())));
			commodityAnalysisCompareEntity.setRefundOrdersDif(df2.format(Double.parseDouble(cur.getRefundOrders())-Double.parseDouble(pri.getRefundOrders())));
	
			//退款金额
			commodityAnalysisCompareEntity.setRefundAmountCur(cur.getRefundAmount());
			commodityAnalysisCompareEntity.setRefundAmountPri(pri.getRefundAmount());
			if(Double.parseDouble(cur.getRefundAmount()) !=0 && Double.parseDouble(pri.getRefundAmount()) !=0 )
			commodityAnalysisCompareEntity.setRefundAmountRate(df.format(Double.parseDouble(cur.getRefundAmount())/Double.parseDouble(pri.getRefundAmount())));
			commodityAnalysisCompareEntity.setRefundAmountDif(df2.format(Double.parseDouble(cur.getRefundAmount())-Double.parseDouble(pri.getRefundAmount())));
	
			
			result.add(commodityAnalysisCompareEntity);
		}
		dataGrid.setResults(result);*/
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除commodity_analysis
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(CommodityAnalysisEntity commodityAnalysis, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		commodityAnalysis = systemService.getEntity(CommodityAnalysisEntity.class, commodityAnalysis.getC_Id());
		message = "commodity_analysis删除成功";
		try{
			commodityAnalysisService.delete(commodityAnalysis);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "commodity_analysis删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除commodity_analysis
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "commodity_analysis删除成功";
		try{
			for(String id:ids.split(",")){
				if(!id.equals("")){
					CommodityAnalysisEntity commodityAnalysis = systemService.getEntity(CommodityAnalysisEntity.class,
							Integer.parseInt(id)
					);
					commodityAnalysisService.delete(commodityAnalysis);
					systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "commodity_analysis删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加commodity_analysis
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(CommodityAnalysisEntity commodityAnalysis, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "commodity_analysis添加成功";
		try{
			commodityAnalysisService.save(commodityAnalysis);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "commodity_analysis添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新commodity_analysis
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(CommodityAnalysisEntity commodityAnalysis, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "commodity_analysis更新成功";
		CommodityAnalysisEntity t = commodityAnalysisService.get(CommodityAnalysisEntity.class, commodityAnalysis.getC_Id());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(commodityAnalysis, t);
			commodityAnalysisService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "commodity_analysis更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * commodity_analysis新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(CommodityAnalysisEntity commodityAnalysis, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(commodityAnalysis.getC_Id())) {
			commodityAnalysis = commodityAnalysisService.getEntity(CommodityAnalysisEntity.class, commodityAnalysis.getC_Id());
			req.setAttribute("commodityAnalysisPage", commodityAnalysis);
		}
		return new ModelAndView("com/buss/compare/commodityAnalysis-add");
	}
	/**
	 * commodity_analysis编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(CommodityAnalysisEntity commodityAnalysis, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(commodityAnalysis.getC_Id())) {
			commodityAnalysis = commodityAnalysisService.getEntity(CommodityAnalysisEntity.class, commodityAnalysis.getC_Id());
			req.setAttribute("commodityAnalysisPage", commodityAnalysis);
		}
		return new ModelAndView("com/buss/compare/commodityAnalysis-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name", "commodityAnalysisController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(CommodityAnalysisEntity commodityAnalysis,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(CommodityAnalysisEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, commodityAnalysis, request.getParameterMap());
		List<CommodityAnalysisEntity> commodityAnalysiss = this.commodityAnalysisService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"commodity_analysis");
		modelMap.put(NormalExcelConstants.CLASS,CommodityAnalysisEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS, new ExportParams("commodity_analysis列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(),
				"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST, commodityAnalysiss);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(CommodityAnalysisEntity commodityAnalysis,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		modelMap.put(TemplateExcelConstants.FILE_NAME, "commodity_analysis");
		modelMap.put(TemplateExcelConstants.PARAMS,new TemplateExportParams("Excel模板地址"));
		modelMap.put(TemplateExcelConstants.MAP_DATA,null);
		modelMap.put(TemplateExcelConstants.CLASS,CommodityAnalysisEntity.class);
		modelMap.put(TemplateExcelConstants.LIST_DATA,null);
		return TemplateExcelConstants.JEECG_TEMPLATE_EXCEL_VIEW;
	}

	/**
	 * 产品周销量导入
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			String time = "";
			String storeName = "";
			time = file.getOriginalFilename().split("-")[1].replace(".xls", "");
			storeName = file.getOriginalFilename().split("-")[0];
			ImportParams params = new ImportParams();
			params.setTitleRows(0);
			params.setHeadRows(1);
			params.setNeedSave(true);
			try {
				List<CommodityAnalysisEntity> listCommodityAnalysisEntitys = ExcelImportUtil.importExcel(file.getInputStream(),CommodityAnalysisEntity.class,params);
				for (CommodityAnalysisEntity commodityAnalysis : listCommodityAnalysisEntitys) {
					if("商品ID".equals(commodityAnalysis.getC_Id()))continue;
					commodityAnalysis.setTime(time.substring(0, 4)+"-"+time.substring(4, 6)+"-"+time.substring(6, 8));
					SkuProductEntity sku = skuProductService.get(SkuProductEntity.class, commodityAnalysis.getC_Id().replace(",", ""));
					StoreProductEntity storeProduct = storeProductService.get(StoreProductEntity.class, commodityAnalysis.getC_Id().replace(",", ""));
					if(storeProduct == null){
						storeProduct = new StoreProductEntity();
						storeProduct.setProductId(commodityAnalysis.getC_Id().replace(",", ""));
						storeProduct.setStoreName(storeName);
						storeProduct.setProductName(commodityAnalysis.getCTitle());
						storeProduct.setFirstTime(df.format(new Date()));
						storeProduct.setType("1");
						storeProductService.save(storeProduct);
						commodityAnalysis.setC_Id(commodityAnalysis.getC_Id().replace(",", ""));
					}
					if(sku == null) {
						sku = new SkuProductEntity();
						sku.setProductId(commodityAnalysis.getC_Id().replace(",", ""));
						sku.setStoreName(storeName);
						sku.setProductName(commodityAnalysis.getCTitle());
						skuProductService.save(sku);
						commodityAnalysis.setC_Id(commodityAnalysis.getC_Id().replace(",", ""));
					}
					commodityAnalysis.setStoreProduct(storeProduct);
					//commodityAnalysis.setSku(sku);
//					commodityAnalysis.setC_Id(commodityAnalysis.getC_Id().replace(",", ""));
					commodityAnalysis.setSearchExposure(commodityAnalysis.getSearchExposure().replace(",", ""));
					commodityAnalysis.setViewNum(commodityAnalysis.getViewNum().replace(",", ""));
					commodityAnalysis.setVisitorsNum(commodityAnalysis.getVisitorsNum().replace(",", ""));
					commodityAnalysis.setAddShoppingCartPerson(commodityAnalysis.getAddShoppingCartPerson().replace(",", ""));
					commodityAnalysis.setCollectionPerson(commodityAnalysis.getCollectionPerson().replace(",", ""));
					commodityAnalysis.setPayOrders(commodityAnalysis.getPayOrders().replace(",", ""));
					commodityAnalysis.setPayMoney(commodityAnalysis.getPayMoney().replace(",", ""));
					List<CommodityAnalysisEntity> list = commodityAnalysisService.findHql("from CommodityAnalysisEntity where c_Id = '"+commodityAnalysis.getC_Id().replace(",", "")+"' and time = '"+time+"'");
					if(list.size()==0){
						commodityAnalysisService.save(commodityAnalysis);
					}else{
						commodityAnalysis = list.get(0);
						//commodityAnalysis.setSku(sku);
						commodityAnalysis.setC_Id(commodityAnalysis.getC_Id().replace(",", ""));
						commodityAnalysisService.saveOrUpdate(commodityAnalysis);
					}

				}
				j.setMsg("文件导入成功！");
			} catch (Exception e) {
				j.setMsg("文件导入失败！");
				logger.error(ExceptionUtil.getExceptionMessage(e));
			}finally{
				try {
					file.getInputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return j;
	}
	
	
	private static Connection con=null;
    
    
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
		if(con == null){
			Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection(Constant.url_local, Constant.user, Constant.password);
		}
		return con;
	}
}
