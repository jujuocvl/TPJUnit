package ticketmachine;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class TicketMachineTest {
	private static final int PRICE = 50; // Une constante

	private TicketMachine machine; // l'objet à tester

	@BeforeEach
	public void setUp() {
		machine = new TicketMachine(PRICE); // On initialise l'objet à tester
	}

	@Test
	// On vérifie que le prix affiché correspond au paramètre passé lors de l'initialisation
	// S1 : le prix affiché correspond à l’initialisation.
	void priceIsCorrectlyInitialized() {
		// Paramètres : valeur attendue, valeur effective, message si erreur
		assertEquals(PRICE, machine.getPrice(), "Initialisation incorrecte du prix");
	}

	@Test
	// S2 : la balance change quand on insère de l’argent
	void insertMoneyChangesBalance() {
		machine.insertMoney(10);
		machine.insertMoney(20);
                // Les montants ont été correctement additionnés  
		assertEquals(10 + 20, machine.getBalance(), "La balance n'est pas correctement mise à jour");              
	}

	@Test
	//S3 : on imprime le tickets si le montant inséré est insuffisant
	void ImprimePasSiMontantInsufissant() {
		machine.insertMoney(PRICE -1);
		assertFalse(machine.printTicket(), "Le ticket ne doit pas être imprimé");
	}

	@Test
	//S4 : on imprime si le montant du ticket est suffisant
	void imprimeSiMontantSuffisant() {
		machine.insertMoney(PRICE +1);
		assertTrue(machine.printTicket(), "Le ticket peut être imprimé");
	}

	@Test
	//S5 : Quand on imprime un ticket la balance est décrémentée du prix du ticket
	void decrementerBalance() {
		machine.insertMoney(PRICE +10);
		machine.printTicket();
		assertEquals(10, machine.getBalance(), "La balance a été décrémentée du prix du ticket");
	}

	@Test
	//S6 : le montant collecté est mis à jour quand on imprime un ticket (pas avant)
	void miseAJourPostTicket() {
		machine.insertMoney(PRICE );
		machine.printTicket();
		assertEquals(50, machine.getTotal(), "le montant est bien collecté après l'impression");
	}
	@Test
	 void pasMJBeforePrint() {
		machine.insertMoney( PRICE +10);
		assertEquals(0, machine.getTotal(), "le montant n'est pas mis à jour");
	 }

	@Test
	//S7 : refund() rend correctement la monnaie
	void MonnaieCorectementRendue(){
		machine.insertMoney(PRICE +10);
		machine.printTicket();
		assertTrue(machine.refund()==10, "la monnaie est correctement rendue");
	}

	@Test
	//S8 : refund() remet la balance à zéro
	void MonnaieRemiseZero(){
		machine.insertMoney(PRICE +10);
		machine.printTicket();
		machine.refund();
		assertTrue(machine.refund()==10, "la monnaie est correctement rendue");
	}

	@Test
	//S9: on ne peut pas insérer un montant négatif
	void montantNegatif() {
		try {
			machine.insertMoney(-PRICE);
			fail ("Cet appel doit lever une exception");
		}
		catch(IllegalArgumentException e){}
		}
	

	@Test
	//S10: on ne peut pas créer de machine qui délivre des tickets dont le prix est négatif
	void pasTicketNegatif() {
		try {
			machine = new TicketMachine(-50);
			fail("Argument must not be null");
		}
		catch (IllegalArgumentException e) {

		}
		}

	}
	
