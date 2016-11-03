package com.buss.caiji.util;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import com.buss.caiji.entity.FeedbackInfo;
import com.buss.caiji.entity.StoreBean;
import com.buss.caiji.entity.StoreEntity;
import com.buss.caiji.method.GroupMethod;
import org.apache.ibatis.jdbc.ScriptRunner;

import com.alibaba.druid.support.logging.Resources;
import com.buss.caiji.entity.CaijiCategoryEntity;

/**
 * 常用工具
 *
 * <p>字段串的操作,时间操作,MD5加密,上传文件
 */
public class Tools {

	/**
	 * md5加密
	 * @param x
	 * @return 加密后的字符串
	 */
	public static String md5(String x) {
		try {
			MessageDigest m = MessageDigest.getInstance("MD5");
			m.update(x.getBytes("UTF8"));
			byte s[] = m.digest();
			String result = "";
			for (int i = 0; i < s.length; i++) {
				result += Integer.toHexString((0x000000FF & s[i]) | 0xFFFFFF00)
						.substring(6);
			}
			return result;
		} catch (Exception e) {
			System.out.println("Tools.md5 加密[" + x + "]失败");
			return null;
		}
	}

	/**
	 * 格式化时间
	 * @param dateTime  要格式化的时间
	 * @param pattern   格式化的样式
	 * @return 已格式化的时间
	 */
	public static String formatDateTime(Date dateTime, String pattern){
		SimpleDateFormat dateFmt = new SimpleDateFormat(pattern);
		return dateTime==null?"":dateFmt.format(dateTime);
	}
	/**
	 * 取得时间
	 * @param dateTime
	 * @return 返回"2007"数据格式的字符串
	 */
	public static String miniTime(Date dateTime) {
		return formatDateTime(dateTime,"yyyy");
	}

	/**
	 * 取得时间
	 * @param dateTime
	 * @return 返回"2007-09-10"数据格式的字符串
	 */
	public static String shortTime(Date dateTime) {
		return formatDateTime(dateTime,"yyyy-MM-dd");
	}

	/**
	 * 取得时间
	 * @param dateTime
	 * @return  返回"2007-09-10 16:09"数据格式的字符串
	 */
	public static String middleTime(Date dateTime) {
		return formatDateTime(dateTime,"yyyy-MM-dd HH:mm");
	}

