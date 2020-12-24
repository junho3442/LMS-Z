package gd.fintech.lms.manager.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gd.fintech.lms.manager.mapper.ManagerMemberMapper;
import gd.fintech.lms.vo.Account;

@Service
@Transactional
public class ManagerMemberService {
	@Autowired ManagerMemberMapper managerMemberMapper;
	
	//활성화 되어있는 학생,강사 리스트 출력하기 위한 메퍼 호출
	public List<Account> getTeacherAndStudentList(int beginRow, int rowPerPage){
		Map<String, Object> map = new HashMap<>();
		map.put("beginRow", beginRow);
		map.put("rowPerPage", rowPerPage);
		return managerMemberMapper.selectTeacherAndStudent(map);
	}
	//페이징을 위한 카운트를 위한 메퍼 호출
	public int getTeacherAndStudentCount() {
		return managerMemberMapper.selectTeacherAndStudentCount();
	}
}