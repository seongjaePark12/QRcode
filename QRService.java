package com.spring.javagreenS.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.spring.javagreenS.vo.KakaoAreaVO;
import com.spring.javagreenS.vo.ChartVO;
import com.spring.javagreenS.vo.KakaoAddressVO;
import com.spring.javagreenS.vo.OperatorVO;
import com.spring.javagreenS.vo.PersonVO;

public interface QRService {
	public String qrCreate(String mid, String uploadPath, String moveUrl);

}



























