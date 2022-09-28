package com.spring.javagreenS.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.javagreenS.vo.KakaoAreaVO;
import com.spring.javagreenS.vo.ChartVO;
import com.spring.javagreenS.vo.KakaoAddressVO;
import com.spring.javagreenS.vo.OperatorVO;
import com.spring.javagreenS.vo.PersonVO;

public interface QRDAO {
	public void setQrCreate(@Param("qrCodeName") String qrCodeName);

}






























