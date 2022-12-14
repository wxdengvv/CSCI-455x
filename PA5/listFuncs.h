// Name: Wanxin Deng
// USC NetID:
// CSCI 455 PA5
// Spring 2022


//*************************************************************************
// Node class definition 
// and declarations for functions on ListType

// Note: we don't need Node in Table.h
// because it's used by the Table class; not by any Table client code.

// Note2: it's good practice to *not* put "using" statement in *header* files.  Thus
// here, things from std libary appear as, for example, std::string

#ifndef LIST_FUNCS_H
#define LIST_FUNCS_H

#include <string>


struct Node {
	std::string key;
	int value;

	Node* next;

	Node(const std::string& theKey, int theValue);

	Node(const std::string& theKey, int theValue, Node* n);
};


typedef Node* ListType;

//*************************************************************************
//add function headers (aka, function prototypes) for your functions
//that operate on a list here (i.e., each includes a parameter of type
//ListType or ListType&).  No function definitions go in this file.

//initiate the list
void initList(ListType& list);

//insert front
void insertFront(ListType& list, const std::string name, int num);

//print the list
void printList(ListType list);

//
void removeFront(ListType& list);

//
bool contains(ListType list, const std::string name);

//
bool removeNode(ListType& list, const std::string name);

//
int size(ListType list);













// keep the following line at the end of the file
#endif