	/**
	 * 取得时间
	 * @param dateTime
	 * @return 返回"2007-09-10 16:09:00"数据格式的字符串
	 */
	public static String longTime(Date dateTime) {
		return formatDateTime(dateTime,"yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 取得时间
	 * @param dateTime
	 * @return 返回"20070910160900"数据格式的字符串
	 */
	public static String minLongTime(Date dateTime) {
		return formatDateTime(dateTime,"yyyyMMddHHmmss");
	}

	/**
	 * 取得时间
	 * @param dateTime
	 * @return 返回"2007年09月10号 16点09分00秒"数据格式的字符串
	 */
	public static String longZhTime(Date dateTime) {
		return formatDateTime(dateTime,"yyyy年MM月dd号 HH点mm分ss秒");
	}

	/**
	 * 取得时间
	 * @param dateTime
	 * @return 返回"2007/09/10 16:09:00.000"数据格式的字符串
	 */
	public static String bigLongTime(Date dateTime) {
		return formatDateTime(dateTime, "yyyy/MM/dd HH:mm:ss.SSS");
	}

	/**
	 * 时间+-天数 :要得到的时间
	 * @param d      时间
	 * @param offset 天数
	 * @param bool  true天数加false天数减
	 * @return
	 */
	public static Date changeDay(Date d,int offset,boolean bool){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		if(bool){
			calendar.set(Calendar.DAY_OF_YEAR,(calendar.get(Calendar.DAY_OF_YEAR) + offset));
		}else{
			calendar.set(Calendar.DAY_OF_YEAR,(calendar.get(Calendar.DAY_OF_YEAR) - offset));
		}
		return calendar.getTime();
	}
	/**
	 * 时间+-天数 :要得到的时间
	 * @param d      时间
	 * @param offset 天数
	 * @param bool  true天数加false天数减
	 * @return
	 */
	public static Timestamp changeDay(Timestamp d,int offset,boolean bool){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		if(bool){
			calendar.set(Calendar.DAY_OF_YEAR,(calendar.get(Calendar.DAY_OF_YEAR) + offset));
		}else{
			calendar.set(Calendar.DAY_OF_YEAR,(calendar.get(Calendar.DAY_OF_YEAR) - offset));
		}
		return new Timestamp(calendar.getTimeInMillis());
	}
	/**
	 * 时间+-多少年 :要得到的时间
	 * @param d      时间
	 * @param offset 年数
	 * @param bool  true年数加false年数减
	 * @return
	 */
	public static Date changeYear(Date d,int offset,boolean bool){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		if(bool){
			calendar.set(Calendar.YEAR,(calendar.get(Calendar.YEAR) + offset));
		}else{
			calendar.set(Calendar.YEAR,(calendar.get(Calendar.YEAR) - offset));
		}
		return calendar.getTime();
	}
	/**
	 * 时间+-多少年 :要得到的时间
	 * @param d      时间
	 * @param offset 年数
	 * @param bool  true年数加false年数减
	 * @return
	 */
	public static Timestamp changeYear(Timestamp d,int offset,boolean bool){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		if(bool){
			calendar.set(Calendar.YEAR,(calendar.get(Calendar.YEAR) + offset));
		}else{
			calendar.set(Calendar.YEAR,(calendar.get(Calendar.YEAR) - offset));
		}
		return new Timestamp(calendar.getTimeInMillis());
	}


	/**
	 * 字符串是否可以转化成Double形式
	 * @param str
	 * @return
	 */
	public static boolean isDouble(String str){
		Pattern pattern = Pattern.compile("^[-\\+]?\\d+(\\.\\d*)?|\\.\\d+$");
		return pattern.matcher(str).matches();
	}
	/**
	 * 是否可以转化为整数
	 * @param str
	 * @return
	 */
	public static boolean isInteger(String str){
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	/**
	 * 是否可以转化为整数加字符串
	 * @param str
	 * @return
	 */
	public static boolean isStr(String str){
		Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
		return pattern.matcher(str).matches();
	}

	/**
	 * 是否可以转化为数字
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str){
		return isInteger(str) || isDouble(str);
	}

	/**
	 * 取得结束的时间
	 * 如果参数为2007，则返回2007-12-31 23:59:59
	 * 如果参数为2007-08 ，则返回2007-08-31 23:59:59
	 * 如果参数为2007-09 ，则返回2007-09-30 23:59:59
	 * 如果参数为2007-09-09 ，则返回2007-09-09 23:59:59
	 * @param time yyyy yyyy-MM yyyy-MM-dd形式
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getTimeEnd(final String time){
		Calendar timeEnd = Calendar.getInstance();
		if(time!=null){
			if(time.length()==4){
				timeEnd.set(Calendar.YEAR, Integer.parseInt(time));
				timeEnd.set(Calendar.MONTH,11);
				timeEnd.set(Calendar.DATE, 1);
				timeEnd.roll(Calendar.DATE, -1);
				timeEnd.set(Calendar.HOUR_OF_DAY, 23);
				timeEnd.set(Calendar.MINUTE, 59);
				timeEnd.set(Calendar.SECOND, 59);
			}
			if(time.length()==7){
				timeEnd.set(Calendar.YEAR, Integer.parseInt((time.split("-"))[0]));
				timeEnd.set(Calendar.MONTH,Integer.parseInt((time.split("-"))[1])-1);
				timeEnd.set(Calendar.DATE, 1);
				timeEnd.roll(Calendar.DATE, -1);
				timeEnd.set(Calendar.HOUR_OF_DAY, 23);
				timeEnd.set(Calendar.MINUTE, 59);
				timeEnd.set(Calendar.SECOND, 59);
			}
			if(time.length()==10){
				timeEnd.set(Calendar.YEAR, Integer.parseInt((time.split("-"))[0]));
				timeEnd.set(Calendar.MONTH,Integer.parseInt((time.split("-"))[1])-1);
				timeEnd.set(Calendar.DATE, Integer.parseInt((time.split("-"))[2]));
				timeEnd.set(Calendar.HOUR_OF_DAY, 23);
				timeEnd.set(Calendar.MINUTE, 59);
				timeEnd.set(Calendar.SECOND, 59);
			}
		}
		return formatDateTime(timeEnd.getTime(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 取得起始的时间
	 * 如果参数为2007，则返回2007-01-01 00:00:00
	 * 如果参数为2007-09 ，则返回2007-09-01 00:00:00
	 * 如果参数为2007-09-09 ，则返回2007-09-09 00:00:00
	 * @param time yyyy yyyy-MM yyyy-MM-dd形式
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getTimeStart(final String time){
		Calendar timeStart = Calendar.getInstance();
		if(time!=null){
			if(time.length()==4){
				timeStart.set(Calendar.YEAR, Integer.parseInt(time));
				timeStart.set(Calendar.MONTH,0);
				timeStart.set(Calendar.DATE, 1);
				timeStart.set(Calendar.HOUR_OF_DAY, 0);
				timeStart.set(Calendar.MINUTE, 0);
				timeStart.set(Calendar.SECOND, 0);
			}
			if(time.length()==7){
				timeStart.set(Calendar.YEAR, Integer.parseInt((time.split("-"))[0]));
				timeStart.set(Calendar.MONTH,Integer.parseInt((time.split("-"))[1])-1);
				timeStart.set(Calendar.DATE, 1);
				timeStart.set(Calendar.HOUR_OF_DAY, 0);
				timeStart.set(Calendar.MINUTE, 0);
				timeStart.set(Calendar.SECOND, 0);
			}
			if(time.length()==10){
				timeStart.set(Calendar.YEAR, Integer.parseInt((time.split("-"))[0]));
				timeStart.set(Calendar.MONTH,Integer.parseInt((time.split("-"))[1])-1);
				timeStart.set(Calendar.DATE, Integer.parseInt((time.split("-"))[2]));
				timeStart.set(Calendar.HOUR_OF_DAY, 0);
				timeStart.set(Calendar.MINUTE, 0);
				timeStart.set(Calendar.SECOND, 0);
			}

		}
		return formatDateTime(timeStart.getTime(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 判断str是否为空或为all,成立返回false,反之返回true
	 * @param str
	 * @return
	 */
	public static boolean isActive(String str){
		return str!=null && !str.trim().equals("all") && !str.trim().equals("");
	}

	/**
	 * 判断str是否为空或为空字符串,成立返回false,反之返回true
	 * @param str
	 * @return
	 */
	public static boolean isNullChar(String str){
		return str!=null && !str.trim().equals("");
	}

	/**
	 * yyyy-MM-dd hh:mm:ss转换为date类型
	 * @param str
	 */
	public static Date getDate(String str) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = null;
		try {
			date = df.parse(str);
		} catch (Exception e) {
			System.out.println("Tools.getDate失败");
		}
		return date;
	}

	/**
	 * 数据处理,保留precision位小数
	 * @param val 要处理的数字
	 * @param precision 要保留的小数位数
	 * @return
	 */
	public static Double roundDouble(double val, int precision) {
		Double ret = null;
		try {
			double factor = Math.pow(10, precision);
			ret = Math.floor(val * factor + 0.5) / factor;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	/**
	 * 获得随机数字符串
	 *
	 * @param length
	 *            需要获得随机数的长度
	 * @param type
	 *            随机数的类型：'0':表示仅获得数字随机数；'1'：表示仅获得字符随机数；'2'：表示获得数字字符混合随机数
	 * @return
	 */
	public static String getRandomStr(int length, int type) {
		String strRandom = "";
		Random rnd = new Random();
		if (length < 0)
			length = 5;
		if ((type > 2) || (type < 0))
			type = 2;
		switch (type) {
			case 0:
				for (int iLoop = 0; iLoop < length; iLoop++) {
					strRandom += Integer.toString(rnd.nextInt(10));
				}
				break;
			case 1:
				for (int iLoop = 0; iLoop < length; iLoop++) {
					strRandom += Integer.toString((35 - rnd.nextInt(10)), 36);
				}
				break;
			case 2:
				for (int iLoop = 0; iLoop < length; iLoop++) {
					strRandom += Integer.toString(rnd.nextInt(36), 36);
				}
				break;
		}
		return strRandom;
	}

	public static boolean chkInputByRegex(String inputString,String regexString){
		Pattern p=Pattern.compile(regexString);
		Matcher m=p.matcher(inputString);
		return m.matches();
	}
	/**
	 * 取得时间段内的年的日期集合
	 * @param beginDateStr
	 * @param endDateStr
	 * @return
	 */
	public static String[] getYearList(String dateFrom, String dateEnd) {
		dateFrom = dateFrom.substring(0,4);
		dateEnd = dateEnd.substring(0,4);
		int df = Integer.valueOf(dateFrom);
		int de = Integer.valueOf(dateEnd);
		List<String> dateList = new ArrayList<String>();
		for (int i = df; i <= de; i++) {
			dateList.add(""+i);
		}
		String[] dateArray = new String[dateList.size()];
		dateList.toArray(dateArray);
		return dateArray;
	}
	/**
	 * 取得时间段内的月的日期集合
	 * @param beginDateStr
	 * @param endDateStr
	 * @return
	 */
	public static String[] getMonthList(String dateFrom, String dateEnd) {
		//指定要解析的时间格式
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		List<String> dateList = new ArrayList<String>();
		//定义一些变量
		Date beginDate = null;
		Date endDate = null;
		GregorianCalendar beginGC = null;
		GregorianCalendar endGC = null;
		try {
			//将字符串parse成日期
			beginDate = f.parse(dateFrom);
			endDate = f.parse(dateEnd);
			//设置日历
			beginGC = new GregorianCalendar();
			beginGC.setTime(beginDate);
			endGC = new GregorianCalendar();
			endGC.setTime(endDate);
			//直到两个时间相同
			while(beginGC.getTime().compareTo(endGC.getTime())<=0){
				dateList.add(beginGC.get(Calendar.YEAR) + "-" + getM(beginGC.get(Calendar.MONTH)+1));
				//以月为单位，增加时间
				beginGC.add(Calendar.MONTH,1);
			}
			dateList.add(beginGC.get(Calendar.YEAR) + "-" + getM(beginGC.get(Calendar.MONTH)+1));
			String[] dateArray = new String[dateList.size()];
			dateList.toArray(dateArray);
			return dateArray;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	private static String getM(int i) {
		String st = ""+i;
		st = "00".substring(st.length())+st;
		return st;
	}

	/**
	 * 取得时间段内的日的日期集合
	 * @param dateFrom
	 * @param dateEnd
	 * @return
	 */
	public static String[] getDayList(String dateFrom, String dateEnd){
		long time = 1l;
		long perDayMilSec = 24 * 60 * 60 * 1000;
		List<String> dateList = new ArrayList<String>();
		dateList.add(dateFrom);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		while (true) {
			try {
				time = sdf.parse(dateFrom).getTime();
				time = time + perDayMilSec;
				dateFrom = sdf.format(new Date(time));
				if (dateFrom.compareTo(dateEnd) < 0) {
					dateList.add(dateFrom);
				} else {
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
		}
		dateList.add(dateEnd);
		String[] dateArray = new String[dateList.size()];
		dateList.toArray(dateArray);
		return dateArray;

	}

	/**
	 * 取得map中的真实值
	 * @param map
	 * @param key
	 * @return
	 */
	public static String getValue(Map<String, Object> map, String key) {
		Object obj = map.get(key);
		if(obj == null)
			return null;
		else if(!obj.getClass().isArray())
			return obj.toString();
		else
			return Arrays.toString((Object[])obj).replace("[", "").replace("]", "");
	}


	public static String getTableName(String name){
		if(name != null){
			String categoryName = name.replace(" ", "").replace("&#39;", "_").replace(",", "").replace("-", "_").replace("&amp;", "").replace("&", "");
			return Constant.CATEGORY+"_"+categoryName;
		}
		return name;
	}


	public static String getTableNameById(Integer pid,
										  List<CaijiCategoryEntity> categoryEntities) {
		for(CaijiCategoryEntity category:categoryEntities){
			if(category.getId() == pid){
				if(isRoot(category, categoryEntities)){
					String categoryName = category.getCName().replace(" ", "").replace("&#39;", "_").replace(",", "").replace("-", "_").replace("&amp;", "");
					return Constant.CATEGORY+"_"+categoryName;
				}else{
					getTableNameById(category.getPid(), categoryEntities);
				}
			}
		}
		return null;
	}


	//判断是否是一级目录， 即它的父级是pid为0的类目。
	public static boolean isRoot(CaijiCategoryEntity categoryEntity,
								 List<CaijiCategoryEntity> categoryEntities){
		for(CaijiCategoryEntity category:categoryEntities){
			if(category.getPid() == 0 && category.getId() == categoryEntity.getPid()){
				return true;
			}
		}
		return false;
	}

	/**
	 * @author LuoB.
	 * @param oldTime 较小的时间
	 * @param newTime 较大的时间 (如果为空   默认当前时间 ,表示和当前时间相比)
	 * @return -1 ：同一天.    0：昨天 .   1 ：至少是前天.
	 * @throws ParseException 转换异常
	 */
	public static int isYeaterday(Date oldTime,Date newTime) throws ParseException{
		if(newTime==null){
			newTime=new Date();
		}
		//将下面的 理解成  yyyy-MM-dd 00：00：00 更好理解点
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String todayStr = format.format(newTime);
		Date today = format.parse(todayStr);
		//昨天 86400000=24*60*60*1000 一天
		if(today == null){
			return 0;
		}
		if((today.getTime()-oldTime.getTime())>0 && (today.getTime()-oldTime.getTime())<=86400000) {
			return 0;
		}
		else if((today.getTime()-oldTime.getTime())<=0){ //至少是今天
			return -1;
		}
		else{ //至少是前天
			return 1;
		}

	}


	/**
	 * @author LuoB.
	 * @param oldTime 较小的时间
	 * @param newTime 较大的时间 (如果为空   默认当前时间 ,表示和当前时间相比)
	 * @return -1 ：同一天.    0：昨天 .   1 ：至少是前天.
	 * @throws ParseException 转换异常
	 */
	public static int passTime(Date oldTime,Date newTime) throws ParseException{
		if(newTime==null){
			newTime=new Date();
		}
		//将下面的 理解成  yyyy-MM-dd 00：00：00 更好理解点
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String todayStr = format.format(newTime);
		Date today = format.parse(todayStr);
		//昨天 86400000=24*60*60*1000 一天
		if(today == null){
			return 0;
		}
		if((today.getTime()-oldTime.getTime())>0 && (today.getTime()-oldTime.getTime())<=86400000) {
			return 0;
		} else if((today.getTime()-oldTime.getTime())<=172800000){
			return 1;
		} else if((today.getTime()-oldTime.getTime())<=259200000){
			return 2;
		}  else{
			return 3;
		}

	}



	/**
	 * @author LuoB.
	 * @param oldTime 较小的时间
	 * @param newTime 较大的时间 (如果为空   默认当前时间 ,表示和当前时间相比)
	 * @return -1 ：同一天.    0：昨天 .   1 ：至少是前天.
	 * @throws ParseException 转换异常
	 */
	public static boolean isToday(Date oldTime,Date newTime) throws ParseException{
		if(newTime==null){
			newTime=new Date();
		}
		long create = oldTime.getTime();
		Calendar now = Calendar.getInstance();
		long ms  = 1000*(now.get(Calendar.HOUR_OF_DAY)*3600+now.get(Calendar.MINUTE)*60+now.get(Calendar.SECOND));//毫秒数
		long ms_now = now.getTimeInMillis();
		if(ms_now-create<ms){
			return true;
		}
		return false;
	}


	public static void download(){
		int bytesum = 0;
		int byteread = 0;

		URL url = null;
		try {
			url = new URL("http://120.26.228.123/product.zip");
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}

		try {
			URLConnection conn = url.openConnection();
			InputStream inStream = conn.getInputStream();
			FileOutputStream fs = new FileOutputStream("d:/product_base.zip");

			byte[] buffer = new byte[1204];
			int length;
			while ((byteread = inStream.read(buffer)) != -1) {
				bytesum += byteread;
//                System.out.println(bytesum);
				fs.write(buffer, 0, byteread);
			}
		} catch (FileNotFoundException e) {
			download();
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * 解压文件到指定目录
	 * @param zipFile
	 * @param descDir
	 * @author isea533
	 */
	@SuppressWarnings("rawtypes")
	public static void unZipFiles(File zipFile,String descDir)throws IOException{
		File pathFile = new File(descDir);
		if(!pathFile.exists()){
			pathFile.mkdirs();
		}
		ZipFile zip = new ZipFile(zipFile);
		for(Enumeration entries = zip.entries();entries.hasMoreElements();){
			ZipEntry entry = (ZipEntry)entries.nextElement();
			InputStream in = zip.getInputStream(entry);
			String outPath = descDir+"/product_base.sql";
			//判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
			if(new File(outPath).isDirectory()){
				continue;
			}
			//输出文件路径信息
			System.out.println(outPath);

			OutputStream out = new FileOutputStream(outPath);
			byte[] buf1 = new byte[1024];
			int len;
			while((len=in.read(buf1))>0){
				out.write(buf1,0,len);
			}
			in.close();
			out.close();
		}
		System.out.println("******************解压完毕********************");
	}


	public static void runSqlFile(String fileName){
		try {
			Class.forName(Constant.driver);
			Connection conn = DriverManager.getConnection(Constant.url_service, Constant.user, Constant.password);
			ScriptRunner runner = new ScriptRunner(conn);
			runner.setAutoCommit(true);

			File file = new File(fileName);

			try {
				if (file.getName().endsWith(".sql")) {
					runner.setFullLineDelimiter(true);
					runner.setDelimiter("##");
					runner.setSendFullScript(false);
					runner.setAutoCommit(true);
					runner.setStopOnError(true);
					runner.runScript(new InputStreamReader(new FileInputStream(fileName),"UTF-8"));
				}
			}catch(Exception e){
				e.printStackTrace();
			}

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 读取 SQL 文件，获取 SQL 语句
	 * @param sqlFile SQL 脚本文件
	 * @return List<sql> 返回所有 SQL 语句的 List
	 * @throws Exception
	 */
	private List<String> loadSql(String sqlFile) throws Exception {
		List<String> sqlList = new ArrayList<String>();

		try {
			InputStream sqlFileIn = new FileInputStream(sqlFile);

			StringBuffer sqlSb = new StringBuffer();
			byte[] buff = new byte[1024];
			int byteRead = 0;
			while ((byteRead = sqlFileIn.read(buff)) != -1) {
				sqlSb.append(new String(buff, 0, byteRead));
			}

			// Windows 下换行是 /r/n, Linux 下是 /n
			String[] sqlArr = sqlSb.toString().split("(;//s*//r//n)|(;//s*//n)");
			for (int i = 0; i < sqlArr.length; i++) {
				String sql = sqlArr[i].replaceAll("--.*", "").trim();
				if (!sql.equals("")) {
					sqlList.add(sql);
				}
			}
			return sqlList;
		} catch (Exception ex) {
			throw new Exception(ex.getMessage());
		}
	}



	/**
	 * 自建连接，独立事物中执行 SQL 文件
	 * @param sqlFile SQL 脚本文件
	 * @throws Exception
	 */
	public static void execute(String sqlFile) throws Exception {
		try {

			Class.forName(Constant.driver).newInstance();
			Connection conn = (Connection) DriverManager.getConnection(Constant.url_service, Constant.user, Constant.password);
			ScriptRunner runner = new ScriptRunner(conn);
			runner.setErrorLogWriter(null);
			runner.setLogWriter(null);
			runner.runScript(new InputStreamReader(new FileInputStream(sqlFile), "UTF-8"));
//			FileReader read = new FileReader(sqlFile);
//			System.out.println(read.toString());
//			runner.runScript(read);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/************************************************************************************************/
	public static void setCreateTime(StoreEntity store) {
		try {
			URL serverUrl = new URL("http://m.aliexpress.com/store/sellerInfo.htm?sellerAdminSeq="+store.getSellerAdminSeq());
			HttpURLConnection conn = (HttpURLConnection) serverUrl
					.openConnection();
			conn.setRequestProperty("Cookie", "ali_apache_id=11.227.7.27.1470909775656.996174.4; ali_beacon_id=11.227.7.27.1470909775656.996174.4; cna=3w4yEJlbElUCATEsOe/ZBDy0; xman_f=Lmv687wW3+STPgGCZizbYhfTwVFMfSpu6/c/5A/g4Szvf64+h3ohfM5EnjLaVSxpU4WoBYjDeYCZwG0sH9fig+gpgEruFqWF4D9KF51dx+g2x/DUzw5SCQ==; __utma=3375712.787323053.1470910075.1470910241.1470916656.2; __utmz=3375712.1470916656.2.2.utmcsr=aliexpress.com|utmccn=(referral)|utmcmd=referral|utmcct=/; aep_history=keywords%5E%0Akeywords%09%0A%0Aproduct_selloffer%5E%0Aproduct_selloffer%0932698265016%0932703762672%091861300775%09997626966%09997470494; _ga=GA1.2.787323053.1470910075; acs_usuc_t=acs_rt=f026c597232a44169b19a2e6139598e7; xman_t=R7l4/FiF/8uPM7BxCSz/iYRr3ez3LSJdTPnY0BkCszqD1aEeD8dBU78LppRsnJIZ; JSESSIONID=5A057AD4A08A9251F2C103645DEAE749; ali_apache_track=; ali_apache_tracktmp=; xman_us_f=x_l=1&x_locale=en_US; aep_usuc_f=site=glo&region=IN&b_locale=en_US&c_tp=USD; intl_locale=en_US; intl_common_forever=J57DxG1KiTM3eHvgl1k4LVq+/CiqLmMIYqgy3DEGyakbqmoz8QXoqg==; l=AvDwKoL9ISHIvy7WJmW7lpQ6QLRC5NSD; isg=Ak9Pl3D3ROt3V0CWHPUFBtze3uMY9S5SS-kb3mFcNr7FMG8yaEQz5k0uBNd0");
			conn.setRequestMethod("GET");
			conn.addRequestProperty("X-Forwarded-For", "");
			BufferedReader br = null;
			conn.setRequestProperty("X-Requested-With", "XMLHttpRequest");
			br = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "utf-8"));
			String strRead = "";
			String createTime = "";
			String storeName = "";
			String threeMonthFeedback = "";
			String timeRegular= "<div[^>]*>([^<]*)</div>";
			String feedbackRegular= "<td[^>]*>([^<]*)</td>";
			boolean start = false;
			GroupMethod gMethod = new GroupMethod();
			while ((strRead = br.readLine()) != null) {
				if(strRead.contains("ms-seller-name")){
					storeName = gMethod.regularGroup(timeRegular,strRead);
					store.setStoreName(storeName);
				}
				if(strRead.contains("ms-seller-since")){
					SimpleDateFormat sdf1 = new SimpleDateFormat("dd MMM yyyy", Locale.US);
					SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
					createTime = gMethod.regularGroup(timeRegular,strRead);
					createTime = createTime.replace("Fournisseur Depuis ", "").replace("Supplier Since&nbsp; ","");
					createTime = sdf2.format(sdf1.parse(createTime));
					store.setCreateTime(createTime);
					System.out.println(createTime);
				}
				if(start){
					if (strRead.contains("<tbody>")) {
						br.readLine();
						br.readLine();
						strRead = br.readLine();
						threeMonthFeedback = gMethod.regularGroup(feedbackRegular,strRead);
						System.out.println(threeMonthFeedback);
						store.setThreeFeedback(threeMonthFeedback);
						start = false;
						return;
					}
				}
				if (strRead.contains("feedback-history")) {
					start = true;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}

	public static void setProductNum(StoreEntity store){
		try {
			String urlstr = "https://m.aliexpress.com/product/ajax/sellerStatisticsInfo.do?sellerAdminSeq=" + store.getSellerAdminSeq() + "&sellerCompanyId=" + store.getSellerCompanyId();
			HttpURLConnection conn = (HttpURLConnection) new URL(urlstr)
					.openConnection();
			BufferedReader br = null;
			try {
				conn.addRequestProperty("X-Forwarded-For", "");
				conn.setRequestProperty("X-Requested-With", "XMLHttpRequest");
				br = new BufferedReader(new InputStreamReader(
						conn.getInputStream(), "utf-8"));
			} catch (Exception e) {

			}
			String jsonString = "";
			String strRead = "";
			while ((strRead = br.readLine()) != null) {
				jsonString += strRead;
			}
			StoreBean storeBean = (StoreBean) JsonPluginsUtil
					.jsonToBean(jsonString, StoreBean.class);
			store.setCollectNum(storeBean.getWishlistCount());//收藏量
			store.setTotalNum(storeBean.getItemsCount());//产品数
			FeedbackInfo feedbackInfo = storeBean.getFeedbackInfo();
			store.setLevel(feedbackInfo.getSellerLevel());//店铺等级
			store.setStoreFacebackper(feedbackInfo.getSellerPositiveRate());//好评率
			store.setStoreFaceback(feedbackInfo.getSellerScore() + "");//好评分
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public static void setSellerAdminSeq(StoreEntity store) {

		try {
			URL serverUrl = new URL("http://m.aliexpress.com/store/storeHome.htm?storeNo="+store.getStoreId());
			HttpURLConnection conn = (HttpURLConnection) serverUrl
					.openConnection();
			conn.setRequestProperty("Cookie", "ali_apache_id=11.227.7.27.1470909775656.996174.4; ali_beacon_id=11.227.7.27.1470909775656.996174.4; cna=3w4yEJlbElUCATEsOe/ZBDy0; xman_f=Lmv687wW3+STPgGCZizbYhfTwVFMfSpu6/c/5A/g4Szvf64+h3ohfM5EnjLaVSxpU4WoBYjDeYCZwG0sH9fig+gpgEruFqWF4D9KF51dx+g2x/DUzw5SCQ==; __utma=3375712.787323053.1470910075.1470910241.1470916656.2; __utmz=3375712.1470916656.2.2.utmcsr=aliexpress.com|utmccn=(referral)|utmcmd=referral|utmcct=/; aep_history=keywords%5E%0Akeywords%09%0A%0Aproduct_selloffer%5E%0Aproduct_selloffer%0932698265016%0932703762672%091861300775%09997626966%09997470494; _ga=GA1.2.787323053.1470910075; acs_usuc_t=acs_rt=f026c597232a44169b19a2e6139598e7; xman_t=R7l4/FiF/8uPM7BxCSz/iYRr3ez3LSJdTPnY0BkCszqD1aEeD8dBU78LppRsnJIZ; JSESSIONID=5A057AD4A08A9251F2C103645DEAE749; ali_apache_track=; ali_apache_tracktmp=; xman_us_f=x_l=1&x_locale=en_US; aep_usuc_f=site=glo&region=IN&b_locale=en_US&c_tp=USD; intl_locale=en_US; intl_common_forever=J57DxG1KiTM3eHvgl1k4LVq+/CiqLmMIYqgy3DEGyakbqmoz8QXoqg==; l=AvDwKoL9ISHIvy7WJmW7lpQ6QLRC5NSD; isg=Ak9Pl3D3ROt3V0CWHPUFBtze3uMY9S5SS-kb3mFcNr7FMG8yaEQz5k0uBNd0");
			conn.setRequestMethod("GET");
			conn.addRequestProperty("X-Forwarded-For", "");
			BufferedReader br = null;
			conn.setRequestProperty("X-Requested-With", "XMLHttpRequest");
			br = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "utf-8"));
			String strRead = "";
			String sellerAdminSeq = "";
			String sellerCompanyId = "";
			String regular= "value=\"([^\"]*)\"";
			boolean start = false;
			GroupMethod gMethod = new GroupMethod();
			while ((strRead = br.readLine()) != null) {
				if(strRead.contains("companyId")){
					start = true;
					sellerCompanyId = gMethod.regularGroup(regular,strRead);
					store.setSellerCompanyId(sellerCompanyId);
				}
				if (start && strRead.contains("var sellerAdminSeq=")) {
					sellerAdminSeq  = strRead.replace("var sellerAdminSeq=", "").replace(";", "");
					store.setSellerAdminSeq(sellerAdminSeq);
					start = false;
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}

	/**
	 * .
	 *
	 * @Funtion Description：
	 */
	public static boolean isNullOrEmpty(final Object str) {
		boolean result = false;
		if (str == null || "null".equals(str) || "".equals(str.toString().trim())) {
			result = true;
		}
		return result;
	}

	/***
	 * 去掉字符串前后的空间，中间的空格保留
	 * @param str
	 * @return
	 */
	public static String trimInnerSpaceStr(String str){
		str = str.trim();
		while(str.startsWith(" ")){
			str = str.substring(1,str.length()).trim();
		}
		while(str.endsWith(" ")){
			str = str.substring(0,str.length()-1).trim();
		}
		return str;
	}

	public static void main(String args[]){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			System.out.println(isYeaterday(sdf.parse("2015-11-30 10:18:54"),new Date()));
			try {
				long start = System.currentTimeMillis();
				execute("F:/采集/数据库/product_base.sql");
				System.out.println((System.currentTimeMillis()-start)+"s");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

