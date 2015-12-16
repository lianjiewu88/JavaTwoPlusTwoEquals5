package optionalTest;

import java.util.Optional;

public class OptionalTest {

	class Person {
		private String mName;
		private Skill mSkill;
		public Person(String name) {
			mName = name;
		}
		
		public Skill getSkill(){
			return mSkill;
		}
		
		public Optional<Skill> getSkillOptional(){
			return Optional.ofNullable(mSkill);
		}
		public void setSkill(Skill skill){
			mSkill = skill;
		}
		public String getName() {
			return mName;
		}
		
		public void greet() {
			System.out.println("I am: " + mName);
		}
		
	}
	
	class Skill{
		private String mName;
		private int mLevel;
		public Skill(String name, int level) {
			mName = name;
			mLevel = level;
		}
		public void display(){
			System.out.println("Skill: " + mName + " level: " + mLevel);
		}
		
	}
	
	public void scenario1(){
		Person person = null;
		try {
			Optional<Person> p1 = Optional.of(person);
		}
		catch ( Exception e) {
			System.out.println("Error: " + e.getClass().getName() + " - " + e.getLocalizedMessage());
		}
		
		Optional<Person> p2 = Optional.ofNullable(person);
		System.out.println(p2.isPresent());
		
		Person jerry = new Person("Jerry");
		Optional<Person> p3 = Optional.ofNullable(jerry);
		System.out.println(p3.isPresent());
		
		System.out.println("p2 test");
		p2.ifPresent((p) -> p.greet());
		System.out.println("p3 test");
		p3.ifPresent((p) -> p.greet());
		
		System.out.println("Example 3");
		// before Jave8
		Person oldImplementation = ( person  != null? person:new Person("Ji")); 
		Person newPerson = p2.orElse(new Person("Ji"));
		Person existPerson = p3.orElse(new Person("Mr Unknown"));
		newPerson.greet();
		existPerson.greet();
		
		if( jerry != null && jerry.getName().equals("Jerry")) {
			System.out.println("old style-> Jerry found: " + jerry.getName());
		}
		p3.filter( personHolder -> "Jerry".equals(personHolder.getName())).
			ifPresent((personH2) -> System.out.println("new style-> Jerry found: " + personH2.getName()));
		
		p3.map(Person::getName)
			.filter( Name -> "Jerry".equals(Name))
			.ifPresent( foundName -> System.out.println("new style 2-> Jerry found: " + foundName));
		
		/*jerry.setSkill("JavaScript");
		
		Optional<String> jerrySkill = p3.map(Person::getSkill);
		System.out.println(jerrySkill.isPresent());
		
		p3.map(Person::getSkill)
			.filter(Skill -> "JavaScript".equals(Skill))
			.ifPresent( Skill2 -> System.out.println("Jerry's skill is: " + Skill2 )  );*/
		
		Skill jsSkill = new Skill("JavaScript", 80);
		jerry.setSkill(jsSkill);
		
		Optional<Skill> temp = p3.map(Person::getSkill); 
		Optional<Skill> temp2 = p3.flatMap(Person::getSkillOptional);
		
		Skill s1 = temp.get();
		Skill s2 = temp2.get();
		
		s1.display();
		s2.display();

	}
	
	public static void main(String[] args) {
		OptionalTest test = new OptionalTest();
		test.scenario1();
	}

}
