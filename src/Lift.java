import java.util.Arrays;
import java.util.Scanner;

// Added this line after cloning 

class LiftPair implements Comparable<LiftPair> {
    private final int index;
    private final int value;

    public LiftPair(int index, int value) {
        this.index = index;
        this.value = value;
    }

    public int getIndex() {
		return index;
	}

	public int getValue() {
		return value;
	}

	@Override
    public int compareTo(LiftPair other) {
    	return Integer.valueOf(this.value).compareTo(other.value);
    }
}

public class Lift {
	
	/*
	 * Assumption: 1)Drop time is not considered for optimization
	 * 			2) Even no of persons
	 * 			3) Drop floor is never equal to initial position of lifts
	 * 
	 * First, partition persons into both lifts according to the direction
	 * and then balance the lifts by sending person(s) from overloaded lift to under-loaded lift.
	 * 
	 */
	
	 
	
	public static void main(String... args) {
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter initial position of lifts:");
		int x = input.nextInt(); // initial position of both lifts
		
		System.out.println("Enter no persons:");
		int noPersons = input.nextInt();
		int i; //ctr
		
		
		LiftPair personDropFloor[] = new LiftPair[noPersons];
		for(i=0;i<personDropFloor.length;i++) {
			personDropFloor[i] = new LiftPair(i+1, input.nextInt());
		}
		
		Arrays.sort(personDropFloor);
		
//		for(i=0;i<noPersons;i++) {
//			System.out.print(personDropFloor[i].getIndex()+"\t");
//		}
		System.out.println();
		
		int choiceLift = -1;
		
		if(personDropFloor[noPersons/2-1].getValue()>x &&  personDropFloor[0].getValue()<x) {
			choiceLift = 0; //A is having persons going in both direction
		}
		else if((personDropFloor[noPersons/2].getValue()<x) && (personDropFloor[noPersons-1].getValue()>x)) {
			choiceLift = 1; //B is having persons going in both direction
		}
		else if(personDropFloor[0].getValue()<x && personDropFloor[noPersons-1].getValue()<x) {
			choiceLift = 2; // Both A and B moving downward from initial position
		}
		else  if(personDropFloor[0].getValue()>x && personDropFloor[noPersons-1].getValue()>x) {
			choiceLift = 3; // Both A and B moving upward from initial position
		}
		
//		To decide initial direction of lift, max waiting time is calculated of both case is calculated
		
		int maxTravel1=0,maxTravel2=0; // max of travel time
		int cntr= 0;
		if(choiceLift == 0) {
			
			
			for(i=0;i<noPersons/2;i++) {
				if(personDropFloor[i].getValue()<x) {
					cntr++;
				}
			}
			
			maxTravel1 += Math.abs(x-personDropFloor[0].getValue());
			maxTravel2 += Math.abs(x-personDropFloor[noPersons/2-1].getValue());
			
			System.out.println("Lift A:");
			int p; //ctr
			if(maxTravel1 + 2*maxTravel2> 2*maxTravel1+maxTravel2) {
				for(p = cntr;p<noPersons/2;p++) {
					System.out.print(personDropFloor[p].getIndex()+"\t");
				}
				for(p=cntr-1;p>=0;p--) {
					System.out.print(personDropFloor[p].getIndex()+"\t");
				}
				
			}
			else {
				for(p=cntr-1;p>=0;p--) {
					System.out.print(personDropFloor[p].getIndex()+"\t");
				}
				for(p = cntr;p<noPersons/2;p++) {
					System.out.print(personDropFloor[p].getIndex()+"\t");
				}
			}
			System.out.println();
			System.out.println("Lift B:");
			for(p=noPersons/2;p<noPersons;p++) {
				System.out.print(personDropFloor[p].getIndex()+"\t");
			}
			
			
			
		}
		else if(choiceLift == 1) {
			int p;//ctr
			System.out.println("Lift A:");
			
			for(i=noPersons/2-1;i>=0;i--) {
				System.out.print(personDropFloor[i].getIndex()+"\t");
			}
			
			maxTravel1 += Math.abs(x-personDropFloor[noPersons/2].getValue());
			maxTravel2 += Math.abs(x-personDropFloor[noPersons-1].getValue());
			
			for(i=noPersons/2;i<noPersons;i++) {
				if(personDropFloor[i].getValue()<x) {
					cntr++;
				}
			}
			System.out.println();
			System.out.println("Lift B:");
			if(maxTravel1 + 2*maxTravel2> 2*maxTravel1+maxTravel2) {
				for(p=noPersons/2+cntr;p<noPersons;p++) {
					System.out.print(personDropFloor[p].getIndex()+"\t");
				}
				for(p=noPersons/2+cntr-1;p>=noPersons/2;p--) {
					System.out.print(personDropFloor[p].getIndex()+"\t");
				}
			}
			else {
				for(p=noPersons/2+cntr-1;p>=noPersons/2;p--) {
					System.out.print(personDropFloor[p].getIndex()+"\t");
				}
				for(p=noPersons/2+cntr;p<noPersons;p++) {
					System.out.print(personDropFloor[p].getIndex()+"\t");
				}
				
			}
			
			
		}
		else if (choiceLift ==  2){ // choice = 2 both lift move downwards
			System.out.println("Lift A:");
			for(i=noPersons/2-1;i>=0;i--) {
				System.out.print(personDropFloor[i].getIndex()+"\t");
			}
			System.out.println();
			System.out.println("Lift B:");
			for(i=noPersons-1;i>=noPersons/2;i--) {
				System.out.print(personDropFloor[i].getIndex()+"\t");
			}
		}
		
		else if (choiceLift == 3) { // choice = 3 both lift move upwards
			System.out.println("Lift A:");
			for(i=0;i<noPersons/2;i++) {
				System.out.print(personDropFloor[i].getIndex()+"\t");
			}
			System.out.println();
			System.out.println("Lift B:");
			for(i=noPersons/2;i<noPersons;i++) {
				System.out.print(personDropFloor[i].getIndex()+"\t");
			}
		}
		
		

		
		
		
		
		
		
	}
	

}
