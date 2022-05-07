package org.go.traffic.service;

import org.go.traffic.model.AccidentDTO;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

@Service
public class AccidentServiceImpl implements AccidentService{

	@Override
	public AccidentDTO tempSave(String totalVal) {
		
		AccidentDTO dto = new AccidentDTO();
		
		JSONParser jsonParser = new JSONParser();
        
        //3. To Object
        Object obj;
		try {
			obj = jsonParser.parse(totalVal);
	        JSONObject jsonObj = (JSONObject) obj;
			/*
			 * Json으로 변환 된 Key와 Value 가져오는 방법. 
			 * String useVar = jsonObj.get("Key")
			 */
	        System.out.println("json으로 변환된 값 확인 : " + jsonObj);
	        String accidentDate = (String) jsonObj.get("occrrnc_dt");
	        String dayOfWeek = (String) jsonObj.get("occrrnc_day_cd");
	        Long deathCount = (Long) jsonObj.get("dth_dnv_cnt");
	        Long injureCount = (Long) jsonObj.get("injpsn_cnt");
	        String latitude = (String) jsonObj.get("la_crd");
	        String longitude = (String) jsonObj.get("lo_crd");
	        
	        System.out.println("accidentDate 키 값 확인 : " + accidentDate);
	        System.out.println("dayOfWeek 키 값 확인 : " + dayOfWeek);
	        System.out.println("deathCount 키 값 확인 : " + deathCount);
	        System.out.println("injureCount 키 값 확인 : " + injureCount);
	        System.out.println("latitude 키 값 확인 : " + latitude);
	        System.out.println("longitude 키 값 확인 : " + longitude);
	        
	        dto.setAccidentDate(accidentDate);
	        dto.setDayOfWeek(dayOfWeek);
	        dto.setDeathCount(deathCount);
	        dto.setInjureCount(injureCount);
	        dto.setLatitude(latitude);
	        dto.setLongitude(longitude);
	        
	        return dto;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return dto;
	}

}
