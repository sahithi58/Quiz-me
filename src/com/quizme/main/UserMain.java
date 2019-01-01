
package com.quizme.main;

import java.util.Iterator;
import java.util.Scanner;

import com.quizme.bean.Question;
import com.quizme.bean.Score;
import com.quizme.bean.User;
import com.quizme.service.QuizService;
import com.quizme.service.ScoreService;
import com.quizme.service.UserService;
import com.quizme.service.impl.QuizServiceImpl;
import com.quizme.service.impl.ScoreServiceImpl;
import com.quizme.service.impl.UserServiceImpl;

public class UserMain {
	static int score = 0;
	static String category = null;

	/**
	 * @author Satya S Vundavalli
	 * 
	 * 	  
	 * Description:
	 * This method implements all the logic that controls how the user interacts with QuizMe app.
	 * 
	 */
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Choose one option:");
		System.out.println("1. Signup");
		System.out.println("2. Login");
		System.out.println("3. Take anonymous quiz");
		System.out.println("4. Exit");
		System.out.println("Enter the option number: ");
		int choice = sc.nextInt();
		sc.nextLine();
		switch (choice) {
		case 1:
			boolean flag = false;
			UserService uservice = new UserServiceImpl();
			User currentSUser = new User();
			String name = null, pass = null;
			while (!flag) {
				System.out.println("Enter your name: ");
				name = sc.nextLine();

				flag = uservice.checkName(name);
				System.out.println(flag);
			}
			currentSUser.setName(name);
			boolean passwordflag = false;

			while (!passwordflag) {
				System.out.println("Enter password: ");

				pass = sc.nextLine();
				passwordflag = uservice.checkpasswordLength(pass);
			}
			currentSUser.setPassword(pass);
			uservice.signUpuser(currentSUser);
			quizMenu();
			ScoreService savesscore = new ScoreServiceImpl();
			savesscore.addScore(score, category, currentSUser.getName());
			break;
		case 2:
			UserService ulservice = new UserServiceImpl();
			User currentLUser = new User();
			boolean loginflag = false;
			while (!loginflag) {
				System.out.println("Enter your name: ");
				currentLUser.setName(sc.nextLine());
				System.out.println("Enter password: ");
				currentLUser.setPassword(sc.nextLine());
				loginflag = ulservice.loginuser(currentLUser);
			}
			
			/*
			 * @author Varun Saket
			 * 
			 *this method gets user's score and best score
			 */
			System.out.println("Choose An Option: ");
			System.out.println("1. Take A Quiz");
			System.out.println("2. Get My Scores");
			System.out.println("3. Get My Best Score");
			int userchoice = sc.nextInt();
			switch (userchoice) {
			case 1:boolean quizflag=false;
				
			while(!quizflag)
				{
				quizMenu();
				
				ScoreService savelscore = new ScoreServiceImpl();
				savelscore.addScore(score, category, currentLUser.getName());
				stillBestScore(currentLUser);
				System.out.println("Choose An Option: ");
				System.out.println("1. Take Another Quiz");
				System.out.println("2. quit");
				int quizchoice =sc.nextInt();
				
				switch(quizchoice)
				{
				case 1:
					quizflag=false;
					break;
				case 2:
					quizflag=true;
					break;
				}
				
				}break;
			case 2:
				ScoreService qservice = new ScoreServiceImpl();
				Iterator<Score> userscore;
				userscore = qservice.getScore(currentLUser);
				while (userscore.hasNext()) {
					Score scr = userscore.next();
					System.out.println(scr);
				}
				break;
			case 3:
				stillBestScore(currentLUser);
				break;
			}
			break;
		case 3:
			quizMenu();
			break;
		case 4:
			System.out.println("We miss you!");
			break;
		}
		sc.close();
	}

	/**
	 * @author Satya S Vundavalli
	 * @param qList -> List of questions requested by the user.
	 * 
	 * Description: This method takes the questions list and displays one after the other while taking user inputs.
	 * After the questions are completed the result will be displayed.
	 */
	
	static void showQuestion(Iterator<Question> qList) {
		int i = 1;
		score = 0;
		System.out.println("showQuestion");
		while (qList.hasNext() && i <= 5) {
			Question qLoad = qList.next();
			Scanner sc = new Scanner(System.in);
			int j = 1;
			System.out.println(i++ + ". " + qLoad.getQuestion());
			System.out.println("\t" + (j++) + ". " + qLoad.getChoice1());
			System.out.println("\t" + (j++) + ". " + qLoad.getChoice2());
			System.out.println("\t" + (j++) + ". " + qLoad.getChoice3());
			System.out.println("\t" + j + ". " + qLoad.getChoice4());
			System.out.println("Enter your number choice: ");
			boolean flag = false;
			do {
				flag = false;
				switch (sc.nextInt()) {
				case 1:
				
					if (qLoad.getChoice1().equals(qLoad.getRightChoice()))
						System.out.println(qLoad.getRightChoice());
						score++;
					break;
				case 2:
					if (qLoad.getChoice2().equals(qLoad.getRightChoice()))
						score++;
					System.out.println(qLoad.getRightChoice());
					break;
				case 3:
					if (qLoad.getChoice3().equals(qLoad.getRightChoice()))
						score++;
					System.out.println(qLoad.getRightChoice());
					break;
				case 4:
					if (qLoad.getChoice4().equals(qLoad.getRightChoice()))
						score++;
					System.out.println(qLoad.getRightChoice());
					break;
				default:
					System.out.println("!!Invalid choice!!");
					flag = true;
					sc.nextLine();
					System.out.println("Enter a valid option: ");
					break;
				}
			} while (flag);
			//sc.close();
		}
		System.out.println("Your score is : " + score);
		

	}

	
	/**
	 * @author Satya S Vundavalli
	 * 
	 * Description: This method displays all the categories from which user can choose.
	 * Depending on the choice questions are requested from that category and displayed using showQuestions() method
	 */
	
	static void quizMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Choose Quiz category: ");
		System.out.println("1. Movies");
		System.out.println("2. Sports");
		System.out.println("3. GK");
		System.out.println("4. Science");
		System.out.println("5. General");
		int quizChoice = sc.nextInt();
		QuizService qservice = new QuizServiceImpl();

		switch (quizChoice) {
		case 1:
			System.out.println("in case 1");
			showQuestion(qservice.getQuiz("Movies"));
			category = "Movies";
			break;
		case 2:
			showQuestion(qservice.getQuiz("Sports"));
			category = "Sports";
			break;
		case 3:
			showQuestion(qservice.getQuiz("GK"));
			category = "GK";
			break;
		case 4:
			showQuestion(qservice.getQuiz("Science"));
			category = "Science";
			break;
		case 5:
			showQuestion(qservice.getQuiz("General"));
			category = "General";
			break;
		}
		sc.close();
	}
	
	/**
	 * @author Phanindra Pvs
	 * @param still the best score from the category
	 * Description:This method takes up a user object and category and checks the best score from the score table according to the category.*/
	
	static void stillBestScore(User user)
	{
		ScoreService qbestservice = new ScoreServiceImpl();
		int userbestscore;
		userbestscore = qbestservice.getBestScore(user,category);
		System.out.println("Your Best Score Amongst The Attempted Quizzes in the category"
				+" "+ category+" is " + userbestscore);
	}
}
