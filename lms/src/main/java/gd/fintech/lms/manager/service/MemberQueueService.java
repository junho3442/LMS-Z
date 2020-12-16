package gd.fintech.lms.manager.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gd.fintech.lms.manager.mapper.MemberQueueMapper;
import gd.fintech.lms.vo.StudentQueue;
import gd.fintech.lms.vo.TeacherQueue;


@Service
@Transactional
public class MemberQueueService {
	@Autowired private MemberQueueMapper memberQueueMapper;
	//학생 대기상태
	public List<StudentQueue> getStudentMemberQueueList(){
		return memberQueueMapper.selectStudentMemberQueue();
	}
	//강사 대기상태
	public List<TeacherQueue> getTeacherMemberQueueList(){
		return memberQueueMapper.selectTeacherMemberQueue();
	}
	//학생 승인
	public void insertStudent(String studentId, String accountState, String managerId) {
		StudentQueue studentQueue = memberQueueMapper.selectStudentOne(studentId);
		//System.out.println(studentQueue);
		//System.out.println(studentId);
		Map<String, Object> s = new HashMap<String,Object>();
		s.put("s", studentQueue);
		s.put("managerId", managerId);
		//System.out.println(s);
		memberQueueMapper.insertStudent(s);
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("memberId", studentId);
		map.put("accountState", accountState);
		memberQueueMapper.updateAccountState(map);
		memberQueueMapper.deleteStudentQueue(studentId);
	}
	//강사 승인
	public void insertTeacher(String teacherId, String accountState, String managerId) {
		TeacherQueue teacherQueue = memberQueueMapper.selectTeacherOne(teacherId);
		//System.out.println(teacherQueue);
		//System.out.println(teacherId);
		Map<String, Object> t = new HashMap<String,Object>();
		t.put("t", teacherQueue);
		t.put("managerId", managerId);
		//System.out.println(t);
		memberQueueMapper.insertTeacher(t);
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("memberId", teacherId);
		map.put("accountState", accountState);
		memberQueueMapper.updateAccountState(map);
		memberQueueMapper.deleteTeacherQueue(teacherId);
	}
	//학생 승인 거절
	public void deleteStudentQueue(String studentId, String accountState) {
		memberQueueMapper.deleteStudentQueue(studentId);
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("memberId", studentId);
		map.put("accountState", accountState);
		memberQueueMapper.updateAccountState(map);
	}
	//강사 승인 거절
	public void deleteTeacherQueue(String teacherId, String accountState) {
		memberQueueMapper.deleteTeacherQueue(teacherId);
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("memberId", teacherId);
		map.put("accountState", accountState);
		memberQueueMapper.updateAccountState(map);
	}
}
