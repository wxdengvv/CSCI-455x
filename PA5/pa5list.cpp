// Name: Wanxin Deng
// USC NetID:
// CS 455 PA5
// Spring 2022

// pa5list.cpp
// a program to test the linked list code necessary for a hash table chain

// You are not required to submit this program for pa5.

// We gave you this starter file for it so you don't have to figure
// out the #include stuff.  The code that's being tested will be in
// listFuncs.cpp, which uses the header file listFuncs.h

// The pa5 Makefile includes a rule that compiles these two modules
// into one executable.

#include <iostream>
#include <string>
#include <cassert>

using namespace std;

#include "listFuncs.h"


int main() {
	string name;
	int num;
	string ope;
	Node* testlist = NULL;
	bool keepgoing = true;

	cout << "testcase1---------------------------------" << endl;
	cout << "a null node list" << endl;
	cout << "test printList function" << endl;
	printList(testlist);

	cout << "test contains function " << endl;
	if (contains(testlist, "test")) {
		cout << "it contains the word" << endl;
	}
	else {
		cout << "it doesn't contain the word" << endl;
	}

	cout << "test remove function " << endl;
	remove(testlist, "test");
	cout << "the list is: " << endl;
	printList(testlist);

	cout << "test size function " << endl;
	cout << "the size is: " << size(testlist) << endl;

	cout << "test insert function " << endl;
	cout << "the list is: " << endl;
	insertFront(testlist, "test", 1);
	printList(testlist);

	//--------------------------------------------------------------------------------------------------
	cout << endl;
	cout << "testcase2----------------------------------" << endl;
	insertFront(testlist, "aa", 2);
	insertFront(testlist, "aw", 3);
	insertFront(testlist, "af", 6);
	cout << "the list is: " << endl;
	printList(testlist);

	cout << "test remove function( remove aa ) " << endl;
	remove(testlist, "aa");
	cout << "the list is: " << endl;
	printList(testlist);
	cout << "test contains function " << endl;
	if (contains(testlist, "test")) {
		cout << "it contains the word test" << endl;
	}
	else {
		cout << "it doesn't contain the word test" << endl;
	}
	if (contains(testlist, "tr")) {
		cout << "it contains the word tr" << endl;
	}
	else {
		cout << "it doesn't contain the word tr" << endl;
	}




	//--------------------------------------------------------------------------------------------------
	cout << endl;
	cout << "testcase3----------------------------------" << endl;
	initList(testlist);
	while (keepgoing) {
		cout << "please enter operation" << endl;
		cin >> ope;

		if(ope=="iF") {
			cout << "enter insert key" << endl;
		    cin >> name;
		    cout << "enter insert val" << endl;
		    cin >> num;
		    insertFront(testlist, name, num);
		    printList(testlist);
		
		}

		else if (ope == "rF") {
			removeFront(testlist);
			cout << "after removing the front data:" << endl;
			printList(testlist);

		}

		else if (ope == "con") {
			cout << "please enter the key"<<endl;
			string target;
			cin >> target;
			if (contains(testlist, target)) {
				cout << "it contains " << target << endl;
			
			}
			else {
				cout << "it doesn't contain " << target << endl;
			}	

		}

		else if (ope == "rem") {
			cout << "please enter the key" << endl;
			string target;
			cin >> target;
			remove(testlist, target);
			cout << "the list is" << endl;
			printList(testlist);

		}

		else if (ope == "size") {
			cout << "the size is: " << size(testlist) << endl;
		}





	
	}





	return 0;
}
