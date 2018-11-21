package cn.bdqn.controller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;

@Controller
public class UploadController {
	
	//显示upload.html
	@RequestMapping("/upload.html")
	public String showUpload(){
		return "upload";
	}
	
	//处理上传
	@RequestMapping("/doupload.html")
	public String doUpload(
			String userName,HttpSession session,
			@RequestParam(value="pic")
			MultipartFile[] pic,Model model){
		//System.out.println("上传人："+userName);
		//String path="E:\\Upload";
		String ret="success";
		String path=session.getServletContext().getRealPath("/images/");
		for(MultipartFile mf:pic){
			String oldFileName=mf.getOriginalFilename();
			String suffix=oldFileName.substring(oldFileName.lastIndexOf(".")+1);
			List<String> listTypes=Arrays.asList("gif","png","jpg");
			//System.out.println(oldFileName);
			if(listTypes.contains(suffix)){
				File fileTo=new File(path,oldFileName);
				try {
					mf.transferTo(fileTo);
					
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					ret="error";
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					ret="error";
				}
			}else{
				String msg="后缀不对";
				model.addAttribute("msg",msg);
				return "upload";
			}
		}
		
		return ret;
	}
}
