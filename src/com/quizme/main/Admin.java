package com.quizme.main;

import java.util.Scanner;

import com.quizme.bean.Question;
import com.quizme.service.QuizService;
import com.quizme.service.impl.QuizServiceImpl;

public class Admin {

	/**
	 * @author Satya S Vundavalli
	 * 
	 * Description: This method is used by the admin to enter questions into the table.
	 * 
	 */
	public static void main(String[] args) {

		System.out.println("Enter question details:");
		QuizService qs = new QuizServiceImpl();
		
		Scanner sc = new Scanner(System.in);
		Question ques = new Question();
		System.out.println("Enter question:");
		ques.setQuestion(sc.nextLine());
		System.out.println("Enter choice 1:");
		ques.setChoice1(sc.nextLine());
		System.out.println("Enter choice 2:");
		ques.setChoice2(sc.nextLine());
		System.out.println("Enter choice 3:");
		ques.setChoice3(sc.nextLine());
		System.out.println("Enter choice 4:");
		ques.setChoice4(sc.nextLine());
		System.out.println("Enter right choice:");
		ques.setRightChoice(sc.nextLine());
		System.out.println("Enter category:");
		ques.setCategory(sc.nextLine());
		qs.addQuestion(ques);
		
		sc.close();
	}

}