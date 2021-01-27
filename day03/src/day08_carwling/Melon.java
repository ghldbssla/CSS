package day08_carwling;

import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Melon {
	public static String id = "webdriver.chrome.driver";
	public static String path = "C:/chromedriver.exe";
	public static void main(String[] args) {
		System.setProperty(id, path);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("headless");
		String url = "https://www.melon.com/";
		options.setCapability("ignoreProtectedModeSettings", true);
		WebDriver driver = new ChromeDriver(options);
//		------------------------------------------------------------
		driver.get(url);
		Scanner sc = new Scanner(System.in);
		System.out.print("검색할 키워드 : ");
		String keyword = sc.next();
		WebElement searchInput = driver.findElement(By.id("top_search"));
		searchInput.sendKeys(keyword);
		try {Thread.sleep(500);} catch (InterruptedException e) {}
		WebElement searchBtn = driver.findElement(By.className("search_m"));
		searchBtn.click();
//		------------------------------------------------------------
		try {Thread.sleep(500);} catch (InterruptedException e) {}
		WebElement musicList = driver.findElement(By.tagName("tbody"));
		List<WebElement> titleList = musicList.findElements(By.cssSelector("a.fc_gray"));
		
		System.out.println("========="+keyword+"의 음악들========");
		for(int i = 0; i<titleList.size();i++) {
			System.out.println(titleList.get(i).getText());
		}
		System.out.println("==========================");
		System.out.print("제목 : ");
		sc=new Scanner(System.in);
		String title = sc.nextLine();
		int idx = 0;
		for(int i = 0; i<titleList.size();i++) {
			if(titleList.get(i).getText().equals(title)) {
				idx=i;
			}
		}
		List<WebElement> lyrics = musicList.findElements(By.cssSelector("a.btn_icon_detail"));
		lyrics.get(idx).click();
		try {Thread.sleep(500);} catch (InterruptedException e) {}
		WebElement lyric=null;
		try {
			lyric = driver.findElement(By.id("d_video_summary"));
			System.out.println(lyric.getText());
		} catch (NoSuchElementException nsee) {
			System.out.println("선택하신 노래는 가사가 등록되지 않았습니다.");
		}
		driver.close();		
	}
}







