package com.svi.solitaire.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SolitaireGame {

	ArrayList<Card> talon = new ArrayList<Card>();
	ArrayList<Card> talonPile = new ArrayList<Card>();
	ArrayList<Manouevrestack> manouevreStacks = new ArrayList<Manouevrestack>();
	ArrayList<Foundationzone> foundationZones = new ArrayList<Foundationzone>();
	static Deck deck = new Deck();

	int movenumber;
	int move = 0;
	boolean hasMoved;

	public static void main(String[] args) {

		SolitaireGame solitaire = new SolitaireGame();

		System.out.println("^^^^^^^^^^SOLITAIRE^^^^^^^^");
		System.out.println();

		solitaire.play();

	}

	public void play() {

		Collections.shuffle(deck.deckOfCards);

		createFoundationZones(foundationZones);

		createManouevrestacks(manouevreStacks);

		distributToManouevrestacks(deck.deckOfCards);

		cardsMoveToTalon(deck.deckOfCards);

		displayInitialBoard();

		hasMoved = true;

		while (hasMoved) {
			generateMoveString();
		}

	}

	private void createFoundationZones(ArrayList<Foundationzone> foundationZones) {
		for (int x = 0; x < 4; x++) {
			foundationZones.add(new Foundationzone());
		}
	}

	private void createManouevrestacks(ArrayList<Manouevrestack> manouevreStacks) {
		for (int x = 0; x < 7; x++) {
			manouevreStacks.add(new Manouevrestack());
		}

	}

	private void distributToManouevrestacks(ArrayList<Card> deckOfCards) {
		for (int i = 0; i < 7; i++) {
			for (int j = i; j < 7; j++) {

				manouevreStacks.get(j).cardsOfStack.add(0, deckOfCards.remove(0));

			}
		}
		for (int i = 0; i < manouevreStacks.size(); i++) {
			manouevreStacks.get(i).cardsOfStack.get(0).setFaceUp(true);
		}
	}

	private void cardsMoveToTalon(ArrayList<Card> deckOfCards) {
		while (!deckOfCards.isEmpty()) {
			for (int x = 0; x < deckOfCards.size(); x++) {
				talon.add(deckOfCards.remove(0));
				if (deckOfCards.isEmpty()) {
					break;
				}
			}
		}
	}

	private void generateMoveString() {

		int prevmove;

		while (move >= 0) {
			prevmove = move;
			int m2f, cim;
			while (move >= 0) {
				m2f = move;
				moveAceFromManouevreToFoundation();

				if (move == m2f) {
					break;
				}
			}

			while (move >= 0) {
				m2f = move;
				moveCardFromManouevreToFoundation();

				if (move == m2f) {
					break;
				}
			}

			while (move >= 0) {
				cim = move;
				moveCardsInManouevre();

				if (move == cim) {
					break;
				}
			}

			while (move >= 0) {
				cim = move;
				moveKingInManouevre();

				if (move == cim) {
					break;
				}
			}

			if (move == prevmove) {
				break;
			}
		}

		drawThreeFromTalon(talonPile);

		while (move >= 0) {
			prevmove = move;
			int t2fmove, t2mmove;
			while (move >= 0) {
				t2fmove = move;
				moveCardsFromTalonToFoundation();

				if (move == t2fmove) {
					break;
				}
			}

			while (move >= 0) {
				t2mmove = move;
				moveCardsFromTalonToManouevre();

				if (move == t2mmove) {
					break;
				}
			}

			if (move == prevmove) {
				break;
			}
		}

	}

	public void displayMoveNumber() {
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		String moveString = "Move " + ++movenumber;
		System.out.println(moveString);
		System.out.println();
	}

	public void displayTalonCardCount() {

		if (talon.size() > 0) {
			System.out.print("[" + talon.size() + "]" + "\t\t");
		} else if (talon.size() == 0) {
			System.out.print("[   ]\t\t");
		}
	}

	public void displayTopCardOfTalonpile() {

		if (!talonPile.isEmpty()) {
			System.out.println(talonPile.get(0));
		} else {
			System.out.println("[   ]");
		}
	}

	public void displayFoundationZone() {

		for (int j = 0; j < 4; j++) {
			if (foundationZones.get(j).zoneSize() > 0) {

				System.out.print(foundationZones.get(j).cardsOfZone.get(0).toString() + "\t");
			} else {
				System.out.print("[X-X]\t");
			}
		}
		System.out.println();
		System.out.println();
	}

	public void displayManouevrestacks() {
		int maxSize = 0;
		System.out.println();
		System.out.println();
		for (int i = 0; i < manouevreStacks.size(); i++) {
			if (manouevreStacks.get(i).stackSize() > maxSize) {
				maxSize = manouevreStacks.get(i).stackSize();
			}
		}
		// System.out.println(maxSize);
		int n = 0;
		for (int j = 0; j < maxSize; j++) {
			for (int k = 0; k < manouevreStacks.size(); k++) {
				if (j < manouevreStacks.get(k).stackSize()) {

					n = ((manouevreStacks.get(k).stackSize() - 1) - j);

					System.out.print(manouevreStacks.get(k).getCardsOfStack().get(n).toString() + "\t");
				} else {
					System.out.print("     \t");
				}
			}
			System.out.println();
		}

	}

	public void displayBoard() {
		displayMoveNumber();

		displayTalonCardCount();

		displayFoundationZone();

		displayTopCardOfTalonpile();

		displayManouevrestacks();
	}

	public void displayInitialBoard() {
		System.out.println("Initial Position:");
		System.out.println();

		displayTalonCardCount();

		displayFoundationZone();

		displayTopCardOfTalonpile();

		displayManouevrestacks();
	}

	public void moveAceFromManouevreToFoundation() {

		int stackTopRankValue = 0;

		for (int i = 0; i < manouevreStacks.size(); i++) {
			if (manouevreStacks.get(i).getCardsOfStack().isEmpty()) {
				continue;
			}

			stackTopRankValue = this.manouevreStacks.get(i).getTopCard().cardRankValue();

			if (stackTopRankValue == 0) {
				for (Foundationzone f : foundationZones) {
					if (f.cardsOfZone.isEmpty()) {
						if (manouevreStacks.get(i).stackSize() > 0) {
							f.cardsOfZone.add(manouevreStacks.get(i).cardsOfStack.remove(0));
							move += 1;
							f.getTopCard().setFaceUp(true);
							if (manouevreStacks.get(i).stackSize() > 0) {
								manouevreStacks.get(i).cardsOfStack.get(0).setFaceUp(true);
								displayBoard();
								System.out.println();
								System.out.println(f.getTopCard() + ">>> moved from Manouevre to Foundation");
								System.out.println();
								if (!f.cardsOfZone.isEmpty()) {
									break;
								}

							} else {
								if (manouevreStacks.get(i).stackSize() > 0) {
									displayBoard();
									System.out.println();
									System.out.println(f.getTopCard() + ">>> moved from Manouevre to Foundation");
									System.out.println();
									if (!f.cardsOfZone.isEmpty()) {
										break;
									}
								}
							}

						}
					}

				}

			}
		}

	}

	public void moveCardFromManouevreToFoundation() {

		int stackTopRankValue = 0;
		int stackTopSuitValue = 0;
		int zoneTopRankValue = 0;
		int zoneTopSuitValue = 0;

		for (int i = 0; i < manouevreStacks.size(); i++) {

			if (manouevreStacks.get(i).getCardsOfStack().isEmpty()) {
				continue;
			}

			stackTopRankValue = this.manouevreStacks.get(i).getTopCard().cardRankValue();
			stackTopSuitValue = this.manouevreStacks.get(i).getTopCard().cardSuitValue();

			for (int k = 0; k < foundationZones.size(); k++) {
				if (foundationZones.get(k).zoneSize() > 0) {

					zoneTopRankValue = this.foundationZones.get(k).getTopCard().cardRankValue();

					zoneTopSuitValue = this.foundationZones.get(k).getTopCard().cardSuitValue();

					if (stackTopRankValue - 1 == zoneTopRankValue && stackTopSuitValue == zoneTopSuitValue) {

						foundationZones.get(k).cardsOfZone.add(0, manouevreStacks.get(i).cardsOfStack.remove(0));
						move += 1;
						
						if (manouevreStacks.get(i).stackSize() > 0) {
							manouevreStacks.get(i).cardsOfStack.get(0).setFaceUp(true);
						}
						displayBoard();
						System.out.println();
						System.out.println(
								foundationZones.get(k).cardsOfZone.get(0) + ">>> moved from Manouevre to Foundation");
						System.out.println();
						if (manouevreStacks.isEmpty()) {
							break;
						}

					}

				}
			}

		}
	}

	private void drawThreeFromTalon(ArrayList<Card> talonPile) {

		if (talon.size() == 0) {
			while (!talonPile.isEmpty()) {
				for (int r = 0; r < talonPile.size(); r++) {
					talon.add(0, talonPile.remove(0));
					talon.get(0).setFaceUp(false);
					if (talonPile.isEmpty()) {
						break;
					}
				}
			}
			if (talon.size() == 0 && talonPile.size() == 0) {
				System.out.println();
			} else {
				displayBoard();
				System.out.println("Returned Cards to Talon");
			}

			if (move < 1 && talon.size() >= 1) {
				System.out.println("GAMEOVER");
				hasMoved = false;

			} else if (move < 1) {
				int l = 0;
				for (int k = 0; k < foundationZones.size(); k++) {
					l += foundationZones.get(k).zoneSize();

				}
				if (l == 52) {
					System.out.println("YOU WIN");
					hasMoved = false;

				} else {
					System.out.println("GAMEOVER");
					hasMoved = false;

				}
			}
			move = 0;

		}

		else if (talon.size() >= 3)

		{

			for (int r = 0; r < 3; r++) {

				talonPile.add(0, talon.remove(0));
				talonPile.get(0).setFaceUp(true);

			}

			displayBoard();
			System.out.println("Draw Three Cards From Talon");

		} else if (talon.size() == 2) {
			for (int r = 0; r < 2; r++) {
				talonPile.add(0, talon.remove(0));
				talonPile.get(0).setFaceUp(true);
			}

			displayBoard();
			System.out.println("Draw Two Cards From Talon");

		}

		else if (talon.size() == 1) {
			talonPile.add(0, talon.remove(0));
			talonPile.get(0).setFaceUp(true);

			displayBoard();
			System.out.println("Draw One Card From Talon");

		}
	}

	private void moveCardsFromTalonToFoundation() {
		int pileTopCardRank = 0;
		int pileTopCardSuit = 0;
		int zoneTopRankValue = 0;
		int zoneTopSuitValue = 0;

		int c = 0;

		if (talonPile.size() > 0) {
			pileTopCardRank = this.talonPile.get(c).cardRankValue();
			while (pileTopCardRank == 0) {
				System.out.println();

				for (Foundationzone f : foundationZones) {

					if (f.cardsOfZone.isEmpty() && this.talonPile.size() > 0) {

						f.cardsOfZone.add(0, talonPile.remove(c));
						move += 1;
						f.getTopCard().setFaceUp(true);
						displayBoard();
						System.out.println();
						System.out.println(f.getTopCard() + ">>> moved From Talon to Foundation");
						System.out.println();
						if (talonPile.size() > 0) {
							pileTopCardRank = this.talonPile.get(c).cardRankValue();
							break;
						}
					}

				}
				if (talonPile.size() == 0) {

					break;
				}
			}
			if (talonPile.size() > 0) {
				pileTopCardSuit = this.talonPile.get(c).cardSuitValue();
				pileTopCardRank = this.talonPile.get(c).cardRankValue();

				for (int j = 0; j < foundationZones.size(); j++) {
					if (foundationZones.get(j).zoneSize() > 0) {
						zoneTopRankValue = this.foundationZones.get(j).getTopCard().cardRankValue();
						zoneTopSuitValue = this.foundationZones.get(j).getTopCard().cardSuitValue();

						if (pileTopCardRank - 1 == zoneTopRankValue && pileTopCardSuit == zoneTopSuitValue) {

							foundationZones.get(j).cardsOfZone.add(0, talonPile.remove(0));
							move += 1;
							foundationZones.get(j).cardsOfZone.get(0).setFaceUp(true);
							displayBoard();
							System.out.println();
							System.out.println(
									foundationZones.get(j).cardsOfZone.get(0) + ">>> moved from Talon to Foundation");
							System.out.println();

							if (talonPile.size() > 0) {
								pileTopCardSuit = this.talonPile.get(c).cardSuitValue();
								pileTopCardRank = this.talonPile.get(c).cardRankValue();

								if (talonPile.isEmpty()) {
									break;
								}

							}

						}

					}

				}
			}
		}
	}

	public void moveCardsFromTalonToManouevre() {

		int pileTopCardRank = 0;

		int pileTopCardColor = 0;

		int stackTopRankValue = 0;
		int stackTopColorValue = 0;
		int c = 0;
		if (!talonPile.isEmpty()) {
			pileTopCardRank = this.talonPile.get(c).cardRankValue();
			pileTopCardColor = this.talonPile.get(c).cardColor();

			for (int k = 0; k < manouevreStacks.size(); k++) {
				if (manouevreStacks.get(k).stackSize() > 0) {
					stackTopRankValue = this.manouevreStacks.get(k).getTopCard().cardRankValue();
					stackTopColorValue = this.manouevreStacks.get(k).getTopCard().cardColor();

					// added talonPile size check to fix out of bounds
					if (pileTopCardRank + 1 == stackTopRankValue && pileTopCardColor != stackTopColorValue
							&& this.talonPile.size() > 0) {

						manouevreStacks.get(k).cardsOfStack.add(0, talonPile.remove(0));
						move += 1;
						manouevreStacks.get(k).cardsOfStack.get(0).setFaceUp(true);
						displayBoard();
						System.out.println();
						System.out.println(
								manouevreStacks.get(k).cardsOfStack.get(0) + ">>> moved from Talon to Manouevre");
						System.out.println();

						if (talonPile.size() > 0) {
							pileTopCardRank = this.talonPile.get(c).cardRankValue();
							pileTopCardColor = this.talonPile.get(c).cardColor();

							if (talonPile.isEmpty()) {
								break;
							}

						}

					}

				} else if (manouevreStacks.get(k).stackSize() == 0) {
					// added if to fix out of bounds
					if (this.talonPile.size() > 0) {
						pileTopCardRank = this.talonPile.get(0).cardRankValue();
						if (pileTopCardRank == 12) {
							manouevreStacks.get(k).cardsOfStack.add(0, talonPile.remove(0));
							move += 1;
							displayBoard();
							System.out.println();
							System.out.println(
									manouevreStacks.get(k).cardsOfStack.get(0) + ">>> moved from Talon to Manouevre");
							System.out.println();

						}
					}
				}

			}
		}

	}

	public void moveCardsInManouevre() {

		int stackTopRankValue = 0;
		int stackTopColorValue = 0;
		int stackNextBottomRankValue = 0;
		int stackNextBottomColorValue = 0;

		for (int k = 0; k < manouevreStacks.size(); k++) {
			if (manouevreStacks.size() > 0) {

				for (int j = manouevreStacks.get(k).stackSize() - 1; j >= 0; j--) {
					if (manouevreStacks.get(k).stackSize() > 0) {
						if (manouevreStacks.get(k).cardsOfStack.get(j).isFaceUp() == 1) {
							stackNextBottomRankValue = this.manouevreStacks.get(k).cardsOfStack.get(j).cardRankValue();

							stackNextBottomColorValue = this.manouevreStacks.get(k).cardsOfStack.get(j).cardColor();

							for (int i = 0; i < manouevreStacks.size(); i++) {
								if (manouevreStacks.get(i).stackSize() > 0) {
									// this.manouevreStacks.get(i).getTopCard();
									stackTopRankValue = this.manouevreStacks.get(i).getTopCard().cardRankValue();
									stackTopColorValue = this.manouevreStacks.get(i).getTopCard().cardColor();

									if (stackNextBottomRankValue + 1 == stackTopRankValue
											&& stackNextBottomColorValue != stackTopColorValue) {

										List<Card> tempCards = new ArrayList<>(
												manouevreStacks.get(k).cardsOfStack.subList(0, j + 1));
										manouevreStacks.get(k).cardsOfStack.removeAll(tempCards);

										manouevreStacks.get(i).cardsOfStack.addAll(0, tempCards);
										move += 1;

										if (manouevreStacks.get(k).stackSize() > 0) {
											manouevreStacks.get(k).cardsOfStack.get(0).setFaceUp(true);
											displayBoard();
											System.out.println();
											System.out.println(tempCards.get(tempCards.size() - 1)
													+ ">>> moved from Manouevre to Manouevre");
											System.out.println();

										} else if (manouevreStacks.get(k).stackSize() == 0) {
											displayBoard();
											System.out.println();
											System.out.println(tempCards.get(tempCards.size() - 1)
													+ ">>> moved from Manouevre to Manouevre");
											System.out.println();
										}

										return;
									}
								}

							}
							break;
						} else {
							continue;
						}

					}
				}
			}

		}
	}

	public void moveKingInManouevre() {

		int stackNextBottomRankValue;

		for (int m = 0; m < manouevreStacks.size(); m++) {
			if (manouevreStacks.get(m).stackSize() > 0) {

				for (int n = manouevreStacks.get(m).stackSize() - 1; n >= 0; n--) {
					if (manouevreStacks.get(m).stackSize() > 0) {
						if (manouevreStacks.get(m).cardsOfStack.get(n).isFaceUp() == 1) {
							stackNextBottomRankValue = this.manouevreStacks.get(m).cardsOfStack.get(n).cardRankValue();
						} else {
							continue;
						}
						for (int i = 0; i < manouevreStacks.size(); i++) {
							if (manouevreStacks.get(i).stackSize() == 0) {

								if (stackNextBottomRankValue == 12 && !manouevreStacks.get(m).getCardsOfStack()
										.get(manouevreStacks.get(m).cardsOfStack.size() - 1).faceUp) {
									List<Card> tempCards = new ArrayList<>(
											manouevreStacks.get(m).cardsOfStack.subList(0, n + 1));
									manouevreStacks.get(m).cardsOfStack.removeAll(tempCards);
									manouevreStacks.get(i).cardsOfStack.addAll(0, tempCards);
									move += 1;
									if (manouevreStacks.get(m).stackSize() > 0) {
										manouevreStacks.get(m).cardsOfStack.get(0).setFaceUp(true);
										displayBoard();
										System.out.println();
										System.out.println(tempCards.get(tempCards.size() - 1)
												+ ">>> moved King Manouevre to Manouevre");
										System.out.println();

									}

									return;
								}

							}
						}

					} else {
						continue;
					}
				}
			}
		}
	}
}
