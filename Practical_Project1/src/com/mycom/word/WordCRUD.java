package com.mycom.word;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class WordCRUD implements ICRUD{
	
	ArrayList<Word> list;
	Scanner s;
	final String fname = "Dictionary.txt";
	
	WordCRUD(Scanner s){
		list = new ArrayList<>();
		this.s = s;//이렇게만 작성해도 되는구나? Scanner s = new Scanner(System.in)과는 뭐가 다르지?
	}
	@Override
	public Object add() {
		System.out.print("=> 난이도(1,2,3) & 새 단어 입력 : ");
		int level = s.nextInt();
		String word = s.nextLine();
		// 1 deriveway
		System.out.print("뜻 입력 : ");
		String meaning = s.nextLine();
		// 차고 진입로
		
		return new Word(0,level, word, meaning);
	}
	
	public void addWord() {
		Word one = (Word)add();
		list.add(one);
		System.out.println("\n새 단어가 단어장에 추가되었습니다.\n");
	}

	@Override
	public int update(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void selectOne(int id) {
		// TODO Auto-generated method stub
		
	}
	public void listAll() {
		System.out.println("-----------------------------");
		for(int i =0;i<list.size();i++) {
			System.out.print((i+1) + " ");
			System.out.println(list.get(i).toString());
		}
		System.out.println("-----------------------------");
	}
	public void listAll(int slevel) {
		int j=0;
		System.out.println("-----------------------------");
		for(int i =0;i<list.size();i++) {
			if(slevel==list.get(i).getLevel()) {
			System.out.print((j+1) + " ");
			System.out.println(list.get(i).toString());
			j++;
			}
		}
		System.out.println("-----------------------------");
	}
	public ArrayList<Integer> listAll(String s) {
		int j=0;
		ArrayList<Integer> num= new ArrayList<>();
		System.out.println("-----------------------------");
		for(int i =0;i<list.size();i++) {
			String word = list.get(i).getWord();
			if(word.contains(s)){
			System.out.print((j+1) + " ");
			System.out.println(list.get(i).toString());
			num.add(i);
			j++;
			}
		}
		
		System.out.println("-----------------------------");
		return num;
	}
	
	public void updateItem() {
		System.out.print("=> 수정할 단어 검색 : ");
		String keyword = s.next();//수정할 단어가 포함되어 있는 단어를 입력받음
		ArrayList<Integer> snum= this.listAll(keyword);
		System.out.print("=> 수정할 번호 검색 : ");
		int num = s.nextInt();
		s.nextLine();
		System.out.print("=> 뜻 입력 : ");
		String meaning = s.nextLine();
		list.get(snum.get(num-1)).setMeaning(meaning);
		System.out.println("단어가 수정되었습니다.");
	}
	public void deleteItem() {
		System.out.print("=> 삭제할 단어 검색 : ");
		String keyword = s.next();//수정할 단어가 포함되어 있는 단어를 입력받음
		ArrayList<Integer> snum= this.listAll(keyword);
		System.out.print("=> 삭제할 번호 검색 : ");
		int num = s.nextInt();
		s.nextLine();
		System.out.print("=> 정말로 삭제하실래요?(Y/N) ");
		String meaning = s.nextLine();
		if(meaning.equalsIgnoreCase("y"))
		list.remove((int)snum.get(num-1));
		System.out.println("\n단어가 삭제되었습니다.");
	}
	public void listByLevel() {
		System.out.print("=>레벨(1:초급,2:중급,3:고급)선택 :");
		int slevel=s.nextInt();//검색하고자 하는 레벨
		this.listAll(slevel);
	}
	public void searchWord() {
		System.out.print("검색할 단어 입력 :");
		String keyword= s.next();//검색할 단어 저장
		listAll(keyword);
	}
	public void loadFile() {
			try {
				BufferedReader br = new BufferedReader(new FileReader(fname));
				String line;
				int count=0;
				while((line = br.readLine())!=null) {
					String[] words = line.split("\\|");
					int level = Integer.parseInt(words[0]);
					String word = words[1];
					String meaning = words[2];
					list.add(new Word(0,level,word,meaning));
					count++;
				}
				System.out.println("=> "+count+"개 단어 로딩 완료!");
				br.close();
			}  catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public void saveFile() {
		try {
			PrintWriter pr = new PrintWriter(new FileWriter(fname));
			for(Word one : list) {
				pr.write(one.toStringFile()+"\n");
			}
			pr.close();
			System.out.println("모든 단어 저장 완료!!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
