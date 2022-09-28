package com.spring.javagreenS;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/qrcode")
public class StudyController {
	
	// QR코드 생성하기 폼(URL 등록폼)
	@RequestMapping(value="/qrCode", method=RequestMethod.GET)
	public String qrCodeGet() {
		return "study/qrCode/qrCode";
	}
	
	// QR코드 생성하기 처리부분
	@SuppressWarnings("deprecation")
	@ResponseBody
	@RequestMapping(value="/qrCreate", method=RequestMethod.POST)
	public String barCreatePost(HttpServletRequest request, HttpSession session, String moveUrl) {
		String mid = (String) session.getAttribute("sMid");
		String uploadPath = request.getRealPath("/resources/data/qrCode/");
		String qrCodeName = studyService.qrCreate(mid, uploadPath, moveUrl);	// qr코드가 저장될 서버경로와 qr코드 찍었을때 이동할 url을 서비스객체로 넘겨서 qr코드를 생성하게 한다.
		
    return qrCodeName;
	}
	
}










