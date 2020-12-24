package gd.fintech.lms.manager.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.vo.Account;

@Mapper
public interface ManagerMemberMapper {
	//강사와 학생 모든 리스트를 출력하기 위한 메퍼 호출
	List<Account> selectTeacherAndStudent(Map<String, Object> map);
	//강사와 학생 전부를 불러오는 페이징을 위한 count 출력
	int selectTeacherAndStudentCount();
	//강사 또는 학생만 검색하기 위한 메퍼 호출
	List<Account> selectTeacherOrStudent(Map<String, Object> map);
	//직책별 검색을 통한 페이징을 위한 count 출력
	int selectTeacherOrStudentCount(String searchLevel);
	//이름만 검색했을때 출력하기 위한 메퍼 호출
	List<Account> selectSearchName(Map<String, Object> map);
}
