package com.diorsding.college.util;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import com.diorsding.college.model.Exam;
import com.diorsding.college.model.ExamMark;

public class ExamUtil {
	// sort Exam score and calculate avg, max, min	
	public static Exam sortExamMark(Exam exam) {
		List<ExamMark> examMarks = exam.getExamMarks();
		
		Collections.sort(examMarks);
		
		BigDecimal topMark = null;
		BigDecimal lowestMark = null;
		BigDecimal total = new BigDecimal(0);
		
		int rank = examMarks.size();
		
		for (int i = 0; i < examMarks.size(); i++) {
			ExamMark examMark = examMarks.get(i);
			
			if (i == 0) {
				lowestMark = examMark.getMark();
			}
			
			if (i == examMarks.size() - 1) {
				topMark = examMark.getMark();
			}
			
			examMark.setExamTime(exam.getExamTime());
			examMark.setRank(rank--);
			total = total.add(examMark.getMark());
		}
		
		exam.setAverage(total.divide(new BigDecimal(examMarks.size()), 2, BigDecimal.ROUND_DOWN));
		exam.setTopMark(topMark);
		exam.setLowestMark(lowestMark);
		
		return exam;
	}
}
