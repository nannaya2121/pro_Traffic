package org.go.traffic.model;

import lombok.Data;

@Data
public class AccidentDTO {

	// 발생연월시
	private String accidentDate;

	// 발생요일
	private String dayOfWeek;

	// 사망자수
	private Long deathCount;

	// 부상자수
	private Long injureCount;

	// 가해자 법규 위반 코드
	private String violationName;

	// 위도 좌표
	private String latitude;

	// 경도 좌표
	private String longitude;

}
