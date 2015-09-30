package org.jingxuan.util;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.dom4j.CDATA;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.alibaba.fastjson.JSON;

/**
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>公司名称 : 北京彩通农旗资讯有限公司</p>
 * <p>项目名称 : test</p>
 * <p>创建时间 : 2015年9月9日 下午5:52:34</p>
 * <p>类描述 : test类</p>
 *
 * @author lixinran
 */
public class Test {
	private static final String CONSTSTRING = "<p>fat cat</p>,<p> fat cat, fat cat, I like.</p>";
	
	private void parseXml(){
		try {
			SAXReader saxReader = new SAXReader();
			InputStream is = Test.class.getClassLoader().getResourceAsStream("resource/index.xml");
			Document document = saxReader.read(is);
			//头部
			Element root = document.getRootElement();
			String rootStr = root.getText();
			List<Node> branchs = root.content();
			//Node header = document.selectSingleNode("header_web");
			Element header = root.element("header_web");
			int index = branchs.indexOf(header);
			CDATA cdata = DocumentHelper.createCDATA("header");
			branchs.set(index, cdata);
			
			Element app = root.element("app_web");
			String appStr = app.getText();
			Element app_a = root.element("app_a");
			if(app_a==null){
				app_a = app.element("app_a");
			}
			String app_aStr = app_a.getText();
			
			for(Node n:branchs){
				System.out.println(n.getText());
			}
			List<Element> eles = header.elements();
			String headerStr = header.getText();
			headerStr = headerStr.replace("", "");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private void parseJson(){
		List<NameValuePair> values = new ArrayList<NameValuePair>();
		values.add(new BasicNameValuePair("room_id", "1111"));
		values.add(new BasicNameValuePair("room_jid", "111@fsafdsa.com"));
		values.add(new BasicNameValuePair("room_name", "room name"));
		values.add(new BasicNameValuePair("mynickname", "nickname"));
		System.out.println(JSON.toJSONString(values));
		
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("room_id", "lixinran");
		map.put("room_jid", "lixinran");
		List<Map<String, String>> members = new ArrayList<Map<String,String>>();
		
		Map<String, String> member1 = new HashMap<String,String>();
		member1.put("member_id", "张三");
		member1.put("member_name", "vh");
		members.add(member1);
		Map<String, String> member2 = new HashMap<String,String>();
		member2.put("member_id", "lisi");
		member2.put("member_name", "vh");
		members.add(member2);
		map.put("members", members);
		System.out.println(JSON.toJSONString(map));
	}
	
	public static void main(String[] args) throws Exception{
		
		String a = ",11,,";
		String[] aArr = a.split(",",-1);
		System.out.println(aArr.length);
		
		Test test = new Test();
		
		test.parseJson();
		
		test.parseXml();
		
		String regex = "<[^>]*>";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(CONSTSTRING);
		System.out.println("<dfafa>sd12141>".matches(regex));
		
		//replaceFirst()只替换匹配到的第一个地方
		System.out.println(m.replaceAll(""));
		System.out.println(CONSTSTRING.replaceAll(regex, ""));
		
		String testStr = "prolist_i";
		//String regex_prolist = "prolist[\\d]*_i";
		String regex_prolist = "prolist\\d{1,2}_i";
		p = Pattern.compile(regex_prolist);
		m = p.matcher(testStr);
		System.out.println(m.matches());
		System.out.println(testStr.matches(regex_prolist));
		
		String str = "[\"1\",\"2\",\"3\"]";
		regex = "[\\[\"\\]]";
		System.out.println(str.replaceAll(regex, ""));
		
		str = "<li style=\"${qqBut_i_display}\"><li style=\"${phoneBut_i_display}\">";
		//regex = "(\\{^\\}*display\\})";
		regex = "\\$\\{[^\\}]*display\\}";
		System.out.println(str.replaceAll(regex, ""));
		
		str = "test-----test--------";
		regex = "test";
		System.out.println(str.replace(regex, ""));
		
		str = "　　    	------     fsafsfs";
		regex = "[\\s\u3000]*";
		System.out.println(str.replaceAll(regex, ""));
		System.out.println(str.replaceFirst(regex, ""));
		
		//windows根目录
		String url = "/test_dir";
		File[] files = new File(url).listFiles();
		System.out.println(files.length);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", new Integer(10));
		Integer i = (Integer) map.get("id");
		System.out.println(i.toString());
	}
}
