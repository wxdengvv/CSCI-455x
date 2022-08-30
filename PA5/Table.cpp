// Name: Wanxin Deng
// USC NetID:
// CSCI 455 PA5
// Spring 2022

// Table.cpp  Table class implementation


#include "Table.h"

#include <iostream>
#include <string>
#include <cassert>

// for hash function called in private hashCode method defined below
#include <functional>

using namespace std;


// listFuncs.h has the definition of Node and its methods.  -- when
// you complete it it will also have the function prototypes for your
// list functions.  With this #include, you can use Node type (and
// Node*, and ListType), and call those list functions from inside
// your Table methods, below.

#include "listFuncs.h"


//*************************************************************************


Table::Table() {
	table = new ListType[HASH_SIZE];
	hashSize = HASH_SIZE;
	for (int i = 0; i < hashSize; i++) {
		table[i] = NULL;
	}

}


Table::Table(unsigned int hSize) {
	table = new ListType[hSize];
	hashSize = hSize;
	for (int i = 0; i < hashSize; i++) {
		table[i] = NULL;
	}
}


int* Table::lookup(const string& key) {
	unsigned int hcode = hashCode(key);
	
	if (contains(table[hcode], key)) {
		
		Node* p = table[hcode];
		while (p!=NULL) {
			if (p->key == key) {
				return &p->value;
			}
			else {
				p = p->next;
			}

		}
		
	}
	else {
		return NULL;  // dummy return value for stub
	}
}


bool Table::remove(const string& key) {
	unsigned int hcode = hashCode(key);
	if (contains(table[hcode], key)) {
		removeNode(table[hcode], key);
		return true;
	}
	else {
		return false;  // dummy return value for stub
	}
}


bool Table::insert(const string& key, int value) {
	unsigned int hcode = hashCode(key);

	if (!contains(table[hcode], key)) {
		insertFront(table[hcode], key, value);
		return true;
	}
	else{
		return false;  // dummy return value for stub
	}

}


int Table::numEntries() const {
	int num = 0;
	for (int i = 0; i < hashSize; i++) {
			num += size(table[i]);
	}

	return num;      // dummy return value for stub
}


void Table::printAll() const {
	for (int i = 0; i < hashSize; i++) {
		printList(table[i]);
	}

}

// Sample output from this function
	//   number of buckets: 997
	//   number of entries: 10
	//   number of non-empty buckets: 9
	//   longest chain: 2
void Table::hashStats(ostream& out) const {
	cout << "number of buckets: " << hashSize << endl;
	cout << "number of entries: " << numEntries() << endl;
	cout << "number of non-empty buckets: " << nonEmpty() << endl;
	cout << "longest chain: " << longestChain() << endl;
}


// hash function for a string
// (we defined it for you)
// returns a value in the range [0, hashSize)
unsigned int Table::hashCode(const string& word) const {

	// Note: calls a std library hash function for string (it uses the good hash
	//   algorithm for strings that we discussed in lecture).
	return hash<string>()(word) % hashSize;

}


// add definitions for your private methods here
int Table::nonEmpty() const {
	int num = 0;
	for (int i = 0; i < hashSize; i++) {
		if (size(table[i]) != 0) {
			num++;
		}
	}

	return num;      // dummy return value for stub
}


int Table::longestChain() const {
	int length = 0;
	for (int i = 0; i < hashSize; i++) {
		if (size(table[i]) > length) {
			length = size(table[i]);
		}
	}

	return length;      // dummy return value for stub
}
