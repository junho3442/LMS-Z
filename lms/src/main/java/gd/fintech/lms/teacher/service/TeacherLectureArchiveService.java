package gd.fintech.lms.teacher.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import gd.fintech.lms.teacher.mapper.TeacherLectureArchiveFileMapper;
import gd.fintech.lms.teacher.mapper.TeacherLectureArchiveMapper;
import gd.fintech.lms.vo.Lecture;
import gd.fintech.lms.vo.LectureArchive;
import gd.fintech.lms.vo.LectureArchiveAddForm;
import gd.fintech.lms.vo.LectureArchiveFile;
import gd.fintech.lms.vo.QuestionAddForm;
import gd.fintech.lms.vo.QuestionFile;

@Service
public class TeacherLectureArchiveService {
	private final String PATH ="C:\\Users\\git\\LMS-Z\\lms\\src\\main\\webapp\\uploadfile\\lectureArchivefile\\";
	
	@Autowired TeacherLectureArchiveMapper teacherLectureArchiveMapper;
	@Autowired TeacherLectureArchiveFileMapper teacherLectureArchiveFileMapper;
	public List<LectureArchive> getLectureArchiveList(int currentPage, int rowPerPage, String accountId){
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("accountId", accountId);
		map.put("beginRow", (currentPage-1)*rowPerPage);
		map.put("rowPerPage", rowPerPage);
		
		return teacherLectureArchiveMapper.selectLectureArchive(map);
	}
	
	public int listCountLectureArchive(String accountId) {
		return teacherLectureArchiveMapper.totalLectureArchive(accountId);
	}
	
	public void addLectureArchive(LectureArchiveAddForm lectureArchiveAddForm) {
		LectureArchive lectureArchive = new LectureArchive();
		
		lectureArchive.setLectureNo(lectureArchiveAddForm.getLectureNo());
		lectureArchive.setAccountId(lectureArchiveAddForm.getAccountId());
		lectureArchive.setLectureArchiveWriter(lectureArchiveAddForm.getLectureArchiveWriter());
		lectureArchive.setLectureArchiveTitle(lectureArchiveAddForm.getLectureArchiveTitle());
		lectureArchive.setLectureArchiveContent(lectureArchiveAddForm.getLectureArchiveContent());
		lectureArchive.setLectureArchiveCreatedate(lectureArchiveAddForm.getLectureArchiveCreatedate());
		lectureArchive.setLectureArchiveUpdatedate(lectureArchiveAddForm.getLectureArchiveUpdatedate());
		lectureArchive.setLectureArchiveCount(lectureArchiveAddForm.getLectureArchiveCount());
		
		teacherLectureArchiveMapper.insertLectureArchive(lectureArchive);
		
		List<LectureArchiveFile> lectureArchiveFile=null;
		if(lectureArchiveAddForm.getLectureArchiveFile() != null) {
			lectureArchiveFile = new ArrayList<LectureArchiveFile>();
			for(MultipartFile mf : lectureArchiveAddForm.getLectureArchiveFile()) {
				LectureArchiveFile lf = new LectureArchiveFile();
				lf.setLectureArchiveNo(lectureArchive.getLectureArchiveNo());
				int count = 0;
				int p = mf.getOriginalFilename().lastIndexOf(".");
				
				String ext = mf.getOriginalFilename().substring(p).toLowerCase();
				String filename = UUID.randomUUID().toString().replace("-","");
				
				lf.setLectureArchiveFileUuid(filename+ext);
				lf.setLectureArchiveFileOriginal(mf.getOriginalFilename());
				lf.setLectureArchiveFileSize(mf.getSize());
				lf.setLectureArchiveFileType(mf.getContentType());
				lf.setLectureArchiveFileCount(count);
				lectureArchiveFile.add(lf);
				
				try {
					mf.transferTo(new File(PATH+filename+ext));
				}catch(Exception e) {
					e.printStackTrace();
					throw new RuntimeException();
				}
			}
		} 
		if(lectureArchiveFile != null) {
			for(LectureArchiveFile lf : lectureArchiveFile) {
				teacherLectureArchiveFileMapper.insertLectureArchiveFile(lf);
			}
		}
	}
	public List<Lecture> getLectureList(String accountId){
		return teacherLectureArchiveMapper.selectLectureList(accountId);
	}
	public LectureArchive getLectureArchiveOne(int lectureArchiveNo) {
		return teacherLectureArchiveMapper.selectLectureArchiveOne(lectureArchiveNo);
	}
	public int deleteLectureArchiveOneFile(String lectureArchiveFileUuid) {
		File file = new File(PATH+lectureArchiveFileUuid);
		if(file.exists()) {
			file.delete();
		}
		return teacherLectureArchiveFileMapper.deleteLectureArchiveOneFile(lectureArchiveFileUuid);
	}
	
	
	public void updateLectureArchive(LectureArchiveAddForm lectureArchiveAddForm) {
		LectureArchive lectureArchive = new LectureArchive();
		lectureArchive.setLectureArchiveNo(lectureArchiveAddForm.getLectureArchiveNo());
		lectureArchive.setLectureNo(lectureArchiveAddForm.getLectureNo());
		lectureArchive.setLectureArchiveWriter(lectureArchiveAddForm.getLectureArchiveWriter());
		lectureArchive.setLectureArchiveTitle(lectureArchiveAddForm.getLectureArchiveTitle());
		lectureArchive.setLectureArchiveContent(lectureArchiveAddForm.getLectureArchiveContent());
		lectureArchive.setLectureArchiveUpdatedate(lectureArchiveAddForm.getLectureArchiveUpdatedate());
		teacherLectureArchiveMapper.updateLectureArchive(lectureArchive);
		
		List<LectureArchiveFile> lectureArchiveFile=null;
		if(lectureArchiveAddForm.getLectureArchiveFile() != null) {
			lectureArchiveFile = new ArrayList<LectureArchiveFile>();
			for(MultipartFile mf : lectureArchiveAddForm.getLectureArchiveFile()) {
				LectureArchiveFile lf = new LectureArchiveFile();
				lf.setLectureArchiveNo(lectureArchive.getLectureArchiveNo());
				int count = 0;
				int p = mf.getOriginalFilename().lastIndexOf(".");
				
				String ext = mf.getOriginalFilename().substring(p).toLowerCase();
				String filename = UUID.randomUUID().toString().replace("-","");
				
				lf.setLectureArchiveFileUuid(filename+ext);
				lf.setLectureArchiveFileOriginal(mf.getOriginalFilename());
				lf.setLectureArchiveFileSize(mf.getSize());
				lf.setLectureArchiveFileType(mf.getContentType());
				lf.setLectureArchiveFileCount(count);
				lectureArchiveFile.add(lf);
				
				try {
					mf.transferTo(new File(PATH+filename+ext));
				}catch(Exception e) {
					e.printStackTrace();
					throw new RuntimeException();
				}
			}
		} 
		if(lectureArchiveFile != null) {
			for(LectureArchiveFile lf : lectureArchiveFile) {
				teacherLectureArchiveFileMapper.insertLectureArchiveFile(lf);
			}
		}
	}
}