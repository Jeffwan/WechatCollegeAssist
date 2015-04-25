package com.diorsding.college.web.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.diorsding.college.model.Constants;
import com.diorsding.college.model.Message;
import com.diorsding.college.model.Reply;
import com.diorsding.college.service.WechatService;
import com.diorsding.college.util.WechatUtil;

@Controller
@Scope("prototype")
public class WechatController {

	@Resource(name="wechatService")
	private WechatService wechatService; 
	
	@RequestMapping(value="/wechat", method=RequestMethod.POST, produces="text/html;charset=UTF-8")
	@ResponseBody
	public String replyMessage(HttpServletRequest request) {
		if (checkWechatRequest(request)) {
			Map<String, String> requestMap = WechatUtil.parseXml(request);
			Message message = WechatUtil.mapToMessage(requestMap);
			wechatService.addMessage(message);//保存接受消息到数据库
			String replyContent = Reply.WELCOME_CONTENT;
			String type = message.getMsgType();
			if (type.equals(Message.TEXT)) {//仅处理文本回复内容
				String content = message.getContent();//消息内容
				String [] cs = content.split("_");//消息内容都以下划线_分隔
				if(cs.length == 2){
					int studentid ;//学生编号
					String process = cs[1];//操作
					try {
						studentid = Integer.parseInt(cs[0]);
						if("考试".equals(process)){
							replyContent = wechatService.getSingleExamMarkStringByStudentId(studentid);
						}else if("考试历史".equals(process)){
							replyContent = wechatService.getExamMarkHistoryStringByStudentId(studentid);
						}else if("留言".equals(process)){
							replyContent = wechatService.getSingleStudentMessageByStudentId(studentid);
						}else if("留言历史".equals(process)){
							replyContent = wechatService.getStudentMessageHistoryByStudentId(studentid);
						}else if("动态".equals(process)){
							replyContent = wechatService.getSingleClassesNewsByStudentId(studentid);
						}else if("动态历史".equals(process)){
							replyContent = wechatService.getClassesNewsHistoryByStudentId(studentid);
						} 
					} catch (NumberFormatException e) {
						replyContent = Reply.ERROR_CONTENT;
					}
				}
			}
			//拼装回复消息
			Reply reply = new Reply();
			reply.setToUserName(message.getFromUserName());
			reply.setFromUserName(message.getToUserName());
			reply.setCreateTime(new Date());
			reply.setMsgType(Reply.TEXT);
			reply.setContent(replyContent);
			wechatService.addReply(reply);//保存回复消息到数据库
			//将回复消息序列化为xml形式
			String back = WechatUtil.replyToXml(reply);
			System.out.println(back);
			
			return back; 
		} else {
			return "error";
		}
		
	}
	
	//微信公众平台验证url是否有效使用的接口
		@RequestMapping(value="/wechat",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
		@ResponseBody
		public String initWeixinURL(HttpServletRequest request){
			String echostr = request.getParameter("echostr");
			if (checkWechatRequest(request) && echostr != null) {
				return echostr;
			}else{
				return "error";
			}
		}
		
		private static boolean checkWechatRequest(HttpServletRequest request){
			String signature = request.getParameter("signature");
			String timestamp = request.getParameter("timestamp");
			String nonce = request.getParameter("nonce");
			
			if (signature != null && timestamp != null && nonce != null ) {
				String[] strSet = new String[] { Constants.TOKEN, timestamp, nonce };
				java.util.Arrays.sort(strSet);
				String key = "";
				for (String string : strSet) {
					key = key + string;
				}
				String pwd = WechatUtil.sha1(key);
				
				return pwd.equals(signature);
			}else {
				return false;
			}
		}
		
		@RequestMapping(value="/manager/messages",method=RequestMethod.GET)
		public ModelAndView listMessage(String pageNum){
			ModelAndView mv = new ModelAndView();
			mv.addObject("sidebar","messages");
			mv.setViewName("messages");
			
			int num = 1;
			if(pageNum != null){
				num = Integer.parseInt(pageNum);
			}
			
			List<Message> list = wechatService.listMessage((num-1) * Constants.pageSize, Constants.pageSize);
			mv.addObject("messageList", list);
			mv.addObject("pageNum", num);
			mv.addObject("length", list.size());
			return mv;
		}
		
		@RequestMapping(value="/manager/replys",method=RequestMethod.GET)
		public ModelAndView listReply(String pageNum){
			ModelAndView mv = new ModelAndView();
			mv.addObject("sidebar","replys");
			mv.setViewName("replys");
			
			int num = 1;
			if(pageNum != null){
				num = Integer.parseInt(pageNum);
			}
			
			List<Reply> list = wechatService.listReply((num-1)* Constants.pageSize, Constants.pageSize);
			mv.addObject("replyList", list);
			mv.addObject("pageNum", num);
			mv.addObject("length", list.size());
			return mv;
		}
}
