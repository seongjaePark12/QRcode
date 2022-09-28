package com.spring.javagreenS.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;-
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.google.zxing.BarcodeFormat;-
import com.google.zxing.client.j2se.MatrixToImageConfig;-
import com.google.zxing.client.j2se.MatrixToImageWriter;-
import com.google.zxing.common.BitMatrix;-
import com.google.zxing.qrcode.QRCodeWriter;-
import com.spring.javagreenS.dao.StudyDAO;-


@Service
public class StudyServiceImpl implements StudyService {

	@Autowired
	QRDAO qrDAO;

	@Override
	public String qrCreate(String mid, String uploadPath, String moveUrl) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmm");
		UUID uid = UUID.randomUUID();
		String strUid = uid.toString().substring(0,4);
		String qrCodeName = "";
		
		if(moveUrl.indexOf("@") == -1) {	// 이동할 주소로 넘어올때의 처리
			qrCodeName = sdf.format(new Date()) + "_" + mid + "_" + strUid;
		}
		else {		// 개인정보(이메일주소)로 넘어올때의 처리
			qrCodeName = sdf.format(new Date()) + "_" + mid + "_" + moveUrl + "_" + strUid;
		}
	  try {
	      File file = new File(uploadPath);		// qr코드 이미지를 저장할 디렉토리 지정
	      if(!file.exists()) {
	          file.mkdirs();
	      }
	      String codeurl = new String(moveUrl.getBytes("UTF-8"), "ISO-8859-1");	// qr코드 인식시 이동할 url 주소
	      //int qrcodeColor = 0xFF2e4e96;			// qr코드 바코드 생성값(전경색)
	      int qrcodeColor = 0xFF000000;			// qr코드 바코드 생성값(전경색) - 뒤의 6자리가 색상코드임
	      int backgroundColor = 0xFFFFFFFF;	// qr코드 배경색상값
	      
	      QRCodeWriter qrCodeWriter = new QRCodeWriter();
	      BitMatrix bitMatrix = qrCodeWriter.encode(codeurl, BarcodeFormat.QR_CODE,200, 200);
	      
	      MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig(qrcodeColor,backgroundColor);
	      BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix,matrixToImageConfig);
	      
	      ImageIO.write(bufferedImage, "png", new File(uploadPath + qrCodeName + ".png"));		// ImageIO를 사용한 바코드 파일쓰기
	      
	      // qr코드 생성후 정보를 DB에 저장하기(신상내역으로 보낸것들만 저장하려함 - 나중에 본인이 생성된 qr코드 가져왔을때 DB에 있는 정보와 일치하는지 알아보기 위함)
	      if(qrCodeName.indexOf("@") != -1) qrDAO.setQrCreate(qrCodeName);
	  } catch (Exception e) {
	      e.printStackTrace();
	  }
	  return qrCodeName;
	}	

}
