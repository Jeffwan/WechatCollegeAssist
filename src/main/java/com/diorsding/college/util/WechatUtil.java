package com.diorsding.college.util;

import java. io.InputStream;
import java.io.Writer;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.diorsding.college.model.Article;
import com.diorsding.college.model.ExamMark;
import com.diorsding.college.model.Message;
import com.diorsding.college.model.Reply;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

public class WechatUtil {

	public static String singleExamMarkToString(ExamMark em) {
		if (em == null || em.getExam() == null) {
			return null;
		}
		
		StringBuilder sb = new StringBuilder();
		return sb.toString();
	}
	
	
	public static XStream xstream = new XStream(new XppDriver() {
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out) {
				// add CDATA tag
				boolean cdata = true;
				
				public void startNode(String name, Class clazz) {
					super.startNode(name, clazz);
				}
				
				protected void writeText(QuickWriter writer, String text) {
					if (cdata) {
						writer.write("<![CDATA[>");
						writer.write(text);
						writer.write("]]>");
					} else {
						writer.write("]]>");
					}
				}
				
			};
		}
	});
	
	
	public static String replyToXml(Reply reply) {
		String type = reply.getMsgType();
		
		if (Reply.TEXT.equals(type)) {
			xstream.omitField(Reply.class, "articles");
			xstream.omitField(Reply.class, "articleCount");
			xstream.omitField(Reply.class, "musicUrl");
			xstream.omitField(Reply.class, "hQMusicUrl");
		} else if(Reply.MUSIC.equals(type)){
			xstream.omitField(Reply.class, "articles");
			xstream.omitField(Reply.class, "articleCount");
			xstream.omitField(Reply.class, "content");
		}else if(Reply.NEWS.equals(type)){
			xstream.omitField(Reply.class, "content");
			xstream.omitField(Reply.class, "musicUrl");
			xstream.omitField(Reply.class, "hQMusicUrl");
		}
		
		xstream.autodetectAnnotations(true);
		xstream.alias("xml", reply.getClass());
		xstream.alias("item", new Article().getClass());
		
		return xstream.toXML(reply);
	}
	
	public static Message mapToMessage(Map<String,String> map){
		if (map == null) {
			return null;
		}
		
		String msgType = map.get("MsgType");
		Message message = new Message();
		message.setToUserName(map.get("ToUserName"));
		message.setFromUserName(map.get("FromUserName"));
		message.setCreateTime(new Date(Long.parseLong(map.get("CreateTime"))));
		message.setMsgType(msgType);
		message.setMsgId(map.get("MsgId"));
		
		if(msgType.equals(Message.TEXT)){
			message.setContent(map.get("Content"));
		}else if(msgType.equals(Message.IMAGE)){
			message.setPicUrl(map.get("PicUrl"));
		}else if(msgType.equals(Message.LINK)){
			message.setTitle(map.get("Title"));
			message.setDescription(map.get("Description"));
			message.setUrl(map.get("Url"));
		}else if(msgType.equals(Message.LOCATION)){
			message.setLocationX(map.get("Location_X"));
			message.setLocationY(map.get("Location_Y"));
			message.setScale(map.get("Scale"));
			message.setLabel(map.get("Label"));
		}else if(msgType.equals(Message.EVENT)){
			message.setEvent(map.get("Event"));
			message.setEventKey(map.get("EventKey"));
		}
		
		return message;
	}
	
	public static Map<String, String> parseXml(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		InputStream inputStream;
		
		try {
			inputStream = request.getInputStream();
			SAXReader reader = new SAXReader();
			Document docutment = reader.read(inputStream);
			Element root = docutment.getRootElement();
			
			List<Element> elementList = root.elements();
			for (Element e : elementList) {
				map.put(e.getName(), e.getText());
			}
			
			System.out.println(map);
			
			inputStream.close();
			inputStream = null;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return map;
	}
	
	
	public static String sha1(String key) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA1");
			md.update(key.getBytes());
			String pwd = new BigInteger(1, md.digest()).toString(16);
			
			return pwd;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return key;
		}
		
	}
}
