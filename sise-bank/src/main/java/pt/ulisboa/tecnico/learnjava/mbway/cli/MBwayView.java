package pt.ulisboa.tecnico.learnjava.mbway.cli;

import java.util.Scanner;

import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;
import pt.ulisboa.tecnico.learnjava.mbway.controllers.Associate_MBwayController;
import pt.ulisboa.tecnico.learnjava.mbway.controllers.Confirm_MBwayController;
import pt.ulisboa.tecnico.learnjava.mbway.controllers.Split_Insurance_MBwayController;
import pt.ulisboa.tecnico.learnjava.mbway.controllers.Transfer_MBwayController;
import pt.ulisboa.tecnico.learnjava.mbway.domain.MBway;


public class MBwayView {
	
	private MBway clients;
	
	public MBwayView (MBway clients) {
		this.clients = clients;
	}
	
	public void FromUser() throws AccountException {
		
		Scanner in = new Scanner(System.in);	
		
		while (true) {
			String command_entry = in.nextLine();
			String[] aux = command_entry.split("\\s+");
			String command = aux[0]; 

			if (command.equals(new String("associate-mbway"))) {
				associateMbway();
				}
			else if (command.equals(new String("confirm-mbway"))) {
				confirmMbway();
				}
			else if (command.equals(new String("mbway-transfer"))) {
				transferMbway();
			}
			else if (command.equals(new String("mbway-split-insurance"))) {
				splitInsuranceMbway();
			}
			else if (command.equals(new String("exit"))) {
				in.close();
				break;
				}
		}
    }
	
	
	public String associateMbway() throws AccountException {
		associateMbway();
		String IBAN = aux[1];
		String phone = aux[2];
		Associate_MBwayController controller = new Associate_MBwayController(IBAN, phone, clients);
		
		return "Code: " + controller.setMBway() + " (don’t share it with anyone)";
	}
	
	public String confirmMbway() throws AccountException {
		String phone = aux[1];
		String code = aux[2];
		int code_int = Integer.parseInt(code);
		Confirm_MBwayController controller = new Confirm_MBwayController(phone, code_int, clients);

    	if (controller.confirmMBway() == code_int) {
    		return "MBWAY association confirmed successfully!";
    	} else {
    		return "Wrong confirmation code. Try association again.";
    		}
	}
	
	public String transferMbway() throws AccountException {
		String source_phone = aux[1];
		String target_phone = aux[2];
		String amount = aux[3];
		Integer amount_int = Integer.parseInt(amount);
		Transfer_MBwayController controller = new Transfer_MBwayController(source_phone, target_phone, amount_int, clients);
		
		if (controller.transfer() == 0) {
			return "Transfer performed successfully!";
		} else if (controller.transfer() == 1) {
			return "Wrong phone number.";
		} else if (controller.transfer() == 2) {
			return "Not enough money on the source account.";
		}
		return null;
	}
	
	public String splitInsuranceMbway() throws AccountException {
		String number_fam_member = aux[1];
		Integer number_fam_member_int = Integer.parseInt(number_fam_member);
		String amount = aux[2];
		Integer amount_int = Integer.parseInt(amount);
		Split_Insurance_MBwayController controller = new Split_Insurance_MBwayController(number_fam_member_int, amount_int, clients);
		
		while (true) {
			String command_entry_friend = in.nextLine();
			String[] aux_friend = command_entry_friend.split("\\s+");
			String command_friend = aux_friend[0];
			
			if (command_friend.equals(new String("friend"))) {
				String phone_fam_member = aux_friend[1];
				String amount_friend = aux_friend[2];
				Integer amount_int_friend = Integer.parseInt(amount_friend);

				if (controller.confirmFriend(phone_fam_member) == false) {
					return "Friend " + phone_fam_member + " is not registered.";
				} else {
					controller.addFriend(phone_fam_member, amount_int_friend);
				}
			} else if (command_friend.equals(new String("end"))) {
				if (controller.confirmMembersNumber() == 0) {
					return "Oh no! One family member is missing.";
				} else if (controller.confirmMembersNumber() == 1) {
					return "Oh no! Too many family members.";
				}
				break;
				}
			}
		if (controller.confirmInsuranceValue() == false) {
			return "Something is wrong. Is the insurance amount right?";
		} else {
			if (controller.payInsurance() == false) {
				return "Oh no! One family member doesn’t have money to pay!";
			} else {
				return "Insurance paid successfully!";
			}
		}
	}
	
}
