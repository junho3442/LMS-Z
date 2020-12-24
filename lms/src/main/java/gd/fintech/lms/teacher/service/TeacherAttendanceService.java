package gd.fintech.lms.teacher.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gd.fintech.lms.teacher.mapper.TeacherAttendanceMapper;
import gd.fintech.lms.vo.Attendance;

@Service
@Transactional
public class TeacherAttendanceService {

	@Autowired TeacherAttendanceMapper teacherAttendanceMapper;
	
	//학생 출석부 목록출력
	public List<Attendance> getAttendanceList(int lectureNo) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lectureNo", lectureNo);
		
		return teacherAttendanceMapper.selectAttendanceList(map);
	}
	
	//학생 출석부 상태변경
	public int modifyAttendanceState(Attendance attendance) {
		return teacherAttendanceMapper.updateAttendanceState(attendance);
	}
	
	//학생 출석부 상세보기
	public List<Attendance> getAttendanceStateOne(int lectureNo, String studentId, String attendanceDay) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lectureNo", lectureNo);
		map.put("studentId", studentId);
		map.put("attendanceDay", attendanceDay);
		
		return teacherAttendanceMapper.selectAttendanceOne(map);
	}
}
