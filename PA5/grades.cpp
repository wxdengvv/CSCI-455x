// Name: Wanxin Deng
// USC NetID:
// CSCI 455 PA5
// Spring 2022

/*
 * grades.cpp
 * A program to test the Table class.
 * How to run it:
 *      grades [hashSize]
 *
 * the optional argument hashSize is the size of hash table to use.
 * if it's not given, the program uses default size (Table::HASH_SIZE)
 *
 */

#include "Table.h"

 // cstdlib needed for call to atoi
#include <cstdlib>

using namespace std;

int main(int argc, char* argv[]) {

    // gets the hash table size from the command line


    int hashSize = Table::HASH_SIZE;

    Table* grades;  // Table is dynamically allocated below, so we can call
    // different constructors depending on input from the user.

    if (argc > 1) {
        hashSize = atoi(argv[1]);  // atoi converts c-string to int

        if (hashSize < 1) {
            cout << "Command line argument (hashSize) must be a positive number"
                << endl;
            return 1;
        }

        grades = new Table(hashSize);

    }
    else {   // no command line args given -- use default table size
        grades = new Table();
    }


    grades->hashStats(cout);

    // add more code here
    // Reminder: use -> when calling Table methods, since grades is type Table*
    string cmd;
    cout << "cmd> ";
    while (true) {
        cin >> cmd;
        //for insert
        if (cmd == "insert") {
            string key;
            int value;
            cin >> key;
            cin >> value;
            if (grades->insert(key, value)) {
                cout << "cmd>";
            }
            else {
                cout << "insert fail";
            }

        }
        //for print
        if (cmd == "print") {
            grades->printAll();
            cout << "cmd>";
        }

        //for print
        if (cmd == "quit") {
            break;
        }
        if (cmd == "remove") {
            string key;
            cin >> key;
            if (!grades->remove(key)) {
                cout << "remove fail";
            }
            cout << "cmd>";
        }


    }

    return 0;
}
