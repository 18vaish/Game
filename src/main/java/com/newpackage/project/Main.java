package com.newpackage.project;

import java.util.Random;

class Player {
	int H; // Health
	int S; // Strength
	int attack; // Attack
	Random randomNumber;

	public Player(int H, int S, int attack) {
		this.H = H;
		this.S = S;
		this.attack = attack;
		this.randomNumber = new Random();
	}

	int getH() {
		return H;
	}

	int rollDice() {
		return randomNumber.nextInt(6) + 1; // 6-sided die Roll
	}
	int defend() {
		
		return randomNumber.nextInt(6)+1;
		 
	}

	void reduceH(int damage) {
		H -= damage;
	}

	boolean isAlive() {
		return H > 0;
	}
}

class MagicalArena {
	Player playerA;//instance of a Player class which represent the 
	Player playerB;

	public MagicalArena(Player playerA, Player playerB) {
		this.playerA = playerA;
		this.playerB = playerB;
	}

	void fight() {
		while (playerA.isAlive() && playerB.isAlive()) {
			// Both players attack and defend simultaneously
			int attackRollA = playerA.rollDice();
			int defenseRollA = playerA.rollDice();
			int attackRollB = playerB.rollDice();
			int defenseRollB = playerB.rollDice();

			//  damage for playerB
			int damageToB = attackRollA * playerA.attack - defenseRollB * playerB.S;
			if (damageToB > 0) {
				playerB.reduceH(damageToB);
			}

			//  damage for playerA
			int damageToA = attackRollB * playerB.attack - defenseRollA * playerA.S;
			if (damageToA > 0) {
				playerA.reduceH(damageToA);
			}
		}

		if (playerA.isAlive()) {
			System.out.println("Player A wins!");
		} else {
			System.out.println("Player B wins!");
		}
	}
}

public class Main {
	public static void main(String[] args) {
		Player playerA = new Player(50, 5, 10);
		Player playerB = new Player(100, 10, 5);

		MagicalArena arena = new MagicalArena(playerA, playerB);
		arena.fight();
	}
}
