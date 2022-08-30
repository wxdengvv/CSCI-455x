// Name: Wanxin Deng
// USC NetID:
// CSCI 455 PA5
// Spring 2022


#include <iostream>

#include <cassert>

#include "listFuncs.h"

using namespace std;

Node::Node(const string& theKey, int theValue) {
	key = theKey;
	value = theValue;
	next = NULL;
}

Node::Node(const string& theKey, int theValue, Node* n) {
	key = theKey;
	value = theValue;
	next = n;
}




//*************************************************************************
// put the function definitions for your list functions below

//initiate the list
void initList(ListType& list) {
	list = NULL;
}

//insert at front
void insertFront(ListType& list, const string name, int num) {
	if (!contains(list, name)) {
		Node* newguy = new Node(name, num);

		newguy->next = list;

		list = newguy;
	}
}


//print the list
void printList(ListType list) {

	if (list != NULL) {


		Node* p = list;
		while (p != NULL) {
			cout << p->key << " " << p->value << endl;
			p = p->next;
		}

	}
}

//
void removeFront(ListType& list) {
	if(list != NULL) {
		Node* save = list->next;
		delete list;
		list = save;
	}
	else {
		cout << "the list is null" << endl;	
	}
}

//
bool contains(ListType list, const string name) {
	Node* p = list;
	while (p != NULL) {	
		if (p->key == name) {
			return true;
		}
		else {
			p = p->next;
		}
	}
		return false;
}

//
bool removeNode(ListType& list, const string name) {
	if (contains(list, name)) {

		Node* p = list;
		if (p->key == name) {    //special case
			removeFront(list);
			return true;
		}

		else {

			while (p != NULL) {
				if (p->next->key == name) {
					if (p->next->next != NULL) {
						p->next = p->next->next;
					}
					else {
						p->next = NULL;
					}
					return true;
				}
				else {
					p = p->next;
				}
			}

		}
	}
	else {
		cout << "can't remove because the list doesn't contain the key" << endl;
		return false;
	
	}
}

//
int size(ListType list){
	int size = 0;
	while (list != NULL) {
		size++;
		list = list->next;
	}
	return size;
}



//
