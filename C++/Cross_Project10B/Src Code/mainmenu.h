/*
 * Name: Ebony Cross
 * Class: CS 226
 * Project 10B: mainmenu.h
 * Instructor: Tsai
 * Description: Implement user input, validation, file input/output streams, while loops, functions, implementation of classes, structures, pointers and display module menus
 * Due: 11/23/2015
 * Platform/Compiler: Xcode
 */


#ifndef PROJECT3_CROSS_MAINMENU_H  //Project10B_Cross_mainmenu_h
#define PROJECT3_CROSS_MAINMENU_H  //Project10B_Cross_mainmenu_h
#include <string>
#include <cstring>
#include <fstream>
using namespace std;

class BookData
{
    private:
        char bookTitle[51]; //holds title of each book in inventory
        char isbn[14]; //holds ISBN of each book
        char author[31]; //hold name of the author of each book
        char publisher[31]; //hold name of publisher of each book
        char dateAdded[11]; // hold date of each book, sotre in the form mm-dd-yy
        int qtyOnHand; // hold the quantity of the each book
        double wholesale; //hold the wholesale price of each book
        double retail; //hold the retail price of each book
    public:
    //default constructor
        BookData ();
    //constructor
        BookData (char [], char [], char[], char [],char [], int, double, double);
    //setters
        void removeBook(BookData s[], int index);
        void setTitle(char[]);
        void setISBN(char[]);
        void setAuthor(char[]);
        void setPub(char[]);
        void setDateAdded(char[]);
        void setQty(int);
        void setWholesale(double);
        void setRetail(double);
        int isEmpty(BookData [], int);
    
    //getters
    const char *getTitle() const
    {return bookTitle;}
    const  char *getIBSN() const{ return isbn;}
    const char *getAuthor() const {return author;}
    const char *getPub() const { return publisher;}
    const char *getDateAdded() {return dateAdded;}
    const int getQty() {return qtyOnHand;}
    const double getWholesale() {return wholesale;}
    const double getRetail() {return retail;}
    
    //other functions
    bool bookMatch(BookData[], int, char[]);
    int printMainMenu();
    int printReportMenu();
    int printInvMenu();
  
};


//prototypes
void strUpper(char *ptr);
bool openFileIn(fstream &, string);
void writeToFile(fstream &, string fileName);
int searchFile(fstream &, string name, char value[], int size);
void addFileContents(fstream &file);
void bookinfo(BookData info[], int size);
//void removeBook(BookData s[], int index);
void cashier();
void invmenu();
void reports();
void lookupBook();
void addBook();
void editBook();
void deleteBook();
void repListing();
void repWholesale();
void repRetail();
void repQty();
void repCost();
void repAge();
int searchISBN(BookData list[], int size, char[]);
int isEmpty(BookData [], int);
void dateSort(BookData s[], int size);
void costSort(BookData s[], int size);
void QtySort(BookData s[], int size);
double totalSum(BookData *arr, BookData *arr_ptr2, int size);
string convert(string);
int searchList(BookData list[], int size, char value[]);
int strSearch(BookData list[], int size, char value[]);
int strSearchISBN(BookData list[], int size, char value[]);
int strSearch2(char list [][11], int size, char value[][11]);
void setTitle(BookData *s);
#endif //end of header file 