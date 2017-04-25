package A03_DoubleLinkedList;

public class DoubleLinkedList<T> {

	Node aktu = null;
	Node last = null;
	Node first = null;

	// absolut

	/**
	 * Einfügen einer neuen <T>
	 * 
	 * @param a
	 *            <T>
	 */
	public void add(T a) {

		if (first == null) {
			Node node = new Node<T>(a);
			node.setNext(null);
			node.setPrevious(null);
			aktu = node;
			first = aktu;
			last = aktu;
		} else {
			aktu = last;
			Node node = new Node<T>(a);
			node.setPrevious(aktu);
			last.setNext(node);
			last = last.getNext();
			aktu = null;
		}
	}

	/**
	 * Internen Zeiger für next() zurücksetzen
	 */
	public void reset() {
		aktu = first;
	}

	/**
	 * analog zur Funktion reset()
	 */
	public void resetToLast() {
		aktu = last;
	}

	/**
	 * Liefert erste Node der Liste retour oder null, wenn Liste leer
	 * 
	 * @return Node|null
	 */
	public Node<T> getFirst() {
		return first;
	}

	/**
	 * Liefert letzte Node der Liste retour oder null, wenn Liste leer
	 * 
	 * @return Node|null
	 */
	public Node<T> getLast() {
		return last;
	}

	/**
	 * Gibt aktuelle <T> zurück und setzt internen Zeiger weiter. Falls current
	 * nicht gesetzt, wird null retourniert.
	 * 
	 * @return <T>|null
	 */
	public T next() {
		// Beschreibung bei previous()
		if (aktu == null) {
			return null;
		} else if (last.equals(aktu)) {
			aktu = null;
			return (T) last.getData();
		} else if (last.equals(aktu.getNext())) {
			aktu = aktu.getNext();
			return (T) aktu.getPrevious().getData();
		} else {
			aktu = aktu.getNext();
			return (T) aktu.getPrevious().getData();
		}
	}

	/**
	 * analog zur Funktion next()
	 * 
	 * @return <T>|null
	 */
	public T previous() {
		// aktu == null ist dann, wenn aktu einen schritt zuvor first war;
		if (aktu == null) {
			return null;
		} else if (first.equals(aktu)) {
			// wir sind am Ende, deswegen setzen wir aktu auf null (für die
			// Abfrage oberhalb) und geben den ersten Wert zurück;
			aktu = null;
			return (T) first.getData();
		} else if (first.equals(aktu.getPrevious())) {
			// überprüft, ob der Knoten vor dem aktuellen der letzte ist, dass
			// hat den Grund, dass beim letzten die Methode .getPrevios eine
			// NullPointerException wirft;
			aktu = first;
			return (T) first.getNext().getData();
		} else {
			// das ist der Standardfall solange der nicht beim vorletzten Knoten
			// angekommen ist.
			aktu = aktu.getPrevious();
			return (T) aktu.getNext().getData();
		}

	}

	/**
	 * Current-Pointer auf nächste <T> setzen (aber nicht auslesen). Ignoriert
	 * still, dass current nicht gesetzt ist.
	 */
	public void moveNext() throws CurrentNotSetException {
		try {
			aktu = aktu.getNext();
		} catch (Exception e) {
			throw new CurrentNotSetException();
		}

	}

	/**
	 * Analog zur Funktion moveNext()
	 */
	public void movePrevious() throws CurrentNotSetException {
		try {
			aktu = aktu.getPrevious();
		} catch (Exception e) {
			throw new CurrentNotSetException();
		}

	}

	/**
	 * Retourniert aktuelle (current) <T>, ohne Zeiger zu ändern
	 * 
	 * @return <T>
	 * @throws CurrentNotSetException
	 */
	public T getCurrent() throws CurrentNotSetException {
		try {
			return (T) aktu.getData();
		} catch (Exception e) {
			throw new CurrentNotSetException();
		}
	}

	/**
	 * Gibt <T> an bestimmter Position zurück
	 * 
	 * @param pos
	 *            Position, Nummerierung startet mit 1
	 * @return <T>|null
	 */
	public T get(int pos) {
		aktu = first;
		int cnt = 1;
		while (aktu != last || cnt != pos) {
			if (cnt == pos) {
				return (T) aktu.getData();
			}
			cnt++;
			aktu = aktu.getNext();
		}
		return null;
	}

	/**
	 * Entfernen des Elements an der angegebenen Position. Falls das entfernte
	 * Element das aktuelle Element ist, wird current auf null gesetzt.
	 * 
	 * @param pos
	 */
	public void remove(int pos) throws CurrentNotSetException {
		try {
			Node temp = first;
			int cnt = 1;
			boolean b1 = false;
			if (first != null || last != null || aktu != null) {
				do {
					if (cnt == pos) {
						if (temp.equals(aktu)) {
							aktu = null;
						}
						if (temp.equals(first)) {
							first = temp.getNext();
							temp = first;
						} else if (temp.equals(last)) {
							last = temp.getPrevious();
							temp = last;
						} else {
							temp.getNext().setPrevious(temp.getPrevious());
							temp.getPrevious().setNext(temp.getNext());
						}
					}
					if (b1) {
						break;
					} else if (temp.equals(last)) {
						b1 = true;
					}

					temp = temp.getNext();
					cnt++;
				} while (true);
			}

		} catch (Exception e) {
			throw new CurrentNotSetException();
		}
	}

	/**
	 * Entfernt das aktuelle Element. Als neues aktuelles Element wird der
	 * Nachfolger gesetzt oder (falls kein Nachfolger) das vorhergehende Element
	 * 
	 * @throws CurrentNotSetException
	 */
	public void removeCurrent() throws CurrentNotSetException {

		try {
			if (!aktu.equals(null)) {
				// entfernt das aktuelle Element nur, wenn es ein aktuelles
				// Element gibt sonst wirft er Exception
				if (aktu.equals(last)) {
					// schaut, ob das schon das letzte element ist, dann wird
					// die verbindung unterbrochen;
					aktu.getPrevious().setNext(null);
					last = aktu.getPrevious();
					aktu = last;
				} else if (first.equals(aktu)) {
					aktu.getNext().setPrevious(null);
					first = aktu.getNext();
					aktu = first;
				} else {
					aktu.getPrevious().setNext(aktu.getNext());
					aktu.getNext().setPrevious(aktu.getPrevious());
					aktu = aktu.getNext();
				}
			}
		} catch (Exception e) {
			throw new CurrentNotSetException();
		}

	}

	/**
	 * Die Methode fügt die übergebene <T> nach der aktuellen (current) ein und
	 * setzt dann die neu eingefügte <T> als aktuelle (current) <T>.
	 * 
	 * @throws CurrentNotSetException
	 */
	public void insertAfterCurrentAndMove(T a) throws CurrentNotSetException {
		try {
			if (aktu.equals(null)) {
				aktu = new Node<T>(a);
				last = aktu;
				first = aktu;
			} else {
				Node temp = aktu.getNext();
				aktu.setNext(new Node<T>(a));
				aktu.getNext().setPrevious(aktu);
				aktu = aktu.getNext();
				aktu.setNext(temp);
			}
		} catch (Exception e) {
			throw new CurrentNotSetException();
		}
	}
}
